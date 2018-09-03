package connectFour;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import java.util.List;

public class Game {

	Board gameBoard;
	
	ArrayList<Player> players;
	String name;
	static List<String> data = new ArrayList<String>();
	
	boolean computerIsHard = false;
	
	int totalWins;
	int numTurns;
	
	public Game(String name, boolean isHard) {
		
		this.computerIsHard = isHard;
		this.players = new ArrayList<Player>();
		this.name = name;
	}
	
	//Runs a Round of the game
	public void runGame() {
		
		for(int i = 0 ; i<2 ; i++) {
			numTurns++;
			nextTurn();
			
			//Checks for tie
			if (gameBoard.checkTie()) {
				System.out.println("GAME IS DONE");
				System.out.println("Tied Game!");
				TiedGame dialog = new TiedGame(players.get(0).name, this);
				dialog.setVisible(true);
				Connect4.disableConnect4();
				return;
				
			//Checks for win
			} else if (gameBoard.checkWin()) {
				System.out.println("GAME IS DONE");
				Player winner = gameBoard.getCurrentPlayer();
				System.out.println(winner.getName() + " won the game!");
				WinGame dialog = new WinGame(winner.getName(),players.get(0).name, this);
				dialog.setVisible(true);
				Connect4.disableConnect4();
				return;
			}
			gameBoard.switchPlayers();
		}
	}
	
	public void newGame() {

		gameBoard = new Board(7,6);
		players.add(new Player(name, Color.BLUE));
		players.add(new ComputerPlayer("Computer", Color.RED, computerIsHard));
		
		gameBoard.setPlayers(players.get(0), players.get(1));
		
		for (int i = 0; i < players.size(); i++) {
			System.out.println("Player " + (i+1) + " is " + players.get(i).getName());
		}
		
		
		totalWins = 0;
		numTurns = -1;
		data.add(players.get(0).getName());
		data.add(players.get(1).getName());
		data.add(String.valueOf(computerIsHard));
	}
	
	
	//if player wants to load a saved game, initialize board
	public boolean loadGame(File file){
		
		try (Stream<String> stream = Files.lines(Paths.get(file.getPath()))) {
			data = Arrays.asList((String[]) stream.toArray(String[]::new));
			data = new ArrayList<String>(data);
			gameBoard = new Board(7,6);
			players.add(new Player(data.get(0), Color.BLUE));
			
			players.add(new ComputerPlayer(data.get(1), Color.RED, Boolean.parseBoolean(data.get(2))));
			for (int i = 3; i < data.size(); i++) {
				if (i % 2 == 1) {
				   int col = gameBoard.addPiece(Integer.parseInt(data.get(i)), Color.BLUE);
				   Connect4.fillConnect4(data.get(i),col,Color.BLUE);
				} else {
				   int col = gameBoard.addPiece(Integer.parseInt(data.get(i)), Color.RED);
				   Connect4.fillConnect4(data.get(i),col, Color.RED);
				}
			}
			totalWins = 0;
			numTurns = data.size() - 4;
			
			if (numTurns % 2 == 0) {
				gameBoard.setPlayers(players.get(0), players.get(1));
			} else {
				gameBoard.setPlayers(players.get(1), players.get(0));
			}			
			
			System.out.println("Game Loaded");
			System.out.println(gameBoard);
			
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("No such file. Starting new game...");
			return false;
		}
	}
	
	
	
	//saves game file
	public void saveGame(File file) throws IOException {
		Path saveFile = Paths.get(file.getPath());
		System.out.println();
		System.out.println();
		System.out.println("LIST STRING IS....");
		String listString = "";

		for (String s : data)
		{
		    listString += s + "\t";
		}

		System.out.println(listString);
		Files.write(saveFile, data, Charset.forName("UTF-8"), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
	}
	
	//alternate between computer and real player
	public boolean nextTurn() {
		
		System.out.println();
		Player currPlayer = players.get(numTurns % 2);
		
		System.out.println("It is " + currPlayer.getName() + "'s turn.");
		data.add(Integer.toString(currPlayer.pickPiece(gameBoard)));
		System.out.println(gameBoard);
		
		
		return true;
	}	
	
	public boolean getComputerDifficulty() {
		return computerIsHard;
	}
}
