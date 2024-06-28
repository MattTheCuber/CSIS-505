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
        // Reversed year-month order as permitted via email.
        indexedList.insert("April2024");
        indexedList.insert("May2024");
        indexedList.insert("June2024");
        indexedList.insert("July2024");
        indexedList.insert("August2024");
        indexedList.insert("September2024");
        indexedList.insert("October2024");
        indexedList.insert("November2024");

        // Print the list.
        indexedList.print();
        System.out.println();

        // Create a list of search strings (test cases).
        String[] searches = { "April2024", "May2024", "January2024", "April", "april2024", "APRIL2024", "2", "", null };
        // Search for each string in the list and print the result.
        for (String search : searches) {
            String result = indexedList.searchVine(search);
            System.out.println("Search result for '" + search + "': " + result);
        }
    }
}
