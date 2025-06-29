public class TestSearch {
    public static void main(String[] args) {
        Product[] products = {
            new Product(101, "iPhone", "Electronics"),
            new Product(102, "Shoes", "Fashion"),
            new Product(103, "Laptop", "Electronics"),
            new Product(104, "Watch", "Accessories"),
            new Product(105, "Tablet", "Electronics")
        };

        String target = "Laptop";

        // Linear Search Test
        Product result1 = ProductSearch.linearSearch(products, target);
        System.out.println("Linear Search Result: " + result1);

        // Binary Search Test
        Product result2 = ProductSearch.binarySearch(products, target);
        System.out.println("Binary Search Result: " + result2);
    }
}
