
public class BST {
	private int count;
	private TreeNode<Term> root;
	
	//which initializes an instance variable called "root" as null and an instance variable called "count" as 0.
	public BST(){		
		count = 0;
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
						return;

					}

				} else if (word.compareTo(currentNode.getItem().getName())>0){ // If we get here put the node on the right

					currentNode = currentNode.getRight();

					// If the right child has no children

					if (currentNode == null) {

						// then place the new node on the right of it

						parent.setRight(newNode);
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
		Term term = new Term(word);
		TreeNode<Term> newNode = new TreeNode<Term>(term);
		//TreeNode<Term> root = new TreeNode<Term>(wp.termsTree.getRootItem());
		
		
		
		//empty node so add the term
		if(root == newNode) {
			if(printDepth)
				System.out.println("  At depth " + depth);
			return term;
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
					depth++;

					// If the left child has no children

					if (currentNode == newNode) {

						// then place the new node on the left of it
						if(printDepth)
							System.out.println("  At depth " + depth);
						return term;

					}

				} else if (word.compareTo(currentNode.getItem().getName())>0){ // If we get here put the node on the right

					currentNode = currentNode.getRight();
					depth++;

					// If the right child has no children

					if (currentNode == newNode) {

						// then place the new node on the right of it
						if(printDepth)
							System.out.println("  At depth " + depth);
						return term;

					}
				//if we get here the node exists already
				}else {
					
					if(printDepth)
						System.out.println("  At depth " + depth);
					return null;
				}

			}
		}
		
		
	}
	
	public void inOrder(TreeNode<Term> node) {
		
		if(node != null){
			inOrder(node.getLeft());
			System.out.println(node);
			inOrder(node.getRight());
		}
		
	}
	
	
	public static void main(String[] args) {

		BST tree = new BST();
		
		tree.add("Google", "a");
		tree.add("Google", "z");
		tree.add("Google", "c");
		tree.add("Google", "d");
		tree.add("Google", "d");
		
		tree.inOrder(tree.root);
		
		
		tree.get("d",true);

		// Different ways to traverse binary trees

		// theTree.inOrderTraverseTree(theTree.root);

		// theTree.preorderTraverseTree(theTree.root);

		// theTree.postOrderTraverseTree(theTree.root);

		// Find the node with key 75

		//System.out.println("\nNode with the key 75");

		//System.out.println(theTree.findNode(75));

}

}
