/*<listing chapter="C" number="1">*/
package KW.AXC;

import KW.PhoneDirectoryExample.PhoneDirectory;
import KW.PhoneDirectoryExample.PDUserInterface;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/** Class to display and modify a simple phone directory.
 *  @author Koffman & Wolfgang
 **/
public class PDButtonUI extends JFrame implements PDUserInterface {

    // Data Field
    private PhoneDirectory theDirectory;

    // Constructor
    public PDButtonUI() {
        // Set the title on the top of the frame.
        super("Phone Directory");
        // Define the window close action.
        addWindowListener(new WindowClosing());
        // Create a panel to hold the buttons.
        JPanel panel = new JPanel();
        // Create buttons and add them to the panel.
        // Add/Change Entry
        JButton addEntryButton = new JButton("Add/Change Entry");
        addEntryButton.addActionListener(new DoAddChangeEntry());
        panel.add(addEntryButton);
        // Look Up Entry
        JButton lookupEntryButton = new JButton("Look Up Entry");
        lookupEntryButton.addActionListener(new DoLookupEntry());
        panel.add(lookupEntryButton);
        // Remove Entry
        JButton removeEntryButton = new JButton("Remove Entry");
        removeEntryButton.addActionListener(new DoRemoveEntry());
        panel.add(removeEntryButton);
        // Save Directory
        JButton saveDirectoryButton = new JButton("Save Directory");
        saveDirectoryButton.addActionListener(new DoSave());
        panel.add(saveDirectoryButton);
        // Exit
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new DoSaveAndExit());
        panel.add(exitButton);
        // Put the panel into the frame.
        getContentPane().add(panel);
        // Size the frame to hold the panel.
        pack();
    }

    @Override
    public void processCommands(PhoneDirectory thePhoneDirectory) {
        theDirectory = thePhoneDirectory;
        setVisible(true);
    }

    // Action Event Listener Classes
    /*<example chapter="C" number="1">*/
    /** Class to respond to the Look Up Entry Button. */
    private class DoLookupEntry implements ActionListener {

        /** Method to look up an entry.
         *  pre:  The directory has been loaded with data.
         *  post: No changes made to the directory.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            // Request the name.
            String theName =
                    JOptionPane.showInputDialog("Enter name");
            if (theName == null) {
                return; // Dialog was canceled.
            }      // Look up the name.
            String theNumber = theDirectory.lookupEntry(theName);
            String message = null;
            if (theNumber != null) { // Name was found.
                message =
                        "The number for " + theName + " is " + theNumber;
            } else { // Name was not found.
                message =
                        theName + " is not listed in the directory";
            }
            // Display the result.
            JOptionPane.showMessageDialog(null, message);
        }
    }
    /*</example>*/

    // Insert listener classes for other buttons.
    /** Class respond to add or change an entry button. */
    private class DoAddChangeEntry implements ActionListener {

        /** Method to respond to the button pressed event
         *  Pre:  The directory exists and has been loaded with data.
         *  Post: A new name is added, or the value for the name is
         *        changed, and modified is set true.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            // Request the name
            String newName =
                    JOptionPane.showInputDialog("Enter name");
            if (newName == null) {
                return; // Dialog was canceled
            }            // Request the number
            String newNumber =
                    JOptionPane.showInputDialog("Enter number");
            if (newNumber == null) {
                return; // Dialog was canceled
            }            // Insert/change name-number
            String oldNumber =
                    theDirectory.addOrChangeEntry(newName, newNumber);
            String message = null;
            if (oldNumber == null) { // new entry
                message = newName + " was Added to the directory"
                        + "\nNew number: " + newNumber;
            } else { // changed entry
                message = newName + " was Changed in the directory"
                        + "\nOld number: " + oldNumber
                        + "\nNew number: " + newNumber;
            }
            // Display confirmation message
            JOptionPane.showMessageDialog(null, message);
        }
    }

    /** Class to respond to the Remove Entry button */
    private class DoRemoveEntry implements ActionListener {

        /** Method to remove an entry
         *  Pre:  The directory has been loaded with data.
         *  Post: The requested name is removed, modifed is set true
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            // Request the name
            String theName = JOptionPane.showInputDialog("Enter name");
            if (theName == null) {
                return; // Dialog was canceled
            }            // Remove the entry
            theDirectory.removeEntry(theName);
            // Display confirmaion message
            JOptionPane.showMessageDialog(null,
                    theName + " has been removed from the directory");
        }
    }

// Insert solution to programming exercise 3, section 1, chapter C here

    /** Class to respond to the Save and Close button. */
    private class DoSaveAndExit implements ActionListener {

        /** Method to save the directory to the data file and
         *  close the data file.
         *  Pre:  The directory has been loaded with data.
         *  Post: The current contents of the directory have been saved
         *  to the data file, and the data file is closed.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            theDirectory.save();
            System.exit(0);
        }
    }

    /** Class to respond to the WindowClosing event. */
    private class WindowClosing extends WindowAdapter {

        /** Method to save the directory to the data file and close
         *  the data file.
         *  pre:  The directory has been loaded with data.
         *  post: The current contents of the directory have been
         *  saved to the data file, and the data file is closed.
         */
        @Override
        public void windowClosing(WindowEvent e) {
            theDirectory.save();
            System.exit(0);
        }
    }
}
/*</listing>*/
