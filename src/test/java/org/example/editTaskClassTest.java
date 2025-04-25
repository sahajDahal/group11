import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class editTaskClassTest {

    private List<Task> tasks;
    private editTaskClass taskEditor;

    @BeforeEach
    public void setUp() {
        tasks = new ArrayList<>();
        Task initialTask = new Task("Original Task", "Original Description", LocalDateTime.of(2025, 4, 20, 12, 0));
        tasks.add(initialTask);
        taskEditor = new editTaskClass(tasks);
    }

    @Test
    public void testEditTaskValidIndex() {
        LocalDateTime newTime = LocalDateTime.of(2025, 4, 25, 15, 30);

        taskEditor.editTask(0, "Updated Task", "Updated Description", newTime);

        Task updatedTask = tasks.get(0);
        assertEquals("Updated Task", updatedTask.getName());
        assertEquals("Updated Description", updatedTask.getDescription());
        assertEquals(newTime, updatedTask.getScheduledTime());
    }

    @Test
    public void testEditTaskInvalidIndex() {
        LocalDateTime newTime = LocalDateTime.now().plusDays(3);

        // Try to edit at invalid index (e.g., index 5 when there's only 1 task)
        taskEditor.editTask(5, "Should Fail", "No Change", newTime);

        // Make sure original task remains unchanged
        Task task = tasks.get(0);
        assertEquals("Original Task", task.getName());
        assertEquals("Original Description", task.getDescription());
        assertEquals(LocalDateTime.of(2025, 4, 20, 12, 0), task.getScheduledTime());
    }
}
