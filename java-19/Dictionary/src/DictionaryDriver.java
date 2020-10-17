// File Header comes Here
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * This class implements a Simple Dictionary Driver Application Using DictionaryBST class
 * 
 * @author Mouna
 *
 */

public class DictionaryDriver {

  /**
   * Helper method to print the application menu
   */
  private static void printMenu() {
    String userCommands = "\n=========================== Dictionary ============================\n"
        + "Enter one of the following options:\n"
        + "[A  ] to add a new word and its definition in the dictionary\n"
        + "[L ] to search a word in the dictionary and display its definition\n"
        + "[G] to print all the words in the dictionary in sorted order\n"
        + "[S] to get the count of all words in the dictionary\n"
        + "[H] to get the height of this dictionary implemented as a binary search tree\n"
        + "[Q] to quit the program\n"
        + "======================================================================\n";
    System.out.println(userCommands);
  }

  /**
   * print out all the web pages present in the search engine in the ascendant order
   */
  private static void printAllDictionaryWords(DictionaryBST dictionary) {
    if (dictionary.isEmpty()) // empty dictionary
      System.out.println("Dictionary is empty.");
    else { // non-empty dictionary
      // Get all dictionary words and print them
      ArrayList<String> words = dictionary.getAllWords();
      StringBuilder result = new StringBuilder();
      if (words != null && words.size() != 0) { // if dictionary is not empty, this condition should
                                                // be satisfied. [optional condition]
        for (String word : words) { // traverse the words collection
          result.append(word + ", ");
        }
      }
      // remove ", " after the last word
      System.out.println(result.toString().substring(0, result.length() - 2));
    }
  }

  /**
   * Process user command lines with respect to the menu
   * @param dictionary - a reference to a DictionaryBST object
   * @param scanner - scanner object to read user input commands
   */
  private static void processUserCommands(DictionaryBST dictionary, Scanner scanner) {
    boolean quit = false; // checks whether to quit this method or not

    while (!quit) {
      // print menu and prompt user command line
      printMenu(); 
      System.out.print("Please enter your command: ");
      String[] input = scanner.nextLine().trim().split("\\s+", 3);
      // process user command line
      if (input != null && input.length >= 1) {
        String command = input[0].toLowerCase();
        switch (command) {
          case "a": // add a word definition to the dictionary
            if (input.length == 3) // check for command syntax
              try {
                if (!dictionary.addWord(input[1], input[2])) {
                  System.out.println("WARNING: failed to add duplicate word: " + input[1]);
                }
              } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
              }
            else // syntax error -- display an error message
              System.out.println("WARNING: Syntax Error for [A  ] command line.");
            break;
          case "l": // lookup for a word meaning
            if (input.length == 2) { // check for command syntax
              try {
                String meaning = dictionary.lookup(input[1]);
                if (meaning != null && !meaning.isEmpty()) {
                  System.out.println(input[1] + ": " + meaning); // word found, print out its meaning
                }
              } catch (NoSuchElementException e) { // word not found
                System.out.println("No definition found for the word " + input[1]);
              }

            } else // syntax error -- display an error message
              System.out.println("WARNING: Syntax Error for [L ] command line.");
            break;
          case "g": // get all dictionary words sorted
            if (input.length == 1)
              printAllDictionaryWords(dictionary);
            else
              System.out.println("WARNING: Syntax Error.");
            break;
          case "s": // get the size of the dictionary
            if (input.length == 1)
              System.out.println(dictionary.size());
            else
              System.out.println("WARNING: Syntax Error.");
            break;
          case "h": // get the height of the Dictionary BST
            if (input.length == 1)
              System.out.println(dictionary.height());
            else
              System.out.println("WARNING: Syntax Error.");
            break;
          case "q": // quit the program
            if (input.length == 1) {
              System.out.println(
                  "============================== END ===================================");
              quit = true;
            } else
              System.out.println("WARNING: Syntax Error.");
            break;
          default:
            System.out.println("WARNING: Unrecognized command.");
            break;
        }
      } else
        System.out.println("WARNING: Unrecognized command.");
    }

  }



  /**
   * Main method that launchs the Dictionary Driver Application 
   * @param args input arguments if any
   */
  public static void main(String[] args) {
    DictionaryBST dictionary = new DictionaryBST(); // creates a new empty DictionaryBST
    Scanner scanner = new Scanner(System.in); // creates a Scanner object to read user input
    processUserCommands(dictionary, scanner); // read and process user input commands
    if (scanner != null) // close scanner if it is not null
      scanner.close();
  }
}