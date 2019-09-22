/**
 * Implementation of Car Class
 * CPSC 224-01, Fall 2019
 * Programming Assignment #3
 * No sources to cite.
 *
 * @author Alex Giacobbi
 * @version v1.0 9/19/19
 */


/**
 * Class Car implements Comparable and Driveable interfaces. There are
 * 4 fields for a car: make, model, year, and odometerReading. There are
 * EVC and DVC constructors as well as getter and setter methods for all
 * fields. This class also overrides Object's toString() function for
 * formatting a string form a car, the compareTo() function for comparing
 * cars and drive() function for driving a car.
 */
public class Car implements Comparable<Car>, Driveable {
    private String make;
    private String model;
    private int year;
    private int odometerReading;

    /**
     * DVC for Car - sets all fields to default values
     */
    public Car() {
        this.make = "EMPTY MAKE";
        this.model = "EMPTY MODEL";
        this.year = -1;
        this.odometerReading = -1;
    }

    /**
     * EVC for Car - sets all fields to specified values
     *
     * @param make a string containing the make of a car (Ex: Honda)
     * @param model a string containing the model of a car (Ex: Civic)
     * @param year an int containing the car's model year
     * @param odometerReading an int containing the odometer reading for a car
     */
    public Car(String make, String model, int year, int odometerReading) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.odometerReading = odometerReading;
    }

    /**
     * Gets the make of a car for the user
     *
     * @return a string containing the make of a car
     */
    public String getMake() {
        return make;
    }

    /**
     * Sets the make of a car to specified value
     *
     * @param make a string containing the make of a car
     */
    public void setMake(String make) {
        this.make = make;
    }

    /**
     * Gets the model of a car for the user
     *
     * @return a string containing the model of a car
     */
    public String getModel() {
        return model;
    }

    /**
     * Sets the model for a car to a specified value
     *
     * @param model a string containing the model of a car
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Gets the year of a car for the user
     *
     * @return an int containing the car's model year
     */
    public int getYear() {
        return year;
    }

    /**
     * Sets the year of a car for the user
     *
     * @param year an int to set the car's model year to
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Gets the odometer reading of a car for the user
     *
     * @return an int containing the odometer reading for a car
     */
    public int getOdometerReading() {
        return odometerReading;
    }

    /**
     * Sets the odometer reading for a car to a specified value
     *
     * @param odometerReading an int to set the odometer reading to
     */
    public void setOdometerReading(int odometerReading) {
        this.odometerReading = odometerReading;
    }

    /**
     * Formats a string to contain readable information about a car
     *
     * @return a string representation of a car
     */
    @Override
    public String toString() {
        return year + " " + make + " " + model + " with " + odometerReading + " miles";
    }

    /**
     * Drives car, updating odometer accordingly
     *
     * @param milesDriven an int specifying how far a car is driven/how
     *                    much to adjust the odometer
     */
    @Override
    public void drive(int milesDriven) {
        odometerReading += milesDriven;
    }

    /**
     * Compares two cars by year, make, model, then odometer reading
     *
     * @param car a Car to be compared to
     * @return an int containing the difference between 2 cars, positive (+)
     * if this is greater than car, negative (-) if this less than car, 0 if
     * cars are equal in all compared fields
     */
    @Override
    public int compareTo(Car car) {
        if (this.year != car.getYear()) {
            return this.year - car.getYear();
        }
        if (this.make.compareTo(car.getMake()) != 0) {
            return this.make.compareTo(car.getMake());
        }
        if (this.model.compareTo(car.getModel()) != 0) {
            return this.model.compareTo(car.getModel());
        }
        return (this.odometerReading - car.getOdometerReading());
    }
}
