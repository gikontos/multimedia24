package src.users;

import java.util.ArrayList;
import java.util.List;
import src.books.Book;
import src.books.Category;
import src.books.Comment;
import src.books.Loan;
import java.io.Serializable;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.Iterator;

public class LibraryManager implements Serializable {
    private List<Book> books;
    private List<Category> categories;
    private List<User> users;
    private List<Loan> loans;
    private Administrator administrator;

    public LibraryManager() {
        this.books = new ArrayList<>();
        this.categories = new ArrayList<>();
        this.users = new ArrayList<>();
        this.loans = new ArrayList<>();
        this.administrator = new Administrator("medialab", "medialab_2024");
    }

    // methods for administrator
    public String adminUsername() {
        return administrator.getUsername();
    }

    public String adminPassword() {
        return administrator.getPassword();
    }

    public void adminChangeCredintials(String newUsername, String newPassword) {
        administrator.ChangeCredintials(newUsername, newPassword);
    }

    // Methods for book management
    public void setAllBooks(List<Book> books) {
        this.books = books;
    }

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

    public List<Book> getTopRatedBooks() {
        List<Book> ratedBooks = books.stream()
                .sorted(Comparator.comparingDouble(Book::getRating).reversed())
                .collect(Collectors.toList());

        return ratedBooks.subList(0, Math.min(5, ratedBooks.size()));
    }

    // Methods for category management
    public void setAllCategories(List<Category> categories) {
        this.categories = categories;
    }

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

    public boolean isValidCategory(String categoryName) {
        for (Category category : categories) {
            if (category.getCategory().equals(categoryName)) {
                return true;
            }
        }
        return false;
    }

    // Methods for user management
    public void setAllUsers(List<User> users) {
        this.users = users;
    }

    public void registerUser(String username, String password, String firstName, String lastName, String idNumber,
            String email) {
        User user = new User(username, password, firstName, lastName, idNumber, email);
        users.add(user);
    }

    public List<User> getAllUsers() {
        return users;
    }

    public void deleteUser(User user) {
        Iterator<Loan> loanIterator = loans.iterator();
        while (loanIterator.hasNext()) {
            Loan loan = loanIterator.next();
            if (loan.getUser().getUsername().equals(user.getUsername())) {
                loan.returnBook();
                loanIterator.remove();
            }
        }
        users.remove(user);
    }

    public void updateUser(User user, String username, String password, String firstName, String lastName,
            String idNumber,
            String email, int borrowedBooks) {
        user.updateUserInfo(username, password, firstName, lastName, idNumber, email, borrowedBooks);
    }

    public boolean isValidCredentials(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public User findUser(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public boolean usedUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public boolean usedEmail(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    public boolean usedId(String id) {
        for (User user : users) {
            if (user.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    // Methods for loan management
    public void setAllLoans(List<Loan> l) {
        this.loans = l;
    }

    public List<Loan> getAllLoans() {
        return loans;
    }

    public void addLoan(Book book, User user) {
        if (user.canBorrowBook() && book.canloanBook()) {
            user.Borrowed(book);
            book.loanedBook();
            Loan loan = new Loan(book, user);
            loans.add(loan);
        }
    }

    public void terminateLoan(Loan loan) {
        User user = loan.getUser();
        Book book = loan.getBook();
        book.returnBorrowedBook();
        loan.returnBook();
        User user2 = findUserByUsername(user.getUsername());
        user2.returnBorrowedBook(loan, this);
        if (loans != null) {
            loans.remove(loan);
        }
    }

    // Methods for users
    public void userAddRating(Book book, User user, int r) {
        if (user.canComment(book)) {
            if (r >= 1 && r <= 5) {
                book.addRating(r);
            } else {
                System.out.println("Η βαθμολογία πρέπει να είναι από 1 έως 5.");
            }
        }
    }

    public void userAddComment(Book book, User user, String c) {
        if (user.canComment(book)) {
            Comment comment = new Comment(c);
            book.addComment(comment);
        }
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

    // searches
    public User findUserByUsername(String usernameString) {
        if (users != null) {
            for (User user : users) {
                if (user.getUsername().equals(usernameString)) {
                    return user;
                }
            }
        }
        return null;
    }

    public Book findBookByTitle(String titleString) {
        if (books != null) {
            for (Book book : books) {
                if (book.getTitle().equals(titleString)) {
                    return book;
                }
            }
        }
        return null;
    }

}
