package src.books;

import src.users.User;
import java.time.LocalDate;

@SuppressWarnings("unused")
public class Loan {
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

    public void returnBook() {
        this.returned = true;
    }
}
