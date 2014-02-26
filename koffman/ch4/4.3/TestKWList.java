public class TestKWList{
	public static void main(String[] args){
		KWArrayList<String> list = new KWArrayList<String>(5);
		list.add("this");
		list.add("list");
		list.add("is");
		list.add("my");
		list.add("implementation");

		System.out.println(list.toString());

		list.add(" - woohoo!");
		System.out.println(list.toString());
		list.add(1, "awesome");
		System.out.println(list.toString());
		list.set(3, "was");
		System.out.println(list.toString());
		//list.remove(0);
		System.out.println("removed item: "+list.remove(0));
		System.out.println(list.toString());
		
		System.out.println("the word awesome is at index: "+list.find("awesome"));
		System.out.println("popped item: "+list.pop());
		System.out.println(list.toString());

		System.out.println("shifted item: "+list.shift());
		System.out.println(list.toString());
	}
}