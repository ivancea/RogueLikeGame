package es.hol.ivancea;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import es.hol.ivancea.PlayerActions.ActionType;
import es.hol.ivancea.Utils.Direction;
import es.hol.ivancea.enemies.RandomMoveEnemy;

public class RogueLikeGame extends JFrame {
	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 800,
							HEIGHT = 600;
	
	private MapZone[][] map;
	
	JFrame frame = this;
	
	private PlayerActions playerActions;
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
				Direction dir = null;
				switch(arg.getKeyCode()){
				case KeyEvent.VK_ESCAPE:
					frame.dispose();
					System.exit(0);
					break;
				case KeyEvent.VK_UP:
				case KeyEvent.VK_W:
					dir = Direction.UP;
					break;
				case KeyEvent.VK_DOWN:
				case KeyEvent.VK_S:
					dir = Direction.DOWN;
					break;
				case KeyEvent.VK_LEFT:
				case KeyEvent.VK_A:
					dir = Direction.LEFT;
					break;
				case KeyEvent.VK_RIGHT:
				case KeyEvent.VK_D:
					dir = Direction.RIGHT;
					break;
				}
				if(dir != null){
					switch(Utils.canMove(player.pos, map, dir)){
					case ENEMY:
						Point p = Utils.getNextPos(player.pos, dir);
						for(int i=0; i<enemies.size(); i++){
							if(enemies.get(i).pos.equals(p)){
								enemies.remove(i);
								break;
							}
						}
					case NONE:
						Utils.move(player.pos, map, dir, MapZone.PLAYER);
						playerActions.add(ActionType.MOVE, dir);
						player.moved(null);
						logic();
						break;
					default:
						break;
					}
				}
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
		playerActions = new PlayerActions();
		player = new Player(7,7);
		
		enemies = new ArrayList<Enemy>();
		
		map = new MapZone[WIDTH/20][HEIGHT/20];
		for(int i=0; i<map.length; i++)
			for(int j=0; j<map[i].length; j++)
				if(i!=0 && i!=map.length-1
				&& j!=0 && j!=map[i].length-1)
					map[i][j] = MapZone.NONE;
				else
					map[i][j] = MapZone.WALL;
		for(int i=0; i<map.length; i++)
			for(int j=0; j<map[i].length; j++)
				if((player.pos.x != i || player.pos.y != j)
				&& (i==19 || i==20 || j==14 || j==15)
				&& i!=30 && i!=29 && j!=21 && j!=22 && i!=9 && i!=10 && j!=7 && j!=8)
					map[i][j] = MapZone.WALL;
		enemies.add(new RandomMoveEnemy(5,5));
		map[5][5] = MapZone.ENEMY;
		enemies.add(new RandomMoveEnemy(25,25));
		map[25][25] = MapZone.ENEMY;
		enemies.add(new RandomMoveEnemy(5,25));
		map[5][25] = MapZone.ENEMY;
		enemies.add(new RandomMoveEnemy(25,5));
		map[25][5] = MapZone.ENEMY;
		enemies.add(new RandomMoveEnemy(7,6));
		map[5][5] = MapZone.ENEMY;
	}
	
	private void paintScene(Graphics g){
		for(int i=0; i<map.length; i++)
			for(int j=0; j<map[i].length; j++){
				switch(map[i][j]){
				case NONE:
				case PLAYER:
				case ENEMY:
					g.setColor(Color.WHITE);
					break;
				case WALL:
					g.setColor(Color.BLACK);
					break;
				}
				g.fillRect(i*20,j*20, 20,20);
			}
		for(int i=0; i<enemies.size(); i++)
			enemies.get(i).draw(g);
		player.draw(g);
	}
	
	private void logic(){
		for(int i=0; i<enemies.size(); i++)
			enemies.get(i).logic(map, playerActions, player);
	}
	
	public enum MapZone{ // Temporal
		NONE,
		WALL,
		ENEMY,
		PLAYER
	}
	
	
	public static void main(String[] args){
		new RogueLikeGame();
	}
}

