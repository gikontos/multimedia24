package src;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;
import src.Controllers.MainController;
import src.users.LibraryManager;

import java.io.IOException;

public class App extends Application {
    private static LibraryManager libraryManager;

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Library System");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("ui/login.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        MainController controller = loader.getController();
        controller.setStage(primaryStage);
        controller.setRoot(root);
        controller.setLibraryManager(libraryManager);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void setLibraryManager(LibraryManager manager) {
        libraryManager = manager;
    }

    public static void main(String[] args) {
        launch(args);
    }

}