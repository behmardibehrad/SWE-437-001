/**
 * Author: Pisith Yim & Behrad Behmardi
 * SWE437
 * Professor:
 *
 */

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class TestAddKeywordToQuote {
    Keyword newKeyword;
    Quote newQuote;
    @Before public void setUp(){
        newKeyword = new Keyword();
        newKeyword.setKeyword("New keyword");
        newQuote = new Quote();
        newQuote.addKeyword(newKeyword);
    }

    @Test public void testAddToQuote(){
        assertTrue(newQuote.isKeyword(newKeyword));
    }

    @Test public void testDeleteKeywordFromQuote(){
        newQuote.deleteKeyword(newKeyword);
        assertFalse(newQuote.isKeyword(newKeyword));
    }

    @Test public void getAllKeywordsFromQuote(){
        Keyword firstKey = new Keyword();
        firstKey.setKeyword("First");
        Keyword secondKey = new Keyword();
        secondKey.setKeyword("Second");
        Keyword thirdKey = new Keyword();
        thirdKey.setKeyword("Third");
        newQuote.addKeyword(firstKey);
        newQuote.addKeyword(secondKey);
        newQuote.addKeyword(thirdKey);
        ArrayList<Keyword> result = newQuote.getAllKeywords();
        ArrayList<Keyword> testResult = new ArrayList<>();
        testResult.add(newKeyword);
        testResult.add(firstKey);
        testResult.add(secondKey);
        testResult.add(thirdKey);
        assertEquals(testResult, result);
    }

    @After public void tearDown(){
        newQuote = null;
        newKeyword = null;
    }
}
