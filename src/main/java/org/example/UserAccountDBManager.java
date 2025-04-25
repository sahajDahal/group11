package org.example;
import java.sql.*;


public class UserAccountDBManager {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/task_manager";
    private static final String DB_USER = "guest";
    private static final String DB_PASS = "123456";
    
    // Reuse uniqueness check
    public static boolean isUnique(Connection conn, String table, String column, String value) throws SQLException {
        String query = "SELECT COUNT(*) FROM " + table + " WHERE " + column + " = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, value);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return rs.getInt(1) == 0;
        }
    }

    // Reuse validations from CreateUserAccount and connect to DB
    public static String createUser(String username, String email, String password) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {

            // Reuse validation logic
            if (!CreateUserAccount.isUsernameLengthValid(username)) return "Username length invalid";
            if (!isUnique(conn, "UserAccountInfo", "LoginID", username)) return "Username already taken";

            if (!CreateUserAccount.isEmailValidFormat(email)) return "Email format invalid";
            if (!isUnique(conn, "UserAccountInfo", "Email", email)) return "Email already registered";

            if (CreateUserAccount.isPasswordTooShort(password)) return "Password too short";
            if (CreateUserAccount.isPasswordTooLong(password)) return "Password too long";
            if (!CreateUserAccount.hasLetterDigitSpecial(password)) return "Password must include a letter, digit, and special character";

            String insertQuery = "INSERT INTO UserAccountInfo (LoginID, Email, Password) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(insertQuery)) {
                stmt.setString(1, username);
                stmt.setString(2, email);
                stmt.setString(3, password);
                stmt.executeUpdate();
            }

            return "Account created successfully";

        } catch (SQLException e) {
            return "Database error: " + e.getMessage();
        }
    }

    // MAIN FOR TESTING
    public static void main(String[] args) {
        String result = createUser("newUser1234", "new@example.com", "New@1234");
        System.out.println(result);
    }
}
