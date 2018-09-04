package connectFour;

import java.awt.Color;

public class Player {

	protected String name;
	protected Color color;
	protected int choice;
	protected int col;
	
	/**
	 * Creates a new Player
	 * @param String name 
	 * @param Color color
	 */
	public Player(String name, Color color)
	{
		this.name = name;
		this.color = color;
	}
	
	/**
	 * Player chooses a location to place the next piece
	 * @param Board b - Current Game Board
	 * @return int - Location of player choice
	 */
	public int pickPiece(Board b) {		
		
		while (true) {
		
			if (choice < 0 || choice > b.getBoardWidth()) {
				System.out.println("Input must be between 0 and " + b.getBoardWidth() + "!");
				continue;
			}
			
			if (b.checkIfColIsFull(choice)) {
				System.out.println("That space is full!");
				continue;
				
			} else {
				col = b.addPiece(choice, color);
				break;
			}
		}
		
		System.out.println("Player " + name + " chose column " + choice + ".");
		
		return choice;
	}
	
	/**
	 * 
	 * @return player Color
	 */
	public Color getColor() {
		return color;
	}
	
	/**
	 * 
	 * @return player Name
	 */
	public String getName() {
		return name;
	}
	
}
