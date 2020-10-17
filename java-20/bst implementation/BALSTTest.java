//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           BALSTTest
// Files:           BALSTTest, BALSTADT, BALST, BSTNode
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


import static org.junit.Assert.fail;


import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

/*
 * This class is used to test the implementation of the BALST class. This class 
 * has 10 different test for the insert, remove, transversal, and other 
 * various helper methods. 
 * 
 * @author Tejvir Mann
 */
public class BALSTTest {

    BALST<String,String> balst1;	
    BALST<Integer,String> balst2;

    /**
     * @throws java.lang.Exception
     */
    @BeforeEach
    void setUp() throws Exception {
        balst1 = createInstance();
        balst2 = createInstance2();
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterEach
    void tearDown() throws Exception {
        balst1 = null;
        balst2 = null;
    }

    /*
     * creates an instance of BALST
     */
    protected BALST<String, String> createInstance() {
        return new BALST<String,String>();
    }

    /*
     * creates another instance of BALST
     */
    protected BALST<Integer,String> createInstance2() {
        return new BALST<Integer,String>();
    }

    /** 
     * Insert three values in sorted order and then check 
     * the root, left, and right keys to see if rebalancing 
     * occurred.
     */
    @Test
    void testBALST_001_insert_sorted_order_simple() {
        try {
            balst2.insert(10, "10");
            if (!balst2.getKeyAtRoot().equals(10)) 
                fail("avl insert at root does not work");
            
            balst2.insert(20, "20");
            if (!balst2.getKeyOfRightChildOf(10).equals(20)) 
                fail("avl insert to right child of root does not work");
            
            balst2.insert(30, "30");
            Integer k = balst2.getKeyAtRoot();
            if (!k.equals(20)) 
                fail("avl rotate does not work");
            
            // IF rebalancing is working,
            // the tree should have 20 at the root
            // and 10 as its left child and 30 as its right child

            Assert.assertEquals(balst2.getKeyAtRoot(), Integer.valueOf(20)); //used to be new Integer(20); plus the two below.
            Assert.assertEquals(balst2.getKeyOfLeftChildOf(20),Integer.valueOf(10));
            Assert.assertEquals(balst2.getKeyOfRightChildOf(20),Integer.valueOf(30));

            balst2.print();
            
        } catch (Exception e) {
            e.printStackTrace();
            fail( "Unexpected exception AVL 000: "+e.getMessage() );
        }
    }

    /** 
     * Insert three values in reverse sorted order and then check 
     * the root, left, and right keys to see if rebalancing 
     * occurred in the other direction.
     */
    @Test
    void testBALST_002_insert_reversed_sorted_order_simple() {
        try {
            balst2.insert(30, "30");
            if (!balst2.getKeyAtRoot().equals(30)) 
                fail("avl insert at root does not work");
            
            balst2.insert(20, "20");
            if (!balst2.getKeyOfLeftChildOf(30).equals(20)) 
                fail("avl insert to right child of root does not work");
            
            balst2.insert(10, "10");
            Integer k = balst2.getKeyAtRoot();
            if (!k.equals(20)) 
                fail("avl rotate does not work");
            
            // IF rebalancing is working,
            // the tree should have 20 at the root
            // and 10 as its left child and 30 as its right child

            Assert.assertEquals(balst2.getKeyAtRoot(), Integer.valueOf(20)); //used to be new Integer(20); plus the two below.
            Assert.assertEquals(balst2.getKeyOfLeftChildOf(20),Integer.valueOf(10));
            Assert.assertEquals(balst2.getKeyOfRightChildOf(20),Integer.valueOf(30));

            balst2.print();
            
        } catch (Exception e) {
            e.printStackTrace();
            fail( "Unexpected exception AVL 000: "+e.getMessage() );
        }   
    }

    /** 
     * Insert three values so that a right-left rotation is
     * needed to fix the balance.
     * 
     * Example: 10-30-20
     * 
     * Then check the root, left, and right keys to see if rebalancing 
     * occurred in the other direction.
     */
    @Test
    void testBALST_003_insert_smallest_largest_middle_order_simple() {
    	try {
            balst2.insert(10, "10");
            if (!balst2.getKeyAtRoot().equals(10)) 
                fail("avl insert at root does not work");
            
            balst2.insert(30, "30");
            if (!balst2.getKeyOfRightChildOf(10).equals(30)) 
                fail("avl insert to right child of root does not work");
            
            balst2.insert(20, "20");
            Integer k = balst2.getKeyAtRoot();
            if (!k.equals(20)) 
                fail("avl rotate does not work");
            
            // IF rebalancing is working,
            // the tree should have 20 at the root
            // and 10 as its left child and 30 as its right child

            Assert.assertEquals(balst2.getKeyAtRoot(), Integer.valueOf(20)); //used to be new Integer(20); plus the two below.
            Assert.assertEquals(balst2.getKeyOfLeftChildOf(20),Integer.valueOf(10));
            Assert.assertEquals(balst2.getKeyOfRightChildOf(20),Integer.valueOf(30));

            balst2.print();
            
        } catch (Exception e) {
            e.printStackTrace();
            fail( "Unexpected exception AVL 000: "+e.getMessage() );
        }    
    }

    /** 
     * Insert three values so that a left-right rotation is
     * needed to fix the balance.
     * 
     * Example: 30-10-20
     * 
     * Then check the root, left, and right keys to see if rebalancing 
     * occurred in the other direction.
     */
    @Test
    void testBALST_004_insert_largest_smallest_middle_order_simple() {
        
    	try {
            balst2.insert(30, "30");
            if (!balst2.getKeyAtRoot().equals(30)) 
                fail("avl insert at root does not work");
            
            balst2.insert(10, "10");
            if (!balst2.getKeyOfLeftChildOf(30).equals(10)) 
                fail("avl insert to right child of root does not work");
            
            balst2.insert(20, "20");
            Integer k = balst2.getKeyAtRoot();
            if (!k.equals(20)) 
                fail("avl rotate does not work");
            
            // IF rebalancing is working,
            // the tree should have 20 at the root
            // and 10 as its left child and 30 as its right child

            Assert.assertEquals(balst2.getKeyAtRoot(), Integer.valueOf(20)); //used to be new Integer(20); plus the two below.
            Assert.assertEquals(balst2.getKeyOfLeftChildOf(20),Integer.valueOf(10));
            Assert.assertEquals(balst2.getKeyOfRightChildOf(20),Integer.valueOf(30));

            balst2.print();
            
        } catch (Exception e) {
            e.printStackTrace();
            fail( "Unexpected exception AVL 000: "+e.getMessage() );
        }   
        
    }
    
   //Extra Tests
    
    /*
     * This insert test inserts over 10 values and checks that all of the 
     * AVL rotations occured and that the inerst method is correctly 
     * working. 
     * 
     * @param na
     */
    @Test
    void testBALST_005_insertCheck() {
    
    try {
        balst2.insert(30, "30");  //values inserted. 
        balst2.insert(17, "17");
        balst2.insert(1, "1");
        balst2.insert(3, "3");
        balst2.insert(19, "19");
        balst2.insert(300, "300");
        balst2.insert(46, "46");
        balst2.insert(50, "50");
        balst2.insert(23, "23");
        balst2.insert(9, "9");
        balst2.insert(31, "31");
        balst2.insert(40, "40");
        balst2.insert(4, "4");
        balst2.insert(27, "27");
        
        
        if (!balst2.getKeyOfRightChildOf(30).equals(50)) //checks right and left childs for 
            fail("avl insert to right child of root does not work");  //various roots.
        if (!balst2.getKeyOfLeftChildOf(30).equals(17)) 
            fail("avl insert to left child of root does not work"); //if failure 
        
        if (!balst2.getKeyOfRightChildOf(23).equals(27)) 
            fail("avl insert to right child of root does not work");
        if (!balst2.getKeyOfLeftChildOf(23).equals(19)) 
            fail("avl insert to left child of root does not work");
        
        if (!balst2.getKeyOfRightChildOf(40).equals(46)) 
            fail("avl insert to right child of root does not work");
        if (!balst2.getKeyOfLeftChildOf(40).equals(31)) 
            fail("avl insert to left child of root does not work");
        
    }
    catch (Exception e) {  //if an exception is thrown 
        e.printStackTrace();
        fail( "Unexpected exception AVL 000: "+e.getMessage() );
    }  
    
    
    
  }
    
    /*
     * This test is to test the remove method and the insert method. This 
     * checks if both are working correctly. 
     * @param na
     */
    @Test
    void testBALST_006_insertRemove() {
    	try {
            balst2.insert(30, "30");  //insert and remove 
            balst2.insert(17, "17");
            balst2.insert(1, "1");
            balst2.remove(1);
            balst2.insert(2, "2");
            if (!balst2.getKeyOfLeftChildOf(17).equals(2)) 
                fail("avl insert to right child of root does not work"); //if fail 
    	
    }
    
    	catch (Exception e) { //if exception thrown, gets caught. 
            e.printStackTrace();
            fail( "Unexpected exception AVL 000: "+e.getMessage() );
        }  
    
    }
    
    /*
     * This test is meant to verify the correctness of InOrder Traveral. 
     * @param na. 
     */
    @Test
    void testBALST_007_InOrdertraversals() {
    	
      try {
		
    	  balst2.insert(30, "30"); //bunch of inserts 
    	  balst2.insert(17, "17");
          balst2.insert(1, "1");
          balst2.insert(3, "3");
          balst2.insert(19, "19");
          balst2.insert(300, "300");
          balst2.insert(46, "46");
          balst2.insert(50, "50");
          balst2.insert(23, "23");
          balst2.insert(9, "9");
          balst2.insert(31, "31");
          balst2.insert(40, "40");
          balst2.insert(4, "4");
          balst2.insert(27, "27");
    	  
         if( balst2.getInOrderTraversal().get(4) != 17) { //checks a value, if its right 
        	 fail( "InOrder Traversal should be 17 prob: " + balst2.getInOrderTraversal().get(4) );
         }

    	  
	} catch (IllegalNullKeyException e) { //if exception thrown.
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (DuplicateKeyException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 

    }

    /*
     * This is meant to verify to correctness of PreOrder Traversal. 
     * @param na. 
     */
    @Test
    void testBALST_008_PreOrdertraversals() {
    	
    	try {
    	balst2.insert(30, "30"); //inserts 
  	    balst2.insert(17, "17");
        balst2.insert(1, "1");
        balst2.insert(3, "3");
        balst2.insert(19, "19");
        balst2.insert(300, "300");
        balst2.insert(46, "46");
        balst2.insert(50, "50");
        balst2.insert(23, "23");
        balst2.insert(9, "9");
        balst2.insert(31, "31");
        balst2.insert(40, "40");
        balst2.insert(4, "4");
        balst2.insert(27, "27");
        
        if( balst2.getPreOrderTraversal().get(7) != 19) { //compares it to the last value 
       	 fail( "PreOrder Traversal Prob: should be 19 but: " + balst2.getPreOrderTraversal().get(6) );
        }
    	}
    	
    	catch (IllegalNullKeyException e) { //if exception is thrown.
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	} catch (DuplicateKeyException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	} 
    	
    	
    	
    }
    
    /*
     * This is meant to verify the correctness of PostOrder Traversal
     * @param na
     */
    @Test
    void testBALST_009_PostOrdertraversals() {
    	
    	try {
        	balst2.insert(30, "30"); //inserts 
      	    balst2.insert(17, "17");
            balst2.insert(1, "1");
            balst2.insert(3, "3");
            balst2.insert(19, "19");
            balst2.insert(300, "300");
            balst2.insert(46, "46");
            balst2.insert(50, "50");
            balst2.insert(23, "23");
            balst2.insert(9, "9");
            balst2.insert(31, "31");
            balst2.insert(40, "40");
            balst2.insert(4, "4");
            balst2.insert(27, "27");
            
            if( balst2.getPostOrderTraversal().get(6) != 23) { //checks if preorder traversal inserted right
              	 fail( "PreOrder Traversal Prob: should be 23 but: " + balst2.getPostOrderTraversal().get(6) );
               }
            
        	}
        	
        	catch (IllegalNullKeyException e) {
        		// TODO Auto-generated catch block
        		e.printStackTrace();
        	} catch (DuplicateKeyException e) {
        		// TODO Auto-generated catch block
        		e.printStackTrace();
        	} 
        	
    }

    /*
     * This checks the corretness of level order traversal. 
     * @param na. 
     */
    @Test
    void testBALST_0010_LevelOrdertraversals() {
    	
    	try {
        	balst2.insert(30, "30"); //inserts 
      	    balst2.insert(17, "17");
            balst2.insert(1, "1");
            balst2.insert(3, "3");
            balst2.insert(19, "19");
            balst2.insert(300, "300");
            balst2.insert(46, "46");
            balst2.insert(50, "50");
            balst2.insert(23, "23");
            balst2.insert(9, "9");
            balst2.insert(31, "31");
            balst2.insert(40, "40");
            balst2.insert(4, "4");
            balst2.insert(27, "27");
            
            if( balst2.getLevelOrderTraversal().get(6) != 300) { //checks ifthe value of level order works 
              	 fail( "LevelOrder Traversal Prob: should be 300 but: " + balst2.getLevelOrderTraversal().get(6) );
               }
        	}
        	
        	catch (IllegalNullKeyException e) { //checks for exceptions 
        		// TODO Auto-generated catch block
        		e.printStackTrace();
        	} catch (DuplicateKeyException e) {
        		// TODO Auto-generated catch block
        		e.printStackTrace();
        	} 
        	
    }

    /*
     * Checks if the Print method works. 
     * @param none 
     */
    @Test
    void testBALST_0011_PrintChecker() {
     	try {
        	balst2.insert(30, "30"); //insert 
      	    balst2.insert(17, "17");
            balst2.insert(1, "1");
            balst2.insert(3, "3");
            balst2.insert(19, "19");
            balst2.insert(300, "300");
            balst2.insert(46, "46");
            balst2.insert(50, "50");
            balst2.insert(23, "23");
            balst2.insert(9, "9");
            balst2.insert(31, "31");
            balst2.insert(40, "40");
            balst2.insert(4, "4");
            balst2.insert(27, "27");
            
            balst2.print();
            
        	}
        	
        	catch (IllegalNullKeyException e) { //catches exceptions 
        		// TODO Auto-generated catch block
        		e.printStackTrace();
        	} catch (DuplicateKeyException e) {
        		// TODO Auto-generated catch block
        		e.printStackTrace();
        	} 
        	
    	
    }
    
}
