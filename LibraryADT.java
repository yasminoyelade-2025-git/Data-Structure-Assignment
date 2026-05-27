public interface LibraryADT {

    void addBook(int isbnCode,
                 String title,
                 String author,
                 String category);

    void searchBook(int isbnCode);

    void searchBookByTitle(String keyword);

    void borrowBook(int isbnCode);

    void returnLatestBook();

    void deleteBook(int isbnCode);

    void viewBorrowingHistory();

    void displayAllBooks();

    void showMostBorrowedBook();

    void exportCatalogue();
}