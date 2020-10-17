//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Button
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

  /**
   * 
   * The Button Class a super class for any Button that can be added to a PApplet application
   * from the ClearPark button, and the Tiger/Deer buttons. 
   * 
   * This class implements the ParkGUI interface.
   * 
   * This class is needed to draw the Tiger and Deer images onto the GUI. The class
   * uses the draw() method to draw, has a Button constructor, then has methods 
   * including isMouseOver(), mousePressed(), and mouseReleased() to be able to 
   * move Tiger and Deer PNG's around.
   * 
   * @author Tejvir Mann
   * 
   */
public class Button implements ParkGUI {
  private static final int WIDTH = 85; // Width of the Button
  private static final int HEIGHT = 32; // Height of the Button
  protected JunglePark processing; // PApplet object where the button will be displayed
  private float[] position; // array storing x and y positions of the Button with respect to 
                            // the display window 
  protected String label;   // text/label that represents the button

  /**
   * This Button constructor is needed to initialize many of the needed variables 
   * needed in order for the button class to work. The constructor initializes 
   * processing, positions, and label. 
   * 
   * @param float x - for the x position
   * @param float y - for the y position
   * @param JunglePark processing - the specific PApplet object where the button will be displayed
   * 
   */
  public Button(float x, float y, JunglePark processing) {
    this.processing = processing;  //initializes processing
    this.position = new float[2];  //initializing position, for x and y
    this.position[0] = x;  //
    this.position[1] = y;
    this.label = "Button"; //sets the value of label.
  }

	/*
   * The draw() method draws the button at the specific positions that are accessed through
   * processing. The draw method uses many helper methods such as .fill(), .rect(), .fill(),
   * and .text()
   * 
	 * @param none
	 */ 
  @Override
  public void draw() {
    this.processing.stroke(0);// set line value to black
    if (isMouseOver())
      processing.fill(100); // set the fill color to dark gray if the mouse is over the button
    else
      processing.fill(200); // set the fill color to light gray otherwise

    // draw the button (rectangle with a centered text)
    processing.rect(position[0] - WIDTH / 2.0f, position[1] - HEIGHT / 2.0f,
        position[0] + WIDTH / 2.0f, position[1] + HEIGHT / 2.0f);
    processing.fill(0); // set the fill color to black
    processing.text(label, position[0], position[1]); // display the text of the current button
  }

	/*
   * mousePressed() method adds a new tiger or deer to the listGUI depending on the type 
	 * This method is override by other mousePressed() in both the AddAnimalButton() class, and 
	 * the clearButton() class. 
	 * 
	 * @param none
	 */
  @Override
  public void mousePressed() {
    if (isMouseOver())
      System.out.println("A button was pressed."); //prints when the method is not override.
  }

	/*
   * mouseReleased() is empty and override by other classes.
	 * 
	 * @param none
	 */
  @Override
  public void mouseReleased() {}

	/*
   * isMouseOver() checks if the mouse is over the tiger or the deer. If the position of the 
   * mouse is between an area of animal, then the if statement will return true. The way the 
   * positions are captured is by calling this.processing.mouseX/Y and this.position[0 or 1]. 
   * 
   * @param na
   * @return boolean true or false; 
	 */
  @Override
  public boolean isMouseOver() {
    if (this.processing.mouseX > this.position[0] - WIDTH / 2 
        && this.processing.mouseX < this.position[0] + WIDTH / 2
        && this.processing.mouseY > this.position[1] - HEIGHT / 2
        && this.processing.mouseY < this.position[1] + HEIGHT / 2) //this if statement uses positions, widths, and heights
      return true;                                                 //to calculate if the mouse is over the animal
    return false;
  }
}