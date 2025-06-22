// TestTaskSystem.java
public class TestTaskSystem {
    public static void main(String[] args) {
        TaskList taskList = new TaskList();

        taskList.addTask(new Task(1, "Design Database", "Pending"));
        taskList.addTask(new Task(2, "Develop API", "In Progress"));
        taskList.addTask(new Task(3, "Write Tests", "Pending"));

        System.out.println("All Tasks:");
        taskList.traverseTasks();

        System.out.println("\nSearching Task with ID 2:");
        Task result = taskList.searchTask(2);
        System.out.println(result != null ? result : "Task not found.");

        System.out.println("\nDeleting Task with ID 1:");
        taskList.deleteTask(1);

        System.out.println("\nUpdated Task List:");
        taskList.traverseTasks();
    }
}
