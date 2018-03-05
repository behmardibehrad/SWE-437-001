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

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class TestSearchQuotes {
    QuoteSaxParser qParser = new QuoteSaxParser ("src/quotes.xml");
    QuoteList quoteList = qParser.getQuoteList();

    @Test public void testSearchQuotesByKeyword(){
        Keyword testKeyword = new Keyword();
        testKeyword.setKeyword("Test Driven Development");
        QuoteList expectedQuotes = new QuoteList();
        for(int i = 0; i < quoteList.getSize(); i++){
            String author = quoteList.getQuote(i).getAuthor();
            if(author.equalsIgnoreCase("Richard Nixon")
                    || author.equalsIgnoreCase("Don Cunningham")
                    || author.equalsIgnoreCase("Ramsey Clark")){
                expectedQuotes.setQuote(quoteList.getQuote(i));
                quoteList.getQuote(i).addKeyword(testKeyword);
            }
        }
        QuoteList actualQuotes = quoteList.searchQuotesByKeyword("Test Driven Development");
        assertEquals(expectedQuotes.getQuoteArray(), actualQuotes.getQuoteArray());

    }
}

