// Generic Methods and Classes - Exercise 1
// Matthew Vine
// CSIS 505-B01 (Liberty University)
// June 14, 2024

/**
 * Main class to demonstrate object comparisons using a generic method.
 */
public class Main {
    /**
     * Main method to demonstrate object comparisons using a generic method.
     * 
     * @param args Unused command-line arguments.
     */
    public static void main(String[] args) {
        // Create objects of various types to compare
        Boolean b1 = true;
        Boolean b2 = true;
        Boolean b3 = false;

        Integer i1 = 1;
        Integer i2 = 1;
        Integer i3 = 2;

        Integer three = 3;
        Integer four = 4;
        Integer five = 5;

        Double d1 = 1.0;
        Double d2 = 1.0;
        Double d3 = 2.0;

        Character c1 = 'a';
        Character c2 = 'a';
        Character c3 = 'b';

        String s1 = "Matthew";
        String s2 = "Matthew";
        String s3 = "Vine";
        String s4 = "a";

        Object o1 = new Object();
        Object o2 = new Object();

        // Compare the objects and print the results
        System.out.println("Comparing Boolean to itself: " + compareVine(b1, b1));
        System.out.println("Comparing equal Booleans: " + compareVine(b1, b2));
        System.out.println("Comparing different Booleans: " + compareVine(b1, b3));
        System.out.println();
        System.out.println("Comparing Integer to itself: " + compareVine(i1, i1));
        System.out.println("Comparing equal Integers: " + compareVine(i1, i2));
        System.out.println("Comparing different Integers: " + compareVine(i1, i3));
        System.out.println();
        System.out.println("Comparing 4 and 4 Integers: " + compareVine(four, four));
        System.out.println("Comparing 3 and 5 Integers: " + compareVine(three, five));
        System.out.println();
        System.out.println("Comparing Double to itself: " + compareVine(d1, d1));
        System.out.println("Comparing equal Doubles: " + compareVine(d1, d2));
        System.out.println("Comparing different Doubles: " + compareVine(d1, d3));
        System.out.println();
        System.out.println("Comparing equal Integer and Double: " + compareVine(i1, d1));
        System.out.println("Comparing different Integer and Double: " + compareVine(i1, d3));
        System.out.println();
        System.out.println("Comparing Character to itself: " + compareVine(c1, c1));
        System.out.println("Comparing equal Characters: " + compareVine(c1, c2));
        System.out.println("Comparing different Characters: " + compareVine(c1, c3));
        System.out.println();
        System.out.println("Comparing String to itself: " + compareVine(s1, s1));
        System.out.println("Comparing equal Strings: " + compareVine(s1, s2));
        System.out.println("Comparing different Strings: " + compareVine(s1, s3));
        System.out.println();
        System.out.println("Comparing equal Character and String: " + compareVine(c1, s4));
        System.out.println("Comparing different Character and String: " + compareVine(c1, s1));
        System.out.println();
        System.out.println("Comparing Object: " + compareVine(o1, o1));
        System.out.println("Comparing different Objects: " + compareVine(o1, o2));
        System.out.println();
        System.out.println("Comparing with Object: " + compareVine(null, o1));
        System.out.println("Comparing nulls: " + compareVine(null, null));
        System.out.println();
        System.out.println("Comparing Boolean with Integer: " + compareVine(b1, i1));
        System.out.println("Comparing Integer with String: " + compareVine(i1, s1));
        System.out.println("Comparing String with Object: " + compareVine(s1, o1));
    }

    /**
     * Compares two objects.
     * 
     * @param a   The first object to compare.
     * @param b   The second object to compare.
     * @param <T> The type of the objects to compare.
     * @return True if the objects are equal, false otherwise.
     */
    public static <T> boolean compareVine(T a, T b) {
        // This block catches null values so that the equals method does not throw a
        // runtime error.
        if (a == null && b == null) { // If the objects are both null, they are equal.
            return true;
        } else if (a == null || b == null) { // Else, if one object is null and the other is not, they are not equal.
            return false;
        }
        // Compare the objects using the equals method.
        return a.equals(b);
    }
}
