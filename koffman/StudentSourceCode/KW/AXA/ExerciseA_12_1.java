/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package KW.AXA;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Paul Wolfgang
 */
public class ExerciseA_12_1 {

    public static int[] readArray(String fileName) throws FileNotFoundException {
        try {
            BufferedReader in = new BufferedReader(new FileReader(fileName));
            List<Integer> list = new ArrayList<Integer>();
            Scanner scan = new Scanner(in);
            while (scan.hasNextInt()) {
                list.add(scan.nextInt());
            }
            int[] result = new int[list.size()];
            for (int i = 0; i < list.size(); i++) {
                result[i] = list.get(i);
            }
            return result;
        } catch (FileNotFoundException ex) {
            throw new FileNotFoundException("File " + fileName + " not found");
        } catch (IOException ioex) {
            ioex.printStackTrace();
            System.exit(1);
        }
        return null;
    }

    public static void main(String[] args) {
        try {
            int[] data = readArray(args[0]);
            for (int i : data) System.out.println(i);
// Insert solution to programming exercise 1, section 12, chapter A here
    }

}
