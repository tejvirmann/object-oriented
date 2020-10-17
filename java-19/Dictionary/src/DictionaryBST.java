//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           DictionaryBST
// Files:           DictionaryTests, Dictionary, DictionaryWords, DictionaryBST,
//                  DictionaryDriver
// Course:          CS300, Summer, 2019
//
// Author:          Tejvir Mann
// Email:           tsmann@wisc.edu
// Lecturer's Name: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: NA
// Partner Email: NA
// Partner Lecturer's Name: NA
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   __x_ Write-up states that pair programming is allowed for this assignment.
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

import java.util.ArrayList;
import java.util.NoSuchElementException;

/*
 * This class DictionaryBST creates a BST, and implements the dictionary. 
 * This class also implements a bunch of methods needed to manipulate the 
 * BST and find important info. 
 * 
 * @author Tejvir Mann
 */
public class DictionaryBST implements Dictionary {

	private DictionaryWord root; // represents the root of this BST.
	
	/*
	 * Creates an empty dictionaryBST.
	 * 
	 */
	  public DictionaryBST() { 
		  root = null;  
	  }

	  // Methods defined in the Dictionary interface
	  
	  /*
	   * Checks if the BST is empty. 
	   * 
	   * @returns true if BST is empty, otherwise returns false. 
	   */
	  public boolean isEmpty() { 
		  if(root == null) {  //if the root is empty, then list  
			  return true;   //is empty, and should return true.
		  }
		  
		  return false;  
	  }
	 
	  /**
	   * adds a word definition (word and meaning provided) to this dictionary
	   * 
	   * @param word of the word definition to be added to this dictionary
	   * @param meaning of the word definition to be added to this dictionary
	   * @return true if the word was successfully added to the dictionary and false if the
	   *  word was
	   *         already in this dictionary
	   * @throws IllegalArgumentException if either word or meaning is null or an empty String
	   */
	  public boolean addWord(String word, String meaning) {   

		if(word == null) {
				throw new IllegalArgumentException ("WARNING: Word cannot be null.");
			}
			
	    if(meaning == null) {
				throw new IllegalArgumentException ("WARNING: Meaning cannot be null.");
			}
			
	    if(word.equals("")) {
				throw new IllegalArgumentException ("WARNING: Word cannot be an empty String");
			}
			
	    if(meaning.equals("")) {
				throw new IllegalArgumentException ("WARNING: Meaning cannot be an empty String");
			}
		  
	    if (isEmpty()) { //if the BST is empty, then the word node is added to the root.                
	        root = new DictionaryWord(word, meaning);  
	        return true;  
	    }
	    
	   DictionaryWord newNode = new DictionaryWord(word,meaning);  //else, a new node is created. 
	   
	   
	   return addWordHelper(newNode, root); // start recursion of addHelper to add node to 
	   //right spot.  	
	   
	   
	  }
	 
	  /**
	   * Looks up for a meaning of a given word s if present in this dictionary
	   * @param s a word
	   * @return meaning of the provided string s if present in this dictionary
	   * @throws NoSuchElementException if the word s was not found in this dictionary
	   */  
	  public String lookup(String s) { 
		  String word = "";
		  boolean found = false; 
		  
		  if(isEmpty()) {
			  throw new NoSuchElementException ("Word not found in empty dictionary");
		  }
		  
		  word = lookupHelper(s, root);
		  
		  if(word.equals("")) {
			  found = true;
		  }
		  
		  if(found == true) {
			  throw new NoSuchElementException ("Word not found in dictionary");
		  }
		  
		  return word;
	  }
	 
	  /*
	   * @returns int - the size of the BST
	   */
	  public int size(){
		  return(sizeHelper(root)); 
	  }
	  
	  // Public methods not defined in the Dictionary interface
	  
	  /**
	   * Computes and returns the height of this dictionaryBST, as the number of nodes 
	   * from root to the deepest leaf DictionaryWord node.
	   * 
	   * @return the height of this Binary Search Tree counting the number of DictionaryWord nodes
	   */
	  public int height() { 
		  
	  if(this.isEmpty()){
		 return 0;
      }
	  
	  else{
		 return heightHelper(root);
		    }    
	  }
	  
	  /**
	   * Returns all the words within this dictionary sorted from A to Z
	   * 
	   * @return an ArrayList that contains all the words within this dictionary sorted in 
	   *         the ascendant order
	   */
	  public ArrayList<String> getAllWords() { 
		 
		  return getAllWordsHelper(root);  
	  }

	  // Recursive private helper methods
	  // Each public method should make call to the recursive helper method with the
	  // corresponding name
	  
	  /**
	   * Recursive helper method to add newWord in the subtree rooted at node
	   * 
	   * @param newWordNode a new DictionaryWord to be added to this dictionaryBST
	   * @param current the current DictionaryWord that is the root of the subtree where 
	   *        newWord will be inserted
	   * @return true if the newWordNode is successfully added to this dictionary, false otherwise
	   */
	  private static boolean addWordHelper(DictionaryWord newWordNode, DictionaryWord current) { 
		  
	  int compare = newWordNode.getWord().toLowerCase().compareTo(current.getWord().toLowerCase());
		  
		  if (compare == 0) {
		        return false;
		    }

	      if (compare < 0) {
	            if (current.getLeftChild() != null) {
	                return addWordHelper(newWordNode, current.getLeftChild());
	                		
	            } else {
	            	current.setLeftChild(newWordNode); 
	                return true;
	            }
	      }
	      
	      else if (compare > 0) {
	            if (current.getRightChild() != null) {
	                return addWordHelper(newWordNode, current.getRightChild());
	                		
	            } else {
	            	current.setRightChild(newWordNode); 
	                return true;
	            }
	      }
	      
	      else {
	    	  return false;
	      } 
	  }

	  /**
	   * Recursive helper method to lookup a word s in the subtree rooted at current
	   * 
	   * @param s String that represents a word
	   * @param current pointer to the current DictionaryWord within this dictionary
	   * @return the meaning of the word s if it is present in this dictionary
	   * @throws NoSuchElementException with significant error message if is not found in 
	   * this dictionary
	   */
	  private static String lookupHelper(String s, DictionaryWord current) { 
		  String definition = "";
		  
		  int compare = s.toLowerCase().compareTo(current.getWord().toLowerCase());
		  
		  if (compare == 0) {
			   definition = current.getMeaning();
               return definition;
		    }

		  else if (compare < 0 && (current.getLeftChild() != null)) {
	            if (current.getLeftChild().getWord() != s) {
	                return lookupHelper(s, current.getLeftChild());
	                		
	            } else {
	            	definition = current.getLeftChild().getMeaning();
                    return definition;
	            }
	      }
	      
	      else if (compare > 0 && (current.getRightChild() != null)) {
	    	    if (current.getRightChild().getWord() != s) {
	                return lookupHelper(s, current.getRightChild());
	                		
	            } else {
	            	definition = current.getRightChild().getMeaning();
                  return definition;
	            }
	      }
	      
	      else {
				  throw new NoSuchElementException ("Word not found in dictionary");
	      }   
	  }

	  /**
	   * Recursive helper method that returns the number of dictionary words stored in 
	   * the subtree rooted at current
	   * 
	   * @param current current DictionaryWord within this dictionaryBST
	   * @return the size of the subtree rooted at current
	   */
	  private static int sizeHelper(DictionaryWord current) { 
		  
		  
		  if (current == null) {
			  return(0); 
		  }
		  else { 
		    return(sizeHelper(current.getLeftChild()) + 1 + sizeHelper(current.getRightChild()));  
		  }
		  
	  }

	  /**
	   * Recursive helper method that computes the height of the subtree rooted at current
	   * 
	   * @param current pointer to the current DictionaryWord within this DictionaryBST
	   * @return height of the subtree rooted at current counting the number of   
	   * DictionaryWord nodes from the current node to the deepest leaf in the subtree
	   * rooted at current
	   */
	  private static int heightHelper(DictionaryWord current) { 
		  int heightLeft = 0;
		  int heightRight = 0;
		   
		  if(current.getLeftChild()!=null) {
		        heightLeft = heightHelper(current.getLeftChild());
		  }
		  
		  if(current.getRightChild()!=null) {
		        heightRight = heightHelper(current.getRightChild());
		  }
		    
		  if(heightLeft > heightRight){
		        return heightLeft+1;
		    }
		    
		  else{
		        return heightRight+1;
		    }  
	  }

	  /**
	   * Recursive Helper method that returns a list of all the words stored in 
	   * the subtree rooted at current sorted alphabetically from A to Z
	   * 
	   * @param current pointer to the current DictionaryWord within this dictionaryBST
	   * @return an ArrayList of all the words stored in the subtree rooted at current
	   */
	  private static ArrayList<String> getAllWordsHelper(DictionaryWord current) { 
		
		 ArrayList<String> allWords = new ArrayList<String>();
		  
	     if (current == null) {
	            return allWords;
	     }
	     
	     if (current.getLeftChild()!= null) {
	     allWords.addAll(getAllWordsHelper(current.getLeftChild()));
	     }
	     allWords.add(current.getWord());
	     
	     
	     if (current.getRightChild() != null) {
	     allWords.addAll(getAllWordsHelper(current.getRightChild()));
	     }
		  
		  return allWords;
 
	  }
}
