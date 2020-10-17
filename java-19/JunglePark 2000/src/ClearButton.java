//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           ClearButton
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
   * The ClearButton extends the Button class and both creates, and then runs a clearAll
   * method to completely empty the listGUI.
   * 
   * @author Tejvir Mann
   */
    public class ClearButton extends Button {
	
	  /**
   * The ClearButton method calls the super class button, and sets the value of label. 
	   * The calling super class, initializes x, y, and park.
	   * 
	   * @param float x - for the position x for the button
	   * @param float y - for the position y for the button
	   * @param JunglePark park  - the specific PApplet object where the button will be displayed
	   */
	public ClearButton(float x, float y, JunglePark park) {
	    super(x, y, park); //
	    this.label = "Clear Park";	
	}
	
	/*
   * mousePressed() method overrides the mousePressed() method in animal, and when the method 
   * is called, then if the isMouseOver() is true, then processing.clear() clears the entire 
   * listGUI. 
	 * 
	 * @param none
	 */
	@Override
	public void mousePressed() {
	  if (isMouseOver()) {
      processing.clear(); 
	  }
	}
}
