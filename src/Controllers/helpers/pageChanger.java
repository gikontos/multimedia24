package src.Controllers.helpers;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import src.Controllers.MainController;
import src.users.LibraryManager;

public class pageChanger {
    private Stage stage;

    public pageChanger(Stage stage) {
        this.stage = stage;
    }

    public void changePage(String file, LibraryManager manager) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../ui/" + file + ".fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            MainController controller = loader.getController();
            controller.setStage(stage);
            controller.setRoot(root);
            controller.setLibraryManager(manager);
            if (file.equals("Login")) {
                controller.initialize();
            }
            if (file.equals("categoryList")) {
                controller.initializeCategoryTable();
            }
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
