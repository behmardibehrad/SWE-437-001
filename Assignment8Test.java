import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import com.gargoylesoftware.htmlunit.javascript.host.dom.Document;
import com.gargoylesoftware.htmlunit.xml.XmlPage;

import net.sourceforge.htmlunit.corejs.javascript.json.JsonParser;

public class Assignment8Test {


	QuoteList quoteList;
	final String author  = "author";
	final String quote   = "quote";
	final String both    = "both";
	final String quoteServerURL = "https://cs.gmu.edu:8443/offutt/servlet/quotes.quoteserve";
	final List<String> searchStringArray = new ArrayList<String>();
	final List<String> modeArray = new ArrayList<String>();


	@Before public void setUp(){
		modeArray.add(author);
		modeArray.add(quote);
		modeArray.add(both);
		searchStringArray.add("John Keats");
		searchStringArray.add("Eschew obfuscation!");
		searchStringArray.add("none-exist");
	}




	@Test
	public void TestQuoteListsearch () throws FailingHttpStatusCodeException, MalformedURLException, IOException, InterruptedException {
		try (final WebClient webClient = new WebClient()) {
			HtmlPage page = webClient.getPage(quoteServerURL);
			final HtmlForm form = page.getFormByName("quoteServ");
			final HtmlSubmitInput button = form.getInputByName("submit");
			HtmlTextInput searchText = form.getInputByName("searchText");
			HtmlInput searchScope = form.getInputByName("searchScope");
			/*
			for(String searchInput : searchStringArray) {
				searchText.click();
				searchText.setValueAttribute(searchInput);

				for(String modeInput:modeArray) {
					searchScope.setAttribute("searchScope", modeInput);
					page = button.click();
					webClient.waitForBackgroundJavaScript(600);
					HtmlElement resultQuote = (HtmlElement) page.getElementsByTagName("dt").get(0);
					HtmlElement resultAuthor = (HtmlElement) page.getElementsByTagName("dd").get(0);
					System.out.println("resultAuthor: " +resultAuthor.asText());
					System.out.println("resultQuote: " +resultQuote.asText());
					if(searchInput.equals("John Keats") ) {
						if(modeInput.equals("author")) {
							Assert.assertEquals("Nothing ever becomes real till it is experienced; even a proverb is no proverb to you till your life has illustrated it.", resultQuote.asText());
							Assert.assertEquals("—John Keats", resultAuthor.asText());
						}//else 
							//if(modeInput.equals("quote")) 

					}


				}
			}
			 */
			searchText.click();
			searchText.setValueAttribute(searchStringArray.get(2));

			searchScope.setAttribute("searchScope", modeArray.get(1));
			page = button.click();

			webClient.waitForBackgroundJavaScript(600);
			//HtmlElement resultQuote = (HtmlElement) page.getElementsByTagName("dt").get(0);
			//HtmlElement resultAuthor = (HtmlElement) page.getElementsByTagName("dd").get(0);
			//System.out.println("resultAuthor: " +resultAuthor.asText());
			//System.out.println("resultQuote: " +resultQuote.asText());
			//org.jsoup.nodes.Document doc = Jsoup.connect("https://cs.gmu.edu:8443/offutt/servlet/quotes.quoteserve").get();
			//XmlPage xmlpage = page.getXmlVersion();

			System.out.println(page.asText().contains("did not match any quotes"));












		}
	}

}
