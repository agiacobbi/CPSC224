/**
 * Implementation of Employee Class
 * CPSC 224-01, Fall 2019
 * Programming Assignment #3
 * No sources to cite.
 *
 * @author Alex Giacobbi
 * @version v1.0 9/19/19
 */

import java.text.DecimalFormat;

/**
 * Class Employee is abstract and specifies two fields for
 * a given employee: name and salary. This class contains methods
 * to get and set these fields as well as DVC and EVC and toString
 * to format info about an employee. There is also an abstract method
 * reportSalary() that will be used to print an employee's salary and
 * any relevant bonuses
 */
public abstract class Employee {
    protected String name;
    protected double salary;

    /**
     * DVC for Employee - sets salary to base salary
     */
    public Employee() {
        this.name = "EMPTY NAME";
        this.salary = 40000.00;
    }

    /**
     * EVC for Employee - sets name to specified name and salary to base salary
     * @param name a string containing the name of an employee
     */
    public Employee(String name) {
        this.name = name;
        this.salary = 40000.00;
    }

    /**
     * Gets employee name for user
     *
     * @return a string containing employee name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets employee name to specified name
     *
     * @param name a string to set name field for an employee
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets employee salary for user
     *
     * @return a double containing employee salary
     */
    public double getSalary() {
        return salary;
    }

    /**
     * Sets employee salary to a specified value
     *
     * @param salary a double to set salary field for an employee
     */
    public void setSalary(double salary) {
        this.salary = salary;
    }

    /**
     * Format employee as a string to easily read information about employee
     *
     * @return a string form of an employee object
     */
    @Override
    public String toString() {
        DecimalFormat dollar = new DecimalFormat("#.00");
        return "Name: " + name + '\n' +
                "Salary: $" + dollar.format(salary) + '\n';
    }

    /**
     * Method for an employee that prints employee's salary and any bonuses
     */
    protected abstract void reportSalary();
}
