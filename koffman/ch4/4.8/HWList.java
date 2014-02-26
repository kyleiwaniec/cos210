import java.util.*;

public class HWList{
	public static LinkedList<Assignment> asList = new LinkedList();

	public static void completeAssignment(Assignment ass){
		asList.remove(ass);
	}

	public static void completeAssignment(String assName){

		Iterator<Assignment> iter = asList.iterator();

		while(iter.hasNext()){
			Assignment ass = iter.next();
			if(ass.name.equals(assName)){
				asList.remove(ass);
				break;
			}
		}
	}
	public static void getEarliestDue(){
		ListIterator<Assignment> iter = asList.listIterator();

		while(iter.hasNext()){
			Assignment prev = iter.next();
			Assignment next = iter.next();

			System.out.println("next: "+next.toString()+"\nprev: "+prev.toString()+"\n");

			iter.previous();


			/**********************
			//Find maximum (largest) value in array using recursion  
			public static int getMax(int[] numbers, int a, int n){  
				return a >= numbers.length ? n : Math.max(n,getMax(numbers,a+1,numbers[a] > n ? numbers[a] : n));  
			}
			***********************/
			/**********************
			public static int findLargest(int[] numbers){  
			    int largest = numbers[0];  
			    for(int i = 1; i < numbers.length; i++){  
			        if(numbers[i] > largest){  
			            largest = numbers[i];  
			        }  
			    }  
			    return largest;
			}
			/***********************/

		}
	}

	public static void getAllInOrder(){
		System.out.println(asList.toString());
	}

	public static void addAssignment(String assName, String className, int year, int month, int date){

		Assignment ass = new Assignment();
			ass.setName(assName);
			ass.setClassName(className);
			ass.setDueDate(year, month, date);

		asList.add(ass);
	}

	public static void main(String[] args){
		
		addAssignment("Lesson1", "Math", 2013, 5, 11);
		addAssignment("Lesson2", "Math", 2013, 4, 12);
		addAssignment("Lesson3", "Math", 2012, 5, 13);
		addAssignment("Lesson4", "Math", 2013, 6, 19);

		//completeAssignment("Lesson2");

		//getAllInOrder();

		getEarliestDue();
	}
}