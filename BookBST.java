public class BookBST {

    private Book root;

    public BookBST() {
        root = null;
    }

    public Book getRoot() {
        return root;
    }

    public boolean insert(int isbnCode,
                          String title,
                          String author,
                          String category) {

        if (searchDuplicate(root, isbnCode)) {

            System.out.println("Duplicate ISBN is not allowed.");

            return false;
        }

        root = insertRecursive(
                root,
                isbnCode,
                title,
                author,
                category
        );

        return true;
    }

    private Book insertRecursive(Book current,
                                 int isbnCode,
                                 String title,
                                 String author,
                                 String category) {

        if (current == null) {

            return new Book(
                    isbnCode,
                    title,
                    author,
                    category
            );
        }

        if (isbnCode < current.getIsbnCode()) {

            current.setLeft(
                    insertRecursive(
                            current.getLeft(),
                            isbnCode,
                            title,
                            author,
                            category
                    )
            );
        }

        else {

            current.setRight(
                    insertRecursive(
                            current.getRight(),
                            isbnCode,
                            title,
                            author,
                            category
                    )
            );
        }

        return current;
    }

    private boolean searchDuplicate(Book current,
                                    int isbnCode) {

        if (current == null) {
            return false;
        }

        if (isbnCode == current.getIsbnCode()) {
            return true;
        }

        if (isbnCode < current.getIsbnCode()) {

            return searchDuplicate(
                    current.getLeft(),
                    isbnCode
            );
        }

        return searchDuplicate(
                current.getRight(),
                isbnCode
        );
    }

    public Book delete(Book current,
                       int isbnCode) {

        if (current == null) {
            return null;
        }

        if (isbnCode < current.getIsbnCode()) {

            current.setLeft(
                    delete(
                            current.getLeft(),
                            isbnCode
                    )
            );
        }

        else if (isbnCode > current.getIsbnCode()) {

            current.setRight(
                    delete(
                            current.getRight(),
                            isbnCode
                    )
            );
        }

        else {

            if (current.getLeft() == null) {
                return current.getRight();
            }

            if (current.getRight() == null) {
                return current.getLeft();
            }

            Book smallest = findSmallest(current.getRight());

            current = smallest;

            current.setRight(
                    delete(
                            current.getRight(),
                            smallest.getIsbnCode()
                    )
            );
        }

        return current;
    }

    private Book findSmallest(Book current) {

        while (current.getLeft() != null) {
            current = current.getLeft();
        }

        return current;
    }

    public void deleteBook(int isbnCode) {

        root = delete(root, isbnCode);
    }

    public void displayBooks() {

        if (root == null) {

            System.out.println("Library catalogue is empty.");

            return;
        }

        System.out.println(
                "\n========== BOOK CATALOGUE =========="
        );

        inorderTraversal(root);
    }

    private void inorderTraversal(Book current) {

        if (current != null) {

            inorderTraversal(current.getLeft());

            System.out.println(current);

            System.out.println(
                    "-----------------------------------"
            );

            inorderTraversal(current.getRight());
        }
    }
}