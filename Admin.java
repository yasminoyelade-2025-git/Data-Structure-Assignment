public class Admin {

    private BookBST catalogue;
    private BorrowingHistory history;
    private RecordFinder finder;

    public Admin(BookBST catalogue,
                 BorrowingHistory history,
                 RecordFinder finder) {

        this.catalogue = catalogue;
        this.history = history;
        this.finder = finder;
    }

    public void borrowBook(int isbnCode) {

        Book foundBook = finder.findBook(catalogue.getRoot(), isbnCode);

        if (foundBook == null) {
            System.out.println("Book not found in catalogue.");
            return;
        }

        history.push(foundBook);

        System.out.println("Book borrowed successfully.");
        System.out.println(foundBook);
    }

    public void returnLatestBook() {

        Book returnedBook = history.pop();

        if (returnedBook == null) {
            System.out.println("No borrowed books to return.");
            return;
        }

        System.out.println("Book returned successfully.");
        System.out.println(returnedBook);
    }
}