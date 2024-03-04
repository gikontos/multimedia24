package src;

import src.filesystem.FileHandler;
import src.users.*;
import src.books.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Start {
    private static final String USERS_FILE = "multimedia/users.ser";
    private static final String BOOKS_FILE = "multimedia/books.ser";
    private static final String LOANS_FILE = "multimedia/loans.ser";
    private static final String CATEGORIES_FILE = "multimedia/categories.ser";

    public static void main(String[] args) {
        LibraryManager libraryManager = new LibraryManager();
        initializeApplication(libraryManager);

        executeApplication(libraryManager);

        terminateApplication(libraryManager);
    }

    // private static void print(LibraryManager libraryManager) {
    // List<Book> loadedUsers = libraryManager.getAllBooks();
    // if (loadedUsers != null) {
    // for (Book user : loadedUsers) {
    // System.out.println("----");
    // System.out.println(user.viewInfo());
    // }
    // } else {
    // System.out.println("Δεν βρέθηκαν χρήστες.");
    // }
    // }

    private static void initializeApplication(LibraryManager libraryManager) {
        createFileIfNotExists(USERS_FILE);
        createFileIfNotExists(BOOKS_FILE);
        createFileIfNotExists(LOANS_FILE);
        createFileIfNotExists(CATEGORIES_FILE);

        List<User> users = (List<User>) FileHandler.loadObjects(USERS_FILE);
        List<Book> books = (List<Book>) FileHandler.loadObjects(BOOKS_FILE);
        List<Loan> loans = (List<Loan>) FileHandler.loadObjects(LOANS_FILE);
        List<Category> categories = (List<Category>) FileHandler.loadObjects(CATEGORIES_FILE);

        if (users != null)
            libraryManager.setAllUsers(users);
        if (books != null)
            libraryManager.setAllBooks(books);
        if (loans != null)
            libraryManager.setAllLoans(loans);
        if (categories != null)
            libraryManager.setAllCategories(categories);
    }

    private static void executeApplication(LibraryManager libraryManager) {
    }

    private static void terminateApplication(LibraryManager libraryManager) {
        FileHandler.saveObjects(USERS_FILE, libraryManager.getAllUsers());
        FileHandler.saveObjects(BOOKS_FILE, libraryManager.getAllBooks());
        FileHandler.saveObjects(LOANS_FILE, libraryManager.getAllLoans());
        FileHandler.saveObjects(CATEGORIES_FILE, libraryManager.getAllCategories());
    }

    private static void createFileIfNotExists(String filename) {
        File file = new File(filename);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
