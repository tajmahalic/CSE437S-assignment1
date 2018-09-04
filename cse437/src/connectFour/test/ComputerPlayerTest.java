package connectFour.test;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.jupiter.api.Test;

import connectFour.Board;
import connectFour.ComputerPlayer;
import connectFour.Piece;
import connectFour.Player;

class ComputerPlayerTest {

	@Test
	void computerShouldPickPiece() {
		Board b = new Board(5, 5);
		Player computer = new ComputerPlayer("Computer", Color.RED, false);
		int colChosen = computer.pickPiece(b);
		Piece p = new Piece(Color.RED);
		assertEquals(b.getPieces()[colChosen][0], p);
	}
	
	@Test
	void computerShouldPickPieceSmartly() {
		Board a = new Board(5, 5);
		Player human = new Player("Tom", Color.BLUE);
		Player computer = new ComputerPlayer("Computer", Color.RED, true);
		Board b = new Board(a.getPieces(), computer, human);
		for (int i = 0; i < 3; i++) {
			b.addPiece(0, Color.BLUE);
		}
		computer.pickPiece(b);
		Piece p = new Piece(Color.RED);
		assertEquals(b.getPieces()[0][3], p);
	}

}
