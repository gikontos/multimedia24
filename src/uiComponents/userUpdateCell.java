package src.uiComponents;

import src.users.User;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;

public class userUpdateCell extends TableCell<User, Button> {
    private final Button updateButton = new Button("Update");

    public userUpdateCell() {

        updateButton.setOnAction(event -> {
            User user = getTableView().getItems().get(getIndex());
            System.out.println("Update button clicked for user: " + user.getUsername());
            // Add your update logic here
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
