/**
 * Implementation of Lawyer Class
 * CPSC 224-01, Fall 2019
 * Programming Assignment #3
 * No sources to cite.
 *
 * @author Alex Giacobbi
 * @version v1.0 9/19/19
 */

import java.text.DecimalFormat;

/**
 * Class Layer is subclass of Employee. It implements one additional field,
 * stockEarned that specifies the number of shares a lawyer has earned as a bonus.
 * This class also contains getter and setter for stockEarned as well as a DVC and
 * EVC. Also includes an override of the toString function to include lawyer-specific
 * information and implements reportSalary() method to report salary and stock earned.
 */
public class Lawyer extends Employee {
    private int stockEarned;

    /**
     * DVC for Lawyer - sets stock bonus to 0 and increases salary
     * by +$30000.00 from base salary
     */
    public Lawyer() {
        this.stockEarned = 0;
        super.salary += 30000;
    }

    /**
     * EVC for Lawyer - sets name and stock bonus to specified values,
     * increases salary by +$30000.00 from base salary
     *
     * @param name a string containing a lawyer's name
     * @param stockEarned an int specifying number of stock options earned
     */
    public Lawyer(String name, int stockEarned) {
        super(name);
        this.stockEarned = stockEarned;
        super.salary += 30000.00;
    }

    /**
     * Gets stock earned value for the user
     *
     * @return an int number of stock shares earned
     */
    public int getStockEarned() {
        return stockEarned;
    }

    /**
     * Sets stock earned to specified value
     *
     * @param stockEarned an int to set how many shares a lawyer has earned
     */
    public void setStockEarned(int stockEarned) {
        this.stockEarned = stockEarned;
    }

    /**
     * Formats a string to contain readable information about a lawyer
     *
     * @return a string form of lawyer object
     */
    @Override
    public String toString() {
        return super.toString() +
                "Position: Lawyer\n" +
                "Shares of stock: " + stockEarned + '\n';
    }

    /**
     * Prints the salary and stock shares earned for a lawyer
     */
    @Override
    protected void reportSalary() {
        DecimalFormat dSalary = new DecimalFormat("#.00");

        System.out.println("I'm a lawyer. I make $" + dSalary.format(salary) +
                " and I have " + stockEarned + " shares.");
    }
}
