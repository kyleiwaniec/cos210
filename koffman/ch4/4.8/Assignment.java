import java.util.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Assignment{
	public String name;
	public String className;
	public int year;
	public int month;
	public int date;
	public DueDate dueDate = new DueDate();
	

	// constructors
	public Assignment(){};
	public Assignment(String name, String className, DueDate dueDate){
		this.name = name;
		this.className = className;
		this.dueDate = dueDate;
	}

	// public methds
	public String getName(){
		return name;
	};
	public String getClassName(){
		return className;
	};
	public DueDate getDueDate(){
		return dueDate;
	};
	
	
	public String setName(String name){
		return this.name = name;
	};
	public String setClassName(String className){
		return this.className = className;
	};
	public DueDate setDueDate(int year, int month, int date){
		return this.dueDate = dueDate.setDueDate(year, month, date);
	};


	public String toString(){
			String result = "\nAssignment: " + name +
							"\nClass: " + className +
							"\nDue Date: " + dueDate;
			return result;				
		}
}