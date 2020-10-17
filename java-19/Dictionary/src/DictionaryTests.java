//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           DictionaryTests
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
 * This class tests the correctness and implementation of the program
 * 
 * @author Tejvir Mann
 */
public class DictionaryTests {
	
	/*
	 * This main method runs through all the test methods, and prints the results. True 
	 * means that the test passes otherwise, false.
	 */
	public static void main(String[] args) {
		System.out.println("testBST1(): " + testBST1());
		System.out.println("testBST2(): " + testBST2());
		System.out.println("testBST3(): " + testBST3());
		System.out.println("testBST4(): " + testBST4());
		System.out.println("testDictionaryWord(): " + testDictionaryWord());
	}
	
	/*
	 * This method tests how correct the add new word, and search for a word is. 
	 * 
	 * @return TorF - true if the test passes, otherwise false. 
	 */
	public static boolean testBST1(){
		boolean TorF = false;
		
		DictionaryBST dictionary = new DictionaryBST();
		String s = "zebra";
	
		dictionary.addWord("apple", "a red fruit");
		dictionary.addWord("monkey", "my brother");
		dictionary.addWord("zebra", "a white horse with stripes.");
		dictionary.addWord("gun", "a spitting rock.");
		
		if((dictionary.lookup(s)).equals("a white horse with stripes.")) { //checks if 
			TorF = true;                                            //returns right definition
		}
		
		return TorF;
	}
	
	/*
	 * This method tests how correct the add new word, and tests
	 * height, size, and getAllWords methods.  
	 * 
	 * @return TorF - true if the test passes, otherwise false. 
	 */
	public static boolean testBST2(){
		boolean TorF = false;
		boolean TorF2 = false;
		boolean TorF3 = true;
		boolean TorF4 = false;
		
		DictionaryBST dictionary = new DictionaryBST();
		ArrayList<String> allWords = new ArrayList<String>();
		
		allWords.add("apple");
		allWords.add("gun");
		allWords.add("monkey");
		allWords.add("zebra");
	
		dictionary.addWord("apple", "a red fruit");
		dictionary.addWord("monkey", "my brother");
		dictionary.addWord("zebra", "a white horse with stripes.");
		dictionary.addWord("gun", "a spitting rock.");
		
		if(dictionary.height() == 3) {
			TorF = true;
		}
		
		if(dictionary.size() == 4) {
			TorF2 = true; 
		}
		
		for(int i = 0; i < dictionary.size(); i++) {
			if(!(dictionary.getAllWords().get(i).equals(dictionary.getAllWords().get(i)))){
				TorF3 = false;  //compares the array lists and if they are same, then true.
			}
			
		}
		
		if(TorF && TorF2 && TorF3) { //if all true
			TorF4 = true;
		}
	
		return TorF4;
	}
	
	/*
	 * Tests the add word method, and sees if it returns false if a word is added again.
	 * 
	 * @return TorF4 - if all the other tests pass. Else false.
	 */
	public static boolean testBST3(){
		boolean TorF = false;
		
		DictionaryBST dictionary = new DictionaryBST();
	
		
		dictionary.addWord("apple", "a red fruit");
		dictionary.addWord("monkey", "my brother");
		dictionary.addWord("zebra", "a white horse with stripes.");
		dictionary.addWord("gun", "a spitting rock.");
		dictionary.addWord("apple", "a orange fruit");
		
		if(dictionary.addWord("apple", "a orange fruit") == false) {
			TorF= true;
		}
		return TorF;
	}
	
	/*
	 * This method tests when the person tries to look up in an empty dictionary. If so, 
	 * then a NoSuchElementException is thrown. 
	 * 
	 */
	public static boolean testBST4(){
		boolean TorF = false;
		String s = "mercy"; 
		
		DictionaryBST dictionary = new DictionaryBST();
	
		try { 
			dictionary.lookup(s);	
		}
		
		catch (NoSuchElementException e) {
			TorF = true;
		}
		
		return TorF; 
	}
		
	/*
	 * This tests if the dictionary word node class throws an exception if 
	 * word is null.
	 * 
	 * @returns TorF - true if the exception is thrown, else false. 
	 */
	public static boolean testDictionaryWord(){
		boolean TorF = false; 
		
		try {
		DictionaryWord addNode = new DictionaryWord(null, null);
		}
		
		catch (IllegalArgumentException e) {
			TorF = true;
		}
		
		return TorF; 
	}
	
}
	

	

