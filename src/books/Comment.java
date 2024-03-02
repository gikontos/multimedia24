package src.books;

import java.io.Serializable;

import src.users.User;

public class Comment implements Serializable {
    private String comment;
    private User user;

    public Comment(String comment) {
        this.comment = comment;
    }

    public void EditComment(String comment) {
        this.comment = comment;
    }

    public User returnUser() {
        return this.user;
    }

    public String returnComment() {
        return this.comment;
    }
}
