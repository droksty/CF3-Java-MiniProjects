package gr.aueb.cf.projects;

/**
 * Mini Projects - Project 5
 * Theater seats manager
 * 
 * Theater seats layout: 30 rows (1-30) x 12 columns (A-L)
 *
 * @version 0.2
 */
public class Project5 {
    static int[][] seats = new int[30][12];
    static final int MAGIC_KEY = 64;

    public static void main(String[] args) {
        // Test begin

        traverseSeats(); // Expected

        seats[29][0] = 1;
        seats[28][1] = 1;
        traverseSeats(); // Expected

        book('a', 28);
        traverseSeats(); // Expected

        book('a', 28);
        traverseSeats(); // Expected

        book('l', 30);
        traverseSeats(); // Expected

        cancel('l', 30);
        traverseSeats(); // Expected

        cancel('l', 30);
        traverseSeats(); // Expected

        // Test end
    }


    // Methods

    public static void book(char column, int row) {
        if (isBooked(column, row)) {
            System.out.println("Seat is already booked");
        } else {
            int columnIndex = getColumnIndex(column);
            seats[row - 1][columnIndex - 1] = 1;
            System.out.println("Seat reserved. Thank you");
        }
    }


    public static void cancel(char column, int row) {
        if (isBooked(column, row)) {
            System.out.println("Seat released");
            int columnIndex = getColumnIndex(column);
            seats[row - 1][columnIndex - 1] = 0;
        } else {
            System.out.println("Cancel aborted. Seat was already empty.");
        }
    }


    // Utilities

    public static boolean isBooked(char column, int row) {
        int columnIndex = getColumnIndex(column);
        //System.out.println(columnIndex); // Expected

        return seats[row - 1][columnIndex - 1] != 0;
    }


    public static int getColumnIndex(char column) {
        return Character.toUpperCase(column) - MAGIC_KEY;
    }


    public static void traverseSeats() {
        System.out.println("         A B C D E F G H I J K L");
        System.out.println("         _ _ _ _ _ _ _ _ _ _ _ _");
        int rowIndex = 1;
        for (int i = 0; i < seats.length; i++) {
            if (i < 9) {
                System.out.printf("Row  %d | ", rowIndex);
            } else {
                System.out.printf("Row %d | ", rowIndex);
            }
            rowIndex++;

            for (int col : seats[i]) {
                System.out.print(col + " ");
            }

            System.out.println();
        }
    }
}
