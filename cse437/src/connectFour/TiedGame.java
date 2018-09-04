package connectFour;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Font;

@SuppressWarnings("serial")
public class TiedGame extends JDialog {

	private final JPanel contentPanel = new JPanel();
	public String winner;
	public String playerName;
	private Game theGame;
	
	/**
	 * Add components to dialog
	 */
	public void addtoDialog()
	{
		JLabel winnerLabel = new JLabel("It's a tied game!");
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
	 * Create dialog
	 * @param playerName
	 * @param theGame
	 */
	public TiedGame(String playerName, Game theGame) {
		this.theGame = theGame;
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
