//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Book 
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

import java.util.Objects;

/*
 * This class is needed to create an instant of book that contains a 
 * bunch of different parameters such as author, language, etc. 
 * 
 * @author Probably Deb Deppeler or a TA
 */
public class Book {
    private String isbn13;   //various values stored in the book instance.
    private String authors; 
    private String original_publication_year;
    private String title; 
    private String language_code;
    private String average_rating;
    private String cover_type; 
    private String pages;
    
    /*
     * This is a constructor for the book class that initializes the book. 
     * The values for this are taken from the parser.
     */
    public Book(String isbn13, String authors,    //constructor for book 
            String original_publication_year, String title,
            String language_code, String average_rating, 
            String cover_type, String pages){
        this.isbn13 = isbn13; 
        this.title = title;
        this.authors = authors; 
        this.original_publication_year = original_publication_year;
        this.language_code = language_code; 
        this.average_rating = average_rating;
        this.cover_type = cover_type; 
        this.pages = pages; 
    }

    /*
     * This getter is able to get the isbn number when needed 
     */
    public String getKey() { //setter, getter and helper toString method for the class.
        return this.isbn13;
    }
    
    /*
     * This setter sets the isbn number to something else if needed
     * 
     * @param String isbn - new number
     */
    public void setKey(String isbn13) {
        this.isbn13 = isbn13;
    }
    
    /*
     * This to string method is able to print out all of the values stored in 
     * the book instance 
     */
    @Override
    public String toString() {
        return "ISBN13: "+this.isbn13+"; Book: "+ 
               this.title+", Author: "+this.authors+
               ", Original Publication Year: "+
               this.original_publication_year+
               ", Language: "+this.language_code+", Average Rating: "+
               this.average_rating+", Cover Type: "+this.cover_type+ 
               ", Pages: "+ this.pages;                
    }
}

