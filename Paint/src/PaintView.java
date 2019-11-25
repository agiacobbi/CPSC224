/**
 * This the GUI for a paint application
 * CPSC 224-01, Fall 2019
 * Programming Assignment #6
 * No sources to cite
 *
 * @author Alex Giacobbi
 * @version v1.0 11/21/19
 */

import javax.swing.*;
import java.awt.*;


/**
 * This is the view for a Paint clone. It contains a tools panel at the top of the
 * frame where users can select actions, type of shape to draw, whether to fill the
 * shape, stroke weight, and color. Quit, Save JPG, Undo, and Clear canvas options
 * use JButtons. There is a slider for stroke weight, radio buttons to select the
 * shape, and a checkbox to indicate whether or not to fill the shape. There is a
 * CanvasPanel where users can draw their shapes and a mouse position indicator at
 * the bottom of the screen.
 */
public class PaintView extends JFrame {
    JButton clearButton;
    JButton quitButton;
    JButton undoButton;
    JButton saveButton;

    JSlider weightSlider;
    JPanel colorBox;
    JButton colorChooser;

    JRadioButton lineButton;
    JRadioButton rectangleButton;
    JRadioButton ellipseButton;
    JCheckBox filledCheckBox;

    CanvasPanel canvasPanel;

    JLabel mousePosition;

    /**
     * The constructor for the view. Sets up the tools panel, canvas
     * and mouse position indicator.
     */
    public PaintView() {
        super("Paint Lite");

        setVisible(true);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setPreferredSize(new Dimension(1200, 700));

        setupUI();
        pack();
    }

    /**
     * A helper function for the constructor, sets up the layout of the
     * UI, organizing all functions into separate, labeled JPanels
     */
    private void setupUI() {
        JPanel mainPanel = (JPanel) getContentPane();
        JPanel toolsPanel = new JPanel();
        canvasPanel = new CanvasPanel();


        // setup the canvas options group
        JPanel canvasOptions = new JPanel();
        canvasOptions.setBorder(BorderFactory.createTitledBorder("Canvas Options"));
        clearButton = new JButton("Clear");
        quitButton = new JButton("Quit");
        undoButton = new JButton("Undo");
        saveButton = new JButton("Save JPEG");

        canvasOptions.add(undoButton);
        canvasOptions.add(clearButton);
        canvasOptions.add(saveButton);
        canvasOptions.add(quitButton);

        // setup the stroke options group
        JPanel strokeOptions = new JPanel();
        JLabel strokeLabel = new JLabel("Stroke Size");
        strokeOptions.add(strokeLabel);
        final int SLIDER_MIN = 5;
        final int SLIDER_MAX = 25;
        weightSlider = new JSlider(JSlider.HORIZONTAL, SLIDER_MIN, SLIDER_MAX, SLIDER_MIN);
        weightSlider.setMajorTickSpacing(5);
        weightSlider.setPaintTicks(true);
        weightSlider.setPaintLabels(true);
        strokeOptions.add(weightSlider);

        strokeOptions.setBorder(BorderFactory.createTitledBorder("Stroke Options"));
        colorChooser = new JButton("Change Color");
        colorBox = new JPanel();
        colorBox.add(colorChooser);
        colorBox.setBackground(Color.BLACK);
        strokeOptions.add(colorBox);


        // setup the shape options group
        JPanel shapeOptions = new JPanel();
        shapeOptions.setBorder(BorderFactory.createTitledBorder("Shape Options"));
        lineButton = new JRadioButton("Line");
        shapeOptions.add(lineButton);
        rectangleButton = new JRadioButton("Rectangle");
        shapeOptions.add(rectangleButton);
        ellipseButton = new JRadioButton("Ellipse");
        shapeOptions.add(ellipseButton);
        filledCheckBox = new JCheckBox("Filled");
        shapeOptions.add(filledCheckBox);

        ButtonGroup drawModes = new ButtonGroup();
        drawModes.add(lineButton);
        drawModes.add(rectangleButton);
        drawModes.add(ellipseButton);

        toolsPanel.add(canvasOptions);
        toolsPanel.add(strokeOptions);
        toolsPanel.add(shapeOptions);


        mainPanel.add(toolsPanel, BorderLayout.NORTH);
        mainPanel.add(canvasPanel, BorderLayout.CENTER);

        mousePosition = new JLabel("(0, 0)");
        mainPanel.add(mousePosition, BorderLayout.SOUTH);
    }
}
