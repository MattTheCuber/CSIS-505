import java.security.SecureRandom;
import java.util.Arrays;
import java.util.function.Consumer;

public class Evaluator {
    private static int arraySize = 100000;

    public static void main(String[] args) {
        System.out.printf("Results for sorting %d elements in an array.%n%n", arraySize);

        System.out.printf("| %-14s | %-15s | %-12s | %-15s |%n",
                "Sort Method", "Sequential (ms)", "Random (ms)", "Descending (ms)");
        System.out.printf("|----------------|-----------------|--------------|-----------------|%n");
        double[] selectionSortResults = timeSortMethod(Evaluator::selectionSort); // O(n^2)
        System.out.printf("| %-14s | %-15.4f | %-12.4f | %-15.4f |%n",
                "Selection Sort", selectionSortResults[0], selectionSortResults[1], selectionSortResults[2]);
        double[] insertionSortResults = timeSortMethod(Evaluator::insertionSort); // O(n^2)
        System.out.printf("| %-14s | %-15.4f | %-12.4f | %-15.4f |%n",
                "Insertion Sort", insertionSortResults[0], insertionSortResults[1], insertionSortResults[2]);
        double[] mergeSortResults = timeSortMethod(Evaluator::mergeSort); // O(n log n)
        System.out.printf("| %-14s | %-15.4f | %-12.4f | %-15.4f |%n",
                "Merge Sort", mergeSortResults[0], mergeSortResults[1], mergeSortResults[2]);

        System.out.printf("\n| %-14s | %-15s | %-12s | %-15s |%n",
                "Sort Method", "Best (ms)", "Average (ms)", "Worst (ms)");
        System.out.printf("|----------------|-----------------|--------------|-----------------|%n");
        printStatistics("Selection Sort", selectionSortResults);
        printStatistics("Insertion Sort", insertionSortResults);
        printStatistics("Merge Sort", mergeSortResults);
    }

    private static void printStatistics(String sortMethod, double[] results) {
        double bestTime = Arrays.stream(results).min().getAsDouble();
        double worstTime = Arrays.stream(results).max().getAsDouble();
        double averageTime = Arrays.stream(results).average().getAsDouble();
        System.out.printf("| %-14s | %-15.4f | %-12.4f | %-15.4f |%n", sortMethod, bestTime, averageTime, worstTime);
    }

    private static double[] timeSortMethod(Consumer<int[]> sortMethod) {
        double mergeSequentialTime = timeSingleSort(sortMethod, createSequential());
        double mergeRandomTime = timeSingleSort(sortMethod, createRandom());
        double mergeSequentialDescendingTime = timeSingleSort(sortMethod, createDescending());
        return new double[] { mergeSequentialTime, mergeRandomTime, mergeSequentialDescendingTime };
    }

    private static double timeSingleSort(Consumer<int[]> sortMethod, int[] data) {
        long startTime = System.nanoTime();
        sortMethod.accept(data);
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000.0;
    }

    private static int[] createSequential() {
        int[] data = new int[arraySize];
        for (int i = 0; i < arraySize; i++) {
            data[i] = i + 1;
        }
        return data;
    }

    private static int[] createRandom() {
        SecureRandom generator = new SecureRandom();
        return generator.ints(arraySize, 1, arraySize).toArray();
    }

    private static int[] createDescending() {
        int[] data = new int[arraySize];
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
        int combinedIndex = left; // index into temporary working array
        int[] combined = new int[data.length]; // working array

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
        for (int i = left; i <= right; i++) {
            data[i] = combined[i];
        }
    }
}
