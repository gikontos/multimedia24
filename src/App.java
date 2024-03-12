package src;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("hello world");
        StackPane root = new StackPane();
        Text text = new Text("Hello world");
        root.getChildren().add(text);
        Scene scene = new Scene(root, 320, 240);
        stage.setScene(scene);
        stage.show();
    }

    // static void setRoot(String fxml) throws IOException {
    // scene.setRoot(loadFXML(fxml));
    // }

    // private static Parent loadFXML(String fxml) throws IOException {
    // FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml +
    // ".fxml"));
    // return fxmlLoader.load();
    // }

    public static void main(String[] args) {
        launch(args);
    }

}