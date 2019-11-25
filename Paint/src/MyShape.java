/**
 * This is a shape wrapper class for a Shape. It contains a shape
 * and some data about the shape so we can draw it accurately
 * CPSC 224-01, Fall 2019
 * Programming Assignment #6
 * No sources to cite
 *
 * @author Alex Giacobbi
 * @version v1.0 11/21/19
 */

import java.awt.*;
import java.awt.geom.*;

/**
 * A wrapper for shape that contains other information required to draw the
 * shape. Fields are db friendly. Contains getters to access all fields to
 * write to db.
 */
public class MyShape implements Shape {
    static final int LINE = 0;
    static final int RECTANGLE = 1;
    static final int ELLIPSE = 2;

    private boolean filled;
    private int strokeSize;
    private Color color;
    private Point start;
    private Point end;
    private int type;
    private Shape shape;

    /**
     * Constructor for a new shape. Takes in values for shape and start and end points.
     * Makes a new shape of indicated type at location indicated by
     * start and end points
     *
     * @param filled boolean indicates if shape is to be filled
     * @param strokeSize int stroke thickness
     * @param color Color of shape stroke and fill
     * @param start Point initial point of the shape
     * @param end Point end point of shape
     * @param type int type of shape (see class constants)
     */
    public MyShape(boolean filled, int strokeSize, Color color, Point start, Point end, int type) {
        this.filled = filled;
        this.strokeSize = strokeSize;
        this.color = color;
        this.start = start;
        this.end = end;
        this.type = type;

        switch (this.type) {
            case LINE:
                shape = new Line2D.Double(start, end); 
                break;
            case RECTANGLE:
                shape = new Rectangle2D.Double(start.x, start.y, Math.abs(start.x - end.x), Math.abs(start.y - end.y));
                break;
            case ELLIPSE:
                shape = new Ellipse2D.Double(start.x, start.y, Math.abs(start.x - end.x), Math.abs(start.y - end.y));
                break;
            default:
                shape = null;
        }
    }

    /**
     * Tells user if shape is to be filled
     *
     * @return true if shape is filled false otherwise
     */
    public boolean isFilled() {
        return filled;
    }

    /**
     * Gets the stroke size of the shape
     *
     * @return int stroke of the shape
     */
    public int getStrokeSize() {
        return strokeSize;
    }

    /**
     * Gets the color of the shape
     *
     * @return returns the color of the shape
     */
    public Color getColor() {
        return color;
    }

    /**
     * Gets the shape object
     *
     * @return a 2D shape object
     */
    public Shape getShape() {
        return shape;
    }

    /**
     * Gets the initial point of the shape
     *
     * @return Point the initial point
     */
    public Point getStart() {
        return start;
    }

    /**
     * Gets the end point of the shape
     *
     * @return Point the end point
     */
    public Point getEnd() {
        return end;
    }

    /**
     * Gets the type of the shape
     *
     * @return int the type of the shape
     */
    public int getType() {
        return type;
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }

    @Override
    public Rectangle2D getBounds2D() {
        return null;
    }

    @Override
    public boolean contains(double v, double v1) {
        return false;
    }

    @Override
    public boolean contains(Point2D point2D) {
        return false;
    }

    @Override
    public boolean intersects(double v, double v1, double v2, double v3) {
        return false;
    }

    @Override
    public boolean intersects(Rectangle2D rectangle2D) {
        return false;
    }

    @Override
    public boolean contains(double v, double v1, double v2, double v3) {
        return false;
    }

    @Override
    public boolean contains(Rectangle2D rectangle2D) {
        return false;
    }

    @Override
    public PathIterator getPathIterator(AffineTransform affineTransform) {
        return null;
    }

    @Override
    public PathIterator getPathIterator(AffineTransform affineTransform, double v) {
        return null;
    }
}
