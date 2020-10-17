//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           AddAnimalButton
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
   * The AddAnimalButton extends the Button class and both creates then adds an animal button, either a tiger 
   * or a deer, to the GUI array list. 
   * 
   * @author Tejvir Mann
   */
    public class AddAnimalButton extends Button {

	private String type; // type of the animal to add. Tiger or Deer. 

	/*
	 * The AddAnimalButton takes in the type, and coordinates and creates a button of 
	 * Either tiger, deer, or clear.
	 * 
	 * @param String type - is the type of animal, either tiger or deer. 
	 * @param float x - x position of the button
	 * @param float y - y position of the button
	 * @param JunglePark park - is the GUI the button is being added to. 
	 * 
	 */
	public AddAnimalButton(String type, float x, float y, JunglePark park) {
	    super(x, y, park);  //calls the super button constructor 
	    this.type = type.toLowerCase();  //makes the type lower case
	    this.label = "Add " + type;     //creates the label for each button
	}
	
	/*
	 * mousePressed() method adds a new tiger or deer to the listGUI depending on the type 
	 * String passed, and if the mousePresssed() overrides, which happens when the mouse
	 * presses on a button in a specific area. 
	 * 
	 * @param none
	 */
	@Override
	public void mousePressed() {
	  if (isMouseOver()) {    //calls the mouseOver method, if true the following continues
	    switch (type) {
	      case "tiger":
	        //create a new tiger and add it to JunglePark 
	    	  processing.listGUI.add(new Tiger(processing));
	        break;
	      case "deer":
	        //create a new Deer and add it to the JunglePark
	    	  processing.listGUI.add(new Deer(processing));
	        break;
	    }
	  }
	}
	
}
