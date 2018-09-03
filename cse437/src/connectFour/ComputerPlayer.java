package connectFour;

import java.awt.Color;
import java.util.ArrayList;

public class ComputerPlayer extends Player {
	int col;
	boolean isHard;

	public ComputerPlayer(String name, Color color, boolean isHard) {
		super(name, color);
		this.isHard = isHard;
	}

	// picks a random number between 0 and the width of the playing board, then
	// the piece
	// is placed in that column
	public int pickPiece(Board b) {

		ArrayList<Integer> possiblePlays = new ArrayList<Integer>();


		for (int i = 0; i < b.getBoardWidth(); i++) {
			if (!b.checkIfColIsFull(i)) {
				possiblePlays.add(i);
			}
		}
			
		if (isHard) {
			// First checks to see if computer can win in next move. If so
			// chooses that move
			for (int i : possiblePlays) {
				if (b.peekAtNextBoard(i).checkWin()) {
					choice = i;
					col = b.addPiece(choice, color);
					Connect4.fillConnect4(Integer.toString(choice), col, color);
					return choice;

				}
			}

			// Then checks to see if player can win in next move. If so, blocks
			// player
			for (int i : possiblePlays) {
				Board nextBoard = b.peekAtNextBoard(i);
				nextBoard.switchPlayers();
				for (int j : possiblePlays) {
					if (nextBoard.peekAtNextBoard(j).checkWin()) {
						choice = j;
						col = b.addPiece(choice, color);
						Connect4.fillConnect4(Integer.toString(choice), col, color);
						return choice;
					}
				}
			}
		}

		choice = possiblePlays.get((int) (Math.random() * possiblePlays.size()));
		System.out.println("Choice is " + choice);
		col = b.addPiece(choice, color);

		Connect4.fillConnect4(Integer.toString(choice), col, Color.RED);

		return choice;

	}
}
