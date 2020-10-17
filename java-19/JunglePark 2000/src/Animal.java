//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Animal
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
 * This class represents an animal in the Jungle Park application
 * It implements the interface ParkGUI
 * @author Mouna Kacem
 */
public class Animal implements ParkGUI {
  
  private static Random randGen = new Random(); // Generator of random numbers
  protected String label; // represents the animal's identifier
  // Fields defined to draw the animal in the application display window
  protected JunglePark processing; // PApplet object that represents the display window
  protected PImage image; // animal's image

  private float[] position; // animal's position in the display window
                            // Usage: position[0: x-coordinate, or 1: y-coordinate]
  private boolean isDragging; // indicates whether the animal is being dragged or not

  /**
   * Creates a new Animal object positioned at a given position of the display window
   * 
   * @param processing PApplet object that represents the display window
   * @param positionX x-coordinate of the animal's image in the display window
   * @param positionY y-coordinate of the animal's image in the display window
   * @param imageFileName filename of the animal image
   */
  public Animal(JunglePark processing, float positionX, float positionY, String imageFileName) {

    // Set Animal drawing parameters
    this.processing = processing; // set the PApplet Object where the animal will be drawn
    this.position = new float[] {positionX, positionY}; // sets the position of the animal object
    this.image = processing.loadImage(imageFileName);
    isDragging = false; // initially the animal is not dragging
  }

  /**
   * Creates a new Animal object positioned at a random position of the display window
   * 
   * @param processing PApplet object that represents the display window
   * @param imageFileName filename of the animal image
   */
  public Animal(JunglePark processing, String imageFileName) {
    this(processing, (float) randGen.nextInt(processing.width),
        Math.max((float) randGen.nextInt(processing.height), 100), imageFileName);
  }

  /**
   * Draws the animal to the display window. It sets also its position to the mouse position if the
   * tiger is being dragged (i.e. if its isDragging field is set to true).
   */
  @Override
  public void draw() {
    // if the tiger is dragging, set its position to the mouse position with respect to the display
    // window (processing) dimension
    if (this.isDragging) {
      if (this.processing.mouseX < 0) // mouse outside the screen
        this.position[0] = 0;
      else if (this.processing.mouseX > this.processing.width) // mouse outside the screen
        this.position[0] = this.processing.width;
      else
        this.position[0] = this.processing.mouseX;

      if (this.processing.mouseY < 0) // mouse outside the screen
        this.position[1] = 0;
      else if (this.processing.mouseY > this.processing.height) // mouse outside the screen
        this.position[1] = this.processing.height;
      else
        this.position[1] = this.processing.mouseY;
    }

    // draw the tiger at its current position
    this.processing.image(this.image, this.position[0], position[1]);
    // display label
    displayLabel();
    action();
  }

  /**
   * display's the Tiger object label on the application window screen
   */
  private void displayLabel() {
    this.processing.fill(0); // specify font color: black
    this.processing.text(label, this.position[0], this.position[1] + this.image.height / 2 + 4);// display
                                                                                                // label                                                                                              // text
  }

  /**
   * Checks if the mouse is over the given tiger object
   * 
   * @param tiger reference to a given Tiger object
   * @return true if the mouse is over the given tiger object, false otherwise
   */
  @Override
  public boolean isMouseOver() {
    int tigerWidth = image.width; // image width
    int tigerHeight = image.height; // image height

    // checks if the mouse is over the tiger
    if (processing.mouseX > position[0] - tigerWidth / 2
        && processing.mouseX < position[0] + tigerWidth / 2
        && processing.mouseY > position[1] - tigerHeight / 2
        && processing.mouseY < position[1] + tigerHeight / 2) {
      return true;
    }
    return false;
  }

  /**
   * If the mouse is over the given tiger object, isDragging is true.
   * 
   * @param Na
   * @return Na
   */
  @Override
  public void mousePressed() {
    if (isMouseOver())
      isDragging = true;
  }

  /**
   * If mouseReleased is called, then isDragging is set to false;
   * 
   * @param Na
   * @return Na
   */
  @Override
  public void mouseReleased() {
    isDragging = false;
  }

  /**
   * @return the label that represents the tiger's identifier
   */
  public String getLabel() {
    return label;
  }

  /**
   * @return the image of type PImage of the tiger object
   */
  public PImage getImage() {
    return image;
  }

  /**
   * @return the X coordinate of the animal position
   */
  public float getPositionX() {
    return position[0];
  }

  /**
   * @return the Y coordinate of the animal position
   */
  public float getPositionY() {
    return position[1];
  }

  /**
   * @param position the XPosition to set
   */
  public void setPositionX(float position) {
    this.position[0] = position;
  }

  /**
   * @param position the YPosition to set
   */
  public void setPositionY(float position) {
    this.position[1] = position;
  }

  /**
   * @return true if the animal is being dragged, false otherwise
   */
  public boolean isDragging() {
    return isDragging;
  }

  /**
   * Computes the euclidean distance between the current animal and another one
   * @param otherAnimal reference to another animal
   * @return distance between the current animal and otherAnimal
   */
  public double distance(Animal otherAnimal) {
    return Math.sqrt(Math.pow(this.getPositionX() - otherAnimal.getPositionX(), 2)
        + Math.pow(this.getPositionY() - otherAnimal.getPositionY(), 2));
  }
  
  /**
   * Defines the behavior of the current animal in the jungle park
   */
  public void action() {
    // This method should be overridden by a subclass
  }

  /**
   * isClose method takes the distance method and finds the distance between two animals.
   * Then this method checks if the distance is less than the range, if true, returns true.
   * 
   * @param otherAnimal - the other animal used to measure distance between
   * @param range - the desired range between for a specific animal, either a Tiger or Deer.
   * @return TorF - returns true if otherAnimal is within range
   */
  public boolean isClose(Animal otherAnimal, int range) { 
	boolean TorF = false; 
	  
	if( distance(otherAnimal) <= range ) { //checks if the distance is less than the range. Inclusive 
		TorF = true; 
	}
	
	return TorF;  
  }

}