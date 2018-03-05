
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

import static org.junit.Assert.assertEquals;

public class TestKeyword {
    int counter;
    Keyword newKeyword;
    @Before
    public void setUp(){
        counter = Keyword.counter;
        newKeyword = new Keyword();
    }
    @Test
    public void testCreateKeyWord(){
        newKeyword.setKeyword("New keyword");
        assertEquals(counter + ": New keyword", newKeyword.toString());
    }

    @Test
    public void testCreateDiffKeyWord(){
        newKeyword.setKeyword("Another keyword");
        assertEquals(counter + ": Another keyword", newKeyword.toString());
    }

    @After
    public void tearDown(){
        Keyword.counter = -1;
        newKeyword = null;
    }
}





