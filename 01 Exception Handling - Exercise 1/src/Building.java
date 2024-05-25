// Exception Handling Assignment - Exercise 1
// Matthew Vine
// CSIS 505-B01 (Liberty University)
// May 17, 2024

/**
 * Building class containing monthly billing information and implements the
 * CarbonFootprint interface.
 */
public class Building implements CarbonFootprint {
    private double monthlyElectricBill;
    private double monthlyGasBill;

    /**
     * Constructs a Building object with the specified monthly electric and gas
     * bills.
     * 
     * @param monthlyElectricBill The monthly electric bill.
     * @param monthlyGasBill      The monthly gas bill.
     */
    public Building(double monthlyElectricBill, double monthlyGasBill) {
        this.monthlyElectricBill = monthlyElectricBill;
        this.monthlyGasBill = monthlyGasBill;
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
     * Gets the monthly gas bill.
     * 
     * @return The monthly gas bill.
     */
    public double getMonthlyGasBill() {
        return monthlyGasBill;
    }

    /**
     * Sets the monthly gas bill.
     * 
     * @param monthlyGasBill The monthly gas bill.
     */
    public void setMonthlyGasBill(double monthlyGasBill) {
        this.monthlyGasBill = monthlyGasBill;
    }

    /**
     * Calculates the carbon footprint of the building.
     * 
     * @return The carbon footprint of the building.
     */
    @Override
    public double getCarbonFootprint() {
        // Equation obtained directly from the assignment instructions
        return ((monthlyGasBill / 10.68) * 119.58 * 12) + ((monthlyElectricBill / 0.1188) * 1232 * 12);
    }
}
