package view;

import java.util.Observable;
import java.util.Observer;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import model.TicTacToeGame;

/**
 * One view of the TicTacToe GUI. Click on the canvas to draw pictures of
 * Xs and Os while playing the Game.
 * 
 * @author Rajeev Ram
 *
 */

public class DrawingView extends BorderPane implements Observer {
	
	private int rowInt,columnInt;
	private TicTacToeGame theGame;
	private char[][] gameBoard;
	private Label stateLabel;
	private Canvas canvas;
	private GraphicsContext gc;
	private Pane wrapper;
	private Image ex = new Image("file:images/x.png", 50, 50, false, false);
	private Image oh = new Image("file:images/o.png", 50, 50, false, false);
	final public static String clickMessage = "Click to make a move";
	
	public DrawingView(TicTacToeGame TicTacToeGame) {
		clearEverything(TicTacToeGame);
	}
	
	// Start a new game
	private void clearEverything(TicTacToeGame TicTacToeGame) {
		theGame = TicTacToeGame;
		gameBoard = theGame.getTicTacToeBoard();
		initializePane();
		canvas.addEventHandler(MouseEvent.MOUSE_CLICKED,new ClickDraw());
	}
	
	// Set up nested Pane within BorderPane
	private void initializePane() {
		
		this.stateLabel =  new Label(clickMessage);
		setTop(stateLabel);
		
		Font myFont = new Font("Arial", 17);
		stateLabel.setFont(myFont);
		
		Insets insetTop = new Insets(20,0,0,0);
		BorderPane.setMargin(stateLabel,insetTop);
		BorderPane.setAlignment(stateLabel, Pos.CENTER);
		
		initializeCanvas();
	}
	
	// Set up nested Canvas within Pane
	private void initializeCanvas() {
		
		Insets insetsAll = new Insets(20,0,6,6);
		this.canvas = new Canvas(243,243);
		this.gc = canvas.getGraphicsContext2D();
		this.wrapper = new Pane();
		
		setCenter(wrapper);
		wrapper.getChildren().add(canvas);
		BorderPane.setMargin(wrapper,insetsAll);
		BorderPane.setAlignment(wrapper,Pos.TOP_CENTER);
		
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(3.0);
		gc.strokeRect(0, 0, canvas.getWidth(), canvas.getHeight());
		gc.setLineWidth(0.8);
		gc.strokeLine(0,81,243,81);
		gc.strokeLine(0,162,243,162);
		gc.strokeLine(81,0,81,243);
		gc.strokeLine(162,0,162,243);
	}
	
	// The private listener class
	private class ClickDraw implements EventHandler<MouseEvent> {

		double x = -1;
		double y = -1;
		
		@Override
		public void handle(MouseEvent click) {

			rowInt = columnInt = 1;
			x = click.getX(); y = click.getY();

			if (x<81) { columnInt=0; }
			else if (x<162) { columnInt=1; }
			else if (x<243) { columnInt=2; }

			if (y<81) { rowInt=0; }
			else if (y<162) { rowInt=1; }
			else if (y<243) { rowInt=2; }
			
			if ( !theGame.stillRunning() ) {
				return;
			}
			else if ( (rowInt<0) || (rowInt>2) || (columnInt<0) || (columnInt>2) ) {
				stateLabel.setText("Invalid choice");
			}
			else if (theGame.stillRunning() && theGame.getTicTacToeBoard()[rowInt][columnInt] != '_') {
				stateLabel.setText("Invalid choice");
			}
			else if (theGame.stillRunning() && theGame.getTicTacToeBoard()[rowInt][columnInt] == '_')  {
				theGame.humanMove(rowInt,columnInt,false);
			}

		}

	}
	
	// Draw the X picture
	private void drawX() {
		for (int i = 0; i < 3; i ++) {
			for (int j = 0; j < 3; j++) {
				if ( gameBoard[i][j]=='X' ) {
					gc.drawImage(ex,j*81+16,i*81+16);
				}
			}
		}
	}
	
	// Draw the O picture
	private void drawO() {
		for (int i = 0; i < 3; i ++) {
			for (int j = 0; j < 3; j++) {
				if ( gameBoard[i][j]=='O' ) {
					gc.drawImage(oh,j*81+16,i*81+16);
				}
			}
		}
	}
	
	// Update the Canvas after a move
	private void updateCanvas() {
		wrapper.getChildren().remove(canvas);
		drawX();
		drawO();
		wrapper.getChildren().add(canvas);
	}

	// The update method
	@Override
	public void update(Observable observable, Object arg) {

		theGame = (TicTacToeGame) observable;
		updateCanvas();
		
		if ( theGame.didWin('X') ) {
			stateLabel.setText("X wins");
		}
		else if ( theGame.didWin('O') ) {
			stateLabel.setText("O wins");
		}
		else if ( theGame.tied() ) {
			stateLabel.setText("Tie");
		}
		else if ( (arg!=null) && arg.equals("startNewGame()") ) {
			clearEverything(theGame);
		}
		else {
			stateLabel.setText(clickMessage);
		}

	}

}
