package es.hol.ivancea;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class GameData {
	
	static public Image tempWallSprite = null;
	static public Image tempFloorSprite = null;
	
	public MapZone[][] map;
	
	public Player player;
	public PlayerActions playerActions;
	
	public List<Enemy> enemies;
	public List<Entity> entities;
	
	public enum MapZone{ // Temporal
		NONE,
		WALL
	}
	
	public GameData(int mapWidth,int mapHeight, int playerX,int playerY,int playerLife){
		if(GameData.tempWallSprite == null){
			try{
				GameData.tempWallSprite = ImageIO.read(RogueLikeGame.class.getClassLoader().getResourceAsStream("resources/sprites/wall.png"));
			}catch(Exception e){
				System.err.println(e.getMessage());
				GameData.tempWallSprite = null;
			}
		}
		if(GameData.tempFloorSprite == null){
			try{
				GameData.tempFloorSprite = ImageIO.read(RogueLikeGame.class.getClassLoader().getResourceAsStream("resources/sprites/floor.jpg"));
			}catch(Exception e){
				System.err.println(e.getMessage());
				GameData.tempFloorSprite = null;
			}
		}
		reset(mapWidth,mapHeight, playerX,playerY,playerLife);
	}
	
	public void reset(int mapWidth,int mapHeight, int playerX,int playerY,int playerLife){
		map = new MapZone[mapWidth][mapHeight];
		for(int i=0; i<map.length; i++)
			for(int j=0; j<map[i].length; j++)
				map[i][j] = MapZone.NONE;
		
		player = new Player(7,7, 5);
		playerActions = new PlayerActions();
		
		enemies = new ArrayList<Enemy>();
		entities = new ArrayList<Entity>();
	}
	
	public void draw(Graphics g){
		for(int i=0; i<map.length; i++)
			for(int j=0; j<map[i].length; j++){
				switch(map[i][j]){
				case NONE:
					if(tempFloorSprite!=null)
						g.drawImage(tempFloorSprite, i*20,j*20, (i+1)*20,(j+1)*20, 0,0, 20, 20, null);
					else{
						g.setColor(Color.WHITE);
						g.fillRect(i*20,j*20, 20,20);
					}
					break;
				case WALL:
					if(tempWallSprite!=null)
						g.drawImage(tempWallSprite, i*20,j*20, (i+1)*20,(j+1)*20, 0,0, 20, 20, null);
					else{
						g.setColor(Color.BLACK);
						g.fillRect(i*20,j*20, 20,20);
					}
					break;
				}
			}
		for(int i=0; i<enemies.size(); i++)
			enemies.get(i).draw(g);
		for(int i=0; i<entities.size(); i++)
			entities.get(i).draw(g);
		player.draw(g);
	}
	
	public void addEnemy(Enemy enemy, int position){
		if(position >= enemies.size())
			enemies.add(enemy);
		else if(position < 0)
			enemies.add(0, enemy);
		else enemies.add(position, enemy);
	}
	public void addEnemy(Enemy enemy){
		addEnemy(enemy, enemies.size());
	}
	
	public void addEntity(Entity entity, int position){
		if(position >= entities.size())
			entities.add(entity);
		else if(position < 0)
			entities.add(0, entity);
		else entities.add(position, entity);
	}
	public void addEntity(Entity entity){
		addEntity(entity, enemies.size());
	}
}
