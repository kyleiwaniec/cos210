package KW.CH07;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Class to build an index.
 *  @author Koffman and Wolfgang
 **/
public class IndexGenerator {

    // Data Fields
    /** Tree for storing the index. */
    private SortedMap<String, ArrayList<Integer>> index;

    // Methods
    public IndexGenerator() {
        index = new TreeMap<String, ArrayList<Integer>>();
    }

    /*<listing chapter="7" number="2">*/
    /**
     * Reads each word in data file and stores it in a Map
     * along with an ArrayList of line numbers (a value).
     * @pre  index in an empty Map.
     * @post Lowercase form of each word with line
     *       numbers is stored in index.
     * @param scan A Scanner object
     */
    public void buildIndex(Scanner scan) {
        int lineNum = 0; // Line number
        // Keep reading lines until done.
        while (scan.hasNextLine()) {
            lineNum++;

            // Extract each token and store it in index.
            String token;
            while ((token = scan.findInLine("[\\p{L}\\p{N}']+")) != null) {
                token = token.toLowerCase();
                ArrayList<Integer> lines = index.get(token);
                if (lines == null) {
                    lines = new ArrayList<Integer>();
                }
                lines.add(lineNum);
                index.put(token, lines);  //Store new list
            }
            scan.nextLine();
        }
    }
    /*</listing>*/

// Insert solution to programming exercise 4, section 5, chapter 7 here

    public static void main(String args[]) {
        if (args.length < 1) {
            System.err.println("Usage: IndexGenerator <file>");
            System.exit(1);
        }
        try {
            Scanner scan = new Scanner(new FileReader(args[0]));
            IndexGenerator indexGenerator = new IndexGenerator();
            indexGenerator.buildIndex(scan);
            indexGenerator.showIndex();
        } catch (IOException ioex) {
            System.err.println("Error building index");
            ioex.printStackTrace();
            System.exit(1);
        }
    }
}
