import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameController implements ActionListener{
    private TicTacToeBoard model;
    private TicTacToeView view;
    private String playerOneName;
    private String playerTwoName;
    private GameStats playerXStats;
    private GameStats playerOStats;
    private boolean isWon;
    private int turn;
    private char[] players;
    private char winner;

    public GameController() {
        view = new TicTacToeView("Tic Tac Toe");
        players = new char[]{'X', 'O'};
        playerXStats = new GameStats('X');
        playerOStats = new GameStats('O');

        getPlayerNames();
        setActionListeners();
        setupNewGame();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        JButton button = (JButton) actionEvent.getSource();
        int buttonIndex = view.cells.indexOf(button);
        // gets coordinates of button based on position in array of buttons
        Coordinates move = new Coordinates(buttonIndex / 3, buttonIndex % 3);

        if (model.isValidMove(move)) {
            model.makeMove(move, players[turn]);
            button.setText("" + players[turn]);
            isWon = model.isWinner(players[turn]);
            turn = (turn + 1) % 2;
            setTurnMessage();
        } else {
            String playerName = turn == 0 ? playerOneName : playerTwoName;
            view.turnMessage.setText("Invalid move. Try again, " + playerName);
        }

        if (isWon) {
            winner = players[turn];
            endGame();
        } else if (model.isFull()) {
            endGame();
        }
    }

    private void endGame() {
        String message;
        if (winner == 'X') {
            message = playerOneName + " won! Play again?";
            view.turnMessage.setText(message);
            playerXStats.addWin();
            playerOStats.addLoss();
        } else if (winner == 'O') {
            message = playerTwoName + " won! Play again?";
            view.turnMessage.setText(message);
            playerOStats.addWin();
            playerXStats.addLoss();
        } else {
            message = "Scratch game. Play again?";
            view.turnMessage.setText(message);
            playerXStats.addScratch();
            playerOStats.addScratch();
        }
        updateStats();
        setButtons(false);

        String[] options = {"Play Again", "Quit"};
        int choice = JOptionPane.showOptionDialog(
                view,
                message,
                "Game Over",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );
        if (choice == JOptionPane.NO_OPTION) {
            System.exit(0);
        } else {
            setupNewGame();
        }
    }

    private void setActionListeners() {
        view.resetNameAndStats.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int choice = JOptionPane.showConfirmDialog(
                        view,
                        "This will end the game and set Win/Loss stats to 0. Are you sure?",
                        "Reset Names and Stats",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );

                if (choice == JOptionPane.YES_OPTION) {
                    playerXStats = new GameStats('X');
                    playerOStats = new GameStats('O');
                    getPlayerNames();
                    setupNewGame();
                    updateStats();
                }
            }
        });

        for (JButton cell : view.cells) {
            cell.addActionListener(this);
        }
    }

    private void getPlayerNames() {
        playerOneName = JOptionPane.showInputDialog("Please enter player 1's name");
        playerTwoName = JOptionPane.showInputDialog("Please enter player 1's name");

        if (playerOneName == "" || playerOneName == null) {
            playerOneName = "Player 1";
        }
        if (playerTwoName == "" || playerTwoName == null) {
            playerTwoName = "Player 2";
        }
        if (playerOneName.equals(playerTwoName)) {
            playerOneName += "(1)";
            playerTwoName += "(2)";
        }

        view.p1Name.setText(playerOneName);
        view.p2Name.setText(playerTwoName);
    }

    private void setupNewGame() {
        // clears board, enables buttons, initializes new model
        model = new TicTacToeBoard(3);
        isWon = false;
        winner = '-';
        turn = (int)(Math.random() * 2);

        clearGrid();
        setButtons(true);
        setTurnMessage();
    }

    private void updateStats() {
        view.p1Win.setText(playerXStats.getWins() + "");
        view.p1Loss.setText(playerXStats.getLosses() + "");
        view.p2Win.setText(playerOStats.getWins() + "");
        view.p2Loss.setText(playerOStats.getLosses() + "");
    }

    private void setTurnMessage() {
        if (turn == 0) {
            view.turnMessage.setText(playerOneName + "'s turn");
        } else {
            view.turnMessage.setText(playerTwoName + "'s turn");
        }
    }

    private void setButtons(boolean enabled) {
        for (JButton cell : view.cells) {
            cell.setEnabled(enabled);
        }
    }

    private void clearGrid() {
        for (JButton cell : view.cells) {
            cell.setText(" ");
        }
    }
}
