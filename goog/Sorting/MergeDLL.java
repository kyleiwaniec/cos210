import java.util.*;
import java.util.LinkedList;
import java.util.Date;
import java.io.*;

public class MergeDLL{
	public static void main(String[] args) throws IOException {
		File file = new File("crimeandpunishment/all.txt");
	    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
	    String line = null;
	    DLL words = new DLL();
	    int j = 0;
	    while( (line = br.readLine())!= null ){
	        String [] tokens = line.split("\\s+");
	        for(int i = 0; i < tokens.length; i++, j++){
	          words.addNext(tokens[i]);
	        }
	    }
	    //System.out.println("before sort: "+words.toString());
		Date start = new Date();
		//Node<String> h = words.getHead();
		words.mergeSort(words.getHead());
		//System.out.println("after sort: "+words.nodeToString(words.mergeSort(words.getHead())));
		//Collections.sort(words);
		Date end = new Date();

		System.out.println("time:" +(end.getTime()-start.getTime()));

		
		// System.out.println("words length: "+words.getSize());
		// System.out.println("current: "+words.getFirst());
		

	}

}