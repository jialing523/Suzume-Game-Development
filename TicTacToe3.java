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
import javax.swing.BorderFactory;
import javax.swing.JTextArea;
import javax.swing.JOptionPane;

public class TicTacToe3 implements ActionListener{
        
        //PVP 
        Random random2 = new Random();
	JFrame frame2 = new JFrame();
	JPanel title_panel2 = new JPanel();
	JPanel button_panel4 = new JPanel();
        JPanel button_panel5 = new JPanel();
	JLabel textfield2 = new JLabel();
        String difficultyLevel;
        
        JButton[] modeButtons2 = new JButton[3];
        JButton[] buttons2 = new JButton[25];
        
	boolean player1_turn2;
        boolean finish2=false;
        
        //PVE
        Random random = new Random();
	JFrame frame = new JFrame();
	JPanel title_panel = new JPanel();
	JPanel button_panel = new JPanel();
	JPanel button_panel2 = new JPanel();
        JPanel button_panel3 = new JPanel();
	JLabel textfield = new JLabel();
	
        JPanel rpanel = new JPanel();
        JButton resButton = new JButton();
        JButton undoButton = new JButton();

	JButton[] modeButtons =new JButton[2];
	JButton[] diffButtons =new JButton[3];
	JButton[] buttons = new JButton[25];

        Stack<Integer> stack = new Stack<>();
        
	boolean player_turn;
	public boolean finish=false;
        boolean pve;
	boolean move=true;
        int depth;   
	public int gameStatus=-1;
       
        
	   
    
    public void PVP(){
		//set up frame for choosing mode
		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame2.setSize(800,800);
		frame2.getContentPane().setBackground(new Color(50,50,50));
		frame2.setLayout(new BorderLayout());
		frame2.setVisible(true);
		
                //set up textfield and title panel
		textfield2.setBackground(new Color(25,25,25));
		textfield2.setForeground(new Color(25,255,0));
		textfield2.setFont(new Font("Ink Free",Font.BOLD,75));
		textfield2.setHorizontalAlignment(JLabel.CENTER);
		textfield2.setText("Tic-Tac-Toe");
		textfield2.setOpaque(true);
		
		title_panel2.setLayout(new BorderLayout());
		title_panel2.setBounds(0,0,800,100);
		
                //set up button panel and button for mode
                button_panel4.setLayout(new GridLayout(3,1));
                
                
		button_panel4.setLayout(new GridLayout(5,5));
		button_panel4.setBackground(new Color(150,150,150));
		
		for(int i=0;i<25;i++) {
			buttons2[i] = new JButton();
			button_panel4.add(buttons2[i]);
			buttons2[i].setFont(new Font("MV Boli",Font.BOLD,120));
			buttons2[i].setFocusable(false);
			buttons2[i].addActionListener(listener2);
		}
		
		title_panel2.add(textfield2);
		frame2.add(title_panel2,BorderLayout.NORTH);
		frame2.add(button_panel4);
		
                chooseDifficultyLevel2();
	
	}

        private void chooseDifficultyLevel2() {
        String[] options = {"Easy", "Medium", "Hard"};
        int choice = JOptionPane.showOptionDialog(this, "Choose difficulty level", "Difficulty Level",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);    
    }
        
	ActionListener listener2 = new ActionListener(){
	public void actionPerformed(ActionEvent e) {
		
		for(int i=0;i<25;i++) {
			if(e.getSource()==buttons2[i]) {
				if(player1_turn2) {
					if(buttons2[i].getText()=="") {
						buttons2[i].setForeground(new Color(255,0,0));
						buttons2[i].setText("X");
						player1_turn2=false;
						textfield2.setText("O turn");
						check2();
					}
				}
				else {
					if(buttons2[i].getText()=="") {
						buttons2[i].setForeground(new Color(0,0,255));
						buttons2[i].setText("O");
						player1_turn2=true;
						textfield2.setText("X turn");
						check2();
					}
				}
			}			
		}
	}
        };
	public void firstTurn2() {
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(random2.nextInt(2)==0) {
			player1_turn2=true;
			textfield2.setText("X turn");
		}
		else {
			player1_turn2=false;
			textfield2.setText("O turn");
		}
	}
	
	public void check2() {
		//check X win conditions
                //Horizontal Check
                for(int i=0;i<25;i+=5){
		if(
				(buttons2[i+0].getText()=="X") &&
				(buttons2[i+1].getText()=="X") &&
				(buttons2[i+2].getText()=="X")
				) {
			xWins((i+0),(i+1),(i+2));
		}
                if(
				(buttons2[i+1].getText()=="X") &&
				(buttons2[i+2].getText()=="X") &&
				(buttons2[i+3].getText()=="X")
				) {
			xWins((i+1),(i+2),(i+3));
		}
		if(
				(buttons2[i+2].getText()=="X") &&
				(buttons2[i+3].getText()=="X") &&
				(buttons2[i+4].getText()=="X")
				) {
			xWins((i+2),(i+3),(i+4));
		}
                }//Horizontal Check done
                //Vertical Check
                for(int i=0;i<5;i++){
                  if(
				(buttons2[i+0].getText()=="X") &&
				(buttons2[i+5].getText()=="X") &&
				(buttons2[i+10].getText()=="X")
				) {
			xWins((i+0),(i+5),(i+10));
		} 
                  if(
				(buttons2[i+5].getText()=="X") &&
				(buttons2[i+10].getText()=="X") &&
				(buttons2[i+15].getText()=="X")
				) {
			xWins((i+5),(i+10),(i+15));
		} 
                  if(
				(buttons2[i+10].getText()=="X") &&
				(buttons2[i+15].getText()=="X") &&
				(buttons2[i+20].getText()=="X")
				) {
			xWins((i+10),(i+15),(i+20));
		}
                }//Vertical Check done
                //Diaognal Check Top Left to Bottom right
                for(int i=0;i<15;i+=5){
		if(
				(buttons2[i+0].getText()=="X") &&
				(buttons2[i+6].getText()=="X") &&
				(buttons2[i+12].getText()=="X")
				) {
			xWins((i+0),(i+6),(i+12));
		}
                if(
				(buttons2[i+1].getText()=="X") &&
				(buttons2[i+7].getText()=="X") &&
				(buttons2[i+13].getText()=="X")
				) {
			xWins((i+1),(i+7),(i+13));
		}
		if(
				(buttons2[i+2].getText()=="X") &&
				(buttons2[i+8].getText()=="X") &&
				(buttons2[i+14].getText()=="X")
				) {
			xWins((i+2),(i+8),(i+14));
		}
                }//Diaognal Check Top left to Bottom right done
                //Diaognal Check Top right to Bottom left 
                for(int i=0;i<15;i+=5){
		if(
				(buttons2[i+2].getText()=="X") &&
				(buttons2[i+6].getText()=="X") &&
				(buttons2[i+10].getText()=="X")
				) {
			xWins((i+2),(i+6),(i+10));
		}
                if(
				(buttons2[i+3].getText()=="X") &&
				(buttons2[i+7].getText()=="X") &&
				(buttons2[i+11].getText()=="X")
				) {
			xWins((i+3),(i+7),(i+11));
		}
		if(
				(buttons2[i+4].getText()=="X") &&
				(buttons2[i+8].getText()=="X") &&
				(buttons2[i+12].getText()=="X")
				) {
			xWins((i+4),(i+8),(i+12));
		}
                }//Diaognal Check Top right to Bottom left done
                
		//check O win conditions
                //Horizontal Check
                for(int i=0;i<25;i+=5){
		if(
				(buttons2[i+0].getText()=="O") &&
				(buttons2[i+1].getText()=="O") &&
				(buttons2[i+2].getText()=="O")
				) {
			oWins((i+0),(i+1),(i+2));
		}
                if(
				(buttons2[i+1].getText()=="O") &&
				(buttons2[i+2].getText()=="O") &&
				(buttons2[i+3].getText()=="O")
				) {
			oWins((i+1),(i+2),(i+3));
		}
		if(
				(buttons2[i+2].getText()=="O") &&
				(buttons2[i+3].getText()=="O") &&
				(buttons2[i+4].getText()=="O")
				) {
			oWins((i+2),(i+3),(i+4));
		}
                }//Horizontal Check done
                //Vertical Check
                for(int i=0;i<5;i++){
                  if(
				(buttons2[i+0].getText()=="O") &&
				(buttons2[i+5].getText()=="O") &&
				(buttons2[i+10].getText()=="O")
				) {
			oWins((i+0),(i+5),(i+10));
		} 
                  if(
				(buttons2[i+5].getText()=="O") &&
				(buttons2[i+10].getText()=="O") &&
				(buttons2[i+15].getText()=="O")
				) {
			oWins((i+5),(i+10),(i+15));
		} 
                  if(
				(buttons2[i+10].getText()=="O") &&
				(buttons2[i+15].getText()=="O") &&
				(buttons2[i+20].getText()=="O")
				) {
			oWins((i+10),(i+15),(i+20));
		}
                }//Vertical Check done
                //Diaognal Check Top Left to Bottom right
                for(int i=0;i<15;i+=5){
		if(
				(buttons2[i+0].getText()=="O") &&
				(buttons2[i+6].getText()=="O") &&
				(buttons2[i+12].getText()=="O")
				) {
			oWins((i+0),(i+6),(i+12));
		}
                if(
				(buttons2[i+1].getText()=="O") &&
				(buttons2[i+7].getText()=="O") &&
				(buttons2[i+13].getText()=="O")
				) {
			xWins((i+1),(i+7),(i+13));
		}
		if(
				(buttons2[i+2].getText()=="O") &&
				(buttons2[i+8].getText()=="O") &&
				(buttons2[i+14].getText()=="O")
				) {
			oWins((i+2),(i+8),(i+14));
		}
                }//Diaognal Check Top left to Bottom right done
                //Diaognal Check Top right to Bottom left 
                for(int i=0;i<15;i+=5){
		if(
				(buttons2[i+2].getText()=="O") &&
				(buttons2[i+6].getText()=="O") &&
				(buttons2[i+10].getText()=="O")
				) {
			oWins((i+2),(i+6),(i+10));
		}
                if(
				(buttons2[i+3].getText()=="O") &&
				(buttons2[i+7].getText()=="O") &&
				(buttons2[i+11].getText()=="O")
				) {
			oWins((i+3),(i+7),(i+11));
		}
		if(
				(buttons2[i+4].getText()=="O") &&
				(buttons2[i+8].getText()=="O") &&
				(buttons2[i+12].getText()=="O")
				) {
			oWins((i+4),(i+8),(i+12));
		}
                }//Diaognal Check Top right to Bottom left done
	}
	
	public void xWins(int a,int b,int c) {
		buttons2[a].setBackground(Color.GREEN);
		buttons2[b].setBackground(Color.GREEN);
		buttons2[c].setBackground(Color.GREEN);
		
		for(int i=0;i<25;i++) {
			buttons2[i].setEnabled(false);
		}
		textfield2.setText("X wins");
	}
	public void oWins(int a,int b,int c) {
		buttons2[a].setBackground(Color.GREEN);
		buttons2[b].setBackground(Color.GREEN);
		buttons2[c].setBackground(Color.GREEN);
		
		for(int i=0;i<25;i++) {
			buttons2[i].setEnabled(false);
		}
		textfield2.setText("O wins");
	}
    
        
        
    
    public void PVE(){
    	//set up frame for choosing mode
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800,800);
		frame.getContentPane().setBackground(new Color(50,50,50));
		frame.setLayout(new BorderLayout());
		frame.setVisible(true);
                frame.setLocationRelativeTo(null);
		
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
                
                displayGameIntroduction();
                // set up button panel and button for mode
		button_panel3.setLayout(new GridLayout(2,1));
		
		for(int i=0;i<2;i++) {
			modeButtons[i] = new JButton();
			button_panel3.add(modeButtons[i]);
			modeButtons[i].setFont(new Font("MV Boli",Font.BOLD,120));
			modeButtons[i].setFocusable(false);
		}
		modeButtons[0].setText("PvE");
		modeButtons[1].setText("PvP");
                
                // complete the frame
		textfield.setText("Tic-Tac-Toe 5x5");
		title_panel.add(textfield);
                rpanel.add(resButton);
		frame.add(title_panel,BorderLayout.NORTH);
		frame.add(button_panel3);
                frame.add(rpanel, BorderLayout.SOUTH);
                
                modeButtons[0].addActionListener(
				new ActionListener() {
				    public void actionPerformed(ActionEvent e) {
				    	pve=true;
				    	chooseMode();
				    }
				}
			);
		modeButtons[1].addActionListener(
				new ActionListener() {
				    public void actionPerformed(ActionEvent e) {
				    	pve=false;
				    	frame.remove(button_panel3);
                                        frame.dispose();
				    	PVP();
				    }
				}
			);
               
    }
    
  
    
private static void displayGameIntroduction() {
        String text = "5x5 Tic-Tac-Toe\n\n" +
                      "A regular game of TTT in a 5x5 square, players take turns placing shapes either a cross (X) or a nought (O)." + 
                      "The winner is the first player to place 3 of their shape in either a horizontal, vertical, or diagonal row."  ;
        

        JFrame frame = new JFrame("Game Introduction");
        frame.setSize(400, 300);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextArea textArea = new JTextArea(text);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        panel.add(textArea, BorderLayout.CENTER);

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Close the frame when the Close button is clicked
            }
        });
        panel.add(closeButton, BorderLayout.SOUTH);

        frame.setContentPane(panel);
        frame.setLocationRelativeTo(null); // Center the frame on the screen
        frame.setVisible(true);

        
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
	  
	  
	  frame.add(button_panel2);
	  
	}
    
//choose the difficulty of the game    
 public void chooseMode() {
                frame.remove(button_panel3);
                
		// set up button panel and button for mode
                           button_panel.setLayout(new GridLayout(3,1));

                                        for(int i=0;i<3;i++) {
                                                diffButtons[i] = new JButton();
                                                button_panel.add(diffButtons[i]);
                                                diffButtons[i].setFont(new Font("MV Boli",Font.BOLD,120));
                                                diffButtons[i].setFocusable(false);

                                        }
                                        diffButtons[0].setText("Easy");
                                        diffButtons[1].setText("Normal");
                                        diffButtons[2].setText("Hard");

                                        // complete the frame
                                        textfield.setText("Choose difficulty");
                                        title_panel.add(textfield);
                                        frame.add(title_panel,BorderLayout.NORTH);
                                        frame.add(button_panel);


                                        // add ActionListener to the diffButtons

                                        diffButtons[0].addActionListener(
                                        new ActionListener() {
                                            public void actionPerformed(ActionEvent e) {
                                                depth=1;
                                                game(depth);
                                            }
                                        }
                        );
                                        diffButtons[1].addActionListener(
                                        new ActionListener() {
                                            public void actionPerformed(ActionEvent e) {	
                                                depth=2;
                                                game(depth);
                                            }
                                        }
                                );
                                        diffButtons[2].addActionListener(
                                        new ActionListener() {
                                            public void actionPerformed(ActionEvent e) {	
                                                depth=4;
                                                game(depth);
                                            }
                                        }
                                );
                       
		
		
 }
 

 public void actionPerformed(ActionEvent e) {
   
     if (e.getSource() == resButton) {
        resetGame();
     } else {
        for (int i = 0; i < 25; i++) {
            if (e.getSource() == buttons[i]) {
                if (buttons[i].getText().equals("")) {
                    buttons[i].setText("O");
                    buttons[i].setForeground(new Color(0, 0, 255));
                    player_turn = false;
                    textfield.setText("PC turn");
                    title_panel.add(textfield);
                    frame.add(title_panel,BorderLayout.NORTH);
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
    stack.push(index); // Push the PC's move to the stack
    player_turn = true;
    textfield.setText("Your turn");
    title_panel.add(textfield);
    move = true;
    win(checkWin());
}
  
  //Algorithm for three difficulties
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
        return bestscore ;
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
        title_panel.add(textfield);
    } else {
        textfield.setText("PC turn");
        title_panel.add(textfield);
        AiTurn(depth);
    }
    finish = false;
    undoButton.setEnabled(true); // Disable the "Undo" button

    frame.add(button_panel2);
    frame.revalidate();
    frame.repaint();
}


	public void win(int j) {
             if(j==1) {
			for(int i=0;i<25;i++) {
				buttons[i].setEnabled(false);
			}
			textfield.setText("You lose");
                        gameStatus=0;
			finish=true;
                        
		}
		else if(j==-1) {
			for(int i=0;i<25;i++) {
				buttons[i].setEnabled(false);
			}
			textfield.setText("You wins");
                        gameStatus=1;
			finish=true;
                        
		}else if(j==2) {
			for(int i=0;i<25;i++) {
				buttons[i].setEnabled(false);
			}
			textfield.setText("The Game is Draw");
                        gameStatus=2;
			finish=true;
                        frame.dispose();
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
                        xWins1((i+0),(i+1),(i+2));
			return 1;
		}
                if(
				(buttons[i+1].getText()=="X") &&
				(buttons[i+2].getText()=="X") &&
				(buttons[i+3].getText()=="X")
				) {
			xWins1((i+1),(i+2),(i+3));
                        return 1;
		}
		if(
				(buttons[i+2].getText()=="X") &&
				(buttons[i+3].getText()=="X") &&
				(buttons[i+4].getText()=="X")
				) {
			xWins1((i+2),(i+3),(i+4));
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
                        xWins((i+0),(i+5),(i+10));
			return 1;
		} 
                  if(
				(buttons[i+5].getText()=="X") &&
				(buttons[i+10].getText()=="X") &&
				(buttons[i+15].getText()=="X")
				) {
                        xWins((i+5),(i+10),(i+15));
			return 1;
		} 
                  if(
				(buttons[i+10].getText()=="X") &&
				(buttons[i+15].getText()=="X") &&
				(buttons[i+20].getText()=="X")
				) {
			xWins1((i+10),(i+15),(i+20));
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
                        xWins1((i+0),(i+6),(i+12));
			return 1;
		}
                if(
				(buttons[i+1].getText()=="X") &&
				(buttons[i+7].getText()=="X") &&
				(buttons[i+13].getText()=="X")
				) {
			xWins1((i+1),(i+7),(i+13));
                        return 1;
		}
		if(
				(buttons[i+2].getText()=="X") &&
				(buttons[i+8].getText()=="X") &&
				(buttons[i+14].getText()=="X")
				) {
                        xWins1((i+2),(i+8),(i+14));
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
                        xWins1((i+2),(i+6),(i+10));
			return 1;
		}
                if(
				(buttons[i+3].getText()=="X") &&
				(buttons[i+7].getText()=="X") &&
				(buttons[i+11].getText()=="X")
				) {
                        xWins1((i+3),(i+7),(i+11));
			return 1;
		}
		if(
				(buttons[i+4].getText()=="X") &&
				(buttons[i+8].getText()=="X") &&
				(buttons[i+12].getText()=="X")
				) {
                        xWins1((i+4),(i+8),(i+12));
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
                        oWins1((i+0),(i+1),(i+2));
			return -1;
		}
                if(
				(buttons[i+1].getText()=="O") &&
				(buttons[i+2].getText()=="O") &&
				(buttons[i+3].getText()=="O")
				) {
                        oWins1((i+1),(i+2),(i+3));
			return -1;
		}
		if(
				(buttons[i+2].getText()=="O") &&
				(buttons[i+3].getText()=="O") &&
				(buttons[i+4].getText()=="O")
				) {
                        oWins1((i+2),(i+3),(i+4));
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
                        oWins1((i+0),(i+5),(i+10));
			return -1;
		} 
                  if(
				(buttons[i+5].getText()=="O") &&
				(buttons[i+10].getText()=="O") &&
				(buttons[i+15].getText()=="O")
				) {
                        oWins1((i+5),(i+10),(i+15));
			return -1;
		} 
                  if(
				(buttons[i+10].getText()=="O") &&
				(buttons[i+15].getText()=="O") &&
				(buttons[i+20].getText()=="O")
				) {
                        oWins1((i+10),(i+15),(i+20));
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
                        oWins1((i+0),(i+6),(i+12));
			return -1;
		}
                if(
				(buttons[i+1].getText()=="O") &&
				(buttons[i+7].getText()=="O") &&
				(buttons[i+13].getText()=="O")
				) {
                        oWins1((i+1),(i+7),(i+13));
			return -1;
		}
		if(
				(buttons[i+2].getText()=="O") &&
				(buttons[i+8].getText()=="O") &&
				(buttons[i+14].getText()=="O")
				) {
                        oWins1((i+2),(i+8),(i+14));
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
                        oWins1((i+2),(i+6),(i+10));
			return -1;
		}
                if(
				(buttons[i+3].getText()=="O") &&
				(buttons[i+7].getText()=="O") &&
				(buttons[i+11].getText()=="O")
				) {
                        oWins1((i+3),(i+7),(i+11));
			return -1;
		}
		if(
				(buttons[i+4].getText()=="O") &&
				(buttons[i+8].getText()=="O") &&
				(buttons[i+12].getText()=="O")
				) {
                        oWins1((i+4),(i+8),(i+12));
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
        
 public void xWins1(int a,int b,int c) {
		buttons[a].setBackground(Color.GREEN);
		buttons[b].setBackground(Color.GREEN);
		buttons[c].setBackground(Color.GREEN);
		
		
	}
	public void oWins1(int a,int b,int c) {
		buttons[a].setBackground(Color.GREEN);
		buttons[b].setBackground(Color.GREEN);
		buttons[c].setBackground(Color.GREEN);
		
		
	}       
        
        
        
        
        public static void main(String[] args) {
       
           TicTacToe3 ttt3 = new TicTacToe3();
           ttt3.PVE();
           
    }
        
}
