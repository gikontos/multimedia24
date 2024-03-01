package src.users;

import java.util.ArrayList;
import java.util.List;
import src.books.Book;
import src.books.Category;
import src.books.Comment;
import src.books.Loan;

public class LibraryManager {
    private List<Book> books;
    private List<Category> categories;
    private List<User> users;
    private List<Loan> loans;

    public LibraryManager() {
        this.books = new ArrayList<>();
        this.categories = new ArrayList<>();
        this.users = new ArrayList<>();
        this.loans = new ArrayList<>();
    }

    // Methods for book management
    public List<Book> getAllBooks() {
        return books;
    }

    public void addBook(String title, String author, String publisher, String ISBN, int publicationYear,
            String category,
            int numberOfCopies) {
        if (books == null) {
            books = new ArrayList<>();
        }
        Book book = new Book(title, author, publisher, ISBN, publicationYear, category, numberOfCopies);
        books.add(book);
    }

    public void deleteBook(Book book) {
        if (books != null) {
            books.remove(book);
        }
    }

    public void updateBook(Book book, String title, String author, String publisher, String ISBN, int publicationYear,
            String category, int numberOfCopies) {
        book.updateBookInfo(title, author, publisher, ISBN, publicationYear, category, numberOfCopies);
    }

    // Methods for category management
    public List<Category> getAllCategories() {
        return categories;
    }

    public void addCategory(String c) {
        if (categories == null) {
            categories = new ArrayList<>();
        }
        Category category = new Category(c);
        categories.add(category);
    }

    public void deleteCategory(Category category) {
        if (categories != null) {
            categories.remove(category);
        }
    }

    public void updateCategory(Category category, String newCategory) {
        category.setCategory(newCategory);
    }

    // Methods for user management
    public void registerUser(String username, String password, String firstName, String lastName, String idNumber,
            String email) {
        User user = new User(username, password, firstName, lastName, idNumber, email);
        users.add(user);
    }

    public List<User> getAllUsers() {
        return users;
    }

    public void deleteUser(User user) {
        if (users != null) {
            users.remove(user);
        }
    }

    public void updateUser(User user, String username, String password, String firstName, String lastName,
            String idNumber,
            String email, int borrowedBooks) {
        user.updateUserInfo(username, password, firstName, lastName, idNumber, email, borrowedBooks);
    }

    // Methods for loan management
    public List<Loan> getAllLoans() {
        return loans;
    }

    public void addLoan(Book book, User user) {
        if (user.addBorrowedBook()) {
            Loan loan = new Loan(book, user);
            loans.add(loan);
        }
    }

    public void terminateLoan(Loan loan) {
        User user = loan.getUser();
        loan.returnBook();
        user.returnBorrowedBook();
        if (loans != null) {
            loans.remove(loan);
        }
    }

    // Methods for users
    public void userAddRating(Book book, int r) {
        if (r >= 1 && r <= 5) {
            book.addRating(r);
        } else {
            System.out.println("Η βαθμολογία πρέπει να είναι από 1 έως 5.");
        }
    }

    public void userAddComment(Book book, String c) {
        Comment comment = new Comment(c);
        book.addComment(comment);
    }

    public List<Book> searchBooks(String title, String author, int publicationYear) {
        List<Book> result = new ArrayList<>();

        for (Book book : books) {
            boolean matches = true;

            if (title != null && !title.isEmpty()) {
                matches = matches && book.getTitle().toLowerCase().contains(title.toLowerCase());
            }

            if (author != null && !author.isEmpty()) {
                matches = matches && book.getAuthor().toLowerCase().contains(author.toLowerCase());
            }

            if (publicationYear > 0) {
                matches = matches && book.getPublicationYear() == publicationYear;
            }

            if (matches) {
                result.add(book);
            }
        }

        return result;
    }

}
