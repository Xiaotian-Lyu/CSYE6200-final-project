package cc.forestadventure.controller;

import cc.forestadventure.Director;
import cc.forestadventure.sound.SoundEffect;
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
    	SoundEffect.play("/resources/sound/button.wav");
    }

    @FXML
    void mouseExitedStartGame(MouseEvent event) {
        startGame.setOpacity(1);
    }

}

