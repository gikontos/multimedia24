package src.uiComponents;

import src.Controllers.MainController;
import src.books.*;
import src.users.LibraryManager;
import src.users.User;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.stage.Stage;

public class resultsViewInfoCell extends TableCell<Book, Button> {
    private final Button updateButton = new Button("View Info");

    public resultsViewInfoCell(LibraryManager manager, Stage stage, User user) {

        updateButton.setOnAction(event -> {
            Book book = getTableView().getItems().get(getIndex());
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/bookInfo.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                MainController controller = loader.getController();
                controller.setStage(stage);
                controller.setRoot(root);
                controller.setUser(user);
                controller.setLibraryManager(manager);
                controller.initializeBookInfo(book);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    protected void updateItem(Button item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            setGraphic(null);
        } else {
            setGraphic(updateButton);
        }
    }

    public Button getUpdateButton() {
        return updateButton;
    }
}
