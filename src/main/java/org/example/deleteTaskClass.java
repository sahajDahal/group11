package org.example;
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
                // Do not delete if the task is in progress or finished.
                if ("In Progress".equals(task.getStatus()) || "Finished".equals(task.getStatus())) {
                    System.out.println("Task cannot be deleted due to its status: " + task.getStatus());
                    return false;
                }
                iterator.remove();
                System.out.println("Task deleted: " + task);
                return true;
            }
        }
        System.out.println("Task not found: " + name);
        return false;
    }
}
