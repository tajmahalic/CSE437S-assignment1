package connectFour;

public class Board {

	Piece[][] board;
	int BOARDWIDTH;
	int BOARDHEIGHT;
	
	
	public Board(int width, int height) {
		
		BOARDWIDTH = width;
		BOARDHEIGHT = height;
		
		
		
		//Add 1 Height Row for visualizing piece placement
		this.board = new Piece[width][height+1];
		
		//Initializes Board Values to null
			for (int i = 0; i < board.length; i++) {
				for (int j = 0; i < board[0].length; j++) {
					board[i][j] = null;
				}
			}
		
	}
	
	//check to see if any colors make a line of four (can be diagonal)
	public boolean checkWin() {
		
		return true;
	}
	
	//just drop the piece into the board
	public void addPiece(int width, int height, int color) {
		
		Piece p = new Piece(color);
		board[width][height] = p;
	}
	
	public boolean checkIfColIsFull(int width) {
		
		for (int h = BOARDHEIGHT-1; h >= 0; h--) {
			
		}
		
		return true;
		
	}
	
	public boolean squareIsEmpty(int width, int height) {
		
		return board[width][height] == null;
	}
	
	public void printBoard() {
		
	}
	
	
}
