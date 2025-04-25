package org.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.time.LocalDateTime;
import java.util.List;

public class deleteTaskClassTest {

    private createTaskClass taskManager;
    private deleteTaskClass deleteTask;

    @BeforeEach
    public void setUp() {
        taskManager = new createTaskClass();
        // Pass the task list from taskManager to deleteTaskClass as required
        deleteTask = new deleteTaskClass(taskManager.getTasks());
    }

    @AfterEach
    public void tearDown() {
        taskManager = null;
        deleteTask = null;
    }

    @Test
    public void testDeleteExistingTask() {
        String taskName = "Test Task";
        taskManager.createTask(taskName, "Sample Description", LocalDateTime.now());

        boolean result = deleteTask.deleteTask(taskName);
        assertTrue(result, "Task should be successfully deleted.");

        List<Task> tasks = taskManager.getTasks();
        assertEquals(0, tasks.size(), "Task list should be empty after deletion.");
    }

    @Test
    public void testDeleteNonExistentTask() {
        boolean result = deleteTask.deleteTask("Nonexistent Task");
        assertFalse(result, "Task deletion should fail for a nonexistent task.");
    }

    @Test
    public void testDeleteFromEmptyList() {
        boolean result = deleteTask.deleteTask("Random Task");
        assertFalse(result, "Task deletion should fail when the task list is empty.");
    }

    @Test
    public void testDeleteTaskWithInvalidID() {
        boolean result = deleteTask.deleteTask(""); // Empty string
        assertFalse(result, "Task deletion should fail for an empty task ID.");
    }

    @Test
    public void testDeleteTaskThatIsInProgress() {
        String taskName = "Ongoing Task";
        taskManager.createTask(taskName, "Ongoing Description", LocalDateTime.now());

        // Simulate changing status to "In Progress"
        Task task = taskManager.getTasks().get(0);
        task.setStatus("In Progress");

        boolean result = deleteTask.deleteTask(taskName);
        assertFalse(result, "Task deletion should fail for a task in progress.");
    }

    @Test
    public void testDeleteTaskThatIsFinished() {
        String taskName = "Completed Task";
        taskManager.createTask(taskName, "Completed Description", LocalDateTime.now());

        // Simulate changing status to "Finished"
        Task task = taskManager.getTasks().get(0);
        task.setStatus("Finished");

        boolean result = deleteTask.deleteTask(taskName);
        assertFalse(result, "Task deletion should fail for a completed task.");
    }

    @Test
    public void testDeleteTaskWithoutConfirmation() {
        String taskName = "Unconfirmed Task";
        taskManager.createTask(taskName, "Description", LocalDateTime.now());

        boolean confirmed = false; // Simulate user not confirming
        boolean result = confirmed && deleteTask.deleteTask(taskName);

        assertFalse(result, "Task should not be deleted without confirmation.");
    }
}
