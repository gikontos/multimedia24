package src.uiComponents;

import src.Controllers.helpers.pageChanger;
import src.books.*;
import src.users.LibraryManager;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.stage.Stage;

public class BookDeleteCell extends TableCell<Book, Button> {
    private final Button deleteButton = new Button("Delete");

    public BookDeleteCell(LibraryManager manager, Stage stage) {
        deleteButton.setOnAction(event -> {
            Book book = getTableView().getItems().get(getIndex());
            manager.deleteBook(book);
            pageChanger page = new pageChanger(stage);
            page.changePage("bookList", manager, null);
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
