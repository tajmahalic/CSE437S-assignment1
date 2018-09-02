package connectFour;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class Game {

	Board gameBoard;
	
	ArrayList<Player> players;
	static List<String> data = new ArrayList<String>();
	
	
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
		
		Scanner scan = new Scanner(System.in);
		System.out.print("Would you like to load a saved game? (y or n)");
		
		String ans = scan.nextLine();
		while (!ans.equals("y") && !ans.equals("n")) {
			System.out.print("Would you like to load a saved game? (y or n)");
			ans = scan.nextLine();
		}
		if (ans.equals("y")) {
			System.out.println("Please enter filename of saved game.");
			ans = scan.nextLine();
			loadGame(ans);

			return;
		}
		
		System.out.print("What is your name?: ");
		String name = scan.nextLine();
		gameBoard = new Board(7,6);
		players.add(new Player(name, RED));
		players.add(new ComputerPlayer("Computer", BLACK));
		data.add(this.players.get(0).name);
		data.add(this.players.get(1).name);
		for (int i = 0; i < players.size(); i++) {
			System.out.println("Player " + (i+1) + " is " + players.get(i).getName());
		}
		
		
		totalWins = 0;
		numTurns = -1;

	}
	
	//if player wants to load a saved game, initialize board
	public boolean loadGame(String filename) {
		try (Stream<String> stream = Files.lines(Paths.get(filename))) {
			data = Arrays.asList((String[]) stream.toArray(String[]::new));
			data = new ArrayList<String>(data);
			gameBoard = new Board(7,6);
			players.add(new Player(data.get(0), RED));
			players.add(new ComputerPlayer(data.get(1), BLACK));
			for (int i = 2; i < data.size(); i++) {
				if (i % 2 == 0) {
					gameBoard.addPiece(Integer.parseInt(data.get(i)), RED);
				} else {
					gameBoard.addPiece(Integer.parseInt(data.get(i)), BLACK);
				}
			}
			totalWins = 0;
			numTurns = data.size() - 3;
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("No such file. Starting new game...");
			return false;
		}
		
	}
	
	//alternate between computer and real player
	public boolean nextTurn() {

		Player currPlayer = players.get(numTurns % 2);
		
		System.out.println("It is " + currPlayer.getName() + "'s turn.");
		
		data.add(Integer.toString(currPlayer.pickPiece(gameBoard)));
		
		return true;
	}	
	
	public static void saveGame(String filename) throws IOException {
		Path saveFile = Paths.get(filename);
		Files.write(saveFile, data, Charset.forName("UTF-8"), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
	}
}
