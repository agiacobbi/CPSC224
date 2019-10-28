/**
 * This program drives a GUI hangman game
 * CPSC 224-01, Fall 2019
 * Programming Assignment #4
 * No sources to cite.
 *
 * @author Alex Giacobbi
 * @version v1.0 10/13/19
 */

/**
 * A driver for our hangman game. Initializes the game model and
 * a controller to handle the interactions
 */
public class HangpersonTester {
    /**
     * Main method initializes the controller with a model
     *
     * @param args command line args
     */
    public static void main(String[] args) {
        HangpersonModel model = new HangpersonModel();
        HangpersonController controller = new HangpersonController(model);
    }
}
