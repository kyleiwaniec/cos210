import java.util.ArrayList;
import java.util.List;

public class Replace{


/* using a for-loop I can target all occurances, whereas using indexOf only targets the first item found */
	// public static void replace(ArrayList<String> aList, String oldItem, String newItem){
	// 	for(int i = aList.size(); i > 0; i--){
	// 		if(aList.get(i-1) == oldItem){
	// 			aList.set(i-1, newItem);
	// 			//return; // stop after finding the first occurance (from the end of the list)
	// 		}
	// 	}
	// }

	// public static void delete(ArrayList<String> aList, String target){
	// 	for(int i = aList.size(); i > 0; i--){
	// 		if(aList.get(i-1) == target){
	// 			aList.remove(i-1);
	// 			// return; // stop after finding the first occurance (from the end of the list)
	// 		}
	// 	}
	// }

	public static void replace(ArrayList<String> aList, String oldItem, String newItem){
		aList.set(aList.indexOf(oldItem), newItem); // only the first item found
	}

	public static void delete(ArrayList<String> aList, String target){
		aList.remove(aList.indexOf(target)); // only the first item found
	}

	public static void main(String[] args){
		List<String> list = new ArrayList();
		list.add("my");
		list.add("first");
		list.add("list");
		list.add("my");
		list.add("first");
		list.add("list");

		System.out.println(list.toString());

		replace((ArrayList) list, "first", "second");
		System.out.println(list.toString());

		delete((ArrayList) list, "second");
		System.out.println(list.toString());

	}
}

