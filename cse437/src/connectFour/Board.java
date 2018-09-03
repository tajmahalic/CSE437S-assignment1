package connectFour;

import java.awt.Color;

public class Board {

	private Piece[][] board;
	private int BOARDWIDTH;
	private int BOARDHEIGHT;
	private Player currPlayer;
	private Player otherPlayer;
	
	public Board(int width, int height) {
		
		this.BOARDWIDTH = width;
		this.BOARDHEIGHT = height;
		this.currPlayer = null;
		this.otherPlayer = null;
		
		
		
		//Add 1 Height Row for visualizing piece placement
		this.board = new Piece[width][height];
		
		Piece emptyPiece = new Piece();
		
		//Initializes Board Values to emptyPiece
			for (int i = 0; i < board.length; i++) {
				for (int j = 0; j < board[0].length; j++) {
					board[i][j] = emptyPiece;
				}
			}
		
	}
	
	//Makes board from piece array and current player p
	public Board(Piece[][] pieces, Player p, Player other) {
		this.BOARDWIDTH = pieces.length;
		this.BOARDHEIGHT = pieces[0].length;
		this.currPlayer = p;
		this.otherPlayer = other;
		
		this.board = new Piece[BOARDWIDTH][BOARDHEIGHT];
		
		for (int i = 0; i < BOARDWIDTH; i++) {
			for (int j = 0; j < BOARDHEIGHT; j++) {
				board[i][j] = new Piece(pieces[i][j]);
			}
		}
	}
	
	//Makes copy of board b
		public Board(Board b) {
			this(b.getPieces(), b.getCurrentPlayer(), b.getOtherPlayer());
		}
	
	//Makes a copy of the current board and returns the board after placing a piece at width
		public Board peekAtNextBoard(int width) {
			Board newBoard = new Board(this);
			newBoard.addPiece(width, currPlayer.getColor());
			return newBoard;
		}
	
	public boolean isDone() {
		System.out.println("IN ISDONE....  "+checkWin());
		return checkWin() || checkTie();
	}
	
	public boolean checkTie() {
		
		for (int i = 0; i < BOARDWIDTH; i++) {
			if (!checkIfColIsFull(i)) {
				return false;
			}
		}
		
		if (checkWin()) {
			return false;
		}
		return true;
	}
	
	//check to see if any colors make a line of four (can be diagonal)
	public boolean checkWin() {
		
		for (int w = 0; w < BOARDWIDTH; w++) {
			for (int h = 0; h < BOARDHEIGHT; h++) {
				if (board[w][h].getColor() == currPlayer.getColor()) {
					
					Piece currPiece = board[w][h];
				
					//Check Horizontal
					if (w+3 < BOARDWIDTH && board[w+1][h].equals(currPiece) 
										 && board[w+2][h].equals(currPiece) 
										 && board[w+3][h].equals(currPiece)) {
						return true;
					}
					
					if (h+3 < BOARDHEIGHT) {
						
						//Check Vertical
						if (board[w][h+1].equals(currPiece) 
								&& board[w][h+2].equals(currPiece) 
								&& board[w][h+3].equals(currPiece)) {
							return true;
						}
						
						//Check UpperRight Diagonal
						if (w+3 < BOARDWIDTH && board[w+1][h+1].equals(currPiece) 
								 && board[w+2][h+2].equals(currPiece) 
								 && board[w+3][h+3].equals(currPiece)) {
								return true;
						}
						
						//Check UpperLeft Diagonal
						if (w-3 >= 0 && board[w-1][h+1].equals(currPiece) 
								 && board[w-2][h+2].equals(currPiece) 
								 && board[w-3][h+3].equals(currPiece)) {
								return true;
						}
						
					}		
				}
			}
		}
		
		return false;
	}
	
	public boolean boardIsFull() {
		
		for (int i = 0; i < BOARDWIDTH; i++) {
			for (int j = 0; j < BOARDHEIGHT; j++) {
				if (squareIsEmpty(i,j)) {
					return false;
				}
			}
		}
		return true;
	}
	
	//just drop the piece into the board
	public int addPiece(int width, Color color) {
		
		for (int i = 0; i < BOARDHEIGHT; i++) {
			if (squareIsEmpty(width, i)) {
				board[width][i] = new Piece(color);
				return i;
			}
		}
		return -1;
	}
	
	public boolean checkIfColIsFull(int width) {
		return !squareIsEmpty(width, BOARDHEIGHT-1);	
	}
	
	public boolean squareIsEmpty(int width, int height) {
		
		Piece emptyPiece = new Piece();
		
		return board[width][height].equals(emptyPiece);
	}
	
	public String toString() {
		String output = "";
		String newLine = System.getProperty("line.separator");
		for (int h = BOARDHEIGHT -1; h >= 0; h--) {
			for (int w = 0; w < BOARDWIDTH; w++) {
				output += board[w][h].getColor().toString() + " ";
			}
			output += newLine;
		}
		output += newLine;
		
		return output;
	}
	
	public Piece[][] getPieces() {
		return board;
	}
	
	public int getBoardHeight() {
		return BOARDHEIGHT;
	}
	
	public int getBoardWidth() {
		return BOARDWIDTH;
	}
	
	public void setPlayers(Player curr, Player other) {
		currPlayer = curr;
		otherPlayer = other;
	}

	public void switchPlayers() {
		Player temp = currPlayer;
		currPlayer = otherPlayer;
		otherPlayer = temp;
	}	
	
	public Player getCurrentPlayer() {
		return currPlayer;
	}
	
	public Player getOtherPlayer() {
		return otherPlayer;
	}
	
}
