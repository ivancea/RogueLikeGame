package es.hol.ivancea;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import es.hol.ivancea.GameData.MapZone;
import es.hol.ivancea.PlayerActions.ActionType;
import es.hol.ivancea.Utils.Direction;
import es.hol.ivancea.enemies.RandomMoveEnemy;
import es.hol.ivancea.enemies.TurretEnemy;

public class RogueLikeGame extends JFrame {
	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 800,
							HEIGHT = 600;
	
	JFrame frame = this;
	
	GameData game;
	
	private JPanel panel;
	
	public RogueLikeGame(){
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		
		panel = new JPanel(){
			private static final long serialVersionUID = 1L;
			
			@Override
			public void paint(Graphics g){
				g.clearRect(0, 0, this.getWidth(), this.getHeight());
				game.draw(g);
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
					if(Utils.canMove(game.player.pos, game, dir)){
						Utils.move(game.player.pos, game, dir);
						game.playerActions.add(ActionType.MOVE, dir);
						game.player.moved(null);
						logic();
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
		game = new GameData(WIDTH/20,HEIGHT/20, 7,7,5);
		for(int i=0; i<game.map.length; i++)
			for(int j=0; j<game.map[i].length; j++)
				if(i!=0 && i!=game.map.length-1
				&& j!=0 && j!=game.map[i].length-1)
					game.map[i][j] = MapZone.NONE;
				else
					game.map[i][j] = MapZone.WALL;
		for(int i=0; i<game.map.length; i++)
			for(int j=0; j<game.map[i].length; j++)
				if((game.player.pos.x != i || game.player.pos.y != j)
				&& (i==19 || i==20 || j==14 || j==15)
				&& i!=30 && i!=29 && j!=21 && j!=22 && i!=9 && i!=10 && j!=7 && j!=8)
					game.map[i][j] = MapZone.WALL;
		game.addEnemy(new RandomMoveEnemy(5,5));
		game.addEnemy(new RandomMoveEnemy(25,5));
		game.addEnemy(new TurretEnemy(7,6, Direction.UP, 5));
		game.addEnemy(new TurretEnemy(17,6, Direction.LEFT, 2));
		game.addEnemy(new TurretEnemy(21,25, Direction.RIGHT, 1));
		game.addEnemy(new TurretEnemy(8,20, Direction.DOWN, 0));
	}
	
	private void logic(){
		for(int i=0; i<game.enemies.size(); i++){
			List<LogicEvent> events = game.enemies.get(i).logic(game);
			boolean destroyed = false;
			if(events != null){
				for(int j=0; j<events.size(); j++){
					LogicEvent ev = events.get(j);
					switch(ev.type){
					case DESTROY:
						if(!destroyed){
							game.map[game.enemies.get(i).pos.x][game.enemies.get(i).pos.y] = MapZone.NONE;
							game.enemies.remove(i);
							--i;
							destroyed = true;
						}
						break;
					case DAMAGE_PLAYER:
						System.out.println("New player life: " + game.player.life);
						break;
					case DAMAGE_ENEMY:
						Enemy enemy = (Enemy)ev.data;
						if(enemy.life <= 0)
							if(game.enemies.remove(enemy))
								--i;
						break;
					case ADD_ENEMY:
						game.addEnemy((Enemy)ev.data, 0);
						++i;
						break;
					case ADD_ENTITY:
						game.addEntity((Entity)ev.data, 0);
						break;
					default:
						break;
					}
				}
			}
		}
		for(int i=0; i<game.entities.size(); i++){
			List<LogicEvent> events = game.entities.get(i).logic(game);
			boolean destroyed = false;
			if(events != null){
				for(int j=0; j<events.size(); j++){
					LogicEvent ev = events.get(j);
					switch(ev.type){
					case DESTROY:
						if(!destroyed){
							game.entities.remove(i);
							--i;
							destroyed = true;
						}
						break;
					case DAMAGE_PLAYER:
						System.out.println("New player life: " + game.player.life);
						break;
					case DAMAGE_ENEMY:
						Enemy enemy = (Enemy)ev.data;
						if(enemy.life <= 0)
							if(game.entities.remove(enemy))
								--i;
						break;
					case ADD_ENEMY:
						game.addEnemy((Enemy)ev.data, 0);
						++i;
						break;
					case ADD_ENTITY:
						game.addEntity((Entity)ev.data, 0);
						break;
					default:
						break;
					}
				}
			}
		}
	}
	
	
	public static void main(String[] args){
		new RogueLikeGame();
	}
}

