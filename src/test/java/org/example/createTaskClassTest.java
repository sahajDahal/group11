package org.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.time.LocalDateTime;
import java.util.List;

public class createTaskClassTest {

    private createTaskClass taskCreator;

    @BeforeEach
    public void setUp() {
        taskCreator = new createTaskClass();
    }

    @AfterEach
    public void tearDown() {
        taskCreator = null;
    }

    @Test
    public void testCreateTask() {
        String taskName = "Sample Task";
        String description = "This is a sample task";
        LocalDateTime scheduledTime = LocalDateTime.of(2025, 4, 1, 10, 0);

        // Create the task
        taskCreator.createTask(taskName, description, scheduledTime);

        // Retrieve the list of tasks
        List<Task> tasks = taskCreator.getTasks();
        assertNotNull(tasks, "Task list should not be null.");
        assertEquals(1, tasks.size(), "Task list should contain one task.");

        Task task = tasks.get(0);
        assertEquals(taskName, task.getName(), "Task name should match.");
        assertEquals(description, task.getDescription(), "Task description should match.");
        assertEquals(scheduledTime, task.getScheduledTime(), "Scheduled time should match.");
    }
}
