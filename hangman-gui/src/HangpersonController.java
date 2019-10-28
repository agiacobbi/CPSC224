/**
 * This program implements a controller for a hangman game
 * CPSC 224-01, Fall 2019
 * Programming Assignment #4
 * No sources to cite.
 *
 * @author Alex Giacobbi
 * @version v1.0 10/13/19
 */

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class HangpersonController is the controller for our hangman game.
 * It connects the game logic to the graphical interface to allow user
 * to make guesses and start new games. There is a constructor that
 * initializes the view and connects the model. It also sets up the
 * view for a new game and adds listeners to both buttons with the help
 * of an addListeners() method. The addListener helper method adds button
 * listeners to both the Guess! button and the New Game! button using
 * anonymous classes. It uses the model to validate inputs and evaluate
 * good guesses and update the status label accordingly. There is also
 * a helper to handle the end of the game, using a dialog box to prompt
 * the user to play again or quit.
 */
public class HangpersonController {
    private HangpersonModel model;
    private HangpersonView view;

    /**
     * Constructor to set model and create view and add button listeners
     *
     * @param model a hangman game model
     */
    public HangpersonController(HangpersonModel model) {
        this.model = model;
        this.view = new HangpersonView(this);
        setupGame();
        addListeners();
    }

    /**
     * Updates the view of the JFrame for the beginning of the game
     */
    private void setupGame() {
        updateView();
        view.checkGuessLabel.setText(" ");
    }

    /**
     * Adds action listeners to the buttons
     */
    private void addListeners() {
        view.guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                char guess = model.getValidGuess(view.guessField.getText());
                boolean gameOver;

                handleGuess(guess);
                updateView();

                if (model.isOver) {
                    gameOver(model.isWon);
                }
            }

            /**
             * Handles updating view depending on the user input
             * @param guess a character from the getValidGuess function where
             *              guess is a 0 or 1 if guess is invalid or a valid
             *              character
             */
            private void handleGuess(char guess) {
                boolean correct;

                if (guess == '0') {
                    view.checkGuessLabel.setText("ERROR: please enter a single alphabetic character");
                } else if (guess == '1') {
                    view.checkGuessLabel.setText("You cannot guess " + view.guessField.getText());
                } else {
                    correct = model.evaluateGuess(guess);
                    if (correct) {
                        view.checkGuessLabel.setText("Nice! " + guess + " is in the word");
                    } else {
                        view.checkGuessLabel.setText("Too bad. " + guess + " is not in the word");
                    }
                }
            }

            /**
             * Runs when the game is over and prompts user to start another game or
             * quit
             * @param isWon true if user won game, false otherwise
             */
            private void gameOver(boolean isWon) {
                String message;
                String[] options = {"Play again", "Quit"};
                int choice;

                if (isWon) {
                    message = "You win! The word was " + new String(model.targetWord);
                } else {
                    message = "You lose! The word was " + new String(model.targetWord);
                }

                choice = JOptionPane.showOptionDialog(view,
                        message,
                        "Game Over",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]);
                if (choice == JOptionPane.NO_OPTION) {
                    System.exit(0);
                } else {
                    playAgain();
                }
            }
        });

        view.newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                playAgain();
            }
        });
    }

    /**
     * Starts a new game if there are words to play else prompts user
     * to replay old words or quit
     */
    private void playAgain() {
        if (model.hasWords) {
            model.setupGame();
            setupGame();
        } else {
            String[] options = {"Replay words", "Quit"};
            int choice = JOptionPane.showOptionDialog(view,
                    "Oops, there are no new words left to start a new game. " +
                            "Would you like to play again with the used words or quit?",
                    "Out of Words",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.ERROR_MESSAGE,
                    null,
                    options,
                    options[0]);
            if (choice == JOptionPane.NO_OPTION) {
                System.exit(0);
            } else {
                model = new HangpersonModel();
                setupGame();
            }
        }
    }

    /**
     * Updates view labels and text fields
     */
    private void updateView() {
        view.wordProgress.setText(makePrettyString(model.guessedWord));
        view.availableLetters.setText(makePrettyString(model.availableLetters));
        view.remainingGuesses.setText(model.numberOfWrongGuesses + " incorrect guesses remaining");
        view.guessField.setText("");
    }

    /**
     * Formats a new string for display adding spaces between each character for readability
     * @param word a char array to display
     * @return a string composed of char[] word with spaces b/w each character
     */
    private String makePrettyString(char[] word) {
        StringBuilder builder = new StringBuilder();
        for (char ch : word) {
            builder.append(ch );
            builder.append(' ');
        }

        return builder.toString();
    }
}
