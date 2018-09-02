package connectFour;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class Player {

	protected String name;
	protected int color;
	
	public Player(String name, int color) {
		this.name = name;
		this.color = color;
	}
	
	//allows the user to use to left and right arrow keys to pick which column they want to
	//	place their piece in
	public int pickPiece(Board b) {
		
		
		b.setCurrentPlayer(this);
		
		Scanner scan = new Scanner(System.in);
		
		int choice;
		
		while (true) {
			System.out.print("Where would you like to move?: ");
			
			if (!scan.hasNextInt()) {
				String line = scan.nextLine();
				System.out.println(line);
				if (line.equals("save as")) {
					System.out.println("Please enter file name (.txt) to save to.");
					line = scan.nextLine();
					if (line.endsWith(".txt")) {
						try {
							Game.saveGame(line);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else {
						System.out.println("Invalid file name.");
					}
					continue;
				} else {
					System.out.println("Input must be a number!");
					scan.next();
					continue;
				}
			}
			
			choice = scan.nextInt();
			
			if (choice < 0 || choice > b.getBoardWidth()) {
				System.out.println("Input must be between 0 and " + b.getBoardWidth() + "!");
				continue;
			}
			
			if (b.checkIfColIsFull(choice)) {
				System.out.println("That space is full!");
				continue;
				
			} else {
				b.addPiece(choice, color);
				break;
			}
		}
		
		System.out.println("Player " + name + " chose column " + choice + ".");

		return choice;
	}
	
	public int getColor() {
		return color;
	}
	
	public String getName() {
		return name;
	}
	
}
