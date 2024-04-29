package cc.forestadventure.scene;

import java.io.IOException;

import cc.forestadventure.controller.OverController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import cc.forestadventure.scene.GameScene;

public class GameOver {

    public static void load(Stage stage, boolean success) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Index.class.getResource("/resources/fxml/GameOver.fxml"));
            Parent root = fxmlLoader.load();
            OverController overController = fxmlLoader.getController();
            if(success) overController.flagSuccess();
            stage.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
            
          
        }
    }
}
