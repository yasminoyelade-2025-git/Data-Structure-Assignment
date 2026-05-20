interface LibraryADT {
    
    //adding book by ISBN code, book title and name of author
    void addBook(int isbnCode, String bookTitle, String authorName);
    
    //search book by ISBN code
    void searchBook(int isbnCode);
    
    //recording movement of book
    void borrowBook(int isbnCode);
    
    //display recently viewed book
    void viewBorrowingHistory();
    
}
