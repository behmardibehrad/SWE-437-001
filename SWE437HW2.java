import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

/* New Import*/
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.*;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.*;
import java.io.*;

public class SWE437HW2{	

	static QuoteList quoteList;								// instance of QuoteList
	static HashMap<String, QuoteList> savedSearches = new HashMap<>();		// instance of HashMap to save user's searches
    static QuoteSaxParser qParser;
	public static void main(String[] args) {
		qParser = new QuoteSaxParser ("quotes.xml");			// reading the quotes.xml
		quoteList = qParser.getQuoteList();					// saving the quotes.xml to quotelist
		printMenue();									// print main menu
	}

	/**
	 * Prints the main menu
	 * waits for the user's selection 
	 * if user selects a valid item calls userAnswer(int) to determine the selection
	 * makes calls to PrintSearchMenu() and generateRandomQuote()
	 * if user selects an invalid option
	 * prints error and loops back to main menu
	 */
	public static void printMenue() {
		Scanner keyboard = new Scanner(System.in);  					// Creating a new Scanner named keyboard
		int mainMenuUsersChoice = 0;							// User's Answer
		System.out.println("Please select from the following options:\n");		// Printing Menu options for user
		System.out.println("1. Get a random quote\t(press 1)");				// Printing Menu options for user
		System.out.println("2. Search\t\t(press 2)");					// Printing Menu options for user
		System.out.println("3. Add new quote\t(press 3)");				// Add new quote
		System.out.println("4. Exit\t\t\t(press 4)");					// Printing Menu options for user
		if (keyboard.hasNextInt()) 							// if user selects an integer
		{											
			mainMenuUsersChoice = keyboard.nextInt(); 				// Scan users input as an int
			if(userAnswer(mainMenuUsersChoice)==1) {				// if user selects 1
				generateRandomQuote();						// generate a random quote
				printMenue();}							// print the main menu 										
			else if(userAnswer(mainMenuUsersChoice)==2){				// if user selects 2
				PrintSearchMenu();}						// print the search menu
			else if(userAnswer(mainMenuUsersChoice) == 3){
				addQuoteMenu();
			}
		}
		else										// if user doesnt select an integer
		{
			System.out.println("Invaid Input!\n");					// print Invaid Input
			printMenue();								// go back to main menu
		}
	}


	/**
	 * takes the user selection as input
	 * determines the user input 1 or 2 or 3 
	 * if user's input is a valid option
	 * returns the user's input
	 * else prints an error and goes back to main menu
	 */
	public static int userAnswer(int userAnswer) {
		int returnValue = 0;								// the return value
		switch(userAnswer){								// check the user's input
		case 1:										// if user selected 1
			returnValue= 1;								// sets the returnValue to 1	
			break;									// breaks out of switch
		case 2:										// if user selected 2
			returnValue = 2;							// sets the returnValue to 2
			break;									// breaks out of switch
		case 3:
			returnValue = 3;
			break;
		case 4:										// if user selected 3
			System.exit(0);								// exits the program
		default:									// default
			System.out.println("Invalid Input!\n");					// prints Invalid Input
			printMenue();}								// goes back to main menu
		return returnValue;								// returns the returnValue
	}

	/**
	 * Add quote menu function
	 * Calling helper function for adding quote
	 */
	public static void addQuoteMenu(){
		Scanner keyboard = new Scanner(System.in);
		String author, quoteText;
		System.out.println("Please enter author name:");
		author = keyboard.nextLine();
		System.out.println("Please enter quote text:");
		quoteText = keyboard.nextLine();
		if(addQuote(author, quoteText)){
			System.out.println("Quote has been successfully added to the list");
		}
		printMenue();
	}

	/**
	 * Helper function TO add new quote to the list
	 * @returns boolean if successful
	 */
	public static boolean addQuote(String author, String quoteText){
		Quote newQuote = new Quote(author, quoteText);
		quoteList.setQuote(newQuote);
		return saveToXml("quotes.xml", quoteList);

	}

	public static boolean saveToXml(String xmlFile, QuoteList pList){
		Document dom;
		Element quote = null;
		Element author = null;
		Element quoteText = null;
		Quote tempQuote;
		String tempAuthor, tempText;
		// instance of a DocumentBuilderFactory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try {
			// use factory to get an instance of document builder
			DocumentBuilder db = dbf.newDocumentBuilder();
			// create instance of DOM
			dom = db.newDocument();

			// create the root element
			Element rootEle = dom.createElement("quote-list");

			for(int i = 0; i<pList.getSize(); i++){
				tempQuote = new Quote();
				tempQuote = pList.getQuote(i);
				// create data elements and place them under root
				quote = dom.createElement("quote");
				author = dom.createElement("author");
				author.appendChild(dom.createTextNode(tempQuote.getAuthor()));
				quoteText = dom.createElement("quote-text");
				quoteText.appendChild(dom.createTextNode(tempQuote.getQuoteText()));
				quote.appendChild(quoteText);
				quote.appendChild(author);

				rootEle.appendChild(quote);
			}

			dom.appendChild(rootEle);

			try {
				Transformer tr = TransformerFactory.newInstance().newTransformer();
				tr.setOutputProperty(OutputKeys.INDENT, "yes");
				tr.setOutputProperty(OutputKeys.METHOD, "xml");
				tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
				//tr.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "roles.dtd");
				tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

				// send DOM to file
				tr.transform(new DOMSource(dom),
						new StreamResult(new FileOutputStream(xmlFile)));
				return true;

			} catch (TransformerException te) {
				System.out.println(te.getMessage());
			} catch (IOException ioe) {
				System.out.println(ioe.getMessage());
			}
		} catch (ParserConfigurationException pce) {
			System.out.println("UsersXML: Error trying to instantiate DocumentBuilder " + pce);
		}
		return false;
	}


	/**
	 * This was taken directly from the quoteserver.java class
	 * Slightly modified 
	 * changed out.println to System.out.println
	 * deleted all the html related codes
	 */
	private void printSearch (PrintWriter out, String searchText, String searchScope) {
		if (searchText != null && !searchText.equals(""))
		{  // Received a search request
			int searchScopeInt = QuoteList.SearchBothVal; // Default
			if (searchScope != null && !searchScope.equals(""))
			{  // If no parameter value, let it default.
				if (searchScope.equals ("quote"))
				{
					searchScopeInt = QuoteList.SearchTextVal;
				} else if (searchScope.equals ("author"))
				{
					searchScopeInt = QuoteList.SearchAuthorVal;
				} else if (searchScope.equals ("both"))
				{
					searchScopeInt = QuoteList.SearchBothVal;
				}
			}

			QuoteList searchRes = quoteList.search (searchText, searchScopeInt);
			Quote quoteTmp;
			if (searchRes.getSize() == 0)
			{
				System.out.println("Your search "+ searchText +" did not match any quotes.");
			}
			else
			{
				System.out.println ("\n");
				for (int i = 0; i < searchRes.getSize() ; i++)
				{
					quoteTmp = searchRes.getQuote(i);
					System.out.println("\n" + quoteTmp.getQuoteText() + "\n");
					System.out.println("\n" + quoteTmp.getAuthor() + "\n");
				}
				System.out.println("\n");
			}
		}
		System.out.println("\n");
	}

	/**
	 * void function to generates random quote 
	 * uses the getRandomQuote() method in Quote class
	 */
	public static void generateRandomQuote() {
		Quote quoteTmp = quoteList.getRandomQuote();							// new instance of Quote, assigns random quote to it
		System.out.println("\n");									// prints new line
		System.out.println ("Random quote of the day:");						// prints Random quote of the day
		System.out.println ("\n"+quoteTmp.getQuoteText());						// prints quote text
		System.out.println ("\n" + quoteTmp.getAuthor());						// prints quote author
		System.out.println ("\n");									// prints new line
	}


	/**
	 * prints the search menu which is the subset of main menu( option 2)
	 * saves the searched terms and results in hash-map, displays it on entry
	 * takes the user input first, then displays 3 options for searching user's input
	 * if user selects a valid option then it searches and display the results
	 * if user select invalid option goes back to main menu 
	 */
	public static void PrintSearchMenu() {
		Quote quoteTmp1;											// create a new Quote
		System.out.println("***************Most Recent Searches***************");				// prints Most Recent Searches
		for (Entry<String, QuoteList> entry : savedSearches.entrySet()) {					// for every entry in saved searches
			System.out.println("searched term: "+ entry.getKey() + "\n");					// prints the user searched term
			for(int i=0; i<entry.getValue().getSize(); i++) {						// for the user searched term
				System.out.println(entry.getValue().getQuote(i).getAuthor()+"\n");			// prints the resulted quote's author
				System.out.println(entry.getValue().getQuote(i).getQuoteText()+"\n");			// prints the resulted quote text
			}
		}
		System.out.println("**************************************************");

		Scanner keyboard = new Scanner(System.in);  								// Reading from keyboard
		QuoteList returnQuotes = new QuoteList();								// new QuoteList to store the search result
		String searchText = "";											// saves the user entered search term
		int searchOption = 0;											// saves the user search criteria 
		System.out.println(" Please Enter the search term:");							// prints a msg for user input
		searchText = keyboard.nextLine();									// reads the user input and saves it to searchText
		System.out.println("Please select from the following options: ");					// prints a msg for user
		System.out.println("1. Search by Author\t\t(press 1)");							// prints a msg for user
		System.out.println("2. Search by quote\t\t(press 2)");							// prints a msg for user
		System.out.println("3. Both\t\t\t\t(press 3)");								// prints a msg for user
		System.out.println("4. Back to Main Menu\t\t(press 4)");						// prints a msg for user
		searchOption = keyboard.nextInt();									// takes the user criteria
		switch(searchOption){											// switch for determining user's criteria selections

		case 1:													// if user select 1
			returnQuotes = quoteList.search(searchText, 0);							// cross check the term for author's name
			savedSearches.put(searchText, returnQuotes);							// save the result to display later for saved searches
			Quote quoteTmp;											// new quote
			for (int i = 0; i < returnQuotes.getSize() ; i++){						// loops through all the results
				quoteTmp = returnQuotes.getQuote(i);							// assign the result to quote
				System.out.println("\n" + quoteTmp.getQuoteText() + "\n");				// display the quote text
				System.out.println (quoteTmp.getAuthor() + "\n");}					// display the quote author
			break;												// break out of switch 
		case 2:													// if user select 2
			returnQuotes =quoteList.search(searchText, 1);							// cross check the term for quotes text 
			savedSearches.put(searchText, returnQuotes);							// save the result to display later for saved searches
			for (int i = 0; i < returnQuotes.getSize() ; i++){						// loops through all the results
				quoteTmp = returnQuotes.getQuote(i);							// assign the result to quote
				System.out.println("\n" + quoteTmp.getQuoteText() + "\n");				// display the quote text
				System.out.println (quoteTmp.getAuthor() + "\n");}					// display the quote author
			break;												// break out of switch 
		case 3:													// if user select 3
			returnQuotes =quoteList.search(searchText, 2);							// cross check the term for both author's name and quote text
			savedSearches.put(searchText, returnQuotes);							// save the result to display later for saved searches
			for (int i = 0; i < returnQuotes.getSize() ; i++){						// loops through all the results
				quoteTmp = returnQuotes.getQuote(i);							// assign the result to quote
				System.out.println("\n" + quoteTmp.getQuoteText() + "\n");				// display the quote text
				System.out.println (quoteTmp.getAuthor() + "\n");}					// display the quote author
			break;												// breaks out of switch
		case 4:													// if user select 4
			printMenue();											// goes back to main menu
			break;												// breaks out of switch

		default:												// if none of the above is selected
			System.out.println("Invaid Input!\n");								// prints Invaid Input
			PrintSearchMenu();										// prints the search menu again
		}													// when done withsearch 
		printMenue();												// goes back to main menu

	}
}








