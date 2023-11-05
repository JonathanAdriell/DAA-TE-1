import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class RQS {
    private static Scanner in = new Scanner(System.in);
    
    // Perform random partition
    public static int randomPartition(int[] arr, int left, int right) {
        Random random = new Random();
        int randomIndex = left + random.nextInt(right - left + 1);
        int pivot = arr[randomIndex];
        swap(arr, randomIndex, right);
        int lastFilled = left - 1;

        for (int i = left; i < right; i++) {
            if (arr[i] <= pivot) {
                lastFilled++;
                swap(arr, lastFilled, i);
            }
        }

        lastFilled++;
        swap(arr, lastFilled, right);

        return lastFilled;
    }

    // Perform randomized quick sort
    public static void randomizedQuickSort(int[] arr, int left, int right) {
        if (left < right) {
            int finalPivotPos = randomPartition(arr, left, right);
            randomizedQuickSort(arr, left, finalPivotPos - 1);
            randomizedQuickSort(arr, finalPivotPos + 1, right);
        }
    }

    // Swaps two elements in an integer array
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // Calculate randomized quick sort memory usage and running time
    public static long[] calculateRQSMemoryAndTime(int[] arr, int n) {
        long startTime = System.currentTimeMillis();

        randomizedQuickSort(arr, 0, n - 1);
        
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

        // Calculate RQS memory usage and running time
        long[] result = calculateRQSMemoryAndTime(numbers, numbers.length);

        // // Check result
        // System.out.println("\nIs the array after RQS sorted?");
        // System.out.println("Answer: " + Boolean.toString(checkArrayEquality(numbers, sortedNumbers)));
        // System.out.println();
        
        // Print RQS memory usage
        System.out.println("Total memory usage: " + result[0] + " bytes");

        // Print RQS running time
        System.out.println("Total running time: " + result[1] + " ms");

        in.close();
    }
}
