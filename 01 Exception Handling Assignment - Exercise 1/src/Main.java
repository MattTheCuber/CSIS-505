// Exception Handling Assignment - Exercise 1
// Matthew Vine
// CSIS 505-B01 (Liberty University)
// May 17, 2024

/**
 * Main class to demonstrate the CarbonFootprint interface and its
 * implementation.
 */
public class Main {
    /**
     * Main method to demonstrate the CarbonFootprint interface and its
     * implementation.
     * 
     * @param args Unused command-line arguments
     */
    public static void main(String[] args) {
        // Create objects of each class
        Building building = new Building(150, 25);
        Car car = new Car(5200, 20);
        Bicycle bicycle = new Bicycle(520);

        // Place references of these objects in an array of CarbonFootprint
        CarbonFootprint[] carbonFootprints = new CarbonFootprint[] { building, car, bicycle };

        // Iterate through the array
        for (CarbonFootprint obj : carbonFootprints) {
            // Print the class name
            System.out.println(obj.getClass().getSimpleName());
            // Depending on the object type, print additional information by downcasting
            if (obj instanceof Building) {
                Building b = (Building) obj;
                System.out.println("  Monthly electric bill: " + b.getMonthlyElectricBill());
                System.out.println("  Monthly water bill: " + b.getMonthlyWaterBill());
            } else if (obj instanceof Car) {
                Car c = (Car) obj;
                System.out.println("  Miles driven per year: " + c.getMilesDrivenPerYear());
                System.out.println("  Miles per gallon: " + c.getMilesPerGallon());
            } else if (obj instanceof Bicycle) {
                Bicycle b = (Bicycle) obj;
                System.out.println("  Miles traveled per month: " + b.getMilesTraveledPerMonth());
            }
            // Print the carbon footprint
            System.out.println("  Carbon footprint: " + obj.getCarbonFootprint());
        }
    }
}
