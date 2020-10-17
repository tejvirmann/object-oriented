//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: MasterMind
// Files: MasterMind.java TestMasterMind.java Config.java
// Course: CS200 Spring 19
//
// Author: Tejvir Mann
// Email: tsmann@wisc.edu
// Lecturer's Name: Renault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: Quinn Mcbride
// Partner Email: qmcbride@wisc.edu
// Lecturer's Name: Renault
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// x___ Write-up states that pair programming is allowed for this assignment.
// x___ We have both read and understand the course Pair Programming Policy.
// x___ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here. Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates
// strangers, etc do. If you received no outside help from either type of
// source, then please explicitly indicate NONE.
//
// Persons: NONE
// Online Sources: NONE
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * Provides the methods required in order to play a computerized version of the game "Mastermind",
 * and calculates some statistics based upon the user guesses.
 * 
 * Provides several methods to come up with a hidden code to be broken based on a user entered seed.
 * Then takes user input, and determines if it matches some criteria. Compares each user input to
 * the hidden code. Uses more methods to determine all possible remaining guesses, and provide some
 * useful statistics to the user.
 * 
 * @Author Tejvir Mann
 */
public class Mastermind {

    /**
     * Prompts the user for a value by displaying prompt. Note: This method should not add a new
     * line to the output of prompt.
     *
     * After prompting the user, the method will consume an entire line of input while reading an
     * int. Leading whitespace is ignored. If the value read is between min and max (inclusive),
     * that value is returned. Otherwise, output "Expected value between 0 and 10." where 0 and 10
     * are the values in the min and max parameters, respectively. Invalid input may be non-integer
     * in which case the same error message is displayed and the user is prompted again.
     * 
     * Note: This is a general purpose method to prompt for, read and validate an int within the min
     * and max. This method should be tested for any min and max.
     *
     * @param input  The Scanner instance to read from System.in.
     * @param prompt Output to the user.
     * @param min    The minimum acceptable int value (inclusive).
     * @param min    The maximum acceptable int value (inclusive).
     * @return Returns the value read from the user.
     */
    public static int promptInt(Scanner input, String prompt, int min, int max) {
        int seed; // variable to store user input

        // do while loop always prompts + checks for int input, loops until receives proper input
        do {
            System.out.print(prompt);
            if (input.hasNextInt()) {
                seed = input.nextInt();
                if ((seed >= min) && (seed <= max)) {
                    if (input.hasNextLine()) {
                        input.nextLine();
                    }
                    break;
                }
            } else {
                if (input.hasNextLine()) {
                    input.nextLine();
                }
                System.out.println("Expected value between " + min + " and " + max + ".");
            }
        } while (true);
        return seed;
    }

    /**
     * Returns the index within arr of the first occurrence of the specified character.
     * If arr is null or 0 length then return -1. For all arrays, don't assume a length
     * but use the array .length attribute.
     * @param arr  The array to look through.
     * @param ch   The character to look for.
     * @return The index within the array of the first occurrence of the specified
     *     character or -1 if the character is not found in the array.
     */
    public static int indexOf(char[] arr, char ch) {
        int index = -1;
        if (arr == null) {
            System.out.print("Exception : null");
            return index;
        }

        // loop iterates thru arr, until it find first index equal to char passed into method
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == ch) {
                index = i;
                break;
            }
        }
        return index;
    }

    /**
     * Generates the hidden code to be guessed by the user. The hidden code
     * returned is an array of characters with length numPositions.
     * The characters in the array are randomly chosen, in order starting at index 0,
     * from the symbols array.
     *    rand.nextInt( symbols.length)
     * is used to determine the index in the symbols array of each character
     * in the code. For all arrays, don't assume a length but use the array .length attribute.
     * 
     * Example: 
     * if numPositions is 3 and symbols is the array {'A','B','C'}
     * the returned array will have a length of 3 and may contain any selection of 
     * the available symbols such as {'B','C','B'} or {'C','A','B'}.
     * 
     * @param rand A random number generator.
     * @param numPositions  The number of symbols in the code.
     * @param symbols  The symbols to choose from.
     * @return An array of length numPositions of randomly chosen symbols.
     */
    public static char[] generateHiddenCode(Random rand, int numPositions, char[] symbols) {
        int i;
        int j;
        char k;
        char[] gameCode = new char[numPositions];

        // fills array numPositions with random chars taken from symbols array
        for (i = 0; i < numPositions; i++) {
            j = rand.nextInt(symbols.length);
            k = symbols[j];
            gameCode[i] = k;
        }
        return gameCode;
    }

    /**
     * Checks whether the code is the correct length and only contains valid symbols.
     * Uses the indexOf method you wrote to check whether each character in the input is in the 
     * symbols array.  If code or symbols are null then false is returned.
     * For all arrays, don't assume a length but use the array .length attribute.
     *   
     * @param numPositions  The required number of symbols in the code.
     * @param symbols  The allowed symbols in the code.
     * @param code  The code that is being checked.
     * @return true if the code is the correct length and has only valid symbols otherwise
     * returns false.
     */
    public static boolean isValidCode(int numPositions, char[] symbols, char[] code) {
        if (code.length != numPositions) {
            return false;
        }

        // iterates thru all indices of array code to ensure they match chars passed in by symbols
        // array
        for (int i = 0; i < code.length; i++) {
            if (indexOf(symbols, code[i]) == -1) {
                return false;
            }
        }
        return true;
    }

    /**
     * Prompts the user for a string value by displaying prompt.
     * Note: This method should not add a new line to the output of prompt. 
     *
     * After prompting the user, the method will read an entire line of input and remove
     * leading and trailing whitespace. If the line equals the single character '?'
     * then return null. If the line is a valid code (determine with isValidCode) return
     * the code, otherwise print "Invalid code." and prompt again.
     *
     * @param input The Scanner instance to read from System.in
     * @param prompt The user prompt.
     * @param numPositions The number of code positions.
     * @param symbols The valid symbols.
     * @return Returns null or a valid code.
     */
    public static char[] promptForGuess(Scanner input, String prompt, int numPositions,
        char[] symbols) {
        String userGuess = null;
        char[] guess = new char[numPositions];
        while (true) {

            System.out.print(prompt);
            userGuess = input.nextLine().trim();
            guess = userGuess.toCharArray();

            if (isValidCode(numPositions, symbols, guess)) {
                break;
            } else if (userGuess.equals("?")) {
                return null;
            } else {
                System.out.println("Invalid code.");
            }
        }
        return guess;
    }

    /**
     * Returns the sum of "black hits" and "white hits" between the hiddenCode 
     * and guess.  A "black hit" indicates a matching symbol in the same position in the
     * hiddenCode and guess.  A "white hit" indicates a matching symbol but different
     * position in the hiddenCode and guess that is not already accounted for with other 
     * hits.
     * 
     * Algorithm to determine the total number of hits:
     * 
     * Count the number of each symbol in the hiddenCode, and separately count the
     * number of each symbol in the guess. For each symbol, determine the minimum of the
     * count of that symbol in the hiddenCode and the count of that symbol found in the guess.  
     * The total number of hits, black and white, is the sum of these minimums for 
     * all the symbols.
     * 
     * Algorithm based on Donald Knuth, 1976, The Computer As Master Mind, 
     *      J. Recreational Mathematics, Vol. 9(1)
     *      
     * Suggestion: To do the count, create an array of int the length of the number of symbols.
     * For each symbol use the indexOf method you wrote to determine the index in the array to increment
     * the symbols count.
     *  
     * @param hiddenCode The code hidden from the user.
     * @param guess  The user's guess of the code.
     * @param symbols  The possible symbols in the hiddenCode and guess.
     * @return The total number of hits.
     */
    public static int countAllHits(char[] hiddenCode, char[] guess, char[] symbols) {
        int i;
        int j;
        int k;
        int hits = 0;
        int[] hitsGuess = new int[symbols.length];
        int[] hitsHiddenCode = new int[symbols.length];

        // nested for loop. checks how many times each char from symbols array appears in both the
        // userGuess, and the hiddenCode. Two arrays parallel to symbols are populated with the
        // number of occurrences of that char in the symbols array
        for (i = 0; i < symbols.length; i++) {
            for (j = 0; j < guess.length; j++) {
                if (guess[j] == symbols[i]) {
                    hitsGuess[i]++;
                }
                if (hiddenCode[j] == symbols[i]) {
                    hitsHiddenCode[i]++;
                }
            }
        }
        for (k = 0; k < symbols.length; k++) {
            hits += java.lang.Math.min(hitsGuess[k], hitsHiddenCode[k]);
        }

        return hits;
    }

    /**
     * Returns the number of each kind of hit the guess has with the code. 
     * The results are an array of length Config.HITS_ARRAY_LENGTH. 
     * The count of the number of symbols in the guess that correspond in position 
     * and symbol with the hidden code are recorded in the Config.BLACK_HITS_INDEX 
     * position within the result array. The number of symbols that match between
     * the guess and the hidden code but are in different positions and not otherwise
     * counted are recorded in the Config.WHITE_HITS_INDEX within the result array.
     * 
     * Algorithm:
     * Count the number of black hits - the number of positions in the guess and hidden code
     * that have the same symbol.
     * Count the total number of hits using countAllHits and subtract to find the number
     * of white hits. White hits are symbols that match between guess and hiddenCode that
     * are not in the same position and not already accounted for with other hits.
     * 
     * @param hiddenCode  The code the user is trying to guess.
     * @param guess  The user's guess.
     * @param symbols  The possible symbols in the hiddenCode and guess.
     * @return The array containing the number of "black hits" and "white hits".
     */
    public static int[] determineHits(char[] hiddenCode, char[] guess, char[] symbols) {
        int i;
        int[] hits = new int[Config.HITS_ARRAY_LENGTH]; // [black, white]

        // loop find the number of black hits
        for (i = 0; i < guess.length; i++) {
            if (hiddenCode[i] == guess[i]) {
                hits[0]++;
            }
        }
        hits[1] = (countAllHits(hiddenCode, guess, symbols)) - hits[0];

        return hits;
    }

    /**
     * Prints out the game board showing the guesses and the corresponding hits.
     * See output examples. 
     * Game board example:
     *  6) [4, 5, 2, 4] BBBB
     *  5) [4, 4, 2, 5] BBWW
     *  4) [4, 4, 2, 4] BBB
     *  3) [1, 3, 3, 3] 
     *  2) [2, 3, 3, 3] W
     *  1) [1, 1, 2, 2] B
     * 
     * Only rows with non-null guesses are shown. The number on the left is the guess, 
     * so the guesses are shown from last to first.
     * Looking at one line in detail:
     *  5) [4, 4, 2, 5] BBWW
     * 				      ^^  2 white hits, the 2nd 4 and 5 (we don't know which until solved)
     *                  ^^ 2 black hits, the 1st 4 and 2 (we don't know which until solved)
     *     ^^^^^^^^^^^^ the guess output using Arrays.toString()
     *  ^^ the guess number
     * The symbols B and W are the characters from Config.BLACK_HIT_SYMBOL and
     * Config.WHITE_HIT_SYMBOL. All the black hits will be shown before the white hits.
     * The length of all arrays should be determined using the array .length attribute, not
     * assumed from a constant.
     * 
     * @param guesses  The array of guesses. Each row is a guess or null (meaning no guess
     * yet).
     * @param hits  The array of hits. Each row is the hits from determineHits for 
     * the corresponding guess in the parallel guesses array, or null.
     */
    public static void printBoard(char[][] guesses, int[][] hits) {
        int i;
        int j;
        int l = 0;
        int m;
        int n;
        int o;
        char blackHits = Config.BLACK_HITS_SYMBOL;
        char whiteHits = Config.WHITE_HITS_SYMBOL;

        // loop iterates through array guesses, and prints the guess char arrays until it finds an
        // unpopulated part of the array
        for (i = 0; i < guesses.length; ++i) {
            o = 0;
            if (guesses[i][0] != '\u0000') {
                for (n = 0; n < guesses.length; n++) {
                    if (guesses[n][0] != '\u0000') {
                        o++;
                    } else {
                        break;
                    }
                }
                System.out.print(" " + (o - i) + ")");
                System.out.print(" " + Arrays.toString(guesses[o - i - 1]) + " ");

                while (l < o) {
                    for (j = 0; j < hits[o - l - 1][0]; ++j) {
                        System.out.print("" + blackHits);
                    }
                    for (m = 0; m < hits[o - l - 1][1]; ++m) {
                        System.out.print("" + whiteHits);
                    }
                    l++;
                    break;
                }
            } else {
                break;
            }
            System.out.println("");
        }
    }

    /**
     * The MasterMind main method that contains the welcome and the main game
     * loop. Carefully examine example output to help answer questions on prompts and
     * how this program should work. 
     * 
     * Algorithm:
     * Use appropriate constants from Config. For example, to create an array use Config.MAX_GUESSES,
     *     but once an array exists don't use the constants but use the array .length attribute.
     * Determine seed or not (call promptInt with Integer.MIN_VALUE, Integer.MAX_VALUE)
     * Display welcome message.
     * Generate the hidden code (call generateHiddenCode)
     * Create 2D arrays for guesses and corresponding hits. Initially every row is null
     *     until guesses are made and hits are determined for a guess.
     * (milestone 3) enumerate all the possibilities (call enumeratePossibilities)
     * Loop
     *     Prompt for guess (call promptForGuess)
     *     (milestone 3) If guess is null then call computerGuess
     *     Determine how many black and white hits (call determineHits)
     *     Output the board (call printBoard)
     *     (milestone 3) Output number of remaining possibilities
     * End loop when won or lost  
     * Output won or lost message.
     * 
     * @param args  unused
     */
    public static void main(String[] args) {
        String seedPrompt = "Enter seed (0 for unrepeatable): ";
        String guessPrompt = "Enter guess (? for help): ";
        Scanner scnr = new Scanner(System.in);
        int seed = promptInt(scnr, seedPrompt, -2147483648, 2147483647);
        Random rand0 = new Random();
        Random rand = new Random(seed);

        char[][] guesses = new char[Config.MAX_GUESSES][Config.CODE_POSITIONS]; // stores all guess
        int[][] hits = new int[Config.MAX_GUESSES][Config.HITS_ARRAY_LENGTH]; // stores all BW hits
        char[] hiddenCode = new char[Config.CODE_POSITIONS]; // stores hidden code
        int[] win = {Config.CODE_POSITIONS, 0};
        char[][] possibilities = enumeratePossibilities(Config.CODE_POSITIONS, Config.CODE_SYMBOLS);
        int possibleRemain;
        int hints = 0;

        if (seed == 0) {
            hiddenCode = generateHiddenCode(rand0, Config.CODE_POSITIONS, Config.CODE_SYMBOLS);
        } else {
            hiddenCode = generateHiddenCode(rand, Config.CODE_POSITIONS, Config.CODE_SYMBOLS);
        }

        System.out.println("Welcome to Master Mind!");
        System.out.print("I have a " + Config.CODE_POSITIONS + " symbol code using [");
        for (int i = 0; i < Config.CODE_SYMBOLS.length - 1; i++) {
            System.out.print(Config.CODE_SYMBOLS[i] + ", ");
        }
        System.out.println(Config.CODE_SYMBOLS[Config.CODE_SYMBOLS.length - 1] + "].");
        System.out.println("Can you guess my code within " + Config.MAX_GUESSES + " guesses?");

        // this loop runs the game by calling all of the necessary methods, and populating the
        // arrays to store each guess, and all the hits from those guesses.
        for (int j = 0; j < Config.MAX_GUESSES; j++) {
            guesses[j] =
                promptForGuess(scnr, guessPrompt, Config.CODE_POSITIONS, Config.CODE_SYMBOLS);
            if (guesses[j] == null) {
                guesses[j] = computerGuess(possibilities);
                hints++;
            }
            hits[j] = determineHits(hiddenCode, guesses[j], Config.CODE_SYMBOLS);

            updatePossibilities(guesses[j], hits[j], possibilities, Config.CODE_SYMBOLS);
            possibleRemain = 0;

            for (int k = 0; k < possibilities.length; k++) {
                if (possibilities[k] != null) {
                    possibleRemain++;
                }
            }

            printBoard(guesses, hits);
            System.out.println("remaining possibilities: " + possibleRemain);

            if (Arrays.equals(hits[j], win)) {
                System.out.println("Congratulations! You guessed code with only " + (j + 1)
                    + " guesses and " + hints + " hints!");
                return;
            }
        }
        System.out.println("You lost, the code was: " + Arrays.toString(hiddenCode));
    }

    /**
     * Determine the next code in sequence given the ordered symbols and
     * a code. See MasterMindTests.testNextCode() method for various test cases.
     * Most significant positions are the left most, just like for a number 
     * such as 1234, where 1 is the most significant digit.
     * 
     * Consider how you would add 1 to 199. Work out on paper.  Now try with
     * the symbols A, B, C in that order. If you added B to BC what would
     * the result be? CA?
     * 
     * Algorithm:
     * Loop from least significant position to the most significant
     *     Find the index of the symbol
     *     if least significant position
     *         if last symbol then 
     *             set to first symbol and carry
     *         else set next symbol
     *     else 
     *         if carry and last symbol
     *             set to first symbol and keep carry set
     *         else if carry and not last symbol
     *             set next symbol, clear carry
     *         else 
     *             no carry, so keep symbol
     *         end if
     *     end if
     * End loop
     * If carry then prepend an additional symbol. Since, in decimal, leading 0's 
     * are assumed then we assume the same for any symbols. So, we would prepend
     * the 2nd symbol, in decimal a 1.   
     *     
     * @param code   A code with the symbols.
     * @param symbols  The symbols to use for the code.
     * @return  The next code in the sequence based on the order of the symbols.
     */
    public static char[] nextCode(char[] code, char[] symbols) {
        boolean carry = false;
        char[] nextCode = new char[code.length];

        // iterates thru the char array code to change one or more of the chars to find the next
        // code as specified
        for (int i = code.length - 1; i >= 0; i--) {
            if (i == code.length - 1) {
                if (code[i] == symbols[symbols.length - 1]) {
                    nextCode[i] = symbols[0];
                    carry = true;
                } else {
                    nextCode[i] = symbols[indexOf(symbols, code[i]) + 1];
                }
            } else {
                if (carry && (code[i] == symbols[symbols.length - 1])) {
                    nextCode[i] = symbols[0];
                } else if (carry && (code[i] != symbols[symbols.length - 1])) {
                    nextCode[i] = symbols[indexOf(symbols, code[i]) + 1];
                    carry = false;
                } else {
                    nextCode[i] = code[i];
                }
            }
        }
        if (carry) {
            char[] nextCodeExpand = new char[nextCode.length + 1];
            for (int j = nextCode.length; j > 0; j--) {
                nextCodeExpand[j] = nextCode[j - 1];
            }
            nextCodeExpand[0] = symbols[1];
            return nextCodeExpand;
        }
        return nextCode;
    }

    /**
     * List all the possibilities (permutations) of codes for the specified number of 
     * positions and the provided codes.
     * 
     * Algorithm:
     * Create an array the length being the number of possibilities (permutations). 
     *     For example, 3 symbols in each of 4 positions means there are 3*3*3*3 or 3^4 
     *     which equals 81 permutations.
     * Determine the "first" code (all positions having the same first symbol).
     * For each permutation call nextCode to generate the next code from the current.
     * 
     * If numPositions is less than or equal to 0 or symbols is 0 length or null
     * then return null.
     * 
     * @param numPositions The number of positions in a code.
     * @param symbols The possible symbols used in a code.
     * @return An array of all the possible codes that can be generated from the 
     * symbols for the numPositions.
     */
    public static char[][] enumeratePossibilities(int numPositions, char[] symbols) {
        if ((numPositions <= 0) || (symbols.length == 0) || (symbols[0] == '\u0000')) {
            return null;
        }
        int numPossible = (int) Math.pow(symbols.length, numPositions);
        char[][] allCodes = new char[numPossible][];
        char[] firstCode = new char[numPositions];

        // populates char arrray firstCode with the first char from symbols
        for (int i = 0; i < numPositions; i++) {
            firstCode[i] = symbols[0];
        }
        allCodes[0] = firstCode;

        // populates each index of allCode with char arrays determined by the nextCode method
        for (int i = 1; i < numPossible; i++) {
            allCodes[i] = nextCode(allCodes[i - 1], symbols);
        }
        return allCodes;
    }

    /**
     * Updates the remaining possibilities array and returns the number
     * of possibilities.
     * The hiddenCodeHits parameter contains the black and white hits when the guess is compared 
     * to the code. The possibilities parameter contains all the possible remaining candidates
     * for the hidden code. Determine the hits for each non-null guess when compared to each 
     * possibility and only keep the possibilities that match the result parameter hits.
     * Remove a possibility from the array of possibilities by setting it to null.
     * 
     * @param guess  The current guess
     * @param hiddenCodeHits  The hits when guess is compared to hiddenCode.
     * @param possibilities The remaining codes that contain the hidden code.
     * @param symbols The potential symbols in the codes.
     * @return The number of remaining possibilities.
     */
    public static int updatePossibilities(char[] guess, int[] hiddenCodeHits,
        char[][] possibilities, char[] symbols) {
        int numRemain = possibilities.length;

        // sets possibilities to zero if they have already been fulfilled.
        for (int i = 0; i < possibilities.length; i++) {
            if (possibilities[i] == null) {
                continue;
            }
            if (!(Arrays.equals(determineHits(possibilities[i], guess, symbols), hiddenCodeHits))) {
                possibilities[i] = null;
                numRemain--;
            }
        }
        return numRemain;
    }

    /**
     * Select the first remaining code (lowest index) from possibilities.
     * If no codes are left then return null.
     * 
     * @param possibilities The array of remaining possible codes.
     * @return A code from the array.
     */
    public static char[] computerGuess(char[][] possibilities) {

        // iterates thru possibilities until a non null array is found
        for (int i = 0; i < possibilities.length; i++) {
            if (possibilities[i] != null) {
                return possibilities[i];
            }
        }
        return null;
    }
}



