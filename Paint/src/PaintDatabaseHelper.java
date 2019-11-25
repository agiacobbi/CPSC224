/**
 * This is the database helper for the paint application
 * CPSC 224-01, Fall 2019
 * Programming Assignment #6
 * No sources to cite
 *
 * @author Alex Giacobbi
 * @version v1.0 11/21/19
 */

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This is a database helper for our program. This class establishes a connection to a database
 * where we can store our shapes so that they will persist between runs of the app. There are
 * functions to create the table, fill it, clear it and read from it.
 */
public class PaintDatabaseHelper {
    private static final String DATABASE_NAME = "shapes.db";
    private static final String CONNECTION_URL = "jdbc:sqlite:databases/" + DATABASE_NAME;
    private static final String TABLE_NAME = "painting";
    private static final String ID = "id";
    private static final String SHAPE_TYPE = "shape_type";
    private static final String IS_FILLED = "is_filled";
    private static final String STROKE_SIZE = "stroke_size";
    private static final String RED = "red";
    private static final String GREEN = "green";
    private static final String BLUE = "blue";
    private static final String X_START = "x_init";
    private static final String Y_START = "y_init";
    private static final String X_END = "x_end";
    private static final String Y_END = "y_end";

    private Connection connection;

    /**
     * This is the constructor for the database helper. Establishes a connection
     * and creates the table if one does not exist
     */
    public PaintDatabaseHelper() {
        getConnection();
        createPaintTable();
    }

    /**
     * Creates the table to store shapes if one does not exist.
     */
    private void createPaintTable() {
        String sqlCreate = "CREATE TABLE " + TABLE_NAME + "(" +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                SHAPE_TYPE + " INTEGER, " +
                IS_FILLED + " INTEGER, " +
                STROKE_SIZE + " INTEGER, " +
                RED + " INTEGER, " +
                GREEN + " INTEGER, " +
                BLUE + " INTEGER, " +
                X_START + " INTEGER, " +
                Y_START + " INTEGER, " +
                X_END + " INTEGER, " +
                Y_END + " INTEGER)";
        System.out.println(sqlCreate);

        if (connection != null && !tableExists()) {
            try {
                Statement statement = connection.createStatement();
                statement.execute(sqlCreate);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Inserts a shape into the shapes table
     *
     * @param shape an object of type MyShape
     */
    public void insertShape(MyShape shape) {
        int type = shape.getType();
        int isFilled = shape.isFilled() ? 1 : 0;
        int stroke = shape.getStrokeSize();
        int red = shape.getColor().getRed();
        int green = shape.getColor().getGreen();
        int blue = shape.getColor().getBlue();
        int xst = shape.getStart().x;
        int yst = shape.getStart().y;
        int xen = shape.getEnd().x;
        int yen = shape.getEnd().y;
        String sqlInsert = "INSERT INTO " + TABLE_NAME + " VALUES(null, " +
                type + ", " +
                isFilled + ", " +
                stroke + ", " +
                red + ", " +
                green + ", " +
                blue + ", " +
                xst + ", " +
                yst + ", " +
                xen + ", " +
                yen + ")";
        System.out.println(sqlInsert);

        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                statement.execute(sqlInsert);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * Gets a list of all shapes stored in the database
     *
     * @return a List of MyShapes
     */
    public List<MyShape> getAllShapes() {
        String sqlSelect = "SELECT * FROM " + TABLE_NAME;
        List<MyShape> shapes = new ArrayList<>();
        System.out.println(sqlSelect);
        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sqlSelect);
                while (resultSet.next()) {
                    System.out.println(resultSet.getInt("shape_type"));
                    int type = resultSet.getInt(SHAPE_TYPE);
                    boolean isFilled = resultSet.getInt(IS_FILLED) == 1;
                    int stroke = resultSet.getInt(STROKE_SIZE);
                    int red = resultSet.getInt(RED);
                    int green = resultSet.getInt(GREEN);
                    int blue = resultSet.getInt(BLUE);
                    int xst = resultSet.getInt(X_START);
                    int yst = resultSet.getInt(Y_START);
                    int xen = resultSet.getInt(X_END);
                    int yen = resultSet.getInt(Y_END);
                    Color color = new Color(red, green, blue);
                    Point start = new Point(xst, yst);
                    Point end = new Point(xen, yen);

                    MyShape newShape = new MyShape(isFilled, stroke, color, start, end, type);
                    shapes.add(newShape);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return shapes;
    }

    /**
     * Clears all shapes from the database
     */
    public void deleteAllShapes() {
        String sqlDelete = "DELETE FROM " + TABLE_NAME;
        System.out.println(sqlDelete);

        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                statement.execute(sqlDelete);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Establishes a connection to the database
     */
    private void getConnection() {
        try {
            connection = DriverManager.getConnection(CONNECTION_URL);
            System.out.println("Successfully connected to the database");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Closes the connection to the database
     */
    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Disconnected");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Indicates whether table shapes exists in the database
     *
     * @return true if shapes table has been created false otherwise
     */
    private boolean tableExists() {
        // http://www.java2s.com/Code/Java/Database-SQL-JDBC/Detectifatableexists.htm
        DatabaseMetaData md = null;
        boolean hasNext = false;
        try {
            md = connection.getMetaData();
            ResultSet rs = md.getTables(null, null, TABLE_NAME, null);
            hasNext = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hasNext;
    }

}
