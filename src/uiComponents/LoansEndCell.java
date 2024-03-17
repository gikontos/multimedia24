package src.uiComponents;

import src.Controllers.helpers.pageChanger;
import src.books.*;
import src.users.LibraryManager;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.stage.Stage;

public class LoansEndCell extends TableCell<Loan, Button> {
    private final Button deleteButton = new Button("End Loan");

    public LoansEndCell(LibraryManager manager, Stage stage) {
        deleteButton.setOnAction(event -> {
            Loan loan = getTableView().getItems().get(getIndex());
            manager.terminateLoan(loan);
            pageChanger page = new pageChanger(stage);
            page.changePage("loansList", manager);
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
