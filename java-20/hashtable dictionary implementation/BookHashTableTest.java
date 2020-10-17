/**
 * Filename:   TestHashTableDeb.java
 * Project:    p3
 * Authors:    Debra Deppeler (deppeler@cs.wisc.edu)
 * 
 * Semester:   Fall 2018
 * Course:     CS400
 * 
 * Due Date:   before 10pm on 10/29
 * Version:    1.0
 * 
 * Credits:    None so far
 * 
 * Bugs:       Test that is comparable to test 7 on gradescope
 */

import org.junit.After;
import java.io.FileNotFoundException;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/** 
 * Test HashTable class implementation to ensure that required 
 * functionality works for all cases.
 */
public class BookHashTableTest {

    // Default name of books data file
    public static final String BOOKS = "books_clean.csv";

    // Empty hash tables that can be used by tests
    static BookHashTable bookObject;
    static ArrayList<Book> bookTable;

    static final int INIT_CAPACITY = 2;
    static final double LOAD_FACTOR_THRESHOLD = 0.49;
       
    static Random RNG = new Random(0);  // seeded to make results repeatable (deterministic)

    /** Create a large array of keys and matching values for use in any test */
    @BeforeAll
    public static void beforeClass() throws Exception{
        bookTable = BookParser.parse(BOOKS);
    }
    
    /** Initialize empty hash table to be used in each test */
    @BeforeEach
    public void setUp() throws Exception {
        // TODO: change HashTable for final solution
         bookObject = new BookHashTable(INIT_CAPACITY,LOAD_FACTOR_THRESHOLD);
    }

    /** Not much to do, just make sure that variables are reset     */
    @AfterEach
    public void tearDown() throws Exception {
        bookObject = null;
    }

    private void insertMany(ArrayList<Book> bookTable) 
        throws IllegalNullKeyException, DuplicateKeyException {
        for (int i=0; i < bookTable.size(); i++ ) {
            bookObject.insert(bookTable.get(i).getKey(), bookTable.get(i));
        }
    }

    /** IMPLEMENTED AS EXAMPLE FOR YOU
     * Tests that a HashTable is empty upon initialization
     */
    @Test
    public void test000_collision_scheme() {
        if (bookObject == null)
        	fail("Gg");
    	int scheme = bookObject.getCollisionResolutionScheme();
        if (scheme < 1 || scheme > 9) 
            fail("collision resolution must be indicated with 1-9");
    }

    /** IMPLEMENTED AS EXAMPLE FOR YOU
     * Tests that a HashTable is empty upon initialization
     */
    @Test
    public void test000_IsEmpty() {
        //"size with 0 entries:"
        assertEquals(0, bookObject.numKeys());
    }

    /** IMPLEMENTED AS EXAMPLE FOR YOU
     * Tests that a HashTable is not empty after adding one (key,book) pair
     * @throws DuplicateKeyException 
     * @throws IllegalNullKeyException 
     */
    @Test
    public void test001_IsNotEmpty() throws IllegalNullKeyException, DuplicateKeyException {
    	bookObject.insert(bookTable.get(0).getKey(),bookTable.get(0));
        String expected = ""+1;
        //"size with one entry:"
        assertEquals(expected, ""+bookObject.numKeys());
    }
    
    /** IMPLEMENTED AS EXAMPLE FOR YOU 
    * Test if the hash table  will be resized after adding two (key,book) pairs
    * given the load factor is 0.49 and initial capacity to be 2.
    */
    @Test 
    public void test002_Resize() throws IllegalNullKeyException, DuplicateKeyException {
    	bookObject.insert(bookTable.get(0).getKey(),bookTable.get(0));
    	int cap1 = bookObject.getCapacity(); 
    	bookObject.insert(bookTable.get(1).getKey(),bookTable.get(1));
    	int cap2 = bookObject.getCapacity();
    	
        //"size with one entry:"
        assertTrue(cap2 > cap1 & cap1 ==2);
    }
    
    /*
     * This test inserts a bunch of books, then removes one book
     */
    @Test 
    public void test003_insert_many_then_remove() throws IllegalNullKeyException, DuplicateKeyException {
    	boolean TorF = false;
    	
    	bookObject.insert(bookTable.get(0).getKey(),bookTable.get(0)); //inserted books
    	bookObject.insert(bookTable.get(1).getKey(),bookTable.get(1));
    	bookObject.insert(bookTable.get(2).getKey(),bookTable.get(2));
    	bookObject.insert(bookTable.get(3).getKey(),bookTable.get(3));
    	bookObject.insert(bookTable.get(4).getKey(),bookTable.get(4));
    	bookObject.insert(bookTable.get(5).getKey(),bookTable.get(5));
    	bookObject.insert(bookTable.get(6).getKey(),bookTable.get(6));
    	bookObject.insert(bookTable.get(7).getKey(),bookTable.get(7));
    	bookObject.insert(bookTable.get(8).getKey(),bookTable.get(8));
    	bookObject.insert(bookTable.get(9).getKey(),bookTable.get(9));
    	bookObject.insert(bookTable.get(10).getKey(),bookTable.get(10));
    	bookObject.remove(bookTable.get(5).getKey()); //removed book 
    	
    	if(bookObject.remove(bookTable.get(5).getKey()) == false) { //checking if correct book removed
    		TorF = true;
    	}
    	
    	if(TorF = false) {
    		fail("If test worked, TorF should have returned true, but false.");
    	}
    	
    	
    }
    
    /*
     * This test checks what happens when you insert and remove 
     * multiple times
     */
    @Test 
    public void test004_insert_remove_many() throws IllegalNullKeyException, DuplicateKeyException {
    	boolean TorF = false;
    	
    	bookObject.insert(bookTable.get(0).getKey(),bookTable.get(0)); //inserts 
    	bookObject.insert(bookTable.get(1).getKey(),bookTable.get(1));
    	bookObject.insert(bookTable.get(2).getKey(),bookTable.get(2));
    	bookObject.insert(bookTable.get(3).getKey(),bookTable.get(3));
    	bookObject.insert(bookTable.get(4).getKey(),bookTable.get(4));
    	bookObject.insert(bookTable.get(5).getKey(),bookTable.get(5));
    	bookObject.remove(bookTable.get(3).getKey()); //removes 
    	bookObject.remove(bookTable.get(2).getKey());
    	bookObject.remove(bookTable.get(1).getKey());
    	bookObject.remove(bookTable.get(4).getKey());
    	bookObject.remove(bookTable.get(5).getKey());
    	
    	if(bookObject.numKeys() == 0) { //checks if all of the values were removed. 
    		TorF = true;
    	}
    	
    	if(TorF = false) {
    		fail("If test worked, TorF should have returned true, but false.");
    	}
    	
    	
    }
    
    /*
     * This test checks what happens when you insert duplicates
     */
    @Test 
    public void test006_insert_duplicates() throws IllegalNullKeyException, DuplicateKeyException {
    	boolean TorF = false;
    	try {
    	bookObject.insert(bookTable.get(0).getKey(),bookTable.get(0)); //inserts, same book
    	bookObject.insert(bookTable.get(0).getKey(),bookTable.get(1));
 
    	}
    	
    	catch(DuplicateKeyException e){ //if program catches duplicate
    		TorF = true;
    	}
    	
    	if(TorF = false) {
    		fail("If test worked, TorF should have returned true, but false.");
    	}
    
    }
    
    /*
     * This test checks when you try to remove a book with a null key
     */
    @Test 
    public void test007_remove_IllegalNullKey() throws IllegalNullKeyException, DuplicateKeyException {
    	boolean TorF = false;
    	try {
    	bookObject.insert(bookTable.get(0).getKey(),bookTable.get(0)); //inserts
    	bookObject.insert(bookTable.get(1).getKey(),bookTable.get(1));
    	bookObject.remove(null); //the remove is null
 
    	}
 
    	catch(IllegalNullKeyException e){ //if the program catches the problem, should catch
    		TorF = true;
    	}
    	
    	if(TorF = false) {
    		fail("If test worked, TorF should have returned true, but false.");
    	}
    }
    	 /** IMPLEMENTED AS EXAMPLE FOR YOU 
    	    * Test if the hash table  will be resized after adding two (key,book) pairs
    	    * given the load factor is 0.49 and initial capacity to be 2.
    	    */
    	    @Test 
    public void test008_more_resize() throws IllegalNullKeyException, DuplicateKeyException {
    	bookObject.insert(bookTable.get(0).getKey(),bookTable.get(0)); //checks if resize is 
    	int cap1 = bookObject.getCapacity();  //2                      
    	bookObject.insert(bookTable.get(1).getKey(),bookTable.get(1));
    	int cap2 = bookObject.getCapacity();  //5, 11, 23, 57, 115
    	bookObject.insert(bookTable.get(2).getKey(),bookTable.get(2));
    	bookObject.insert(bookTable.get(3).getKey(),bookTable.get(3));
    	int cap3 = bookObject.getCapacity();
    	bookObject.insert(bookTable.get(4).getKey(),bookTable.get(4));
    	bookObject.insert(bookTable.get(5).getKey(),bookTable.get(5));
    	int cap4 = bookObject.getCapacity();
    	bookObject.insert(bookTable.get(6).getKey(),bookTable.get(6));
    	bookObject.insert(bookTable.get(7).getKey(),bookTable.get(7));
    	bookObject.insert(bookTable.get(8).getKey(),bookTable.get(8));
    	bookObject.insert(bookTable.get(9).getKey(),bookTable.get(9));
    	bookObject.insert(bookTable.get(10).getKey(),bookTable.get(10));
    	bookObject.insert(bookTable.get(11).getKey(),bookTable.get(11));
    	int cap5 = bookObject.getCapacity();
    	
        //"size with one entry:"
        assertTrue(cap5 > cap4 && cap4 == cap3 && cap3 > cap2 && cap2 > cap1 && cap1 == 2 && cap5 == 23);
     }
    
     
}
