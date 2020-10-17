//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           DrawingChange
// Files:           AsciiArt, AsciiTest, Canvas, DrawingChange, DrawingStack, DrawingStackIterator, Node, StackADT
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
 * This class creates a node of that contains x, y, prevChar, and newChar data. The way 
 * that this node is created is through a constructor. 
 * 
 * @author Tejvir Mann
 */
public class DrawingChange {
	
	public final int x; // x coordinate for a change
	public final int y; // y coordinate for a change
	public final char prevChar; // previous character in the (x,y)
	public final char newChar; // new character in the (x,y)
	
	/*
	 * 
	 * This constructor initializes a new node with four pieces of data. 
	 * 
	 * @param x - the x position
	 * @param y - the y position
	 * @param prevChar - the previous character
	 * @param newChar - the new character
	 */
	public DrawingChange(int x, int y, char prevChar, char newChar) { 
		this.x = x;  //sets x position
		this.y = y; //sets the y position
		this.prevChar = prevChar; //sets the prev char.
		this.newChar = newChar; //sets the new char.
	}

}
