package org.example;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class createTaskClass {
    private List<Task> tasks;

    public createTaskClass() {
        tasks = new ArrayList<>();
    }

    /**
     * Creates a new task and adds it to the task list.
     *
     * @param name the name of the task
     * @param description a brief description of the task
     * @param scheduledTime the time the task is scheduled to run
     */
    public void createTask(String name, String description, LocalDateTime scheduledTime) {
        Task task = new Task(name, description, scheduledTime);
        tasks.add(task);
        System.out.println("Task created: " + task);
    }

    /**
     * Getter for the list of tasks.
     * @return the list of tasks.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Main method for testing task creation interactively.
     */
    public static void main(String[] args) {
        createTaskClass taskCreator = new createTaskClass();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter task name: ");
        String name = scanner.nextLine();

        System.out.print("Enter task description: ");
        String description = scanner.nextLine();

        System.out.print("Enter scheduled time (format: yyyy-MM-ddTHH:mm): ");
        String scheduledTimeStr = scanner.nextLine();

        LocalDateTime scheduledTime;
        try {
            scheduledTime = LocalDateTime.parse(scheduledTimeStr);
        } catch (Exception e) {
            System.out.println("Invalid date/time format. Please use the format yyyy-MM-ddTHH:mm");
            scanner.close();
            return;
        }

        taskCreator.createTask(name, description, scheduledTime);

        System.out.println("\nCurrent Tasks:");
        for (Task task : taskCreator.getTasks()) {
            System.out.println(task);
        }

        scanner.close();
    }
}

/**
 * A simple Task class representing a task with a name, description, and scheduled time.
 */


class Task {
    private String name;
    private String description;
    private LocalDateTime scheduledTime;
    private String status; // New field for task status

    public Task(String name, String description, LocalDateTime scheduledTime) {
        this.name = name;
        this.description = description;
        this.scheduledTime = scheduledTime;
        this.status = "Pending"; // Default status value
    }

    // Getters for testing purposes
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getScheduledTime() {
        return scheduledTime;
    }

    public String getStatus() {
        return status;
    }

    // New setter method for status
    public void setStatus(String status) {
        this.status = status;
    }
}

