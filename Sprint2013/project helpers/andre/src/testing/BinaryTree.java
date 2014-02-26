package testing;

class BinaryTree {
	BNode BTroot;

	BinaryTree() {
		BTroot = null;
	}

	private BNode insertAt(BNode CurrentRoot, BNode newNode) {
		if (CurrentRoot == null) {
			CurrentRoot = newNode;
		} else if (CurrentRoot.anyString.compareTo(newNode.anyString) < 0) {
			CurrentRoot.leftNode = insertAt(CurrentRoot.leftNode, newNode);
		} else {
			CurrentRoot.rightNode = insertAt(CurrentRoot.rightNode, newNode);
		}
		return CurrentRoot;
	}

	public void insertBTNode(String s) {
		BNode newBNode = new BNode(s);
		BTroot = insertAt(BTroot, newBNode);
	}

	private BNode searchBNode(BNode CurrentRoot, String s) {
		if (CurrentRoot == null) {
			return null;
		} else {
			if (s.compareTo(CurrentRoot.anyString) == 0) {
				return CurrentRoot;
			} else if (s.compareTo(CurrentRoot.anyString) < 0) {
				return searchBNode(CurrentRoot.leftNode, s);
			} else {
				return searchBNode(CurrentRoot.rightNode, s);
			}
		}
	}

	public BNode searchNodes(String s) {

		if (s == null) {
			return null;
		}

		BNode temp = searchBNode(BTroot, s);
		if (temp == null) {
			return null;
		} else {
			return temp;
		}
	}

	public void inorderShow(BNode CurrentRoot) {
		if (CurrentRoot != null) {
			inorderShow(CurrentRoot.leftNode);
			CurrentRoot.show();
			inorderShow(CurrentRoot.rightNode);
		}
	}

	public void inorderBTshow() {
		inorderShow(BTroot);
	}

	public void populateBTree(String s) {
		for (int i = 0; i < s.length(); i++) {
			insertBTNode(String.valueOf(s.charAt(i)));

		}
	}

}