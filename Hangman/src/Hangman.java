/**
 * This program implements a text version of the game hangman
 * CPSC 224-01, Fall 2019
 * Programming Assignment #1
 * No sources to cite.
 *
 * @author Alex Giacobbi
 * @version v1.0 8/29/19
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Hangman {
    /**
     * Continues game loop until program runs out of words or user doesn't want to play again
     *
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<String> availableWords = getWordsFromFile();
        boolean keepPlaying = true;

        while(keepPlaying) {
            playGame(availableWords);
            keepPlaying = endGame(availableWords);
        }

        System.out.println("Thanks for playing! Goodbye.");
    }

    /**
     * Reads list of game words from a file named "words.txt" saved on the users desktop
     *
     * @return an ArrayList of Strings containing words for gameplay
     * @throws FileNotFoundException
     */
    public static ArrayList<String> getWordsFromFile() throws FileNotFoundException {
        ArrayList<String> words = new ArrayList<String>();
        Scanner wordScanner = new Scanner(new File(System.getProperty("user.home") + "/Desktop/words.txt"));

        while (wordScanner.hasNextLine()) {
            words.add(wordScanner.nextLine());
        }

        return words;
    }

    /**
     * Randomly selects a word for new hangman game and removes it from list of available words
     *
     * @param availableWords ArrayList of Strings containing words that have not been played yet
     * @return a String containing the word selected for the game
     */
    public static String setWord(ArrayList<String> availableWords) {
        Random indexGenerator = new Random();
        int index = indexGenerator.nextInt(availableWords.size());
        String word = availableWords.get(index);

        //removes word from ArrayList so it cannot be played again
        availableWords.remove(index);

        return word;
    }

    /**
     * Prints important info for a game turn: letters guessed, available letters, and remaining incorrect guesses
     *
     * @param guessedLetters an array of correctly guessed characters; characters not guessed yet are represented as '-'
     * @param availableLetters an array of letters that have not been guessed yet
     * @param numberOfWrongGuesses the number of wrong guesses remaining before the game is lost
     */
    public static void printTurn(char[] guessedLetters, char[] availableLetters, int numberOfWrongGuesses) {
        System.out.println();
        System.out.println(guessedLetters);
        System.out.println("Available letters: " + new String(availableLetters));
        System.out.println(numberOfWrongGuesses + " incorrect guesses remaining");
    }

    /**
     * Checks if a specific character is contained in an array of characters
     * @param target character being searched for
     * @param set an array of characters to be searched
     * @return true if the arrau contains the specified character, else false
     */
    public static boolean isIn(char target, char[] set) {
        for(char test : set) {
            if(test == target) {
                return true;
            }
        }
        return false;
    }

    /**
     * Takes in a guess from the user and ensures that it is an available character, updates list of valid characters
     *
     * @param availableLetters a list of letters for the user to select a guess from
     * @return a character that has not been guessed yet
     */
    public static char getValidGuess(char[] availableLetters) {
        Scanner characterReader = new Scanner(System.in);
        char guess;

        System.out.println("Enter a guess: ");
        guess = characterReader.next().charAt(0);

        //gets input from user until a letter is selected from the available letters
        while(!(isIn(guess, availableLetters))) {
            System.out.println(guess + " is not available");
            System.out.println("Invalid guess. Select from available characters.");
            guess = characterReader.next().charAt(0);
        }

        //replaces chosen letter with a space
        availableLetters[new String(availableLetters).indexOf(guess)] = ' ';

        return guess;
    }

    /**
     * Checks if guess is in the target word, if so, congratulates user and updates guessed characters, if not,
     * decrements number of wrong guesses remaining
     * @param guess a character to be evaluated
     * @param numberOfWrongGuesses number or incorrect guesses remaining
     * @param targetWord word user is trying to guess
     * @param guessedWord letters guessed by user so far
     * @return updated number of incorrect guesses remaining
     */
    public static int evaluateGuess(char guess, int numberOfWrongGuesses, char[] targetWord, char[] guessedWord){
        //checks if the guessed letter is in the target word
        if(isIn(guess, targetWord)) {
            System.out.println("Nice guess. The word contains an " + guess);
            //reveals where correct letters are located
            for(int i = 0; i < targetWord.length; i++) {
                if(targetWord[i] == guess) {
                    guessedWord[i] = guess;
                }
            }
        } else {
            System.out.println("Incorrect guess. There is no " + guess + " in the word");
            numberOfWrongGuesses--;
        }

        return numberOfWrongGuesses;
    }

    /**
     * Runs the game loop from start to finish, sets the target word, and takes turns from user until word is guessed
     * correctly or user runs out of guesses
     *
     * @param availableWords a list of words available for gameplay
     */
    public static void playGame(ArrayList<String> availableWords) {
        //sets alphabet and number of wrong guesses at beginning of new game
        char[] availableLetters = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
                                   'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        int numberOfWrongGuesses = 7;

        char[] targetWord;
        char[] guessedWord;
        char guess;

        //picks a word for the game and
        targetWord = setWord(availableWords).toCharArray();
        guessedWord = new char[targetWord.length];
        Arrays.fill(guessedWord, '-');

        System.out.println("\nThe word to guess has " + targetWord.length + " letters");

        //follow game turn loop while guessed word is not equal to target and user still has more than one wrong guess
        while(!Arrays.equals(targetWord, guessedWord) && numberOfWrongGuesses > 0) {
            printTurn(guessedWord, availableLetters, numberOfWrongGuesses);
            guess = getValidGuess(availableLetters);
            numberOfWrongGuesses = evaluateGuess(guess, numberOfWrongGuesses, targetWord, guessedWord);
            if(targetWord == guessedWord) {
                System.out.println("You win!");
            }
        }

        //end of game
        if(Arrays.equals(targetWord, guessedWord)) {
            System.out.println("You guessed " + new String(targetWord) + ". You win!");
        } else {
            System.out.println("You ran out of guesses. You lose! The word was " + new String(targetWord));
        }
    }

    /**
     * After a game finishes, asks user if they would like to play again. If there are no words left to play with, ends
     * program
     *
     * @param availableWords a list of words available to play a new game with
     * @return if there's available words and user wants to play again returns true else returns false
     */
    public static boolean endGame(ArrayList<String> availableWords) {
        Scanner characterReader = new Scanner(System.in);
        char response;

        //if no more words, returns false to end program
        if(availableWords.isEmpty()) {
            System.out.println("Out of words!");
            return false;
        }

        //get valid response
        System.out.println("Would you like to play again? y/n");
        response = characterReader.next().charAt(0);

        while(response != 'y' && response != 'n') {
            System.out.println(response + " is not a valid response");
            System.out.println("Select y or n");
            response = characterReader.next().charAt(0);
        }

        if(response == 'y') {
            return true;
        } else {
            return false;
        }
    }
}