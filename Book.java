public class Book {
    
    //declaration
    private int isbnCode;
    private String authorName;
    private String bookTitle;
    private Book left;
    private Book right;
    
    //constructor
    public Book(int isbnCode, String bookTitle, String authorName) {
        
        this.isbnCode = isbnCode;
        this.bookTitle = bookTitle;
        this.authorName = authorName;
        
    }
    
    //getter
    public int getIsbnCode() {
        return isbnCode;
    }
    
    public String getAuthorName() {
        return authorName;
    }
    
    public String getBookTitle() {
        return bookTitle;
    }
    
    public Book getLeft() {
        return left;
    }
    
    public Book getRight() {
        return right;
    }
    
    //setter
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
    
    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }
    
    public void setLeft(Book left) {
        this.left = left;
    }
    
    public void setRight(Book right) {
        this.right = right;
    }
    
    //all to string
    public String toString() {
        return "ISBN: " + isbnCode + "\n" +
                "Title: " + bookTitle + "\n" +
                "Author: " + authorName + "\n";
    }
}
