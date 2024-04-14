package cc.adventure.sprite;


import java.util.List;
import java.util.Random;

import cc.forestadventure.Director;
import cc.forestadventure.scene.GameScene;
import cc.forestadventure.util.Direction;
import cc.forestadventure.util.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

public class Character extends Role{
	
	Direction pdir;
	boolean keyup, keydown, keyleft, keyright;
    double oldx, oldy;
    public static Random random = new Random();
	
    public Character(double x, double y, Group group, Direction dir,Direction pdir, GameScene gameScene) {
		super(x, y, 60, 60, group, dir, gameScene);
		// the size 60-60 match the picture
		// TODO Auto-generated constructor stub
		this.pdir = pdir;
        speed = 5;
        if(group.equals(Group.green)) {//adventure
            imageMap.put("up", new Image("resources/adventure-up.png"));
            imageMap.put("down", new Image("resources/adventure-1.png"));
            imageMap.put("left", new Image("resources/adventure-left.png"));
            imageMap.put("right", new Image("resources/adventure-rigrht.png"));
        } else {
            imageMap.put("up", new Image("resources/monter-1.png"));
            imageMap.put("down", new Image("resources/monter-1.png"));
            imageMap.put("left", new Image("resources/monter-2.png"));
            imageMap.put("right", new Image("resources/monter-2.png"));
        }
		
	}

    //different direction when user use
    public void pressed(KeyCode keyCode) {
        switch (keyCode) {
            case UP:
                keyup = true;
                break;
            case DOWN:
                keydown = true;
                break;
            case LEFT:
                keyleft = true;
                break;
            case RIGHT:
                keyright = true;
        }
        redirect();
    }
    
    
    
    //release keyborod, move and open fire
    public void released(KeyCode keyCode) {
        switch (keyCode) {
            case F:
                openFire(); 
                break;
            case UP:
                keyup = false;
                break;
            case DOWN:
                keydown = false;
                break;
            case LEFT:
                keyleft = false;
                break;
            case RIGHT:
                keyright = false;
        }
        redirect();
    }

    public void redirect() {
        if(keyup && !keydown && !keyleft && !keyright) dir = Direction.up;
        else if(!keyup && keydown && !keyleft && !keyright) dir = Direction.down;
        else if(!keyup && !keydown && keyleft && !keyright) dir = Direction.left;
        else if(!keyup && !keydown && !keyleft && keyright) dir = Direction.right;
        else if(!keyup && !keydown && !keyleft && !keyright) dir = Direction.stop;
    }
    
    
    @Override
    public void move() {
        oldx = x;
        oldy = y;
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

        if(dir != Direction.stop) {
            pdir = dir;
        }

        //To make sure adventure not out of the border
        if(x < 0) x = 0;
        if(y < 0) y = 0;
        if(x > Director.WIDTH - width - 5) x = Director.WIDTH - width - 5;
        if(y > Director.HEIGHT - height - 30) y = Director.HEIGHT - height - 30;

        if(group.equals(Group.red)) {
            int i = random.nextInt(40);
            switch (i) {
                case 15:
                    Direction d[] = Direction.values();
                    dir = d[random.nextInt(d.length)];
                    break;
                case 30:
                    openFire(); 
                    break;
            }
        }
    }
    
    
    @Override
    public void paint(GraphicsContext graphicsContext) {
    	if (!alive) {
            gameScene.bullets.remove(this);
          
            return;
        }
        super.paint(graphicsContext);
        move();
        switch (pdir) {
            case up:
                image = imageMap.get("up");
                break;
            case down:
                image = imageMap.get("down");
                break;
            case left:
                image = imageMap.get("left");
                break;
            case right:
                image = imageMap.get("right");
                break;
        }
        super.paint(graphicsContext);
        move();
    }

    //adventure openFire
    public void openFire() {
        double bulletx = x;
        double bullety = y;
        switch (pdir) {
        	//make sure the fire from the bullet do not offset
            case up:
                bulletx = x + 25;
                bullety = y;
                break;
            case down:
                bulletx = x + 25;
                bullety = y + height;
                break;
            case left:
                bulletx = x;
                bullety = y + 25;
                break;
            case right:
                bulletx = x + width;
                bullety = y + 25;

        }
        
        //SoundEffect.play("/sound/attack.mp3");
        gameScene.bullets.add(new Bullet(bulletx, bullety, group, pdir, gameScene));
    }
    
    public boolean impactCharacter(Sprite sprite) {
        if(sprite != null && !sprite.equals(this) && getContour().intersects(sprite.getContour())) {
    	
            x = oldx;
            y = oldy;
            return true;
        }
        return false;
    }

    public void impactCharacter(List<? extends Sprite> sprites) {
        for (Sprite sprite :sprites) {
        	impactCharacter(sprite);
        }
    }
    
    
   

}
