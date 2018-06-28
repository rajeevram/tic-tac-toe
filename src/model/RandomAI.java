package model;

import java.awt.Point;
import java.util.Random;

/**
 * This strategy selects the first available move at random.  It is easy to beat
 * 
 * @throws IGotNowhereToGoException whenever asked for a move that is impossible to deliver
 * 
 * @author Rajeev Ram
 */
public class RandomAI implements TicTacToeStrategy {

	int openSpotsLeft;
	
	// throw exception if no open moves
	public RandomAI() {
		openSpotsLeft = 9;
	}
	
	@Override
	public Point desiredMove(TicTacToeGame theGame) {
		Random random = new Random();
		char[][] gameBoard = theGame.getTicTacToeBoard();
		
		int x = random.nextInt(3);
		int y = random.nextInt(3);
		while (gameBoard[x][y] != '_') {
			x = random.nextInt(3);
			y = random.nextInt(3);
			if ( !theGame.stillRunning() && openSpotsLeft==0 ) {
				throw new IGotNowhereToGoException("No moves left.");
			}
		}
		openSpotsLeft--;
		return new Point(x,y);
	}
}