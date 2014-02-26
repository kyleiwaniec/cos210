/**
 * Towers of Hanoi
 * @author Paul Wolfgang
 */
package KW.CH05.towersofhanoi;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.JFrame;

/**
 * A Board  object represents the playing board for the Towers
 * of Hanoi.  It contains the three rings.  It also plays the game.
 * The game is played in an independently executing thread.
 */
public class Board implements Runnable {

    // Data Fields
    /** The time to sleep between moves. */
    private long sleepDur = 0;
    /** The array of pegs. */
    private Peg[] thePegs = new Peg[3];
    /** The total number of rings. */
    private int numRings;
    /** Set true when the Board is constructed, set
    false when play begins. */
    private boolean initialized;
    /** Set true when the game is paused. */
    private boolean paused;
    /** Set true when the game is in single step mode */
    private boolean singleStep;
    /** Set true when the game it to be stopped */
    private boolean stopped = false;
    /** The parent frame. */
    private JFrame frame;
    /** The width of the board */
    private int width;
    /** The height of the board less the base thickness */
    private int height;
    /** The thickness of the base */
    private static final int BASE_THICK = 30;
    /** The sequence number for this instance */
    private static int seqNo = 0;

    // Constructor
    /**
     * Construct a Board.
     * @param totalWidth The total width of the window
     * @param totalHeight The total height of the window
     * @param numRings The number of rings
     * @param parent The parent frame in which the <code>Board</code>
     *        is displayed
     */
    public Board(int totalWidth, int totalHeight, int numRings,
            JFrame parent) {
        ++seqNo;
        width = totalWidth;
        height = totalHeight - BASE_THICK;
        frame = parent;
        this.numRings = numRings;
        initialized = true;
// Insert solution to programming project 5, chapter -1 here

    /**
     * Set the time between moves
     * @param s The time between moves in miliseconds
     */
    public synchronized void setSleepDur(long s) {
        sleepDur = s;
        singleStep = false;
    }

    /**
     * Draw the <code>Board</code>
     * @param g2 The graphics environment
     */
    public synchronized void draw(Graphics2D g2) {
        thePegs[0].draw(g2);
        thePegs[1].draw(g2);
        thePegs[2].draw(g2);
        Rectangle base = new Rectangle(0, height, width, BASE_THICK);
        g2.setPaint(Color.black);
        g2.fill(base);
    }

    /**
     * Play the game. Calls the internal Play function with
     * the initial values.  This function executes in an
     * independent thread.
     */
    @Override
    public void run() {
        Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
        initialized = false;
        stopped = false;
        try {
            // Play the game
            play(0, 2, 1, numRings);
        } catch (IsStoppedException ex) {
            // Execution has been stopped.
            // Exit method, stopping the thread
        }
    }

    /**
     * Initialized is set true when the board is constructe
     * and set false when play starts.  Used by the play
     * menu event handlers to determine if the board needs
     * to be reinitialized prior to starting.
     * @return The state of the initialized flag.
     */
    public boolean isInitialized() {
        return initialized;
    }

    /** Stop the execution of the game. */
    public synchronized void setStopped() {
        stopped = true;
    }

    /**
     * Pause the execution of the game and enter single step
     * mode.
     */
    public synchronized void pause() {
        paused = true;
        singleStep = true;
    }

    /** Advance one step. */
    public synchronized void step() {
        paused = false;
        notify();
    }

    /** Continue the game after it is paused. */
    public synchronized void release() {
        paused = false;
        singleStep = false;
        notify();
    }

    /** Set the single step mode. */
    public synchronized void setSingleStep() {
        singleStep = true;
    }

    /**
     * Recursive play algorithm.
     * If there is one ring, move it from from to to.
     * Otherwise, move n-1 rings from from to aux,
     * move one ring from from to to, and then move
     * n-1 rings from aux to to.
     * @param n    The number of rings.
     * @param from The source peg.
     * @param to   The destination peg.
     * @param aux  The remaining peg.
     * @throws IsStoppedException thrown by makeMove
     */
    private void play(int from, int to, int aux, int n)
            throws IsStoppedException {
        if (n == 1) {
            makeMove(from, to);
        } else {
            play(from, aux, to, n - 1);
            makeMove(from, to);
            play(aux, to, from, n - 1);
        }
    }

    /**
     * Move one ring from the source peg to the destination peg.
     * Prior to making the move, a test of the stopped flag is made.
     * If it is set, the IsStoppedException is thrown. This causes a
     * return to the run method.  If the program is in the play slow
     * mode, sleepDur will be non-zero.  It then checks to see if 
     * pause is set.  If pause is set it calls wait.
     * @param from The index of the source peg.
     * @param to The index of the destination peg.  
     * @throws IsStoppedException when stopped flag is set
     */
    private synchronized void makeMove(int from, int to)
            throws IsStoppedException {
        if (stopped) {
            throw new IsStoppedException();
        }
        try {
            if (sleepDur != 0) {
                wait(sleepDur);
            }
            while (paused) {
                wait();
            }
        } catch (InterruptedException e) {
            // Ignore
        }
        if (singleStep) {
            paused = true;
        }
        Ring aRing = thePegs[from].pop();
        thePegs[to].push(aRing);
        frame.repaint();
    }

    /** 
     * Exception class used to stop execution.
     */
    private class IsStoppedException extends Exception {
    }

    /**
     * Create a string representation of this object.
     *  @return A string representation of this object.
     */
    @Override
    public String toString() {
        return super.toString() + "seqNo: " + seqNo + "  numRings" + numRings;
    }
}
