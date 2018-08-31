package connectFour;

public class Piece {

	private int color;
	
	public Piece(int color) {
		this.color = color;
	}

	public int getColor() {
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
