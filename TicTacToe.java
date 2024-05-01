import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class TicTacToe implements ActionListener{
	
	Random random = new Random();
	JFrame frame = new JFrame();
	JPanel title_panel = new JPanel();
	JPanel button_panel = new JPanel();
	JLabel textfield = new JLabel();
	JButton[] buttons = new JButton[9];
	boolean player1_turn;
	int countFilled = 0;
	
	
	public TicTacToe() {
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 900);
		frame.getContentPane().setBackground(new Color(50, 50, 50));
		frame.getContentPane().setLayout(new BorderLayout());
		frame.setVisible(true);
		
		textfield.setBackground(new Color(25, 25, 25));
		textfield.setForeground(new Color(25, 255, 0));
		textfield.setFont(new Font("Ink Free", Font.BOLD, 75));
		textfield.setHorizontalAlignment(JLabel.CENTER);
		textfield.setText("Tic-Tac-Toe");
		textfield.setOpaque(true);
		
		title_panel.setLayout(new BorderLayout());
		title_panel.setBounds(0, 0, 800, 100);
		
		button_panel.setLayout(new GridLayout(3, 3));
		button_panel.setBackground(new Color(150, 150, 150));
		
		for(int i=0; i<9; i++) {
			buttons[i] = new JButton();
			button_panel.add(buttons[i]);
			buttons[i].setFont(new Font("MV Boli", Font.BOLD, 120));
			buttons[i].setFocusable(false);
			buttons[i].addActionListener(this);
			buttons[i].setEnabled(false);
			
			
		}
		
		title_panel.add(textfield, BorderLayout.CENTER);
		frame.getContentPane().add(title_panel, BorderLayout.NORTH);
		frame.getContentPane().add(button_panel);
		
		
		firstTurn();
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		for(int i=0; i<9; i++) {
			if(e.getSource()==buttons[i]) {
				if(player1_turn) {
					if(buttons[i].getText()=="") {
						buttons[i].setForeground(new Color(255, 0, 0));
						buttons[i].setText("X");
						player1_turn = false;
						textfield.setText("O turn");
						countFilled++;
						check(i, "X");
					}
				}
				else{
					if(buttons[i].getText()=="") {
						buttons[i].setForeground(new Color(0, 0, 255));
						buttons[i].setText("O");
						player1_turn = true;
						textfield.setText("X turn");
						countFilled++;
						check(i, "O");
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
		
		
		if(random.nextInt(2) == 0) {
			player1_turn = true;
			textfield.setText("X turn");
		}
		else {
			player1_turn = false;
			textfield.setText("O turn");
		}
		
//		Nur nach 2 Sekunden, können die Spieler anfangen, zu spielen
		for(JButton button: buttons) {
			button.setEnabled(true);
		}
		
		
		
	}
	
	public void check(int position, String player) {
		boolean gameOver=true;
		int start = position - position%3;
		for(int i=start; i<start+3; i++) {
			if(buttons[i].getText()!=player){
				gameOver=false;
			}
		}
		if(gameOver) {
			wins(start, start+1, start+2, player);
			return;
		}
		
		gameOver=true;
		start= position%3;
		for(int i= start; i<9; i+=3) {
			if(buttons[i].getText()!=player){
				gameOver=false;
			}
		}
		if(gameOver) {
			wins(start, start+3, start+6, player);
			return;
		}
		
		
		int[][] diagonals = new int[][] {{0, 4, 8}, {2, 4, 6}};
		for(int[] diagonal: diagonals) {
			gameOver=true;
			for(int i: diagonal) {
				if(buttons[i].getText()!=player) {
					gameOver=false;
				}
			}
			if(gameOver) {
				wins(diagonal[0], diagonal[1], diagonal[2], player);
				return;
			}
		}
		if(countFilled == 9) {
			draw();
		}
		
		
	}
	
	public void wins(int a, int b, int c, String player) {
		buttons[a].setBackground(Color.GREEN);
		buttons[b].setBackground(Color.GREEN);
		buttons[c].setBackground(Color.GREEN);
		endGame();
		textfield.setText(player + " wins");
	}
	

	
	public void draw() {
		textfield.setText("DRAW-END GAME");
		textfield.setForeground(new Color(255, 0, 0));
		endGame();
	}
	
	public void endGame() {
		for(JButton button: buttons) {
			button.setEnabled(false);
		}
	}
	
	
}
