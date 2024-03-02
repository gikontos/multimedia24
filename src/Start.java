package src;

import src.users.LibraryManager;
import src.users.User;
import src.filesystem.FileHandler;

import java.util.List;

public class Start {
    public static void main(String[] args) {
        // Αρχικοποίηση εφαρμογής - Φόρτωση πληροφοριών από τα ειδικά αρχεία
        LibraryManager libraryManager = initializeApplication();
        libraryManager.registerUser("b", "m", "m", "m", "m", "m");
        saveApplicationState(libraryManager);
        List<User> loadedUsers = (List<User>) FileHandler.loadObjects("multimedia/users.ser");

        if (loadedUsers != null) {
            for (User user : loadedUsers) {
                System.out.println(user.viewInfo());
            }
        } else {
            System.out.println("Δεν βρέθηκαν χρήστες.");
        }
    }

    private static LibraryManager initializeApplication() {
        // Φορτώστε τα δεδομένα από τα ειδικά αρχεία χρησιμοποιώντας το FileHandler
        List<?> loadedObjects = FileHandler.loadObjects("multimedia/users.ser");

        List<User> userList = (List<User>) loadedObjects;

        // Δημιουργήστε έναν LibraryManager και αρχικοποιήστε τον με τα δεδομένα
        LibraryManager libraryManager = new LibraryManager();
        libraryManager.setAllUsers(userList);

        return libraryManager;
    }

    private static void saveApplicationState(LibraryManager libraryManager) {
        // Αποθηκεύστε τα δεδομένα στα ειδικά αρχεία χρησιμοποιώντας το FileHandler
        FileHandler.saveObjects(".multimedia/users.ser", libraryManager.getAllUsers());
    }
}
