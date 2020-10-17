//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           CSVParser
// Files:           CSVParser.java TestCSVParser.java
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


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

//FIXME  Add your own file header comment here


/**
 * This class contains methods to parse text, specifically intended to eventually parse CSV (comma separated values) 
 * files. 
 * 
 * Definitions of Terms: 
 * delimiter: The character used to specify the boundary between fields in a sequence of characters.
 *            for example, the comma would be the delimiter between the fields:  
 *              name, id, section
 *            Note the field values include the spaces. In general, the field value is every character except
 *            for the delimiter.
 *            
 * qualifier: If a field value contains a delimiter such as a comma with the name field: 
 *              John Doe, MD, 1234567, 321
 *            then a qualifier, such as ", is placed around a field value to indicate it is the same field.
 *            for example: 
 *              "John Doe, MD", 1234567, 321
 *            If the qualifier itself is a part of the field value then the qualifier is duplicated.
 *            for example for the field value:
 *              H. Jackson Brown, Jr. said "The best preparation for tomorrow is doing your best today."
 *            would be enclosed with " and internal quotes duplicated resulting in the qualified field value:
 *              "H. Jackson Brown, Jr. said ""The best preparation for tomorrow is doing your best today."""
 *              
 *            A example with , as delimiter and " as qualifier with a newline \n in a field value should be 
 *            processed as follows:
 *              "This is a\nline of text.",bbb,ccc
 *            would have 3 fields
 *              "This is a\nline of text."
 *              bbb
 *              ccc
 *              
 * This project is derived from RFC 4180: https://tools.ietf.org/html/rfc4180
 *            
 */
public class CSVParser {
    
    /**
     * This method returns the array of string containing the field values.
     * The field values in text are identified using , as the delimiter.
     * Qualifiers are not considered at all in the parsing of text.
     * 
     * Example:
     *   aaa,bbb,ccc
     * should result in an array with 3 fields
     *   aaa
     *   bbb
     *   ccc 
     * 
     *   aaa,bbb,
     * should also result in an array with 3 fields with the 3rd field containing either whitespace or no spaces
     * depending on the actual characters after the last ,.
     * 
     * If text is null then a zero length array is returned. If text is non-null
     * but no delimiters are found then a single length array is returned with the text as the field value.
     * 
     * @param text  A sequence of characters that may include , as a delimiter.
     * @return An array of String containing each field value.  The size of the
     *    array is the number of fields found.
     */
    public static String[] separate(String text) {
    	
    	
    	ArrayList<String> elements = new ArrayList<String>();       
    	StringBuilder elementBuilder = new StringBuilder();
    	String[] ifNull = new String[0];
    	String curVal = " ";
    	   
    	if (text == null) {
           	return ifNull;
           }
    	  
    	if (!(text.contains(",")) && !(text.contains("\n"))) { 
    		curVal = text; 
           	elements.add(curVal);
           	String[] array = elements.toArray(new String[elements.size()]);
            return array;
          }

    	   boolean isQuoted = false;
    	   for (char c : text.toCharArray()) {
    	     
    	     if (c == ',') {    //','
    	        elements.add(elementBuilder.toString());// .trim());
    	        elementBuilder = new StringBuilder();
    	        continue;
  
    	     }
    	     elementBuilder.append(c); 
    	   
    	   }
    	   elements.add(elementBuilder.toString()); //.trim()); 
    	   
    	   if (!(text.contains("\n"))) {
	        	if (elements.size() == 2 ) {
	        	elements.add("");
	        	}
	       
    	   }
    	   
           String[] array = elements.toArray(new String[elements.size()]);
    	   return array;
    }
    
    /**
     * This method is the same as separate(String text) except that any character can be used as the delimiter
     * specified as a parameter. In this method, qualifiers are not considered at all in the parsing of text.
     * 
     * Example:
     *   aaa,bbb,ccc\nddd,eee,fff
     * with \n as the delimiter should result in an array with 2 fields
     *   aaa,bbb,ccc
     *   ddd,eee,fff
     * 
     * @param text  A sequence of characters.
     * @param delimiter The character that separates field values in text.
     * @return An array of String containing each field value.  The size of the
     *    array is the number of fields found.
     *    
     */ 
    public static String[] separate(String text, char delimiter) {
    	
    	ArrayList<String> elements = new ArrayList<String>();       
    	StringBuilder elementBuilder = new StringBuilder();
    	String[] ifNull = new String[0];
    	String curVal = " ";
    	   
    	if (text == null) {
           	return ifNull;
           }
    	  
    	if (!(text.contains(",")) && !(text.contains("\n"))) { 
    		curVal = text; 
           	elements.add(curVal);
           	String[] array = elements.toArray(new String[elements.size()]);
            return array;
          }

    	   boolean isQuoted = false;
    	   for (char c : text.toCharArray()) {
    	     
    	     if (c == delimiter) {    //','
    	        elements.add(elementBuilder.toString());// .trim());
    	        elementBuilder = new StringBuilder();
    	        continue;
  
    	     }
    	     elementBuilder.append(c); 
    	   
    	   }
    	   elements.add(elementBuilder.toString()); //.trim()); 
    	   
    	   if (!(text.contains("\n"))) {
	        	if (elements.size() == 2 ) {
	        	elements.add("");
	        	}
	       
    	   }
    	   
           String[] array = elements.toArray(new String[elements.size()]);
    	   return array;
    	   
    }


    /**
     * This method extends the previous separate methods in that qualifiers are now considered. See the class
     * header comment for examples of qualifier. Qualifiers are kept as a part of the field values.
     * 
     * Example:
     *    "H. Jackson Brown, Jr. said ""The best preparation for tomorrow is doing your best today.""",bbb
     * With delimiter , and qualifier " would result in the following field values:
     *    "H. Jackson Brown, Jr. said ""The best preparation for tomorrow is doing your best today."""
     *    bbb   
     * 
     * @param text  A sequence of characters.
     * @param delimiter The character that separates field values in text.
     * @param qualifier The character indicating the beginning and ending of the field value such 
     *                  that delimiters could be within a field value.
     * @return An array of String containing each field value.  The size of the
     *    array is the number of fields found.
     *    
     */
    public static String[] separate(String text, char delimiter, char qualifier) {
    	
    	ArrayList<String> elements = new ArrayList<String>();       
    	StringBuilder elementBuilder = new StringBuilder();
    	String[] ifNull = new String[0];
    	String curVal = " ";
    	   
    	if (text == null) {
           	return ifNull;
           }
    	  
    	if (!(text.contains(",")) && !(text.contains("\n"))) { 
    		curVal = text; 
           	elements.add(curVal);
           	String[] array = elements.toArray(new String[elements.size()]);
            return array;
          }

    	   boolean isQuoted = false;
    	   for (char c : text.toCharArray()) {
    	     if (c == qualifier) {   //'\"'
    	        isQuoted = !isQuoted;
    	        // continue;        // changed according to the OP comment - \" shall not be skipped
    	     }
    	     if (c == delimiter && !isQuoted) {    //','
    	        elements.add(elementBuilder.toString());// .trim());
    	        elementBuilder = new StringBuilder();
    	        continue;
  
    	     }
    	     elementBuilder.append(c); 
    	   }
    	   elements.add(elementBuilder.toString()); //.trim()); 
    	   
    	   if (!(text.contains("\n"))) {
	        	if (elements.size() == 2 ) {
	        	elements.add("");
	        	}
	       }
    	   
    	   
           String[] array = elements.toArray(new String[elements.size()]);
    	   return array;
    	}
   
    
    /**
     * This method extends the previous by adding the parameter keepQualifiers.  If keepQualifiers is true
     * the returned field values include the qualifier characters.  If keepQualifiers is false then the
     * returned field values have the qualifier characters removed.
     * 
     * Example:
     *    "H. Jackson Brown, Jr. said ""The best preparation for tomorrow is doing your best today.""", bbb
     * With delimiter , and qualifier " and keepQualifiers false would result in the following field values:
     *    H. Jackson Brown, Jr. said "The best preparation for tomorrow is doing your best today."
     *     bbb
     * 
     * @param text  A sequence of characters.
     * @param delimiter The character that separates field values in text.
     * @param qualifier The character indicating the beginning and ending of the field value such 
     *                  that delimiters could be within a field value.
     * @param keepQualifiers true to keep qualifiers, false to remove qualifiers                 
     * @return An array of String containing each field value.  The size of the
     *    array is the number of fields found.
     */
    public static String[] separate(String text, char delimiter, char qualifier, boolean keepQualifiers) {

    	ArrayList<String> elements = new ArrayList<String>();       
    	StringBuilder elementBuilder = new StringBuilder();
    	String[] ifNull = new String[0];
    	String curVal = " ";
    	   
    	if (text == null) {
           	return ifNull;
           }
    	  
    	if (!(text.contains(",")) && !(text.contains("\n"))) { 
    		curVal = text; 
           	elements.add(curVal);
           	String[] array = elements.toArray(new String[elements.size()]);
            return array;
          }

    	   boolean isQuoted = false;
    	   for (char c : text.toCharArray()) {
    	     if (c == qualifier) {   //'\"'
    	        isQuoted = !isQuoted;
    	        // continue;        // changed according to the OP comment - \" shall not be skipped
    	     }
    	     if (c == delimiter && !isQuoted) {    //','
    	        elements.add(elementBuilder.toString());// .trim());
    	        elementBuilder = new StringBuilder();
    	        continue;
  
    	     }
    	     elementBuilder.append(c); 
    	   }
    	   elements.add(elementBuilder.toString()); //.trim()); 
    	   
    	   if (!(text.contains("\n"))) {
	        	if (elements.size() == 2 ) {
	        	elements.add("");
	        	}
	       }
    	   
    	   String s =String.valueOf(qualifier);  
    	   
    	   if(keepQualifiers == false) { 
    		   for( int i = 0; i<elements.size(); ++i) {
    			   if (elements.get(i).contains(s)){
    				   elements.get(i).replaceAll("\"", "");
    				   elements.get(i).replaceAll("\"" , "");
    				   //elements.get(i).replace(s, "");
    				   
    			   }
    		   }
    	   }
    	   
    	   
           String[] array = elements.toArray(new String[elements.size()]);
    	   return array;
    	}
   
}