//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           BST Node
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

/*
 *  Class Header: Classes that use this type:  <BALST uses this Type>
 *  This class is meant to provide the nodes for the BST class. This 
 *  initializes each node and some of its factors. 
 *  
 *  @author Tejvir Mann
 */
class BSTNode<K,V> {
    
    K key;
    V value;
    BSTNode<K,V> left;
    BSTNode<K,V> right;
    int balanceFactor;
    int height;
    

    /**
     * 
     * This class initializes the node to be used in a BST. 
     * @param key - used for balancing
     * @param value - the value of the node 
     * @param leftChild - the left child of the node
     * @param rightChild - the right child of the node 
     */
    BSTNode(K key, V value, BSTNode<K,V>  leftChild, BSTNode<K,V> rightChild) {
        this.key = key;
        this.value = value;
        this.left = leftChild;
        this.right = rightChild;
        this.height = 0;
        this.balanceFactor = 0;
    }
    
    /*
     * This class the same as the one above, it isn't used. 
     * 
     * @param key 
     * @param value 
     * 
     */
    BSTNode(K key, V value) { this(key,value,null,null); }
    
}
