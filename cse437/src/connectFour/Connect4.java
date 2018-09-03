package connectFour;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import java.awt.event.WindowEvent;



public class Connect4 {
	Game theGame;
	static JFrame frame = new JFrame("connect four");
	static JPanel panel = (JPanel) frame.getContentPane();
	static int col = 0;
	public Connect4(Game theGame)
	{
		this.theGame = theGame;
	}
	
	//Close connect4 frame when a new game is loaded
	public static void closeConnect4()
	{
		panel.removeAll();
		panel.removeAll();
		panel.repaint();
		panel.revalidate();
	}
	
	//Disable connect4 frame when another dialog is opens
	public static void disableConnect4()
	{
		Component[] component = panel.getComponents();

	    for(int i=0; i<component.length; i++)
	    {
	        if (component[i] instanceof JButton)
	        {
	            JButton button = (JButton)component[i];
	            button.setEnabled(false);
	        }

	    }
	}
	


	
	//fill connect4 frame when a game is loaded from a file
	public static void fillConnect4(String row, int col, Color color) {
		JButton button = new JButton("");
		button.setBackground(color);
		panel.setLayout(null);
		switch (row) { 
			case "0": 
				if(col>=0) {
					button.setBounds(0,481-(col)*80,100,80); 
					panel.add(button);
				}
				break;
	  		case "1":
	  			 if(col>=0) {
	  				System.out.println("IN ONNNNNEEEEEEE.....");
	  			 	button.setBounds(98,481-(col)*80,100,80); 
	  			 	panel.add(button);
	  			 }
	  			 break;
	  		case "2":    
	  			if(col>=0) {
	  				button.setBounds(195,481-(col)*80,100,80); 
	  				panel.add(button);
	  			}
	  			break;
	  		case "3": 
	  			if(col>=0) {
	  				button.setBounds(290,481-(col)*80,100,80); 
	  				panel.add(button);
	  		    }
	  			break;
	  		case "4": 
	  			if(col>=0) {
	  				button.setBounds(388,481-(col)*80,100,80); 
	  				panel.add(button);
	  			}
	  			break;
	  		case "5":
	  			if(col>=0) {
	  				button.setBounds(485,481-(col)*80,100,80); 
	  				panel.add(button);
	  			}
	  			break;
	  		case "6": 
	  			if(col>=0) {
	  				button.setBounds(582,481-(col)*80,100,80); 
	  				panel.add(button);
	  			}
	  			break;
	   }
		  	 panel.revalidate();
		     panel.repaint();
		
	}
	

	//Create contents of the window
	protected void createContents() {
		 int BOARDWIDTH = 7;
		 int BOARDHEIGHT = 7;
	     panel.setLayout((LayoutManager) new GridLayout(BOARDWIDTH, BOARDHEIGHT ));
	     JLabel[][] grids = new JLabel[BOARDWIDTH][BOARDHEIGHT];   
	     JButton[] buttons = new JButton[7];
	         
	     for (int i = 0; i <= BOARDWIDTH-1; i++) {
	          buttons[i] = new JButton("" + (i));
	          buttons[i].setActionCommand("" + i);
	          panel.add(buttons[i]);
	          buttons[i].addActionListener(new ButtonListener(panel,theGame));
	     }
	         
	     for (int column = 0; column < BOARDWIDTH; column++) {
	    	 for (int row = 0; row < BOARDWIDTH-1; row++) {
	    		 grids[row][column] = new JLabel();
	    		 grids[row][column].setHorizontalAlignment(SwingConstants.CENTER);
	    		 grids[row][column].setBorder((Border) new LineBorder(Color.black));
	    		 panel.add(grids[row][column]);
	         }
	      }

	        
	     frame.setContentPane(panel);
	     frame.setSize(700, 600);
	     frame.setVisible(true);
	     frame.setLocationRelativeTo(null);
	     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	     frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

	     frame.addWindowListener(new WindowAdapter() {
	    	 public void windowClosing(WindowEvent e) {
	    		 SaveGame dialog = new SaveGame(theGame,frame);
	    		 dialog.setVisible(true);
	    		 Connect4.disableConnect4();
	    	 }
	     });
	
	}
}


class ButtonListener implements ActionListener {
	JPanel panel;
	Game theGame;
	String compChoiceName;
	int col = 0;
	ButtonListener(JPanel panel, Game theGame) {
		this.panel = panel;
		this.theGame = theGame;
	}

	public void actionPerformed(ActionEvent e) {
		JButton c = (JButton)e.getSource();
		String choiceName = c.getText();
		int choice = Integer.parseInt(choiceName);
		
		//If choice is invalid
		if (theGame.gameBoard.checkIfColIsFull(choice)) {
			JOptionPane.showMessageDialog(panel, "That space is full! Choose another.");
			return;
		}
		
		theGame.players.get(0).choice = choice;
		theGame.runGame();
	   
		JButton button = new JButton("");
		button.setBackground(Color.BLUE);
		panel.setLayout(null);
		button.setBounds(0,396,100,77);
		switch (choiceName) {
			case "0": 
		    	 col = 6-theGame.players.get(0).col;
		    	 if(col>0) {
		    		 button.setBounds(0,481+(col-6)*80,100,80); 
		    		 panel.add(button);
		    	 }
		    	 break;
		  	case "1": 
		  		 col = 6-theGame.players.get(0).col;
		  		 if(col>0) {
		  			 button.setBounds(98,481+(col-6)*80,100,80); 
		  			 panel.add(button);
		  		 }
		  		 break;
		  	case "2": 
		  		 col = 6-theGame.players.get(0).col;
		  		 if(col>0) {
		  			 button.setBounds(195,481+(col-6)*80,100,80); 
		  			 panel.add(button);
		  		 }
		  		 break;
		  	case "3": 
		  		col = 6-theGame.players.get(0).col;
		  		if(col>0) {
		  			button.setBounds(290,481+(col-6)*80,100,80); 
		  			panel.add(button);
		  		}
		  		break;
		  	case "4": 
		  		col = 6-theGame.players.get(0).col;
		  		if(col>0) {
		  			button.setBounds(388,481+(col-6)*80,100,80); 
		  			panel.add(button);
		  		}
		  		break;
		  	case "5":
		  		col = 6-theGame.players.get(0).col;
		  		if(col>0) {
		  			button.setBounds(485,481+(col-6)*80,100,80); 
		  			panel.add(button);
		  		}
		  		break;
		  	case "6": 
		  		col = 6-theGame.players.get(0).col;
		  		if(col>0) {
		  			button.setBounds(582,481+(col-6)*80,100,80); 
		  			panel.add(button);
		  		}
		  		break;
		  }
		    panel.revalidate();
	        panel.repaint();	    
	  }
	}
