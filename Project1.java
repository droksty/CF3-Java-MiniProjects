package gr.aueb.cf.projects;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

/**
 *  CF3 - Project 1
 *
 * @version 0.1
 * @author droksty
 */
public class Project1 {

    public static void main(String[] args) {
        final int MIN_INTS = 6 + 1;
        final int MAX_INTS = 49; // ATTENTION! Lower MAX_INTS value before running, to avoid possible lengthy(t) file write
        final int MIN_VAL = 1;
        final int MAX_VAL = 49;
        final int N = 6;

        String inFilename = "inFile.txt";
        String outFilename = "outFile.txt";
        File dir = new File("C:/tmp/droksty/");
        File inFile = new File(dir + "/" + inFilename);
        File outFile = new File(dir + "/" + outFilename);

        //
        createDir(dir);
        generateFile(inFile, inFilename, MIN_INTS, MAX_INTS, MIN_VAL, MAX_VAL);

        printFile(inFile); // Test print file's contents. Expected
        System.out.printf("%s contains %d integers\n", inFilename, getCount(inFile)); // Expected

        int[] ints = new int[getCount(inFile)];
        populateArray(ints, inFile);
        traverseArray(ints); // Expected

        Arrays.sort(ints);
        traverseArray(ints); // Expected

        getCombinations(ints, N, outFile);
    }


    // Functions

    /**
     * Gets all possible combinations of N from an array and writes filtered combinations to a file
     * @param arr   The array to read from
     * @param file  The file to write to.
     */
    public static void getCombinations(int[] arr, int N, File file) {
        int[] tempArr = new int[N];

        if (file.exists()) file.delete();

        for (int i = 0; i <= arr.length - N; i++) {
            for (int j = i + 1; j <= arr.length - N + 1; j++) {
                for (int k = j + 1; k <= arr.length - N + 2; k++) {
                    for (int l = k + 1; l <= arr.length - N + 3; l++) {
                        for (int m = l + 1; m <= arr.length - N + 4; m++) {
                            for (int n = m + 1; n <= arr.length - N + 5; n++) {
                                tempArr[0] = arr[i];
                                tempArr[1] = arr[j];
                                tempArr[2] = arr[k];
                                tempArr[3] = arr[l];
                                tempArr[4] = arr[m];
                                tempArr[5] = arr[n];

                                /*// Test print all possible combinations, to cross-check.
                                System.out.printf("%d\t%d\t%d\t%d\t%d\t%d\n",
                                        tempArr[0], tempArr[1],tempArr[2],tempArr[3],tempArr[4],tempArr[5]);
                                // Expected*/

                                if (filterCombination(tempArr)) writeFile(tempArr, file);
                            }
                        }
                    }
                }
            }
        }
    }


    /**
     * Filters an array to match project criterias.
     * @param arr   The array to filter
     * @return  true if array matches all criteria, false otherwise
     */
    public static boolean filterCombination(int[] arr) {
        return (!isEven(arr, 4)) && (!isOdd(arr, 4)) &&
                (!isContiguous(arr)) && (!isSameEnding(arr, 3)) &&
                (!isSameTen(arr, 3));
    }


    /**
     * Check an array for counts of tens
     * @param arr   The array to check
     * @return  true, if a count of a grouping of tens is bigger than THRESHOLD, false otheriwse
     */
    public static boolean isSameTen(int[] arr, int THRESHOLD) {
        int ones = 0;
        int tens = 0;
        int twenties = 0;
        int thirties = 0;
        int forties = 0;

        for (int num : arr) {
            if (num / 10 == 0) ones++;
            else if (num / 10 == 1) tens++;
            else if (num / 10 == 2) twenties++;
            else if (num / 10 == 3) thirties++;
            else if (num / 10 == 4) forties++;
        }

        return ((ones > THRESHOLD) || (tens > THRESHOLD)|| (twenties > THRESHOLD) || (thirties > THRESHOLD) || (forties > THRESHOLD));
    }

    /**
     * Checks if an array has more than THRESHOLD count of the same ending number, contiguously
     * @param arr   The array to check
     * @return  True, if there are more than THRESHOLD count of the same ending number, contiguously
     */
    public static boolean isSameEnding(int[] arr, int THRESHOLD) {
        int count = 0;
        int lastIndex = arr.length - 1;

        for (int i = lastIndex; i >= arr.length - THRESHOLD; i--) {
           if (arr[lastIndex] == arr[i-1]) {
               count++;
           } else {
               break;
           }
        }
        return count >= THRESHOLD;
    }


    /**
     * Checks if an array has 2 contiguous numbers
     * @param arr The array to check
     * @return  true, if passed array contains two contiguous numbers, false otherwise
     */
    public static boolean isContiguous(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] == arr[i+1]) {
                return true;
            }
        }
        return false;
    }


    /**
     * Checks if an array contains more than a certain amount of even ints.
     * @param arr   The array to check
     * @param THRESHOLD number to compare count of even ints with
     * @return  true, if count of even ints is greater than threshold
     */
    public static boolean isEven(int[] arr, int THRESHOLD) {
        int count = 0;

        for (int num : arr) {
            if (num % 2 == 0) count++;
        }

        return (count > THRESHOLD);
    }


    /**
     * Checks if an array contains more than a certain amount of odd ints.
     * @param arr   The array to check
     * @param THRESHOLD number to compare count of odd ints with
     * @return  true, if count of odd ints is greater than threshold
     */
    public static boolean isOdd(int[] arr, int THRESHOLD) {
        int count = 0;

        for (int num : arr) {
            if (num % 2 != 0) count++;
        }

        return (count > THRESHOLD);
    }

    /**
     * Writes an array's elements to a file
     * @param arr   The array to read from
     * @param outFile   The file to write to
     */
    public static void writeFile(int[] arr, File outFile) {
        try (PrintWriter pw = new PrintWriter(new FileOutputStream(outFile, true))) {
            pw.printf("%d\t%d\t%d\t%d\t%d\t%d\n", arr[0], arr[1], arr[2], arr[3], arr[4], arr[5]);
        } catch (FileNotFoundException e){
            System.err.printf("Unexpected error. Please contact sysadmin.\n%s\n", e.getMessage());
        }
    }


    /**
     * Traverses passed array to print all elements
     * @param arr   Array to be traversed
     */
    public static void traverseArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " | ");
        }

        System.out.println();
    }


    /**
     * Poupulates passed array with values from a file
     * @param arr   The array to be populated
     * @param file  The file to populate array from
     */
    public static void populateArray(int[] arr, File file) {
        try (Scanner in = new Scanner(file)) {
            for (int i = 0; i < arr.length; i++) {
                arr[i] = in.nextInt();
            }
        } catch (FileNotFoundException e) {
            System.err.printf("Unexpected error. Please contact sysadmin.\n%s\n", e.getMessage());
        }
    }


    /**
     * Counts how many integers are inside the passed file
     * @param file  The passed file
     * @return  Count of integers inside passed file
     */
    public static int getCount(File file) {
        int count = 0;
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextInt()) {
                sc.nextInt();
                count++;
            }
            return count;
        } catch (FileNotFoundException e) {
            System.err.printf("Unexpected error. Please contact sysadmin.\n%s\n", e.getMessage());
            return -1;
        }
    }


    /**
     * Prints passed file's contents at std. out
     * @param file  The passed file
     */
    public static void printFile(File file) {
        try (Scanner in = new Scanner(file)) {
            while (in.hasNextInt()) {
                int num = in.nextInt();
                System.out.print(num + " ");
            }
            System.out.println();
        } catch (FileNotFoundException e) {
            System.err.printf("Unexpected error. Please contact sysadmin.\n%s\n", e.getMessage());
        }
    }


    /**
     * Generates a new file and writes a random count of random integers
     * @param file   The file path where the file is created
     * @param filename  The filename
     * @param MIN_INTS  The minimum count of ints to be written
     * @param MAX_INTS  The maximum count of ints to be written
     * @param MIN_VAL   The minimum value of an integer
     * @param MAX_VAL   The maximum value of an integer
     */
    public static void generateFile(File file, String filename, int MIN_INTS, int MAX_INTS, int MIN_VAL, int MAX_VAL) {
        try (PrintStream ps = new PrintStream(file)) {
            for (int i = 0; i < generateRandomInt(MIN_INTS, MAX_INTS); i++) {
                ps.println(generateRandomInt(MIN_VAL, MAX_VAL));
            }

            System.out.printf("Created %s\n", filename);
        } catch (FileNotFoundException e) {
            System.err.printf("Critical error. Please contact sysadmin.\n%s\n", e.getMessage());
        }
    }


    /**
     * Returns a random int between min and max parameters
     * @param min   min int value
     * @param max   max int value
     * @return  a random int between min and max parameters
     */
    public static int generateRandomInt(int min, int max) {
        return (int) (Math.random() * ((max - min) + 1) + min);
    }


    /**
     * Creates in/out files directory
     * @param dir   the passed directory path
     */
    public static void createDir(File dir) {
        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                System.err.printf("Fatal error in mkdir. Consider manually creating directory with path: \"%s\\\"\n", dir);
                System.exit(1);
            }

            System.out.println("Created directory");
        }
    }
}
