//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           TestAdventureStory
// Files:           AdventureStory.java TestAdventureStory.java
// Course:          CS 200 Spring 2019
//
// Author:          Tejvir Mann
// Email:           tsmann@wisc.edu
// Lecturer's Name: Renault
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully 
// acknowledge and credit those sources of help here.  Instructors and TAs do 
// not need to be credited here, but tutors, friends, relatives, room mates 
// strangers, etc do.  If you received no outside help from either type of 
// source, then please explicitly indicate NONE.
//
// Persons:         (identify each person and describe their help in detail)
// Online Sources:  (identify each URL and describe their assistance in detail)
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

///////////////////////////////////////////////////////////////////////////////
// Config.java Spring 2019
//
// This file contains constants used within your AdventureStory program.
// This file will not be handed in, because testing Config.java files will
// be used to test your program. Your code must reference these constant
// values by the names defined below and not the values themselves.
//
///////////////////////////////////////////////////////////////////////////////
public class Config {

    /**
     * Display maximum line length
     */
    public static int DISPLAY_WIDTH = 80; 

    /**
     * Room details String[] constants
     */
    public static int ROOM_DET_LEN = 3; // room details array length
    public static int ROOM_ID = 0; // index of room id
    public static int ROOM_TITLE = 1; // index of room title
    public static int ROOM_DESC = 2; // index of room desc

    /**
     * Transition details String[] constants
     */
    public static int TRAN_DET_LEN = 3; // transition details array length
    public static int TRAN_DESC = 0; // index of transition description
    public static int TRAN_ROOM_ID = 1; // index of transition destination (id of the room)
    public static int TRAN_PROB = 2; // index of transition probability weight

    /**
     * Terminal state constants
     */
    public static String SUCCESS = "=)";
    public static String FAIL = "=(";

    /**
     * File magic numbers
     */
    public static String MAGIC_STORY = "#!STORY";
    public static String MAGIC_BOOKMARK = "#!BOOKMARK";

    /**
     * Character used when printing a line
     */
    public static char LINE_CHAR = '-';

    /**
     * Random seed to use
     */
    public static long SEED = 6;
}