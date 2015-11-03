package es.hol.ivancea;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class RogueLikeGame extends JFrame {
	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 800,
							HEIGHT = 600;
	
	private int[][] map;
	
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
		
		this.addKeyListener(new KeyListener(){
			@Override
			public void keyPressed(KeyEvent arg0) {
				
			}
			@Override
			public void keyReleased(KeyEvent arg0) {}
			@Override
			public void keyTyped(KeyEvent arg0) {}
			
		});
		
		this.setContentPane(panel);
		this.pack();
		
		this.initialiceGame();

		this.setVisible(true);
	}
	
	private void initialiceGame(){
		map = new int[WIDTH/20][HEIGHT/20];
		for(int i=0; i<map.length; i++)
			for(int j=0; j<map[i].length; j++)
				if(i!=0 && i!=map.length-1
				&& j!=0 && j!=map[i].length-1)
					map[i][j] = 0;
				else
					map[i][j] = 1;
	}
	
	private void paintScene(Graphics g){
		for(int i=0; i<map.length; i++)
			for(int j=0; j<map[i].length; j++){
				if(map[i][j] == 0)
					g.setColor(Color.WHITE);
				else
					g.setColor(Color.BLACK);
				g.fillRect(i*20,j*20, 20,20);
			}
	}
	
	/** TODO:
	 *  -Add enemies (abstract base class, inherited classes with IA)
	 *  -Add player
	 *  -Game moves on player move (key listeners, ¿mouse listeners?)
	 */
	
	
	public static void main(String[] args){
		new RogueLikeGame();
	}
}

