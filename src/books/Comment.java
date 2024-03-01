package src.books;

public class Comment {
    @SuppressWarnings("unused")
    private String comment;

    public Comment(String comment) {
        this.comment = comment;
    }

    public void EditComment(String comment) {
        this.comment = comment;
    }
}
