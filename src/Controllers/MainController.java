package src.Controllers;

import java.util.List;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import src.books.Book;
import src.uiComponents.BookListCell;
import src.users.LibraryManager;

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
    private void goToRegisterPage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/register.fxml"));
        Parent secondRoot = loader.load();
        stage.setScene(new Scene(secondRoot, 600, 400));
    }
}
