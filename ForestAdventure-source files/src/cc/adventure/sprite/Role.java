package cc.adventure.sprite;

import java.util.HashMap;
import java.util.Map;

import cc.forestadventure.scene.GameScene;
import cc.forestadventure.util.Direction;
import cc.forestadventure.util.Group;
import javafx.scene.image.Image;

public abstract class Role extends Sprite {

	boolean alive = true;
	Group group;
	Direction dir;
	double speed;

	Map<String, Image> imageMap = new HashMap<>();

	public Role(double x, double y, double width, double height, Group group, Direction dir, GameScene gameScene) {
		super(null, x, y, width, height, gameScene);
		this.group = group;
		this.dir = dir;
	}

	public abstract void move();

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

}
