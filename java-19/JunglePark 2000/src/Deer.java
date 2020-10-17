//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Deer
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
    * The Deer class extends the animal class. Overall, the deer class is called whenever
 * a new deer is initialized and the method action() is constantly called, overriding
 * the action() in animal. 
 * 
 * Inside the deer class, there is a Deer constructor which helps initialize variables for
 * each deer such as is ID. 
 * 
 * There is also the scanForThreat() which runs through all of the animals in listGUI and 
 * checks if any of the tigers are close() to a specific deer.
 * 
	 * 
	 * @author Tejvir Mann
	 */
    public class Deer extends Animal {

	private static final int SCAN_RANGE = 175; // scan range area to check for a threat in the neighborhood
	private static final String IMAGE_FILE_NAME = "images/deer.png";
	private static int nextID = 1; // class variable that represents the identifier of the next deer
	                               // to be created
	  
	private static final String TYPE = "DR"; // A String that represents the deer type
	private final int id; // Deer's id: positive number that represents the order of the deer

	/*
	 * Constructor that creates a new Deer object positioned at a random position of the display window
	 * 
	 * @param JunglePark processing - PApplet object where the button will be displayed
	 */
	public Deer(JunglePark processing) { 

		// Set Deer drawing parameters
	    super(processing, IMAGE_FILE_NAME);

	    // Set Deer identification fields
	    id = nextID;
	    this.label = TYPE + id; // String that identifies the current deer
	    nextID++;	

	}

	/*
	 * Checks if there is a threat (a Tiger for instance) at the neighborhood
	 * scanRange is an integer that represents the range of the area to be scanned around the animal.
	 * 
	 * @param int scanRange - the range that the tiger has to be within for a threat to be scanned.
	 * @return boolean TorF - true of threat, false if no threat.
	 */
	public boolean scanForThreat(int scanRange) {  
		boolean TorF = false;
		
		//goes through the GUI and for each tiger checks if it is the range of the deer, true. if not
		//return false. 
		for(int i = 0 ; i < processing.listGUI.size(); i++) {
		  if(processing.listGUI.get(i) instanceof Tiger) {
			
			 ParkGUI obj = processing.listGUI.get(i); // to downcast the listGUI so the .isCLose() method
    		 Animal stf = (Animal)obj;                // can run through the list and see if any animal is a threat
			 if( this.isClose(stf, scanRange) == true) {
		     TorF = true;
		     return TorF;
		 }
		}
	   }
		
		return TorF;
	}

	/*
	 * Defines the behavior of a Deer object in the Jungle park
	 * @param NA
	 */
	  @Override
	public void action() {  
		  if(scanForThreat(SCAN_RANGE) == true) {
		  this.processing.fill(0); // specify font color: black
		  this.processing.text("THREAT!", this.getPositionX(), this.getPositionY() - this.image.height / 2 - 4);
		  //prints "THREAT!" for the current deer. 
		  }
		  
	  }
	
	
	
}
