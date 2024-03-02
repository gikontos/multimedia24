package src;

import src.users.LibraryManager;
import src.users.User;

import java.util.List;

import src.filesystem.*;

public class Start {
    public static void main(String[] args) {
        LibraryManager libraryManager = new LibraryManager();
        libraryManager.registerUser("medialab", "medialab_2024", "giorgos", "kontos", "1", "el20176@mail.ntua.gr");
        FileHandler.saveObjects("users.ser", libraryManager.getAllUsers());

        List<User> loadedUsers = (List<User>) FileHandler.loadObjects("users.ser");

        if (loadedUsers != null) {
            for (User user : loadedUsers) {
                System.out.println(user.viewInfo());
            }
        } else {
            System.out.println("Δεν βρέθηκαν χρήστες.");
        }
    }
}
