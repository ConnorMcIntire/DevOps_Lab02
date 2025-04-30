package edu.westga.devops.a7.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import edu.westga.devops.a7.model.HiLowGame;
import edu.westga.devops.a7.model.GuessReport;

/**
 * CodeBehind To Handle Processing for the MainWindow
 *
 * @author	DevOps
 * @version Spring 2025
 */
public class MainWindow {       
	private HiLowGame gameManager;
	
	@FXML private TextField guess;
	@FXML private TextArea result;
	@FXML private Button makeGuessButton;

	@FXML
	public void makeGuess(ActionEvent event) {
		int guess = -1;
		try {
			guess = Integer.parseInt(this.guess.getText());
		} catch (NumberFormatException e) {
			this.result.setText("Guess must be a valid integer.");
			return;
		}
		
		GuessReport result = this.gameManager.checkGuess(guess);
		if(result == GuessReport.TOO_HIGH) {
			this.result.setText("Guess is too high, try again.");
		}
		else if(result == GuessReport.TOO_LOW) {
			this.result.setText("Guess is too low, try again.");
		}
		else if(result == GuessReport.GUESS_LIMIT_REACHED) {
			this.result.setText("Failed to guess the number, new number has been generated, try again.");
		}
		else if(result == GuessReport.CORRECT) {
			this.result.setText("Sucessfully guessed the number, new number has been generated, try again.");
		}
		else {
			this.result.setText("How did you get here? Try again???");
		}
	}
	
	/**
	 * Handle initialization checks for the JavaFX components, and perform any
	 * necessary custom initialization.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 */
	@FXML
	public void initialize() {
		this.gameManager = new HiLowGame();
	}
}
