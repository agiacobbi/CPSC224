/**
 * This program implements a model of the game hangman
 * CPSC 224-01, Fall 2019
 * Programming Assignment #4
 * No sources to cite.
 *
 * @author Alex Giacobbi
 * @version v1.0 10/13/19
 */

import java.io.Externalizable;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * Class HangpersonModel handles the game logic for the word guessing game
 * hangman. It stores the number of incorrect guesses remaining, a target
 * word, the user's progress withing a word, available letters to guess, and
 * boolean values to indicate if the game is over, if the game is won, and if
 * the program has more words to play. There are methods to validate a guess taken
 * from a textField in the view, to evaluate a validated guess as well as helpers
 * to load the words from a file and set up a new game, set the word for a new
 * game, and check if a character is in a given char array
 */
public class HangpersonModel {
    protected ArrayList<String> words;
    protected int numberOfWrongGuesses;
    protected char[] availableLetters;
    protected char[] targetWord;
    protected char[] guessedWord;
    protected boolean isOver;
    protected boolean isWon;
    protected boolean hasWords;

    /**
     * Loads words from file and sets up game
     */
    public HangpersonModel() {
        words = getWordsFromFile();
        hasWords = words.size() > 0;
        setupGame();
    }

    /**
     * Sets up a new hangman game: sets new words from list of
     * available words, sets number of wrong guesses to 7, resets
     * available letters and guessed word, updates, hasWords
     */
    protected void setupGame() {
        numberOfWrongGuesses = 7;
        availableLetters = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
                'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        targetWord = setWord(words).toCharArray();
        guessedWord = new char[targetWord.length];
        Arrays.fill(guessedWord, '-');
        hasWords = words.size() > 0;
        isOver = false;
        isWon = false;
    }

    /**
     * Reads list of game words from a file named "words.txt" saved on the users desktop
     *
     * @return an ArrayList of Strings containing words for gameplay
     */
    private static ArrayList<String> getWordsFromFile() {
        ArrayList<String> wordList = new ArrayList<String>();
        Scanner wordScanner;

        try {
            wordScanner = new Scanner(new File(System.getProperty("user.home") + "/Desktop/words.txt"));
            while (wordScanner.hasNextLine()) {
                wordList.add(wordScanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return wordList;
    }

    /**
     * Randomly selects a word for new hangman game and removes it from list of available words
     *
     * @param availableWords ArrayList of Strings containing words that have not been played yet
     * @return a String containing the word selected for the game
     */
    private static String setWord(ArrayList<String> availableWords) {
        Random indexGenerator = new Random();
        int index = indexGenerator.nextInt(availableWords.size());
        String word = availableWords.get(index);

        //removes word from ArrayList so it cannot be played again
        availableWords.remove(index);

        return word;
    }

    /**
     * Checks if a specific character is contained in an array of characters
     *
     * @param target character being searched for
     * @param set an array of characters to be searched
     * @return true if the array contains the specified character, else false
     */
    private static boolean isIn(char target, char[] set) {
        for (char test : set) {
            if (test == target) {
                return true;
            }
        }
        return false;
    }

    /**
     * Takes in a guess from the user and ensures that it is an available character, updates list of valid characters
     *
     * @param userGuess a string guess to validate
     * @return 0 if guess is too long, 1 if guess is a letter that has been guessed, or
     * a character that has not been guessed yet
     */
    protected char getValidGuess(String userGuess) {
        if (userGuess.length() != 1) {
            return '0';
        } else if (!(isIn(userGuess.charAt(0), availableLetters)) || userGuess.charAt(0) == '*') {
            return '1';
        } else {
            return userGuess.charAt(0);
        }
    }

    /**
     * Checks if guess is in the target word, if so, updates guessed characters, if not,
     * decrements number of wrong guesses remaining
     *
     * @param userGuess a valid character to be evaluated
     * @return true if guess is correct or false if incorrect
     */
    protected boolean evaluateGuess(char userGuess){
        boolean correct;

        availableLetters[new String(availableLetters).indexOf(userGuess)] = '*';

        //checks if the guessed letter is in the target word
        if(isIn(userGuess, targetWord)) {
            correct = true;
            //reveals where correct letters are located
            for(int i = 0; i < targetWord.length; i++) {
                if(targetWord[i] == userGuess) {
                    guessedWord[i] = userGuess;
                }
            }
            if (Arrays.equals(guessedWord, targetWord)) {
                isOver = true;
                isWon = true;
            }
        } else {
            correct = false;
            numberOfWrongGuesses--;
            if (numberOfWrongGuesses == 0) {
                isOver = true;
            }
        }
        return correct;
    }
}