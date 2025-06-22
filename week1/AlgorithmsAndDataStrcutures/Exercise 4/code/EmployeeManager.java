// EmployeeManager.java
public class EmployeeManager {
    private Employee[] employees;
    private int size;

    public EmployeeManager(int capacity) {
        employees = new Employee[capacity];
        size = 0;
    }

    public void addEmployee(Employee emp) {
        if (size < employees.length) {
            employees[size++] = emp;
        } else {
            System.out.println("Array is full. Cannot add more employees.");
        }
    }

    public Employee searchEmployee(int empId) {
        for (int i = 0; i < size; i++) {
            if (employees[i].employeeId == empId) {
                return employees[i];
            }
        }
        return null;
    }

    public void deleteEmployee(int empId) {
        for (int i = 0; i < size; i++) {
            if (employees[i].employeeId == empId) {
                for (int j = i; j < size - 1; j++) {
                    employees[j] = employees[j + 1];
                }
                employees[--size] = null;
                System.out.println("Employee deleted.");
                return;
            }
        }
        System.out.println("Employee not found.");
    }

    public void traverseEmployees() {
        if (size == 0) {
            System.out.println("No employee records.");
        } else {
            for (int i = 0; i < size; i++) {
                System.out.println(employees[i]);
            }
        }
    }
}
