package src.Controllers;

import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import src.books.Book;
import src.uiComponents.BookListCell;
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
    private TableView<User> listOfUsers;
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

    private LibraryManager libraryManager;

    public void setLibraryManager(LibraryManager libraryManager) {
        this.libraryManager = libraryManager;
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
    public void login(ActionEvent event) {
        popUps popup = new popUps();
        pageChanger page = new pageChanger(stage);
        String username = usernameField.getText();
        String password = passwordField.getText();
        if (username.equals("m") && password.equals("m")) {
            page.changePage("adminPage", libraryManager);
        } else if (libraryManager.isValidCredentials(username, password)) {
            page.changePage("userPage", libraryManager);
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
        if (libraryManager == null) {
            System.out.println("gio");
        }
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
                page.changePage("Login", libraryManager);
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
                page.changePage("bookList", libraryManager);
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
            page.changePage("categoryList", libraryManager);
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
        page.changePage("register", libraryManager);
    }

    @FXML
    private void goToUserListPage(ActionEvent event) throws IOException {
        pageChanger page = new pageChanger(stage);
        page.changePage("userList", libraryManager);
    }

    @FXML
    private void backToAdminPage(ActionEvent event) throws IOException {
        pageChanger page = new pageChanger(stage);
        page.changePage("adminPage", libraryManager);
    }

    @FXML
    private void backToLoginPage(ActionEvent event) throws IOException {
        pageChanger page = new pageChanger(stage);
        page.changePage("Login", libraryManager);
    }

    @FXML
    private void goToAddBook(ActionEvent event) throws IOException {
        pageChanger page = new pageChanger(stage);
        page.changePage("addBook", libraryManager);
    }

    @FXML
    private void goToBookList(ActionEvent event) throws IOException {
        pageChanger page = new pageChanger(stage);
        page.changePage("bookList", libraryManager);
    }

    @FXML
    private void goToCategoryList(ActionEvent event) throws IOException {
        pageChanger page = new pageChanger(stage);
        page.changePage("categoryList", libraryManager);
    }

    @FXML
    private void goToAddCategory(ActionEvent event) throws IOException {
        pageChanger page = new pageChanger(stage);
        page.changePage("addCategory", libraryManager);
    }
}
