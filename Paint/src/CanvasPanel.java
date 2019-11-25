/**
 * This is the custom JPanel that will be our canvas. It is a subclass
 * of JPanel. paintComponent() is overridden to paint all shapes on the
 * screen
 * CPSC 224-01, Fall 2019
 * Programming Assignment #6
 * No sources to cite
 *
 * @author Alex Giacobbi
 * @version v1.0 11/21/19
 */

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This is a subclass of JPanel that will allow us to paint shape on
 * a canvas, like MS Paint. There is a List of MyShapes that stores
 * the shapes to be drawn on the canvas. There is also a function
 * to save the image on the panel as a jpeg to the desktop.
 */
public class CanvasPanel extends JPanel {
    List<MyShape> shapes;

    /**
     * Constructor of JPanel. Sets the background color, initializes the List
     * data structure and sets the cursor appearance to crosshair style when
     * cursor is in JPanel
     */
    public CanvasPanel() {
        setBackground(Color.WHITE);
        shapes = new ArrayList<>();
        setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
    }

    /**
     * A method to save the image created as a JPEG to the user's desktop.
     * Adapted from some code by AdoPi at https://gist.github.com/AdoPi/11032315
     *
     * @param name the name of the file to save to
     */
    public void saveImage(String name) {
        String path = System.getProperty("user.home") + "/Desktop/" + name;
        String type = "jpg";
        BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = image.createGraphics();
        paint(g2);

        try {
            ImageIO.write(image, type, new File(path + "." + type));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Paints the shapes in the list to the canvas
     *
     * @param g a Graphics object
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        for (MyShape toDraw : shapes) {
            if (toDraw != null) {
                Shape draw = toDraw.getShape();
                g2.setColor(toDraw.getColor());
                g2.setStroke(new BasicStroke(toDraw.getStrokeSize()));
                g2.draw(draw);
                if (toDraw.isFilled()) {
                    g2.fill(draw);
                }
            }
        }
    }
}
