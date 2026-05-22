public class BorrowingHistory {
    private Node top;

    //stack node
    private static class Node {
        private Book book;
        private Node next;

        public Node(Book book) {
            this.book = book;
            this.next = null;
        }
    }

    //constructor
    public BorrowingHistory() {
        top = null;
    }

    //check if stack is empty
    public boolean isEmpty() {
        return top == null;
    }

    //push book onto stack (check null & borrow Book)
    public void push(Book book) {
        if (book == null) {
            System.out.println("Invalid book. Cannot add null book to history.");
            return;
        }

        Node newNode = new Node(book);
        newNode.next = top;
        top = newNode;

        System.out.println("Book added to borrowing history.");
    }

    //pop book from stack (return book)
    public Book pop() {
        if (isEmpty()) {
            System.out.println("Borrowing history is empty.");
            return null;
        }

        Book borrowedBook = top.book;
        top = top.next;
        return borrowedBook;
    }

    //peek at most recent borrowed book
    public Book peek() {
        if (isEmpty()) {
            System.out.println("Borrowing history is empty.");
            return null;
        }

        return top.book;
    }

    //display borrowing history (in LIFO order)
    public void displayHistory() {
        if (isEmpty()) {
            System.out.println("No borrowing history available.");
            return;
        }

        System.out.println("\n=== Borrowing History (Most Recent First) ===");

        Node current = top;
        int count = 1;

        while (current != null) {
            System.out.println(count + ". " + current.book);
            current = current.next;
            count++;
        }
    }
}
