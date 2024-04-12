package cc.forestadventure.scene;

import cc.adventure.sprite.Adventure;
import cc.adventure.sprite.Background;
import cc.forestadventure.Director;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class GameScene {
	private Canvas canvas = new Canvas(Director.WIDTH, Director.HEIGHT);
	private GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
	
	private KeyProcess keyProcess = new KeyProcess();
	private Refresh refresh = new Refresh();
	private boolean running = false;
	
	private Background background = new Background();
//	private Adventure selfAdventure = new Adventure(x, y, width, height, group, up, this); 
	
	private void paint() {
		background.paint(graphicsContext);
		
	}
	
	public void init(Stage stage) {
		AnchorPane root = new AnchorPane(canvas);
		stage.getScene().setRoot(root);
		stage.getScene().setOnKeyReleased(keyProcess);
		running = true;
		refresh.start();
	}
	
	public void clear(Stage stage) {
		stage.getScene().removeEventHandler(KeyEvent.KEY_RELEASED, keyProcess);
		refresh.stop();
	}
	
	private class Refresh extends AnimationTimer {
		@Override
		public void handle(long now) {
			if (running) {
			paint();
			}
		}
	}
	
	private class KeyProcess implements EventHandler<KeyEvent>{
		@Override
		public void handle(KeyEvent event) {
			KeyCode keyCode = event.getCode();
			if(keyCode.equals(KeyCode.SPACE)) {
				pauseOrContinue();
			}
		
	    }
    }
	
	public void pauseOrContinue() {
		if(running) {
			running = false;
		}else {
			running = true;
		}
	}
		

}
