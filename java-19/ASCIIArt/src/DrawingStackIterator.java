//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           DrawingStackIterator
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
import java.util.NoSuchElementException;

/**
 * Iterator implementation for Stack
 * 
 * @param  type of element returned by this Iterator
 */
public class DrawingStackIterator implements Iterator<DrawingChange> {
  Node<DrawingChange> current; // reference to the current element in the iteration

  /**
   * Creates a DrawingStackIterator that iterates over an iteration of elements of type DrawingChange
   * @param head reference to the first node in the iteration 
   */
  public DrawingStackIterator(Node<DrawingChange> head) {
    //Sets current instance field appropriately
	  this.current = head;
  }
  
  /**
   * Tests whether the iterator has a next object or not.
   * 
   * @return true if there are further objects in the list, false otherwise
   */
   @Override
   public boolean hasNext() {
	
	boolean TorF = false; 
	
	if(current.getNext() != null) { //if there is another value next. 
	TorF = true;
	return TorF;
	}
	
	return TorF;
}

/**
   * Returns the next object in the iterator.
 *
 * @return next element in the list
 * @throws NoSuchElementException if there are no further elements in the list
 */
   @Override
   public DrawingChange next() {
	
    if (!hasNext()) { //if the stack is null.
        throw new NoSuchElementException("No more elements in the list");
    }
      
    return current.getNext().getData(); // post-increment index, so it is ready for future call to next
  
}



}