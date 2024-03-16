package src.uiComponents;

import src.books.*;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;

public class CategoryUpdateCell extends TableCell<Category, Button> {
    private final Button updateButton = new Button("Update");

    public CategoryUpdateCell() {

        updateButton.setOnAction(event -> {
            Category category = getTableView().getItems().get(getIndex());
            System.out.println("Update button clicked for category: " + category.getCategory());
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
