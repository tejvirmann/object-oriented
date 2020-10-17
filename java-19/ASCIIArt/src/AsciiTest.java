//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           AsciiTest
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
 * This class is to test the strength of the DrawingStack class, The Canvas class, 
 * and many others to check correct implementation.
 * 
 * @author Tejvir Mann
 * 
 */
public class AsciiTest {
	
	/*
	 * This method checks if the DrawingStack class correctly pushes and peeks. 
	 * 
	 * @return TorF - true if the stack is peek method contains the right node, otherwise false
	 */
	public static boolean testStackPushPeek(){ 
		boolean TorF = false; 
		
		try {
	    //create a new stack of type DrawingChange
		DrawingStack stack = new DrawingStack(); 
		
		DrawingChange element = new DrawingChange(5,5,'a','a'); //creates new nodes to be pushed to a stack.
		DrawingChange element2 = new DrawingChange(6,5,'a','a'); 
		DrawingChange element3 = new DrawingChange(7,5,'a','a'); 
		
		//push
		stack.push(element); //adds each element 
		stack.push(element2);
		stack.push(element3);
		
		
		//peek
		if(stack.peek().x == 7) { //checks which element is on top
			TorF = true;
		}
		return TorF;
		}
		
		catch(RuntimeException e) {  //if an exception is thrown than the test returns false.
			return false;
		}
	}

	/*
	 * Tests if the DrawingStack class correctly throws an exception if element is null.
	 * 
	 * @return TorF - true if an exception is caught, else false. 
	 */
	public static boolean testDrawingStack() {
		boolean TorF = false; //if the exception is not caught. 
		
		try {
		
		DrawingStack stack = new DrawingStack(); //creates a new stack
		DrawingChange element = null; //a null node
		
		stack.push(element);
		}
		
		catch (IllegalArgumentException e) { //catches potential exception
		TorF = true;
		}
		
		catch (RuntimeException e) {
		TorF = true;
		}
		
		return TorF;
	}

	/*
	 * Tests if the Canvas class is implemented correctly, checking the draw, undo, and 
	 * redo methods. 
	 * 
	 * @return TorF - true if the canvas contains the char value, false otherwise. 
	 * 
	 */
	public static boolean canvasTest1(){
		boolean TorF = false; 
		
		try {
		Canvas newCanvas = new Canvas(10,10); //creates new canvas
	
		
		newCanvas.draw(1, 2, 'c');
		
		newCanvas.draw(3,4, 'e');
		newCanvas.draw(5, 5, '9');
		newCanvas.undo();
		newCanvas.redo();
		newCanvas.undo();
		newCanvas.draw(4, 4, 'e');
		newCanvas.draw(4, 4, 'c');
		
		if(!(newCanvas.toString().contains("9"))) { //check if the canvas contains this char.
			TorF = true;
		}
	}
		
		catch(RuntimeException e) {
			TorF = false;
		}
		
		return TorF; 
	}
	
	/*
	 * Tests if the Canvas class is implemented correctly, checking the draw, undo, and 
	 * redo methods. 
	 * 
	 * @return TorF - true if the canvas contains the char value, false otherwise. 
	 * 
	 */
	public static boolean canvasTest2(){
		boolean TorF = false; 
		
		try {
		Canvas newCanvas = new Canvas(90,90); //creates new canvas
	
		//draw, redo, and undo methods
		newCanvas.draw(3,4, 'f');
		newCanvas.undo();
		newCanvas.draw(5, 5, '9');
		newCanvas.undo();
		newCanvas.redo();

		if(newCanvas.toString().contains("9")) { //check if the canvas contains this char.
			TorF = true;
		}
	}
		catch(RuntimeException e) {
			TorF = true;
		}
		
		return TorF; 
	}
	
	/*
	 * This runs all of your other tests, and returns true if they all pass.
	 * 
	 * @return TorF - true if all the tests return true.
	 */
	public static boolean runStackTestSuite() {
	boolean TorF = false; 
	
	//this runs all of your other tests, and returns true if they all pass. 
	if(canvasTest1() && canvasTest2() && testDrawingStack()) {
		TorF = true; 
	}
		
	return TorF; 
	}
	
	/*
	 * This main method runs all of the test method. This also prints the results of 
	 * each test. 
	 */
	 public static void main(String[] args) {
		   System.out.println("testStackPushPeek(): " + testStackPushPeek());
		   System.out.println("runStackTestSuite(): " + runStackTestSuite());
	 }
}
