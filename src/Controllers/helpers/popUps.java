package src.Controllers.helpers;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class popUps {
    public popUps() {
    }

    public void showPopUp(String file) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../ui/" + file + ".fxml"));
            Parent popupRoot = loader.load();
            Scene popupScene = new Scene(popupRoot);
            Stage popupStage = new Stage();
            popupStage.setScene(popupScene);
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
