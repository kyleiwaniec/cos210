import java.util.*;
public class Sieve {
    
    private static int upperLimit = 1000;
    private static boolean[] flags;

    public Sieve() {
    }

    public static void main(String[] args) {
        initialize(args);
        findPrimes();
        displayPrimes();
    }
    
    private static void initialize(String[] args) {
        if (args.length > 0) {
            upperLimit = Integer.parseInt(args[0]);
        }
        flags = new boolean[upperLimit + 1];
        for (int position = 0; position <= upperLimit; position++) {
            flags[position] = true;
        }
        
    }

    private static void findPrimes() {
        for (int position = 2; position <= Math.sqrt(upperLimit); position++) {
            if (flags[position]) {
                int multiple = position * 2;
                while (multiple <= upperLimit) {
                    flags[multiple] = false;
                    multiple += position;
                }                
            }
        }
    }

    private static void displayPrimes() {
        for (int position = 2; position <= upperLimit; position++) { 
            if (flags[position]) { 
                System.out.print(position + ", "); 
            } 
        }     
    }

}