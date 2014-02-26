package KW.CH07;

import java.util.NavigableSet;
import java.util.TreeSet;
import java.util.Random;

public class Exercise_7_7_1 {

    public static void main(String[] args) {

        Random rand = new Random();
        NavigableSet<Integer> s = new TreeSet<Integer>();
        for (int i = 0; i < 10; i++) {
            s.add(rand.nextInt(100));
        }
// Insert solution to programming exercise 1, section 7, chapter 7 here
    }
}
