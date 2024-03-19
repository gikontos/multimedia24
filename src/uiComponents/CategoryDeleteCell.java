package src.uiComponents;

import src.Controllers.helpers.pageChanger;
import src.books.*;
import src.users.LibraryManager;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.stage.Stage;

public class CategoryDeleteCell extends TableCell<Category, Button> {
    private final Button deleteButton = new Button("Delete");

    public CategoryDeleteCell(LibraryManager manager, Stage stage) {
        deleteButton.setOnAction(event -> {
            Category category = getTableView().getItems().get(getIndex());
            manager.deleteCategory(category);
            pageChanger page = new pageChanger(stage);
            page.changePage("categoryList", manager, null);
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
