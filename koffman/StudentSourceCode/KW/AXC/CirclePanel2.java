/*<listing chapter="C" number="3">*/
package KW.AXC;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/** A CirclePanel will contain a circle and a button.
 *  @author Koffman & Wolfgang
 **/
public class CirclePanel2 extends JPanel {

    // Data Fields
    /** The button object */
    JButton onOffButton;
    /** The Circle object */
    MyCircle2 theCircle;

    // Constructor
    /** Construct a CirclePanel object. */
    public CirclePanel2(int size) {
        setLayout(new BorderLayout());
        theCircle = new MyCircle2(size);
        onOffButton = new JButton("On / Off");
        onOffButton.addActionListener(new ToggleState());
        add(theCircle, BorderLayout.CENTER);
        add(onOffButton, BorderLayout.SOUTH);
    }

    // Inner Class
    /** The action listener for the button. */
    private class ToggleState
            implements ActionListener {

        @Override
// Insert solution to programming exercise 2, section 2, chapter C here
    }
}
/*</listing>*/
