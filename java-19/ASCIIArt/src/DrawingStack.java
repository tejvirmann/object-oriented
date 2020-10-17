//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           DrawingStack
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

import java.util.Iterator;

/*
 * This class DrawingStack initializes the stack, and creates methods that manipulate 
 * the stack. This stack contains the push(), peek(), pop(), etc. of methods that help
 * manipulate the the specific stack that is created. 
 * 
 * @author Tejvir Mann
 * 
 */
 public class DrawingStack implements StackADT<DrawingChange>{
	private Node<DrawingChange> head; //defines the head of the stack. 
	                                //instance field of type Node<DrawingChange>, works as a pointer
	                               //which refers to the top (meaning head) of the linked stack.

	/*
	* Creates a new stack with this no argument constructor.
	 * 
	 * @param na
	 */
	public DrawingStack() {
		head = null; //sets a new stack with head equals null. 
	}
	
	/*
	 * This method pushes a node of type DrawingChange and pushes/adds it to the 
	 * DrawingStack. If the node is null, then a exception is thrown. Otherwise, the 
	 * node is set to after the head. 
	 * 
	 * @param DrawingChange element - a node of type DrawingChange
	 */
	@Override  
	public void push(DrawingChange element) throws IllegalArgumentException {
		Node <DrawingChange> newData = new Node <DrawingChange>(element, null); //create a node with the data
		
		if(element == null) {
			throw new IllegalArgumentException("WARNING: Input cannot be null.");
		}
		
		if(head == null) {  //avoids a null pointer. For first node added to the stack. 
		   head = newData;
		}
		
		else {
			newData.setNext(head); //head is top. this sets the node between the next value and head.
			head = newData;
		}
		
	}

	/*
	 * This method pops the top of the stack. It gets the value of the top node, then
	 * removes it. 
	 * 
	 * @param na
	 * @return poppedElement - the data of the top node. 
	 * 
	 */
	@Override
	public DrawingChange pop() {
		
		if(head == null) {   //avoiding null pointer. If stack is empty. 
			return null;     
		}
   
		 DrawingChange poppedElement = head.getData(); //the data of the element on top/on head.
		 head = head.getNext(); //set the value of head to next value, removing the top value on stack.
		
		return poppedElement;
	}

	/*
	 * If the Stack element is not empty, then the method returns the data of the 
	 * top of the stack. 
	 * 
	 * @param na. 
	 * @return head.getData() - the data of the top node. 
	 */
	@Override
	public DrawingChange peek() {
		
		if(head == null) {   //avoiding null pointer, if the stack is null.
			return null;
		}
		
		return head.getData();
	}

	/*
	 * This method checks if the stack is empty. 
	 * If the size of the stack is zero, then the
	 * stack is empty.
	 * 
	 * @return empty - true if the stack is empty. 
	 */
	@Override
	public boolean isEmpty() {
		boolean empty = false; 
		
		if(size() == 0) { //checks the size of the stack.
		empty = true; 
		}
		
		return empty;
	}

	/*
	 * Runs a while loop through the stack. While the stack has a next value that is not 
	 * null, the size increments until a null reference is reached. If head null, size
	 * zero. 
	 * 
	 * @return size - the number of non null nodes in the stack. 
	 * 
	 */
	@Override
	public int size() {
		int size = 0; 
		
		if(head == null) { //if the stack is empty
			size = 0;
		}
		 
		else {   //if the stack is not empty, increments the size. 
			while(head.getNext() != null) { 
				size++;  //increments size
				
			}
		}
		
		return size;
	}

	/*
	 * Returns the iterator of the stack.
	 * 
	 * @return this.iterator() - the current iterator of the stack.
	 */
	@Override
	public Iterator<DrawingChange> iterator() {
		
		return this.iterator(); 
	}
	

}
