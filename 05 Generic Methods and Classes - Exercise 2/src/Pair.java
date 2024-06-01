// Generic Methods and Classes - Exercise 2
// Matthew Vine
// CSIS 505-B01 (Liberty University)
// June 14, 2024

/**
 * Pair class that holds a first and second value.
 * 
 * @param <F> The type of the first element in the pair.
 * @param <S> The type of the second element in the pair.
 */
public class Pair<F, S> {
    // The pair elements
    private F first;
    private S second;

    /**
     * Constructor to create a pair with a first and second element.
     * 
     * @param first  The first element in the pair.
     * @param second The second element in the pair.
     */
    public Pair(F first, S second) {
        this.first = first;
        this.second = second;
    }

    /**
     * Get the first element in the pair.
     * 
     * @return The first element in the pair.
     */
    public F getFirst() {
        return first;
    }

    /**
     * Set the first element in the pair.
     * 
     * @param first The first element in the pair.
     */
    public void setFirst(F first) {
        this.first = first;
    }

    /**
     * Get the second element in the pair.
     * 
     * @return The second element in the pair.
     */
    public S getSecond() {
        return second;
    }

    /**
     * Set the second element in the pair.
     * 
     * @param second The second element in the pair.
     */
    public void setSecond(S second) {
        this.second = second;
    }

    /**
     * Returns a string representation of the pair.
     * 
     * @return A string representation of the pair in the format `(first, second)`.
     */
    @Override
    public String toString() {
        return String.format("(%s, %s)", first, second);
    }
}