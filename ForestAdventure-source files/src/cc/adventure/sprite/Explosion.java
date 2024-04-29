package cc.adventure.sprite;

import cc.forestadventure.scene.GameScene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Explosion extends Sprite {
	private int count = 0;
	private static Image[] images = new Image[] { new Image("/resources/explosion1.png"),
			new Image("/resources/explosion2.png"), new Image("/resources/explode3.png"),
			new Image("/resources/explosion4.png"),
			// explosions

	};

	public Explosion(double x, double y, GameScene gameScene) {
		super(null, x, y, 0, 0, gameScene);
	}

	@Override
	public void paint(GraphicsContext graphicsContext) {
		if (count >= images.length) {
			gameScene.explosion.remove(this);
			return;
		}
		image = images[count];
		double ex = x - image.getWidth() / 2;
		double ey = y - image.getHeight() / 2;
		graphicsContext.drawImage(image, ex, ey);
		count++;
	}
}
