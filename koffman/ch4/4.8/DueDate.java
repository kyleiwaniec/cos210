import java.util.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class DueDate{

	public int year;
	public int month;
	public int date;

	// constructors
	public DueDate(){};
	public DueDate(int year, int month, int date){
		this.year = year;
		this.month = month;
		this.date = date;
	}

	public int getYear(){
		return year;
	}
	public int getMonth(){
		return month;
	}
	public int getDate(){
		return date;
	}
	public int setYear(int year){
		return this.year = year;
	}
	public int setMonth(int month){
		return this.month = month;
	}
	public int setDate(int date){
		return this.date = date;
	}

	public DueDate setDueDate(int year, int month, int date){
		this.year = setYear(year);
		this.month = setMonth(month);
		this.date = setDate(date);
		return this;
	}

	public String toString(){
			String result = month +"/"+ date +"/"+ year;
			return result;				
		}
}