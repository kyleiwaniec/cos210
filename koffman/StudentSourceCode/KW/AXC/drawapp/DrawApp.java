/*<listing chapter="C" number="16">*/
package KW.AXC.drawapp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;

/** DrawApp.java
 *  Simple Drawing Application. This program draws selected figures.
 *  The list of available figures is provided as a command line
 *  argument. The figure to be drawn is selected via a menu or a
 *  toolbar button.  The interior and border color is selected from
 *  a menu choice.
 *  @author Koffman and Wolfgang
 */
public class DrawApp extends JFrame {

    // DataFields
    /** Currently selected figure kind to be drawn. */
    private Drawable selectedFig = null;
    /** Currently selected border color */
    private Color currentBorderColor = Color.BLACK;
    /** Currently selected interior color */
    private Color currentInteriorColor = Color.WHITE;
    /** Array of figure prototypes */
    private Drawable[] theFigKinds;

    // Constructor
    /** Construct a DrawApp object. 
     *  @param args An array of strings containing Drawable class
     *  names. */
    private DrawApp(String args[]) {
        // Set title
        super("Draw App");
        // Set frame size
        setSize(750, 750);
        // Set default close operation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Load the figure kinds
        loadFigKinds(args);
        // Create drawingToolMenu
        JMenu drawingToolMenu = createDrawingToolMenu();
        // Create drawingToolToolBar
        JToolBar drawingToolToolBar = createDrawingToolToolBar();
        selectedFig = theFigKinds[0];
        // Create the color choice menu
        JMenu colorMenu = createColorMenu();
        // Create a menu bar to hold the menu
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(colorMenu);
        menuBar.add(drawingToolMenu);
        // Add the menu bar to the frame
        setJMenuBar(menuBar);
        // Add the toolbar to the frame
        getContentPane().add(drawingToolToolBar, BorderLayout.EAST);
        // Add the DrawPanel to the frame
        getContentPane().add(new DrawPanel(this),
                BorderLayout.CENTER);
    }

// Insert solution to programming exercise 2, section 6, chapter C here

    /** Method to create the drawing tool menu. This method loops 
     *  through the FigKinds array and creates a menu item for each
     *  entry.  It then creates  an action listener that will select
     *  this figure when the actionPerformed method is called.
     *  @return A reference to the JMenu object that is created.
     */
    private JMenu createDrawingToolMenu() {
        JMenu drawingToolMenu = new JMenu("Drawing Tool");
        for (int i = 0; i < theFigKinds.length; i++) {
            // create a menu item for this figure kind
            JMenuItem item = new JMenuItem(theFigKinds[i].getName());
            // set the action listener
            item.addActionListener(new ToolSelector(theFigKinds[i]));
            // add the item to the menu
            drawingToolMenu.add(item);
        }
        return drawingToolMenu;
    }

    /** Method to create the tool bar. This method loops through the
     *  FigKinds array and creates a button for each entry, using that
     *  figure kind to construct an icon.  It then creates an action
     *  listener that will select this figure when the actionPerformed
     *  method is called.
     *  @return A reference to the JToolBar object that is created.
     */
    JToolBar createDrawingToolToolBar() {
        JToolBar drawingToolToolBar = new JToolBar(JToolBar.VERTICAL);
        for (int i = 0; i < theFigKinds.length; i++) {
            JButton aButton = new JButton(theFigKinds[i].getIcon(16));
            aButton.addActionListener(new ToolSelector(theFigKinds[i]));
            drawingToolToolBar.add(aButton);
        }
        return drawingToolToolBar;
    }

    /** Method to create the color menu. The menu will contain two 
     *  commands: SetInteriorColor and SetBorderColor. For each command
     *  a JColorChooser dialog is displayed, and the users choice is
     *  saved in the appropriate attribute for each of the figures in
     *  the FigKinds array.
     *  @return A reference to the JMenu object that is created.
     */
    private JMenu createColorMenu() {
        JMenu colorMenu = new JMenu("Colors");
        JMenuItem setInteriorColor =
                new JMenuItem("Set Interior Color");
        setInteriorColor.addActionListener(new SetInteriorColor());
        colorMenu.add(setInteriorColor);
        JMenuItem setBorderColor = new JMenuItem("Set Border Color");
        setBorderColor.addActionListener(new SetBorderColor());
        colorMenu.add(setBorderColor);
        return colorMenu;
    }

    /** Access the currently selected figure.
     *  @return The currently selected figure.
     */
    public Drawable getSelectedFig() {
        return selectedFig;
    }

    /** Main method. This method instantiates a DrawApp object
     *  and shows it.
     *  @param args An array of Drawable class names.
     */
    public static void main(String args[]) {
        DrawApp drawApp = new DrawApp(args);
        drawApp.setVisible(true);
    }

    // Inner Classes
    /** Common ActionListener for the figures menu and the
     *  figures toolbar.
     */
    private class ToolSelector implements ActionListener {
        // Data Fields

        /** Figure prototype to be selected when this action
         *  listener is fired. */
        private Drawable desiredFig;

        // Constructor
        /** Construct a ToolSelector object with the specified
         *  figure prototype.
         *  @param desiredFig The figure prototype to be selected.
         */
        public ToolSelector(Drawable desiredFig) {
            this.desiredFig = desiredFig;
        }

        // Methods
        /** Set the selected figure to the desired figure when
         *  the action is performed.
         *  @param e Not used.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            selectedFig = desiredFig;
        }
    }

    /** ActionListener class for the SetInteriorColor command */
    private class SetInteriorColor implements ActionListener {

        /** Action Performed is to display the color chooser
         *  dialog and then set the inerior color of each of the
         *  figure prototypes. The display is then repainted
         *  @param e ActionEvent -- Not Used
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            currentInteriorColor =
                    JColorChooser.showDialog(
                    DrawApp.this,
                    "Select Interior Color",
                    currentInteriorColor);
            for (int i = 0; i < theFigKinds.length; i++) {
                theFigKinds[i].setInteriorColor(currentInteriorColor);
            }
            repaint();
        }
    }

// Insert solution to programming exercise 3, section 6, chapter C here

}
/*</listing>*/
