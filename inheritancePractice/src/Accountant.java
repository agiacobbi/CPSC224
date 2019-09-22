/**
 * Implementation of Accountant Class
 * CPSC 224-01, Fall 2019
 * Programming Assignment #3
 * No sources to cite.
 *
 * @author Alex Giacobbi
 * @version v1.0 9/19/19
 */

import java.text.DecimalFormat;

/**
 * Class Accountant is a subclass of Employee. It
 * implements one additional field, parking allowance that
 * specifies the balance of an accountant's parking allowance.
 * This class also contains getter and setter for parkingAllowance
 * as well as a DVC and EVC. Also includes an override of
 * the toString function to include accountant-specific information
 * and implements reportSalary() method to report salary and stock
 * earned.
 */
public class Accountant extends Employee{
    private double parkingAllowance;

    /**
     * DVC for Accountant - sets parking allowance to $0.00
     */
    public Accountant() {
        this.parkingAllowance = 0.00;
    }

    /**
     * EVC for Accountant - sets name to specified name and
     * parking allowance to a specified value
     *
     * @param name a string containing an accountant's name
     * @param parkingAllowance a double specifying an accountant's parking allowance
     */
    public Accountant(String name, double parkingAllowance) {
        super(name);
        this.parkingAllowance = parkingAllowance;
    }

    /**
     * Gets an accountant's parking allowance for user
     *
     * @return a double containing parking allowance
     */
    public double getParkingAllowance() {
        return parkingAllowance;
    }

    /**
     * Sets the parking allowance for an accountant
     *
     * @param parkingAllowance a double to set the parking allowance value to
     */
    public void setParkingAllowance(double parkingAllowance) {
        this.parkingAllowance = parkingAllowance;
    }

    /**
     * Formats a string to contain readable information about an accountant
     *
     * @return a string representation of an accountant
     */
    @Override
    public String toString() {
        DecimalFormat dollar = new DecimalFormat("#.00");
        return super.toString() +
                "Position: Accountant\n" +
                "Parking allowance: $" + dollar.format(parkingAllowance) + '\n';
    }

    /**
     * Prints the salary and parking allowance for an accountant
     */
    @Override
    protected void reportSalary() {
        DecimalFormat dollar = new DecimalFormat("#.00");
        System.out.println("I'm an accountant. I make $" + dollar.format(salary) +
                " and I have " + dollar.format(parkingAllowance) + " of parking allowance.");
    }


}
