package src.uiComponents;

import src.books.*;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;

public class LoansEndCell extends TableCell<Loan, Button> {
    private final Button deleteButton = new Button("End Loan");

    public LoansEndCell() {
        deleteButton.setOnAction(event -> {
            Loan book = getTableView().getItems().get(getIndex());
            System.out.println("End button clicked for book: " + book.getBookTitle());
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
