package cc.forestadventure.scene;

import java.util.ArrayList;
import java.util.List;

import cc.adventure.sprite.Adventure;
import cc.adventure.sprite.Background;
import cc.adventure.sprite.Bullet;
import cc.forestadventure.Director;
import cc.forestadventure.util.Direction;
import cc.forestadventure.util.Group;
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
	private Adventure adventureself = new Adventure(949, 928, Group.green, Direction.stop, Direction.down,this);//the size of picture not sure yet
	public List<Bullet> bullets = new ArrayList<>();
    public List<Adventure> Adventures = new ArrayList<>();
	
		
	private void paint() {
		background.paint(graphicsContext);
		adventureself.paint(graphicsContext);
		
		for(Bullet bullet : bullets) {
			bullet.paint(graphicsContext);
		}
		
	}
	
	public void init(Stage stage) {
		AnchorPane root = new AnchorPane(canvas);
		stage.getScene().setRoot(root);
		stage.getScene().setOnKeyReleased(keyProcess);
		stage.getScene().setOnKeyPressed(keyProcess);
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
	
	private class KeyProcess implements EventHandler<KeyEvent> {

        @Override
        public void handle(KeyEvent event) {
            KeyCode keyCode = event.getCode();


            if(event.getEventType() == KeyEvent.KEY_RELEASED) {
                if(keyCode.equals(KeyCode.SPACE)) {
                    pauseOrContinue();
                }
                if(adventureself != null) adventureself.released(keyCode);
            }else if(event.getEventType() == KeyEvent.KEY_PRESSED) {
                if(adventureself != null) {
                	adventureself.pressed(keyCode);
                }
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
