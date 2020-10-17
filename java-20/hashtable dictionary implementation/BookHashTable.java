//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           BookHashTable
// Files:           Book Parser, Book, BookHashTable, BookHashTableTests, 2 inf
// Course:          CS400, Fall, 2019
//
// Author:          Tejvir Mann
// Email:           tsmann@wisc.edu
// Lecturer's Name:  Deb Deppeler
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: NA
// Partner Email: NA
// Partner Lecturer's Name: NA
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   ___ Write-up states that pair programming is allowed for this assignment.
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.lang.Math;

// I have chosen #4 for my resolution scheme, which is a arraylist 
// and a bunch of arraylist used as buckets connected each element 
// of the main arraylist. 
// So the overall data structure is a arraylist of arraylists. 
//
//Overall, the hashing algorithm goes through and randomly assigns a 
//position for a book which can easily be found using the hashcode function
//this makes the time efficiency close to O(1). However, in the case of 
//high level collisions the efficiency goes closer to O(N)

/** HashTable implementation that uses:
 * @param <K> unique comparable identifier for each <K,V> pair, may not be null
 * @param <V> associated value with a key, value may be null
 * 
 * @author Tejvir Mann
 */
public class BookHashTable implements HashTableADT<String, Book> {

    /** The initial capacity that is used if none is specifed user */
    static final int DEFAULT_CAPACITY = 101;
    
    /** The load factor that is used if none is specified by user */
    static final double DEFAULT_LOAD_FACTOR_THRESHOLD = 0.75;
    
    private int numKeys; //number of keys in the Hashtable
    private double thresh; //this is the LF threshold of the hashtable
    
    ArrayList<ArrayList<Book>> bucketArray; // the arraylist data structure that 
    										//contains the bucket arraylists 
    
    /**
     * REQUIRED default no-arg constructor
     * Uses default capacity and sets load factor threshold 
     * for the newly created hash table.
     */
    public BookHashTable() {
        this(DEFAULT_CAPACITY,DEFAULT_LOAD_FACTOR_THRESHOLD);
    }
    
    /**
     * Creates an empty hash table with the specified capacity 
     * and load factor.
     * @param initialCapacity number of elements table should hold at start.
     * @param loadFactorThreshold the ratio of items/capacity that causes table to resize and rehash
     */
    public BookHashTable(int initialCapacity, double loadFactorThreshold) {

    	bucketArray = new ArrayList<ArrayList<Book>>(initialCapacity); //initialized
    	
    	for(int j = 0; j < initialCapacity; j++) { //a bunch of new book arraylists are added
    		bucketArray.add(new ArrayList<Book>());
    	}
    	
        thresh = loadFactorThreshold; //threshold stored
    }

    /*
     * Returns the number of key,value pairs in the data structure
     */
    @Override
    public int numKeys() {
    	
		return numKeys;
    }
    
    /*
     * Notice:
     * THIS INTERFACE EXTENDS AND INHERITS ALL METHODS FROM DataStructureADT
     * and adds the following operations:
     * Returns the load factor for this hash table
     * that determines when to increase the capacity 
     * of this hash table
     * 
     */
    @Override
    public double getLoadFactorThreshold() {
 
		return thresh; //the LF threshold
    }
    
    /*
     * Capacity is the size of the hash table array
     * This method returns the current capacity.
     * The initial capacity must be a positive integer, 1 or greater
      *and is specified in the constructor.
     * REQUIRED: When the load factor is reached, 
     * the capacity must increase to: 2 * capacity + 1
     * Once increased, the capacity never decreases
     */
    @Override
    public int getCapacity() {

    	return bucketArray.size(); //this is the size of the hashtable. 
    	
    }
    
    /*
     *  Returns the collision resolution scheme used for this hash table.
      Implement this ADT with one of the following collision resolution strategies
      and implement this method to return an integer to indicate which strategy.
     
       1 OPEN ADDRESSING: linear probe
       2 OPEN ADDRESSING: quadratic probe
       3 OPEN ADDRESSING: double hashing
       4 CHAINED BUCKET: array list of array lists
       5 CHAINED BUCKET: array list of linked lists
       6 CHAINED BUCKET: array list of binary search trees
       7 CHAINED BUCKET: linked list of array lists
       8 CHAINED BUCKET: linked list of linked lists
       9 CHAINED BUCKET: linked list of of binary search trees
     */
    @Override
    public int getCollisionResolutionScheme() {
  
    	return 4; //this is the one I choose to do
    }

    /*
	 * Add the key,value pair to the data structure and increase the number of keys.
     * If key is null, throw IllegalNullKeyException;
     * If key is already in data structure, throw DuplicateKeyException();
     * 
     * @param key - key
     * @param value - value
	 */
    @Override
	public void insert(String key, Book value) throws IllegalNullKeyException, DuplicateKeyException {
		
    	if(key == null) {
    		throw new IllegalNullKeyException();
    	}

    	//contains helper method. 
    	if(contains(key) == true) {
    		throw new DuplicateKeyException();
    	}
    	
    	//checks the load factor, and if you should rehash
    	if(loadFactor() >= getLoadFactorThreshold()) {
    		rehash(); 
    	}
    	
    	int hashKey = key.hashCode(); //gets the int hashKey.
    	
    	//then mod it. To make sure that the hashKey is in the range 
    	hashKey = hashKey%getCapacity();
    	hashKey = Math.abs(hashKey);
    	
    	//adds to the hashtable arraylist
    	bucketArray.get(hashKey).add(value);  

    	numKeys++; //increases the number of keys by 1
 
	}
    
    /*
     * If key is found, 
     * remove the key,value pair from the data structure
     * decrease number of keys.
     * return true
     * 
     * If key is null, throw IllegalNullKeyException
     * If key is not found, return false
     * 
     * @param key - key 
     * @return true or false. True if removes successfully removes.
     */
	@Override
	public boolean remove(String key) throws IllegalNullKeyException {
		if(key == null) {
			throw new IllegalNullKeyException();
    	}
		
		if(contains(key) == false) { //checks if contained
			return false;
		}
		
		//runs through the table. If the key is found, then the element attached to it is deleted. 
		for(int i = 0; i < getCapacity(); i++) {
			for(int j = 0; j < bucketArray.get(i).size(); j++) {
				if(key.equals(bucketArray.get(i).get(j).getKey())) {
					bucketArray.get(i).remove(j);
					numKeys--;
					return true;
				}
			}
		}
		
		return false;
	}
	
	/*
	 * Returns the value associated with the specified key
     * Does not remove key or decrease number of keys
     *
     * If key is null, throw IllegalNullKeyException 
     * If key is not found, throw KeyNotFoundException().
     * 
     * @param key - key 
	 */
	@Override
	public Book get(String key) throws IllegalNullKeyException, KeyNotFoundException {
		if(key == null) { //chekcs if key null
			throw new IllegalNullKeyException();
    	}
		
		if(contains(key) == false) { //checks if contained
			throw new KeyNotFoundException();
		}
		
		for(int i = 0; i < getCapacity(); i++) { //runs through the table and finds the value
			for(int j = 0; j < bucketArray.get(i).size(); j++) {
				if(key.equals(bucketArray.get(i).get(j).getKey())) {
					return bucketArray.get(i).get(j);
				}
			}
		}

		return null;
	}
	
	/*
	 * Checks if the key is in the data structure. Key is added to separate key 
	 * list when insertion occurs and when removal occurs.
	 * 
	 * @param String key - key in order to check if it is in the keyValues array.
	 * @return TorF - boolean true if the key is in the data structure.
	 */
	private boolean contains(String key) {
		
		Boolean TorF = false; //boolean to check if contained of not 
		int capacity = getCapacity();
		
		for(int i = 0; i < capacity; i++) {  //runs through the entire table, and if found
			for(int j = 0; j < bucketArray.get(i).size(); j++) {  	//makes boolean true
				if(key.equals(bucketArray.get(i).get(j).getKey())) {
					return true;
				}
			}
		}

		return TorF;
	
	}
	
	/*
	 * This gets the load factor for the hashtable. If the loadFactor>=to 
     * Threshold, then resize. Load factor is the number of keys 
     * divided by the capacity
	 */
	private double loadFactor() {
		double value = 0; 
    	
    	value = (numKeys * 1.0) /(getCapacity()); //equation to get LF
    	
		return value;
	
	}
   
	/*
     * If the Lf is above the threshold then the table is expanded and 
     * then each value is rehashed into a new position using the hashcode. 
     * 
     * This method saves the old values and copies it to the 
     * newly expanded hashtable. 
     */
    private void rehash() throws IllegalNullKeyException, DuplicateKeyException {
    	
    	// The present bucket list is made temp to store values
        ArrayList<ArrayList<Book>> temp = bucketArray; 
        int oldCapacity = getCapacity();
    
         // New bucketList of double the old size +1 is created 
        bucketArray = new ArrayList<ArrayList<Book>>(2 * oldCapacity + 1);
        numKeys = 0; //reset
        
        //adds a new arraylist object book type to each element of the 1st layer arraylist
        for (int i = 0; i < 2 * oldCapacity + 1; i++) { 
        	bucketArray.add(new ArrayList<Book>());
        	
        }
        
        //copies the stuff from the temporary old array to the newly expaned bucketArray.
        for(int j = 0; j < oldCapacity; j++) {  //instead of numKeys, use temp.size()
        	for(int k = 0; k < temp.get(j).size(); k++) {
        		insert(temp.get(j).get(k).getKey(), temp.get(j).get(k));
        	}
        }
 
    }
    
    /*
     * this is a unused method to calculate how many collisions 
     * occured in the hashtable. 
     */
    private int numberCollisions() {
    	
    	int j = 0;
    	
    	for(int i = 0; i < getCapacity(); i++) {
    		if(bucketArray.get(i).get(1) != null) {
    			j++;
    		}
    	}
    	
    	return j;
    	
    	
    }
    
    
    
}