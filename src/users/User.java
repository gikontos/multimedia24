package src.users;

import java.util.ArrayList;
import java.util.List;

import src.books.Book;
import java.io.Serializable;

public class User implements Serializable {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String idNumber;
    private String email;
    private int borrowedBooks;
    private List<Book> loans;

    public User(String username, String password, String firstName, String lastName, String idNumber, String email) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.idNumber = idNumber;
        this.email = email;
        this.borrowedBooks = 0;
        this.loans = new ArrayList<>();
    }

    public void updateUserInfo(String username, String password, String firstName, String lastName, String idNumber,
            String email, int borrowedBooks) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.idNumber = idNumber;
        this.email = email;
        this.borrowedBooks = borrowedBooks;
    }

    public boolean canBorrowBook() {
        if (this.borrowedBooks < 2) {
            return true;
        } else {
            System.out.println("Ο χρήστης δεν δικαιούται δανεισμό");
            return false;
        }
    }

    public void Borrowed(Book book) {
        this.borrowedBooks++;
        this.loans.add(book);
    }

    public List<Book> getAllLoans() {
        return loans;
    }

    public void returnBorrowedBook() {
        if (this.borrowedBooks > 0) {
            this.borrowedBooks--;
        } else {
            System.out.println("Δεν Υπάρχει δανεισμός στο όνομα του χρήστη");
        }
    }

    public boolean canComment(Book book) {
        return loans.contains(book);
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getEmail() {
        return this.email;
    }

    public String getId() {
        return this.idNumber;
    }

    public String viewInfo() {
        return "User Info:\n" +
                "Username: " + username + "\n" +
                "Password: " + password + "\n" +
                "First Name: " + firstName + "\n" +
                "Last Name: " + lastName + "\n" +
                "ID Number: " + idNumber + "\n" +
                "Email: " + email + "\n" +
                "Borrowed Books: " + borrowedBooks;
    }
}
