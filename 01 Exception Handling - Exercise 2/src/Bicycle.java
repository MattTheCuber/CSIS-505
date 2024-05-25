// Exception Handling Assignment - Exercise 2
// Matthew Vine
// CSIS 505-B01 (Liberty University)
// May 17, 2024

/**
 * Bicycle class containing monthly mileage information and implements the
 * CarbonFootprintinterface.
 */
public class Bicycle implements CarbonFootprint {
    private double milesTraveledPerMonth;

    /**
     * Constructs a Bicycle object with the specified miles traveled per month.
     * 
     * @param milesTraveledPerMonth The miles traveled per month.
     */
    public Bicycle(double milesTraveledPerMonth) throws InvalidFootprintException {
        if (milesTraveledPerMonth < 0) {
            throw new InvalidFootprintException("Miles traveled per month must be non-negative.");
        }

        this.milesTraveledPerMonth = milesTraveledPerMonth;
    }

    /**
     * Gets the miles traveled per month.
     * 
     * @return The miles traveled per month.
     */
    public double getMilesTraveledPerMonth() {
        return milesTraveledPerMonth;
    }

    /**
     * Sets the miles traveled per month.
     * 
     * @param milesTraveledPerMonth The miles traveled per month.
     */
    public void setMilesTraveledPerMonth(double milesTraveledPerMonth) {
        this.milesTraveledPerMonth = milesTraveledPerMonth;
    }

    /**
     * Calculates the carbon footprint of the bicycle.
     * 
     * @return The carbon footprint of the bicycle.
     */
    @Override
    public double getCarbonFootprint() {
        // Equation obtained directly from the assignment instructions
        return milesTraveledPerMonth * 0.9;
    }
}
