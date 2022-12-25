package gr.aueb.cf.projects;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * CF3 Mini Projects - Project 4
 * Tic-Tac-Toe
 *
 * @version 0.1
 * @author droksty
 */
public class Project4 {
    //
    static final Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        //
        char[][] theBoard = new char[3][3];
        char player1Mark = ' ';
        char player2Mark = ' ';
        int playerTurn; // 1 = Player1, 2 = Player2
        int[] playerMove = new int[2]; // playerMove[0] = row, playerMove[1] = column

        // Game Init
        populateBoard(theBoard);
        printIntro();
        player1Mark = getPlayerMark();
        player2Mark = getPlayerMark(player1Mark);
        printPlayerMarks(player1Mark, player2Mark);
        playerTurn = whoPlaysFirst();
        printBoard(theBoard);

        // Main Loop
        do {
            System.out.printf("Player%d make your move!\n", playerTurn);

            do {
                getPlayerMove(playerMove, playerTurn);
                if (isMoveAllowed(theBoard, playerMove[0], playerMove[1])){
                    break;
                } else {
                    System.out.printf("Invalid move: Position is not empty!!\nPlayer%d make a different move\n", playerTurn);
                    printBoard(theBoard);
                }
            } while (true);

            makeMove(theBoard, playerTurn, player1Mark, player2Mark, playerMove[0], playerMove[1]);
            printBoard(theBoard);

            if (hasWon(theBoard, playerTurn, player1Mark, player2Mark)) {
                System.out.printf("Player%d has won the game!!\n", playerTurn);
                System.out.println("Thank you for playing Tic-Tac-Toe!");
                break;
            } else if (isTie(theBoard)) {
                break;
            } else {
                playerTurn = switchTurn(playerTurn);
            }

        } while (true);
    }

    // Functions
    /**
     * Alternates player turns.
     * @param turn  Which player is currently playing
     * @return  1 -> Player1, 2 -> Player2
     */
    public static int switchTurn(int turn) {
        return (turn == 1) ? 2 : 1;
    }

    /**
     * Checks whether Game Over with tie
     * @param board The 3x3 game board
     * @return true if the game is tie, false otherwise
     */
    public static boolean isTie(char[][] board) {
        for (char row[] : board) {
            for (char col : row) {
                if (col == ' ') {
                    return false;
                }
            }
        }

        System.out.println("Game is tied! Excellent play!\nGame Over.");
        return true;
    }

    /**
     * Checks whether player's move won the game.
     * @param board The 3x3 game board
     * @param turn  Which player is currently playing
     * @param playerMark1   Player1's mark. 'x' or 'o'
     * @param playerMark2   Player2's mark. 'o' or 'x'
     * @return true if player won the game, false otherwise
     */
    public static boolean hasWon(char[][] board, int turn, char playerMark1, char playerMark2) {
        char pm = (turn == 1) ? playerMark1 : playerMark2;

        return (
                ((board[0][0] == pm) && (board[0][1] == pm) && (board[0][2] == pm)) ||
                ((board[1][0] == pm) && (board[1][1] == pm) && (board[1][2] == pm)) ||
                ((board[2][0] == pm) && (board[2][1] == pm) && (board[2][2] == pm)) ||
                ((board[0][0] == pm) && (board[1][0] == pm) && (board[2][0] == pm)) ||
                ((board[0][1] == pm) && (board[1][1] == pm) && (board[2][1] == pm)) ||
                ((board[0][2] == pm) && (board[1][2] == pm) && (board[2][2] == pm)) ||
                ((board[0][0] == pm) && (board[1][1] == pm) && (board[2][2] == pm)) ||
                ((board[0][2] == pm) && (board[1][1] == pm) && (board[2][0] == pm))
        );
    }

    /**
     * Marks the board with player's move
     * @param board The 3x3 game board
     * @param turn  Which player is currently playing
     * @param playerMark1   Player1's mark. 'x' or 'o'
     * @param playerMark2   Player2's mark. 'o' or 'x'
     * @param row   Game board row player wants to make a move
     * @param col   Game board column player want to make a move
     */
    public static void makeMove(char[][] board, int turn, char playerMark1, char playerMark2, int row, int col) {
        board[row - 1][col - 1] = (turn == 1) ? playerMark1 : playerMark2;
    }

    /**
     * Checks whether the position provided by the player is indeed empty.
     * @param board The 3x3 game board
     * @param row   Game board row player wants to make a move
     * @param col   Game board column player want to make a move
     * @return  true, if the move provided is possible. false, otherwise.
     */
    public static boolean isMoveAllowed(char[][]board, int row, int col) {
        return board[row - 1][col - 1] == ' ';
    }

    /**
     * Asks user to input board row and board column they wish to make a move at.
     * @param userMove  Array to store player input
     * @param playerTurn    Which player is currently playing
     * @return  Array that stores player's input
     */
    public static int[] getPlayerMove(int[] userMove, int playerTurn) {
        int row = 0;
        int column = 0;

        System.out.printf("Player%d insert row to place your mark (1-3):\n", playerTurn);
        do {
            try {
                row = in.nextInt();
                System.out.println(row);
                if ((row == 1) || ( row == 2) || ( row == 3)) {
                    userMove[0] = row;
                    break;
                } else {
                    System.err.println("Error: Incorrect user input.");
                    System.out.printf("Player%d please insert an integer from 1 to 3\n", playerTurn);
                }
            } catch (InputMismatchException e) {
                System.err.println("Error: Incorrect user input.");
                System.out.printf("Player%d please insert an integer from 1 to 3\n", playerTurn);
                in.next();
            }
        } while (true);

        System.out.printf("Player%d insert column to place your mark:\n", playerTurn);
        do {
            try {
                column = in.nextInt();
                if ((column == 1) || ( column == 2) || ( column == 3)) {
                    userMove[1] = column;
                    break;
                } else {
                    System.err.println("Error: Incorrect user input.");
                    System.out.printf("Player%d please insert an integer from 1 to 3\n", playerTurn);
                }
            } catch (InputMismatchException e) {
                System.err.println("Error: Incorrect user input.");
                System.out.printf("Player%d please insert an integer from 1 to 3\n", playerTurn);
                in.next();
            }
        } while (true);

        return userMove;
    }

    /**
     * Prints the board that was passed
     * @param board The board to print
     */
    public static void printBoard(char[][] board) {
        System.out.println("*".repeat(11));
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print("|" + board[i][j] + "| ");
            }
            System.out.println();
        }
        System.out.println("*".repeat(11));
    }

    /**
     * Decides which player will play first
     * @return 1 for Player1, 2 for Player2
     */
    public static int whoPlaysFirst() {
        int turn = (int) (Math.random() * 2 + 1);
        System.out.printf("Player%d shall play first!\n", (turn == 1) ? 1 : 2);
        return turn;
    }

    /**
     * Prints player marks selected
     * @param player1Mark   Player1's mark. 'x' or 'o'
     * @param player2Mark   Player2's mark. 'o' or 'x'
     */
    public static void printPlayerMarks(char player1Mark, char player2Mark) {
        System.out.printf("Player1 mark is: %c\nPlayer2 mark is: %c\n", player1Mark, player2Mark);
    }

    /**
     * Sets player mark. Used to set Player2 mark
     * @param player1Mark Player1 mark
     * @return 'x' or 'o'
     */
    public static char getPlayerMark(char player1Mark) {
        return (player1Mark == 'x') ? 'o' : 'x';
    }

    /**
     * Sets player mark
     * @return 'x' or 'o'
     */
    public static char getPlayerMark() {
        do {
            char validator = in.next().toLowerCase().charAt(0);
            if ((validator == 'x') || (validator == 'o')) {
                return validator;
            }
            System.out.println("Error: Incorrect user input.\nPlease insert 'X/x' or 'O/o'.");
        } while (true);
    }

    /**
     * Prints the game's intro text
     */
    public static void printIntro() {
        System.out.println("\nWelcome to Tic-Tac-Toe!\nThe game is played by 2 players, alternating turns.");
        System.out.println("Player 1, do you want to be X or O? (Insert X/x or O/o)");
    }

    /**
     * Populates the array that passed
     * @param board The array to populate
     */
    public static void populateBoard(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = ' ';
            }
        }
    }
}
