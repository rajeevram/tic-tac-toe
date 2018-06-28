package view;

import java.util.Observable;
import java.util.Observer;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import model.TicTacToeGame;

/**
 * One view of the TicTacToe GUI. This calls on a toString() version for the
 * Tic Tac Toe Game. Then, you can enter numbers in to TextFields and click
 * on a buttom to make the move.
 * 
 * @author Rajeev Ram
 *
 */


public class TextAreaView extends BorderPane implements Observer {

	private int rowInt,columnInt;
	private TicTacToeGame theGame;
	private Label rLabel, cLabel;
	private TextField rInput, cInput;
	private Button pressButton;
	private TextArea gameString;

	// Start a new game
	public TextAreaView(TicTacToeGame TicTacToeGame) {
		theGame = TicTacToeGame;
		initializePane();
	}

	// Set up the Border Pane
	private void initializePane() {
		
		this.gameString = new TextArea(theGame.toString());
		gameString.setEditable(false);
		Font myFont = new Font("Courier New", 38);
		gameString.setFont(myFont);
		setBottom(gameString);
		
		Insets insetTop = new Insets(10,0,0,0);
		BorderPane.setMargin(gameString,insetTop);
		
		GridPane gridPane = new GridPane();
		gridPane.setAlignment(Pos.CENTER);
	    gridPane.setVgap(5);
	    gridPane.setHgap(5);
		
	    Insets insetLeft = new Insets(0,0,0,60);
	    
	    this.rLabel = new Label("Row");
	    rLabel.setMinWidth(50.0);
	    rLabel.setAlignment(Pos.CENTER);
	    GridPane.setMargin(rLabel,insetLeft);
	    this.cLabel = new Label("Column");
	    cLabel.setMinWidth(50.0);
	    cLabel.setAlignment(Pos.CENTER);
	    GridPane.setMargin(cLabel,insetLeft);
	    
	    
	    this.rInput = new TextField();
	    rInput.setMaxWidth(50.0);
	    this.cInput = new TextField();
	    cInput.setMaxWidth(50.0);
	    
	    
	    this.pressButton = new Button("Make move");
	    
	    gridPane.add(rLabel, 1, 1);
	    gridPane.add(rInput, 1, 1);
	    gridPane.add(cLabel, 1, 2);
	    gridPane.add(cInput, 1, 2);
	    gridPane.add(pressButton,1,3);
	    
	    setTop(gridPane);
		
	    pressButton.setOnAction(new ButtonHandler());
		
	}
	
	// The private listener class
	private class ButtonHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			
			boolean rowTextFieldSet = false;
			boolean columnTextFieldSet = false;
			
			try {
				if ( theGame.stillRunning() ) {
					rowInt = Integer.valueOf(rInput.getText());
					pressButton.setText("Make move");
					rowTextFieldSet = true;
				}
			}
			catch (NumberFormatException e) {
				rInput.setText("");
				rowInt = -1;
			}
			
			try {
				if ( theGame.stillRunning() ) {
					columnInt = Integer.valueOf(cInput.getText());
					pressButton.setText("Make move");
					columnTextFieldSet = true;
				}
			}
			catch (NumberFormatException e) {
				cInput.setText("");
				columnInt = -1;
			}
			
			if ( !theGame.stillRunning() ) {
				return;
			}
			else if ( !rowTextFieldSet || !columnTextFieldSet ) {
				pressButton.setText("Make move");
			}
			else if ( (rowInt<0) || (rowInt>2) || (columnInt<0) || (columnInt>2) ) {
				pressButton.setText("Invalid choice");
				rInput.setText("");
				cInput.setText("");
			}
			else if (theGame.stillRunning() && theGame.getTicTacToeBoard()[rowInt][columnInt] != '_') {
				pressButton.setText("Invalid choice");
				rInput.setText("");
				cInput.setText("");
			}
			else if (theGame.stillRunning() && theGame.getTicTacToeBoard()[rowInt][columnInt] == '_')  {
				theGame.humanMove(rowInt, columnInt, false);
			}
			
		}
		
	}
	
	// The update method
	@Override
	public void update(Observable observable, Object arg) {
		
		theGame = (TicTacToeGame) observable;
		
		rInput.setText("");
		cInput.setText("");
		gameString.setText(theGame.toString());
		
		if ( theGame.didWin('X') ) {
			pressButton.setText("X wins");
			pressButton.setDisable(true);
		}
		else if ( theGame.didWin('O') ) {
			pressButton.setText("O wins");
			pressButton.setDisable(true);
		}
		else if ( theGame.tied() ) {
			pressButton.setText("Tie");
			pressButton.setDisable(true);
		}
		else if ( (arg!=null) && arg.equals("startNewGame()") ) {
			pressButton.setText("Make move");
			pressButton.setDisable(false);
		}
		else {
			pressButton.setText("Make move");
		}
		
	}
	
	
	
}