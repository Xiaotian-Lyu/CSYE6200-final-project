package cc.forestadventure;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception{
		Director.getInstance().init(primaryStage);
		
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}
}
