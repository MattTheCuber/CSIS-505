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
        list.insertAtFront(1);
        list.insertAtFront(2);
        list.insertAtFront(3);
        list.insertAtFront(4);
        list.insertAtFront(5);
        list.insertAtFront(6);
        list.insertAtFront(7);
        list.insertAtFront(8);
        list.insertAtFront(9);
        list.insertAtFront(10);
        list.insertAtFront(11);
        list.insertAtFront(12);
        list.insertAtFront(13);
        list.insertAtFront(14);
        list.insertAtFront(15);
        list.insertAtFront(16);
        list.insertAtFront(17);
        list.insertAtFront(18);
        list.insertAtFront(19);
        list.insertAtFront(20);
        list.insertAtFront(21);
        list.insertAtFront(22);
        list.insertAtFront(23);
        list.insertAtFront(24);
        list.insertAtFront(25);

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
