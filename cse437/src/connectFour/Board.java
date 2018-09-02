package connectFour;

import java.util.ArrayList;

public class Board {

	private Piece[][] board;
	private int BOARDWIDTH;
	private int BOARDHEIGHT;
	private Player currPlayer;

	
	public Board(int width, int height) {
		
		this.BOARDWIDTH = width;
		this.BOARDHEIGHT = height;
		this.currPlayer = null;
		
		
		//Add 1 Height Row for visualizing piece placement
		this.board = new Piece[width][height];
		
		Piece emptyPiece = new Piece(-1);
		
		//Initializes Board Values to emptyPiece
			for (int i = 0; i < board.length; i++) {
				for (int j = 0; j < board[0].length; j++) {
					board[i][j] = emptyPiece;
				}
			}
		
	}
	
	public boolean isDone() {
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
	public void addPiece(int width, int color) {
		
		//Piece p = new Piece(color);
		
		for (int i = 0; i < BOARDHEIGHT; i++) {
			if (squareIsEmpty(width, i)) {
				board[width][i] = new Piece(color);
				System.out.println("Added to " + width + " " + i);
				return;
			}
		}
	}
	
	public boolean checkIfColIsFull(int width) {
		return !squareIsEmpty(width, BOARDHEIGHT-1);	
	}
	
	public boolean squareIsEmpty(int width, int height) {
		
		Piece emptyPiece = new Piece(-1);
		
		return board[width][height].equals(emptyPiece);
	}
	
	public void printBoard() {
		
	}
	
	public int getBoardHeight() {
		return BOARDHEIGHT;
	}
	
	public int getBoardWidth() {
		return BOARDWIDTH;
	}

	public void setCurrentPlayer(Player player) {
		currPlayer = player;
	}	
	
	public Player getCurrentPlayer() {
		return currPlayer;
	}
	
}
