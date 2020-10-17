

	/**
	 * This file contains testing methods for the CSVParser project.
	 * These methods are intended to serve several purposes:
	 * 1) provide an example of a way to incrementally test your code
	 * 2) provide example method calls for the various methods in CSVParser
	 * 3) provide examples of creating and accessing arrays
	 */

	import java.util.Arrays;

	/**
	 * This class contains methods for testing CSVParser methods as they are developed or modified.
	 * These methods are all private as they are only intended for use within this class.
	 * 
	 * @author Jim Williams
	 * @author FIXME add your name here if you modify or extend the tests
	 *
	 */
	public class TestCSVParser {
	    
	    /**
	     * This is the main method that runs the various tests. Uncomment the tests
	     * when you are ready for them to run.
	     * 
	     * @param args  (unused)
	     */
	    public static void main(String[] args) {
	      testParserPart1();
	      testParserPart2();
	      testParserPart3();
	    }
	    
	    /**
	     * A testing method for part 1 of CSV Parser project
	     * Part 1 implementation separates based on comma and then generalizes to any character, but does not consider "
	     * or any qualifier characters.
	     */
	    private static void testParserPart1() {
	        boolean error = false;

	        String input = null;
	        String[] expectedResult = new String[0];
	        String[] actualResult = CSVParser.separate(input);
	        if (checkForError("testParserPart1.1", expectedResult, actualResult)) {
	            error = true;
	        }

	        input = "";
	        expectedResult = new String[] {""};
	        actualResult = CSVParser.separate(input);
	        if (checkForError("testParserPart1.2", expectedResult, actualResult)) {
	            error = true;
	        }

	        input = " a ";
	        expectedResult = new String[] {" a "};
	        actualResult = CSVParser.separate(input);
	        if (checkForError("testParserPart1.3", expectedResult, actualResult)) {
	            error = true;
	        }

	        input = "aaa,bbb,ccc";
	        expectedResult = new String[] {"aaa", "bbb", "ccc"};
	        actualResult = CSVParser.separate(input);
	        if (checkForError("testParserPart1.4", expectedResult, actualResult)) {
	            error = true;
	        }

	        input = "aaa,bbb,";
	        expectedResult = new String[] {"aaa", "bbb", ""};
	        actualResult = CSVParser.separate(input);
	        if (checkForError("testParserPart1.5", expectedResult, actualResult)) {
	            error = true;
	        }

	        input = " aa a, bbb , cc c ";
	        expectedResult = new String[] {" aa a", " bbb ", " cc c "};
	        actualResult = CSVParser.separate(input);
	        if (checkForError("testParserPart1.6", expectedResult, actualResult)) {
	            error = true;
	        }

	        input = "aaa,bbb,ccc\nzzz,yyy,xxx";
	        expectedResult = new String[] {"aaa,bbb,ccc", "zzz,yyy,xxx"};
	        actualResult = CSVParser.separate(input, '\n');
	        if (checkForError("testParserPart1.7", expectedResult, actualResult)) {
	            error = true;
	        }

	        if (error) {
	            System.err.println("testParserPart1 failed");
	        } else {
	            System.out.println("testParserPart1 passed");
	        }
	    }
	    
	    /**
	     * A testing method for part 2 of CSV Parser project
	     * Part 2 methods add a qualifier, such as ", so that delimiter characters can be within field values.
	     *
	     */
	    private static void testParserPart2() {
	        boolean error = false;

	        String input = null;
	        String[] expectedResult = new String[0];
	        String[] actualResult = CSVParser.separate(input, ',', '"');
	        if (checkForError("testParserPart2.1", expectedResult, actualResult)) {
	            error = true;
	        }

	        input = "";
	        expectedResult = new String[] {""};
	        actualResult = CSVParser.separate(input, ',', '"');
	        if (checkForError("testParserPart2.2", expectedResult, actualResult)) {
	            error = true;
	        }

	        input = " a ";
	        expectedResult = new String[] {" a "};
	        actualResult = CSVParser.separate(input, ',', '"');
	        if (checkForError("testParserPart2.3", expectedResult, actualResult)) {
	            error = true;
	        }

	        input = "aaa,bbb,ccc";
	        expectedResult = new String[] {"aaa", "bbb", "ccc"};
	        actualResult = CSVParser.separate(input, ',', '"');
	        if (checkForError("testParserPart2.4", expectedResult, actualResult)) {
	            error = true;
	        }

	        input = "aaa,bbb,";
	        expectedResult = new String[] {"aaa", "bbb", ""};
	        actualResult = CSVParser.separate(input, ',', '"');
	        if (checkForError("testParserPart2.5", expectedResult, actualResult)) {
	            error = true;
	        }

	        input = " aa a, bbb , cc c ";
	        expectedResult = new String[] {" aa a", " bbb ", " cc c "};
	        actualResult = CSVParser.separate(input, ',', '"');
	        if (checkForError("testParserPart2.6", expectedResult, actualResult)) {
	            error = true;
	        }

	        input = "aaa,bbb,ccc\nzzz,yyy,xxx";
	        expectedResult = new String[] {"aaa,bbb,ccc", "zzz,yyy,xxx"};
	        actualResult = CSVParser.separate(input, '\n', '"');
	        if (checkForError("testParserPart2.7", expectedResult, actualResult)) {
	            error = true;
	        }


	        input = "field_name,field_name,field_name\naaa,bbb,ccc\nzzz,yyy,xxx";
	        expectedResult =
	            new String[] {"field_name,field_name,field_name", "aaa,bbb,ccc", "zzz,yyy,xxx"};
	        actualResult = CSVParser.separate(input, '\n', '"');
	        if (checkForError("testParserPart2.8", expectedResult, actualResult)) {
	            error = true;
	        }

	        input = "\"aaa\",\"bbb\",\"ccc\""; // recall in Java, to have " within String literals we
	                                           // have to precede with \
	        expectedResult = new String[] {"\"aaa\"", "\"bbb\"", "\"ccc\""};
	        actualResult = CSVParser.separate(input, ',', '"');
	        if (checkForError("testParserPart2.9", expectedResult, actualResult)) {
	            error = true;
	        }
// here is the new stuff you need to account for 
	        input = "\"a, aa\",\"b ,bb\",\"cc , c\"";
	        expectedResult = new String[] {"\"a, aa\"", "\"b ,bb\"", "\"cc , c\""};
	        actualResult = CSVParser.separate(input, ',', '"');
	        if (checkForError("testParserPart2.10", expectedResult, actualResult)) {
	            error = true;
	        }

	        input = "\"a\"\",aa\",\"b ,\"\"bb\",\"cc , c\"";
	        expectedResult = new String[] {"\"a\"\",aa\"", "\"b ,\"\"bb\"", "\"cc , c\""};
	        actualResult = CSVParser.separate(input, ',', '"');
	        if (checkForError("testParserPart2.11", expectedResult, actualResult)) {
	            error = true;
	        }

	        input = "\"aaa\",\"b\n bb\",\"ccc\"";
	        expectedResult = new String[] {"\"aaa\"", "\"b\n bb\"", "\"ccc\""};
	        actualResult = CSVParser.separate(input, ',', '"');
	        if (checkForError("testParserPart2.12", expectedResult, actualResult)) {
	            error = true;
	        }

	        input = "\"John Doe, MD\", 1234567, 321";
	        expectedResult = new String[] {"\"John Doe, MD\"", " 1234567", " 321"};
	        actualResult = CSVParser.separate(input, ',', '"');
	        if (checkForError("testParserPart2.13", expectedResult, actualResult)) {
	            error = true;
	        }

	        if (error) {
	            System.err.println("testParserPart2 failed");
	        } else {
	            System.out.println("testParserPart2 passed");
	        }
	    }

	    /**
	     * A testing method for part 3 of CSV Parser project
	     * Part 3 methods add a field keepQualifiers that specify whether the field values should
	     * contain the qualifier characters or not.
	     *
	     */ 
	    private static void testParserPart3() {
	        boolean error = false;

	        String input = null;
	        String[] expectedResult = new String[0];
	        String[] actualResult = CSVParser.separate(input, ',', '"', false);
	        if (checkForError("testParserPart3.1", expectedResult, actualResult)) {
	            error = true;
	        }

	        input = "";
	        expectedResult = new String[] {""};
	        actualResult = CSVParser.separate(input, ',', '"', false);
	        if (checkForError("testParserPart3.2", expectedResult, actualResult)) {
	            error = true;
	        }

	        input = " a ";
	        expectedResult = new String[] {" a "};
	        actualResult = CSVParser.separate(input, ',', '"', false);
	        if (checkForError("testParserPart3.3", expectedResult, actualResult)) {
	            error = true;
	        }

	        input = "aaa,bbb,ccc";
	        expectedResult = new String[] {"aaa", "bbb", "ccc"};
	        actualResult = CSVParser.separate(input, ',', '"', false);
	        if (checkForError("testParserPart3.4", expectedResult, actualResult)) {
	            error = true;
	        }

	        input = "aaa,bbb,";
	        expectedResult = new String[] {"aaa", "bbb", ""};
	        actualResult = CSVParser.separate(input, ',', '"', false);
	        if (checkForError("testParserPart3.5", expectedResult, actualResult)) {
	            error = true;
	        }

	        input = " aa a, bbb , cc c ";
	        expectedResult = new String[] {" aa a", " bbb ", " cc c "};
	        actualResult = CSVParser.separate(input, ',', '"', false);
	        if (checkForError("testParserPart3.6", expectedResult, actualResult)) {
	            error = true;
	        }

	        input = "aaa,bbb,ccc\nzzz,yyy,xxx";
	        expectedResult = new String[] {"aaa,bbb,ccc", "zzz,yyy,xxx"};
	        actualResult = CSVParser.separate(input, '\n', '"', false);
	        if (checkForError("testParserPart3.7", expectedResult, actualResult)) {
	            error = true;
	        }

	        input = "field_name,field_name,field_name\naaa,bbb,ccc\nzzz,yyy,xxx";
	        expectedResult =
	            new String[] {"field_name,field_name,field_name", "aaa,bbb,ccc", "zzz,yyy,xxx"};
	        actualResult = CSVParser.separate(input, '\n', '"', false);
	        if (checkForError("testParserPart3.8", expectedResult, actualResult)) {
	            error = true;
	        }
//
	        input = "\"aaa\",\"bbb\",\"ccc\""; // recall in Java, to have " within String literals we
	                                           // have to precede with \
	        expectedResult = new String[] {"\"aaa\"", "\"bbb\"", "\"ccc\""};
	        actualResult = CSVParser.separate(input, ',', '"', true);
	        if (checkForError("testParserPart3.9T", expectedResult, actualResult)) {
	            error = true;
	        }

	        input = "\"aaa\",\"bbb\",\"ccc\""; // recall in Java, to have " within String literals we
	                                           // have to precede with \
	        expectedResult = new String[] {"aaa", "bbb", "ccc"};
	        actualResult = CSVParser.separate(input, ',', '"', false);
	        if (checkForError("testParserPart3.9F", expectedResult, actualResult)) {
	            error = true;
	        }

	        input = "\"a, aa\",\"b ,bb\",\"cc , c\"";
	        expectedResult = new String[] {"\"a, aa\"", "\"b ,bb\"", "\"cc , c\""};
	        actualResult = CSVParser.separate(input, ',', '"', true);
	        if (checkForError("testParserPart3.10T", expectedResult, actualResult)) {
	            error = true;
	        }

	        input = "\"a, aa\",\"b ,bb\",\"cc , c\"";
	        expectedResult = new String[] {"a, aa", "b ,bb", "cc , c"};
	        actualResult = CSVParser.separate(input, ',', '"', false);
	        if (checkForError("testParserPart3.10F", expectedResult, actualResult)) {
	            error = true;
	        }

	        input = "\"a\"\",aa\",\"b ,\"\"bb\",\"cc , c\"";
	        expectedResult = new String[] {"\"a\"\",aa\"", "\"b ,\"\"bb\"", "\"cc , c\""};
	        actualResult = CSVParser.separate(input, ',', '"', true);
	        if (checkForError("testParserPart3.11T", expectedResult, actualResult)) {
	            error = true;
	        }

	        input = "\"a\"\",aa\",\"b ,\"\"bb\",\"cc , c\"";
	        expectedResult = new String[] {"a\",aa", "b ,\"bb", "cc , c"};
	        actualResult = CSVParser.separate(input, ',', '"', false);
	        if (checkForError("testParserPart3.11F", expectedResult, actualResult)) {
	            error = true;
	        }

	        input = "\"aaa\",\"b\n bb\",\"ccc\"";
	        expectedResult = new String[] {"\"aaa\"", "\"b\n bb\"", "\"ccc\""};
	        actualResult = CSVParser.separate(input, ',', '"', true);
	        if (checkForError("testParserPart3.12T", expectedResult, actualResult)) {
	            error = true;
	        }

	        input = "\"aaa\",\"b\n bb\",\"ccc\"";
	        expectedResult = new String[] {"aaa", "b\n bb", "ccc"};
	        actualResult = CSVParser.separate(input, ',', '"', false);
	        if (checkForError("testParserPart3.12F", expectedResult, actualResult)) {
	            error = true;
	        }

	        input = "\"John Doe, MD\", 1234567, 321";
	        expectedResult = new String[] {"\"John Doe, MD\"", " 1234567", " 321"};
	        actualResult = CSVParser.separate(input, ',', '"', true);
	        if (checkForError("testParserPart3.13T", expectedResult, actualResult)) {
	            error = true;
	        }

	        input = "\"John Doe, MD\", 1234567, 321";
	        expectedResult = new String[] {"John Doe, MD", " 1234567", " 321"};
	        actualResult = CSVParser.separate(input, ',', '"', false);
	        if (checkForError("testParserPart3.13F", expectedResult, actualResult)) {
	            error = true;
	        }

	        if (error) {
	            System.err.println("testParserPart3 failed");
	        } else {
	            System.out.println("testParserPart3 passed");
	        }
	    }
	    
	    /**
	     * A supporting test method that compares the expected and actual results of calling a method.
	     * This method returns true if a test fails and false if the test case succeeds.
	     * 
	     * @param testName The name of the test to include in the output
	     * @param expected  The expected return value from the separate method.
	     * @param actual The actual return value from the separate method.
	     * @return  true if error, false if no error detected.
	     */
	    private static boolean checkForError( String testName, String[] expected, String[] actual) {
	        boolean error = false;
	        if (expected == null || actual == null) {
	            System.err.println(
	                testName + "a: actual=" + (actual == null ? "null" : Arrays.toString(actual))
	                + " expected=" + (expected == null ? "null" : Arrays.toString(expected)));
	            error = true;
	        } else if (expected.length != actual.length) {
	            System.err.println(testName + "b: actual.length=" + actual.length + " expected.length="
	                + expected.length);
	            System.err.println(testName + "b: actual=" + Arrays.toString(actual) + " expected="
	                + Arrays.toString(expected));
	            error = true;
	        }
	        for (int i = 0; !error && i < expected.length; i++) {
	            if (!expected[i].equals(actual[i])) {
	                error = true;
	                System.err.println(testName + "c: actual[" + i + "]=" + actual[i] + " expected[" + i
	                    + "]=" + expected[i]);
	            }
	        }
	        return error;
	    }    
	    
	}
	
	
	
	

