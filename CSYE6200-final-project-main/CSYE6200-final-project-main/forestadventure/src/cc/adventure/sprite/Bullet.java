package cc.adventure.sprite;

import java.util.List;

import cc.forestadventure.Director;
import cc.forestadventure.scene.GameScene;
import cc.forestadventure.util.Direction;
import cc.forestadventure.util.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Bullet extends Role {		
	 public Bullet(double x, double y, Group group, Direction dir, GameScene gameScene) {
	        super(x, y, 0, 0, group, dir, gameScene);
	        // the speed of the bullet
	        speed = 10;
	        //different direction of bullet
	        if (dir.equals(Direction.up) || dir.equals(Direction.down)) {
	            width = 10;
	            height = 22;
	        } else if (dir.equals(Direction.left) || dir.equals(Direction.right)) {
	            height = 10;
	            width = 22;
	        }

	        if (group.equals(Group.green)) {//means the adventure
	            switch (dir) {
	                case up:
	                    image = new Image("resources/bullet-green-up.png");
	                    break;
	                case down:
	                    image = new Image("resources/bullet-green-down.png");
	                    break;
	                case left:
	                    image = new Image("resources/bullet-green-left.png");
	                    break;
	                case right:
	                    image = new Image("resources/bullet-green-right.png");
	                    break;
	            }
	        } else {
	            switch (dir) {
	                case up:
	                    image = new Image("resources/bullet-red-up.png");
	                    break;
	                case down:
	                    image = new Image("resources/bullet-red-down.png");
	                    break;
	                case left:
	                    image = new Image("resources/bullet-red-left.png");
	                    break;
	                case right:
	                    image = new Image("resources/bullet-red-right.png");
	                    break;
	            }
	        }
	    }

	    @Override
	    public void move() {
	        switch (dir) {
	            case up:
	                y -= speed;
	                break;
	            case down:
	                y += speed;
	                break;
	            case left:
	                x -= speed;
	                break;
	            case right:
	                x += speed;
	                break;
	        }
	        
//	        if(x > Director.WIDTH - width - 5) x = Director.WIDTH - width - 5;
//	        if(y > Director.HEIGHT - height - 30) y = Director.HEIGHT - height - 30;
	        if (x < 0 || y < 0 || x > Director.WIDTH || y > Director.HEIGHT) {
	            gameScene.bullets.remove(this);   
	        }

	    }
	    
	    public void paint(GraphicsContext graphicsContext) {
	    	  if (!alive) {
	              gameScene.bullets.remove(this);
	              gameScene.explosion.add(new Explosion(x, y, gameScene));
	              //SoundEffect.play("/sound/explosion.wav");
	              
	              return;
	          }
	    	super.paint(graphicsContext);
	    	move();
	    }
	    public boolean impactCharacter(Character character) {
	        if (character != null && !character.group.equals(this.group) && getContour().intersects(character.getContour())) {
	        	character.setAlive(false);
	            alive = false;
	            return true;
	        }
	        return false;
	    }
	    public void impactCharacter(List<Character> characters) {
	        for (Character c : characters) {
	            impactCharacter(c);
	        }
	    }
	    public boolean impactVine(Vine vine) {
	        if (vine != null && getContour().intersects(vine.getContour())) {
	            alive = false;
	            gameScene.vines.remove(vine);
	            return true;
	        }
	        return false;
	    }

	    public void impactVine(List<Vine> vines) {

	        for (int i = 0; i < vines.size(); i++) {
	        	Vine vine = vines.get(i);
	            impactVine(vine);
	        }
	    }
}
