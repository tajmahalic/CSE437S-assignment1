package connectFour;

import java.awt.Color;

public class Piece {

	private Color color;
	
	/**
	 * Creates a new piece
	 * @param piece color
	 */
	public Piece(Color color) {
		this.color = color;
	}
	
	/**
	 * Copy Constructor for new Piece
	 * @param p
	 */
	public Piece(Piece p) {
		this.color = p.getColor();
	}
	
	/**
	 * Default Constructor for Piece
	 */
	public Piece() {
		this.color = Color.BLACK;
	}

	/**
	 * 
	 * @return piece Color
	 */
	public Color getColor() {
		return color;
	}
	
	/**
	 * Method for comparing two pieces
	 */
	@Override
	public boolean equals(Object o) {
		
		if (!(o instanceof Piece))
			return false;
		
		Piece p = (Piece) o;
		
		return this.color == p.color;
		
	}
}
