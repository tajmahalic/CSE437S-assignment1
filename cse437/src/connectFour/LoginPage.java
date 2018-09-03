package connectFour;


import org.eclipse.swt.widgets.Shell;

import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Font;

public class LoginPage {

	protected Shell shell;

	/**
	 * Create contents of the window.
	 */
	protected static void createContents() {	
		JFrame frame = new JFrame("Login Page");
		JPanel panel = (JPanel) frame.getContentPane();
		panel.setLayout(null);
		
		frame.setContentPane(panel);
        frame.setSize(700, 600);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	    
        JLabel player = new JLabel("Name");
		panel.add(player);
		player.setBounds(100,130,100,77);
		player.setFont(new Font("Dialog", Font.BOLD, 20));
		
		TextField text = new TextField(20);
		panel.add(text);
		text.setBounds(200,150,250,40);
		text.setFont(new Font("Dialog", Font.PLAIN, 20));
		
		JButton newGameButton = new JButton("New Game");
		newGameButton.addActionListener(new newGameButtonListener(text,frame)); 
		newGameButton.setFont(new Font("Dialog", Font.BOLD, 20));
		newGameButton.setBounds(220,220,200,50);
		panel.add(newGameButton);
		
		
		JButton loadGameButton = new JButton("Load Game");
		loadGameButton.addActionListener(new loadGameButtonListener(text,frame));
		loadGameButton.setFont(new Font("Dialog", Font.BOLD, 20));
		loadGameButton.setBounds(220,280,200,50);
		panel.add(loadGameButton);
		
		panel.revalidate();
	    panel.repaint();

	}
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			createContents();			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

class newGameButtonListener implements ActionListener {
	JFrame frame;
	TextField text;
	String name;
	int[] rows = new int[7];
	newGameButtonListener(TextField text, JFrame frame) {
		 this.text = text;
		 this.frame = frame;
	}
  
	public void actionPerformed(ActionEvent e) {  
		name = text.getText();
		Game theGame = new Game(name);
		theGame.newGame();
		Connect4 connect4Board = new Connect4(theGame);
		connect4Board.createContents();
		frame.dispose();
  }
}

class loadGameButtonListener implements ActionListener {
	JFrame frame;
	TextField text;
	String name;
	loadGameButtonListener(TextField text, JFrame frame) {
		this.text = text;
		this.frame = frame;
	}
  
	public void actionPerformed(ActionEvent e) {  
	  	name = text.getText();
		JFileChooser fileChooser = new JFileChooser();;
	    int choice = fileChooser.showOpenDialog(null);
	    if(choice == JFileChooser.APPROVE_OPTION) {	
	    	File file = fileChooser.getSelectedFile();
	    	Game theGame = new Game(name);
	    	Connect4 connect4Board = new Connect4(theGame);
	    	connect4Board.createContents();
	    	theGame.loadGame(file);
	    	frame.dispose(); 
	    }
  
  }
}


