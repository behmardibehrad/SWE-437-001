/**
 * Author: Pisith Yim & Behrad Behmardi
 * SWE437
 * Professor:
 * Test class for SWE437HW2
 *
 */

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.*;
import static org.junit.Assert.*;

public class TestSWE4372 {

    QuoteSaxParser qParser;
    QuoteList quoteList;
    List<String> words;

    /**
     * Prefix and Postfix
     */
    @Before public void setUp(){
        qParser  = new QuoteSaxParser ("src/quotes.xml");
        quoteList = qParser.getQuoteList();
        words = new ArrayList<>();
    }

    @After public void tearDown(){
        qParser = null;
        quoteList = null;
    }

    /**
     * Test if search is found
     * Should pass if the keyword is found
     * It has low observability, especially if we have to search from a database with hundreds of records
     * It's easier to control the input by changing the input in the test
     */
    @Test
    public void testSearchNotEmpty(){
        QuoteList searchedList = quoteList.search("Behrad", 0);
        assertFalse(Arrays.asList(searchedList).isEmpty());
    }

    /**
     * Test if search is not found
     * Should pass if nothing return from the search
     * It has low observability, especially if we have to search from a database with hundreds of records
     * It's easier to control the input by changing the input in the test
     */
    @Test
    public void testSearchForEmpty(){
        QuoteList emptyList = quoteList.search("homeefeojo", 0);
        assertFalse(emptyList.getSize() != 0);
    }

    /**
     * Test for nullpointerexecption when passing null argument
     * Should pass if null argument passed in
     * It has low observability, especially if we have to search from a database with hundreds of records
     * It's easier to control the input by changing the input in the test
     */
    @Test(expected = NullPointerException.class)
    public void testSearchNullString(){
        QuoteList nullString = quoteList.search(null, 0);
        System.out.println(nullString);
    }

    /**
     * Test for nullpointerexception is the list is null
     * Should pass if the list is null
     * It has low observability, especially if we have to search from a database with hundreds of records
     * It's easier to control the input by changing the input in the test
     */
    @Test(expected = NullPointerException.class)
    public void testSearchNullList(){
        quoteList = null;
        QuoteList invalidArgs = quoteList.search("Berad", 2);
        System.out.println(invalidArgs);
    }

    /**
     * Test if wrong mode argument was passed in
     * List should be empty accoding to search function
     * Therefore should pass if list size if 0
     * It has low observability, especially if we have to search from a database with hundreds of records
     * It's easier to control the input by changing the input in the test
     */
    @Test
    public void testSearchInvalidMode(){
        QuoteList invalidMode = quoteList.search("Berad", 6);
        assertEquals(0, invalidMode.getSize());
    }

    /**
     * Test with string object or empty string
     * Since any quotes contain empty string somewhere
     * This should return all the quotes back
     * It has low observability, especially if we have to search from a database with hundreds of records
     * It's easier to control the input by changing the input in the test
     */
    @Test
    public void testSearchInvalidArgs(){
        String objStr = new String();
        System.out.println(objStr.length());
        QuoteList  objArg= quoteList.search(objStr, 1);
        assertEquals(10, objArg.getSize());
    }
}
