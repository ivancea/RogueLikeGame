package es.hol.ivancea;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class RogueLikeGame extends JFrame {
	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 800,
							HEIGHT = 600;
	
	private JPanel panel;
	
	public RogueLikeGame(){
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		
		panel = new JPanel(){
			private static final long serialVersionUID = 1L;
			
			@Override
			public void paint(Graphics g){
				g.clearRect(0, 0, this.getWidth(), this.getHeight());
				paintScene(g);
			}
			
		};
		panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		this.setContentPane(panel);
		this.pack();

		this.setVisible(true);
	}
	
	private void paintScene(Graphics g){
		// POC
		g.drawRect(50,50, WIDTH-100, HEIGHT-100);
	}
	
	
	public static void main(String[] args){
		new RogueLikeGame();
	}
}

