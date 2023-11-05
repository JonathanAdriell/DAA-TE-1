import java.util.Arrays;
import java.util.Scanner;

public class CBIS {
    private static Scanner in = new Scanner(System.in);

    // Perform binary search
    public static int binaryLocFinder(int[] arr, int start, int end, int key) {
        if (start == end) {
            if (arr[start] > key) {
                return start;
            } else {
                return start + 1;
            }
        }

        if (start > end) {
            return start;
        }

        int middle = (start + end) / 2;
        if (arr[middle] < key) {
            return binaryLocFinder(arr, middle + 1, end, key);
        } else if (arr[middle] > key) {
            return binaryLocFinder(arr, start, middle - 1, key);
        } else {
            return middle;
        }
    }

    // Insert end to its appropriate position
    public static int[] placeInserter(int[] arr, int start, int end) {
        int temp = arr[end];
        for (int k = end; k > start; k--) {
            arr[k] = arr[k - 1];
        }
        arr[start] = temp;
        return arr;
    }

    // Perform clustered binary insertion sort
    public static void clusteredBinaryInsertionSort(int[] arr) {
        int POP = 0;
        for (int i = 1; i < arr.length; i++) {
            int COP = i;
            int key = arr[COP];
            int place;
            if (key >= arr[POP]) {
                place = binaryLocFinder(arr, POP + 1, COP - 1, key);
            } else {
                place = binaryLocFinder(arr, 0, POP - 1, key);
            }
            POP = place;
            arr = placeInserter(arr, place, COP);
        }
    }

    // Calculate clustered binary insertion sort memory usage and running time
    public static long[] calculateCBISMemoryAndTime(int[] arr) {
        long startTime = System.currentTimeMillis();

        clusteredBinaryInsertionSort(arr);
        
        long endTime = System.currentTimeMillis();
        long memoryUsed = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        long[] result = new long[2];
        result[0] = memoryUsed;
        result[1] = endTime - startTime;

        return result;
    }
    
    // Check the equality of two integer arrays
    public static boolean checkArrayEquality(int[] arr1, int[] arr2) {
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // Process input
        String input = in.nextLine();
        String[] numberStr = input.substring(1, input.length() - 1).split(", ");

        // Convert numberStr into integer array
        int[] numbers = new int[numberStr.length];
        for (int i = 0; i < numberStr.length; i++) {
            numbers[i] = Integer.parseInt(numberStr[i]);
        }

        // // Create a copy
        // int[] sortedNumbers = Arrays.copyOf(numbers, numbers.length);

        // // Sort the copy
        // Arrays.sort(sortedNumbers);

        // // Check the input array
        // System.out.println("Input array:\n" + Arrays.toString(numbers));

        // Calculate CBIS memory usage and running time
        long[] result = calculateCBISMemoryAndTime(numbers);

        // // Check result
        // System.out.println("\nIs the array after CBIS sorted?");
        // System.out.println("Answer: " + Boolean.toString(checkArrayEquality(numbers, sortedNumbers)));
        // System.out.println();

        // Print CBIS memory usage
        System.out.println("Total memory usage: " + result[0] + " bytes");

        // Print CBIS running time
        System.out.println("Total running time: " + result[1] + " ms");

        in.close();
    }
}
