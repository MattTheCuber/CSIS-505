// Generic Collections, Lambdas, and Streams - Exercise 2
// Matthew Vine
// CSIS 505-B01 (Liberty University)
// June 28, 2024

import java.util.ArrayList;

/**
 * IndexedList class to store a list of names indexed by the first letter of the
 * name for quick searching.
 */
public class IndexedList {
    // Initialize an array list of lists to store names indexed by the first letter.
    private ArrayList<List<String>> indexedLists = new ArrayList<>(26);

    /**
     * Constructor to initialize the indexed list.
     */
    public IndexedList() {
        // Initialize a list for each letter of the alphabet.
        for (int i = 0; i < 26; i++) {
            // Create a string with the letter of the alphabet using ASCII values.
            String letter = "list " + (char) ('A' + i);
            List<String> newList = new List<>(letter);
            indexedLists.add(newList);
        }
    }

    /**
     * Get the list of names indexed by character.
     * 
     * @param c The character for the list.
     * @return The list of names indexed by character.
     */
    private List<String> getListByCharacter(char c) {
        // Later letters are have higher ASCII values.
        // For example, 'D' is 68 and 'A' is 65. So, 'D' should be at index 68 - 65 = 3.
        int characterIndex = Character.toUpperCase(c) - 'A';
        // If the character is not a letter, return null.
        if (characterIndex < 0 || characterIndex >= 26) {
            return null;
        }
        return indexedLists.get(characterIndex);
    }

    /**
     * Insert a name into the indexed list. The name is inserted at the back of the
     * list.
     * 
     * @param name The name to insert.
     */
    public void insert(String name) {
        // We can't add null or empty strings to this indexed list.
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
        // Insert the name into the list indexed by the first letter of the name.
        getListByCharacter(name.charAt(0)).insertAtBack(name);
    }

    /**
     * Print the indexed list of names.
     */
    public void print() {
        // Print each list in the indexed list if it is not empty.
        for (List<String> list : indexedLists) {
            if (!list.isEmpty()) {
                list.print();
            }
        }
    }

    /**
     * Search for a name in the indexed list.
     * 
     * @param name The name to search for.
     * @return The name if found, or null if not found.
     */
    public String searchVine(String name) {
        // If the name is null or empty, return null.
        if (name == null || name.isEmpty()) {
            return null;
        }
        // Search for the name in the list indexed by the first letter of the name.
        List<String> list = getListByCharacter(name.charAt(0));
        if (list == null) {
            return null;
        }
        return list.searchVine(name);
    }
}
