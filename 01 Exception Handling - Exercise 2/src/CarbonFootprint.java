// Exception Handling Assignment - Exercise 2
// Matthew Vine
// CSIS 505-B01 (Liberty University)
// May 17, 2024

/**
 * Interface for carbon footprint calculation. Classes implementing this
 * interface must provide a method to calculate the carbon footprint of the
 * object.
 */
public interface CarbonFootprint {
    /**
     * Calculates the carbon footprint of the object.
     * 
     * @return The carbon footprint of the object.
     */
    double getCarbonFootprint() throws InvalidFootprintException;
}
