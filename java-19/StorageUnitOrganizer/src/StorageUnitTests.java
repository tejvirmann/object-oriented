//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           StorageUnitTests
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
 * This class is meant to check if the overall program functions correctly. The class
 * contains an assortment of tests to check if methods such as remove, add, contain, 
 * and many more work. 
 * 
 * @author Tejvir Mann
 * 
 */
public class StorageUnitTests {
	
	/*
	 *  Checks whether the behavior of equals method is correct
	 *  
	 *  @return TorF - true if the test correctly passes. 
	 */
	public static boolean testBoxEquals(){
		boolean TorF = false; 
		
		Box one = new Box(3,3);  //create new boxes
		Box two = new Box(3,3);
		
		if(one.equals(two)) { //checks if equal
		  TorF = true;
		}
		
		return TorF;
		
	}
	
	/*
	 *  Checks whether the behavior of equals method is correct, testing color
	 *  
	 *  @return TorF - true if the test correctly passes. 
	 */
	public static boolean test2BoxEquals(){
		boolean TorF = true; 
		
		Box one = new Box(3,3); //create new boxes
		Box two = new Box(4,3);
		
		if(one.equals(two)) { //check if equal
		  TorF = false;
		}
		
		return TorF;
		
	}
	
	/*
	 *  Checks whether the behavior of equals method is correct, testing weight
	 *  
	 *  @return TorF - true if the test correctly passes. 
	 */
	public static boolean test3BoxEquals(){
		boolean TorF = true; 
		
		Box one = new Box(3,4); //create new boxes
		Box two = new Box(3,3);
		
		if(one.equals(two)) { //check if equal
		  TorF = false;
		}
		
		return TorF;
		
	}

	/*
	 *  Checks whether the behavior of compareTo method is correctly implemented
	 *  
	 *  @return TorF - true if the test correctly passes. 
	 */
   public static boolean testBoxCompareTo(){
		boolean TorF = false;
		boolean lessTorF = false;
		boolean equalTorF = false;
		boolean moreTorF = false;
		
		Box one = new Box(3,4);  //creates four new boxes
		Box two = new Box(3,3);
		Box three = new Box(3,5);
		Box four = new Box(3,4);
		
		if(one.compareTo(four) == 0) {  //compares the weights of the boxes
		   equalTorF = true;
		}
		
		if(two.compareTo(three) < 0) {
			lessTorF = true;
		}
		
		if(three.compareTo(one) > 0){
			moreTorF = true;
		}
		
		if(equalTorF && lessTorF && moreTorF) { //if all the boolean are true
			TorF = true;
		}
		
		return TorF;

	}
   
    /*
     *  Checks whether remove method defined in your LinkedBoxList works correctly
     *  
     *  @return TorF - true if the test correctly passes. 
     */
    public static boolean testLinkedBoxListRemove() {
    	boolean TorF = false; 
    	boolean containsTorF = false;
    	int capacity = 10;
    	int boxOneColor = 12;
    	int boxOneWeight = 22; 
    	int boxTwoColor = 9; 
    	int boxTwoWeight = 9;
    	
    	
    	LinkedBoxList listOne = new LinkedBoxList(capacity);
    	Box boxOne  = new Box(boxOneColor,boxOneWeight);  //creates new boxes
    	Box boxTwo = new Box(boxTwoColor, boxTwoWeight);
    	
    	
    	listOne.add(boxOne);  //adds the boxes to the list
    	listOne.add(boxTwo);
    	
    	listOne.remove(0); //removes the list

    	
    	if(listOne.size() == 1) { //if the size of the list is 1, then true. 
    		TorF = true;
    	}
    	
    	if(listOne.contains(boxOne) == false) { //if the list doesn't contain the box, then true
    	   containsTorF = true;
    	}
    	
    	if(TorF && containsTorF) { //if both booleans true
    		TorF = true;
    	}
    	
    	else {
    		TorF = false; 
    	}
    
    	
    	return TorF; 
    }
    
    /*
     * Checks whether remove method defined in your LinkedBoxList works correctly
     * This tests adds 6 nodes, then removes the first node. Then if the list contains 
     * the right data and is of the right size, the test passes.
     * 
     * @return TorF - true if the test correctly passes.
     */
    public static boolean test2LinkedBoxListRemove() {
    	boolean TorF = false; 
    	boolean containsTorF = false;
    	int capacity = 10;
    	int boxOneColor = 5;
    	int boxOneWeight = 6; 
    	int boxTwoColor = 9; 
    	int boxTwoWeight = 6;
    	int boxThreeColor = 19; 
    	int boxThreeWeight = 10;
    	int boxFourWeight = 5;
    	int boxFiveWeight = 22;
    	int boxSixWeight =13;
    	
    	LinkedBoxList listOne = new LinkedBoxList(capacity); //creates a list
    	Box boxOne  = new Box(boxOneColor,boxOneWeight); //creates six boxes
    	Box boxTwo = new Box(boxTwoColor, boxTwoWeight);
    	Box boxThree = new Box(boxThreeColor, boxThreeWeight);
    	Box boxFour  = new Box(boxOneColor,boxFourWeight);
    	Box boxFive = new Box(boxTwoColor, boxFiveWeight);
    	Box boxSix = new Box(boxThreeColor, boxSixWeight);
    	
    	listOne.add(boxOne); //adds all the boxes, then removes the box with the most weight
    	listOne.add(boxTwo);
    	listOne.add(boxThree);
    	listOne.add(boxFour);
    	listOne.add(boxFive);
    	listOne.add(boxSix);
    	listOne.remove(0);
    	listOne.toString();
    	
    	if(listOne.size() == 5) { //if the size is 5, correct. then true
    		TorF = true;
    	}
    	
    	if(listOne.contains(boxFive) == false) { //if the list contains the box. then true
    	   containsTorF = true;
    	}
    	
    	if(TorF && containsTorF) {
    		TorF = true;
    	}
    	
    	else {
    		TorF = false; 
    	}
    
    	
    	return TorF; 
    }
    
   public static void main(String[] args) {
	   System.out.println("testBoxEquals(): " + testBoxEquals());
	   System.out.println("test2BoxEquals(): " + test2BoxEquals());
	   System.out.println("test3BoxEquals(): " + test3BoxEquals());
	   System.out.println("testBoxCompareTo(): " + testBoxCompareTo());
	   System.out.println("testLinkedBoxListRemove(): " + testLinkedBoxListRemove());
	   System.out.println("test2LinkedBoxListRemove(): " + test2LinkedBoxListRemove());
   }
   
 }