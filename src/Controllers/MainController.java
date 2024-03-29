package src.Controllers;

import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Callback;
import src.books.Book;
import src.books.Category;
import src.books.Loan;
import src.uiComponents.BookDeleteCell;
import src.uiComponents.BookListCell;
import src.uiComponents.BookUpdateCell;
import src.uiComponents.CategoryDeleteCell;
import src.uiComponents.CategoryUpdateCell;
import src.uiComponents.LoansEndCell;
import src.uiComponents.resultsReserveCell;
import src.uiComponents.resultsViewInfoCell;
import src.uiComponents.userDeleteCell;
import src.uiComponents.userLoanComment;
import src.uiComponents.userLoanRating;
import src.uiComponents.userUpdateCell;
import src.users.LibraryManager;
import src.users.User;
import src.Controllers.helpers.*;
import java.io.IOException;

public class MainController {

    private Stage stage;
    private Parent root;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setRoot(Parent root) {
        this.root = root;
    }

    @FXML
    private ListView<Book> top5Books;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField newUsernameField;
    @FXML
    private PasswordField newPasswordField;
    @FXML
    private TextField adtField;
    @FXML
    private TextField titleField;
    @FXML
    private TextField authorField;
    @FXML
    private TextField publisherField;
    @FXML
    private TextField ISBNField;
    @FXML
    private TextField numberOfCopiesField;
    @FXML
    private TextField yearField;
    @FXML
    private TextField categoryField;
    @FXML
    private TextField newCategory;
    @FXML
    private TableView<Category> categoryTableView;
    @FXML
    private TableColumn<Category, String> categoryColumn;
    @FXML
    private TableColumn<Category, Button> categoryDelete;
    @FXML
    private TableColumn<Category, Button> categoryUpdate;
    @FXML
    private TableView<User> listOfUsers;
    @FXML
    private TableColumn<User, String> nameColumn;
    @FXML
    private TableColumn<User, String> lastNameColumn;
    @FXML
    private TableColumn<User, String> usernameColumn;
    @FXML
    private TableColumn<User, Button> userDelete;
    @FXML
    private TableColumn<User, Button> userUpdate;
    @FXML
    private TableView<Book> adminBookList;
    @FXML
    private TableColumn<Book, String> adminBookTitle;
    @FXML
    private TableColumn<Book, String> adminBookAuthor;
    @FXML
    private TableColumn<Book, String> adminBookPublisher;
    @FXML
    private TableColumn<Book, String> adminBookisbn;
    @FXML
    private TableColumn<Book, String> adminBookYear;
    @FXML
    private TableColumn<Book, String> adminBookCopies;
    @FXML
    private TableColumn<Book, Button> adminBookDelete;
    @FXML
    private TableColumn<Book, Button> adminBookUpdate;
    @FXML
    private TableColumn<Book, String> adminBookCategory;
    @FXML
    private TableColumn<Book, String> adminBookRating;
    @FXML
    private TableView<Loan> adminLoansList;
    @FXML
    private TableColumn<Loan, String> adminLoansTitle;
    @FXML
    private TableColumn<Loan, String> adminLoansUser;
    @FXML
    private TableColumn<Loan, String> adminLoansDate;
    @FXML
    private TableColumn<Loan, Button> adminLoansEnd;
    @FXML
    private TextField searchTitle;
    @FXML
    private TextField searchAuthor;
    @FXML
    private TextField searchYear;
    @FXML
    private TableView<Book> userLoanList;
    @FXML
    private TableColumn<Book, String> userTitle;
    @FXML
    private TableColumn<Book, String> userIsbn;
    @FXML
    private TableColumn<Book, String> userStartDate;
    @FXML
    private TableColumn<Book, String> userDate;
    @FXML
    private TableColumn<Book, Button> userRating;
    @FXML
    private TableColumn<Book, Button> userComment;

    private LibraryManager libraryManager;
    private User user;

    public void setLibraryManager(LibraryManager libraryManager) {
        this.libraryManager = libraryManager;
    }

    public void setUser(User u) {
        user = u;
    }

    @FXML
    public void initialize() {
        if (libraryManager == null) {
            return;
        }
        List<Book> topBooks = libraryManager.getTopRatedBooks();
        top5Books.getItems().setAll(topBooks);
        top5Books.setCellFactory(param -> new BookListCell());
    }

    @FXML
    public void initializeCategoryTable() {
        categoryColumn.setCellValueFactory(new Callback<>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Category, String> cellData) {
                String category = cellData.getValue().getCategory();
                return new SimpleStringProperty(category);
            }
        });
        categoryDelete.setCellFactory(param -> new CategoryDeleteCell(libraryManager, stage));
        categoryUpdate.setCellFactory(param -> new CategoryUpdateCell());
        List<Category> categories = libraryManager.getAllCategories();
        categoryTableView.getItems().addAll(categories);
    }

    @FXML
    public void initializeLoansTable() {
        adminLoansTitle.setCellValueFactory(new Callback<>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Loan, String> cellData) {
                String title = cellData.getValue().getBookTitle();
                return new SimpleStringProperty(title);
            }
        });
        adminLoansUser.setCellValueFactory(new Callback<>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Loan, String> cellData) {
                String user = cellData.getValue().getUserUsername();
                return new SimpleStringProperty(user);
            }
        });
        adminLoansDate.setCellValueFactory(new Callback<>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Loan, String> cellData) {
                String date = cellData.getValue().getDueDateString();
                return new SimpleStringProperty(date);
            }
        });
        adminLoansEnd.setCellFactory(param -> new LoansEndCell(libraryManager, stage));
        List<Loan> loans = libraryManager.getAllLoans();
        adminLoansList.getItems().addAll(loans);
    }

    @FXML
    public void initializeUserLoansTable(LibraryManager manager) {
        userTitle.setCellValueFactory(new Callback<>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Book, String> cellData) {
                String title = cellData.getValue().getTitle();
                return new SimpleStringProperty(title);
            }
        });
        userIsbn.setCellValueFactory(new Callback<>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Book, String> cellData) {
                String isbn = cellData.getValue().getIsbn();
                return new SimpleStringProperty(isbn);
            }
        });
        userDate.setCellValueFactory(new Callback<>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Book, String> cellData) {
                Book book = cellData.getValue();
                Loan loan = manager.findOpenLoans(user, book);
                if (loan != null) {
                    String date = loan.getDueDateString();
                    return new SimpleStringProperty(date);
                }
                return null;
            }
        });
        userStartDate.setCellValueFactory(new Callback<>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Book, String> cellData) {
                Book book = cellData.getValue();
                Loan loan = manager.findOpenLoans(user, book);
                if (loan != null) {
                    String date = loan.getStartDateString();
                    return new SimpleStringProperty(date);
                }
                return null;
            }
        });
        userComment.setCellFactory(param -> new userLoanComment());
        userRating.setCellFactory(param -> new userLoanRating());
        List<Book> books = user.getAllLoans();
        userLoanList.getItems().addAll(books);
    }

    @FXML
    public void initializeBookTable() {
        adminBookTitle.setCellValueFactory(new Callback<>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Book, String> cellData) {
                String title = cellData.getValue().getTitle();
                return new SimpleStringProperty(title);
            }
        });
        adminBookAuthor.setCellValueFactory(new Callback<>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Book, String> cellData) {
                String author = cellData.getValue().getAuthor();
                return new SimpleStringProperty(author);
            }
        });
        adminBookisbn.setCellValueFactory(new Callback<>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Book, String> cellData) {
                String isbn = cellData.getValue().getIsbn();
                return new SimpleStringProperty(isbn);
            }
        });
        adminBookPublisher.setCellValueFactory(new Callback<>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Book, String> cellData) {
                String publisher = cellData.getValue().getPublisher();
                return new SimpleStringProperty(publisher);
            }
        });
        adminBookCopies.setCellValueFactory(new Callback<>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Book, String> cellData) {
                String copies = cellData.getValue().getNumberOfCopies();
                return new SimpleStringProperty(copies);
            }
        });
        adminBookYear.setCellValueFactory(new Callback<>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Book, String> cellData) {
                String year = cellData.getValue().getYear();
                return new SimpleStringProperty(year);
            }
        });
        adminBookCategory.setCellValueFactory(new Callback<>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Book, String> cellData) {
                String category = cellData.getValue().getCategory();
                return new SimpleStringProperty(category);
            }
        });
        adminBookRating.setCellValueFactory(new Callback<>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Book, String> cellData) {
                String rating = cellData.getValue().getStringRating();
                return new SimpleStringProperty(rating);
            }
        });
        adminBookDelete.setCellFactory(param -> new BookDeleteCell(libraryManager, stage));
        adminBookUpdate.setCellFactory(param -> new BookUpdateCell());
        List<Book> books = libraryManager.getAllBooks();
        adminBookList.getItems().addAll(books);
    }

    @FXML
    public void initializeUsersTable() {
        nameColumn.setCellValueFactory(new Callback<>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<User, String> cellData) {
                String name = cellData.getValue().getName();
                return new SimpleStringProperty(name);
            }
        });
        lastNameColumn.setCellValueFactory(new Callback<>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<User, String> cellData) {
                String name = cellData.getValue().getLastName();
                return new SimpleStringProperty(name);
            }
        });
        usernameColumn.setCellValueFactory(new Callback<>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<User, String> cellData) {
                String name = cellData.getValue().getUsername();
                return new SimpleStringProperty(name);
            }
        });
        userDelete.setCellFactory(param -> new userDeleteCell(libraryManager, stage));
        userUpdate.setCellFactory(param -> new userUpdateCell());
        List<User> users = libraryManager.getAllUsers();
        listOfUsers.getItems().addAll(users);
    }

    @FXML
    public void login(ActionEvent event) {
        popUps popup = new popUps();
        pageChanger page = new pageChanger(stage);
        String username = usernameField.getText();
        String password = passwordField.getText();
        if (username.equals("medialab") && password.equals("medialab_2024")) {
            page.changePage("adminPage", libraryManager, null);
        } else if (libraryManager.isValidCredentials(username, password)) {
            User user1 = libraryManager.findUser(username);
            page.changePage("userPage", libraryManager, user1);
        } else {
            popup.showPopUp("wrongUser");
        }
    }

    @FXML
    public void register(ActionEvent event) throws IOException {
        popUps popup = new popUps();
        pageChanger page = new pageChanger(stage);
        String username = newUsernameField.getText();
        String name = nameField.getText();
        String lastName = lastNameField.getText();
        String email = emailField.getText();
        String password = newPasswordField.getText();
        String adt = adtField.getText();
        if (!username.isEmpty() && !name.isEmpty() && !lastName.isEmpty() && !email.isEmpty() && !password.isEmpty()
                && !adt.isEmpty()) {
            boolean canRegister = true;
            if (username.equals("medialab") || libraryManager.usedUsername(username)) {
                popup.showPopUp("usedUsername");
                canRegister = false;
            }
            if (libraryManager.usedEmail(email)) {
                popup.showPopUp("usedEmail");
                canRegister = false;
            }
            if (libraryManager.usedId(adt)) {
                popup.showPopUp("usedId");
                canRegister = false;
            }
            if ((canRegister)) {
                popup.showPopUp("successfullRegistration");
                page.changePage("Login", libraryManager, null);
                libraryManager.registerUser(username, password, lastName, lastName, adt, email);
            }
        } else {
            popup.showPopUp("allFieldsRequired");
        }
    }

    @FXML
    public void addBook(ActionEvent event) throws IOException {
        popUps popup = new popUps();
        pageChanger page = new pageChanger(stage);
        String title = titleField.getText();
        String author = authorField.getText();
        String publisher = publisherField.getText();
        String isbn = ISBNField.getText();
        String numberOfCopies = numberOfCopiesField.getText();
        String year = yearField.getText();
        String category = categoryField.getText();
        if (title.isEmpty() || author.isEmpty() || publisher.isEmpty() || isbn.isEmpty()
                || numberOfCopies.isEmpty() || year.isEmpty() || category.isEmpty()) {
            popup.showPopUp("allFieldsRequired");
        } else if (!libraryManager.isValidCategory(category)) {
            popup.showPopUp("notValidCategory");
        } else {
            try {
                int yearOfPublication = Integer.parseInt(year);
                int numberOfCopiesInt = Integer.parseInt(numberOfCopies);
                libraryManager.addBook(title, author, publisher, isbn, yearOfPublication, category, numberOfCopiesInt);
                popup.showPopUp("success");
                page.changePage("bookList", libraryManager, null);
            } catch (NumberFormatException e) {
                popup.showPopUp("notIntegers");
            }
        }
    }

    @FXML
    public void addCategory(ActionEvent event) throws IOException {
        popUps popup = new popUps();
        pageChanger page = new pageChanger(stage);
        String category = newCategory.getText();
        if (libraryManager.isValidCategory(category) || category.isEmpty()) {
            popup.showPopUp("notValidCategory");
        } else {
            popup.showPopUp("success");
            libraryManager.addCategory(category);
            page.changePage("categoryList", libraryManager, null);
        }
    }

    @FXML
    private void closePopUp(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void goToRegisterPage(ActionEvent event) throws IOException {
        pageChanger page = new pageChanger(stage);
        page.changePage("register", libraryManager, null);
    }

    @FXML
    private void goToUserListPage(ActionEvent event) throws IOException {
        pageChanger page = new pageChanger(stage);
        page.changePage("userList", libraryManager, null);
    }

    @FXML
    private void backToAdminPage(ActionEvent event) throws IOException {
        pageChanger page = new pageChanger(stage);
        page.changePage("adminPage", libraryManager, null);
    }

    @FXML
    private void backToLoginPage(ActionEvent event) throws IOException {
        pageChanger page = new pageChanger(stage);
        page.changePage("Login", libraryManager, null);
    }

    @FXML
    private void backToUserPage(ActionEvent event) throws IOException {
        pageChanger page = new pageChanger(stage);
        page.changePage("userPage", libraryManager, user);
    }

    @FXML
    private void goToAddBook(ActionEvent event) throws IOException {
        pageChanger page = new pageChanger(stage);
        page.changePage("addBook", libraryManager, null);
    }

    @FXML
    private void goToBookList(ActionEvent event) throws IOException {
        pageChanger page = new pageChanger(stage);
        page.changePage("bookList", libraryManager, null);
    }

    @FXML
    private void goToCategoryList(ActionEvent event) throws IOException {
        pageChanger page = new pageChanger(stage);
        page.changePage("categoryList", libraryManager, null);
    }

    @FXML
    private void goToAddCategory(ActionEvent event) throws IOException {
        pageChanger page = new pageChanger(stage);
        page.changePage("addCategory", libraryManager, null);
    }

    @FXML
    private void goToLoansList(ActionEvent event) throws IOException {
        pageChanger page = new pageChanger(stage);
        page.changePage("loansList", libraryManager, null);
    }

    @FXML
    private TableView<Book> resultsTable;
    @FXML
    private TableColumn<Book, String> resultsTitle;
    @FXML
    private TableColumn<Book, String> resultsAuthor;
    @FXML
    private TableColumn<Book, String> resultsYear;
    @FXML
    private TableColumn<Book, Button> resultsViewInfo;
    @FXML
    private TableColumn<Book, Button> resultsReserve;

    @FXML
    private void goToResults(ActionEvent event) throws IOException {
        String title = searchTitle.getText();
        String author = searchAuthor.getText();
        String year = searchYear.getText();
        popUps popUp = new popUps();
        try {
            int number = 0;
            if (!year.isEmpty()) {
                number = Integer.parseInt(year);
            }
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/results.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            MainController newcontroller = loader.getController();
            newcontroller.setStage(stage);
            newcontroller.setRoot(root);
            newcontroller.setLibraryManager(libraryManager);
            newcontroller.setUser(user);
            newcontroller.initializeResultsTable(title, author, number);
            stage.setScene(scene);
            stage.show();
        } catch (NumberFormatException e) {
            popUp.showPopUp("notIntegers");
        }

    }

    @FXML
    private void initializeResultsTable(String title, String author, int number) {
        resultsTitle.setCellValueFactory(new Callback<>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Book, String> cellData) {
                String title = cellData.getValue().getTitle();
                return new SimpleStringProperty(title);
            }
        });
        resultsAuthor.setCellValueFactory(new Callback<>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Book, String> cellData) {
                String author = cellData.getValue().getAuthor();
                return new SimpleStringProperty(author);
            }
        });
        resultsYear.setCellValueFactory(new Callback<>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Book, String> cellData) {
                String year = cellData.getValue().getYear();
                return new SimpleStringProperty(year);
            }
        });
        List<Book> books = libraryManager.searchBooks(title, author, number);
        resultsReserve.setCellFactory(param -> new resultsReserveCell(libraryManager, user));
        resultsViewInfo.setCellFactory(param -> new resultsViewInfoCell(libraryManager, stage, user));
        resultsTable.getItems().addAll(books);
    }

    @FXML
    private Label bookInfoText;

    @FXML
    public void initializeBookInfo(Book book) {
        bookInfoText.setText(book.viewInfo());
    }
}
