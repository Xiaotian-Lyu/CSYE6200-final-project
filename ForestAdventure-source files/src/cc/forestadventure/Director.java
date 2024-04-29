package cc.forestadventure;

import cc.forestadventure.scene.GameOver;
import cc.forestadventure.scene.GameScene;
import cc.forestadventure.scene.Index;
import cc.forestadventure.sound.SoundEffect;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Director {
	public static final double WIDTH = 750, HEIGHT = 750;

	private static Director instance = new Director();
	private Stage stage;
	private GameScene gameScene = new GameScene();

	private Director() {
	}

	public static Director getInstance() {
		return instance;
	}

	public void init(Stage stage) {
		AnchorPane root = new AnchorPane();
		Scene scene = new Scene(root, WIDTH, HEIGHT);
		stage.setTitle("Forest Adventure"); // Forest Adventure
		stage.getIcons().add(new Image("/resources/adventure-1.png"));
		stage.setResizable(false);
		stage.setScene(scene);
		stage.setWidth(WIDTH);
		stage.setHeight(HEIGHT);
		this.stage = stage;
		toIndex();
		stage.show();
	}

	public void toIndex() { // to the first page
		Index.load(stage);

	}

	public void gameOver(boolean success) {
		String sound = success ? "/resources/sound/success.wav" : "/resources/sound/move.mp3";
		SoundEffect.play(sound);
		gameScene.clear(stage);
		GameOver.load(stage, success);
	}

	public void gameStart() {
		gameScene.init(stage);
	}

}
