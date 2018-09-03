package connectFour;

import java.awt.Color;

public class Piece {

	private Color color;
	
	public Piece(Color color) {
		this.color = color;
	}
	
	public Piece(Piece p) {
		this.color = p.getColor();
	}
	
	public Piece() {
		this.color = Color.BLACK;
	}

	public Color getColor() {
		return color;
	}
	
	@Override
	public boolean equals(Object o) {
		
		if (!(o instanceof Piece))
			return false;
		
		Piece p = (Piece) o;
		
		return this.color == p.color;
		
	}
}
