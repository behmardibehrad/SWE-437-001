public class Keyword {

    public static int counter = -1;
    private String keyword;
    private int kid;

    public Keyword(){
        this.keyword = "";
        this.kid = counter;
        counter++;
    }
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
    public String getKeyword(){
        return this.keyword;
    }
    public String toString(){
        return this.kid + ": " + this.keyword;
    }

}
