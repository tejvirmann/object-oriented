//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Book Parser
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
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

//learn how Scanner instances that are connected to the keyboard work.
public class BookParser {

    // @param booksfilename - a csv file with book database information

    // Parse the csv file into a list of book object 
    public static ArrayList<Book> parse(String booksfilename) throws FileNotFoundException{
        ArrayList<Book> bookList = new ArrayList<Book>(); 
        try {
            Scanner scanner = new Scanner(new File(booksfilename));

            Scanner valueScanner = null;
            int idx = 0; 

            // try different methods of the Scanner STDIN
            while ( scanner.hasNext() ) {
                valueScanner = new  Scanner(scanner.nextLine());
                valueScanner.useDelimiter(","); 
                ArrayList<String> book = new ArrayList<String>(); 
                while (valueScanner.hasNext()) {
                    String data = valueScanner.next();
                    book.add(data); 
                } 

                Book bookobj = new Book(book.get(0),book.get(1),book.get(2),
                        book.get(3),book.get(4),book.get(5),book.get(6), book.get(7)); 
                //System.out.println(bookobj.toString()); 
                bookList.add(bookobj);


            }
            bookList.remove(0);
            scanner.close();

        }catch(FileNotFoundException e) {
        }
        return bookList;

    }

}

