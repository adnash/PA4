
public class BST {
	private int count;
	private static TreeNode<Term> root;
	
	//which initializes an instance variable called "root" as null and an instance variable called "count" as 0.
	public BST(){		
		count = 0;
		//root = null;
	}

	//which returns the number of unique words in the document (i.e., count).
	public int size(){
		return count;
	}

	//which adds a new Term or increments frequencies if the term already exists in the BST.
	public void add(String docName, String word){
		
		
		Term term = new Term(word);
		term.incFrequency(docName);
		TreeNode<Term> newNode = new TreeNode<Term>(term);
		//TreeNode<Term> root = new TreeNode<Term>(wp.termsTree.getRootItem());
		
//		if(root != null) {
//			BSTIterator<Term> iter = new BSTIterator<Term>(root);
//			
//			while(iter.hasNext()) {
//				System.out.println(iter.next() );
//			}
//		}
//			
		
		//empty node so add the term
		if(root == null) {
			
			root = newNode;		
		}else {
			
			TreeNode<Term> currentNode = root;			
			TreeNode<Term> parent;	
			
			while (true) {

				// root is the top parent so we start
				// there

				parent = currentNode;

				// Check if the new node should go on
				// the left side of the parent node

				if (word.compareTo(currentNode.getItem().getName())<0) {

					// Switch currentNode to the left child

					currentNode = currentNode.getLeft();

					// If the left child has no children

					if (currentNode == null) {

						// then place the new node on the left of it

						parent.setLeft(newNode);
						count++;
						return;

					}

				} else if (word.compareTo(currentNode.getItem().getName())>0){ // If we get here put the node on the right

					currentNode = currentNode.getRight();

					// If the right child has no children

					if (currentNode == null) {

						// then place the new node on the right of it

						parent.setRight(newNode);
						count++;
						return; 

					}
				//if we get here the node exists already
				}else {
					
					currentNode.getItem().incFrequency(docName);
					
					return;
				}
				
				
				

			}
		}
		
		
	}

	//which returns the Term object for the word. If printDepth is true, then get should keep
	//track of how deep in the tree it finds word and print out the value at the end in the form
	//" At depth 1" (At is preceded by 2 spaces). If the word is not found, it should print the 
	//deepest level that it checked.
	public Term get(String word, Boolean printDepth){

		int depth = 1;
		//Term term = new Term(word);
		//TreeNode<Term> newNode = new TreeNode<Term>(term);
		//TreeNode<Term> root = new TreeNode<Term>(wp.termsTree.getRootItem());
		
		
		

			
			TreeNode<Term> currentNode = root;			
				
			
			while(word.compareTo(currentNode.getItem().getName())!=0) {
				
				//check to the left since its less than the current node
				if(word.compareTo(currentNode.getItem().getName())<0){
					currentNode = currentNode.getLeft();
					depth++;
					
				//check to the right since its greater than the current node
				}else if(word.compareTo(currentNode.getItem().getName())>0) {
					currentNode = currentNode.getRight();
					depth++;
				}
				
				
				if(currentNode == null) {
					if(printDepth)
						System.out.println("  At depth "+depth);
					return null;
				}
			}
			//found the value here
			if(printDepth)
				System.out.println("  At depth "+depth);
			return currentNode.getItem();
		
	}
	
	public void inOrder(TreeNode<Term> node) {
		
//		if(node != null){
//			inOrder(node.getLeft());
//			System.out.println(node.getItem());
//			inOrder(node.getRight());
//		}
		
		
		BSTIterator<Term> iter = new BSTIterator<Term>(root);
		
		while(iter.hasNext()) {
			System.out.println(iter.next());
		}
		
	}
	
	public TreeNode<Term> getNode() {
		return this.root;
	}
	
	public static void main(String[] args) {

		BST tree = new BST();
		
		tree.add("Google", "panda");
		tree.add("Google", "tiger");
		tree.add("Google", "a");
		tree.add("Google", "c");
		tree.add("Google", "z");
		
		tree.inOrder(tree.getNode());
		
		
		//tree.get("zz",true);

		// Different ways to traverse binary trees

		// theTree.inOrderTraverseTree(theTree.root);

		// theTree.preorderTraverseTree(theTree.root);

		// theTree.postOrderTraverseTree(theTree.root);

		// Find the node with key 75

		//System.out.println("\nNode with the key 75");

		//System.out.println(theTree.findNode(75));

}

}
