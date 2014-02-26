import javax.swing.*;
import java.util.*;
/** Class to maintain a queue of customers */
public class MaintainQueue {
	// Data field
	private Queue<String> customers;
	// Constructor
	public MaintainQueue(){
		customers = new LinkedList<String>();
	}

	// Public methods
	/** Performs the oprations selected on queue customers
		pre: customers has beed created
		post: customers is modified based on user selections
	*/
	public void processCustomers(){
		int choiceNum = 0;
		String[] choices = {"add","peek","remove","size","position","view list","quit"};
		// Perform all opertions selected by user.
		while(choiceNum < choices.length -1){
			// select the next operation
			choiceNum = JOptionPane.showOptionDialog(null,
				"Select an operation on customer Queue",
				"Queue menu", JOptionPane.DEFAULT_OPTION,
				JOptionPane.QUESTION_MESSAGE, null,
				choices, choices[0]);
		
			// Process the current choice
			try{
				String name;
				switch (choiceNum){
					case 0:
						name = JOptionPane.showInputDialog("Enter customer name");
						customers.offer(name);
						JOptionPane.showMessageDialog(null, "Customer "+name+" added to the Queue");
						break;
					case 1:
						JOptionPane.showMessageDialog(null, "Customer "+customers.element()+" is next in the Queue");
						break;
					case 2:
						JOptionPane.showMessageDialog(null, "Customer "+customers.remove()+" removed from the Queue");
						break;
					case 3:
						JOptionPane.showMessageDialog(null, "The size of the queue is "+customers.size());
						break;
					case 4:
						name = JOptionPane.showInputDialog("Enter customer name");
						int countAhead = 0;
						for (String nextName : customers){
							if(!nextName.equals(name)){
								countAhead ++;
							}else{
								JOptionPane.showMessageDialog(null, "The number fo customers ahead of "+name+" is "+countAhead);
								break;
							}
						}
						// Check whether customer was found
						if(countAhead == customers.size()){
							JOptionPane.showMessageDialog(null, name+" is not in the Queue");
						}
						break;
					case 5:
						JOptionPane.showMessageDialog(null, "List: \n"+this.toString());

					break;	
					case 6:
						JOptionPane.showMessageDialog(null, "Leaving customer Queue. \n"
							+"Number of customers in Queue is "+customers.size());
						break;	
					default:
						JOptionPane.showMessageDialog(null, "Invalid selection");
						break;		
				}
			}catch(NoSuchElementException e){
				JOptionPane.showMessageDialog(null, "The queue is empty", "", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	public String toString(){
		return this.customers.toString();
	}
}