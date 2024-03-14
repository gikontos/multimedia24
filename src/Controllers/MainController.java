package src.Controllers;

import java.util.List;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import src.books.Book;
import src.uiComponents.BookListCell;
import src.users.LibraryManager;
import src.users.User;

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

    private LibraryManager libraryManager;

    public void setLibraryManager(LibraryManager libraryManager) {
        this.libraryManager = libraryManager;
        initialize();
    }

    @FXML
    public void initialize() {
        if (libraryManager == null) {
            return;
        }
        // List<Book> topBooks = libraryManager.getTopRatedBooks();
        List<Book> topBooks = libraryManager.getTopRatedBooks();
        top5Books.getItems().setAll(topBooks);
        top5Books.setCellFactory(param -> new BookListCell());
    }

    @FXML
    public void login(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();
        if (username.equals("medialab") && password.equals("medialab_2024")) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/adminPage.fxml"));
                Parent homeRoot = loader.load();
                Scene homeScene = new Scene(homeRoot);
                Stage homeStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                homeStage.setScene(homeScene);
                homeStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (isValidCredentials(username, password)) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/userPage.fxml"));
                Parent homeRoot = loader.load();
                Scene homeScene = new Scene(homeRoot);
                Stage homeStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                homeStage.setScene(homeScene);
                homeStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/wrongUser.fxml"));
                Parent popupRoot = loader.load();
                Scene popupScene = new Scene(popupRoot);
                Stage popupStage = new Stage();
                popupStage.setScene(popupScene);
                popupStage.initModality(Modality.APPLICATION_MODAL);
                popupStage.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void closeWrongUser(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    private boolean isValidCredentials(String username, String password) {
        List<User> users = libraryManager.getAllUsers();
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    @FXML
    private void goToRegisterPage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/register.fxml"));
        Parent secondRoot = loader.load();
        stage.setScene(new Scene(secondRoot, 600, 400));
    }
}
