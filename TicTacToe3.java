package wia1002;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class TicTacToe3 implements ActionListener{
	Random random = new Random();
	JFrame frame = new JFrame();
	JPanel title_panel = new JPanel();
	JPanel button_panel = new JPanel();
	JPanel button_panel2 = new JPanel();
	JLabel textfield = new JLabel();
	
        JPanel rpanel = new JPanel();
        JButton resButton = new JButton();

	JButton[] modeButtons =new JButton[3];
	
	JButton[] buttons = new JButton[25];
        JButton undoButton = new JButton();
        
       
        
        Stack<Integer> stack = new Stack<>();
        
	boolean player_turn;
	boolean finish=false;
	boolean move=true;
	
	int depth;
 
    private static void createAndShowGUI() {
        // Create the main frame
        JFrame frame = new JFrame("Tic-Tac-Toe 5x5 Rules");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(650, 400);
        frame.getContentPane().setBackground(new Color(255, 239, 213));
        frame.setLayout(new BorderLayout());
	//frame.setVisible(true);
        
        // Create a label to display the rules
        JLabel titleLabel = new JLabel("<html>A regular game of Tic-Tac-Toe is played on a 5x5 square.<br></html><br><br><br>");
        titleLabel.setBounds(10,15,650,500);
        titleLabel.setBackground(new Color(5,255,0));
        titleLabel.setForeground(new Color(25,25,255));
        titleLabel.setFont(new Font("TimesRoman Bold",Font.BOLD,35));
        titleLabel.setHorizontalAlignment(SwingConstants.LEFT);
        titleLabel.setVerticalAlignment(SwingConstants.TOP);
        
        JLabel rulesLabel = new JLabel("<html><br>1. Players take turns placing shapes, either a cross (X) or a nought (O),on an empty cell.<br><br>"
                + "2. The first player to place 3 of their shapein a horizontal, vertical, or diagonal row wins the game.</html>");
        rulesLabel.setBounds(30,0,200,500);
        
        rulesLabel.setFont(new Font("Ink Free",Font.BOLD,25));
        rulesLabel.setHorizontalAlignment(SwingConstants.LEFT);
        rulesLabel.setVerticalAlignment(SwingConstants.CENTER);
        
        // Create a panel to hold the button
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
    
        // Create the button
        JButton proceedButton = new JButton("Start Games!!");
        proceedButton.setBounds(20,20,20,20);
        proceedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Call the method to create and show the next GUI
               TicTacToe3 ttt = new TicTacToe3();
                
                // Close the current GUI
                frame.dispose();
            }
        });
    
        // Add the button to the button panel
        buttonPanel.add(proceedButton);

      
        // Add the label to the frame
        frame.getContentPane().add(titleLabel);
        
        // Add the label to the frame
        frame.getContentPane().add(rulesLabel);
        
       frame.add(buttonPanel, BorderLayout.SOUTH);
        
        // Center the frame on the screen
        frame.setLocationRelativeTo(null);
        
        // Display the frame
        frame.setVisible(true);
        
        
   
        
    }     
        
        
    TicTacToe3(){
    	//set up frame for choosing mode
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800,800);
		frame.getContentPane().setBackground(new Color(50,50,50));
		frame.setLayout(new BorderLayout());
		frame.setVisible(true);
		
		//set up textfield and title panel
		textfield.setBackground(new Color(25,25,25));
		textfield.setForeground(new Color(25,255,0));
		textfield.setFont(new Font("Ink Free",Font.BOLD,75));
		textfield.setHorizontalAlignment(JLabel.CENTER);
		textfield.setText("Tic-Tac-Toe 5X5");
		textfield.setOpaque(true);
		
		title_panel.setLayout(new BorderLayout());
		title_panel.setBounds(0,0,800,100);
                
                /* For the reset Button */
                rpanel.setLayout(new BorderLayout());
                resButton.setText("Reset");
                resButton.setSize(100, 50);
                resButton.addActionListener(this);
                
                title_panel.add(textfield);
                rpanel.add(resButton);
                frame.add(title_panel, BorderLayout.NORTH);
                frame.add(button_panel, BorderLayout.CENTER);
                frame.add(rpanel, BorderLayout.SOUTH);
                frame.setLocationRelativeTo(null);
		
		// set up button panel and button for mode
		button_panel.setLayout(new GridLayout(3,1));
		
		for(int i=0;i<3;i++) {
			modeButtons[i] = new JButton();
			button_panel.add(modeButtons[i]);
			modeButtons[i].setFont(new Font("MV Boli",Font.BOLD,120));
			modeButtons[i].setFocusable(false);
			
		}
		modeButtons[0].setText("Easy");
		modeButtons[1].setText("Normal");
		modeButtons[2].setText("Hard");
		
		// complete the frame
		textfield.setText("Tic-Tac-Toe 5X5");
		title_panel.add(textfield);
		frame.add(title_panel,BorderLayout.NORTH);
		frame.add(button_panel);
		
		chooseMode();
		
		
    }
    public void game(int depth){
    	gameScreen();
        firstTurn();
       if(!player_turn) {
    	   AiTurn(depth);
       }
    	
    	 
    	
    }
    public void gameScreen() {
		frame.remove(button_panel);
	    button_panel2.setLayout(new GridLayout(5,5));	
	  for(int i=0;i<25;i++) {
		   buttons[i] = new JButton();
		   button_panel2.add(buttons[i]);
		   buttons[i].setFont(new Font("MV Boli",Font.BOLD,120));
		   buttons[i].setFocusable(false);
		   buttons[i].addActionListener(this);
	  }
	  textfield.setText("Tic-Tac-Toe 5x5");
	  
	  frame.add(button_panel2);
	  
	}
    
 public void chooseMode() {
		modeButtons[0].addActionListener(
			new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			    	depth=1;
			    	game(depth);
			    }
			}
		);
		modeButtons[1].addActionListener(
				new ActionListener() {
				    public void actionPerformed(ActionEvent e) {	
				        depth=3;
				    	game(depth);
				    }
				}
			);
		modeButtons[2].addActionListener(
				new ActionListener() {
				    public void actionPerformed(ActionEvent e) {	
				    	depth=5;
				    	game(depth);
				    }
				}
			);
                disableButtons();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
                enableButtons();
		textfield.setText("Choose your mode");
		
		
 }
 public void actionPerformed(ActionEvent e) {
   
     if (e.getSource() == resButton) {
        resetGame();
    } else if (player_turn) {
        for (int i = 0; i < 25; i++) {
            if (e.getSource() == buttons[i]) {
                if (buttons[i].getText().equals("")) {
                    buttons[i].setText("O");
                    buttons[i].setForeground(new Color(0, 0, 255));
                    player_turn = false;
                    textfield.setText("Pc turn");
                    move = true;
                    win(checkWin());
                    if (!finish) {
                        AiTurn(depth);
                    }
                }
            }
        }
    }
}
 
  public void resetGame() {
    frame.remove(button_panel2);
    button_panel2 = new JPanel();
    button_panel2.setLayout(new GridLayout(5, 5));

    for (int i = 0; i < 25; i++) {
        buttons[i] = new JButton();
        button_panel2.add(buttons[i]);
        buttons[i].setFont(new Font("MV Boli", Font.BOLD, 120));
        buttons[i].setFocusable(false);
        buttons[i].addActionListener(this);
    }

    player_turn = random.nextInt(2) == 0;
    if (player_turn) {
        textfield.setText("Your turn");
    } else {
        textfield.setText("PC turn");
        AiTurn(depth);
    }

    finish = false;
    undoButton.setEnabled(true); // Disable the "Undo" button

    frame.add(button_panel2);
    frame.revalidate();
    frame.repaint();
}
 
  
public void AiTurn(int depth) {
    // AI turn
    move = false;
    int bestscore = Integer.MIN_VALUE;
    int index = 0;

    for (int i = 0; i < 25; i++) {
        if (buttons[i].getText().equals("")) {
            buttons[i].setText("X");
            int score = minimax(depth - 1, false);
            buttons[i].setText("");

            if (score > bestscore) {
                bestscore = score;
                index = i;
            }
        }
    }

    buttons[index].setForeground(new Color(255, 0, 0));
    buttons[index].setText("X");
    player_turn = true;
    textfield.setText("Your turn");
    move = true;
    win(checkWin());
}
  
  

public int minimax(int depth, boolean isMaximizing) {
    move = false;

    int result = checkWin();
    if (result != 0) {
        return result; // Reverse the sign for AI wins
    }

    if (isMaximizing) {
        int bestscore = Integer.MIN_VALUE;
        for (int i = 0; i < 25; i++) {
            if (buttons[i].getText().equals("")) {
                if (depth != 0) {
                    buttons[i].setText("O");
                    int score = minimax(depth - 1, true);
                    buttons[i].setText("");
                    bestscore = Math.max(score, bestscore);
                } else {
                    bestscore = Math.max(0, bestscore);
                }
            }
        }
        return bestscore;
    }

     else {
                int bestscore = Integer.MAX_VALUE;
        for (int i = 0; i < 25; i++) { 
            if (buttons[i].getText().equals("")) {
                if (depth != 0) {
                    buttons[i].setText("X");
                    int score = minimax(depth - 1, false);
                    buttons[i].setText("");
                    bestscore = Math.min(score, bestscore);
                } else {
                    bestscore = Math.min(0, bestscore);
                }
            }
        }
        return bestscore;
}
}



public void disableButtons() {
    for (int i = 0; i < 3; i++) {
        modeButtons[i].setEnabled(false);
    }
}

public void enableButtons() {
    for (int i = 0; i < 3; i++) {
        modeButtons[i].setEnabled(true);
    }
}

    
    public void firstTurn() {
		
		if(random.nextInt(2)==0) {
			player_turn=true;
			textfield.setText("Your turn");
		}
		else {
			player_turn=false;
			textfield.setText("PC turn");
		}
	}
    
  
	public void win(int j) {
		if(j==1) {
			for(int i=0;i<25;i++) {
				buttons[i].setEnabled(false);
			}
			textfield.setText("You lose");
			finish=true;
		}
		else if(j==-1) {
			for(int i=0;i<25;i++) {
				buttons[i].setEnabled(false);
			}
			textfield.setText("You wins");
			finish=true;
		}else if(j==2) {
			for(int i=0;i<25;i++) {
				buttons[i].setEnabled(false);
			}
			textfield.setText("The Game is Draw");
			finish=true;
		}
	}

        
        public int checkWin() {
		//check X win conditions
                //Horizontal Check
                for(int i=0;i<25;i+=5){
		if(
				(buttons[i+0].getText()=="X") &&
				(buttons[i+1].getText()=="X") &&
				(buttons[i+2].getText()=="X")
				) {
			return 1;
		}
                if(
				(buttons[i+1].getText()=="X") &&
				(buttons[i+2].getText()=="X") &&
				(buttons[i+3].getText()=="X")
				) {
			return 1;
		}
		if(
				(buttons[i+2].getText()=="X") &&
				(buttons[i+3].getText()=="X") &&
				(buttons[i+4].getText()=="X")
				) {
			return 1;
		}
                }//Horizontal Check done
                //Vertical Check
                for(int i=0;i<5;i++){
                  if(
				(buttons[i+0].getText()=="X") &&
				(buttons[i+5].getText()=="X") &&
				(buttons[i+10].getText()=="X")
				) {
			return 1;
		} 
                  if(
				(buttons[i+0].getText()=="X") &&
				(buttons[i+5].getText()=="X") &&
				(buttons[i+10].getText()=="X")
				) {
			return 1;
		} 
                  if(
				(buttons[i+10].getText()=="X") &&
				(buttons[i+15].getText()=="X") &&
				(buttons[i+20].getText()=="X")
				) {
			return 1;
		}
                }//Vertical Check done
                //Diaognal Check Top Left to Bottom right
                for(int i=0;i<15;i+=5){
		if(
				(buttons[i+0].getText()=="X") &&
				(buttons[i+6].getText()=="X") &&
				(buttons[i+12].getText()=="X")
				) {
			return 1;
		}
                if(
				(buttons[i+1].getText()=="X") &&
				(buttons[i+7].getText()=="X") &&
				(buttons[i+13].getText()=="X")
				) {
			return 1;
		}
		if(
				(buttons[i+2].getText()=="X") &&
				(buttons[i+8].getText()=="X") &&
				(buttons[i+14].getText()=="X")
				) {
			return 1;
		}
                }//Diaognal Check Top left to Bottom right done
                //Diaognal Check Top right to Bottom left 
                for(int i=0;i<15;i+=5){
		if(
				(buttons[i+2].getText()=="X") &&
				(buttons[i+6].getText()=="X") &&
				(buttons[i+10].getText()=="X")
				) {
			return 1;
		}
                if(
				(buttons[i+3].getText()=="X") &&
				(buttons[i+7].getText()=="X") &&
				(buttons[i+11].getText()=="X")
				) {
			return 1;
		}
		if(
				(buttons[i+4].getText()=="X") &&
				(buttons[i+8].getText()=="X") &&
				(buttons[i+12].getText()=="X")
				) {
			return 1;
		}
                }//Diaognal Check Top right to Bottom left done
                
		//check O win conditions
                //Horizontal Check
                for(int i=0;i<25;i+=5){
		if(
				(buttons[i+0].getText()=="O") &&
				(buttons[i+1].getText()=="O") &&
				(buttons[i+2].getText()=="O")
				) {
			return -1;
		}
                if(
				(buttons[i+1].getText()=="O") &&
				(buttons[i+2].getText()=="O") &&
				(buttons[i+3].getText()=="O")
				) {
			return -1;
		}
		if(
				(buttons[i+2].getText()=="O") &&
				(buttons[i+3].getText()=="O") &&
				(buttons[i+4].getText()=="O")
				) {
			return -1;
		}
                }//Horizontal Check done
                //Vertical Check
                for(int i=0;i<5;i++){
                  if(
				(buttons[i+0].getText()=="O") &&
				(buttons[i+5].getText()=="O") &&
				(buttons[i+10].getText()=="O")
				) {
			return -1;
		} 
                  if(
				(buttons[i+0].getText()=="O") &&
				(buttons[i+5].getText()=="O") &&
				(buttons[i+10].getText()=="O")
				) {
			return -1;
		} 
                  if(
				(buttons[i+10].getText()=="O") &&
				(buttons[i+15].getText()=="O") &&
				(buttons[i+20].getText()=="O")
				) {
			return -1;
		}
                }//Vertical Check done
                //Diaognal Check Top Left to Bottom right
                for(int i=0;i<15;i+=5){
		if(
				(buttons[i+0].getText()=="O") &&
				(buttons[i+6].getText()=="O") &&
				(buttons[i+12].getText()=="O")
				) {
			return -1;
		}
                if(
				(buttons[i+1].getText()=="O") &&
				(buttons[i+7].getText()=="O") &&
				(buttons[i+13].getText()=="O")
				) {
			return -1;
		}
		if(
				(buttons[i+2].getText()=="O") &&
				(buttons[i+8].getText()=="O") &&
				(buttons[i+14].getText()=="O")
				) {
			return -1;
		}
                }//Diaognal Check Top left to Bottom right done
                //Diaognal Check Top right to Bottom left 
                for(int i=0;i<15;i+=5){
		if(
				(buttons[i+2].getText()=="O") &&
				(buttons[i+6].getText()=="O") &&
				(buttons[i+10].getText()=="O")
				) {
			return -1;
		}
                if(
				(buttons[i+3].getText()=="O") &&
				(buttons[i+7].getText()=="O") &&
				(buttons[i+11].getText()=="O")
				) {
			return -1;
		}
		if(
				(buttons[i+4].getText()=="O") &&
				(buttons[i+8].getText()=="O") &&
				(buttons[i+12].getText()=="O")
				) {
			return -1;
		}
                }//Diaognal Check Top right to Bottom left done
                
                 boolean fullBoard = true;
    for (int i = 0; i < 25; i++) {
        if (buttons[i].getText().equals("")) {
            fullBoard = false;
            break;
        }
    }

    if (fullBoard) {
        return 2; // Tie condition
    }
		else {
			return 0;
		}
	}
        
        public static void main(String[] args) {
       
       SwingUtilities.invokeLater(TicTacToe3::createAndShowGUI);
       // TicTacToe3 ttt = new TicTacToe3();
       
    }
        
}

