package model;

import java.awt.Point;
import java.util.Random;

/**
 * This TTT strategy tries to prevent the opponent from winning by checking
 * for a space where the opponent is about to win. If none found, it randomly
 * picks a place to win, which an sometimes be a win even if not really trying.
 * 
 * The way I've designed this AI, it looks for horizontal wins/blocks first,
 * then vertical blocks/wins, then diagonal blocks/wins.
 * 
 * @author Rajeev Ram
 */
public class IntermediateAI implements TicTacToeStrategy {
	
	int openSpotsLeft;
	
	// throw exception if no open moves
	public IntermediateAI() {
		openSpotsLeft = 9;
	}
	
	@Override
	// Precondition: During testing the AI is associated with the 'O', the odd number move.
	public Point desiredMove(TicTacToeGame theGame) {
		
		char[][] gameBoard = theGame.getTicTacToeBoard();
		
		/* –––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––
		 * Begin by looking for wins
		 */
		
		// Horizontal ––––––––––––––
		// The top row permutations
		if ( (gameBoard[0][0]=='O') && (gameBoard[0][1]=='O') ) {
			if ( gameBoard[0][2]=='_') {
				return new Point(0,2);
			}
			
		}
		else if ( (gameBoard[0][0]=='O') && (gameBoard[0][2]=='O') ) {
			if ( gameBoard[0][1]=='_') {
				return new Point(0,1);
			}
			
		}
		else if ( (gameBoard[0][1]=='O') && (gameBoard[0][2]=='O')) {
			if ( gameBoard[0][0]=='_') {
				return new Point(0,0);
			}
			
		}
		
		// The middle row permutations
		if ( (gameBoard[1][0]=='O') && (gameBoard[1][1]=='O')) {
			if ( gameBoard[1][2]=='_') {
				return new Point(1,2);
			}
			
		}
		else if ( (gameBoard[1][0]=='O') && (gameBoard[1][2]=='O')) {
			if ( gameBoard[1][1]=='_') {
				return new Point(1,1);
			}
			
		}
		else if ( (gameBoard[1][1]=='O') && (gameBoard[1][2]=='O')) {
			if ( gameBoard[1][0]=='_') {
				return new Point(1,0);
			}
			
		}
		
		// The bottom row permutations
		if ( (gameBoard[2][0]=='O') && (gameBoard[2][1]=='O')) {
			if ( gameBoard[2][2]=='_') {
				return new Point(2,2);
			}
			
		}
		else if ( (gameBoard[2][0]=='O') && (gameBoard[2][2]=='O')) {
			if ( gameBoard[2][1]=='_') {
				return new Point(2,1);
			}
			
		}
		else if ( (gameBoard[2][1]=='O') && (gameBoard[2][2]=='O')) {
			if ( gameBoard[2][0]=='_') {
				return new Point(2,0);
			}
			
		}
		
		// Vertical ––––––––––––––––––––
		// The left column permutations
		if ( (gameBoard[0][0]=='O') && (gameBoard[1][0]=='O')) {
			if ( gameBoard[2][0]=='_') {
				return new Point(2,0);
			}
			
		}
		else if ( (gameBoard[0][0]=='O') && (gameBoard[2][0]=='O')) {
			if ( gameBoard[1][0]=='_') {
				return new Point(1,0);
			}
			
		}
		else if ( (gameBoard[1][0]=='O') && (gameBoard[2][0]=='O')) {
			if ( gameBoard[0][0]=='_') {
				return new Point(0,0);
			}
			
		}
		
		// The middle column permutations
		if ( (gameBoard[0][1]=='O') && (gameBoard[1][1]=='O')) {
			if ( gameBoard[2][1]=='_') {
				return new Point(2,1);
			}
			
		}
		else if ( (gameBoard[0][1]=='O') && (gameBoard[2][1]=='O')) {
			if ( gameBoard[1][1]=='_') {
				return new Point(1,1);
			}
			
		}
		else if ( (gameBoard[1][1]=='O') && (gameBoard[2][1]=='O')) {
			if ( gameBoard[0][1]=='_') {
				return new Point(0,1);
			}
			
		}
		
		// The bottom column permutations
		if ( (gameBoard[0][2]=='O') && (gameBoard[1][2]=='O')) {
			if ( gameBoard[2][2]=='_') {
				return new Point(2,2);
			}
			
		}
		else if ( (gameBoard[0][2]=='O') && (gameBoard[2][2]=='O')) {
			if ( gameBoard[1][2]=='_') {
				return new Point(1,2);
			}
			
		}
		else if ( (gameBoard[1][2]=='O') && (gameBoard[2][2]=='O')) {
			if ( gameBoard[0][2]=='_') {
				return new Point(0,2);
			}
			
		}
		
		// Diagonal ––––––––––––––––––
		// The diagonal up permutations
		if ( (gameBoard[0][2]=='O') && (gameBoard[1][1]=='O')) {
			if ( gameBoard[2][0]=='_') {
				return new Point(2,0);
			}
			
		}
		else if ( (gameBoard[0][2]=='O') && (gameBoard[2][0]=='O')) {
			if ( gameBoard[1][1]=='_') {
				return new Point(1,1);
			}
			
		}
		else if ( (gameBoard[2][0]=='O') && (gameBoard[1][1]=='O')) {
			if ( gameBoard[0][2]=='_') {
				return new Point(0,2);
			}
			
		}
		
		
		// The diagonal down permutations
		if ( (gameBoard[0][0]=='O') && (gameBoard[1][1]=='O')) {
			if ( gameBoard[2][2]=='_') {
				return new Point(2,2);
			}
			
		}
		else if ( (gameBoard[0][0]=='O') && (gameBoard[2][2]=='O')) {
			if ( gameBoard[1][1]=='_') {
				return new Point(1,1);
			}
			
		}
		else if ( (gameBoard[2][2]=='O') && (gameBoard[1][1]=='O')) {
			if ( gameBoard[0][0]=='_') {
				return new Point(0,0);
			}
			
		}
		
		/*–––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––
		 *  Continue by looking for blocks
		 */
		
		
		// Horizontal ––––––––––––––
		// The top row permutations
		if ( (gameBoard[0][0]=='X') && (gameBoard[0][1]=='X') ) {
			if ( gameBoard[0][2]=='_') {
				return new Point(0,2);
			}
			
		}
		else if ( (gameBoard[0][0]=='X') && (gameBoard[0][2]=='X') ) {
			if ( gameBoard[0][1]=='_') {
				return new Point(0,1);
			}
			
		}
		else if ( (gameBoard[0][1]=='X') && (gameBoard[0][2]=='X')) {
			if ( gameBoard[0][0]=='_') {
				return new Point(0,0);
			}
			
		}
		
		// The middle row permutations
		if ( (gameBoard[1][0]=='X') && (gameBoard[1][1]=='X')) {
			if ( gameBoard[1][2]=='_') {
				return new Point(1,2);
			}
			
		}
		else if ( (gameBoard[1][0]=='X') && (gameBoard[1][2]=='X')) {
			if ( gameBoard[1][1]=='_') {
				return new Point(1,1);
			}
			
		}
		else if ( (gameBoard[1][1]=='X') && (gameBoard[1][2]=='X')) {
			if ( gameBoard[1][0]=='_') {
				return new Point(1,0);
			}
			
		}
		
		// The bottom row permutations
		if ( (gameBoard[2][0]=='X') && (gameBoard[2][1]=='X')) {
			if ( gameBoard[2][2]=='_') {
				return new Point(2,2);
			}
			
		}
		else if ( (gameBoard[2][0]=='X') && (gameBoard[2][2]=='X')) {
			if ( gameBoard[2][1]=='_') {
				return new Point(2,1);
			}
			
		}
		else if ( (gameBoard[2][1]=='X') && (gameBoard[2][2]=='X')) {
			if ( gameBoard[2][0]=='_') {
				return new Point(2,0);
			}
			
		}
		
		// Vertical ––––––––––––––––––––
		// The left column permutations
		if ( (gameBoard[0][0]=='X') && (gameBoard[1][0]=='X')) {
			if ( gameBoard[2][0]=='_') {
				return new Point(2,0);
			}
			
		}
		else if ( (gameBoard[0][0]=='X') && (gameBoard[2][0]=='X')) {
			if ( gameBoard[1][0]=='_') {
				return new Point(1,0);
			}
			
		}
		else if ( (gameBoard[1][0]=='X') && (gameBoard[2][0]=='X')) {
			if ( gameBoard[0][0]=='_') {
				return new Point(0,0);
			}
			
		}
		
		// The middle column permutations
		if ( (gameBoard[0][1]=='X') && (gameBoard[1][1]=='X')) {
			if ( gameBoard[2][1]=='_') {
				return new Point(2,1);
			}
			
		}
		else if ( (gameBoard[0][1]=='X') && (gameBoard[2][1]=='X')) {
			if ( gameBoard[1][1]=='_') {
				return new Point(1,1);
			}
			
		}
		else if ( (gameBoard[1][1]=='X') && (gameBoard[2][1]=='X')) {
			if ( gameBoard[0][1]=='_') {
				return new Point(0,1);
			}
			
		}
		
		// The bottom column permutations
		if ( (gameBoard[0][2]=='X') && (gameBoard[1][2]=='X')) {
			if ( gameBoard[2][2]=='_') {
				return new Point(2,2);
			}
			
		}
		else if ( (gameBoard[0][2]=='X') && (gameBoard[2][2]=='X')) {
			if ( gameBoard[1][2]=='_') {
				return new Point(1,2);
			}
			
		}
		else if ( (gameBoard[1][2]=='X') && (gameBoard[2][2]=='X')) {
			if ( gameBoard[0][2]=='_') {
				return new Point(0,2);
			}
			
		}
		
		// Diagonal ––––––––––––––––––
		// The diagonal up permutations
		if ( (gameBoard[0][2]=='X') && (gameBoard[1][1]=='X')) {
			if ( gameBoard[2][0]=='_') {
				return new Point(2,0);
			}
			
		}
		else if ( (gameBoard[0][2]=='X') && (gameBoard[2][0]=='X')) {
			if ( gameBoard[1][1]=='_') {
				return new Point(1,1);
			}
			
		}
		else if ( (gameBoard[2][0]=='X') && (gameBoard[1][1]=='X')) {
			if ( gameBoard[0][2]=='_') {
				return new Point(0,2);
			}
			
		}
		
		
		// The diagonal down permutations
		if ( (gameBoard[0][0]=='X') && (gameBoard[1][1]=='X')) {
			if ( gameBoard[2][2]=='_') {
				return new Point(2,2);
			}
			
		}
		else if ( (gameBoard[0][0]=='X') && (gameBoard[2][2]=='X')) {
			if ( gameBoard[1][1]=='_') {
				return new Point(1,1);
			}
			
		}
		else if ( (gameBoard[2][2]=='X') && (gameBoard[1][1]=='X')) {
			if ( gameBoard[0][0]=='_') {
				return new Point(0,0);
			}
			
		}
		
		
		/*–––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––
		 * Finish by picking random
		 */
		
			
		Random random = new Random();
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