import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

public class SWE437HW2{	

	static QuoteList quoteList;								// instance of QuoteList
	static HashMap<String, QuoteList> savedSearches = new HashMap<>();			// instance of HashMap to save user's searches

	public static void main(String[] args) {

		/**
		 * Prints the main menu
		 * waits for the user's selection
		 * if user selects a valid item calls userAnswer(int) to determine the selection
		 * makes calls to PrintSearchMenu() and generateRandomQuote()
		 * if user selects an invalid option
		 * prints error and loops back to main menu
		 */
		public static void printMenu() {
			Scanner keyboard = new Scanner(System.in);  					// Creating a new Scanner named keyboard
			int mainMenuUsersChoice = 0;							// User's Answer
			System.out.println("Please select from the following options:\n");		// Printing Menu options for user
			System.out.println("1. Get a random quote\t(press 1)");				// Printing Menu options for user
			System.out.println("2. Search\t\t(press 2)");					// Printing Menu options for user
			System.out.println("3. Exit\t\t\t(press 3)");					// Printing Menu options for user
			if (keyboard.hasNextInt()) 							// if user selects an integer
			{
				mainMenuUsersChoice = keyboard.nextInt(); 				// Scan users input as an int
				if(userAnswer(mainMenuUsersChoice)==1) {				// if user selects 1
					generateRandomQuote();						// generate a random quote
					printMenu();}							// print the main menu
				else if(userAnswer(mainMenuUsersChoice)==2){				// if user selects 2
					PrintSearchMenu();}						// print the search menu
			}
			else										// if user doesnt select an integer
			{
				System.out.println("Invaid Input!\n");					// print Invaid Input
				printMenu();								// go back to main menu
			}
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

			}
			System.out.println("\n");
		}

	}
}


