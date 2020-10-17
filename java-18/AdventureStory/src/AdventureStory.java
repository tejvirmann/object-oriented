//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           TestAdventureStory
// Files:           AdventureStory.java TestAdventureStory.java
// Course:          CS 200 Spring 2019
//
// Author:          Tejvir Mann
// Email:           tsmann@wisc.edu
// Lecturer's Name: self
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

import java.util.Scanner;
import javax.sound.sampled.Line;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Random;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.io.File;
import java.io.IOException;


/**
 * Provides the methods required in order to play a computerized version of the game "AdventureStory",
 * and calculates some statistics based upon the user guesses.
 * 
 * Provides several methods to come up with a list of options to be broken based on a users choice.
 * Then takes user input, and determines if it matches some criteria. Then based on the option that the 
 * user chooses, the methods create a new list of options for the user to choose from. Uses methods
 * to determine probability, to create a display and to read from the possible story files provided. 
 * 
 * @Author Tejvir Mann
 */
public class AdventureStory {


	/**
	 * Prompts the user for a value by displaying prompt. Note: This method should
	 * not add a new line to the output of prompt.
	 *
	 * After prompting the user, the method will consume an entire line of input
	 * while reading an int. If the value read is between min and max (inclusive),
	 * that value is returned. Otherwise, "Invalid value." terminated by a new line
	 * is output and the user is prompted again.
	 *
	 * @param sc     The Scanner instance to read from System.in.
	 * @param prompt The name of the value for which the user is prompted.
	 * @param min    The minimum acceptable int value (inclusive).
	 * @param max    The maximum acceptable int value (inclusive).
	 * @return Returns the value read from the user.
	 * 
	 * 
	 */
	public static int promptInt(Scanner sc, String prompt, int min, int max) {

		
		while(true){
			System.out.print(prompt);

			if (sc.hasNextInt()) {
				int value = sc.nextInt(); //the value is referring to the value entered by the user
				

				if ((value >= min) && (value <= max)) {
					sc.nextLine();
					return value;
					
				}

				
			}

			System.out.println("Invalid Value.");
			sc.nextLine();

		} 

		
	}

	/**
	 * Prompts the user for a char value by displaying prompt. Note: This method
	 * should not add a new line to the output of prompt.
	 *
	 * After prompting the user, the method will read an entire line of input and
	 * return the first non-whitespace character converted to lower case.
	 *
	 * @param sc     The Scanner instance to read from System.in
	 * @param prompt The user prompt.
	 * @return Returns the first non-whitespace character (in lower case) read from
	 *         the user. If there are no non-whitespace characters read, the null
	 *         character is returned.
	 */
	public static char promptChar(Scanner sc, String prompt) {

		String input = " ";
		char value = '0';
		char nullC = '\u0000';

		System.out.print(prompt);
		input = sc.nextLine();
		input = input.toLowerCase().trim();

		for (int i = 0; i < input.length(); ++i) {  //this is here to read until the first character is reached and its lowercase. 
			if (input.charAt(input.length() - 1) == ' ') {
				return nullC;
			}

			if (input.charAt(i) != ' ') {
				value = input.charAt(i);   //the first char value returned. 
				break;
			}

		}
		return value; //the value returned by the player 
	}

	/**
	 * Prompts the user for a string value by displaying prompt. Note: This method
	 * should not add a new line to the output of prompt.
	 *
	 * After prompting the user, the method will read an entire line of input,
	 * removing any leading and trailing whitespace.
	 *
	 * @param sc     The Scanner instance to read from System.in
	 * @param prompt The user prompt.
	 * @return Returns the string entered by the user with leading and trailing
	 *         whitespace removed.
	 */
	public static String promptString(Scanner sc, String prompt) {
		
		System.out.print(prompt);   
		String remove = "";
		remove = sc.nextLine();
		remove = remove.trim();  //reformat's the string that the user enters. 
		return remove;  
	}
	
	/**
	 * Saves the current position in the story to a file.
	 *
	 * The format of the bookmark file is as follows: Line 1: The value of
	 * Config.MAGIC_BOOKMARK Line 2: The filename of the story file from storyFile
	 * Line 3: The current room id from curRoom
	 *
	 * Note: use PrintWriter to print to the file.
	 *
	 * @param storyFile    The filename containing the cyoa story.
	 * @param curRoom      The id of the current room.
	 * @param bookmarkFile The filename of the bookmark file.
	 * @return false on an IOException, and true otherwise.
	 */
	public static boolean saveBookmark(String storyFile, String curRoom, String bookmarkFile) {

		PrintWriter printWriter = null;
		boolean TorF = true;  //to help check if there is a IO exception 

		try {
			printWriter = new PrintWriter(bookmarkFile); // to help write Magic_Bookmark, story file, and curRoom back into the file
			printWriter.println(Config.MAGIC_BOOKMARK);
			printWriter.println(storyFile);
			printWriter.print(curRoom);
		}

		catch (IOException e) {
			TorF = false;
		}

		if (printWriter != null) {
			printWriter.close();
		}

		return TorF;
	}
	

	/**
	 * Loads the story and current location from a file either a story file or a
	 * bookmark file. NOTE: This method is partially implemented in Milestone 2 and
	 * then finished in Milestone 3.
	 * 
	 * The type of the file will be determined by reading the first line of the
	 * file. The first line of the file should be trimmed of whitespace.
	 *
	 * If the first line is Config.MAGIC_STORY, then the file is parsed using the
	 * parseStory method. If the first line is Config.MAGIC_BOOKMARK, the the file
	 * is parsed using the parseBookmark method. Otherwise, print an error message,
	 * terminated by a new line, to System.out, displaying: "First line:
	 * trimmedLineRead does not correspond to known value.", where trimmedLineRead
	 * is the trimmed value of the first line from the file.
	 *
	 * If there is an IOException, print an error message, terminated by a new line,
	 * to System.out, saying "Error reading file: fName", where fName is the value
	 * of the parameter.
	 *
	 * If there is an error reading the first line, print an error message,
	 * terminated by a new line, to System.out, displaying: "Unable to read first
	 * line from file: fName", where fName is the value of the parameter.
	 *
	 * This method will be partially implemented in Milestone #2 and completed in
	 * Milestone #3 as described below.
	 *
	 * Milestone #2: Open the file, handling the IOExceptions as described above. Do
	 * not read the the first line: Assume the file is a story file and call the
	 * parseStory method.
	 *
	 * Milestone #3: Complete the implementation of this method by reading the first
	 * line from the file and following the rules of the method as described above.
	 *
	 * @param fName   The name of the file to read.
	 * @param rooms   The ArrayList structure that will contain the room details. A
	 *                parallel ArrayList trans.
	 * @param trans   The ArrayList structure that will contain the transition
	 *                details. A parallel ArrayList to rooms. Since the rooms can
	 *                have multiple transitions, each room will be an
	 *                ArrayList<String[]> with one String[] per transition with the
	 *                overall structure being an ArrayList of ArrayLists of
	 *                String[].
	 * @param curRoom An array of at least length 1. The current room id will be
	 *                stored in the cell at index 0.
	 * @return false if there is an IOException or a parsing error. Otherwise, true.
	 */
	public static boolean parseFile(String fName, ArrayList<String[]> rooms, ArrayList<ArrayList<String[]>> trans,
			String[] curRoom) {

		boolean TorF = true;
		Scanner sc = null;
		String firstLine = " ";

		try {
			File file = new File(fName);
			sc = new Scanner(file);
			firstLine = sc.nextLine();

			if (firstLine == null) {
				TorF = false;
			}

			firstLine = firstLine.trim();

			if (firstLine.equals(Config.MAGIC_STORY)) {
				parseStory(sc, rooms, trans, curRoom);
			}

			if (firstLine.equals(Config.MAGIC_BOOKMARK)) {
				parseBookmark(sc, rooms, trans, curRoom);
			}

			if (!(firstLine.equals(Config.MAGIC_STORY)) && !(firstLine.equals(Config.MAGIC_BOOKMARK))) {
				System.out.println("First line: " + firstLine + " does not correspond to known value.");
				TorF = false;
			}

		}

		catch (NoSuchElementException e) {
			TorF = false;
		}

		catch (IOException e) {
			System.out.println("Error reading file: " + fName);
			TorF = false;
		}

		return TorF;
	}
	

	/**
	 * Loads the story and the current room from a bookmark file. This method
	 * assumes that the first line of the file, containing Config.MAGIC_BOOKMARK,
	 * has already been read from the Scanner.
	 *
	 * The format of a bookmark file is as follows: Line No: Contents 1:
	 * Config.MAGIC_BOOKMARK 2: Story filename 3: Current room id
	 *
	 * As an example, the following contents would load the story Goldilocks.story
	 * and set the current room to id 7.
	 *
	 * #!BOOKMARK Goldilocks.story 7
	 *
	 * Your method should not duplicate the code from the parseFile method. It must
	 * use the parseFile method to populate the rooms and trans methods based on the
	 * contents of the story filename read and trimmed from line 2 of the file. The
	 * value of for the cell at index 0 of curRoom is the trimmed value read on line
	 * 3 of the file.
	 *
	 * @param sc      The Scanner object buffering the input file to read.
	 * @param rooms   The ArrayList structure that will contain the room details. A
	 *                parallel ArrayList trans.
	 * @param trans   The ArrayList structure that will contain the transition
	 *                details. A parallel ArrayList to rooms.
	 * @param curRoom An array of at least length 1. The current room id will be
	 *                stored in the cell at index 0.
	 * @return false if there is a parsing error. Otherwise, true.
	 */
	public static boolean parseBookmark(Scanner sc, ArrayList<String[]> rooms, ArrayList<ArrayList<String[]>> trans,
			String[] curRoom) {

		String fName = sc.nextLine(); //filename is the first line of the scanner
		String curRoomID = sc.nextLine();
		curRoomID = curRoomID.trim();
		curRoom[0] = curRoomID;

		if (parseFile(fName, rooms, trans, curRoom) == false) { 
			return false;
		}

		else {
			return true;
		}
	}
	

	/**
	 * This method parses a story adventure file.
	 *
	 * The method will read the contents from the Scanner, line by line, and
	 * populate the parallel ArrayLists rooms and trans. As such the story files
	 * have a specific structure. The order of the rooms in the story file
	 * correspond to the order in which they will be stored in the parallel
	 * ArrayLists.
	 *
	 * When reading the file line-by-line, whitespace at the beginning and end of
	 * the line should be trimmed. The file format described below assumes that
	 * whitespace has been trimmed.
	 *
	 * Story file format:
	 *
	 * - Any line (outside of a room's description) that begins with a '#' is
	 * considered a comment and should be ignored. - Room details begin with a line
	 * starting with 'R' followed by the room id, terminated with a ':'. Everything
	 * after the first colon is the room title. The substrings of the room id and
	 * the room title should be trimmed. - The room description begins on the line
	 * immediate following the line prefixed with 'R', containing the room id, and
	 * continues until a line of ";;;" is read. - The room description may be
	 * multi-line. Every line after the first one, should be prefixed with a newline
	 * character ('\n'), and concatenated to the previous description lines read for
	 * the current room. - The room transitions begin immediately after the line of
	 * ";;;", and continue until a line beginning with 'R' is encountered. There are
	 * 3 types of transition lines: - 1 -- Terminal Transition: A terminal
	 * transition is either Config.SUCCESS or Config.FAIL. This room is the end of
	 * the story. This value should be stored as a transition with the String at
	 * index Config.TRAN_DESC set to the value read. The rest of the Strings in the
	 * transition String array should be null. A room with a terminal transition can
	 * only have one transition associated with it. Any additional transitions
	 * should result in a parse error. - 2 -- Normal Transition: The line begins
	 * with ':' followed by the transition description, followed by " -> " (note the
	 * spaces), followed by the room id to transition to. For normal transitions
	 * (those without a transition weight), set the value at index Config.TRAN_PROB
	 * to null. - 3 -- Weighted Transition: Similar to a normal transition except
	 * that there is a probability weight associated with the transition. After the
	 * room id (as described in the normal transition) is a '?' followed by the
	 * probability weight. - You can assume that room ids do not contain a '?'. -
	 * You can assume that Config.SUCCESS and Config.FAIL do not start with a ':'.
	 *
	 * In the parallel ArrayLists rooms and trans, the internal structures are as
	 * follows:
	 *
	 * The String array structure for each room has a length of Config.ROOM_DET_LEN.
	 * The entries in the array are as follows: Index | Description
	 * -------------------------------------------- Config.ROOM_ID | The room id
	 * Config.ROOM_TITLE | The room's title Config.ROOM_DESC | The room's
	 * description
	 *
	 * The String array structure for each transition. Note that each room can have
	 * multiple transitions, hence, the ArrayList of ArrayLists of String[]. The
	 * length of the String[] is Config.TRAN_DET_LEN. The entries in the String[]
	 * are as follows: Index | Description
	 * ------------------------------------------------------------------
	 * Config.TRAN_DESC | The transition description Config.TRAN_ROOM_ID | The
	 * transition destination (id of the room) Config.TRAN_PROB | The probability
	 * weight for the transition
	 *
	 * If you encounter a line that violates the story file format, the method
	 * should print out an error message, terminated by a new line, to System.out
	 * displaying: "Error parsing file on line: lineNo: lineRead", where lineNo is
	 * the number of lines read by the parseStory method (i.e. ignoring the magic
	 * number if Milestone #3), and lineRead is the offending trimmed line read from
	 * the Scanner.
	 *
	 * After parsing the file, if rooms or trans have zero size, or they have
	 * different sizes, print out an error message, terminated by a new line, to
	 * System.out displaying: "Error parsing file: rooms or transitions not properly
	 * parsed."
	 *
	 * After parsing the file, if curRoom is not null, store the reference of the id
	 * of the room at index 0 of the rooms ArrayList into the cell at index 0 of
	 * curRoom.
	 *
	 * Hint: This method only needs a single loop, reading the file line-by-line.
	 * 
	 * Hint: To successfully parse the file, you will need to maintain a state of
	 * where you are in the file. I.e., are you parsing the description, parsing the
	 * transitions; is there an error; etc? One suggestion would be to use an enum
	 * to enumerate the different states.
	 *
	 * @param sc      The Scanner object buffering the input file to read.
	 * @param rooms   The ArrayList structure that will contain the room details.
	 * @param trans   The ArrayList structure that will contain the transition
	 *                details.
	 * @param curRoom An array of at least length 1. The current room id will be
	 *                stored in the cell at index 0.
	 * @return false if there is a parsing error. Otherwise, true.
	 */
	@SuppressWarnings("unchecked")
	public static boolean parseStory(Scanner sc, ArrayList<String[]> rooms, ArrayList<ArrayList<String[]>> trans,
			String[] curRoom) {

		String curLine = " ";
		String forID = " ";
		String forTitle = " ";
		String forDesc = " ";
		String forDesc2 = " ";
		String transDesc = " ";
		String transID = " ";
		String transProb = " ";
		int i = 0;
		int j = 0;
		int k = 0;
		int l = 0;
		int m = 0; // keeps track of line number
		boolean torfRoom = false;
		boolean TorF = true;
		boolean didSomething = false;
		ArrayList<String[]> transArrStr2 = new ArrayList<String[]>();

		while (sc.hasNextLine()) {

			curLine = sc.nextLine().trim();
			didSomething = false; //to check if there was an error parsing the file. 
			torfRoom = false;
			if (curLine != null)

				if (curLine.length() > 0 && curLine.charAt(0) == '#') {
					++m;
					continue;
				}

			if (curLine.length() <= 0) {  //if the current line is just "", then it skips to the next line. 
				++m;
				continue;
			}

			//rooms
			if ((curLine.charAt(0) == 'R') && curLine.contains(":")) { // for room id
				forID = curLine.substring(curLine.indexOf("R") + 1, curLine.indexOf(":"));
				forID = forID.trim();
				rooms.add(new String[Config.ROOM_DET_LEN]);
				rooms.get(i)[Config.ROOM_ID] = forID;
				++i;
				transArrStr2.clear();
				forDesc2 = "";
				l = 0;
				didSomething = true;
			}

			if ((curLine.charAt(0) == 'R') && curLine.contains(":")) { // for room title
				forTitle = curLine.substring(curLine.indexOf(":") + 1, curLine.length());
				forTitle = forTitle.trim();
				rooms.get(j)[Config.ROOM_TITLE] = forTitle;
				++j;
				didSomething = true;
			}

			if ((curLine.charAt(0) == 'R') && curLine.contains(":")) { // for room description
				while (torfRoom == false) {
					curLine = sc.nextLine();
					++m;
					forDesc2 += curLine + "\n";

					if (forDesc2.contains(";;;")) {
						torfRoom = true;
						forDesc2 = forDesc2.trim();
					}
				}
				forDesc = forDesc2.substring(0, forDesc2.indexOf(";;;"));
				forDesc = forDesc.trim();
				rooms.get(k)[Config.ROOM_DESC] = forDesc;
				++k;
				didSomething = true;
			}

			
			// transitions
			if (curLine.length() <= 0) {  //if the current line is just "", then it skips to the next line. 
				++m;
				continue;
			}

			if (curLine.contains(";;;") || (curLine.contains("->") && curLine.contains(":"))) { // to check if it is on the transition line
				if (curLine.contains(";;;")) {

					if (!(sc.hasNextLine())) {
						break;
					}
					curLine = sc.nextLine();
					++m;

					if (curLine.charAt(0) == '#') {
						++m;
						continue;
					}
				}

				// adding a spot in the transition Array list of Array list, then using clone to
				// remove the reference that the transition has to transArrStr2. 
				//I had to use.clone(), because every time I cleared transArrStr2, it also cleared my transition array list. 
				transArrStr2.add(l, new String[Config.TRAN_DET_LEN]);
				int temp = i - 1;
				if (trans.size() == 0) {
					trans.add(temp, (ArrayList<String[]>) transArrStr2.clone());

				} else {
					if (temp == trans.size()) {
						trans.add((ArrayList<String[]>) transArrStr2.clone());
					} else {
						trans.set(temp, (ArrayList<String[]>) transArrStr2.clone());
					}
				}

				if (curLine.contains(Config.SUCCESS)) { // for terminal transition

					trans.get(i - 1).get(0)[Config.TRAN_DESC] = Config.SUCCESS; 																													
					trans.get(i - 1).get(0)[Config.TRAN_ROOM_ID] = null; 													
					trans.get(i - 1).get(0)[Config.TRAN_PROB] = null;
					didSomething = true;
				}

				if (curLine.contains(Config.FAIL)) { // for terminal transition

					trans.get(i - 1).get(0)[Config.TRAN_DESC] = Config.FAIL;
					trans.get(i - 1).get(0)[Config.TRAN_ROOM_ID] = null;
					trans.get(i - 1).get(0)[Config.TRAN_PROB] = null;
					didSomething = true;

				}

				if (!(curLine.contains(Config.SUCCESS)) && !(curLine.contains(Config.FAIL))) { // for transition
					transDesc = curLine.substring(1, curLine.indexOf(" -> "));
					transDesc = transDesc.trim();
					trans.get(i - 1).get(l)[Config.TRAN_DESC] = transDesc;

					didSomething = true;
				}

				if (curLine.contains("?")) { //transition probability and transID
					transID = curLine.substring(curLine.indexOf(" -> ") + 4, curLine.indexOf(" ? "));
					transProb = curLine.substring(curLine.indexOf(" ? ") + 3, curLine.length());
					trans.get(i - 1).get(l)[Config.TRAN_ROOM_ID] = transID;
					trans.get(i - 1).get(l)[Config.TRAN_PROB] = transProb;
					didSomething = true;
				}

				if (!(curLine.contains("?")) && !(curLine.contains(Config.SUCCESS)) //transID
						&& !(curLine.contains(Config.FAIL))) {
					transID = curLine.substring(curLine.indexOf(" -> ") + 4, curLine.length());
					trans.get(i - 1).get(l)[Config.TRAN_ROOM_ID] = transID;
					trans.get(i - 1).get(l)[Config.TRAN_PROB] = null;

					didSomething = true;
				}
			}

			// error
			if (didSomething == false) {
				System.out.print("Error parsing file on line: " + m + ": " + curLine);
				break;
			}
			++m;
			++l;
		}

		if ((rooms.size() != trans.size()) || rooms.size() == 0 || trans.size() == 0) {
			System.out.println("Error parsing file: rooms or transitions not properly parsed.");
			TorF = false;
		}

		// curRoom
		if (curRoom != null) { // assigns the current room if the curRoom passed in is not null
			curRoom[0] = rooms.get(0)[Config.ROOM_ID];
		}

		sc.close();
		return TorF;

	}
	

	/**
	 * Returns the index of the given room id in an ArrayList of rooms.
	 *
	 * Each entry in the ArrayList contain a String array, containing the details of
	 * a room. The String array structure, which has a length of
	 * Config.ROOM_DET_LEN, and has the following entries: Index | Description
	 * -------------------------------------------- Config.ROOM_ID | The room id
	 * Config.ROOM_TITLE | The room's title Config.ROOM_DESC | The room's
	 * description
	 *
	 * @param id    The room id to search for.
	 * @param rooms The ArrayList of rooms.
	 * @return The index of the room with the given id if found in rooms. Otherwise,
	 *         -1.
	 */
	public static int getRoomIndex(String id, ArrayList<String[]> rooms) {

		int NegOne = -1;

		for (int i = 0; i < rooms.size(); ++i) {  //goes through the rooms and checks if the IDs match
			if (rooms.get(i)[Config.ROOM_ID].equals(id)) { 
				return i; //the room index
			}
		}

		return NegOne;
	}
	

	/**
	 * Returns the room String array of the given room id in an ArrayList of rooms.
	 *
	 * Remember to avoid code duplication!
	 *
	 * @param id    The room id to search for.
	 * @param rooms The ArrayList of rooms.
	 * @return The reference to the String array in rooms with the room id of id.
	 *         Otherwise, null.
	 */
	public static String[] getRoomDetails(String id, ArrayList<String[]> rooms) {

		for (int i = 0; i < rooms.size(); ++i) {
			for (int j = 0; j < Config.ROOM_DET_LEN; ++j) // need to switch Config.Room det len to something else. 
				if (rooms.get(i)[j].equals(id)) { //to check if both of the IDs match
					return rooms.get(i); //the room string array
				}

		}
		return null;
	}
	

	/**
	 * Prints out a line of characters to System.out. The line should be terminated
	 * by a new line.
	 *
	 * @param len The number of times to print out c.
	 * @param c   The character to print out.
	 */
	public static void printLine(int len, char c) {

		for (int i = 0; i < len; ++i) {    //prints out a line of characters for a desired length, and is used for display room.
			System.out.print(c);
		}

		System.out.println("");

	}
	

	/**
	 * Prints out a String to System.out, formatting it into lines of length no more
	 * than len characters.
	 * 
	 * This method will need to print the string out character-by-character,
	 * counting the number of characters printed per line. If the character to
	 * output is a newline, print it out and reset your counter. If it reaches the
	 * maximum number of characters per line, len, and the next character is: -
	 * whitespace (as defined by the Character.isWhitespace method): print a new
	 * line character, and move onto the next character. - NOT a letter or digit (as
	 * defined by the Character.isLetterOrDigit method): print out the character, a
	 * new line, and move onto the next character. - Otherwise: - If the previous
	 * character is whitespace, print a new line then the character. - Otherwise,
	 * print a '-', a new line, and then the character. Remember to reset the
	 * counter when starting a new line.
	 *
	 * After printing out the characters in the string, a new line is output.
	 *
	 * @param len The maximum number of characters to print out.
	 * @param val The string to print out.
	 */
	public static void printString(int len, String val) {
		int restart = 0;
		int max = len - 1; // so 79 if len is 80

		for (int i = 0; i < val.length(); ++i) {
			System.out.print(val.charAt(i));
			restart += 1;

			if (val.charAt(i) == '\n') {
				restart = 0;
			}

			if (restart == max) {

				if ((val.charAt(i) == ' ') || (val.charAt(i + 1) == ' ')) { // if there is a space at max or after max
					System.out.println("");
					if (val.charAt(i + 1) == ' ') {
						i = i + 1;
					}
				}

				else if (val.charAt(i + 1) == '\'') {  //if there is a apostrophe 
					System.out.print(val.charAt(i + 1));
					System.out.println("");
					i = i + 1;
				}

				else {
					System.out.println("-"); //if a word has to be cut between lines 
				}

				len = 0;

			}

			if (len == 0) {
				restart = 0;
				len = 1;
			}

		}

	}

	/**
	 * This method prints out the room title and description to System.out.
	 * Specifically, it first loads the room details, using the getRoomDetails
	 * method. If no room is found, the method should return, avoiding any runtime
	 * errors.
	 *
	 * If the room is found, first a line of Config.LINE_CHAR of length
	 * Config.DISPLAY_WIDTH is output. Followed by the room's title, a new line, and
	 * the room's description. Both the title and the description should be printed
	 * using the printString method with a maximum length of Config.DISPLAY_WIDTH.
	 * Finally, a line of Config.LINE_CHAR of length Config.DISPLAY_WIDTH is output.
	 *
	 * @param id    Room ID to display
	 * @param rooms ArrayList containing the room details.
	 */
	public static void displayRoom(String id, ArrayList<String[]> rooms) {

		String rTitle = "";
		String rDescription = "";

		if (getRoomDetails(id, rooms) == null) {
			return;
		}

		else {

			for (int i = 0; i < Config.DISPLAY_WIDTH; ++i) {
				System.out.print(Config.LINE_CHAR);
			}

			rTitle = rooms.get(getRoomIndex(id, rooms))[Config.ROOM_TITLE]; //the text for the title 
			rDescription = rooms.get(getRoomIndex(id, rooms))[Config.ROOM_DESC];  //the text for the description

			System.out.println("");
			printString(Config.DISPLAY_WIDTH, rTitle);  //prints out the title and description in the correct format
			System.out.println("");
			System.out.println("");
			printString(Config.DISPLAY_WIDTH, rDescription);
			System.out.println("");

			for (int i = 0; i < Config.DISPLAY_WIDTH; ++i) {  //instead of using printline() I just ended up doing this instead 
				System.out.print(Config.LINE_CHAR);
			}

			System.out.println();

		}

	}
	

	/**
	 * Prints out and returns the transitions for a given room.
	 *
	 * If the room ID of id cannot be found, nothing should be output to System.out
	 * and null should be returned.
	 *
	 * If the room is a terminal room, i.e., the transition list is consists of only
	 * a single transition with the value at index Config.TRAN_DESC being either
	 * Config.SUCCESS or Config.FAIL, nothing should be printed out.
	 *
	 * The transitions should be output in the same order in which they are in the
	 * ArrayList, and only if the transition probability (String at index TRAN_PROB)
	 * is null. Each transition should be output on its own line with the following
	 * format: idx) transDesc where idx is the index in the transition ArrayList and
	 * transDesc is the String at index Config.TRAN_DESC in the transition String
	 * array.
	 *
	 * See parseStory method for the details of the transition String array.
	 *
	 * @param id    The room id of the transitions to output and return.
	 * @param rooms The ArrayList structure that contains the room details.
	 * @param trans The ArrayList structure that contains the transition details.
	 * @return null if the id cannot be found in rooms. Otherwise, the reference to
	 *         the ArrayList of transitions for the given room.
	 */
	public static ArrayList<String[]> displayTransitions(String id, ArrayList<String[]> rooms,
			ArrayList<ArrayList<String[]>> trans) {

		int index = getRoomIndex(id, rooms);

		if (index == -1) {
			return null;
		}

		ArrayList<String[]> currentTrans = trans.get(index);

		if (!(currentTrans.size() == 1
				&& (currentTrans.get(0).equals(Config.SUCCESS) || currentTrans.get(0).equals(Config.FAIL)))) {
			for (int j = 0; j < currentTrans.size(); ++j) { //

				if (currentTrans.get(j)[Config.TRAN_PROB] == null) {
					if (!(currentTrans.get(j)[Config.TRAN_DESC]).equals(Config.SUCCESS)
							&& !(currentTrans.get(j)[Config.TRAN_DESC]).equals(Config.FAIL)) {  
						System.out.println(j + ") " + currentTrans.get(j)[Config.TRAN_DESC]); //prints out the transitions
					}
				}
			}
		}

		return currentTrans;
	}
	

	/**
	 * Returns the next room id, selected randomly based on the transition
	 * probability weights.
	 *
	 * If curTrans is null or the total sum of all the probability weights is 0,
	 * then return null. Use Integer.parseInt to convert the Strings at index
	 * Config.TRAN_PROB of the transition String array to integers. If there is a
	 * NumberFormatException, return null.
	 *
	 * It is important to follow the specifications of the random process exactly.
	 * Any deviation may result in failed tests. The random transition work as
	 * follows: - Let totalWeight be the sum of the all the transition probability
	 * weights in curTrans. - Draw a random integer between 0 and totalWeight - 1
	 * (inclusive) from rand. - From the beginning of the ArrayList curTrans, start
	 * summing up the transition probability weights. - Return the String at index
	 * Config.TRAN_ROOM_ID of the first transition that causes the running sum of
	 * probability weights to exceed the random integer.
	 *
	 * See parseStory method for the details of the transition String array.
	 *
	 * @param rand     The Random class from which to draw random values.
	 * @param curTrans The ArrayList structure that contains the transition details.
	 * @return The room id that was randomly selected if the sum of probabilities is
	 *         greater than 0. Otherwise, return null. Also, return null if there is
	 *         a NumberFormatException.
	 */
	public static String probTrans(Random rand, ArrayList<String[]> curTrans) {

		String theID = " ";
		int increasingWeight = 0;
		int totalWeight = 0;
		int randomNum = 0;
		int j = 0;
		boolean TorF = true;

		if (curTrans == null) {
			return null;
		}

		try {
			for (int i = 0; i < curTrans.size(); i++) { // creates the max number
				if (curTrans.get(i)[Config.TRAN_PROB] == null) { // added after first entry
					return null;
				}

				if (curTrans.get(i)[Config.TRAN_PROB] != null) {
					totalWeight += Integer.parseInt(curTrans.get(i)[Config.TRAN_PROB]);
				}
			}

			if (totalWeight == 0) {
				return null;
			}

			randomNum = rand.nextInt(totalWeight); // creates a random variables based on the total weight

			while (TorF) {
				if (curTrans.get(j)[Config.TRAN_PROB] != null) { //to form the increasing number 
					increasingWeight += Integer.parseInt(curTrans.get(j)[Config.TRAN_PROB]);
				}

				if (increasingWeight > randomNum) {  //to check what the room ID should be
					TorF = false;
					theID = curTrans.get(j)[Config.TRAN_ROOM_ID];
					return theID;
				}
				j++;
			}

		}

		catch (NumberFormatException e) {
			return null;
		}

		return null;
	}
	

	/**
	 * This is the main method for the Story Adventure game. It consists of the main
	 * game loop and play again loop with calls to the various supporting methods.
	 * This method will evolve over the 3 milestones.
	 * 
	 * The Scanner object to read from System.in and the Random object with a seed
	 * of Config.SEED will be created in the main method and used as arguments for
	 * the supporting methods as required.
	 *
	 * Milestone #1: - Print out the welcome message: "Welcome to this choose your
	 * own adventure system!" - Begin the play again loop: - Prompt for a filename
	 * using the promptString method with the prompt: "Please enter the story
	 * filename: " - Prompt for a char using the promptChar method with the prompt:
	 * "Do you want to try again? " - Repeat until the character returned by
	 * promptChar is an 'n' - Print out "Thank you for playing!", terminated by a
	 * newline.
	 *
	 * Milestone #2: - Print out the welcome message: "Welcome to this choose your
	 * own adventure system!" - Begin the play again loop: - Prompt for a filename
	 * using the promptString method with the prompt: "Please enter the story
	 * filename: " - If the file is successfully parsed using the parseFile method:
	 * - Begin the game loop with the current room ID being that in the 0 index of
	 * the String array passed into the parseFile method as the 4th parameter -
	 * Output the room details via the displayRoom method - Output the transitions
	 * via the displayTransitions method - If the current transition is not
	 * terminal: - Prompt the user for a number between -1 and the number of
	 * transitions minus 1, using the promptInt method with a prompt of "Choose: " -
	 * If the returned value is -1: - read a char using promptChar with a prompt of
	 * "Are you sure you want to quit the adventure? " - Set the current room ID to
	 * Config.FAIL if that character returned is 'y' - Otherwise: Set the current
	 * room ID to the room ID at index Config.TRAN_ROOM_ID of the selected
	 * transition. - Otherwise, the current transition is terminal: Set the current
	 * room ID to the terminal state in the transition String array. - Continue the
	 * game loop until the current room ID is Config.SUCCESS or Config.FAIL - If the
	 * current room ID is Config.FAIL, print out the message (terminated by a line):
	 * "You failed to complete the adventure. Better luck next time!" - Otherwise:
	 * print out the message (terminated by a line): "Congratulations! You
	 * successfully completed the adventure!" - Prompt for a char using the
	 * promptChar method with the prompt: "Do you want to try again? " - Repeat
	 * until the character returned by promptChar is an 'n' - Print out "Thank you
	 * for playing!", terminated by a newline.
	 *
	 * Milestone #3: - Print out the welcome message: "Welcome to this choose your
	 * own adventure system!" - Begin the play again loop: - Prompt for a filename
	 * using the promptString method with the prompt: "Please enter the story
	 * filename: " - If the file is successfully parsed using the parseFile method:
	 * - Begin the game loop with the current room ID being that in the 0 index of
	 * the String array passed into the parseFile method as the 4th parameter -
	 * Output the room details via the displayRoom method - Output the transitions
	 * via the displayTransitions method - If the current transition is not
	 * terminal: - If the value returnd by the probTrans method is null: - Prompt
	 * the user for a number between -2 and the number of transitions minus 1, using
	 * the promptInt method with a prompt of "Choose: " - If the returned value is
	 * -1: - read a char using promptChar with a prompt of "Are you sure you want to
	 * quit the adventure? " - Set the current room ID to Config.FAIL if that
	 * character returned is 'y' - If the returned value is -2: - read a String
	 * using the promptString method with a prompt of: "Bookmarking current
	 * location: curRoom. Enter bookmark filename: ", where curRoom is the current
	 * room ID. - Call the saveBookmark method and output (terminated by a new
	 * line): - if successful: "Bookmark saved in fSave" - if unsuccessful: "Error
	 * saving bookmark in fSave" where fSave is the String returned by promptString.
	 * - Otherwise: Set the current room ID to the room id at index
	 * Config.TRAN_ROOM_ID of the selected transition. - Otherwise, the value
	 * returned by probTrans is not null: make this value the current room ID. -
	 * Continue the game loop until the current room ID is Config.SUCCESS or
	 * Config.FAIL. - If the current room ID is Config.FAIL, print out the message
	 * (terminated by a line): "You failed to complete the adventure. Better luck
	 * next time!" - Otherwise: print out the message (terminated by a line):
	 * "Congratulations! You successfully completed the adventure!" - Prompt for a
	 * char using the promptChar method with the prompt: "Do you want to try again?
	 * " - Repeat until the character returned by promptChar is an 'n' - Print out
	 * "Thank you for playing!", terminated by a newline.
	 *
	 * @param args Unused
	 */
	public static void main(String[] args) {

		boolean playAgain = true;
		Scanner sc = new Scanner(System.in);
		Random rand = new Random(Config.SEED);
		//
		System.out.println("Welcome to this choose your own adventure system!");

		while (playAgain == true) {

			String[] curRoom = new String[1]; //for current room ID
			String bookmarkPrompt = "Bookmarking current location:" + curRoom[0] + ". Enter bookmark filename:";
			String filePrompt = "Please enter the story filename: ";
			String endPrompt = "Do you want to try again? ";
			String choosePrompt = "Choose: ";
			String youSurePrompt = "Are you sure you want to quit the adventure? ";
			String probTransID = " "; 
			
			char YorN = '2';
			int i = 0;
			int levelchoice = 0;
			char charChoice = ' ';
			String stringChoice = " ";
			String fName = " ";
			ArrayList<String[]> rooms = new ArrayList<String[]>();
			ArrayList<String[]> curTrans = new ArrayList<String[]>();
			ArrayList<ArrayList<String[]>> trans = new ArrayList<ArrayList<String[]>>();
			int min = -2;
			
			boolean gameLoop = true;

			fName = promptString(sc, filePrompt);

			if (parseFile(fName, rooms, trans, curRoom) == true) { // to parse the file and get the values into rooms and transitions
				while (gameLoop == true) {
					displayRoom(curRoom[0], rooms); //to print rooms 
					curTrans = displayTransitions(curRoom[0], rooms, trans); //to print transitions
																				
					if (!(curTrans.get(0)[Config.TRAN_DESC].equals(Config.SUCCESS))
							&& (!(curTrans.get(0)[Config.TRAN_DESC].equals(Config.FAIL)))) { 
						probTransID = probTrans(rand, curTrans);  //to calculate probability
						if (probTransID == null) {
							levelchoice = promptInt(sc, choosePrompt, min, curTrans.size() - 1); //max = curTrans.get(0).size() -1;
							if (levelchoice == -1) {
								charChoice = promptChar(sc, youSurePrompt);
								if (charChoice == 'y') {
									curRoom[0] = Config.FAIL;
								}
							}

							if (levelchoice == -2) {
								stringChoice = promptString(sc, bookmarkPrompt);  //to help save the current spot in the story into a book mark file

								if (saveBookmark(fName, curRoom[0], stringChoice) == true) {
									System.out.println("Bookmark saved in " + stringChoice);
								}
								if (saveBookmark(fName, curRoom[0], stringChoice) == false) {
									System.out.println("Error saving bookmark in " + stringChoice);
								}
							}

							if (levelchoice != -1 && levelchoice != -2) {
								curRoom[0] = curTrans.get(levelchoice)[Config.TRAN_ROOM_ID];
							}
						}

						else {
							curRoom[0] = probTransID;
						}

					}

					else { 
						curRoom[0] = curTrans.get(0)[Config.TRAN_DESC];
					}

					if (curRoom[0].equals(Config.SUCCESS)) {
						gameLoop = false;
						System.out.println("Congratulations! You successfully completed the adventure!");
					}

					if (curRoom[0].equals(Config.FAIL)) {
						gameLoop = false;
						System.out.println("You failed to complete the adventure. Better luck next time!");
					}
				}
			}

			YorN = promptChar(sc, endPrompt); //after the game loop is done, the prompt asks if the player wants to repeat

			if (YorN == 'y') {
				playAgain = true;
			}

			if (YorN == 'n') {
				playAgain = false;
			}
		}
		System.out.println("Thank you for playing!");
	}
}
