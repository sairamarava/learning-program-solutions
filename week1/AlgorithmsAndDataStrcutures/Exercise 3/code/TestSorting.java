// TestSorting.java
public class TestSorting {
    public static void main(String[] args) {
        Order[] orders = {
            new Order(201, "Alice", 2500.0),
            new Order(202, "Bob", 1200.0),
            new Order(203, "Charlie", 4800.0),
            new Order(204, "David", 3300.0)
        };

        // Bubble Sort
        System.out.println("Sorted using Bubble Sort:");
        Order[] bubbleSorted = orders.clone();
        OrderSorter.bubbleSort(bubbleSorted);
        OrderSorter.printOrders(bubbleSorted);

        // Quick Sort
        System.out.println("\nSorted using Quick Sort:");
        Order[] quickSorted = orders.clone();
        OrderSorter.quickSort(quickSorted, 0, quickSorted.length - 1);
        OrderSorter.printOrders(quickSorted);
    }
}
