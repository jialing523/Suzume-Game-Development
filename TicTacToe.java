
package wia1002;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class TicTacToe implements ActionListener{

	Random random = new Random();
	JFrame frame = new JFrame();
	JPanel title_panel = new JPanel();
	JPanel button_panel = new JPanel();
        JPanel button_panel2 = new JPanel();
	JLabel textfield = new JLabel();
        String difficultyLevel;
        
        JButton[] modeButtons = new JButton[3];
        JButton[] buttons = new JButton[25];
        
	boolean player1_turn;
        boolean finish=false;
        public int gameSatus=-1;

	TicTacToe(){
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
		
                //set up button panel and button for mode
                button_panel.setLayout(new GridLayout(3,1));
                
                
		button_panel.setLayout(new GridLayout(5,5));
		button_panel.setBackground(new Color(150,150,150));
		
		for(int i=0;i<25;i++) {
			buttons[i] = new JButton();
			button_panel.add(buttons[i]);
			buttons[i].setFont(new Font("MV Boli",Font.BOLD,120));
			buttons[i].setFocusable(false);
			buttons[i].addActionListener(this);
		}
		
		title_panel.add(textfield);
		frame.add(title_panel,BorderLayout.NORTH);
		frame.add(button_panel);
		
                chooseDifficultyLevel();
	
	}

        private void chooseDifficultyLevel() {
        String[] options = {"Easy", "Medium", "Hard"};
        int choice = JOptionPane.showOptionDialog(this, "Choose difficulty level", "Difficulty Level",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        switch (choice) {
            case 0:
                difficultyLevel = "easy";
                break;
            case 1:
                difficultyLevel = "medium";
                break;
            case 2:
                difficultyLevel = "hard";
                break;
            default:
                JOptionPane.showMessageDialog(this, "Invalid choice. Exiting the game.");
                System.exit(0);
        }
    }
        
	@Override
	public void actionPerformed(ActionEvent e) {
		
		for(int i=0;i<25;i++) {
			if(e.getSource()==buttons[i]) {
				if(player1_turn) {
					if(buttons[i].getText()=="") {
						buttons[i].setForeground(new Color(255,0,0));
						buttons[i].setText("X");
						player1_turn=false;
						textfield.setText("O turn");
						check();
					}
				}
				else {
					if(buttons[i].getText()=="") {
						buttons[i].setForeground(new Color(0,0,255));
						buttons[i].setText("O");
						player1_turn=true;
						textfield.setText("X turn");
						check();
					}
				}
			}			
		}
	}
	
	public void firstTurn() {
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(random.nextInt(2)==0) {
			player1_turn=true;
			textfield.setText("X turn");
		}
		else {
			player1_turn=false;
			textfield.setText("O turn");
		}
	}
	
	public void check() {
		//check X win conditions
                //Horizontal Check
                for(int i=0;i<25;i+=5){
		if(
				(buttons[i+0].getText()=="X") &&
				(buttons[i+1].getText()=="X") &&
				(buttons[i+2].getText()=="X")
				) {
			xWins((i+0),(i+1),(i+2));
		}
                if(
				(buttons[i+1].getText()=="X") &&
				(buttons[i+2].getText()=="X") &&
				(buttons[i+3].getText()=="X")
				) {
			xWins((i+1),(i+2),(i+3));
		}
		if(
				(buttons[i+2].getText()=="X") &&
				(buttons[i+3].getText()=="X") &&
				(buttons[i+4].getText()=="X")
				) {
			xWins((i+2),(i+3),(i+4));
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
		} 
                  if(
				(buttons[i+5].getText()=="X") &&
				(buttons[i+10].getText()=="X") &&
				(buttons[i+15].getText()=="X")
				) {
			xWins((i+5),(i+10),(i+15));
		} 
                  if(
				(buttons[i+10].getText()=="X") &&
				(buttons[i+15].getText()=="X") &&
				(buttons[i+20].getText()=="X")
				) {
			xWins((i+10),(i+15),(i+20));
		}
                }//Vertical Check done
                //Diaognal Check Top Left to Bottom right
                for(int i=0;i<15;i+=5){
		if(
				(buttons[i+0].getText()=="X") &&
				(buttons[i+6].getText()=="X") &&
				(buttons[i+12].getText()=="X")
				) {
			xWins((i+0),(i+6),(i+12));
		}
                if(
				(buttons[i+1].getText()=="X") &&
				(buttons[i+7].getText()=="X") &&
				(buttons[i+13].getText()=="X")
				) {
			xWins((i+1),(i+7),(i+13));
		}
		if(
				(buttons[i+2].getText()=="X") &&
				(buttons[i+8].getText()=="X") &&
				(buttons[i+14].getText()=="X")
				) {
			xWins((i+2),(i+8),(i+14));
		}
                }//Diaognal Check Top left to Bottom right done
                //Diaognal Check Top right to Bottom left 
                for(int i=0;i<15;i+=5){
		if(
				(buttons[i+2].getText()=="X") &&
				(buttons[i+6].getText()=="X") &&
				(buttons[i+10].getText()=="X")
				) {
			xWins((i+2),(i+6),(i+10));
		}
                if(
				(buttons[i+3].getText()=="X") &&
				(buttons[i+7].getText()=="X") &&
				(buttons[i+11].getText()=="X")
				) {
			xWins((i+3),(i+7),(i+11));
		}
		if(
				(buttons[i+4].getText()=="X") &&
				(buttons[i+8].getText()=="X") &&
				(buttons[i+12].getText()=="X")
				) {
			xWins((i+4),(i+8),(i+12));
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
			oWins((i+0),(i+1),(i+2));
		}
                if(
				(buttons[i+1].getText()=="O") &&
				(buttons[i+2].getText()=="O") &&
				(buttons[i+3].getText()=="O")
				) {
			oWins((i+1),(i+2),(i+3));
		}
		if(
				(buttons[i+2].getText()=="O") &&
				(buttons[i+3].getText()=="O") &&
				(buttons[i+4].getText()=="O")
				) {
			oWins((i+2),(i+3),(i+4));
		}
                }//Horizontal Check done
                //Vertical Check
                for(int i=0;i<5;i++){
                  if(
				(buttons[i+0].getText()=="O") &&
				(buttons[i+5].getText()=="O") &&
				(buttons[i+10].getText()=="O")
				) {
			oWins((i+0),(i+5),(i+10));
		} 
                  if(
				(buttons[i+5].getText()=="O") &&
				(buttons[i+10].getText()=="O") &&
				(buttons[i+15].getText()=="O")
				) {
			oWins((i+5),(i+10),(i+15));
		} 
                  if(
				(buttons[i+10].getText()=="O") &&
				(buttons[i+15].getText()=="O") &&
				(buttons[i+20].getText()=="O")
				) {
			oWins((i+10),(i+15),(i+20));
		}
                }//Vertical Check done
                //Diaognal Check Top Left to Bottom right
                for(int i=0;i<15;i+=5){
		if(
				(buttons[i+0].getText()=="O") &&
				(buttons[i+6].getText()=="O") &&
				(buttons[i+12].getText()=="O")
				) {
			oWins((i+0),(i+6),(i+12));
		}
                if(
				(buttons[i+1].getText()=="O") &&
				(buttons[i+7].getText()=="O") &&
				(buttons[i+13].getText()=="O")
				) {
			xWins((i+1),(i+7),(i+13));
		}
		if(
				(buttons[i+2].getText()=="O") &&
				(buttons[i+8].getText()=="O") &&
				(buttons[i+14].getText()=="O")
				) {
			oWins((i+2),(i+8),(i+14));
		}
                }//Diaognal Check Top left to Bottom right done
                //Diaognal Check Top right to Bottom left 
                for(int i=0;i<15;i+=5){
		if(
				(buttons[i+2].getText()=="O") &&
				(buttons[i+6].getText()=="O") &&
				(buttons[i+10].getText()=="O")
				) {
			oWins((i+2),(i+6),(i+10));
		}
                if(
				(buttons[i+3].getText()=="O") &&
				(buttons[i+7].getText()=="O") &&
				(buttons[i+11].getText()=="O")
				) {
			oWins((i+3),(i+7),(i+11));
		}
		if(
				(buttons[i+4].getText()=="O") &&
				(buttons[i+8].getText()=="O") &&
				(buttons[i+12].getText()=="O")
				) {
			oWins((i+4),(i+8),(i+12));
		}
                }//Diaognal Check Top right to Bottom left done
	}
	
	public void xWins(int a,int b,int c) {
		buttons[a].setBackground(Color.GREEN);
		buttons[b].setBackground(Color.GREEN);
		buttons[c].setBackground(Color.GREEN);
		
		for(int i=0;i<25;i++) {
			buttons[i].setEnabled(false);
		}
		textfield.setText("X wins");
                this.gameSatus=0;
                finish=true;
                frame.dispose();
	}
	public void oWins(int a,int b,int c) {
		buttons[a].setBackground(Color.GREEN);
		buttons[b].setBackground(Color.GREEN);
		buttons[c].setBackground(Color.GREEN);
		
		for(int i=0;i<25;i++) {
			buttons[i].setEnabled(false);
		}
		textfield.setText("O wins");
                this.gameSatus=1;
                finish=true;
                frame.dispose();
	}

        
        public static void main(String[] args) {
        TicTacToe tictactoe = new TicTacToe();
    }
}
