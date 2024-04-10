package cc.forestadventure.scene;

import cc.adventure.sprite.Adventure;
import cc.forestadventure.Director;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;

public class GameScene {
	private Canvas canvas = new Canvas(Director.WIDTH, Director.HEIGHT);
	private GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
	
	private KeyProcess keyProcess = new KeyProcess();
	private Refresh refresh = new Refresh();
//	private Adventure selfAdventure = new Adventure(x, y, width, height, group, up, this); 
	
	private void paint() {
		
		
	}
	
	private class Refresh extends AnimationTimer {
		public void handle(long now) {
			paint();
		}
	}
	
	private class KeyProcess implements EventHandler<KeyEvent>{
		@Override
		public void handle(KeyEvent event) {
		
	}
	}
		

}
