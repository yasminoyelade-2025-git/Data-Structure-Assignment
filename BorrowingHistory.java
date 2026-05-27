public class BorrowingHistory {

    private Node top;

    private static class Node {

        Book book;
        Node next;

        public Node(Book book) {

            this.book = book;

            next = null;
        }
    }

    public void push(Book book) {

        Node newNode = new Node(book);

        newNode.next = top;

        top = newNode;
    }

    public Book pop() {

        if (top == null) {
            return null;
        }

        Book latest = top.book;

        top = top.next;

        return latest;
    }

    public void displayHistory() {

        if (top == null) {

            System.out.println(
                    "No borrowing history."
            );

            return;
        }

        Node current = top;

        while (current != null) {

            System.out.println(current.book);

            System.out.println(
                    "---------------------------"
            );

            current = current.next;
        }
    }
}