import java.util.Iterator;
import java.util.List;

public class deleteTaskClass {
    private List<Task> tasks;

    public deleteTaskClass(List<Task> tasks) {
        this.tasks = tasks;
    }

    public boolean deleteTask(String name) {
        Iterator<Task> iterator = tasks.iterator();
        while (iterator.hasNext()) {
            Task task = iterator.next();
            if (task.getName().equalsIgnoreCase(name)) {
                iterator.remove();
                System.out.println("Task deleted: " + task);
                return true;
            }
        }
        System.out.println("Task not found: " + name);
        return false;
    }
}
