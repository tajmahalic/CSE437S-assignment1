package connectFour;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class WinGame extends JDialog {

	private final JPanel contentPanel = new JPanel();
	public String winner;
	public String playerName;
	private Game theGame;
	
	/**
	 * Adds components to dialog
	 */
	public void addtoDialog()
	{
		JLabel winnerLabel = new JLabel(winner+" won");
		contentPanel.add(winnerLabel);
		winnerLabel.setBounds(150,25,200,77);
		winnerLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		JButton newGameButton = new JButton("PLAY A NEW GAME");
		newGameButton.setFont(new Font("Dialog", Font.BOLD, 15));
		newGameButton.setBounds(100,100,200,50);
		contentPanel.add(newGameButton);
		newGameButton.addActionListener(new aNewGameButtonListener(playerName, theGame));
		
		
		JButton quitButton = new JButton("QUIT");
		quitButton.setFont(new Font("Dialog", Font.BOLD, 15));
		quitButton.setBounds(150,160,100,50);
		contentPanel.add(quitButton);
		contentPanel.setVisible(true);		
		quitButton.addActionListener(new quitGameButtonListener());
	}
	
	/**
	 * Creates win game dialog box
	 * @param winner
	 * @param playerName
	 * @param theGame
	 */
	public WinGame(String winner, String playerName, Game theGame) {
		this.theGame = theGame;
		this.winner = winner;
		this.playerName = playerName;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(null);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			
		}
		addtoDialog();
	}


}

class aNewGameButtonListener implements ActionListener {
	String name;
	JPanel panel;
	boolean difficulty;
	
	aNewGameButtonListener(String name, Game game) {
		this.name = name;
		this.difficulty = game.getComputerDifficulty();
	}
  
  public void actionPerformed(ActionEvent e) { 
	  Connect4.closeConnect4();
	  Game theGame = new Game(name, difficulty);
	  theGame.newGame();
	  Connect4 connect4Board = new Connect4(theGame);
      connect4Board.createContents();
	  JButton button = (JButton) e.getSource();
      JDialog dialog = (JDialog) SwingUtilities.getRoot(button);
      dialog.dispose();
  }
}
