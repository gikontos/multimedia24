package src.uiComponents;

import src.books.*;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;

public class resultsReserveCell extends TableCell<Book, Button> {
    private final Button updateButton = new Button("Reserve");

    public resultsReserveCell() {

        updateButton.setOnAction(event -> {
            Book book = getTableView().getItems().get(getIndex());
            System.out.println("Update button clicked for book: " + book.getTitle());
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
