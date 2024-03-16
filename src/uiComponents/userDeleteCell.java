package src.uiComponents;

import src.users.User;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;

public class userDeleteCell extends TableCell<User, Button> {
    private final Button deleteButton = new Button("Delete");

    public userDeleteCell() {
        deleteButton.setOnAction(event -> {
            User user = getTableView().getItems().get(getIndex());
            System.out.println("Delete button clicked for user: " + user.getUsername());
            // Add your delete logic here
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
