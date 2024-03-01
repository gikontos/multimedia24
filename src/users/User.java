package src.users;

public class User {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String idNumber;
    private String email;
    private int borrowedBooks;

    public User(String username, String password, String firstName, String lastName, String idNumber, String email) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.idNumber = idNumber;
        this.email = email;
        this.borrowedBooks = 0;
    }

    // voids to change the info

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

    public boolean addBorrowedBook() {
        if (this.borrowedBooks < 2) {
            this.borrowedBooks++;
            return true;
        } else {
            System.out.println("Ο χρήστης δεν δικαιούται δανεισμό");
            return false;
        }
    }

    public void returnBorrowedBook() {
        if (this.borrowedBooks > 0) {
            this.borrowedBooks--;
        } else {
            System.out.println("Δεν Υπάρχει δανεισμός στο όνομα του χρήστη");
        }
    }

    // functions to retun the needed info

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
