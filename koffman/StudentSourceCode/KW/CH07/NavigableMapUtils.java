package KW.CH07;

import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.AbstractSet;
import java.util.AbstractMap;
import java.util.Iterator;

public class NavigableMapUtils {

    /*<listing chapter="7" number="14">*/
    /**
     * Returns the average of the numbers in its Map argument
     * @param valueMap The map whose values are averaged
     * @return The average of the map values
     */
    public static double computeAverage(Map<Integer, Double> valueMap) {
        int count = 0;
        double sum = 0;
        for (Map.Entry<Integer, Double> entry : valueMap.entrySet()) {
            sum += entry.getValue().doubleValue();
            count++;
        }
        return sum / count;
    }

    /**
     * Returns a list of the averages of nonoverlapping spans
     * of values in its NavigableMap argument.
     * @param delta The number of map values in each span
     * @return A List of average values for each span
     */
    public static List<Double> computeSpans(NavigableMap<Integer, Double> valueMap,
            int delta) {
        List<Double> result = new ArrayList<Double>();
        Integer min = valueMap.firstEntry().getKey();
        Integer max = valueMap.lastEntry().getKey();
        for (int index = min; index <= max; index += delta) {
            double average =
                    computeAverage(valueMap.subMap(index, true,
                    index + delta, false));
            result.add(average);
        }
        return result;
    }
    /*</listing>*/

// Insert solution to programming exercise 2, section 7, chapter 7 here

// Insert solution to programming exercise 3, section 7, chapter 7 here
}
