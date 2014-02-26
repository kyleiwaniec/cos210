import java.util.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.*;
import java.awt.*;

public class ShapeData{
	private LinkedList<Point> points = new LinkedList();
	private String csvFile;
	 Button aButton = new Button();

	public ShapeData(){
		readFile();
		
	}
	public LinkedList<Point> getPoints(){
		return this.points;
	}
	public LinkedList<Point> addPoint(double x, double y){
		this.points.add(new Point(x, y));

		return this.points;
	}

	public void readFile(){
		//String csvFile = JOptionPane.showInputDialog("Enter the path to a data file:");

		JFileChooser chooser = new JFileChooser();
	   
	    int returnVal = chooser.showOpenDialog(aButton);
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	      
	       csvFile = chooser.getSelectedFile().getName();
	    }


		//String csvFile = "cat-coor2.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
	 
		try {
	 
			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
				String[] ps = line.split(cvsSplitBy);
				points.add(new Point(Double.parseDouble(ps[0]), Double.parseDouble(ps[1])));
			}
	 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}	
	}
}