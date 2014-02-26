package testing;

public class testing {

	public static void main(String[] args) {
		BinaryTree bt = new BinaryTree();
		String regex = "[a-z]";
		MetaSet ms = new MetaSet();

		bt.populateBTree(regex);
		bt.inorderBTshow();

		System.out.println(ms.toSet("\u00c0\u00c6"));

	}
}
