package connectFour;

import java.util.ArrayList;

public class ComputerPlayer extends Player {

	public ComputerPlayer(String name, int color) {
		super(name, color);
	}
	
	//picks a random number between 0 and the width of the playing board, then the piece
	//	is placed in that column
	//Note if the computer can win in the next move, it will pick that move
	public int pickPiece(Board b) {
		
		ArrayList<Integer> possiblePlays = new ArrayList<Integer>();
		
		//First, check for all possible moves and add them to arraylist
		for (int i = 0; i < b.getBoardWidth(); i++) {
			if (!b.checkIfColIsFull(i)) {
				possiblePlays.add(i);
			}
		}
		
		//make a random choice for the move
		int choice = possiblePlays.get((int) (Math.random()*possiblePlays.size()));
		
		//Checks to see if computer can win in next move. If so chooses that move
		for (int i : possiblePlays) {
			if (b.peekAtNextBoard(i, color).checkWin()) {
				choice = i;
				break;
			}
		}
		b.addPiece(choice, color);
		
		System.out.println("Computer Player " + name + " chose column " + choice + ".");
		
		return choice;
		
	
	}
}
