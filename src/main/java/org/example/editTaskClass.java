import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class editTaskClass {
    private List<Task> tasks;

    public editTaskClass(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Edits an existing task in the list based on index.
     *
     * @param index the index of the task to edit
     * @param newName the new name of the task
     * @param newDescription the new description of the task
     * @param newScheduledTime the new scheduled time of the task
     */
    public void editTask(int index, String newName, String newDescription, LocalDateTime newScheduledTime) {
        if (index < 0 || index >= tasks.size()) {
            System.out.println("Invalid task index.");
            return;
        }

        Task task = tasks.get(index);
        task.setName(newName);
        task.setDescription(newDescription);
        task.setScheduledTime(newScheduledTime);

        System.out.println("Task updated: " + task);
    }

    /**
     * Main method for testing task editing.
     */
    public static void main(String[] args) {
        createTaskClass taskCreator = new createTaskClass();
        Scanner scanner = new Scanner(System.in);

        // Create a task first for testing
        taskCreator.createTask("Sample Task", "Initial Description", LocalDateTime.now().plusDays(1));
        List<Task> tasks = taskCreator.getTasks();

        System.out.println("\nExisting Tasks:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + ": " + tasks.get(i));
        }

        editTaskClass taskEditor = new editTaskClass(tasks);

        System.out.print("\nEnter index of task to edit: ");
        int index = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter new task name: ");
        String newName = scanner.nextLine();

        System.out.print("Enter new task description: ");
        String newDescription = scanner.nextLine();

        System.out.print("Enter new scheduled time (format: yyyy-MM-ddTHH:mm): ");
        String newTimeStr = scanner.nextLine();

        try {
            LocalDateTime newScheduledTime = LocalDateTime.parse(newTimeStr);
            taskEditor.editTask(index, newName, newDescription, newScheduledTime);
        } catch (Exception e) {
            System.out.println("Invalid date/time format.");
        }

        System.out.println("\nUpdated Tasks:");
        for (Task task : tasks) {
            System.out.println(task);
        }

        scanner.close();
    }
}
