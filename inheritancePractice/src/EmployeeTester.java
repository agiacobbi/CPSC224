/**
 * Test program for Employee (and all subclasses)
 * CPSC 224-01, Fall 2019
 * Programming Assignment #3
 * No sources to cite.
 *
 * @author Alex Giacobbi
 * @version v1.0 9/19/19
 */

import java.util.ArrayList;

/**
 * This is a tester class to test the Employee class and its
 * subclasses: Lawyer, Programmer, and Accountant. This programs stores a
 * collection of 8 employees and print out their information and reports
 * their salary demonstrating the use of the toString() overrides as well
 * as the abstract method of Employee reportSalary().
 *
 * Extra Credit:
 * Employees are stored in an ArrayList which is part of the Collections
 * interface
 */
public class EmployeeTester {
    public static void main(String[] args) {
        ArrayList<Employee> employees = new ArrayList<Employee>(8);

        employees.add(new Programmer("Alex Giacobbi", false));
        employees.add(new Programmer("Ima Nerd", true));
        employees.add(new Lawyer("Kenny Dewitt", 10));
        employees.add(new Lawyer("Dan D. Lyon", 0));
        employees.add(new Lawyer("Willie Makit", 100));
        employees.add(new Accountant("Hal E. Luya", 17.0));
        employees.add(new Accountant("Midas Well", 45.5));
        employees.add(new Accountant("Doll R. Bill", 2.5));


        for (Employee employee : employees) {
            System.out.println(employee);
            employee.reportSalary();
            System.out.println("---------------------");
        }
    }
}
