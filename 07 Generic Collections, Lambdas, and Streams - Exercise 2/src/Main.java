// Generic Collections, Lambdas, and Streams - Exercise 2
// Matthew Vine
// CSIS 505-B01 (Liberty University)
// June 28, 2024

/**
 * Main class to demonstrate the indexed list class.
 */
public class Main {
    /**
     * Main method to demonstrate the indexed list class.
     * 
     * @param args Unused command-line arguments.
     */
    public static void main(String[] args) {
        // Create an indexed list of names.
        IndexedList indexedList = new IndexedList();
        indexedList.insert("Alice");
        indexedList.insert("Bob");
        indexedList.insert("Alex");
        indexedList.insert("Brenda");
        indexedList.insert("Chris");
        indexedList.insert("Carol");
        indexedList.insert("David");
        indexedList.insert("Emily");

        // Print the list.
        indexedList.print();
        System.out.println();

        // Create a list of search strings (test cases).
        String[] searches = { "Alex", "Carol", "Emily", "Charles", "charles", "CHARLES", "C", "", null };
        // Search for each string in the list and print the result.
        for (String search : searches) {
            String result = indexedList.searchVine(search);
            System.out.println("Search result for '" + search + "': " + result);
        }
    }
}
