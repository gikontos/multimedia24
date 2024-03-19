package src.uiComponents;

import src.Controllers.helpers.pageChanger;
import src.users.LibraryManager;
import src.users.User;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.stage.Stage;

public class userDeleteCell extends TableCell<User, Button> {
    private final Button deleteButton = new Button("Delete");

    public userDeleteCell(LibraryManager manager, Stage stage) {
        deleteButton.setOnAction(event -> {
            User user = getTableView().getItems().get(getIndex());
            manager.deleteUser(user);
            pageChanger page = new pageChanger(stage);
            page.changePage("userList", manager, null);
        });

    }

    @Override
    protected void updateItem(Button item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            setGraphic(null);
        } else {
            setGraphic(deleteButton);
        }
    }

    public Button getDeleteButton() {
        return deleteButton;
    }

}
