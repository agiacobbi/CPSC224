import java.util.Random;
import java.util.Scanner;

public class TicTacToeGame {
    public static void main(String[] args) {
        int N = getDimensions();
        boolean playAgain = true;

        while (playAgain) {
            playGame(N);
            playAgain = wantToPlayAgain();
        }

        System.out.println("Thanks for playing. Goodbye.");
    }

    public static int getDimensions() {
        Scanner kb = new Scanner(System.in);
        int dimensions = 0;

        System.out.println("Enter an integer for dimensions of a tic tac toe board in range [3, 9]");
        dimensions = kb.nextInt();

        while (dimensions < 3 || dimensions > 9) {
            System.out.println("Enter a number in range [3, 9]");
            dimensions = kb.nextInt();
        }

        return dimensions;
    }

    public static boolean wantToPlayAgain() {
        Scanner kb = new Scanner(System.in);
        char playing;

        System.out.println("Would you like to play again?");
        playing = kb.next().charAt(0);

        while (playing != 'y' && playing != 'n') {
            System.out.println("Invalid input. Enter y or n");
            playing = kb.next().charAt(0);
        }

        if (playing == 'y') {
            return true;
        }
        return false;
    }

    public static void takeTurn(TicTacToeBoard board, char player, int dimensions) {
        Coordinates coordinates = generateCoordinates(dimensions);
        System.out.println("generating coordinates for " + player);

        while (!board.isValidMove(coordinates)) {
            coordinates = generateCoordinates(dimensions);
        }

        System.out.println(coordinates);
        board.makeMove(coordinates, player);
    }

    public static Coordinates generateCoordinates(int dimensions) {
        Random random = new Random();

        return new Coordinates(random.nextInt(dimensions), random.nextInt(dimensions));
    }

    public static void printBoard(TicTacToeBoard board) {
        System.out.println();
        System.out.println(board);
    }

    public static void playGame(int dimensions) {
        TicTacToeBoard board = new TicTacToeBoard(dimensions);
        boolean isWon = false;
        char winner = '-';

        while (!isWon && !board.isFull()) {
            takeTurn(board, 'X', dimensions);
            printBoard(board);
            if (board.isWinner('X')) {
                isWon = true;
                winner = 'X';
                break;
            }

            takeTurn(board, 'O', dimensions);
            printBoard(board);
            if (board.isWinner('O')) {
                isWon = true;
                winner = 'O';
            }
        }

        System.out.println("the winner is, " + winner + "!");
    }
}
