import java.util.Iterator;
import java.util.Queue;

/**
 * Stub file for students to fill in. CSU CS200 Fall 2014 Lab 7.
 * @author David Newman (Fall 2010), modified AEH (Fall 2014)
 *
 * @param <E>
 */
public class InorderIterator<Term> implements Iterator<Term> {
	
	//TODO: Create your needed instance variables.
	// a queue tracks the order for visiting the tree nodes
	private Queue<TreeNode<Term>> inqueue = new Queue<TreeNode<Term>>();
	private BinaryTree<Term> binTree;
	private TreeNode<Term> curr;
	
	/**
	 * Construct a new iterator object.
	 * @param binTree
	 */
	public InorderIterator(BinaryTree<Term> binTree) {
		//TODO: Initialize your instance variables.
		this.binTree = binTree;
		curr = null;
		setInorder();
		
	}
	
	

	/* (non-Javadoc)
	 * Return true iff the iterator has more objects yet to return.
	 * @see java.util.Iterator#hasNext()
	 */
	@Override
	public boolean hasNext() {		
		return !inqueue.isEmpty();
	}

	/* (non-Javadoc)
	 * Return the first object that has not yet been returned.
	 * @see java.util.Iterator#next()
	 */
	@Override
	public E next() {		
		curr = inqueue.dequeue();		
		return curr.getItem();
	}

	/* (non-Javadoc)
	 * This is an illegal operation for this iterator.
	 * @see java.util.Iterator#remove()
	 */
	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

	/*
	 * Put the correct order of nodes onto the queue
	 */
	public void setInorder() {
		inqueue.dequeueAll();
		inorder(binTree.root);
	}
	private void inorder(TreeNode<E> treeNode) {
		//TODO: recursively visit nodes in the tree, 
		//      adding as appropriate to the queue
		
		if(treeNode != null) {
			inorder(treeNode.getLeft());
			inqueue.enqueue(treeNode);
			inorder(treeNode.getRight());			
		}
	}
}