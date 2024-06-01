// Searching and Sorting - Exercise 2
// Matthew Vine
// CSIS 505-B01 (Liberty University)
// June 7, 2024

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.function.Consumer;

/**
 * Evaluator class to evaluate the performance of selection sort, insertion
 * sort, and merge sort.
 */
public class Evaluator {
    // Size of the arrays for sorting
    private static int arraySize = 100000;

    /**
     * Main method to evaluate the performance of selection sort, insertion sort,
     * and merge sort.
     * 
     * @param args Unused command-line arguments.
     */
    public static void main(String[] args) {
        System.out.printf("Results for sorting %d elements in an array.%n%n", arraySize);

        // Print the header for the table
        System.out.printf("| %-14s | %-15s | %-12s | %-15s |%n",
                "Sort Method", "Sequential (ms)", "Random (ms)", "Descending (ms)");
        System.out.printf("|----------------|-----------------|--------------|-----------------|%n");
        // Run the selection sort and print the results
        double[] selectionSortResults = timeSortMethod(Evaluator::selectionSort); // O(n^2)
        System.out.printf("| %-14s | %-15.4f | %-12.4f | %-15.4f |%n",
                "Selection Sort", selectionSortResults[0], selectionSortResults[1], selectionSortResults[2]);
        // Run the insertion sort and print the results
        double[] insertionSortResults = timeSortMethod(Evaluator::insertionSort); // O(n^2)
        System.out.printf("| %-14s | %-15.4f | %-12.4f | %-15.4f |%n",
                "Insertion Sort", insertionSortResults[0], insertionSortResults[1], insertionSortResults[2]);
        // Run the merge sort and print the results
        double[] mergeSortResults = timeSortMethod(Evaluator::mergeSort); // O(n log n)
        System.out.printf("| %-14s | %-15.4f | %-12.4f | %-15.4f |%n",
                "Merge Sort", mergeSortResults[0], mergeSortResults[1], mergeSortResults[2]);

        // Print the header for the statistics table
        System.out.printf("\n| %-14s | %-15s | %-12s | %-15s |%n",
                "Sort Method", "Best (ms)", "Average (ms)", "Worst (ms)");
        System.out.printf("|----------------|-----------------|--------------|-----------------|%n");
        // Print the statistics for the sort methods
        printStatistics("Selection Sort", selectionSortResults);
        printStatistics("Insertion Sort", insertionSortResults);
        printStatistics("Merge Sort", mergeSortResults);
    }

    /**
     * Print the statistics for a sort method.
     * 
     * @param sortMethod The name of the sort method.
     * @param results    The results of the sort method as an array of three
     *                   doubles.
     */
    private static void printStatistics(String sortMethod, double[] results) {
        // Calculate the best, worst, and average times for the sort method.
        double bestTime = Arrays.stream(results).min().getAsDouble();
        double worstTime = Arrays.stream(results).max().getAsDouble();
        double averageTime = Arrays.stream(results).average().getAsDouble();
        // Print the statistics for the sort method.
        System.out.printf("| %-14s | %-15.4f | %-12.4f | %-15.4f |%n", sortMethod, bestTime, averageTime, worstTime);
    }

    /**
     * Time the sort method for three different types of arrays: sequential, random,
     * and descending sequential.
     * 
     * @param sortMethod The sort method reference to time.
     * @return An array of three doubles representing the time in milliseconds for a
     *         sequential, random, and descending sequential array.
     */
    private static double[] timeSortMethod(Consumer<int[]> sortMethod) {
        // Time the sort method for three different types of arrays.
        double mergeSequentialTime = timeSingleSort(sortMethod, createSequential());
        double mergeRandomTime = timeSingleSort(sortMethod, createRandom());
        double mergeSequentialDescendingTime = timeSingleSort(sortMethod, createDescending());
        // Return an array of double for the times in milliseconds.
        return new double[] { mergeSequentialTime, mergeRandomTime, mergeSequentialDescendingTime };
    }

    /**
     * Time a single sort method for a single type of array.
     * 
     * @param sortMethod The sort method reference to time.
     * @param data       The array to sort.
     * @return The time in milliseconds to sort the array.
     */
    private static double timeSingleSort(Consumer<int[]> sortMethod, int[] data) {
        // Start the timer.
        long startTime = System.nanoTime();
        // Run the sort method.
        sortMethod.accept(data);
        // End the timer.
        long endTime = System.nanoTime();
        // Return the time in milliseconds.
        return (endTime - startTime) / 1000000.0;
    }

    /**
     * Create an array of integers in sequential order from 1 to the arraySize.
     * 
     * @return An array of integers in sequential order.
     */
    private static int[] createSequential() {
        // Initialize the array with the size of the array.
        int[] data = new int[arraySize];
        // Fill the array with integers from 1 to the arraySize.
        for (int i = 0; i < arraySize; i++) {
            data[i] = i + 1;
        }
        return data;
    }

    /**
     * Create an array of random integers from 1 to the arraySize.
     * 
     * @return An array of random integers.
     */
    private static int[] createRandom() {
        // Create a SecureRandom object.
        SecureRandom generator = new SecureRandom();
        // Generate an array of random integers from 1 to the arraySize.
        return generator.ints(arraySize, 1, arraySize + 1).toArray();
    }

    /**
     * Create an array of integers in sequential descending order from the arraySize
     * to 1.
     * 
     * @return An array of integers in sequential descending order.
     */
    private static int[] createDescending() {
        // Initialize the array with the size of the array.
        int[] data = new int[arraySize];
        // Fill the array with integers from the arraySize to 1.
        for (int i = 0; i < arraySize; i++) {
            data[i] = arraySize - i;
        }
        return data;
    }

    ////////////////////////////////////////////////////////
    // Fig. 19.4: SelectionSortTest.java
    // Sorting an array with selection sort.
    ////////////////////////////////////////////////////////

    public static void selectionSort(int[] data) {
        // loop over data.length - 1 elements
        for (int i = 0; i < data.length - 1; i++) {
            int smallest = i; // first index of remaining array

            // loop to find index of smallest element
            for (int index = i + 1; index < data.length; index++) {
                if (data[index] < data[smallest]) {
                    smallest = index;
                }
            }

            swap(data, i, smallest); // swap smallest element into position
        }
    }

    // helper method to swap values in two elements
    private static void swap(int[] data, int first, int second) {
        int temporary = data[first]; // store first in temporary
        data[first] = data[second]; // replace first with second
        data[second] = temporary; // put temporary in second
    }

    ////////////////////////////////////////////////////////
    // Fig. 19.5: InsertionSortTest.java
    // Sorting an array with insertion sort.
    ////////////////////////////////////////////////////////

    // sort array using insertion sort
    public static void insertionSort(int[] data) {
        // loop over data.length - 1 elements
        for (int next = 1; next < data.length; next++) {

            int insert = data[next]; // value to insert
            int moveItem = next; // location to place element

            // search for place to put current element
            while (moveItem > 0 && data[moveItem - 1] > insert) {
                // shift element right one slot
                data[moveItem] = data[moveItem - 1];
                moveItem--;
            }

            data[moveItem] = insert; // place inserted element
        }
    }

    ////////////////////////////////////////////////////////
    // Fig. 19.6: MergeSortTest.java
    // Sorting an array with merge sort.
    ////////////////////////////////////////////////////////

    // calls recursive sortArray method to begin merge sorting
    public static void mergeSort(int[] data) {
        sortArray(data, 0, data.length - 1); // sort entire array
    }

    // splits array, sorts subarrays and merges subarrays into sorted array
    private static void sortArray(int[] data, int low, int high) {
        // test base case; size of array equals 1
        if ((high - low) >= 1) { // if not base case
            int middle1 = (low + high) / 2; // calculate middle of array
            int middle2 = middle1 + 1; // calculate next element over

            // split array in half; sort each half (recursive calls)
            sortArray(data, low, middle1); // first half of array
            sortArray(data, middle2, high); // second half of array

            // merge two sorted arrays after split calls return
            merge(data, low, middle1, middle2, high);
        }
    }

    // merge two sorted subarrays into one sorted subarray
    private static void merge(int[] data, int left, int middle1,
            int middle2, int right) {

        int leftIndex = left; // index into left subarray
        int rightIndex = middle2; // index into right subarray
        int combinedIndex = 0; // index into temporary working array
        int[] combined = new int[right - left + 1]; // working array sized to the number of elements being merged

        // merge arrays until reaching end of either
        while (leftIndex <= middle1 && rightIndex <= right) {
            // place smaller of two current elements into result
            // and move to next space in arrays
            if (data[leftIndex] <= data[rightIndex]) {
                combined[combinedIndex++] = data[leftIndex++];
            } else {
                combined[combinedIndex++] = data[rightIndex++];
            }
        }

        // if left array is empty
        if (leftIndex == middle2) {
            // copy in rest of right array
            while (rightIndex <= right) {
                combined[combinedIndex++] = data[rightIndex++];
            }
        } else { // right array is empty
            // copy in rest of left array
            while (leftIndex <= middle1) {
                combined[combinedIndex++] = data[leftIndex++];
            }
        }

        // copy values back into original array
        for (int i = 0; i < combined.length; i++) {
            data[left + i] = combined[i];
        }
    }
}
