package KW.CH03;

import java.util.Stack;
import java.util.Scanner;

public class Exercise_3_2 {

// Insert solution to programming exercise 1, section 2, chapter 3 here

    public static void main(String[] args) {
        String line = null;
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println("Enter line to be reversed");
            if (in.hasNextLine()) {
                line = in.nextLine();
                System.out.println(reverseWords(line));
            } else {
                break;
            }
        }
    }
}
