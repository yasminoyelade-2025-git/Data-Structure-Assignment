import java.util.Scanner;
import java.io.*;

public class LibrarySystem implements LibraryADT {

    private BookBST catalogue;

    private BorrowingHistory history;

    private RecordFinder finder;

    private Admin admin;

    public LibrarySystem() {

        catalogue = new BookBST();

        history = new BorrowingHistory();

        finder = new RecordFinder();

        admin = new Admin(
                catalogue,
                history,
                finder
        );

        loadBooksFromFile();
    }

    // =====================================
    // LOAD BOOKS FROM FILE
    // =====================================

    private void loadBooksFromFile() {

        File file = new File("data.txt");

        if (!file.exists()) {
            return;
        }

        try {

            Scanner fileReader =
                    new Scanner(file);

            while (fileReader.hasNextLine()) {

                String line =
                        fileReader.nextLine();

                String[] data =
                        line.split(",");

                if (data.length == 4) {

                    int isbn =
                            Integer.parseInt(data[0]);

                    String title =
                            data[1];

                    String author =
                            data[2];

                    String category =
                            data[3];

                    catalogue.insert(
                            isbn,
                            title,
                            author,
                            category
                    );
                }
            }

            fileReader.close();
        }

        catch (Exception e) {

            System.out.println(
                    "Error loading file."
            );
        }
    }

    // =====================================
    // SAVE BOOK TO FILE
    // =====================================

    private void saveBookToFile(int isbn,
                                String title,
                                String author,
                                String category) {

        try {

            FileWriter writer =
                    new FileWriter(
                            "data.txt",
                            true
                    );

            writer.write(
                    isbn + "," +
                    title + "," +
                    author + "," +
                    category + "\n"
            );

            writer.close();
        }

        catch (IOException e) {

            System.out.println(
                    "Error saving file."
            );
        }
    }

    // =====================================
    // REWRITE FILE
    // =====================================

    private void rewriteFile() {

        try {

            FileWriter writer =
                    new FileWriter(
                            "data.txt"
                    );

            RewriteHelper.writeBooks(
                    catalogue.getRoot(),
                    writer
            );

            writer.close();
        }

        catch (IOException e) {

            System.out.println(
                    "Error rewriting file."
            );
        }
    }

    // =====================================
    // ADD BOOK
    // =====================================

    @Override
    public void addBook(int isbnCode,
                        String title,
                        String author,
                        String category) {

        boolean inserted =
                catalogue.insert(
                        isbnCode,
                        title,
                        author,
                        category
                );

        if (inserted) {

            saveBookToFile(
                    isbnCode,
                    title,
                    author,
                    category
            );

            System.out.println(
                    "\u001B[32mBook added successfully.\u001B[0m"
            );
        }
    }

    // =====================================
    // SEARCH BOOK BY ISBN
    // =====================================

    @Override
    public void searchBook(int isbnCode) {

        Book foundBook =
                finder.findBook(
                        catalogue.getRoot(),
                        isbnCode
                );

        if (foundBook == null) {

            System.out.println(
                    "Book not found."
            );
        }

        else {

            System.out.println(
                    "\n======= BOOK FOUND ======="
            );

            System.out.println(foundBook);
        }
    }

    // =====================================
    // SEARCH BOOK BY TITLE
    // =====================================

    @Override
    public void searchBookByTitle(
            String keyword) {

        System.out.println(
                "\n======= SEARCH RESULT ======="
        );

        finder.searchByTitle(
                catalogue.getRoot(),
                keyword
        );
    }

    // =====================================
    // BORROW BOOK
    // =====================================

    @Override
    public void borrowBook(int isbnCode) {

        admin.borrowBook(isbnCode);
    }

    // =====================================
    // RETURN BOOK
    // =====================================

    @Override
    public void returnLatestBook() {

        admin.returnLatestBook();
    }

    // =====================================
    // DELETE BOOK
    // =====================================

    @Override
    public void deleteBook(int isbnCode) {

        catalogue.deleteBook(isbnCode);

        rewriteFile();

        System.out.println(
                "\u001B[31mBook deleted successfully.\u001B[0m"
        );
    }

    // =====================================
    // VIEW HISTORY
    // =====================================

    @Override
    public void viewBorrowingHistory() {

        history.displayHistory();
    }

    // =====================================
    // DISPLAY ALL BOOKS
    // =====================================

    @Override
    public void displayAllBooks() {

        catalogue.displayBooks();
    }

    // =====================================
    // MOST BORROWED BOOK
    // =====================================

    @Override
    public void showMostBorrowedBook() {

        MostBorrowedTracker tracker =
                new MostBorrowedTracker();

        tracker.findMostBorrowed(
                catalogue.getRoot()
        );

        tracker.displayResult();
    }

    // =====================================
    // EXPORT CATALOGUE
    // =====================================

    @Override
    public void exportCatalogue() {

        try {

            FileWriter writer =
                    new FileWriter(
                            "catalogue_report.txt"
                    );

            ExportHelper.exportBooks(
                    catalogue.getRoot(),
                    writer
            );

            writer.close();

            System.out.println(
                    "\u001B[32mCatalogue exported successfully.\u001B[0m"
            );
        }

        catch (IOException e) {

            System.out.println(
                    "Export failed."
            );
        }
    }

    // =====================================
    // LOGIN MENU
    // =====================================

    private int loginMenu(Scanner scanner) {

        while (true) {

            System.out.println(
                    "\n========= LOGIN ========="
            );

            System.out.println("1. Admin");

            System.out.println("2. Student");

            int choice =
                    InputHelper.getIntInput(
                            scanner,
                            "Choose role: "
                    );

            // ADMIN LOGIN

            if (choice == 1) {

                System.out.print(
                        "Enter admin password: "
                );

                String password =
                        scanner.nextLine();

                if (password.equals("admin123")) {

                    System.out.println(
                            "\u001B[32mAdmin login successful.\u001B[0m"
                    );

                    return 1;
                }

                else {

                    System.out.println(
                            "\u001B[31mWrong password.\u001B[0m"
                    );
                }
            }

            // STUDENT LOGIN

            else if (choice == 2) {

                System.out.println(
                        "\u001B[36mStudent login successful.\u001B[0m"
                );

                return 2;
            }

            else {

                System.out.println(
                        "Invalid choice."
                );
            }
        }
    }

    // =====================================
    // RUN SYSTEM
    // =====================================

    public void runSystem() {

        Scanner scanner =
                new Scanner(System.in);

        int role =
                loginMenu(scanner);

        boolean running = true;

        while (running) {

            printMenu(role);

            int choice =
                    InputHelper.getIntInput(
                            scanner,
                            "Enter choice: "
                    );

            // =====================================
            // ADMIN MENU
            // =====================================

            if (role == 1) {

                switch (choice) {

                    case 1:

                        int isbn =
                                InputHelper.getIntInput(
                                        scanner,
                                        "Enter ISBN: "
                                );

                        String title =
                                InputHelper.getStringInput(
                                        scanner,
                                        "Enter Title: "
                                );

                        String author =
                                InputHelper.getStringInput(
                                        scanner,
                                        "Enter Author: "
                                );

                        String category =
                                InputHelper.getStringInput(
                                        scanner,
                                        "Enter Category: "
                                );

                        addBook(
                                isbn,
                                title,
                                author,
                                category
                        );

                        break;

                    case 2:

                        int searchISBN =
                                InputHelper.getIntInput(
                                        scanner,
                                        "Enter ISBN: "
                                );

                        searchBook(searchISBN);

                        break;

                    case 3:

                        String keyword =
                                InputHelper.getStringInput(
                                        scanner,
                                        "Enter keyword: "
                                );

                        searchBookByTitle(keyword);

                        break;

                    // case 4:

                    //     int borrowISBN =
                    //             InputHelper.getIntInput(
                    //                     scanner,
                    //                     "Enter ISBN: "
                    //             );

                    //     borrowBook(borrowISBN);

                    //     break;

                    // case 5:

                    //     returnLatestBook();

                    //     break;

                    case 6:

                        viewBorrowingHistory();

                        break;

                    case 7:

                        displayAllBooks();

                        break;

                    case 8:

                        showMostBorrowedBook();

                        break;

                    case 9:

                        exportCatalogue();

                        break;

                    case 10:

                        int deleteISBN =
                                InputHelper.getIntInput(
                                        scanner,
                                        "Enter ISBN to delete: "
                                );

                        deleteBook(deleteISBN);

                        break;

                    case 11:

                        running = false;

                        System.out.println(
                                "\u001B[36mSystem exited successfully.\u001B[0m"
                        );

                        break;

                    default:

                        System.out.println(
                                "Invalid choice."
                        );
                }
            }

            // =====================================
            // STUDENT MENU
            // =====================================

            else {

                switch (choice) {

                    case 1:

                        int searchISBN =
                                InputHelper.getIntInput(
                                        scanner,
                                        "Enter ISBN: "
                                );

                        searchBook(searchISBN);

                        break;

                    case 2:

                        String keyword =
                                InputHelper.getStringInput(
                                        scanner,
                                        "Enter keyword: "
                                );

                        searchBookByTitle(keyword);

                        break;

                    case 3:

                        int borrowISBN =
                                InputHelper.getIntInput(
                                        scanner,
                                        "Enter ISBN: "
                                );

                        borrowBook(borrowISBN);

                        break;

                    case 4:

                        returnLatestBook();

                        break;

                    case 5:

                        viewBorrowingHistory();

                        break;

                    case 6:

                        displayAllBooks();

                        break;

                    case 7:

                        showMostBorrowedBook();

                        break;

                    case 8:

                        running = false;

                        System.out.println(
                                "\u001B[36mSystem exited successfully.\u001B[0m"
                        );

                        break;

                    default:

                        System.out.println(
                                "Invalid choice."
                        );
                }
            }
        }

        scanner.close();
    }

    // =====================================
    // PRINT MENU
    // =====================================

    private void printMenu(int role) {

        System.out.println(
                "\n===================================="
        );

        System.out.println(
                "        SMART LIBRARY SYSTEM"
        );

        System.out.println(
                "===================================="
        );

        // ADMIN MENU

        if (role == 1) {

            System.out.println(
                    "[ADMIN MODE]"
            );

            System.out.println(
                    "1. Add Book"
            );

            System.out.println(
                    "2. Search Book by ISBN"
            );

            System.out.println(
                    "3. Search Book by Title"
            );

            System.out.println(
                    "4. Borrow Book"
            );

            System.out.println(
                    "5. Return Latest Book"
            );

            System.out.println(
                    "6. View Borrowing History"
            );

            System.out.println(
                    "7. Display All Books"
            );

            System.out.println(
                    "8. Show Most Borrowed Book"
            );

            System.out.println(
                    "9. Export Catalogue"
            );

            System.out.println(
                    "10. Delete Book"
            );

            System.out.println(
                    "11. Exit"
            );
        }

        // STUDENT MENU

        else {

            System.out.println(
                    "[STUDENT MODE]"
            );

            System.out.println(
                    "1. Search Book by ISBN"
            );

            System.out.println(
                    "2. Search Book by Title"
            );

            System.out.println(
                    "3. Borrow Book"
            );

            System.out.println(
                    "4. Return Latest Book"
            );

            System.out.println(
                    "5. View Borrowing History"
            );

            System.out.println(
                    "6. Display All Books"
            );

            System.out.println(
                    "7. Show Most Borrowed Book"
            );

            System.out.println(
                    "8. Exit"
            );
        }
    }
}