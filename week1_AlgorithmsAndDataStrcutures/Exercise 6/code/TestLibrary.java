// TestLibrary.java
public class TestLibrary {
    public static void main(String[] args) {
        Book[] books = {
            new Book(101, "Clean Code", "Robert C. Martin"),
            new Book(102, "The Pragmatic Programmer", "Andrew Hunt"),
            new Book(103, "Introduction to Algorithms", "Cormen"),
            new Book(104, "Design Patterns", "Erich Gamma")
        };

        String searchTitle = "Design Patterns";

        // Linear Search
        System.out.println("Linear Search Result:");
        Book result1 = BookSearch.linearSearch(books, searchTitle);
        System.out.println(result1 != null ? result1 : "Not found");

        // Binary Search
        System.out.println("\nBinary Search Result:");
        Book result2 = BookSearch.binarySearch(books, searchTitle);
        System.out.println(result2 != null ? result2 : "Not found");
    }
}
