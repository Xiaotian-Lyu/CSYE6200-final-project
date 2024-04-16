package cc.forestadventure.scene;

import java.util.ArrayList;
import java.util.List;

import cc.adventure.sprite.Character;
import cc.adventure.sprite.Explosion;
import cc.adventure.sprite.Pine;
import cc.adventure.sprite.Vine;
import cc.adventure.sprite.Background;
import cc.adventure.sprite.Box;
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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GameScene {
	private Canvas canvas = new Canvas(Director.WIDTH, Director.HEIGHT);
	private GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
	
	private KeyProcess keyProcess = new KeyProcess();
	private Refresh refresh = new Refresh();
	private boolean running = false;
	
	private Background background = new Background();
	private Character self = null; //newly added
	public List<Bullet> bullets = new ArrayList<>();
    public List<Character> characters = new ArrayList<>();
    public List<Explosion> explosion = new ArrayList<>();
    public List<Vine>  vines = new ArrayList<>();
	public List<Box> boxes = new ArrayList<>();
	public List<Pine> pines = new ArrayList<>();
		
	private void paint() {
		background.paint(graphicsContext);
		self.paint(graphicsContext);
		self.impactCharacter(characters);
		self.impactCharacter(vines);
		self.impactCharacter(boxes); //newly added
		
		 for (int i = 0; i < bullets.size(); i++) {
	            Bullet bullet = bullets.get(i);
	            bullet.paint(graphicsContext);
	            bullet.impactCharacter(characters);
	            bullet.impactVine(vines);
	            bullet.impactBoulder(boxes); 
	            bullet.impactCharacter(self);
	        }
		 for (int i = 0; i < characters.size(); i++) {
			 Character character = characters.get(i);
			 character.paint(graphicsContext);
			 character.impactCharacter(vines);
	         character.impactCharacter(self);
	         character.impactCharacter(boxes); //newly added after Boulder Class is created
	         character.impactCharacter(characters);//newly added
	         
	        }
		 for (int i = 0; i < explosion.size(); i++) {
			    Explosion e = explosion.get(i);
	            e.paint(graphicsContext);
	        }
		 
		 for (int i = 0; i < vines.size(); i++) {
	            Vine vine = vines.get(i);
	            vine.paint(graphicsContext);
	        }
		 
		 for (int i = 0; i < boxes.size(); i++) {
			 Box box = boxes.get(i);
			 box.paint(graphicsContext);
		 }
		 
		 for (int i = 0; i < pines.size(); i++) {
			 Pine pine = pines.get(i);
			 pine.paint(graphicsContext);
		 }
		 
       graphicsContext.setFill(Color.RED);
       graphicsContext.setFont(new Font(10));
       graphicsContext.fillText("Number of Monsters：" + characters.size(), 200, 60);
       //graphicsContext.fillText("Number of Bullets子弹的数量：" + bullets.size(), 200, 90);
       
       if(!self.isAlive()) {//newly added
    	   Director.getInstance().gameOver(false);
       } else if(characters.isEmpty()){
    	   Director.getInstance().gameOver(true);
       }
   }
		
	
	
	public void init(Stage stage) {
		AnchorPane root = new AnchorPane(canvas);
		stage.getScene().setRoot(root);
		stage.getScene().setOnKeyReleased(keyProcess);
		stage.getScene().setOnKeyPressed(keyProcess);
		running = true;
		self = new Character(949, 928, Group.green, Direction.stop, Direction.down,this);//the size of picture not sure yet //newly added
		initSprite();
		refresh.start();
	}
	
	private void initSprite() {
        for (int i = 0; i < 6; i++) {
            Character character = new Character(200 + i * 80, 100,Group.red, Direction.stop, Direction.down, this);//monster
            characters.add(character);
        }
        for (int i = 0; i < 20; i++) {
            Vine vine1 = new Vine(100 + i* 31, 200 );
            Vine vine2 = new Vine(100 + i* 31, 232 );
            vines.add(vine1);
            vines.add(vine2);
        }
        
        for (int i = 0; i < 5; i++) {
        	Box box = new Box(300 +i*60,300);
        	boxes.add(box);
        }
        
        for (int i = 0;i < 4; i++) {
        	Pine pine1 = new Pine(290 + i*63*2, 400);
        	Pine pine2 = new Pine(63 + i*63*2, 0);
        	pines.add(pine1);
        	pines.add(pine2);
        }
	}
	
	public void clear(Stage stage) {
		stage.getScene().removeEventHandler(KeyEvent.KEY_PRESSED, keyProcess);
		stage.getScene().removeEventHandler(KeyEvent.KEY_RELEASED, keyProcess);
		refresh.stop();
		self = null;
		characters.clear();
		bullets.clear();
		vines.clear();
		explosion.clear();
		boxes.clear();
		pines.clear();
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
                if(self != null) self.released(keyCode);
            }else if(event.getEventType() == KeyEvent.KEY_PRESSED) {
                if(self != null) {
                	self.pressed(keyCode);
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
