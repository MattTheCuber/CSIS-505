// Generic Collections Assignment - Exercise 2
// Matthew Vine
// CSIS 505-B01 (Liberty University)
// May 31, 2024

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Main class to demonstrate the streams and lambdas using the Invoice class.
 */
public class Main {
    /**
     * Main method to demonstrate the streams and lambdas using the Invoice class.
     * 
     * @param args Unused command-line arguments
     */
    public static void main(String[] args) {
        // Create an array of Invoice objects.
        Invoice[] invoices = {
                new Invoice(1000, "Palm Sander", 5, 159.99),
                new Invoice(1001, "Circular Saw", 15, 219.99),
                new Invoice(1002, "Sledge Hammer", 10, 45.99),
                new Invoice(1003, "Hammer", 70, 24.99),
                new Invoice(1004, "Finn Tool", 5, 119.99),
                new Invoice(1005, "Screwdriver", 40, 9.99),
                new Invoice(1006, "Jig Saw", 20, 119.99),
                new Invoice(1007, "Hacksaw", 40, 119.99),
                new Invoice(1008, "Drill", 30, 179.99),
                new Invoice(1009, "Impact Drill", 2, 199.99)
        };
        // Convert the array to a list.
        List<Invoice> invoicesList = Arrays.asList(invoices);

        // Sort the invoices by part description and print them.
        System.out.println("Invoices sorted by description:");
        invoicesList.stream()
                .sorted(Comparator.comparing(Invoice::getPartDescription)) // Sort by part description.
                .forEach(System.out::println); // Print each invoice.

        // Sort the invoices by price and print them.
        System.out.println("\nInvoices sorted by price:");
        invoicesList.stream()
                .sorted(Comparator.comparing(Invoice::getPrice)) // Sort by price.
                .forEach(System.out::println); // Print each invoice.

        // Map the invoices to part description and quantity, sort by quantity, and
        // print them.
        System.out.println("\nInvoices mapped to description and quantity sorted by quantity:");
        invoicesList.stream()
                .map(invoice -> new Object() { // Map to a new objects with description and quantity.
                    String description = invoice.getPartDescription();
                    int quantity = invoice.getQuantity();
                })
                .sorted(Comparator.comparing(obj -> obj.quantity)) // Sort by quantity.
                .forEach(obj -> System.out.printf("Quantity: %-6d Part Description: %s%n", // Print the objects.
                        obj.quantity, obj.description));

        // Map the invoices to part description and invoice value, sort by invoice
        // value, and
        System.out.println("\nInvoices mapped to description and the invoice value sorted by the invoice value:");
        invoicesList.stream()
                .map(invoice -> new Object() { // Map to a new objects with description and invoice value.
                    String description = invoice.getPartDescription();
                    double value = invoice.getInvoiceValue();
                })
                .sorted(Comparator.comparing(obj -> obj.value)) // Sort by invoice value.
                .forEach(obj -> System.out.printf("Invoice Value: $%-10.2f Part Description: %s%n", // Print the objects
                        obj.value, obj.description));

        // Map the invoices to part description and invoice value, sort by invoice,
        // filter from $200 to $500, and print them.
        System.out.println(
                "\nInvoices mapped to description and the invoice value, sorted by the invoice value, and between $200 and $500:");
        invoicesList.stream()
                .map(invoice -> new Object() { // Map to a new objects with description and invoice value.
                    String description = invoice.getPartDescription();
                    double value = invoice.getInvoiceValue();
                })
                .sorted(Comparator.comparing(obj -> obj.value)) // Sort by invoice value.
                .filter(invoice -> invoice.value >= 200 && invoice.value <= 500) // Filter from $200 and $500.
                .forEach(obj -> System.out.printf("Invoice Value: $%-10.2f Part Description: %s%n", // Print the objects
                        obj.value, obj.description));
    }
}