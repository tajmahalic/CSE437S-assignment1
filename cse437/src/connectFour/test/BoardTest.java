package connectFour.test;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.jupiter.api.Test;

import connectFour.Board;
import connectFour.ComputerPlayer;
import connectFour.Piece;
import connectFour.Player;

class BoardTest {

	@Test
	void newBoardShouldNotBeNull() {
		Board b = new Board(10, 10);
		assertNotNull(b, "Board is null.");
	}

	@Test
	void boardShouldBeWon() {
		Board a = new Board(5, 5);
		Player human = new Player("Tom", Color.BLUE);
		Player computer = new ComputerPlayer("Computer", Color.RED, false);
		Board b = new Board(a.getPieces(), human, computer);
		for (int i = 0; i < 4; i++) {
			b.addPiece(0, Color.BLUE);
		}
		assertTrue(b.checkWin());
	}
	
	@Test
	void boardShouldBeTied() {
		Board a = new Board(4, 4);
		Player human = new Player("Tom", Color.BLUE);
		Player computer = new ComputerPlayer("Computer", Color.RED, false);
		Board b = new Board(a.getPieces(), human, computer);
		for (int i = 0; i < b.getBoardWidth() / 2; i++) {
			for (int j = 0; j < b.getBoardHeight(); j++) {
				if (j % 2 == 0) {
					b.addPiece(i, Color.BLUE);
				} else {
					b.addPiece(i, Color.RED);
				}
			}
		}
		for (int i = b.getBoardWidth() / 2; i < b.getBoardWidth(); i++) {
			for (int j = 0; j < b.getBoardHeight(); j++) {
				if (j % 2 == 0) {
					b.addPiece(i, Color.RED);
				} else {
					b.addPiece(i, Color.BLUE);
				}
			}
		}
		assertTrue(b.checkTie());
	}

	@Test
	void pieceShouldBeAdded() {
		Board b = new Board(2, 2);
		int i = b.addPiece(0, Color.RED);
		Piece p = new Piece(Color.RED);
		assertEquals(b.getPieces()[0][i], p);
	}
	
	@Test
	void boardShouldBeFull() {
		Board b = new Board(2, 2);
		for (int i = 0; i < b.getBoardWidth(); i++) {
			for (int j = 0; j < b.getBoardHeight(); j++) {
				b.addPiece(i, Color.BLUE);
			}
		}
		assertTrue(b.boardIsFull());
	}
	
	@Test
	void colShouldBeFull() {
		Board b = new Board(4, 4);
		for (int i = 0; i < 4; i++) {
			b.addPiece(0, Color.BLUE);
		}
		assertTrue(b.checkIfColIsFull(0));
	}
	
	@Test
	void squareShouldBeEmpty() {
		Board b = new Board(1, 1);
		assertTrue(b.squareIsEmpty(0, 0));
	}
}
