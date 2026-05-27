public class RecordFinder {

    public Book findBook(Book root,
                         int isbnCode) {

        return searchRecursive(root, isbnCode);
    }

    private Book searchRecursive(Book current,
                                 int isbnCode) {

        if (current == null) {
            return null;
        }

        if (isbnCode == current.getIsbnCode()) {
            return current;
        }

        if (isbnCode < current.getIsbnCode()) {

            return searchRecursive(
                    current.getLeft(),
                    isbnCode
            );
        }

        return searchRecursive(
                current.getRight(),
                isbnCode
        );
    }

    public void searchByTitle(Book current,
                              String keyword) {

        if (current != null) {

            searchByTitle(current.getLeft(), keyword);

            if (current.getBookTitle()
                    .toLowerCase()
                    .contains(keyword.toLowerCase())) {

                System.out.println(current);

                System.out.println(
                        "-----------------------------"
                );
            }

            searchByTitle(current.getRight(), keyword);
        }
    }
}