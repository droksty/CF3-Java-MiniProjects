package gr.aueb.cf.projects;

import java.util.Arrays;

/**
 * CF3 - Project 3
 * Develop one function that shallow copies a 2D Array
 * Develop one function that deep copies a 2D Array
 * Test functions in main
 *
 * @version 0.1
 * @author droksty
 */
public class Project3 {

    public static void main(String[] args) {
        //
        int[][] odds = { {1, 1, 1, 1}, {3, 3, 3, 3}, {5, 5, 5, 5}, {7, 7, 7, 7} };
        System.out.println("odds:");
        printArray(odds); // Expected

        System.out.println("Initialize oddsClone with shallow copy of odds");
        int[][] oddsClone = shallowCopy(odds);
        System.out.println("oddsClone:");
        printArray(oddsClone); // Expected


        System.out.println("Change some values on oddsClone\nCompare arrays");
        Arrays.fill(oddsClone[0], 9);
        System.out.println("oddsClone:");
        printArray(oddsClone); // Expected
        System.out.println("odds:");
        printArray(odds); // Expected


        System.out.println("Initialize deepClone with deep copy of odds");
        int[][] deepClone = deepCopy(odds);
        System.out.println("deepClone:");
        printArray(deepClone); //Expected
        System.out.println("odds:");
        printArray(odds); // Expected
        System.out.println("Change some values on deepClone\nCompare arrays");
        Arrays.fill(deepClone[0], 1);
        System.out.println("deepClone:");
        printArray(deepClone); //Expected
        System.out.println("odds:");
        printArray(odds); // Expected
    }


    // Functions

    /**
     * Copies a 2D array
     * @param arr   The 2D array to copy
     * @return  The 2D array to copy
     */
    public static int[][] shallowCopy(int[][] arr) {
        return arr;
    }

    /**
     * Clones a 2D array
     * @param arr   The 2D array to copy
     * @return  The cloned 2D array
     */
    public static int[][] deepCopy(int[][] arr) {
        int[][] arrClone = new int[arr.length][];

        for (int row = 0; row < arr.length; row++) {
            arrClone[row] = new int[arr[row].length];
            for (int col = 0; col < arr[row].length; col++) {
                arrClone[row][col] = arr[row][col];
            }
        }
        return arrClone;
    }

    /**
     * Traverses a 2D Array and prints all elements
     * @param arr   The 2D array
     */
    public static void printArray(int[][] arr) {
        for (int[] row : arr) {
            for (int col : row) {
                System.out.print(col + " ");
            }

            System.out.println();
        }
    }
}
