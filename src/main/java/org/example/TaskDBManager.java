package org.example;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class TaskDBManager {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/task_manager";
    private static final String DB_USER = "guest";
    private static final String DB_PASS = "123456";

    // Check if task with same name exists for the same user
    public static boolean isTaskNameUniqueForUser(Connection conn, String loginID, String taskName) throws SQLException {
        String query = "SELECT COUNT(*) FROM TaskDescription WHERE LoginID = ? AND TaskName = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, loginID);
            stmt.setString(2, taskName);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return rs.getInt(1) == 0;
        }
    }

    // Create a new task for a user
    public static String createTask(String loginID, String taskName, String taskDescription, String scheduledTimeStr) {
        if (loginID == null || loginID.trim().isEmpty()) return "LoginID is required";
        if (taskName == null || taskName.trim().isEmpty()) return "Task name is required";
        if (taskDescription == null || taskDescription.trim().isEmpty()) return "Task description is required";
        if (scheduledTimeStr == null || scheduledTimeStr.trim().isEmpty()) return "Scheduled time is required";

        LocalDateTime scheduledTime;
        try {
            scheduledTime = LocalDateTime.parse(scheduledTimeStr);
        } catch (DateTimeParseException e) {
            return "Invalid scheduled time format. Use yyyy-MM-ddTHH:mm";
        }

        if (scheduledTime.isBefore(LocalDateTime.now())) return "Scheduled time must be in the future";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            if (!isTaskNameUniqueForUser(conn, loginID, taskName)) return "You already have a task with this name";

            String insertQuery = "INSERT INTO TaskDescription (LoginID, TaskName, TaskDescription, ScheduledTime) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(insertQuery)) {
                stmt.setString(1, loginID);
                stmt.setString(2, taskName);
                stmt.setString(3, taskDescription);
                stmt.setTimestamp(4, Timestamp.valueOf(scheduledTime));
                stmt.executeUpdate();
            }

            return "Task created successfully";

        } catch (SQLException e) {
            return "Database error: " + e.getMessage();
        }
    }

    // MAIN FOR TESTING
    public static void main(String[] args) {
        String result = createTask(
            "admin123",
            "Clean garage",
            "Organize tools and sweep the floor",
            "2025-08-01T13:00"
        );
        System.out.println(result);
    }
}
