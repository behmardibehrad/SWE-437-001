import java.util.ArrayList;

/**
 * Quote data object.
 * @author Mongkoldech Rajapakdee & Jeff offutt
 *         Date: Nov 2009
 * A quote has two parts, an author and a quoteText.
 * This bean class provides getters and setters for both, plus a toString()
 */
public class Quote
{
   private String author;
   private String quoteText;

   // Added for assign6
   private ArrayList<Keyword> keywords = new ArrayList<>();

   // Default constructor does nothing
   public Quote ()
   {
   }

   // Constructor that assigns both strings
   public Quote (String author, String quoteText)
   {
      this.author = author;
      this.quoteText = quoteText;

   }

   // Method for keywords arraylist
   public void addKeyword(Keyword keyword){
      keywords.add(keyword);
   }
   public void deleteKeyword(Keyword keyword){
      keywords.remove(keyword);
   }
   public boolean isKeyword(Keyword keyword){
      if(this.keywords.contains(keyword)){
         return true;
      }
      return false;
   }
   public ArrayList<Keyword> getAllKeywords() {
      return keywords;
   }

   // Getter and setter for author
   public String getAuthor ()
   {
      return author;
   }
   public void setAuthor (String author)
   {
      this.author = author;
   }

   // Getter and setter for quoteText
   public String getQuoteText ()
   {
      return quoteText;
   }
   public void setQuoteText (String quoteText)
   {
      this.quoteText = quoteText;
   }

   @Override
   public String toString ()
   {
      return "Quote {" + "author='" + author + '\'' + ", quoteText='" + quoteText + '\'' + '}';
   }
}
