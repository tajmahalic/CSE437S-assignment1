package connectFour;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class SaveGame extends JDialog {

	private final JPanel contentPanel = new JPanel();
	Game theGame;
	JFrame frame;
	
	/**
	 * Add components to dialog
	 */
	public void addtoDialog()
	{
		JLabel saveLabel = new JLabel("Do you want to save the game?");
		contentPanel.add(saveLabel);
		saveLabel.setBounds(80,25,300,77);
		saveLabel.setFont(new Font("Dialog", Font.BOLD, 17));
		JButton saveButton = new JButton("SAVE");
		saveButton.setFont(new Font("Dialog", Font.BOLD, 15));
		saveButton.setBounds(150,100,100,50);
		contentPanel.add(saveButton);
		saveButton.addActionListener(new saveGameButtonListener(theGame));
		
		
		JButton quitButton = new JButton("QUIT");
		quitButton.setFont(new Font("Dialog", Font.BOLD, 15));
		quitButton.setBounds(150,160,100,50);
		contentPanel.add(quitButton);
		contentPanel.setVisible(true);
		quitButton.addActionListener(new quitGameButtonListener());
		
	}

	/**
	 * Create the dialog
	 * @param theGame
	 * @param frame
	 */
	public SaveGame(Game theGame, JFrame frame) {
		this.frame = frame;
		this.theGame = theGame;
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

class saveGameButtonListener implements ActionListener {
	
	Game theGame;
	
	saveGameButtonListener(Game theGame) {
		this.theGame = theGame;
  }
  
  public void actionPerformed(ActionEvent e) {  
	  	JFileChooser fileChooser = new JFileChooser();
	    int choice = fileChooser.showSaveDialog(null);
	    if(choice == JFileChooser.APPROVE_OPTION) {
	       File file = fileChooser.getSelectedFile();
	       try {
			theGame.saveGame(file);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
	    }
	    System.exit(0);
  }
}

class quitGameButtonListener implements ActionListener {
	
	
	quitGameButtonListener() {
	}
  
  public void actionPerformed(ActionEvent e) {  
	  System.exit(0);
	  
  }

}
