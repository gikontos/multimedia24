package src.uiComponents;

import src.books.*;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;

public class BookDeleteCell extends TableCell<Book, Button> {
    private final Button deleteButton = new Button("Delete");

    public BookDeleteCell() {
        deleteButton.setOnAction(event -> {
            Book book = getTableView().getItems().get(getIndex());
            System.out.println("Delete button clicked for book: " + book.getTitle());
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
