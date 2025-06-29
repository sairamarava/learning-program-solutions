// TestInventory.java
public class TestInventory {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();

        inventory.addProduct(new Product1(101, "Monitor", 15, 8999.99));
        inventory.addProduct(new Product1(102, "Keyboard", 40, 999.00));
        inventory.addProduct(new Product1(103, "Mouse", 30, 499.00));

        System.out.println("Initial Inventory:");
        inventory.displayInventory();

        inventory.updateProduct(102, 35, 950.00);
        inventory.deleteProduct(103);

        System.out.println("\nUpdated Inventory:");
        inventory.displayInventory();
    }
}
