//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Box
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

import java.util.Random;

/**
 * 
 * This Box class implements the Comparable, Comparable <Box>. This class also 
 * implements the Box constructors which initialize the instance fields of color 
 * and weight. 
 * 
 * The class also contains the equals, and compareTo methods which are used 
 * to test if the the weights and colors of the boxes within the nodes are 
 * equal or not. 
 * 
 * @author Tejvir Mann
 * 
 */
public class Box implements Comparable<Box> {
	
	private static Random randGen = new Random(); // generator of random numbers
	private int color; // color of this box
	private int weight; // weight of this box in lbs between 1 inclusive and 31 exclusive

	/*
	 * Creates a new Box and initializes its instance fields color and weight to 
	 * random values.
	 * 
	 * @param na
	 */
	public Box() {
		this.color = randGen.nextInt();  //initializing color
		this.weight = randGen.nextInt(29)+1;  //initializing weight	
	}
	  
	/*
     * Creates a new Box and initializes its instance fields color 
     * and weight to the specified values.
	 * 
	 * Throws IllegalArgumentException if the provided weight value is out of the 
	 * range of [1..30]
	 * 
	 * @param color - an int value of the color passed in.
	 * @param weight - an int value of the weight used.
	 * 
	 */
	public Box(int color, int weight) {
	
    if(weight<1 || weight>30) { //if the weight is not within the range, exception thrown.
			throw new IllegalArgumentException("Weight must be more than 0 and less than 31");
		}
        
	this.color = color; 
	this.weight = weight;	
  }

	/*
	 * equals(Object other) method returns true if the specified other object is a Box and this 
	 * box and other have the same color and same weight. Otherwise, it returns false.
	 * 
	 * @param Object other - an object (possibly a box that contains a color and weight)
	 * @return TorF - boolean true or false, true if same color and weight
	 */
	@Override
	public boolean equals(Object other) { 
		
	boolean TorF = false; 
    
	if(other instanceof Box) { //checks if the object is an instance of box. 
	    if(((Box) other).getColor() == this.color) {   
	    	if(((Box) other).getWeight() == this.weight) {
	    		TorF = true;  //if color and weight are equal then TorF becomes true
	    	}
	    }
	}
	
    return TorF;
	}

	/*
	 * compareTo(Box otherBox) returns a negative integer, a positive integer, 
	 * or zero as this box is lighter than, heavier than, or has the same weight 
	 * as the specified otherBox.
	 * 
	 * @param otherBox - the box object containing a certain weight and color.
	 * @return - an integer value that is either positive, negative, or equal. 
	 */
	@Override
	public int compareTo(Box otherBox) { 
	int weightLevel = 0;

	if(otherBox.getWeight()<this.getWeight()) { //if this weight > otherBox weight
		weightLevel = this.getWeight() - otherBox.getWeight(); //weightLevel is this weight - otherBox weight
	}
	
	if(otherBox.getWeight() == this.getWeight()) { //if this weight = otherBox weight
		weightLevel = 0;
	}
	
	if(otherBox.getWeight()>this.getWeight()) { //if this weight < otherBox weight
		weightLevel = this.getWeight() - otherBox.getWeight(); //weightLevel is this weight - otherBox weight
	}

	return weightLevel;
	
	} 
	
	/*
	 * Getter for the instance field color of this box
	 */
	public int getColor() {
		return color;
	}
	
	/*
	 * Getter for the instance field weight of this box
	 */
	public int getWeight() {
		return weight;
	}
}
