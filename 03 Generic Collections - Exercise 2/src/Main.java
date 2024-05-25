import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
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
        List<Invoice> invoicesList = Arrays.asList(invoices);

        System.out.println("Invoices sorted by description:");
        invoicesList.stream()
                .sorted(Comparator.comparing(Invoice::getPartDescription))
                .forEach(System.out::println);

        System.out.println("\nInvoices sorted by price:");
        invoicesList.stream()
                .sorted(Comparator.comparing(Invoice::getPrice))
                .forEach(System.out::println);

        System.out.println("\nInvoices mapped to description and quantity sorted by quantity:");
        invoicesList.stream()
                .map(invoice -> new Object() {
                    String description = invoice.getPartDescription();
                    int quantity = invoice.getQuantity();
                })
                .sorted(Comparator.comparing(obj -> obj.quantity))
                .forEach(obj -> System.out.printf("Quantity: %-6d Part Description: %s%n",
                        obj.quantity, obj.description));

        System.out.println("\nInvoices mapped to description and the invoice value sorted by the invoice value:");
        invoicesList.stream()
                .map(invoice -> new Object() {
                    String description = invoice.getPartDescription();
                    double value = invoice.getInvoiceValue();
                })
                .sorted(Comparator.comparing(obj -> obj.value))
                .forEach(obj -> System.out.printf("Invoice Value: $%-10.2f Part Description: %s%n", obj.value,
                        obj.description));

        System.out.println(
                "\nInvoices mapped to description and the invoice value, sorted by the invoice value, and between $200 and $500:");
        invoicesList.stream()
                .map(invoice -> new Object() {
                    String description = invoice.getPartDescription();
                    double value = invoice.getInvoiceValue();
                })
                .sorted(Comparator.comparing(obj -> obj.value))
                .filter(invoice -> invoice.value >= 200 && invoice.value <= 500)
                .forEach(obj -> System.out.printf("Invoice Value: $%-10.2f Description: %s%n", obj.value,
                        obj.description));
    }
}
