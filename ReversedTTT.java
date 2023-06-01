package l.dsasg;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ReversedTTT implements ActionListener{
	Random random = new Random();
	JFrame frame = new JFrame();
	JPanel title_panel = new JPanel();
	JPanel button_panel = new JPanel();
	JPanel button_panel2 = new JPanel();
	JLabel textfield = new JLabel();
	
        JPanel rpanel = new JPanel();
        JButton resButton = new JButton();


	JButton[] modeButtons =new JButton[3];
	
	JButton[] buttons = new JButton[9];
        JButton undoButton = new JButton();
        Stack<Integer> stack = new Stack<>();

	boolean player_turn;
	boolean finish=false;
	boolean move=true;
	int depth;
        
    ReversedTTT(){
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
		textfield.setText("Tic-Tac-Toe");
		textfield.setOpaque(true);
		
		title_panel.setLayout(new BorderLayout());
		title_panel.setBounds(0,0,800,100);
                
                /* For the reset Button */
                rpanel.setLayout(new GridLayout(1, 2));
resButton.setText("Reset");
undoButton.setText("Undo");
rpanel.add(resButton);
rpanel.add(undoButton);
resButton.addActionListener(this);
undoButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        if (!stack.isEmpty()) {
            // Remove the PC's previous move
            int pcMove = stack.pop();
            buttons[pcMove].setText("");
            buttons[pcMove].setEnabled(true);

            if (!stack.isEmpty()) {
                // Remove the player's previous move
                int playerMove = stack.pop();
                buttons[playerMove].setText("");
                buttons[playerMove].setEnabled(true);
            }

            player_turn = true;
            textfield.setText("Your turn");
        }
    }
});
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
		textfield.setText("Tic-Tac-Toe");
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
	    button_panel2.setLayout(new GridLayout(3,3));	
	  for(int i=0;i<9;i++) {
		   buttons[i] = new JButton();
		   button_panel2.add(buttons[i]);
		   buttons[i].setFont(new Font("MV Boli",Font.BOLD,120));
		   buttons[i].setFocusable(false);
		   buttons[i].addActionListener(this);
	  }
	  textfield.setText("Reversed Tic-Tac-Toe");
	  
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
				        depth=2;
				    	game(depth);
				    }
				}
			);
		modeButtons[2].addActionListener(
				new ActionListener() {
				    public void actionPerformed(ActionEvent e) {	
				    	depth=6;
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
    } else if (e.getSource() == undoButton && player_turn) {
        undoButton.doClick(); // Trigger the "Undo" button's action
    } else if (player_turn) {
        for (int i = 0; i < 9; i++) {
            if (e.getSource() == buttons[i]) {
                if (buttons[i].getText().equals("")) {
                    buttons[i].setText("O");
                    buttons[i].setForeground(new Color(0, 0, 255));
                    player_turn = false;
                    textfield.setText("PC turn");
                    move = true;
                    stack.push(i); // Push the index of the button clicked to the stack
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
    button_panel2.setLayout(new GridLayout(3, 3));

    for (int i = 0; i < 9; i++) {
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
    move = false;
    int bestscore = Integer.MIN_VALUE;
    int index = 0;

    for (int i = 0; i < 9; i++) {
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
    move = true;
    win(checkWin());
}


public int minimax(int depth, boolean isMaximizing) {
    move = false;

    int result = checkWin();
    if (result != 0) {
        return -result; // Reverse the sign for AI wins
    }

    if (isMaximizing) {
        int bestscore = Integer.MIN_VALUE;
        for (int i = 0; i < 9; i++) {
            if (buttons[i].getText().equals("")) {
                if (depth != 0) {
                    buttons[i].setText("X");
                    int score = minimax(depth - 1, false);
                    buttons[i].setText("");
                    bestscore = Math.max(score, bestscore);
                } else {
                    bestscore = Math.max(0, bestscore);
                }
            }
        }
        return bestscore;
    } else {
        int bestscore = Integer.MAX_VALUE;
        for (int i = 0; i < 9; i++) {
            if (buttons[i].getText().equals("")) {
                if (depth != 0) {
                    buttons[i].setText("O");
                    int score = minimax(depth - 1, true);
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
            int x=(int)(Math.random()*3);
		if(j==1) {
			for(int i=0;i<9;i++) {
				buttons[i].setEnabled(false);
			}
                        undoButton.setEnabled(false); // Disable the "Undo" button
			textfield.setText("You wins");
			finish=true;
                        JOptionPane.showMessageDialog(frame, "Congratulations: You manage to consider the potential consequences of your moves and anticipate your opponent's responses.", "Congratulations! You win!", JOptionPane.INFORMATION_MESSAGE);
		}
		else if(j==-1) {
			for(int i=0;i<9;i++) {
				buttons[i].setEnabled(false);
			}
                        undoButton.setEnabled(false); // Disable the "Undo" button
			textfield.setText("You lose");
			finish=true;
                        if(x==0){
                        JOptionPane.showMessageDialog(frame, "Recommendation: Pay close attention to your opponent's moves and identify potential lines they might be trying to form. ","You lose! Better luck next time.",  JOptionPane.INFORMATION_MESSAGE);
		}else if(x==1){
                     JOptionPane.showMessageDialog(frame, "Recommendation: Start by placing your shape in one of the corners. The corner has the least possible lines to be made.", "You lose! Better luck next time.", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(frame, "Recommendation: Set up situations where your opponent is forced to make a move that will inevitably lead to a line.  ","You lose! Better luck next time.",  JOptionPane.INFORMATION_MESSAGE);
                }
                }else if(j==2) {
                    
			for(int i=0;i<9;i++) {
				buttons[i].setEnabled(false);
			}
                        undoButton.setEnabled(false); // Disable the "Undo" button
			textfield.setText("The Game is Draw");
			finish=true;
                        if(x==0){
                        JOptionPane.showMessageDialog(frame, "Recommedation: Try to be more aggresive. Defence is not the only way to win in this game.","The game is a draw.",  JOptionPane.INFORMATION_MESSAGE);
		}else if(x==1){
                     JOptionPane.showMessageDialog(frame, "Recommendation: The more you play the game, the better you will become at recognizing patterns and developing winning strategies", "The game is a draw.", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(frame, "Recommendation:  Be prepared to adjust your strategy based on the evolving game situation. As the board fills up, new opportunities and threats may arise. ","The game is a draw.",  JOptionPane.INFORMATION_MESSAGE);
                }
		}
	}
	public int checkWin() {
		//check X win conditions
		if(
				(buttons[0].getText()=="X") &&
				(buttons[1].getText()=="X") &&
				(buttons[2].getText()=="X")
				) {
			
			
			return 1;
		}
		if(
				(buttons[3].getText()=="X") &&
				(buttons[4].getText()=="X") &&
				(buttons[5].getText()=="X")
				) {
			
			return 1;
		}
		if(
				(buttons[6].getText()=="X") &&
				(buttons[7].getText()=="X") &&
				(buttons[8].getText()=="X")
				) {
			
			return 1;
		}
		if(
				(buttons[0].getText()=="X") &&
				(buttons[3].getText()=="X") &&
				(buttons[6].getText()=="X")
				) {
			
			return 1;
		}
		if(
				(buttons[1].getText()=="X") &&
				(buttons[4].getText()=="X") &&
				(buttons[7].getText()=="X")
				) {
			
			return 1;
		}
		if(
				(buttons[2].getText()=="X") &&
				(buttons[5].getText()=="X") &&
				(buttons[8].getText()=="X")
				) {
			
			return 1;
		}
		if(
				(buttons[0].getText()=="X") &&
				(buttons[4].getText()=="X") &&
				(buttons[8].getText()=="X")
				) {
			
			return 1;
		}
		if(
				(buttons[2].getText()=="X") &&
				(buttons[4].getText()=="X") &&
				(buttons[6].getText()=="X")
				) {
			
			return 1;
		}
		//check O win conditions
		if(
				(buttons[0].getText()=="O") &&
				(buttons[1].getText()=="O") &&
				(buttons[2].getText()=="O")
				) {
			
			return -1;
		}
		if(
				(buttons[3].getText()=="O") &&
				(buttons[4].getText()=="O") &&
				(buttons[5].getText()=="O")
				) {
			
			return -1;
		}
		if(
				(buttons[6].getText()=="O") &&
				(buttons[7].getText()=="O") &&
				(buttons[8].getText()=="O")
				) {
			
			return -1;
		}
		if(
				(buttons[0].getText()=="O") &&
				(buttons[3].getText()=="O") &&
				(buttons[6].getText()=="O")
				) {
			
			return -1;
		}
		if(
				(buttons[1].getText()=="O") &&
				(buttons[4].getText()=="O") &&
				(buttons[7].getText()=="O")
				) {
			
			return -1;
		}
		if(
				(buttons[2].getText()=="O") &&
				(buttons[5].getText()=="O") &&
				(buttons[8].getText()=="O")
				) {
			
			return -1;
		}
		if(
				(buttons[0].getText()=="O") &&
				(buttons[4].getText()=="O") &&
				(buttons[8].getText()=="O")
				) {
			
			return -1;
		}
		if(
				(buttons[2].getText()=="O") &&
				(buttons[4].getText()=="O") &&
				(buttons[6].getText()=="O")
				) {
			
			return -1;
		}
                boolean fullBoard = true;
    for (int i = 0; i < 9; i++) {
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
}