// TaskList.java
public class TaskList {
    private TaskNode head;

    // Add task to end
    public void addTask(Task task) {
        TaskNode newNode = new TaskNode(task);
        if (head == null) {
            head = newNode;
            return;
        }
        TaskNode current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
    }

    // Search by taskId
    public Task searchTask(int taskId) {
        TaskNode current = head;
        while (current != null) {
            if (current.task.taskId == taskId) {
                return current.task;
            }
            current = current.next;
        }
        return null;
    }

    // Delete by taskId
    public void deleteTask(int taskId) {
        if (head == null) return;

        if (head.task.taskId == taskId) {
            head = head.next;
            return;
        }

        TaskNode current = head;
        while (current.next != null) {
            if (current.next.task.taskId == taskId) {
                current.next = current.next.next;
                return;
            }
            current = current.next;
        }
    }

    // Traverse all tasks
    public void traverseTasks() {
        TaskNode current = head;
        if (current == null) {
            System.out.println("No tasks available.");
            return;
        }
        while (current != null) {
            System.out.println(current.task);
            current = current.next;
        }
    }
}
