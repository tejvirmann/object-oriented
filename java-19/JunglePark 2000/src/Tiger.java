//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Tiger
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
 * This class represents a Tiger in the JunglePark application
 *
 */
public class Tiger extends Animal {
  private static final int SCAN_RANGE = 100; // range dimension for scanning the neighborhood for food
  private static final String IMAGE_FILE_NAME = "images/tiger.png";
  private static int nextID = 1; // class variable that represents the identifier of the next tiger
                                 // to be created
  // Tiger's identification fields
  private static final String TYPE = "TGR"; // A String that represents the tiger type
  private final int id; // Tiger's id: positive number that represents the order of the tiger
  private int deerEatenCount; // Number of Deers that the current tiger has eaten so far

  /**
   * Creates a new Tiger object positioned at a random position of the display window
   * 
   * @param processing PApplet object that represents the display window
   */
  public Tiger(JunglePark processing) {
    // Set Tiger drawing parameters
    super(processing, IMAGE_FILE_NAME);

    // Set Tiger identification fields
    id = nextID;
    this.label = TYPE + id; // String that identifies the current tiger
    nextID++;
    deerEatenCount = 0;
    
  }

  /**
   * This method gets the amount of deer eaten by each tiger. 
   * 
   * @return deerEatenCount - the number of deer eaten
   */
  public int getDeerEatenCount() {
	return deerEatenCount;
}
  
  /**
   * This hop method is used to attack the deer and eat it. AKA: take the deers current position and remove it. 
   * 
   * If a deer is within range, the tigers position changes, a deer is removed, and the 
   * deerEatenCount is incremented. If the deer is in range, the mouse will also let 
   * go of a tiger if it is dragging and the tiger will attack.
   * 
   * @param Deer food - this is a deer that is in the listGUI array. 
   */
  public void hop(Deer food) {
	
		   if(this.isDragging() == true) {  //if a deer is in range, the tiger will
	       this.mouseReleased();            //be released from mouse and attack deer. 
		    }
		    	
		   this.setPositionX(food.getPositionX()); //set the position of the tiger to deer  
		   this.setPositionY(food.getPositionY());         
		   processing.listGUI.remove(food);  //removes the deer
		   deerEatenCount++;  //increments the kill count
		
	  }

  /**
   * Displays the number of eaten deers if any on the top of the tiger image
   * 
   * @param na
   */
  public void displayDeerEatenCount() {
    this.processing.fill(0); // specify font color: black
    // display deerEatenCount on the top of the Tiger's image
    this.processing.text(deerEatenCount, this.getPositionX(), this.getPositionY() - this.image.height / 2 - 4);  
  }

/**
   * Tiger's behavior in the Jungle Park
   * Scans for food at the neighborhood of the current tiger. 
   * If the Tiger founds any deer at its proximity, it hops on it, and eats it
   * 
   * @param na
   * 
   */
  @Override
  public void action() {
		  for(int i = 0 ; i < processing.listGUI.size(); i++) {
			  if(processing.listGUI.get(i) instanceof Deer) {  //runs through the list, checks if deer
				  
			    ParkGUI obj = processing.listGUI.get(i);  //down casts the array list. 
		        Deer stf = (Deer)obj;                   
			    if( this.isClose(stf, SCAN_RANGE) == true) {  //checks the distance and if deer in range
	            hop(stf); 
			    }
			 }
		  }
	    if(deerEatenCount > 0)
	      displayDeerEatenCount(); // display deerEatenCount
  }
  
 
}