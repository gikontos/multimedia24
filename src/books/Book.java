package src.books;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Book implements Serializable {
    private String title;
    private String author;
    private String publisher;
    private String ISBN;
    private int publicationYear;
    private Category category;
    private int numberOfCopies;
    private double rating;
    private int numberOfRatings;
    private List<Comment> comments;

    public Book(String title, String author, String publisher, String ISBN, int publicationYear, String category,
            int numberOfCopies) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.ISBN = ISBN;
        this.publicationYear = publicationYear;
        this.category.setCategory(category);
        this.numberOfCopies = numberOfCopies;
        this.rating = 0.0;
        this.numberOfRatings = 0;
        this.comments = new ArrayList<>();
    }

    // void to be able to change the fields of the book

    public void updateBookInfo(String title, String author, String publisher, String ISBN, int publicationYear,
            String category, int numberOfCopies) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.ISBN = ISBN;
        this.publicationYear = publicationYear;
        this.category.setCategory(category);
        this.numberOfCopies = numberOfCopies;
    }

    public boolean canloanBook() {
        if (this.numberOfCopies > 0) {
            return true;
        } else {
            return false;
        }
    }

    public void loanedBook() {
        this.numberOfCopies--;
    }

    public void returnBorrowedBook() {
        this.numberOfCopies++;
    }

    public void addRating(int r) {
        this.rating = ((this.rating * this.numberOfRatings) + r) / (this.numberOfRatings + 1);
        this.numberOfRatings++;
    }

    public void addComment(Comment c) {
        if (comments == null) {
            comments = new ArrayList<>();
        }
        comments.add(c);
    }
    // functions to return the needed information

    public String getTitle() {
        return this.title;
    }

    public String getAuthor() {
        return this.author;
    }

    public int getPublicationYear() {
        return this.publicationYear;
    }

    public String viewInfo() {
        return "Book Info:\n" +
                "Title: " + title + "\n" +
                "Author: " + author + "\n" +
                "Publisher: " + publisher + "\n" +
                "ISBN: " + ISBN + "\n" +
                "Publication Year: " + publicationYear + "\n" +
                "Category: " + category + "\n" +
                "Number of Copies: " + numberOfCopies + "\n" +
                "Rating: " + rating + "\n" +
                "Comments: " + comments.toString();
    }
}
