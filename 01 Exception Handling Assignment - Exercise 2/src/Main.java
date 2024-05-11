// Exception Handling Assignment - Exercise 1
// Matthew Vine
// CSIS 505-B01 (Liberty University)
// May 17, 2024

/**
 * Main class to demonstrate exception handling using InvalidFootprintException.
 */
public class Main {
    /**
     * Main method to demonstrate exception handling using
     * InvalidFootprintException.
     * 
     * @param args Unused command-line arguments
     */
    public static void main(String[] args) {
        // Regular values
        System.out.println("Success tests:");
        try {
            new Building(150, 25);
            System.out.println("Created building successfully.");
        } catch (IllegalArgumentException e) {
            System.err.println(e.toString());
        }
        try {
            new Car(5200, 20);
            System.out.println("Created car successfully.");
        } catch (IllegalArgumentException e) {
            System.err.println(e.toString());
        }
        try {
            new Bicycle(520);
            System.out.println("Created bicycle successfully.");
        } catch (IllegalArgumentException e) {
            System.err.println(e.toString());
        }

        // Invalid values
        System.out.println("\nFail tests:");
        try {
            new Building(-150, 25);
            System.out.println("Created building successfully.");
        } catch (IllegalArgumentException e) {
            System.err.println(e.toString());
        }
        try {
            new Car(5200, -20);
            System.out.println("Created car successfully.");
        } catch (IllegalArgumentException e) {
            System.err.println(e.toString());
        }
        try {
            new Bicycle(-520);
            System.out.println("Created bicycle successfully.");
        } catch (IllegalArgumentException e) {
            System.err.println(e.toString());
        }

        System.out.println("\nProgram ending without crash...");
    }
}
