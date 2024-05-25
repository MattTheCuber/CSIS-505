// Generic Collections Assignment - Exercise 2
// Matthew Vine
// CSIS 505-B01 (Liberty University)
// May 31, 2024

/**
 * Invoice class to store information about an invoice.
 */
public class Invoice {
    // Invoice attributes
    int PartNumber;
    String PartDescription;
    int Quantity;
    double Price;

    /**
     * Constructor for the Invoice class.
     * 
     * @param partNumber      The part number of the invoice.
     * @param partDescription The description of the part.
     * @param quantity        The quantity of the part.
     * @param price           The price of the part.
     */
    public Invoice(int partNumber, String partDescription, int quantity, double price) {
        PartNumber = partNumber;
        PartDescription = partDescription;
        Quantity = quantity;
        Price = price;
    }

    /**
     * Get the part number of the invoice.
     * 
     * @return The part number of the invoice.
     */
    public int getPartNumber() {
        return PartNumber;
    }

    /**
     * Set the part number of the invoice.
     * 
     * @param partNumber The part number of the invoice.
     */
    public void setPartNumber(int partNumber) {
        PartNumber = partNumber;
    }

    /**
     * Get the part description of the invoice.
     * 
     * @return The part description of the invoice.
     */
    public String getPartDescription() {
        return PartDescription;
    }

    /**
     * Set the part description of the invoice.
     * 
     * @param partDescription The part description of the invoice.
     */
    public void setPartDescription(String partDescription) {
        PartDescription = partDescription;
    }

    /**
     * Get the quantity of the invoice.
     * 
     * @return The quantity of the invoice.
     */
    public int getQuantity() {
        return Quantity;
    }

    /**
     * Set the quantity of the invoice.
     * 
     * @param quantity The quantity of the invoice.
     */
    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    /**
     * Get the price of the invoice.
     * 
     * @return The price of the invoice.
     */
    public double getPrice() {
        return Price;
    }

    /**
     * Set the price of the invoice.
     * 
     * @param price The price of the invoice.
     */
    public void setPrice(double price) {
        Price = price;
    }

    /**
     * Get the invoice value of the invoice.
     * 
     * @return The invoice value of the invoice.
     */
    public double getInvoiceValue() {
        return Quantity * Price;
    }

    /**
     * Get the string representation of the invoice.
     */
    @Override
    public String toString() {
        return String.format("Part Number: %-7d Quantity: %-6d Price: $%-10.2f Part Description: %s", PartNumber,
                Quantity, Price, PartDescription);
    }
}
