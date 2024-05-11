// Exception Handling Assignment - Exercise 1
// Matthew Vine
// CSIS 505-B01 (Liberty University)
// May 17, 2024

/**
 * Car class containing mileage information and implements the CarbonFootprint
 * interface.
 */
public class Car implements CarbonFootprint {
    private double milesDrivenPerYear;
    private double milesPerGallon;

    /**
     * Constructs a Car object with the specified miles driven per year and miles
     * per gallon.
     * 
     * @param milesDrivenPerYear The miles driven per year.
     * @param milesPerGallon     The miles per gallon.
     */
    public Car(double milesDrivenPerYear, double milesPerGallon) {
        this.milesDrivenPerYear = milesDrivenPerYear;
        this.milesPerGallon = milesPerGallon;
    }

    /**
     * Gets the miles driven per year.
     * 
     * @return The miles driven per year.
     */
    public double getMilesDrivenPerYear() {
        return milesDrivenPerYear;
    }

    /**
     * Sets the miles driven per year.
     * 
     * @param milesDrivenPerYear The miles driven per year.
     */
    public void setMilesDrivenPerYear(double milesDrivenPerYear) {
        this.milesDrivenPerYear = milesDrivenPerYear;
    }

    /**
     * Gets the miles per gallon.
     * 
     * @return The miles per gallon.
     */
    public double getMilesPerGallon() {
        return milesPerGallon;
    }

    /**
     * Sets the miles per gallon.
     * 
     * @param milesPerGallon The miles per gallon.
     */
    public void setMilesPerGallon(double milesPerGallon) {
        this.milesPerGallon = milesPerGallon;
    }

    /**
     * Calculates the carbon footprint of the car.
     * 
     * @return The carbon footprint of the car.
     */
    @Override
    public double getCarbonFootprint() {
        // Equation obtained directly from the assignment instructions
        return milesDrivenPerYear / milesPerGallon * 19.82;
    }
}
