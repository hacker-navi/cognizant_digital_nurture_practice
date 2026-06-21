public class ECommerceSearchTest {

    public static void main(String[] args) {
        Product[] products = {
            new Product(1, "Laptop", "Electronics"),
            new Product(2, "Shoes", "Footwear"),
            new Product(3, "Backpack", "Bags"),
            new Product(4, "Watch", "Accessories"),
            new Product(5, "Phone", "Electronics")
        };

        System.out.println("--- Linear Search ---");
        Product found = SearchAlgorithms.linearSearch(products, "Watch");
        System.out.println(found != null ? "Found: " + found : "Product not found");

        Product[] sortedProducts = {
            new Product(3, "Backpack", "Bags"),
            new Product(1, "Laptop", "Electronics"),
            new Product(5, "Phone", "Electronics"),
            new Product(2, "Shoes", "Footwear"),
            new Product(4, "Watch", "Accessories")
        };

        System.out.println("\n--- Binary Search ---");
        Product binaryResult = SearchAlgorithms.binarySearch(sortedProducts, "Phone");
        System.out.println(binaryResult != null ? "Found: " + binaryResult : "Product not found");

        System.out.println("\n--- Analysis ---");
        System.out.println("Linear Search: O(n) - checks each element one by one.");
        System.out.println("Binary Search: O(log n) - requires sorted array, much faster for large datasets.");
        System.out.println("For an e-commerce platform with many products, Binary Search is preferred after sorting once.");
    }
}
