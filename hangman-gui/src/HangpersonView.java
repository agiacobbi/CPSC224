/**
 * This program implements a GUI view for the game hangman
 * CPSC 224-01, Fall 2019
 * Programming Assignment #4
 * No sources to cite.
 *
 * @author Alex Giacobbi
 * @version v1.0 10/13/19
 */

import javax.swing.*;
import java.awt.*;

/**
 * Class HangpersonView extends class JFrame to create the GUI for
 * our hangman game. It has a constructor that sets up our content
 * panel with the appropriate labels, fields and buttons. There are
 * fields for each element.
 */
public class HangpersonView extends JFrame {
    private HangpersonController controller;
    protected JPanel contentPanel;
    protected JLabel wordProgress;
    protected JLabel availableLetters;
    protected JLabel remainingGuesses;
    protected JTextField guessField;
    protected JButton guessButton;
    protected JButton newGameButton;
    protected JLabel checkGuessLabel;

    /**
     * Constructor for the hangman GUI
     *
     * @param controller a controller for the game
     */
    public HangpersonView(HangpersonController controller) {
        super("Hangperson GUI");
        this.controller = controller;

        setVisible(true);
        setPreferredSize(new Dimension(400, 200));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setupUI();
        pack();
    }

    /**
     * Adds all components to the view
     */
    private void setupUI() {
        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        wordProgress = new JLabel();
        wordProgress.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(wordProgress);

        availableLetters = new JLabel();
        availableLetters.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(availableLetters);

        remainingGuesses = new JLabel();
        remainingGuesses.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(remainingGuesses);

        guessField = new JTextField();
        guessField.setAlignmentX(Component.CENTER_ALIGNMENT);
        guessField.setHorizontalAlignment(JTextField.CENTER);
        guessField.setMaximumSize(new Dimension(300, 30));
        contentPanel.add(guessField);

        guessButton = new JButton("Guess!");
        guessField.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(guessButton);

        newGameButton = new JButton("New Game!");
        newGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(newGameButton);

        checkGuessLabel = new JLabel();
        checkGuessLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(checkGuessLabel);

        getContentPane().add(contentPanel);
    }
}
