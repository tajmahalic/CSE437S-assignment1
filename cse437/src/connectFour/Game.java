package connectFour;

public class Game {

	Board gameBoard;
	Player human;
	Player AI;
	
	boolean isPaused;
	
	int totalWins;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public Game() {
		
	}
	
	public void newGame() {
		
		//ArgsProcessor
		
		String name = "temp";
		gameBoard = new Board(7,6);
		human = new Player(name);
		AI = new ComputerPlayer("Computer");
		
		totalWins = 0;
	}
	
	//if player wants to load a saved game, initialize board
	public boolean loadGame(String filename) {
		
		return true;
	}
	
	//alternate between computer and real player
	public boolean nextTurn() {
		
		return true;
	}
	
	//call addPiece in the Board class
	public void addPieceToBoard() {
		
	}
	
}
