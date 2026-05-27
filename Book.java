public class Book {

    private int isbnCode;
    private String authorName;
    private String bookTitle;
    private String category;

    private boolean borrowed;

    private int borrowCount;

    private String borrowDate;
    private String dueDate;

    private Book left;
    private Book right;

    public Book(int isbnCode,
                String bookTitle,
                String authorName,
                String category) {

        this.isbnCode = isbnCode;
        this.bookTitle = bookTitle;
        this.authorName = authorName;
        this.category = category;

        borrowed = false;

        borrowCount = 0;

        borrowDate = "-";
        dueDate = "-";
    }

    public int getIsbnCode() {
        return isbnCode;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public String getCategory() {
        return category;
    }

    public boolean isBorrowed() {
        return borrowed;
    }

    public int getBorrowCount() {
        return borrowCount;
    }

    public String getBorrowDate() {
        return borrowDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setBorrowed(boolean borrowed) {
        this.borrowed = borrowed;
    }

    public void increaseBorrowCount() {
        borrowCount++;
    }

    public void setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public Book getLeft() {
        return left;
    }

    public Book getRight() {
        return right;
    }

    public void setLeft(Book left) {
        this.left = left;
    }

    public void setRight(Book right) {
        this.right = right;
    }

    @Override
    public String toString() {

        String status;

        if (borrowed) {
            status = "Borrowed";
        }

        else {
            status = "Available";
        }

        return "ISBN       : " + isbnCode + "\n" +
               "Title      : " + bookTitle + "\n" +
               "Author     : " + authorName + "\n" +
               "Category   : " + category + "\n" +
               "Status     : " + status + "\n" +
               "Borrowed   : " + borrowCount + " times\n" +
               "BorrowDate : " + borrowDate + "\n" +
               "DueDate    : " + dueDate;
    }
}