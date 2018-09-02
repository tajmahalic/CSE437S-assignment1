package connectFour;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {

	Board gameBoard;
	
	ArrayList<Player> players;
	
	//Colors for Players
	int RED = 1;
	int BLACK = 2;
	
	int totalWins;
	int numTurns;
	
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		
		Game g = new Game();
		g.newGame();
		
		//TODO: Make option to choose between new game and load game
		//g.loadGame();
		
		g.runGame();

	}
	
	public Game() {
		
		this.players = new ArrayList<Player>();
	}
	
	public void runGame() throws FileNotFoundException, UnsupportedEncodingException {
		
		//Current Player is the first player added
		gameBoard.setCurrentPlayer(players.get(0));
		
		while (!gameBoard.isDone()) {
			numTurns++;
			nextTurn();
			saveGame();
		}
		
		
		if (gameBoard.checkTie()) {
			System.out.println("Tied Game! No Winners!");
			return;
		}
		
		//else if winner
		Player winner = gameBoard.getCurrentPlayer();
		
		System.out.println(winner.getName() + " won the game!");
		return;
	}
	
	public void newGame() {
		
		//ArgsProcessor
		Scanner scan = new Scanner(System.in);
		
		System.out.print("What is your name?: ");
		String name = scan.nextLine();
		
		gameBoard = new Board(7,6);
		
		//Human Player
		players.add(new Player(name, RED));
		
		//Computer Player
		//TODO: Maybe add option for two human players
		players.add(new ComputerPlayer("Computer", BLACK));
		
		for (int i = 0; i < players.size(); i++) {
			System.out.println("Player " + (i+1) + " is " + players.get(i).getName());
		}
		
		
		totalWins = 0;
		numTurns = -1;
	}
	
	//if player wants to load a saved game, initialize board
	public void loadGame(String filename) throws FileNotFoundException {
		
		File file = new File(filename);
		
		Scanner scan = new Scanner(file);		
		
		int width = scan.nextInt();
		int height = scan.nextInt();
		
		Piece[][] pieces = new Piece[width][height];
		
		int heightCount = height -1;
		
		//go through each row
		while (heightCount >= 0) {
			String row = scan.nextLine();
			
			String[] nums = row.split(" ");
			
			//and fill board with ints
			for (int i = 0; i < nums.length; i++) {
				pieces[i][heightCount] = new Piece(Integer.parseInt(nums[i]));
			}
			heightCount--;
		}
		
		//Read in player names
		String player1Name = scan.next();
		String player2Name = scan.next();
		
		players.add(new Player(player1Name, RED));
		players.add(new Player(player2Name, BLACK));
		
		int currPlayer = scan.nextInt();
		numTurns = scan.nextInt();
		
		gameBoard = new Board(pieces, players.get(currPlayer));
	}
	
	//saves game file (Board, player names, current player, and num turns
	public void saveGame() throws FileNotFoundException, UnsupportedEncodingException {
		
		//filename for saved file
		PrintWriter writer = new PrintWriter("connect-four-save.txt", "UTF-8");
		String s = gameBoard.toString();
		System.out.println(s);
		writer.println(gameBoard);
		writer.println(players.get(0).getName());
		writer.println(players.get(1).getName());
		writer.println(players.indexOf(gameBoard.getCurrentPlayer()));
		writer.println(numTurns);
		
		writer.close();
	}

	
	//alternate between computer and real player
	public boolean nextTurn() {
		
		System.out.println();
		Player currPlayer = players.get(numTurns % 2);
		
		System.out.println("It is " + currPlayer.getName() + "'s turn.");
		
		gameBoard.setCurrentPlayer(currPlayer);
		currPlayer.pickPiece(gameBoard);
		System.out.println(gameBoard);
		
		return true;
	}	
	
	public int getNumTurns() {
		return numTurns;
	}
}
