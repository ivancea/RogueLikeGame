package es.hol.ivancea;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class RogueLikeGame extends JFrame {
	
	public static final int WINDOW_WIDTH = 800,
							WINDOW_HEIGHT = 600;
	private JPanel panel;
	
	public RogueLikeGame(){
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
		
		this.setContentPane(panel);
		this.pack();

		this.setVisible(true);
	}
	
	
	public static void main(String[] args){
		new RogueLikeGame();
	}
}
