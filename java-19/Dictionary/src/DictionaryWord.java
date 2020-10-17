//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           DictionaryWord
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

/*
 * This class is NOT a generic class and it models a binary node of a 
 * Binary Search Tree. Each binary node (aka instance of DictionaryWord) 
 * defines its data consisting of a pair of word and its meaning, and a link 
 * to each child (right and left) of the node. 
 * 
 * @author Tejvir Mann
 * 
 */
public class DictionaryWord { 
	
	private final String word; // word that represents the search key for this dictionary word
	private final String meaning; // The meaning of the word that this dictionary node defines
	private DictionaryWord leftChild;  // The leftChild of the the current WebPageNode
	private DictionaryWord rightChild; // The rightChild of the the current WebPageNode

	/*
	 * The following should be the only constructor for this class
	 * Creates a new dictionary word with the provided word and its meaning pair
	 * Throws IllegalArgumentException when the word or meaning are either references to an empty
	 * string or null references. The thrown exception should include a significant error message 
	 * describing which of these problems was encountered.
	 * 
	 * @param word - word that represents the search key for this dictionary word
	 * @param meaning - The meaning of the word that this dictionary node defines
	 */
	public DictionaryWord(String word, String meaning) { 
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
		
		this.word = word;
		this.meaning = meaning;	
	}
	  
	/*
	 * Getter for the left child of this dictionary word
	 */
    public DictionaryWord getLeftChild() {
		return leftChild;
	}
	 
    /*
     * Setter for the left child of this dictionary word
     */
	public void setLeftChild(DictionaryWord leftChild) {
		this.leftChild = leftChild;
	}

	/*
	 * Getter for the right child of this dictionary word
	 */
	public DictionaryWord getRightChild() {
		return rightChild;
	}

	/*
	 * Getter for the right child of this dictionary word
	 */
	public void setRightChild(DictionaryWord rightChild) {
		this.rightChild = rightChild;
	}

	/*
	 * Getter for the word of this dictionary word
	 */
	public String getWord() {
		return word;
	}

	/*
	 * Getter for the meaning of the word of this dictionary word
	 */
	public String getMeaning() {
		return meaning;
	}

	/**
	 * Returns a String representation of this DictionaryWord. This String is formatted as
	  * follows. "word: meaning" 
	  * 
	  * @return String representing a dictionary word and its meaning
	  */
	public String toString() { 
	  return this.word + ": " + this.meaning;
	}
	
	

}
