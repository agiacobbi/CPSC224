/**
 * Implementation of Programmer Class
 * CPSC 224-01, Fall 2019
 * Programming Assignment #3
 * No sources to cite.
 *
 * @author Alex Giacobbi
 * @version v1.0 9/19/19
 */

import java.text.DecimalFormat;

/**
 * Class Programmer is a subclass of Employee. It implements one additional
 * field, hasBusPass that indicates whether a programmer is provided with a
 * bus pass. It also contains DVC and EVC as well as getter and setter for
 * hasBusPass. Programmer also overrides the toString function to include
 * Programmer-specific information. Also, an implementation of reportSalary()
 * is implemented to report a programmer's salary and bus pass status
 */
public class Programmer extends Employee {
    boolean hasBusPass;

    /**
     * DVC for a Programmer - sets bus pass to false and
     * increases salary by +$20000.00 from base salary
     */
    public Programmer() {
        this.hasBusPass = false;
        super.salary += 20000;
    }

    /**
     * EVC for a Programmer - sets name to specified name
     * and bus pass value to true or false as specified,
     * increases salary by +$20000.00 from base salary
     *
     * @param name a string containing a programmer's name
     * @param hasBusPass a boolean true if programmer gets a bus
     *                   pass, false otherwise
     */
    public Programmer(String name, boolean hasBusPass) {
        super(name);
        this.hasBusPass = hasBusPass;
        super.salary += 20000;
    }

    /**
     * Gets bus pass status for a user
     *
     * @return a boolean true if programmer has a bus pass,
     * false otherwise
     */
    public boolean isHasBusPass() {
        return hasBusPass;
    }

    /**
     * Sets bus pass status to specified value
     *
     * @param hasBusPass a boolean true if programmer has a
     *                   bus pass, false otherwise
     */
    public void setHasBusPass(boolean hasBusPass) {
        this.hasBusPass = hasBusPass;
    }

    /**
     * Formats a string to contain readable information about a programmer
     *
     * @return a string representation of a programmer
     */
    @Override
    public String toString() {
        return super.toString() +
                "Position: Programmer\n" +
                "Bus pass: " + hasBusPass + '\n';
    }

    /**
     * Prints the salary and bus pass status for a programmer
     */
    @Override
    protected void reportSalary() {
        DecimalFormat dollar = new DecimalFormat("#.00");
        String bus = "";
        if (!hasBusPass) {
            bus += "do not ";
        }
        System.out.println("I'm a programmer. I make $" + dollar.format(salary) +
                " and I " + bus + "get a bus pass.");
    }
}
