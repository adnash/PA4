import java.util.Iterator;
import java.util.LinkedList;

/**
 * Stub file for students to fill in. CSU CS200 Fall 2014 Lab 7.
 * @author David Newman (Fall 2010), modified AEH (Fall 2014)
 * @author modified by Jeremy Aldrich Fall 2014
 * @param <T>
 */
public class BSTIterator<T> implements Iterator<T> {
	//TODO: Create your needed instance variables.
		// a queue tracks the order for visiting the tree nodes
		private LinkedList<TreeNode<T>> queue = null;
		private TreeNode<T> binTree;
		private TreeNode<T> curr;
		
		
		/**
		 * Construct a new iterator object.
		 * @param binTree
		 */
		public BSTIterator(TreeNode<T> binTree) {
			//TODO: Initialize your instance variables.
			this.binTree = binTree;
			curr = null;						
			queue = new LinkedList<TreeNode<T>>(); 
			setInorder();
			
		}
		
		

		/* (non-Javadoc)
		 * Return true iff the iterator has more objects yet to return.
		 * @see java.util.Iterator#hasNext()
		 */
		@Override
		public boolean hasNext() {		 
			return !queue.isEmpty();
		}

		/* (non-Javadoc)
		 * Return the first object that has not yet been returned.
		 * @see java.util.Iterator#next()
		 */
		@Override
		public T next() {		
			curr = queue.remove();	
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
		
		public void setInorder() {			
			queue.clear();					
			inorder(binTree);
		}
		private void inorder(TreeNode<T> treeNode) {			
			if(treeNode!= null) {
				inorder(treeNode.getLeft());
				queue.add(treeNode);
				inorder(treeNode.getRight());			
			}
		}		

}
