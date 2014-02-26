import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;

public class TestBinaryTree{
	public static void main(String[] args){
		String[] data = {"Kyle", "Robert", "Sammy", "Duke", "Paul", "Fish", "Amoeba"};

		
		BinarySearchTree bst = new BinarySearchTree();

		for(int i = 0; i < data.length; i++){
			bst.add(data[i]);
		};
		

		System.out.println(bst.toString());
	}
}