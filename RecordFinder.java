public class RecordFinder {
    
    //Public Method - the "front door" anyone can call
    public Book findBook(Book root, int isbnCode) {
        return searchRecursive(root, isbnCode);
    }

    //Private Method - the actual recursive search logic
    private Book searchRecursive(Book current, int isbnCode) {

        //Base case 1: Reached empty spot - book not found
        if (current == null) {
            System.out.println("Book with ISBN " + isbnCode + " not found.");
            return null;
        }

        //Base case 2: Found the book!
        if (isbnCode == current.getIsbnCode()) {
            System.out.println("\n=== Book Found ===");
            System.out.println(current);
            return current;
        }

        //Recursive case: Go LEFT if smaller
        if (isbnCode < current.getIsbnCode()) {
            return searchRecursive(current.getLeft(), isbnCode);
        }

        //Recursive case: Go RIGHT if bigger
        else {
            return searchRecursive(current.getRight(), isbnCode);
        }
    }
}
