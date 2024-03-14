package src.Controllers;

import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import src.books.Book;
import src.uiComponents.BookListCell;
import src.users.LibraryManager;
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
        if (username.equals("medialab") && password.equals("medialab_2024")) {
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
    private void closePopUp(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void goToRegisterPage(ActionEvent event) throws IOException {
        pageChanger page = new pageChanger(stage);
        page.changePage("register", libraryManager);
    }
}
