/*<listing chapter="8" number="11">*/
package KW.CH08;

/**
 * Driver program to test sorting methods.
 * @author Koffman and Wolfgang
 */
import javax.swing.JOptionPane;
import java.util.Random;
import java.util.Arrays;
import java.io.FileReader;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;

public class TestSort {

    /**
     * main method
     * @param args
     * arg[0] optionally contains the name of a data file
     */
    public static void main(String[] args) {
        Integer[] items = null;
        Integer[] copy = null;
        int size = 0;
// Insert solution to programming exercise 2, section 10, chapter 8 here
            size = Integer.parseInt(JOptionPane.showInputDialog("Enter Array size:"));
            items = new Integer[size]; // Array to sort.
            copy = new Integer[size]; // Copy of array.
            Random rInt = new Random(); // For random number generation

            // Fill the array and copy with random Integers.
            for (int i = 0; i < items.length; i++) {
                items[i] = rInt.nextInt();
                copy[i] = items[i];
            }
// Insert solution to programming exercise 2, section 10, chapter 8 here
        // Sort with utility method.
        long startTime = System.currentTimeMillis();
        Arrays.sort(items);
        System.out.println("Utility sort time is "
                + (System.currentTimeMillis()
                - startTime) + "ms");
        JOptionPane.showMessageDialog(null,
                "Utility sort successful (true/false): "
                + verify(items));

        // Reload array items from array copy.
        for (int i = 0; i < items.length; i++) {
            items[i] = copy[i];
        }

        // Sort with quicksort.
        startTime = System.currentTimeMillis();
        (new QuickSort3()).sort(items);
        System.out.println("QuickSort time is "
                + (System.currentTimeMillis()
                - startTime) + " ms");
        JOptionPane.showMessageDialog(null,
                "Your Sort successful (true/false): "
                + verify(items));

        dumpTable(items); // Display part of the array.
        System.exit(0);
    }

    /** Verifies that the elements in array test are
    in increasing order.
    @param test The array to verify
    @return true if the elements are in increasing order;
    false if any 2 elements are not in increasing order
     */
    private static boolean verify(Comparable[] test) {
        boolean ok = true;
        int i = 0;
        while (ok && i < test.length - 1) {
            ok = test[i].compareTo(test[i + 1]) <= 0;
            i++;
        }
        return ok;
    }

// Insert solution to programming exercise 1, section 10, chapter 8 here
}
/*</listing>*/
