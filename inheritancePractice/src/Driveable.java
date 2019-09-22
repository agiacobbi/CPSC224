/**
 * Implementation of Driveable Interface
 * CPSC 224-01, Fall 2019
 * Programming Assignment #3
 * No sources to cite.
 *
 * @author Alex Giacobbi
 * @version v1.0 9/19/19
 */

/**
 * Interface Driveable describes objects that can drive. There is one
 * method, Drive(int), that will drive any driveable object the specified
 * distance
 */
public interface Driveable {
    /**
     * A method to drive a driveable object a specified distance
     *
     * @param milesDriven an int specifying number of miles driven
     */
    void drive(int milesDriven);
}
