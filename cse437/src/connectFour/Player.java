package connectFour;

import java.awt.Color;

public class Player {

	protected String name;
	protected Color color;
	protected int choice;
	protected int col;
	
	public Player(String name, Color color)
	{
		this.name = name;
		this.color = color;
	}
	
	public Player(String name, Color color, int choice) {
		this.name = name;
		this.color = color;
		this.choice = choice;
	}

	public int pickPiece(Board b) {		
		b.setCurrentPlayer(this);
		
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
	
	public Color getColor() {
		return color;
	}
	
	public String getName() {
		return name;
	}
	
}
