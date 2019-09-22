/**
 * A "Test Driver" for Car class
 * CPSC 224-01, Fall 2019
 * Programming Assignment #3
 * No sources to cite.
 *
 * @author Alex Giacobbi
 * @version v1.0 9/19/19
 */

import java.util.ArrayList;
import java.util.Random;

/**
 * This is a tester class to test Car class and the interfaces
 * it implements. There is a main method and some helpers to initialize
 * a list of cars in random order, sort the cars based on year, make,
 * model, and mileage, and then drive the cars and re-sort.
 *
 * Extra Credit:
 * Cars are stored in an ArrayList which is part of the Collections interface.
 * Cars are also sorted using an insertion sort that I wrote. (sorry for
 * choosing a slow sort)
 */
public class TestDriver {
    public static void main(String[] args) {
        ArrayList<Car> cars = new ArrayList<Car>(10);

        cars.add(new Car("Toyota", "Prius", 2005, 30000));
        cars.add(new Car("DeLorean", "DMC-12", 1985, 100000));
        cars.add(new Car("Honda", "Odyssey", 2008, 130000));
        cars.add(new Car("GMC", "Yukon", 2011, 80000));
        cars.add(new Car("Hyundai", "Sonata", 2009, 120000));
        cars.add(new Car("Hyundai", "Santa Fe", 2019, 10000));
        cars.add(new Car("Toyota", "Highlander", 2016, 55000));
        cars.add(new Car("Hyundai", "Sonata", 2009, 120000));
        cars.add(new Car("Toyota", "Corolla", 2002, 140000));
        cars.add(new Car("Honda", "Pilot", 2005, 8000));


        printCars(cars);
        driveCars(cars);
        printCars(cars);

    }

    /**
     * A method to display a list of cars in order
     *
     * @param cars an ArrayList of Cars
     */
    private static void printCars(ArrayList<Car> cars) {
        insertionSort(cars);
        for (Car car : cars) {
            System.out.println(car);
        }
    }

    /**
     * A method to randomly select and drive cars in an ArrayList
     * random distances
     *
     * @param cars an ArrayList of Cars
     */
    private static void driveCars(ArrayList<Car> cars) {
        Random rand = new Random();

        System.out.println("\nDriving some cars...\n");

        for (int i = 0; i < 30; i++) {
            cars.get(rand.nextInt(10)).drive(rand.nextInt(30000));
        }
    }

    /**
     * An implementation of insertion sort to sort an ArrayList of cars
     *
     * @param cars an ArrayList of Car objects to be sorted
     */
    private static void insertionSort(ArrayList<Car> cars) {
        for (int i = 1, j; i < cars.size(); i++) {
            Car temp = cars.get(i);
            for (j = i; j > 0 && temp.compareTo(cars.get(j - 1)) < 0; j--) {
                cars.set(j, cars.get(j - 1));
            }
            cars.set(j, temp);
        }
    }
}
