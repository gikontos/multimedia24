package src.uiComponents;

import src.books.*;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;

public class CategoryDeleteCell extends TableCell<Category, Button> {
    private final Button deleteButton = new Button("Delete");

    public CategoryDeleteCell() {
        deleteButton.setOnAction(event -> {
            Category category = getTableView().getItems().get(getIndex());
            System.out.println("Delete button clicked for category: " + category.getCategory());
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
