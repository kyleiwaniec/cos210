package KW.CH10;

import java.util.Iterator;
import java.util.Deque;
import java.util.ArrayDeque;

/** Class to implement the depth-first search algorithm.
 *   @author Koffman and Wolfgang
 **/
public class DepthFirstSearchNR {

    // Data Fields
    /** A reference to the graph being searched. */
    private Graph graph;
    /** Array of parents in the depth-first search tree. */
    private int[] parent;
    /** Flag to indicate whether this vertex has been visited. */
    private boolean[] visited;
    /** The array that contains each vertex in discovery order. */
    private int[] discoveryOrder;
    /** The array that contains each vertex in finish order. */
    private int[] finishOrder;

    // Constructors
// Insert solution to programming exercise 2, section 4, chapter 10 here

    /** Get the finish order
     *  @return finish order
     */
    public int[] getFinishOrder() {
        return finishOrder;
    }

    /** Get the discovery  order
     *  @return discovery order
     */
    public int[] getDiscoveryOrder() {
        return discoveryOrder;
    }

    /** Get the parent
     *  @return the parent
     */
    public int[] getParent() {
        return parent;
    }
}
