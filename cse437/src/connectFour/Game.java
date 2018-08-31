package connectFour;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {

	Board gameBoard;
	
	ArrayList<Player> players;
	
	int RED = 1;
	int BLACK = 2;
	
	int totalWins;
	int numTurns;
	
	public static void main(String[] args) {
		Game g = new Game();
		g.newGame();
		g.runGame();

	}
	
	public Game() {
		
		this.players = new ArrayList<Player>();
	}
	
	public void runGame() {
		
		gameBoard.setCurrentPlayer(players.get(0));
		
		while (!gameBoard.isDone()) {
			numTurns++;
			nextTurn();
		}
		
		Player winner = gameBoard.getCurrentPlayer();
		
		System.out.println(winner.getName() + " won the game!");
	}
	
	public void newGame() {
		
		//ArgsProcessor
		Scanner scan = new Scanner(System.in);
		
		System.out.print("What is your name?: ");
		String name = scan.nextLine();
		gameBoard = new Board(7,6);
		players.add(new Player(name, RED));
		players.add(new ComputerPlayer("Computer", BLACK));
		
		for (int i = 0; i < players.size(); i++) {
			System.out.println("Player " + (i+1) + " is " + players.get(i).getName());
		}
		
		
		totalWins = 0;
		numTurns = -1;
	}
	
	//if player wants to load a saved game, initialize board
	public boolean loadGame(String filename) {
		
		return true;
	}
	
	//alternate between computer and real player
	public boolean nextTurn() {
		
		System.out.println();
		Player currPlayer = players.get(numTurns % 2);
		
		System.out.println("It is " + currPlayer.getName() + "'s turn.");
		
		currPlayer.pickPiece(gameBoard);
		
		return true;
	}	
}
