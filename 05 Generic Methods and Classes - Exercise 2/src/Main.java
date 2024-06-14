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
        // Create a list of pairs of adjacent months by instantiating the generic `Pair`
        // class with `String` and `String` types.
        ArrayList<Pair<String, String>> adjacentMonths = new ArrayList<>();
        adjacentMonths.add(new Pair<>("January", "February"));
        adjacentMonths.add(new Pair<>("February", "March"));
        adjacentMonths.add(new Pair<>("March", "April"));
        adjacentMonths.add(new Pair<>("April", "May"));
        adjacentMonths.add(new Pair<>("May", "June"));
        adjacentMonths.add(new Pair<>("June", "July"));
        adjacentMonths.add(new Pair<>("July", "August"));
        adjacentMonths.add(new Pair<>("August", "September"));
        adjacentMonths.add(new Pair<>("September", "October"));
        adjacentMonths.add(new Pair<>("October", "November"));
        // Removed to keep within the 10 list count requirement.
        // adjacentMonths.add(new Pair<>("November", "December"));

        // Print the list of pairs.
        for (Pair<String, String> pair : adjacentMonths) {
            System.out.println(pair.toString());
        }
    }
}
