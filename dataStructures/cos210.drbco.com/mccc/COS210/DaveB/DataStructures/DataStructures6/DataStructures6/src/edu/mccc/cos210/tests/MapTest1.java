package edu.mccc.cos210.tests;
import java.util.Iterator;
import edu.mccc.cos210.ds.Map;
import edu.mccc.cos210.ds.Set;
import edu.mccc.cos210.ds.Utility;
public class MapTest1 {
	public static void main(String[] sa) throws Exception {
		new MapTest1().doIt();
	}
	private void doIt() throws Exception {
		Map<String, String> map = Utility.getMap();
		map.put("Fish", "Tasty");
		map.put("Duck", "Quack");
		map.put("Cat", "Curious");
		map.put("Cow", "Lunch");
		Set<String> keys = map.getKeys();
		for (String key : keys) {
			System.out.println(key + " " + map.get(key));
		}
	}
}
