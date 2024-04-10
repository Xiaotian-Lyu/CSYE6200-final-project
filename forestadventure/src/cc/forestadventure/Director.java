package cc.forestadventure;

import cc.forestadventure.scene.Index;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Director {
	public static final double WIDTH = 370, HEIGHT = 330;
	
	private static Director instance = new Director();
	private Stage stage;
	
	private Director() {}
	
	public static Director getInstance() {
		return instance;
	}
	
	public void init(Stage stage) {
		AnchorPane root = new AnchorPane(); 
		Scene scene = new Scene(root, WIDTH, HEIGHT);
		stage.setTitle("Forest Adventure"); //Forest Adventure
		stage.getIcons().add(new Image("resources/logo.png")); 
		stage.setResizable(false);
		stage.setScene(scene);
		stage.setWidth(WIDTH);
		stage.setHeight(HEIGHT);
		this.stage = stage;
		toIndex();
		stage.show();
	}
	
	public void toIndex() { //跳转到主页
		Index.load(stage);
		
	}
	
	public void gameOver() {
		
	}
	
	public void gameStart() {
		
	}

}