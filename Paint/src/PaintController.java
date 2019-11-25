/**
 * This is the controller for a paint clone. It creates listeners
 * for all GUI elements and connects app to a database
 * CPSC 224-01, Fall 2019
 * Programming Assignment #6
 * No sources to cite
 *
 * @author Alex Giacobbi
 * @version v1.0 11/21/19
 */
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


/**
 * This is the controller for the Paint app. It add mouse and click
 * listeners for all buttons and sliders and the canvas. This class
 * updates the view as the user generates actions. It also connects
 * to the database to get and write shapes that will allow the drawing
 * to persist across runs of the application.
 */
public class PaintController {
    private int strokeSize;
    private Color shapeColor;
    private int drawMode;
    private boolean filled;
    private CanvasPanel canvas;
    private PaintView view;

    private Point start;
    private Point end;


    /**
     * The constructor for the controller. Sets initial values for the shape,
     * color, filled, and stroke. Makes calls to helper functions to set up
     * click listeners and mouse listeners. Loads in a drawing stored in the
     * database if one exists.
     */
    public PaintController() {
        view = new PaintView();
        canvas = view.canvasPanel;

        strokeSize = 5;
        shapeColor = Color.BLACK;
        drawMode = MyShape.LINE;
        view.lineButton.setSelected(true);
        filled = false;
        view.filledCheckBox.setSelected(false);

        addMouseListeners();
        addButtonListeners();

        PaintDatabaseHelper helper = new PaintDatabaseHelper();
        canvas.shapes = helper.getAllShapes();
        canvas.repaint();
        helper.closeConnection();
    }

    /**
     * A helper function to add button listeners to all buttons in the controls
     * panel.
     */
    private void addButtonListeners() {
        view.undoButton.addActionListener(new ActionListener() {
            /**
             * Removes last shape from the drawing
             *
             * @param actionEvent a mouse click on the Undo button
             */
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (canvas.shapes.size() > 0) {
                    canvas.shapes.remove(canvas.shapes.size() - 1);
                    canvas.repaint();
                }
            }
        });

        view.clearButton.addActionListener(new ActionListener() {
            /**
             * Clears the shapes from the canvas
             *
             * @param actionEvent a click event on the Clear button
             */
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                canvas.shapes = new ArrayList<>();
                canvas.repaint();
            }
        });

        view.saveButton.addActionListener(new ActionListener() {
            /**
             * Prompts the user to enter a file name and saves the image on the
             * canvas to a jpeg on the Desktop
             *
             * @param actionEvent a click event on the Save JPG button
             */
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String fileName = JOptionPane.showInputDialog("Enter a filename: ");
                if (fileName != null) {
                    canvas.saveImage(fileName);
                }
            }
        });

        view.addWindowListener(new WindowAdapter() {
            /**
             * Runs when the user attempts to close the program using the 'X' button
             * at the top of the frame. Prompts the user with options to save and quit,
             * quit without saving, or cancel and return to the drawing.
             *
             * @param e a click on the close window button
             */
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                showQuitDialog();
            }
        });

        view.quitButton.addActionListener(new ActionListener() {
            /**
             * A listener for the Quit button. Prompts the user to save and/or quit
             *
             * @param actionEvent a click on the Quit button
             */
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                showQuitDialog();
            }
        });

        view.weightSlider.addChangeListener(new ChangeListener() {
            /**
             * A state changed listener for the slider. Updates the stroke value when
             * the user adjusts the stroke selector slider
             * @param changeEvent an adjustment to the stroke size slider
             */
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                JSlider slider = (JSlider) changeEvent.getSource();
                strokeSize  = slider.getValue();
                System.out.println("STROKE=" + strokeSize);
            }
        });

        view.colorChooser.addActionListener(new ActionListener() {
            /**
             * An action listener for the Choose Color button. Prompts the
             * user with a color chooser dialog and sets the new color if one
             * is selected
             *
             * @param actionEvent a click on the Choose Color button
             */
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Color newColor = JColorChooser.showDialog(
                        view,
                        "Choose Stroke Color",
                        view.colorBox.getBackground()
                );

                if (newColor != null) {
                    shapeColor = newColor;
                    view.colorBox.setBackground(newColor);
                    System.out.println("COLOR=" + shapeColor);
                }
            }
        });

        view.lineButton.addItemListener(new DrawModeListener());
        view.rectangleButton.addItemListener(new DrawModeListener());
        view.ellipseButton.addItemListener(new DrawModeListener());

        view.filledCheckBox.addItemListener(new ItemListener() {
            /**
             * A listener for the filled checkbox. Updates the value of isFilled
             * when the user clicks the checkbox
             *
             * @param itemEvent the checkbox is clicked
             */
            @Override
            public void itemStateChanged(ItemEvent itemEvent) {
                switch (itemEvent.getStateChange()) {
                    case ItemEvent.SELECTED:
                        filled = true;
                        break;
                    case ItemEvent.DESELECTED:
                        filled = false;
                        break;
                }
                System.out.println("FILLED=" + filled);
            }
        });
    }

    /**
     * Shows a dialog for when the user attempts to close the program. Prompts the
     * user to save, quit, or cancel. If user saves, writes all shapes on canvas
     * to the database. If quit, clears db and quits.
     */
    private void showQuitDialog() {
        String[] options = {"Save and close", "Close without saving", "Cancel"};
        int choice = JOptionPane.showOptionDialog(
                view,
                "Would you like to save your masterpiece?",
                "Closing",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);

        PaintDatabaseHelper helper = new PaintDatabaseHelper();
        switch (choice) {
            case JOptionPane.YES_OPTION:
                helper.deleteAllShapes();
                for (MyShape shape : canvas.shapes) {
                    helper.insertShape(shape);
                }
                helper.closeConnection();
                System.exit(0);
                return;
            case JOptionPane.NO_OPTION:
                helper.deleteAllShapes();
                helper.closeConnection();
                System.exit(0);
        }
    }

    /**
     * Adds mouse listeners to handle painting on the canvas. Adds a new
     * shape when the user presses the mouse. As mouse is dragged, creates a
     * new shape to update the user with how the shape appears on the screen.
     * When the user releases, the shape stays on the screen.
     */
    private void addMouseListeners() {
        canvas.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            /**
             * Creates a new shape and adds it to the list of shapes on the canvas
             * @param e a mouse press
             */
            @Override
            public void mousePressed(MouseEvent e) {
                start = e.getPoint();
                end = e.getPoint();
                MyShape newShape = new MyShape(filled, strokeSize, shapeColor, start, end, drawMode);
                canvas.shapes.add(newShape);
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        canvas.addMouseMotionListener(new MouseMotionListener() {
            /**
             * As mouse is dragged, removes top shape from the list of shapes
             * on the canvas and replaces it with a shape that reflects the current
             * mouse position. Repaints the canvas to show changes
             *
             * @param e mouse is dragged
             */
            @Override
            public void mouseDragged(MouseEvent e) {
                end = e.getPoint();
                MyShape shapePreview = new MyShape(filled, strokeSize, shapeColor, start, end, drawMode);
                canvas.shapes.remove(canvas.shapes.size() - 1);
                canvas.shapes.add(shapePreview);
                canvas.repaint();
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                String position = "(" + e.getX() + ", " + e.getY() + ")";
                view.mousePosition.setText(position);
            }
        });
    }


    class DrawModeListener implements ItemListener {
        /**
         * A state change listener for the radio button group shape selector.
         * Updates the drawMode accordingly.
         *
         * @param itemEvent radio button is clicked
         */
        @Override
        public void itemStateChanged(ItemEvent itemEvent) {
            JRadioButton button = (JRadioButton) itemEvent.getSource();
            if (itemEvent.getStateChange() == ItemEvent.SELECTED) {
                switch (button.getText()) {
                    case "Line":
                        drawMode = MyShape.LINE;
                        break;
                    case "Rectangle":
                        drawMode = MyShape.RECTANGLE;
                        break;
                    case "Ellipse":
                        drawMode = MyShape.ELLIPSE;
                        break;
                }
                System.out.println("DRAW_MODE=" + drawMode);
            }
        }
    }

    /**
     * Main function for program. Initializes controller
     *
     * @param args cmd args
     */
    public static void main(String[] args) {
        new PaintController();
    }
}
