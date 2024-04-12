package cc.forestadventure.controller;

import cc.forestadventure.Director;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class IndexController {

    @FXML
    private ImageView startGame;
    
    @FXML
    void mouseClickedStartGame(MouseEvent event) {
        Director.getInstance().gameStart();
    }

   

    @FXML
    void mouseEnteredStartGame(MouseEvent event) {
    	startGame.setOpacity(0.5);
    }

    @FXML
    void mouseExitedStartGame(MouseEvent event) {
        startGame.setOpacity(1);
    }

}

