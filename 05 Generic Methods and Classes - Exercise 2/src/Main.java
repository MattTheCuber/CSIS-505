// Generic Methods and Classes - Exercise 2
// Matthew Vine
// CSIS 505-B01 (Liberty University)
// June 14, 2024

import java.util.ArrayList;

/**
 * Main class to demonstrate the generic `Pair` class.
 */
public class Main {
    /**
     * Main method to demonstrate the generic `Pair` class.
     * 
     * @param args Unused command-line arguments.
     */
    public static void main(String[] args) {
        // Create a list of pairs from 1 to 10 by instantiating the generic `Pair` class
        // with `Integer` and `String` types.
        ArrayList<Pair<Integer, String>> list = new ArrayList<>();
        list.add(new Pair<>(1, "One"));
        list.add(new Pair<>(2, "Two"));
        list.add(new Pair<>(3, "Three"));
        list.add(new Pair<>(4, "Four"));
        list.add(new Pair<>(5, "Five"));
        list.add(new Pair<>(6, "Six"));
        list.add(new Pair<>(7, "Seven"));
        list.add(new Pair<>(8, "Eight"));
        list.add(new Pair<>(9, "Nine"));
        list.add(new Pair<>(10, "Ten"));

        // Print the list of pairs.
        for (Pair<Integer, String> pair : list) {
            System.out.println(pair.toString());
        }
    }
}
