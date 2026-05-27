public class MostBorrowedTracker {

    private Book mostBorrowedBook;

    public void findMostBorrowed(Book current) {

        if (current != null) {

            findMostBorrowed(current.getLeft());

            if (mostBorrowedBook == null ||
                current.getBorrowCount() >
                mostBorrowedBook.getBorrowCount()) {

                mostBorrowedBook = current;
            }

            findMostBorrowed(current.getRight());
        }
    }

    public void displayResult() {

        if (mostBorrowedBook == null) {

            System.out.println(
                    "No borrowing data available."
            );

            return;
        }

        System.out.println(
                "\n======= MOST BORROWED BOOK ======="
        );

        System.out.println(mostBorrowedBook);
    }
}