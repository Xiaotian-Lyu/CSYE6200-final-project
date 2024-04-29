package cc.forestadventure.controller;

import java.text.SimpleDateFormat;

import cc.forestadventure.Director;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import cc.forestadventure.scene.GameTime;

public class OverController {
	private GameTime gameTime;
	private long startTime;

	@FXML
	private ImageView flag;

	@FXML
	private ImageView toIndex;
	@FXML
	private Text completionTimeText;

	@FXML
	private Text remainingEnemiesText; // UI

	@FXML
	void mouseClickedToIndex(MouseEvent event) {
		Director.getInstance().toIndex(); // return back to first page

	}

	@FXML
	void mouseEnteredToIndex(MouseEvent event) {
		toIndex.setOpacity(0.5);

	}

	@FXML
	void mouseExitedToIndex(MouseEvent event) {
		toIndex.setOpacity(1);
	}

	public void setCompletionTime(long completionTimeInSeconds) {
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		String formattedTime = formatter.format(completionTimeInSeconds * 1000); 
		completionTimeText.setText("Completion Time: " + formattedTime);
	}

	// record the start time
	public void startTimer() {
		gameTime = new GameTime();
		gameTime.begin();
		startTime = System.currentTimeMillis();
	}

	public void endTimer() {
		long endTime = System.currentTimeMillis();
		long elapsedTime = endTime - startTime;
		long completionTimeInSeconds = elapsedTime / 1000; 
		setCompletionTime(completionTimeInSeconds);
	}

	// if win change the text
	public void flagSuccess() {

		try {
			flag.setImage(new Image("resources/win-txt.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}


	}

}
