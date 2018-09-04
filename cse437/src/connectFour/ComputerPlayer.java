package connectFour;

import java.awt.Color;
import java.util.ArrayList;


public class ComputerPlayer extends Player {
	int col;
	boolean isHard;

	/**
	 * Creates a new AI computer player on either easy or hard difficulty
	 * @param String name
	 * @param Color color 
	 * @param Boolean isHard - difficulty setting
	 */
	public ComputerPlayer(String name, Color color, boolean isHard) {
		super(name, color);
		this.isHard = isHard;
	}

	/**
	 * Algorithm for choosing piece on computer's turn
	 * If computer is on easy mode, then random choice
	 * If computer is on hard mode, then will win/block on 3-in-a-row
	 * @param Board b
	 * @return the choice that the computer made
	 */
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
