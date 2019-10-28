import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

public class TicTacToeView extends JFrame {
    protected JLabel p1Name, p1Win, p1Loss, p2Name, p2Win, p2Loss;
    JLabel turnMessage;
    ArrayList<JButton> cells;
    JButton resetNameAndStats;

    public TicTacToeView(String title) throws HeadlessException {
        super(title);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(350, 500));

        setupUI();
        pack();
    }

    private void setupUI() {
        JPanel mainPanel = (JPanel) getContentPane();
        JPanel playerPanel = new JPanel();
        playerPanel.setLayout(new GridLayout(0, 2));
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));

        JPanel playerOne = new JPanel();
        playerOne.setLayout(new GridLayout(0, 2));
        playerOne.setBorder(BorderFactory.createTitledBorder("Player 1 (X)"));
        JLabel p1NameHeader = new JLabel("Name:");
        playerOne.add(p1NameHeader);
        p1Name = new JLabel(" ");
        playerOne.add(p1Name);
        JLabel p1WinsHeader = new JLabel("Wins:");
        playerOne.add(p1WinsHeader);
        p1Win = new JLabel("0");
        playerOne.add(p1Win);
        JLabel p1LossesHeader = new JLabel("Losses:");
        playerOne.add(p1LossesHeader);
        p1Loss = new JLabel("0");
        playerOne.add(p1Loss);
        playerPanel.add(playerOne);

        JPanel playerTwo = new JPanel();
        playerTwo.setLayout(new GridLayout(0, 2));
        playerTwo.setBorder(BorderFactory.createTitledBorder("Player 2 (O)"));
        JLabel p2NameHeader = new JLabel("Name:");
        playerTwo.add(p2NameHeader);
        p2Name = new JLabel(" ");
        playerTwo.add(p2Name);
        JLabel p2WinsHeader = new JLabel("Wins:");
        playerTwo.add(p2WinsHeader);
        p2Win = new JLabel("0");
        playerTwo.add(p2Win);
        JLabel p2LossesHeader = new JLabel("Losses:");
        playerTwo.add(p2LossesHeader);
        p2Loss = new JLabel("0");
        playerTwo.add(p2Loss);
        playerPanel.add(playerTwo);

        topPanel.add(playerPanel);

        turnMessage = new JLabel(" ");
        turnMessage.setHorizontalAlignment(JLabel.CENTER);
        turnMessage.setFont(new Font("Sans Serif", Font.PLAIN, 28));
        topPanel.add(turnMessage);

        mainPanel.add(topPanel, BorderLayout.NORTH);

        JPanel grid = new JPanel();
        grid.setLayout(new GridLayout(0, 3));
        cells = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            cells.add(new JButton(" "));
            cells.get(i).setFont(new Font("Sans Serif", Font.PLAIN, 40));
            grid.add(cells.get(i));
        }

        mainPanel.add(grid, BorderLayout.CENTER);

        resetNameAndStats = new JButton("Reset Names and Stats");
        mainPanel.add(resetNameAndStats, BorderLayout.SOUTH);
    }
}
