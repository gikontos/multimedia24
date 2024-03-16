package src.uiComponents;

import src.books.*;
import javafx.scene.control.ListCell;

public class BookListCell extends ListCell<Book> {

    @Override
    protected void updateItem(Book book, boolean empty) {
        super.updateItem(book, empty);

        if (empty || book == null) {
            setText(null);
        } else {
            setText(String.format("%s by %s\nISBN: %s\nAverage Rating: %.2f\n (from %d Users)",
                    book.getTitle(), book.getAuthor(), book.getIsbn(),
                    book.getRating(), book.getNumberOfRatings()));
        }
    }
}
