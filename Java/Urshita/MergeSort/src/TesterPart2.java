import java.util.Arrays;
import java.util.Random;

public class TesterPart2 {
    public static void main(String[] args) {
        System.out.println("Sorting Algorithm\t\tn\t\tTime (ms)");
        System.out.println("-------------------------------------------");

        int maxN = 1000000;
        int minN = 10;
        int step = 10;

        for (int n = minN; n <= maxN; n *= step) {
            int[] array1 = generateRandomArray(n);
            int[] array2 = Arrays.copyOf(array1, array1.length);
            int[] array3 = Arrays.copyOf(array1, array1.length);

            long startTime, endTime;

            // Merge Sort Iterative
            startTime = System.nanoTime();
            A2.mergeSortIterative(array1);
            endTime = System.nanoTime();
            long mergeSortIterTime = (endTime - startTime) / 1_000_000;

            // Merge Sort Recursive
            startTime = System.nanoTime();
            A2.mergeSortRecursive(array2);
            endTime = System.nanoTime();
            long mergeSortRecurTime = (endTime - startTime) / 1_000_000;

            // Quicksort
            startTime = System.nanoTime();
            Arrays.sort(array3);
            endTime = System.nanoTime();
            long quickSortTime = (endTime - startTime) / 1_000_000;

            System.out.printf("Merge Sort Iterative\t%d\t\t%d ms%n", n, mergeSortIterTime);
            System.out.printf("Merge Sort Recursive\t%d\t\t%d ms%n", n, mergeSortRecurTime);
            System.out.printf("Quicksort\t\t\t%d\t\t%d ms%n", n, quickSortTime);

            if (mergeSortIterTime >= 10000) {
                break; // Stop if the time exceeds 10 seconds
            }
        }
    }

    private static int[] generateRandomArray(int n) {
        int[] array = new int[n];
        Random random = new Random();

        for (int i = 0; i < n; i++) {
            array[i] = random.nextInt();
        }

        return array;
    }
}

