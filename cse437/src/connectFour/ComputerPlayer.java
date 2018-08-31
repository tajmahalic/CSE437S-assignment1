package connectFour;

import java.util.ArrayList;

public class ComputerPlayer extends Player {

	public ComputerPlayer(String name, int color) {
		super(name, color);
	}
	
	//picks a random number between 0 and the width of the playing board, then the piece
	//	is placed in that column
	public int pickPiece(Board b) {
		
		ArrayList<Integer> possiblePlays = new ArrayList<Integer>();
		
		for (int i = 0; i < b.getBoardWidth(); i++) {
			if (!b.checkIfColIsFull(i)) {
				possiblePlays.add(i);
			}
		}
		
		int randomChoice = possiblePlays.get((int) (Math.random()*possiblePlays.size()));
		b.addPiece(randomChoice, color);
		
		System.out.println("Computer Player " + name + " chose column " + randomChoice + ".");
		
		return randomChoice;
		
	
	}
}
