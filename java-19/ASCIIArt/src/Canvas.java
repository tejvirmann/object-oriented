//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Canvas
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
    * This Canvas class initializes a canvas, setting its width, height, the drawingArray, undoStack
 * and redoStack. This class also creates methods that the user can use to manipulate the Canvas, 
 * such as undo, redo, draw, printHistory, and toString(). 
 * 
 * @author Tejvir Mann
 * 
 */
 public class Canvas {
	
	private final int width;  // width of the canvas
	private final int height; // height of the canvas

	private char [][] drawingArray; // 2D character array to store the drawing

	private final DrawingStack undoStack; // store previous changes for undo
	private final DrawingStack redoStack; // store undone changes for redo
	
	/*
	 * This canvas constructor initializes a canvas, and sets the width, height,
	 * undoStack, redoStack, and drawingArray. If the width or height are negative 
	 * then an exception is thrown. 
	 * 
	 * After this constructor is created, a new Canvas object is created, and 
	 * can be manipulated. 
	 * 
	 * A Canvas is initially blank (use the space ' ' character)
	 * 
	 * @param width - the width of the canvas
	 * @param height - the height of the canvas
	 * 
	 */
	public Canvas(int width, int height) {
	
	if(width <= 0 || height <=0) {  //if height or width negative, then exception is thrown. 
		throw new IllegalArgumentException("Both Width and Height must be more than 0.");
	}
	
	this.width = width;  //sets width of canvas
	this.height = height;	//sets height of canvas
	this.undoStack = new DrawingStack(); //creates a new undo stack
	this.redoStack = new DrawingStack(); //creates a new redo stack
	this.drawingArray = new char[height][width]; //creates a new drawingArray
	} 

	/*
	 * This method draws a character at the given position. This method should throw an 
	 * IllegalArgumentException if the drawing position is outside the canvas. Overwrites the position
	 * if a position is already marked. After making a new change, adds a matching DrawingChange 
	 * to the undoStack so that we can undo if needed. After making a new change, the redoStack 
	 * should be empty.
	 * 
	 * @param row - the y value of the canvas. 
	 * @param col - the x value of the canvas.
	 * @param c - the char the user wants to draw.
	 * 
	 */
	public void draw(int row, int col, char c) { 
    	
    	char prevCharS = '_';
	 
    if((width<row) || (height<col)) { //if user input exceeds the height of width. Throws exception. 
    	throw new IllegalArgumentException("Either Row or Column height exceeds width or height.");
    }
    
    if(drawingArray[row][col] != '\u0000') { //if the position is empty, and contains '_', then sets 
 	    if(drawingArray[row][col] != '_') {                       //the previous Char to a that char. 
 		    prevCharS = drawingArray[undoStack.peek().x][undoStack.peek().y]; //sets the prevChar
 	}
    }
  
    DrawingChange undoElement = new DrawingChange(row, col, prevCharS, c); //sets a new node and its values.
    drawingArray[row][col] = c; //sets the new char to the spot on the array.                                     
    undoStack.push(undoElement); //adds a copy of the node to the undoStack, in case the user wants to undo. 
    
      while(redoStack.peek() != null) { //whenever the user decides to draw again, then they aren't able 
    	  redoStack.pop();   //to redo, so the redoStack should be cleared. 
      }
}  

	/*
	* The undo method takes the top node in the undo stack, and sets the point in drawingArray
	 * to the data in the undoStack. If the undoStack is empty, then the method returns false, and 
	 * a error message is displayed. 
	 * 
	 * The top node data is then pushed to the redoStack, and pops the value in the undoStack.
	 * 
	 * Undo the most recent drawing change. Return true if successful. False otherwise.
	 * An undone DrawingChange should be added to the redoStack so that we can redo if needed.
	 * 
	 * @param none
	 */
    public boolean undo() { 
 	boolean TorF = true; //false if the undoStack is empty
 	
 	if(undoStack.peek() == null) { //if the undoStack is null, then return false. 
 		return false; 
 		}

 	drawingArray[undoStack.peek().x][undoStack.peek().y] = undoStack.peek().prevChar; //sets the value of 
 	                                                                    //drawingArray to the prevChar in undoStack.
 	redoStack.push(undoStack.pop()); //pushes the node in that undoStack to the redostack if the user wants to
 	                                 // This clears the value from the undo, and adds it to redo. 

 	return TorF;	
}   
    
    /*
    * Redo's the most recent undone drawing change. Return true if successful. False otherwise.
     * A redone DrawingChange should be added (back) to the undoStack so that we can undo again if needed.
     * 
     * @param NA
     */
    public boolean redo() { 
	boolean TorF = true; 
	
	if(redoStack.peek() == null) { //if the redoStack is null, it is returned as false.
	    return false; 
	}
	
	drawingArray[redoStack.peek().x][redoStack.peek().y] = redoStack.peek().newChar; //sets the value of drawingArray 
    undoStack.push(redoStack.pop()); //adds to undo, clears redo.                    //from redo.
    
    return TorF;
}   

    /* 
     * Returns a printable string version of the Canvas.
     * 
     * Format example: [_ is blank. Use System.lineSeparator() to put a newline character between rows]
     * X___X
     * _X_X_
     * __X__
     * _X_X_
     * X___X
     * 
     * @param NA
     * 
     */
    public String toString() { 
    String drawing = ""; //string representation of the drawing.
    for(int i = 0; i < drawingArray.length; i++) {   //two for loops that run through the positions in the array.
    	for(int j = 0; j <drawingArray[0].length; j++) {  
    		if(drawingArray[i][j] == '\u0000') {
    			drawingArray[i][j] ='_';   //goes through each row, and if it is empty, sets the char. 
    		}
            drawing += Character.toString(drawingArray[i][j]); //updates the string each time the inner for loops iterates.
         }
         drawing += System.lineSeparator(); //after the end of each row, creates a new line in the string. 
    		
    }
	
    return drawing;
    
 }

    /*
     * prints the string of the drawing.
     * 
     * @param na
     */
    public void printDrawing() {
    System.out.println(toString()); //prints the string version of the canvas. 
    }
}
