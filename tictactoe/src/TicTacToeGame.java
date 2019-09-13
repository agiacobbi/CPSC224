/**
 * This program is a game driver for a TicTacToeBoard. It allows users
 * to play NxN Tic Tac Toe and displays performance stats for both
 * players at the end of each game. At the end of a game, users can quit
 * or play another game.
 * CPSC 224-01, Fall 2019
 * Programming Assignment #2
 * No sources to cite.
 *
 * @author Alex Giacobbi
 * @version v1.0 9/10/19
 */

import java.util.Scanner;

public class TicTacToeGame {
    public static void main(String[] args) {
        int N;
        boolean playAgain = true;
        GameStats playerX = new GameStats('X');
        GameStats playerO = new GameStats('O');

        System.out.println("Welcome to Tic Tac Toe! There are two players, player 'X' and player 'O'...");
        N = getDimensions();

        // Plays games and displays stats at the end of each game until user chooses to quit programs
        while (playAgain) {
            playGame(N, playerX, playerO);
            System.out.println(playerX);
            System.out.println(playerO);
            playAgain = wantToPlayAgain();
        }

        System.out.println("Thanks for playing. Goodbye.");
    }

    /**
     * Gets a set of dimensions from user in range [3, 9]
     *
     * @return integer number of dimensions for a Tic Tac Toe board
     */
    private static int getDimensions() {
        Scanner kb = new Scanner(System.in);
        int dimensions;

        System.out.print("Please enter a dimension, N, of the N x N Tic Tac Toe board (an integer in [3, 9]): ");
        dimensions = kb.nextInt();

        while (dimensions < 3 || dimensions > 9) {
            System.out.println("Enter a number in range [3, 9]");
            dimensions = kb.nextInt();
        }

        return dimensions;
    }

    /**
     * Checks if user would like to play again or quit program
     *
     * @return true if user would like to play another game false otherwise
     */
    private static boolean wantToPlayAgain() {
        Scanner kb = new Scanner(System.in);
        char playing;

        System.out.print("Would you like to play again? Enter 'y' to play or 'q' to quit: ");
        playing = kb.next().charAt(0);

        while (playing != 'y' && playing != 'q') {
            System.out.print("Invalid input. Enter 'y' or 'q': ");
            playing = kb.next().charAt(0);
        }

        return playing == 'y';
    }

    /**
     * Gets coordinates valid coordinates for a plyer move and executes move
     *
     * @param board TicTacToeBoard user is making move on
     * @param player character symbol of player making move
     */
    private static void takeTurn(TicTacToeBoard board, char player) {
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

    /**
     * Updates game states for both player the end of a game
     *
     * @param winner character indicating winner ('X' or 'O') of the game '-' if a tie
     * @param x GameStats for player X
     * @param o GameStats for player O
     */
    private static void updateGameStats(char winner, GameStats x, GameStats o) {
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

    /**
     * Handles game turn loop for a game of Tic Tac Toe. Game is over when
     * the board is full or a player has made a winning move.
     *
     * @param dimensions integer number of dimensions of TicTacToeBoard
     * @param playerX GameStats for player X
     * @param playerO GameStats for player O
     */
    private static void playGame(int dimensions, GameStats playerX, GameStats playerO) {
        TicTacToeBoard board = new TicTacToeBoard(dimensions);
        boolean isWon = false;
        int index = (int)(Math.random() * 2);          // Randomly selects which player will go first
        char winner = '-';
        char[] players = {'X', 'O'};                   // Players stored in array and accessed in alternating pattern

        System.out.println("Player " + players[index] + " is going first");
        System.out.println(board);

        while (!isWon && !board.isFull()) {
            takeTurn(board, players[index]);
            isWon = board.isWinner(players[index]);
            if (isWon) {
                winner = players[index];
            }
            index = (index + 1) % 2;
        }

        updateGameStats(winner, playerX, playerO);
    }
}
