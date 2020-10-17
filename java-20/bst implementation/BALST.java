//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           BALST
// Files:           BALSTTest, BALSTADT, BALST, BSTNode
// Course:          CS400, Fall, 2019
//
// Author:          Tejvir Mann
// Email:           tsmann@wisc.edu
// Lecturer's Name:  Deb Deppeler
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: NA
// Partner Email: NA
// Partner Lecturer's Name: NA
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   ___ Write-up states that pair programming is allowed for this assignment.
//   ___ We have both read and understand the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully 
// acknowledge and credit those sources of help here.  Instructors and TAs do 
// not need to be credited here, but tutors, friends, relatives, room mates, 
// strangers, and others do.  If you received no outside help from either type
//  of source, then please explicitly indicate NONE.
//
// Persons:        NA
// Online Sources: NA
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 
 * Class to implement a BalanceSearchTree. 
 * This class contains many method that helps insert, remove and
 * balance the AVL tree
 * 
 * 
 * AVL TREE
 * 
 * @author Tejvir Mann
 * 
 * @param <K> is the generic type of key
 * @param <V> is the generic type of value
 */
public class BALST<K extends Comparable<K>, V> implements BALSTADT<K, V> {

	private BSTNode<K, V> root; // represents the root of this BST.
	List<K> tranversalList; //global list. used for the traversals. 

	private int numKeys; //number of keys in the tree. 

	/*
	 * initializes the tree 
	 * @param none
	 */
	public BALST() {	
	root = null; //when initializing tree, root is null. 
	}

	/*
	 * gets the key at the root
	 * 
	 * @param na 
	 */
	@Override
	public K getKeyAtRoot() {
		
		return root.key; //gets the key
	}

	 /**
     * Tries to find a node with a key that matches the specified key.
     * If a matching node is found, it returns the returns the key that is in the left child.
     * If the left child of the found node is null, returns null.
     * 
     * @param key A key to search for
     * @return The key that is in the left child of the found key
     * 
     * @throws IllegalNullKeyException if key argument is null
     * @throws KeyNotFoundException if key is not found in this BST
     */
	@Override
	public K getKeyOfLeftChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {
		
		if(key == null) { //if key null throw exception 
			throw new IllegalNullKeyException();
		}
		
		if(contains(key) == false) { //if key not found, through exception 
			throw new KeyNotFoundException();
		}
		
		BSTNode <K,V> node = getHelper(key, root);  //finds the node to get child of
		
		if(node == null) {
			throw new KeyNotFoundException();
		}
		
		if(node.key == key) {  //if key is found 
			return node.left.key;
		}
		
		else {  //repeats 
			node = getHelper(key,root);
			return node.left.key;
			
	 }
	}
	
	 /**
     * Tries to find a node with a key that matches the specified key.
     * If a matching node is found, it returns the returns the key that is in the right child.
     * If the right child of the found node is null, returns null.
     * 
     * @param key A key to search for
     * @return The key that is in the right child of the found key
     * 
     * @throws IllegalNullKeyException if key is null
     * @throws KeyNotFoundException if key is not found in this BST
     */
	@Override
	public K getKeyOfRightChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {
		
		if(key == null) { //if key is null throws exception 
			throw new IllegalNullKeyException();
		}
		
		BSTNode <K,V> node = getHelper(key, root);
		
		if(node == null) { //if node is null or not found then exception thrown
			throw new KeyNotFoundException();
		}
		
		if(contains(key) == false) {
			throw new KeyNotFoundException();
		}
		
		if(node.key == key) { //if key is found return 
			return node.right.key;
		}
		
		else {
			//if not, go again. 
			node = getHelper(key,root);
			
			return node.right.key;
	 }
		
		
	}

	 /**
     * Returns the height of this BST.
     * H is defined as the number of levels in the tree.
     * 
     * If root is null, return 0
     * If root is a leaf, return 1
     * Else return 1 + max( height(root.left), height(root.right) )
     * 
     * Examples:
     * A BST with no keys, has a height of zero (0).
     * A BST with one key, has a height of one (1).
     * A BST with two keys, has a height of two (2).
     * A BST with three keys, can be balanced with a height of two(2)
     *                        or it may be linear with a height of three (3)
     * ... and so on for tree with other heights
     * 
     * @return the number of levels that contain keys in this BINARY SEARCH TREE
     */
	@Override
	public int getHeight() {
		if(root == null){  //if root null, height 0
			 return 0;
	      }
		  
		 else{
			 return heightHelper(root);  //gets height 
		  }    
	}

	/**
     * Returns the keys of the data structure in sorted order.
     * In the case of binary search trees, the visit order is: L V R
     * 
     * If the SearchTree is empty, an empty list is returned.
     * 
     * @return List of Keys in-order
     */
	@Override
	public List<K> getInOrderTraversal() {
		tranversalList = new ArrayList<>();
		return getInOrderHelper(root);  //calls helper 
	}

	/**
     * Returns the keys of the data structure in pre-order traversal order.
     * In the case of binary search trees, the order is: V L R
     * 
     * If the SearchTree is empty, an empty list is returned.
     * 
     * @return List of Keys in pre-order
     */
	@Override
	public List<K> getPreOrderTraversal() {
		tranversalList = new ArrayList<>();
		return getPreOrderHelper(root); //calls helper 
	}

	/**
     * Returns the keys of the data structure in post-order traversal order.
     * In the case of binary search trees, the order is: L R V 
     * 
     * If the SearchTree is empty, an empty list is returned.
     * 
     * @return List of Keys in post-order
     */
	@Override
	public List<K> getPostOrderTraversal() {
		tranversalList = new ArrayList<>();
		return getPostOrderHelper(root); //calls helper 
	}

	/**
     * Returns the keys of the data structure in level-order traversal order.
     * 
     * The root is first in the list, then the keys found in the next level down,
     * and so on. 
     * 
     * If the SearchTree is empty, an empty list is returned.
     * 
     * @return List of Keys in level-order
     */
	@Override
	public List<K> getLevelOrderTraversal() {
		tranversalList = new ArrayList<>();
		
		int height = this.heightHelper(root);  //gets height 
		for(int i = 1; i <= height; i++) {
			getLevelOrderHelper(root, i); //calls helper 
		}
		
		return tranversalList;
		
  }

	 /** 
     * Add the key,value pair to the data structure and increase the number of keys.
     * If key is null, throw IllegalNullKeyException;
     * If key is already in data structure, throw DuplicateKeyException(); 
     * Do not increase the num of keys in the structure, if key,value pair is not added.
     */
	@Override
	public void insert(K key, V value) throws IllegalNullKeyException, DuplicateKeyException {
		BSTNode <K,V> newNode = new BSTNode<K,V>(key, value);
		if(key == null) { //checks if key is null, else throws an exception 
			throw new IllegalNullKeyException();
		} 
		
		if(contains(key)) { //if duplicate key
			throw new DuplicateKeyException();
		}
		
		if(root == null) { //if root is null 
			root = newNode;
			numKeys++;
		}
		
		else {
			insertHelper(newNode,root); //normaly inserts a node.  
			
			//checks if bf needs to be fixed, if not returns false, then method done. 
			if(bfIteratorTester(root) == false) {
				return;
			}
			
			BSTNode <K,V> badNode = bfIterator(root); //finds the bad node in the tree
			BSTNode <K,V> goodNode; //initializesthe good node. 
			
			int balFactor = balanceFactor(badNode); //finds the balance factor. 
			

			//if the balance factor is messed up
			if(balFactor > 1) {    
				
				//the 4 options to fix.
				if(balanceFactor(badNode.left) < 0 ) {       
					goodNode = LeftRightHelper(badNode); //left right switch 
				}
				
				else {
					goodNode = RightRightHelper(badNode); //right switch 
				}
			}
			
			else {
				if(balanceFactor(badNode.right) > 0) {
					goodNode = RightLeftHelper(badNode); //right left switch 
				}
				
				else {
					goodNode = LeftLeftHelper(badNode); //left switch 
				}
			}
			
			//now you have the good node
			//you have the bad node. 
			//all you have to do now is run through the tree and set the new node to the bad node
			//insertBalancer does this 
			root = insertBalancer(goodNode, badNode, root);
			
		}
	}

	/** 
	 * If key is found, remove the key,value pair from the data structure and decrease num keys.
	    * If key is not found, do not decrease the number of keys in the data structure.
	    * If key is null, throw IllegalNullKeyException
	    * If key is not found, throw KeyNotFoundException().
	    */
	@Override
	public boolean remove(K key) throws IllegalNullKeyException, KeyNotFoundException {
		
		if(key == null) {
			throw new IllegalArgumentException();
		}
		
		if(contains(key) == false) {
			throw new KeyNotFoundException();
		}
		
		
		BSTNode <K,V> newNode = removeHelper(key, root); //sets newNode to a value
        
		
		if(key == newNode.key) { //checks key 
            	numKeys--;
            	return true;
            }
	
		return false;
		
		
		
	}

	/**
     *  Returns the value associated with the specified key
     *
     * Does not remove key or decrease number of keys
     * If key is null, throw IllegalNullKeyException 
     * If key is not found, throw KeyNotFoundException().
     */
	@Override
	public V get(K key) throws IllegalNullKeyException, KeyNotFoundException {
		
		if(key == null) {
			throw new IllegalNullKeyException();
		}
		
		BSTNode<K, V> node = getHelper(key, root);
		
		if(contains(key) == false) {  //if contains 
			throw new KeyNotFoundException();
		}
		
		if(node == null) {
			throw new KeyNotFoundException();
		}
		
		return node.value;
	}

	/** 
     * Returns true if the key is in the data structure
     * If key is null, throw IllegalNullKeyException 
     * Returns false if key is not null and is not present 
     */
	@Override
	public boolean contains(K key) throws IllegalNullKeyException {
		boolean TorF = false;
		
		
		if(key == null) {
			throw new IllegalNullKeyException();
		}
		
		if(containsHelper(key, root)) { //checks if the tree contains the key. 
			TorF = true;
		}
		
		return TorF;
	}

	/**
     *  Returns the number of key,value pairs in the data structure
     */
	@Override
	public int numKeys() {
//		int numKeys = 0;
//		
//		if(root == null && numKeys == 0) {
//			return 0;
//		}
//       
//		if(root != null) {
//			 
//			 getInOrderHelper(root.left);
//			 numKeys++;
//			 getInOrderHelper(root.right); 
//		 }
		
		if(root == null) {
			return 0;
		}
		
		return numKeys;
	}

	/**
     * Print the tree. 
     *
     * For our testing purposes: all keys that we insert in the tree
     * will have a string length of exactly 2 characters.
     * example: numbers 10-99, or strings aa - zz, or AA to ZZ
     *
     * This makes it easier for you to not worry about spacing issues.
     *
     * You can display in any of a variety of ways, but we should see
     * a tree that we can identify left and right children of each node
     *
     * For example: 
     
       |       |-------50
       |-------40
       |       |-------35
       30
       |-------20
       |       |-------10
       
       Look from bottom to top. Inorder traversal of above tree (10,20,30,35,40,50)
       
       Or, you can display a tree of this kind.
       
           30
           /\
          /  \
         20  40
         /   /\
        /   /  \
       10  35  50 

       Or, you can come up with your own orientation pattern, like this.

       10                 
               20
                       30
       35                
               40
       50                  

       The connecting lines are not required if we can interpret your tree.

     */
	@Override
	public void print() {
		BSTNode <K,V> temp = root;
		if(root == null) {
			System.out.println("The tree is currently empty");  //if empty 
			return;
		}
		printHelper(temp,0); //calls helper 
	}
	
	/*
	 * Helper method for print
	 */
	private void printHelper(BSTNode<K,V> node, int height) {
		if(node == null) {
			return;
		}
		
		printHelper(node.right, height + 1);  //runs through all the nodes 
		if(height != 0) {
			for(int i = 0; i <height -1; i++) {
				System.out.print("|\t"); 
			}
			System.out.println("----->" + node.key); //after each prints this.
		}
		
		else {
			System.out.println(node.key);
		   printHelper(node.left, height + 1);
	}
	}
	
	/*
	 * Helper method for getKeyOfLeftChildOf and getKeyOfRightChildOf
	 */
	private BSTNode <K,V> getHelper(K key, BSTNode <K,V> root) throws KeyNotFoundException {  
		
		if(root.key == key) { //if key is same return. 
			return root;
		}
		
		
		else {
			
			if(key.compareTo(root.key) < 0) { //checks if the key is the same, if not recurses.
				root = getHelper(key, root.left);
			}
			
			if(key.compareTo(root.key) > 0) {
				root = getHelper(key,root.right);
				
			}
		}
		
		return root;
	
	}
	
	/*
	 * Helper method for the height method.
	 */
	private int heightHelper(BSTNode <K,V> root) {

		if(root == null) {
			return 0;
		}
		
		else { //get a height from a node. 
			int right = heightHelper(root.right);
			int left = heightHelper(root.left);
			return Math.max(right,left)+1;
		}
		
		
	 }
	 
	/*
	 * Helper method for getInOrderMethod
	 */
	private List<K> getInOrderHelper(BSTNode <K,V> root) { 
		 
		 if(root != null) {
			 //runs to the left then right 
			 getInOrderHelper(root.left);
			 tranversalList.add(root.key);
			 getInOrderHelper(root.right); 
		 }
		 
		 return tranversalList;
	 }
	
	/*
	 * Helper method for getPreOrderMethod
	 */
	private List<K> getPreOrderHelper(BSTNode <K,V> root) {
		 //adds then runs through the left and the right 
	 
		 if(root != null) {
			 tranversalList.add(root.key);
			 getPreOrderHelper(root.left);             
			 getPreOrderHelper(root.right);
			 
		 }
		 
		 return tranversalList;
	 
	}
	
	/*
	 * Helper method for getPostOrderMethod
	 */
	private List<K> getPostOrderHelper(BSTNode <K,V> root) {
		 //goes left, then right, then adds after. 
	 
		 if(root != null) {
			 
			 getPostOrderHelper(root.left);             
			 getPostOrderHelper(root.right);
			 tranversalList.add(root.key);
			 
		 }
		 
		 return tranversalList;
	 
	}
	
	/**
     * Returns the keys of the data structure in level-order traversal order.
     * 
     * The root is first in the list, then the keys found in the next level down,
     * and so on. 
     * 
     * If the SearchTree is empty, an empty list is returned.
     * 
     * @return List of Keys in level-order
     */
	private void getLevelOrderHelper(BSTNode <K,V> root, int level) {
	
	if(root == null) {  //if root is null, then return 
		return;
	}
		
	//adding for key
	if(level == 1) {
		tranversalList.add(root.key);
	}
	
	else if(level > 1) {
		//go to left then right s-tree
		getLevelOrderHelper(root.left, level - 1);
		getLevelOrderHelper(root.right, level - 1);
	}
		
	}
	
	/*
	 * Meant to help out with contains 
	 * 
	 * @param key - key
	 * @param root - node value 
	 */
	private boolean containsHelper(K key, BSTNode <K,V> root) {
	 
		
	if (root == null) {  //if null return 
		return false;
	}
	
    if (root.key == key) { //if key is key then contains true. 
    	return true;
    }
		    
    int compare = root.key.compareTo(key);  //recursively runs through tree to find key. 
    if (compare > 0) {  //might need to switch
    	return containsHelper(key, root.left);
    }
		
    return containsHelper(key, root.right);

		
    }
	
	/*
	 * Meant to help out with insert. Compares then it inserts into the code.
	 * 
	 * @param newNode - a new node that should have the stuff to be added. 
	 * @param root - the tree node 
	 */
	private void insertHelper(BSTNode <K,V> newNode, BSTNode <K,V> root) {
		
		int compare = newNode.key.compareTo(root.key); //gets the compare val. 
		
	      if (compare < 0) {
	            if (root.left != null) { //if less and not null, then tries to find null spot. 
	                insertHelper(newNode, root.left);
	                		
	            } else {
	            	root.left = newNode;
	            	
	                
	            }
	      }
	      
	      else if (compare > 0) {
	            if (root.right != null) {  //if more and not null, then tries to find null spot. 
	               insertHelper( newNode, root.right);
	                		
	            } else {
	            	root.right = newNode; 
	                
	            }
	      }
	}
	
	/*
	 * Given the goodnode and the badnode and the tree. The method finds the bad node and replaces 
	 * it with the good node. 
	 * 
	 * @param goodNode - the node that is the replacer. 
	 * @param badNode - the node to replace 
	 * @param root - the tree. 
	 */
	private BSTNode<K, V> insertBalancer(BSTNode <K,V> goodNode, BSTNode <K,V> badNode, BSTNode <K,V> root) {
		
	
	 if(root != null) { 
		
			    
	    int compare = root.key.compareTo(badNode.key); //gets the compare 
	    
	    if (compare > 0) {  //
	    	root.left = insertBalancer(goodNode, badNode, root.left);
	    }
	    
	    else if (compare < 0) {
	    	root.right = insertBalancer(goodNode, badNode, root.right);
	    }
	    
	    else {
	    	if (root.key == badNode.key) { //finds the node with the right key, then replaces. 
		    	root = goodNode;
		    	
		    }
	    }
	 }
	return root;
	      
   }
		
	/*
	 * finds the balance factor for a node. 
	 * 
	 * @param root - node that has the tree. 
	 */
	private int balanceFactor(BSTNode <K,V> root) {
		int bFactor = 0;
		int left = 0;
		int right = 0;
		
		if(root.left != null) {
			left = heightHelper(root.left);
		}
		
		if(root.right != null) {
			right = heightHelper(root.right);
		}
		
		bFactor = left - right;   //gets the b factor by subtracting the right by the left. 
		
		return bFactor; 
	}
	
	/*
	 * checks if there is a node that has a bad balance factor.  
	 * 
	 * @param root - the node to go through the tree. 
	 */
	private Boolean bfIteratorTester (BSTNode <K,V> root) {
		int factor = 0;
		Boolean leftBF = false;
		Boolean rightBF = false;
		
		if(root != null) {
			  leftBF =   bfIteratorTester(root.left);  //runs through the tree
			
				factor = balanceFactor(root); //gets the factor, if  factor is wrong then returns true
				if(factor > 1 || factor < -1) {
					return true; 
				}	 
		
			rightBF =bfIteratorTester(root.right); 
		  }	
		
		if(rightBF || leftBF) { 
			return true;
		}
		
		return false; 
	}
	
	/*
	 * returns the root that has the wrong balance factor
	 * 
	 * @param root - the node to go through the tree. 
	 */
	private BSTNode<K,V> bfIterator (BSTNode <K,V> root) {
		BSTNode <K,V> rightN;
		BSTNode <K,V> leftN;
		
		int factor = 0;
		if(root != null) {
			leftN =  bfIterator(root.left);
			rightN = bfIterator(root.right);   //< now its here to to post order instead of in order.
			   //if(root != null) {
			
		  if(rightN != null) {
			//if(balanceFactor(rightN) > 1 || balanceFactor(rightN) < -1) {
				return rightN;
			//}
		  }
			
		  if(leftN != null) {
			//if(balanceFactor(leftN) > 1 || balanceFactor(leftN) < -1) {
				return leftN;
			//}
		  }
				 factor = balanceFactor(root);
				 if(factor > 1 || factor < -1) {
					return root; 
				 }	 
				 
				 else {
					return null;
				 }
			//   }
			   // bfIterator(root.right);   <-- used to be here
		  }
		
		
		
		
		return root; 
	}
	
	/*
	 * Adjusts the tree if a Left node right node rotation is needed.
	 * 
	 * @param root - the node to go through the tree. 
	 */
	private BSTNode<K, V> LeftRightHelper(BSTNode <K,V> root) {

		BSTNode <K,V> newNode = root;  //switches up the nodes. 
		BSTNode <K,V> leftN = root.left;
		BSTNode <K,V> rightN = root.left.right;
		root = rightN;
		newNode.left = root.right;
		leftN.right = root.left;
		root.right = newNode;
		root.left = leftN; 

		return root;  //used to be newNode now root. 
	}	
	
	/*
	 * Adjusts the tree if a right node rotation is needed.
	 * 
	 * @param root - the node to go through the tree. 
	 */
	private BSTNode<K, V> RightRightHelper(BSTNode <K,V> root) {
		BSTNode <K,V> newNode = root;
		
		root = newNode.left;  //switches up the subtree. 
		newNode.left = root.right; //save the subtrees 
		root.right = newNode; 
		
		
		return root;
	}
	
	/*
	 * Adjusts the tree if a right node, then left node rotation is needed.
	 * 
	 * @param root - the node to go through the tree. 
	 */
	private BSTNode<K, V> RightLeftHelper(BSTNode <K,V> root) {
		BSTNode <K,V> newNode = root;
		BSTNode <K,V> rightN = root.right;  //switches up the nodes. 
		BSTNode <K,V> leftN = root.right.left;
		root = leftN;
		newNode.right = root.left;
		rightN.left = root.right;
		root.left = newNode;
		root.right = rightN; 
			

		return root; 
	}
	
	/*
	 * Adjusts the tree if a left node rotation is needed.
	 * 
	 * @param root - the node to go through the tree. 
	 */
	private BSTNode<K, V> LeftLeftHelper(BSTNode <K,V> root) {
		BSTNode <K,V> newNode = root;

		root = newNode.right; //switches the nodes 
		newNode.right = root.left; //this saves the subtree below.
		root.left = newNode;

		return root; 
		
		
	}
	
	/*
	 * just deletes the node, and doesnt balance it. 
	 * 
	 * @param root - the node to go through the tree. 
	 */
	private BSTNode<K, V> removeHelper(K key, BSTNode <K,V> root) {
		 
		/* Base Case: If the tree is empty */
        if (root == null) {
        	return root; 
        }
        
  //deletes the node
        /* Otherwise, recur down the tree */
        if (key.compareTo(root.key) < 0)
            root.left = removeHelper(key, root.left); 
        else if (key.compareTo(root.key) > 0) 
            root.right = removeHelper(key, root.right); 
  
        // if key is same as root's key, then This is the node 
        // to be deleted 
        else
        { 
            // node with only one child or no child 
            if (root.left == null) 
                return root.right; 
            else if (root.right == null) 
                return root.left; 
  
            // node with two children: Get the inorder successor (smallest 
            // in the right subtree) 
            root.key = minValue(root.right); 
  
            // Delete the inorder successor 
            root.right = removeHelper(root.key, root.right); 
        } 
  
        return root; 
		
		
		
		
		
		
		
		
		
	}
	
	/*
	 * a helper for the remove helper. Finds the min value for the remove helper. 
	 * 
	 * @param root - the node to go through the tree. 
	 */
	private K minValue(BSTNode <K,V> root){ 
	    { 
	        K minv = root.key; 
	        while (root.left != null)  //runs through the root and gets the min value. 
	        { 
	            minv = root.left.key; 
	            root = root.left; 
	        } 
	        return minv; 
	    } 
	
	 }
	
}
	

