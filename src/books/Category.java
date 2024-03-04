package src.books;

import java.io.Serializable;

public class Category implements Serializable {
    private String name;

    public Category(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.name = category;
    }

    public String viewInfo() {
        return "category: " + this.name;
    }
}
