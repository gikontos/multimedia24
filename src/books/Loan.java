package src.books;

import src.users.User;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.Serializable;;

public class Loan implements Serializable {
    private Book book;
    private User user;
    private LocalDate dueDate;
    private boolean returned;

    public Loan(Book book, User user) {
        this.book = book;
        this.user = user;
        this.dueDate = LocalDate.now().plusDays(5);
        this.returned = false;
    }

    public Book getBook() {
        return this.book;
    }

    public User getUser() {
        return this.user;
    }

    public String getBookTitle() {
        return this.book.getTitle();
    }

    public String getUserUsername() {
        return this.user.getUsername();
    }

    public String getDueDateString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dueDate.format(formatter);
    }

    public void returnBook() {
        this.returned = true;
        this.dueDate = null;
    }

    public String viewInfo() {
        String status = (returned) ? "Returned" : "Borrowed";

        return "Loan Info:" + "\n" +
                "Title: " + book.getTitle() + "\n" +
                "User: " + user.getUsername() + "\n" +
                "Due Date: " + this.dueDate + "\n" +
                "Status: " + status + "\n";
    }
}
