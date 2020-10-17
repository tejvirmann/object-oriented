import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

/**
 * Implementation of a B+ tree to allow efficient access to
 * many different indexes of a large data set. 
 * BPTree objects are created for each type of index
 * needed by the program.  BPTrees provide an efficient
 * range search as compared to other types of data structures
 * due to the ability to perform log_m N lookups and
 * linear in-order traversals of the data items.
 * 
 * @author sapan (sapan@cs.wisc.edu)
 *
 * @param <K> key - expect a string that is the type of id for each item
 * @param <V> value - expect a user-defined type that stores all data for a food item
 */
public class BPTree<K extends Comparable<K>, V> implements BPTreeADT<K, V> {

    // Root of the tree
    private Node root;
    
    // Branching factor is the number of children nodes 
    // for internal nodes of the tree
    private int branchingFactor;
    private int size;  //the number of leaf nodes
   
    
    
    /**
     * Public constructor
     * 
     * @param branchingFactor 
     */
    public BPTree(int branchingFactor) {
        if (branchingFactor <= 2) {
            throw new IllegalArgumentException(
               "Illegal branching factor: " + branchingFactor);
        }
        this.branchingFactor = branchingFactor;
        root = new LeafNode();
    }
    
    
    /**
     * Inserts the key and value in the appropriate nodes in the tree
     * If the key is null, throw IllegalArgumentException
     * 
     * Note: key-value pairs with duplicate keys can be inserted into the tree.
     * 
     * @param key
     * @param value
     */
    @Override
    public void insert(K key, V value) {
       if (key == null) {
    	   throw new IllegalArgumentException();
       }
       
    	root.insert(key, value);
        size++;
    }
    
    
    /**
     * Gets the values that satisfy the given range 
     * search arguments.
     * 
     * Value of comparator can be one of these: 
     * "<=", "==", ">="
     * 
     * Example:
     *     If given key = 2.5 and comparator = ">=":
     *         return all the values with the corresponding 
     *      keys >= 2.5
     *      
     * If key is null or not found, return empty list.
     * If comparator is null, empty, or not according
     * to required form, return empty list.
     * 
     * @param key to be searched
     * @param comparator is a string
     * @return list of values that are the result of the 
     * range search; if nothing found, return empty list
     */
    @Override
    public List<V> rangeSearch(K key, String comparator) {
        if (!comparator.contentEquals(">=") && 
            !comparator.contentEquals("==") && 
            !comparator.contentEquals("<=") ) {
        	return new ArrayList<V>();
        }
        	
        if(key == null) {
        	return new ArrayList<V>();
        }

        if(get(key) == null) {
        	return new ArrayList<V>();
        }
 
        return root.rangeSearch(key, comparator);
    }
    
    /**
     * Returns the value of the first leaf with a matching key.
     * If key is null, return null.
     * If key is not found, return null.
     *
     * @param key to find
     * @return value of the first leaf matching key
     */
     @Override
     public V get(K key) {
	
 		if(root == null) {
			return null;
		}
    	
    	if(key == null) {
    		return null;
    	}
    	
    	List <V> value = rangeSearch(key, "==");
 
    	if(value != null){
    		return value.get(0);
    	}
    	
    	return null;
    	
     }

     /**
      * Return the number of leaves in the tree. Aka the 
      * number of values. Aka the number of inserts.
      * 
      * @return number of leaves
      */
     @Override
     public int size() {
        return size;
     }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        Queue<List<Node>> queue = new LinkedList<List<Node>>();
        queue.add(Arrays.asList(root));
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            Queue<List<Node>> nextQueue = new LinkedList<List<Node>>();
            while (!queue.isEmpty()) {
                List<Node> nodes = queue.remove();
                sb.append('{');
                Iterator<Node> it = nodes.iterator();
                while (it.hasNext()) {
                    Node node = it.next();
                    sb.append(node.toString());
                    if (it.hasNext())
                        sb.append(", ");
                    if (node instanceof BPTree.InternalNode)
                        nextQueue.add(((InternalNode) node).children);
                }
                sb.append('}');
                if (!queue.isEmpty())
                    sb.append(", ");
                else {
                    sb.append('\n');
                }
            }
            queue = nextQueue;
        }
        return sb.toString();
    }
    
       
    /**
     * This abstract class represents any type of node in the tree
     * This class is a super class of the LeafNode and InternalNode types.
     * 
     * @author sapan
     */
    private abstract class Node {
        
        // List of keys
        List<K> keys;
        protected boolean isLeafNode; 
        
        /**
         * Package constructor
         */
        Node() {
        	this.keys = new ArrayList<K>();
        }
        
        /**
         * Inserts key and value in the appropriate leaf node 
         * and balances the tree if required by splitting
         *  
         * @param key
         * @param value
         */
        abstract void insert(K key, V value);

        /**
         * Gets the first leaf key of the tree
         * 
         * @return key
         */
        abstract K getFirstLeafKey();
        
        /**
         * Gets the new sibling created after splitting the node
         * 
         * @return Node
         */
        abstract Node split();
        
        /**
         * Gets the values that satisfy the given range 
         * search arguments.
         * 
         * Value of comparator can be one of these: 
         * "<=", "==", ">="
         * 
         * Example:
         *     If given key = 2.5 and comparator = ">=":
         *         return all the values with the corresponding 
         *      keys >= 2.5
         *      
         * If key is null or not found, return empty list.
         * If comparator is null, empty, or not according
         * to required form, return empty list.
         * 
         * @param key to be searched
         * @param comparator is a string
         * @return list of values that are the result of the 
         * range search; if nothing found, return empty list
         */
        abstract List<V> rangeSearch(K key, String comparator);

        /**
         * 
         * @return boolean
         */
        abstract boolean isOverflow();
        
        public String toString() {
            return keys.toString();
        }
    
        private int keyNumber() {
			return keys.size();
		}
        
        /*
         *  This method recursively goes down the tree 
         *  using the key and the root. 
         *  once it gets to the parent internal node. it adds a 
         *  new key, to the node, and a new node. 
         *  then it recursively returns back up the tree, and it 
         *  checks if any of the internal nodes are overflow
         *  if yes, it fixes those too.
         *  
         *  @param key - the key of one of the children of the parent node we
         *  				are trying to find. 
         *  @param current - to move through the tree. 
         *  @param sibiling - this contains the key and the values we are going 
         *  						to add.
         */
        private BPTree<K, V>.InternalNode insertRecursive(K key, BPTree<K, V>.InternalNode current, Node sibling ) {
        
        	boolean YorN = false; 
        	
        	
       //when you find the right internalnode, and you insert.  	
       for(int i = 0; i <current.children.size(); i++) {
        if(current.children.get(i).isLeafNode) {  //this will change to current.isLeafNode later probably.
        	
        	//IF loc and valueindex doesnt work: just do a forloop through the children.keys. Find index this 
        	//way
        	
        	//THIS LOC AND VALUEINDEX STUFF IS THE ONLY QUESTIONABLE STUFF
        	//IF DOESNT WORK, then remove for loop, have.isLefnode. and get correct position
        	//to insert into leaf node. 
        	//have to find the right position to add the keys and children at.
			int loc = Collections.binarySearch(current.keys, key); //location the node should be. used to be 'keys' not 'current.keys'
			int valueIndex = loc >= 0 ? loc : -loc - 1; 
	
			if (loc >= 0) { //not sure if this is needed.
				current.keys.set(valueIndex, sibling.getFirstLeafKey());
	        	current.children.set(valueIndex,sibling);
	        	YorN = true;
			} 
			
			else {   //and this........................ are not magically recursive, then this who thing will be replaced by recursive insert
				//add key to current, and add node to current. 
	        	current.keys.add(sibling.getFirstLeafKey());
	        	current.children.add(sibling);
	        	YorN = true;
			}
          }
        }
       
       //else move on to the next node. By comparing the keys. Move to the right one.
       if(YorN = false) {
       	//traverse to the next node. 
    	   if(key.compareTo(current.keys.get(0)) < 0){
    		   current = insertRecursive(key, (BPTree<K, V>.InternalNode) current.children.get(0), sibling );
    	   }
       	
       }
       
       
       
       //if overflow with the current node.
       if(current.isOverflow()) {
    	   //call current.split - it will split differently depending 
    	   // on if its an in
    	   
    	   
       //split aka remove the key from the current
       //add the key to the correct spot of the parent node
       //add the children to the correct spot of the parent node. 
       }
	return current;
       
       
       
       
       
        	
        	
        	
        }
    } 
    
    // End of abstract class Node
    
    /**
     * This class represents an internal node of the tree.
     * This class is a concrete sub class of the abstract Node class
     * and provides implementation of the operations
     * required for internal (non-leaf) nodes.
     * 
     * @author sapan
     */
    private class InternalNode extends Node {

        // List of children nodes
        List<Node> children;
        
        /**
         * Package constructor
         */
        InternalNode() {
            super(); 
           // this.keys = new ArrayList<K>();  //maybe change this back
            this.children= new ArrayList<Node>();
            isLeafNode = false;
        }
        
        /**
         * (non-Javadoc)
         * @see BPTree.Node#getFirstLeafKey()
         */
        K getFirstLeafKey() {
        	return children.get(0).getFirstLeafKey();  //idk about this , not sure how it works
        }
        
        /**
         * (non-Javadoc)
         * @see BPTree.Node#isOverflow()
         */
        boolean isOverflow() {
        	return children.size() > branchingFactor;
        }
        
        /**
         * (non-Javadoc)
         * @see BPTree.Node#insert(java.lang.Comparable, java.lang.Object)
         */
        void insert(K key, V value) {

			Node child = getChild(key);
			
			child.insert(key, value); //inserts in leafnode. 
			
			//going to have to make both of the inserts recursive. 
			// these both are going to be replaced by that 
			//one recurseive method in leafnode. 
			//that recuserive method in leafnode could be moved below later
			
//			if (child.isOverflow()) {
//				Node sibling = child.split();
//				insertChild(sibling.getFirstLeafKey(), sibling);
//			}
//			
//			if (root.isOverflow()) { //maybe this.overflow
//				Node sibling = split();
//				InternalNode newRoot = new InternalNode();
//				newRoot.keys.add(sibling.getFirstLeafKey());
//				newRoot.children.add(this);
//				newRoot.children.add(sibling);
//				root = newRoot;
//			}
        }
        
        /**
         * (non-Javadoc)
         * @see BPTree.Node#split()
         */
        Node split() {
        	
        	int from = 0;
        	int to = 0;
			
        	if(root.keyNumber()%2 != 0) {
        		 from = root.keyNumber() / 2 + 1;
        		  to = from+1;//root.keyNumber(); //maybe to = from +1
        	}
        	
        	else {
        	     from = (root.keyNumber() / 2);
        		 to = from +1; // root.keyNumber(); //maybe to = from +1
        	}
        	
        	InternalNode sibling = new InternalNode();
			sibling.keys.addAll(keys.subList(from, to));
			sibling.children.addAll(children.subList(from, to + 1)); //

			keys.subList(from - 1, to).clear();
			children.subList(from, to + 1).clear();

			return sibling;
        }
        
        /**
         * Gets the values that satisfy the given range 
         * search arguments.
         * 
         * Value of comparator can be one of these: 
         * "<=", "==", ">="
         * 
         * Example:
         *     If given key = 2.5 and comparator = ">=":
         *         return all the values with the corresponding 
         *      keys >= 2.5
         *      
         * If key is null or not found, return empty list.
         * If comparator is null, empty, or not according
         * to required form, return empty list.
         * 
         * @param key to be searched
         * @param comparator is a string
         * @return list of values that are the result of the 
         * range search; if nothing found, return empty list
         */
        List<V> rangeSearch(K key, String comparator) {
        	return getChild(key).rangeSearch(key, comparator);
        }
        
        /*
         * Given the key and child node, it adds the key and node to the 
         * right spot without recursion or anything
         */
        private void insertChild(K key, Node child) {
			int loc = Collections.binarySearch(keys, key);
			int childIndex = loc >= 0 ? loc + 1 : -loc - 1;
			if (loc >= 0) {
				//used to be:children.set(childIndex, child);
				children.add(childIndex, child); 
			} 
			else {
				keys.add(childIndex, key);
				children.add(childIndex + 1, child);
			}
		}
        /*
         * returns the node of the key and value to be inserted in
         * I'm changing 
         */
        private Node getChild(K key) {
			int loc = Collections.binarySearch(keys, key);
			int childIndex = loc >= 0 ? loc + 1 : -loc - 1;
			return children.get(childIndex);
		}
    
    } // End of class InternalNode
    
    
    /**
     * This class represents a leaf node of the tree.
     * This class is a concrete sub class of the abstract Node class
     * and provides implementation of the operations that
     * required for leaf nodes.
     * 
     * @author sapan
     */
    private class LeafNode extends Node {
        
        // List of values
        List<V> values;
        
        // Reference to the next leaf node
        LeafNode next;
        
        // Reference to the previous leaf node
        LeafNode previous;
        
        /**
         * Package constructor
         */
        LeafNode() {
            super();
            //keys = new ArrayList<K>();
			values = new ArrayList<V>();
			isLeafNode = true;
        }
        
        
        /**
         * (non-Javadoc)
         * @see BPTree.Node#getFirstLeafKey()
         */
        K getFirstLeafKey() {
        	return keys.get(0);
        }
        
        /**
         * (non-Javadoc)
         * @see BPTree.Node#isOverflow()
         */
        boolean isOverflow() {
        	return values.size() > branchingFactor - 1;
        }
      
        /**
         * (non-Javadoc)
         * @see BPTree.Node#insert(Comparable, Object)
         */
        void insert(K key, V value) {
			int loc = Collections.binarySearch(keys, key); //location the node should be
			
			//essentially means, if loc>0 is true, then value index = loc
			//if false,(else), then valueindex = -loc-1
			int valueIndex = loc >= 0 ? loc : -loc - 1; 
	
			if (loc >= 0) { //if this ....................
				values.set(valueIndex, value);
			} 
			
			else {   //and this........................ are not magically recursive, then this who thing will be replaced by recursive insert
				keys.add(valueIndex, key);
				values.add(valueIndex, value);
			}
			
			if (this.isOverflow()) { //used to be root.isOverflow
				Node sibling = split();
				
				if(root == this) { //aka # internal nodes = 0
				InternalNode newRoot = new InternalNode();
				newRoot.keys.add(sibling.getFirstLeafKey());
				newRoot.children.add(this);
				newRoot.children.add(sibling);
				root = newRoot;
				return;
				}
				
				else {
					root.insertRecursive(key, (BPTree<K, V>.InternalNode) root, sibling );  //I casted!
					
					
//					this.keys.add(sibling.getFirstLeafKey());
//					this.add(sibling);
					
					
					//find the parent internal node. 
					//add key and values to the right spot. 
					//recursive internal node checker. 
					//	this goes to each parent internal node, and checks 
					//if there is overflow. Then fixes. 
				}
			}
        }
        
        /**
         * (non-Javadoc)
         * @see BPTree.Node#split()
         */
        Node split() {
			LeafNode sibling = new LeafNode();
			
			//added -1, changed root.keynumber to this.keynumber.
			//problem, it only works on the root level. Should be looking 
			//at the child key number, not the root.
			//used to be: 
//			int from = ((root.keyNumber() + 1) / 2) -1, to = root.keyNumber();  
//			int from2 = ((chicken.keys.size()+ 1 / 2) -1);
//			int to2 = chicken.keys.size() + 1; //not sure about this +1, but whatever. 
			int from = (((keys.size()+ 1) / 2) -1);
			int to = keys.size();
			sibling.keys.addAll(keys.subList(from, to));
			sibling.values.addAll(values.subList(from, to));

			keys.subList(from, to).clear();
			values.subList(from, to).clear();

			sibling.next = next;
			next = sibling;
			return sibling;
        }
        
        /**
         * Gets the values that satisfy the given range 
         * search arguments.
         * 
         * Value of comparator can be one of these: 
         * "<=", "==", ">="
         * 
         * Example:
         *     If given key = 2.5 and comparator = ">=":
         *         return all the values with the corresponding 
         *      keys >= 2.5
         *      
         * If key is null or not found, return empty list.
         * If comparator is null, empty, or not according
         * to required form, return empty list.
         * 
         * @param key to be searched
         * @param comparator is a string
         * @return list of values that are the result of the 
         * range search; if nothing found, return empty list
         */
        List<V> rangeSearch(K key, String comparator) {
        
        	List<V> result = new LinkedList<V>();
			LeafNode node = this;
			while (node != null) {
				Iterator<K> kIt = node.keys.iterator();
				Iterator<V> vIt = node.values.iterator();
				
				while (kIt.hasNext()) {
					K keyN = kIt.next();
					V valueN = vIt.next();
					int cmp1 = keyN.compareTo(key);
					//int cmp2 = key.compareTo(key2);
					
					if((comparator.equals("<=") && cmp1 <= 0) || (comparator.equals(">=") && cmp1 >= 0) ||
							(comparator.equals("==") && cmp1 == 0)){
						result.add(valueN);
					}

//					else if ((comparator.equals("<") && cmp2 >= 0)
//							|| (policy2 == RangePolicy.INCLUSIVE && cmp2 > 0)) {
//						return result;
//						
//						//< - 2
//					}
						
				}
				
				node = node.next;
			}
			return result;

        }
        
    } 
    
    
    
    /**
     * Contains a basic test scenario for a BPTree instance.
     * It shows a simple example of the use of this class
     * and its related types.
     * 
     * @param args
     */
    public static void main(String[] args) {
        // create empty BPTree with branching factor of 3
        BPTree<Double, Double> bpTree = new BPTree<>(3);

        // create a pseudo random number generator
        Random rnd1 = new Random();

        // some value to add to the BPTree
        Double[] dd = {0.0d, 0.5d, 0.2d, 0.8d};

        // build an ArrayList of those value and add to BPTree also
        // allows for comparing the contents of the ArrayList 
        // against the contents and functionality of the BPTree
        // does not ensure BPTree is implemented correctly
        // just that it functions as a data structure with
        // insert, rangeSearch, and toString() working.
        List<Double> list = new ArrayList<>();
        
        for (int i = 0; i < 400; i++) {
            Double j = dd[i];							//dd[rnd1.nextInt(4)];
            list.add(j);
            bpTree.insert(j, j);
            System.out.println("\n\nTree structure:\n" + bpTree.toString());
        }
        
        List<Double> filteredValues = bpTree.rangeSearch(0.2d, ">=");
        System.out.println("Filtered values: " + filteredValues.toString());
    }

} 
