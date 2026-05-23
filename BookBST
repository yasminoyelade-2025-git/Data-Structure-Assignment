public class BookBST {

    private Book root;

    // constructor
    public BookBST() {
        root = null;
    }

    // insert book into BST
    public void insert(int isbnCode,
                       String bookTitle,
                       String authorName) {

        root = insertRecursive(
                root,
                isbnCode,
                bookTitle,
                authorName
        );
    }

    // recursive insert
    private Book insertRecursive(Book current,
                                 int isbnCode,
                                 String bookTitle,
                                 String authorName) {

        // create new node if empty
        if (current == null) {

            System.out.println("Book inserted successfully.");

            return new Book(
                    isbnCode,
                    bookTitle,
                    authorName
            );
        }

        // prevent duplicate ISBN
        if (isbnCode == current.getIsbnCode()) {

            System.out.println("Duplicate ISBN not allowed.");

            return current;
        }

        // smaller ISBN goes left
        if (isbnCode < current.getIsbnCode()) {

            current.setLeft(

                    insertRecursive(
                            current.getLeft(),
                            isbnCode,
                            bookTitle,
                            authorName
                    )
            );
        }

        // larger ISBN goes right
        else {

            current.setRight(

                    insertRecursive(
                            current.getRight(),
                            isbnCode,
                            bookTitle,
                            authorName
                    )
            );
        }

        return current;
    }

    // display all books in sorted order
    public void displayBooks() {

        if (root == null) {

            System.out.println("Library is empty.");

            return;
        }

        System.out.println("\n=== BOOK LIST ===");

        inorderRecursive(root);
    }

    // inorder traversal
    private void inorderRecursive(Book current) {

        if (current != null) {

            inorderRecursive(current.getLeft());

            System.out.println(current);

            inorderRecursive(current.getRight());
        }
    }

    // return root node
    public Book getRoot() {
        return root;
    }
}
