package es.hol.ivancea;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import es.hol.ivancea.enemies.BasicEnemy;

public class RogueLikeGame extends JFrame {
	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 800,
							HEIGHT = 600;
	
	private int[][] map;
	
	private Player player;
	private List<Enemy> enemies;
	
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
			public void keyPressed(KeyEvent arg) {
				switch(arg.getKeyCode()){
				case KeyEvent.VK_UP:
				case KeyEvent.VK_W:
					if(map[player.x][player.y-1] == 0)
						player.y -= 1;
					break;
				case KeyEvent.VK_DOWN:
				case KeyEvent.VK_S:
					if(map[player.x][player.y+1] == 0)
						player.y += 1;
					break;
				case KeyEvent.VK_LEFT:
				case KeyEvent.VK_A:
					if(map[player.x-1][player.y] == 0)
						player.x -= 1;
					break;
				case KeyEvent.VK_RIGHT:
				case KeyEvent.VK_D:
					if(map[player.x+1][player.y] == 0)
						player.x += 1;
					break;
				}
				player.move(null);
				logic();
			}
			@Override
			public void keyReleased(KeyEvent arg) {}
			@Override
			public void keyTyped(KeyEvent arg) {}
			
		});
		
		this.setContentPane(panel);
		this.pack();
		
		this.initialiceGame();
		
		Timer timer = new Timer(20, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg) {
				panel.repaint();
			}
		});
		timer.start();

		this.setVisible(true);
	}
	
	private void initialiceGame(){
		player = new Player(7,7);
		
		enemies = new ArrayList<Enemy>();
		
		map = new int[WIDTH/20][HEIGHT/20];
		for(int i=0; i<map.length; i++)
			for(int j=0; j<map[i].length; j++)
				if(i!=0 && i!=map.length-1
				&& j!=0 && j!=map[i].length-1)
					map[i][j] = 0;
				else
					map[i][j] = 1;
		for(int i=0; i<map.length; i++)
			for(int j=0; j<map[i].length; j++)
				if((player.x != i || player.y != j)
				&& (i==19 || i==20 || j==14 || j==15)
				&& i!=30 && i!=29 && j!=21 && j!=22 && i!=9 && i!=10 && j!=7 && j!=8)
					map[i][j] = 1;
		enemies.add(new BasicEnemy(5,5));
		map[5][5] = 2;
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
		for(int i=0; i<enemies.size(); i++)
			enemies.get(i).draw(g);
		player.draw(g);
	}
	
	private void logic(){
		for(int i=0; i<enemies.size(); i++)
			enemies.get(i).move(map, player);
	}
	
	/** TODO:
	 *  -Add enemies (abstract base class, inherited classes with IA)
	 *  -Game moves on player move (key listeners, ¿mouse listeners?)
	 */
	
	
	public static void main(String[] args){
		new RogueLikeGame();
	}
}

