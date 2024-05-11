// Exception Handling Assignment - Exercise 2
// Matthew Vine
// CSIS 505-B01 (Liberty University)
// May 17, 2024

/**
 * Building class containing monthly billing information and implements the
 * CarbonFootprint interface.
 */
public class Building implements CarbonFootprint {
    private double monthlyElectricBill;
    private double monthlyWaterBill;

    /**
     * Constructs a Building object with the specified monthly electric and water
     * bills.
     * 
     * @param monthlyElectricBill The monthly electric bill.
     * @param monthlyWaterBill    The monthly water bill.
     */
    public Building(double monthlyElectricBill, double monthlyWaterBill) throws InvalidFootprintException {
        if (monthlyElectricBill < 0 || monthlyWaterBill < 0) {
            throw new InvalidFootprintException("Electric and water bills must be non-negative.");
        }

        this.monthlyElectricBill = monthlyElectricBill;
        this.monthlyWaterBill = monthlyWaterBill;
    }

    /**
     * Gets the monthly electric bill.
     * 
     * @return The monthly electric bill.
     */
    public double getMonthlyElectricBill() {
        return monthlyElectricBill;
    }

    /**
     * Sets the monthly electric bill.
     * 
     * @param monthlyElectricBill The monthly electric bill.
     */
    public void setMonthlyElectricBill(double monthlyElectricBill) {
        this.monthlyElectricBill = monthlyElectricBill;
    }

    /**
     * Gets the monthly water bill.
     * 
     * @return The monthly water bill.
     */
    public double getMonthlyWaterBill() {
        return monthlyWaterBill;
    }

    /**
     * Sets the monthly water bill.
     * 
     * @param monthlyWaterBill The monthly water bill.
     */
    public void setMonthlyWaterBill(double monthlyWaterBill) {
        this.monthlyWaterBill = monthlyWaterBill;
    }

    /**
     * Calculates the carbon footprint of the building.
     * 
     * @return The carbon footprint of the building.
     */
    @Override
    public double getCarbonFootprint() {
        // Equation obtained directly from the assignment instructions
        return ((monthlyElectricBill / 10.68) * 119.58 * 12) + ((monthlyWaterBill / 0.1188) * 1232 * 12);
    }
}
