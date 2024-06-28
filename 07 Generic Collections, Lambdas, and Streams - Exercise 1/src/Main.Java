// Generic Collections, Lambdas, and Streams - Exercise 1
// Matthew Vine
// CSIS 505-B01 (Liberty University)
// June 28, 2024

import java.util.Scanner;

/**
 * Main class to demonstrate searching a linked list using generics.
 */
public class Main {
    /**
     * Main method to demonstrate searching a linked list using generics.
     * 
     * @param args Unused command-line arguments.
     */
    public static void main(String[] args) {
        // Create a linked list of integers.
        List<Integer> list = new List<>();
        list.insertAtFront(2403);
        list.insertAtFront(2404);
        list.insertAtFront(2405);
        list.insertAtFront(2406);
        list.insertAtFront(2407);
        list.insertAtFront(2408);
        list.insertAtFront(2409);
        list.insertAtFront(2410);
        list.insertAtFront(2411);
        list.insertAtFront(2412);
        list.insertAtFront(2413);
        list.insertAtFront(2414);
        list.insertAtFront(2415);
        list.insertAtFront(2416);
        list.insertAtFront(2417);
        list.insertAtFront(2418);
        list.insertAtFront(2419);
        list.insertAtFront(2420);
        list.insertAtFront(2421);
        list.insertAtFront(2422);
        list.insertAtFront(2423);
        list.insertAtFront(2424);
        list.insertAtFront(2425);
        list.insertAtFront(2426);
        list.insertAtFront(2427);

        // Initialize a scanner to read user input.
        Scanner scanner = new Scanner(System.in);
        
        // Prompt the user to enter a number to search in the list.
        System.out.print("Enter a number to search in the list (or press enter to exit): ");
        String input = scanner.nextLine();

        while (!input.isEmpty()) { // While there is input.
            // Parse the input as an integer or print an error message and prompt for new input.
            int number;
            try {
                number = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
        
                // Prompt the user to enter a number to search in the list.
                System.out.print("Enter a number to search in the list (or press enter to exit): ");
                input = scanner.nextLine();
                continue;
            }

            // Search the list for the number and print whether it was found.
            if (list.searchVine(number) != null) {
                System.out.println(number + " is found in the list.");
            } else {
                System.out.println(number + " is not found in the list.");
            }
        
            // Prompt the user to enter a number to search in the list.
            System.out.print("Enter a number to search in the list (or press enter to exit): ");
            input = scanner.nextLine();
        }

        // Close the scanner.
        scanner.close();
    }
}
