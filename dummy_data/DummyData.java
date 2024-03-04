package dummy_data;

import src.users.LibraryManager;
import src.users.*;
import src.books.*;;

public class DummyData {
    private LibraryManager libraryManager;

    public DummyData(LibraryManager libraryManager) {
        this.libraryManager = libraryManager;
    }

    public void addDummyData() {
        libraryManager.addCategory("Category0");
        libraryManager.addCategory("Category1");
        libraryManager.addCategory("Category2");
        libraryManager.addCategory("Category3");

        // Προσθήκη βιβλίων
        for (int i = 0; i < 10; i++) {
            libraryManager.addBook("Title " + i, "Author " + i, "Publisher " + i, "ISBN" + i,
                    i % 4, "Category " + (i % 4), 5);
        }

        // Προσθήκη χρηστών
        for (int i = 0; i < 4; i++) {
            libraryManager.registerUser("User" + i, "Password" + i, "First" + i, "Last" + i,
                    "ID" + i, "Email" + i);
        }

        // Προσθήκη δανεισμών
        User user1 = libraryManager.findUserByUsername("User1");
        User user2 = libraryManager.findUserByUsername("User2");
        User user3 = libraryManager.findUserByUsername("User3");

        Book book1 = libraryManager.findBookByTitle("Title 1");
        Book book2 = libraryManager.findBookByTitle("Title 2");
        Book book3 = libraryManager.findBookByTitle("Title 3");

        libraryManager.addLoan(book1, user1);
        libraryManager.addLoan(book2, user2);
        libraryManager.addLoan(book3, user3);
    }
}
