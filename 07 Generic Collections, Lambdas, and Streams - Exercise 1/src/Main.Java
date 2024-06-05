// Generic Collections, Lambdas, and Streams - Exercise 1
// Matthew Vine
// CSIS 505-B01 (Liberty University)
// June 28, 2024

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
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

        Scanner scanner = new Scanner(System.in);
        String input;

        do {
            System.out.print("Enter a number to search in the list (or press enter to exit): ");
            input = scanner.nextLine();

            int number;
            try {
                number = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }
            if (list.searchVine(number) != null) {
                System.out.println(number + " is found in the list.");
            } else {
                System.out.println(number + " is not found in the list.");
            }
        } while (!input.isEmpty());

        scanner.close();
    }
}
