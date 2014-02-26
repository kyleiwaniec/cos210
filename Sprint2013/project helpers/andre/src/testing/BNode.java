package testing;

public class BNode {
	public BNode leftNode = null, rightNode = null;
	public String anyString;

	BNode(String anyString) {
		this.anyString = anyString;
		this.leftNode = null;
		this.rightNode = null;
	}

	public void show() {
		System.out.println(this.anyString);
	}

}
