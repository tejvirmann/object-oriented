//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           LinkedBoxList
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

import java.awt.List;

/**
 * 
 * This LinkedBoxList class creates a Linked List and contains many of the 
 * needed methods in order to manipulate the Linked List. This class initialized
 * the list. Creates the instance of head, size, and capacity. 
 * 
 * This class contains add, get, size, and many other methods needed for the program.
 * 
 * @author Tejvir Mann
 * 
 */
public class LinkedBoxList {
	
private LinkedBoxNode head; // head of this LinkedBoxList (refers to the element 
    // stored at index 0 within this list)
private int size; // number of boxes already stored in this list
private int capacity; // capacity of this LinkedBoxList
// maximum number of box elements that this LinkedBoxList
// can store

/*
 * Creates an empty LinkedBoxList with a given initial capacity
 * 
 * @param capacity - max number of elements
 */
public LinkedBoxList(int capacity) {
	
	head = null; //sets head instance to null
	this.size = 0; //sets size instance to 0
	this.capacity = capacity; //sets capacity instance to capacity
	
} 

/*
 * Returns the size of this list
 * 
 * @param na
 */
public int size() {
	return size;
}

/*
 * Return the capacity of this list
 * 
 * @param na
 * @return capacity 
 */
public int getCapacity() {
return capacity;	
	
}

/*
 * Expands the capacity of this LinkedBoxList with the specified number a of 
 * additional elements
 * 
 *  @param a - how much more the capacity should increase by.
 */
public void expandCapacity(int a) {
	this.capacity = capacity + a;
	
}

/*
 * Checks whether this LinkedBoxList is empty
 * returns true if this LinkedBoxList is empty, false otherwise
 * 
 * @return TorF - true of the head is null and the list is empty. 
 */
public boolean isEmpty() {
	
	boolean TorF = false; 
	
	if (head == null) {
		TorF = true;
	}
	
	return TorF;
}

/*
 * Checks whether this LinkedBoxList is full
 * Returns true if this list is full, false otherwise
 * 
 * @returns TorF - if size is capacity then TorF is true. 
 */
public boolean isFull() {
	boolean TorF = false;
	
	if(size == capacity) {
		TorF = true;
	}
	
	return TorF;
	
}

/*
 * Adds a new box into this sorted list
 * Throws IllegalArgumentException if newBox is null
 * Throws IllegalStateException if this list is full
 * 
 * @param newBox - the box node that needs to be added to the linked list.
 * 
 */
public void add(Box newBox) throws IllegalArgumentException, IllegalStateException {
	
	if(newBox == null) {
		throw new IllegalArgumentException("WARNING: Create a Box First!");
	}
	
	if(isFull()) {
		throw new IllegalStateException("WARNING: Storage List Full!");
	}
	
	int i = 0;
	
		LinkedBoxNode newNode = new LinkedBoxNode(newBox);  //the new node to be added
		LinkedBoxNode runner = head; //the runner that runs through the list
		LinkedBoxNode nextRunner = head; //a second runner one index behind runner

	if (head == null) {  //for the first node of the list
		head = newNode;  //sets head to newBox
		size++; //increments size
	}
	
	else {
	
	while(runner != null) {  
		
		if(newBox.compareTo(runner.getBox()) > 0) {// if the new node is bigger than the next one  
			if(i == 0) {   //if the newNode needs to be added to head, since largest weight.
			newNode.setNext(head);
			this.head = newNode;
		    break;
			}
		
	       else {
			newNode.setNext(nextRunner.getNext());	//adds a new node in a specific position on a sorted list
			nextRunner.setNext(newNode); 
			break;
		   }
		}
		
		if(runner.getNext() == null) { //if runner gets to the end of the list
			if(newBox.compareTo(runner.getBox()) == 0) { //increments i if newBox is equal to last node
				i++;
			}
			if(newBox.compareTo(runner.getBox()) < 0) { //increments i if newBox is less than less node
				i++;
			}
			
			break;
		}
		
		runner = runner.getNext();  //advances the runner pointer by one
		i++;
		
		if(i > 1 ) {       // nextRunner is the pointer trailing one behind the runner pointer. 
		nextRunner = nextRunner.getNext(); //This happens because nextRunner starts incrementing after i>1. 
		}
	}
	   
	if((runner.getNext() == null) && (i != 0)) { //if the pointer is at the end of the 
		runner.setNext(newNode);                         //list, and the new box is equal or less.
	}

	this.size++; //increments the size
 }
}

/*
 * Checks if this list contains a box that matches with (equals) a specific box object
 * Returns true if this list contains findBox, false otherwise.
 * 
 * @param findBox - the box needed to be checked
 * @return TorF - returns true of the findBox box is in the list. 
 * 
 */
public boolean contains(Box findBox) {
	boolean TorF = false; 
	
	LinkedBoxNode runner = head; // the runner for the linked list
	
	if(runner != null) {
	
	while(runner.getNext() != null) { 
		if(findBox.equals(runner.getBox())) { 
		TorF = true;
		break;
		}
		runner = runner.getNext(); //advances the runner
	}

	if(runner.getNext() == null) { //when the next reference is null
		if(findBox.equals(runner.getBox())) { //checks if find box equals runner
			TorF = true;
		}
	  }
	
	
	}
	
	return TorF; 
	
	
}

/*
 * Returns a box stored in this list given its index
 * Throws IndexOutOfBoundsException if index is out of the range 0..size-1 
 * 
 * @param index - the position of the Box needed to be retrieved
 * @return Box - the box that is at index. 
 */
public Box get(int index) throws IndexOutOfBoundsException {
	
	if(index > size && index<0) {
		throw new IndexOutOfBoundsException("Index should be in the right range, from get method");
	}
	
	LinkedBoxNode runner = head;
	
	if(isEmpty()) {  //if the list is empty, runner == null
		return null;
	}
	
	else {
		for(int i = 0; i<index; i++) { //runs through a for loop until the desired box is found
			runner = runner.getNext();
	  }	
	}
   return runner.getBox();
   
}

/*
 * Removes a returns the box stored at index from this LinkedBoxList 
 * Throws IndexOutOfBoundsException if index is out of bounds. index should be in 
 * the range of [0.. size()-1]
 * 
 * @param index - the index that the box should be removed from
 * @return Box - the box that was removed
 */
public Box remove(int index) throws IndexOutOfBoundsException {
	
	
	if(index > size && index<0) { //if index is incorrect
		throw new IndexOutOfBoundsException("Index should be within the right range");
	}
	
	LinkedBoxNode runner = head;  //the runner
	LinkedBoxNode removedBox = head;
	int i = 0;

		if(index == 0) { //if you remove the first node
		head = head.getNext(); 
		}
		
		else {   
		for (i = 1; i < index; i++) { 
			runner = runner.getNext(); //advances the runner by one node
		}
		
	    removedBox = new LinkedBoxNode(runner.getNext().getBox()); //sets the box to remove
        runner.setNext(runner.getNext().getNext()); //removes the node at the index.
		}
		 
		size--; //decrements size

	  return removedBox.getBox();
} 

/*
 * Removes all the boxes from this list
 * 
 * @param na
 */
public void clear() {
	
	this.head = null;  //sets head to null eliminating all of the nodes
	size = 0;
	
}

/**
 * Returns a String representation for this LinkedBoxList
 * 
 * Returns a String representation of the state and content of this LinkedBoxList
 * An example of source code for this method is provided you in the next paragraph
 *
 */
@Override
public String toString() { 
  StringBuilder result = new StringBuilder(); // creates a StringBuilder object
  String newLine = System.getProperty("line.separator");
  result.append("------------------------------------------------"+newLine);   
  if (!isEmpty()) {
    LinkedBoxNode runner = head;
    int index = 0;
    // traverse the list and add a String representation for each box
    while (runner != null) {
      result.insert(0,
          "Box at index " + index + ": " + runner.getBox().getWeight() + " lbs" + newLine);
      runner = runner.getNext();
      index++;
    }
    result.insert(0, "Box List Content:"+ newLine);
  }
  result.insert(0,"List size: " + size + " box(es)." + newLine);
  result.insert(0,"Box List is empty: " + isEmpty() + newLine);
  result.insert(0,"------------------------------------------------"+ newLine);
  return result.toString(); 
}





}
