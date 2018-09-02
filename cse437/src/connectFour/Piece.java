package connectFour;

public class Piece {

	private int color;
	
	public Piece(int color) {
		this.color = color;
	}
	
	public Piece(Piece p) {
		this.color = p.getColor();
	}

	public int getColor() {
		return color;
	}
	
	//Method to see if two pieces are equal
	@Override
	public boolean equals(Object o) {
		
		if (!(o instanceof Piece))
			return false;
		
		Piece p = (Piece) o;
		
		return this.color == p.color;
		
	}
}
