import java.util.Random;
import java.util.Scanner;

public class TicTacToeGame {
    public static void main(String[] args) {
        int N;
        boolean playAgain = true;
        GameStats playerX = new GameStats('X');
        GameStats playerO = new GameStats('O');

        System.out.println("Welcome to Tic Tac Toe! There are two players, player 'X' and player 'O'...");
        N = getDimensions();

        while (playAgain) {
            playGame(N, playerX, playerO);
            System.out.println(playerX);
            System.out.println(playerO);
            playAgain = wantToPlayAgain();
        }

        System.out.println("Thanks for playing. Goodbye.");
    }

    public static int getDimensions() {
        Scanner kb = new Scanner(System.in);
        int dimensions = 0;

        System.out.printf("Please enter a dimension, N, of the N x N Tic Tac Toe board (an integer in [3, 9]): ");
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

        System.out.print("Would you like to play again? Enter 'y' to play or 'q' to quit: ");
        playing = kb.next().charAt(0);

        while (playing != 'y' && playing != 'q') {
            System.out.print("Invalid input. Enter 'y' or 'q': ");
            playing = kb.next().charAt(0);
        }

        if (playing == 'y') {
            return true;
        }
        return false;
    }

    public static void takeTurn(TicTacToeBoard board, char player, int dimensions) {
        Scanner kb = new Scanner(System.in);
        Coordinates playerMove = new Coordinates();

        System.out.print("Player " + player + ", please enter the coordinates of your placement: ");
        playerMove.setCoordinate(kb.nextInt(), kb.nextInt());

        while (!board.isValidMove(playerMove)){
            System.out.println(playerMove + " is not a valid move!\n");
            System.out.print("Player " + player + ", please enter the coordinates of your placement: ");
            playerMove.setCoordinate(kb.nextInt(), kb.nextInt());
        }

        board.makeMove(playerMove, player);
        System.out.println(board);
    }

    public static void playGame(int dimensions, GameStats x, GameStats o) {
        TicTacToeBoard board = new TicTacToeBoard(dimensions);
        boolean isWon = false;
        int index = (int)Math.random() * 2;
        char winner = '-';
        char[] players = {'X', 'O'};

        System.out.println("Player " + players[index] + " is going first");
        System.out.println(board);

        while (!isWon && !board.isFull()) {
            /*if (turn % 2 == 0) {
                takeTurn(board, 'X', dimensions);
                isWon = board.isWinner('X');
                winner = 'X';
            } else {
                takeTurn(board, 'O', dimensions);
                isWon = board.isWinner('O');
                winner = 'O';
            }
            turn++;*/
            takeTurn(board, players[index], dimensions);
            isWon = board.isWinner(players[index]);
            if (isWon) {
                winner = players[index];
            }
            index = (index + 1) % 2;
        }

        if (winner == 'X') {
            System.out.println(winner + " won!\n");
            x.addWin();
            o.addLoss();
        } else if (winner == 'O') {
            System.out.println(winner + " won!\n");
            x.addLoss();
            o.addWin();
        } else {
            System.out.println("Scratch game. Too bad.\n");
            x.addScratch();
            o.addScratch();
        }
    }
}
