/**
 * Implements a simple binary tree class for use in CSU
 * CS200 Fall 2010 Lab 9.
 * Based on code downloaded from the Carrano and Pritchard
 * text web site in 2007. Modified to make greater use of
 * generics.
 * @author David Newman
 * @date 2010-10-14
 *
 * @param <T>
 */
public abstract class BinaryTreeBasis<T> {
  protected TreeNode<T> root;

  public BinaryTreeBasis() {
    root = null;
  }  // end default constructor

  public BinaryTreeBasis(T rootItem) {
    root = new TreeNode<T>(rootItem, null, null);
  }  // end constructor
  
  public TreeNode<T> getRoot() {
	  return root;
  }

  public boolean isEmpty() {
// Returns true if the tree is empty, else returns false.
    return root == null;
  }  // end isEmpty

  public void makeEmpty() {
// Removes all nodes from the tree.
    root = null;
  }  // end makeEmpty

  public T getRootItem() throws TreeException {
// Returns the item in the trees root.
    if (root == null) {
      throw new TreeException("TreeException: Empty tree");
    }
    else {
      return root.getItem();
    }  // end if
  }  // end getRootItem

}  // end BinaryTreeBasis
