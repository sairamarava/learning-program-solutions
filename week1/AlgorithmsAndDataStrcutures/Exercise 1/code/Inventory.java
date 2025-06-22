// Inventory.java
import java.util.HashMap;

public class Inventory {
    private HashMap<Integer, Product1> productMap = new HashMap<>();

    // Add product
    public void addProduct(Product1 product) {
        productMap.put(product.productId, product);
    }

    // Update product
    public void updateProduct(int productId, int quantity, double price) {
        Product1 p = productMap.get(productId);
        if (p != null) {
            p.quantity = quantity;
            p.price = price;
        } else {
            System.out.println("Product not found.");
        }
    }

    // Delete product
    public void deleteProduct(int productId) {
        productMap.remove(productId);
    }

    // Display all products
    public void displayInventory() {
        if (productMap.isEmpty()) {
            System.out.println("Inventory is empty.");
        } else {
            for (Product1 p : productMap.values()) {
                System.out.println(p);
            }
        }
    }
}
