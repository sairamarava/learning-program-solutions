// TestEmployeeSystem.java
public class TestEmployee {
    public static void main(String[] args) {
        EmployeeManager manager = new EmployeeManager(5);

        manager.addEmployee(new Employee(1, "Alice", "HR", 50000));
        manager.addEmployee(new Employee(2, "Bob", "Engineer", 75000));
        manager.addEmployee(new Employee(3, "Charlie", "Manager", 90000));

        System.out.println("All Employees:");
        manager.traverseEmployees();

        System.out.println("\nSearching Employee with ID 2:");
        System.out.println(manager.searchEmployee(2));

        System.out.println("\nDeleting Employee with ID 1:");
        manager.deleteEmployee(1);

        System.out.println("\nEmployees After Deletion:");
        manager.traverseEmployees();
    }
}
