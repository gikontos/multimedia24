package src.uiComponents;

import src.Controllers.helpers.popUps;
import src.books.*;
import src.users.LibraryManager;
import src.users.User;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;

public class resultsReserveCell extends TableCell<Book, Button> {
    private final Button updateButton = new Button("Reserve");

    public resultsReserveCell(LibraryManager manager, User user) {

        updateButton.setOnAction(event -> {
            Book book = getTableView().getItems().get(getIndex());
            popUps popup = new popUps();
            if (manager.addLoan(book, user)) {
                popup.showPopUp("Success");
            } else {
                popup.showPopUp("noReserve");
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
