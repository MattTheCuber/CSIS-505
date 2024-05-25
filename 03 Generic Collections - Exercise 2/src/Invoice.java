public class Invoice {
    int PartNumber;
    String PartDescription;
    int Quantity;
    double Price;

    public Invoice(int partNumber, String partDescription, int quantity, double price) {
        PartNumber = partNumber;
        PartDescription = partDescription;
        Quantity = quantity;
        Price = price;
    }

    public int getPartNumber() {
        return PartNumber;
    }

    public void setPartNumber(int partNumber) {
        PartNumber = partNumber;
    }

    public String getPartDescription() {
        return PartDescription;
    }

    public void setPartDescription(String partDescription) {
        PartDescription = partDescription;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public double getInvoiceValue() {
        return Quantity * Price;
    }

    @Override
    public String toString() {
        return String.format("Part Number: %-7d Quantity: %-6d Price: $%-10.2f Part Description: %s", PartNumber,
                Quantity, Price, PartDescription);
    }
}
