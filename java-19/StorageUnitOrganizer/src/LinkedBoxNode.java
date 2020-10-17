//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           LinkedBoxNode
// Files:           NA
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
 * The LinkedBoxNode class is needed to create a node containing box( the 
 * data for the node) and next, which is the reference to the next node. 
 * This class contains two constructors to initialize an instance of the node. 
 * 
 * This class also contains setters and getters for the box and next variables
 * 
 * @author Tejvir Mann
 */
public class LinkedBoxNode {

	
	private Box box; // box that represents the data for this Linked node
	private LinkedBoxNode next; // reference to the next Linked Box Node

	// constructors
	
	/*
	 *  Creates a new LinkedBoxNode object with a given
	 *  box and without referring to any next LinkedBoxNode
	 *  
	 *  @param box - a box that contains some unique data for the node
	 */
	public LinkedBoxNode(Box box) {
	this.box = box;
	
	} 
	
	/*
	 * Creates a new LinkedBoxNode 
	 * object and sets its instance fields box and next to the specified ones
	 * 
	 * @param box - a box that contains some unique data for the node
	 * @param next - a reference for a node that refers to the next one. 
	 */
	public LinkedBoxNode(Box box, LinkedBoxNode next){
	this.box = box;
	this.next = next;

	} 
	
	// getters and setters methods
	
	/*
	 * Getter for box
	 * 
	 * @return box
	 */
	public Box getBox() {
		return box;
	}
	
	/*
	 * Setter for box
	 * 
	 * @param box
	 */
	public void setBox(Box box) {
		this.box = box;
	}
	
	/*
	 * Getter for Next
	 * 
	 * @return next
	 */
	public LinkedBoxNode getNext() {
		return next;
	}
	
	/*
	 * Setter for Next
	 * 
	 * @param next
	 */
	public void setNext(LinkedBoxNode next) {
		this.next = next;
	}	
	
}
