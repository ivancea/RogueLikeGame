package es.hol.ivancea;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.imageio.ImageIO;

import es.hol.ivancea.Utils.Direction;


public class Player {

	static public Image sprite = null;
	
	public Point pos;
	public int life;
	private int spriteState;
	
	public Player(int x, int y, int initialLife){
		this.pos = new Point(x,y);
		this.spriteState = 0;
		this.life = initialLife;
		if(Player.sprite == null){
			try{
				Player.sprite = ImageIO.read(RogueLikeGame.class.getClassLoader().getResourceAsStream("resources/sprites/player_temp_sprite.png"));
			}catch(Exception e){
				System.err.println(e.getMessage());
				Player.sprite = null;
			}
		}
	}
	
	public void moved(Direction mov){
		// TODO: Bad implementation, change it. Just for see sprite animation change
		spriteState = 15;
	}
	
	public void draw(Graphics g){
		if(sprite != null){
			int initialImageX;
			if(spriteState>0){
				spriteState -= 1;
				initialImageX = 20;
			}else
				initialImageX = 0;
			g.drawImage(sprite, pos.x*20,pos.y*20, (pos.x+1)*20,(pos.y+1)*20, initialImageX,0, initialImageX+20, 20, null);
		}else{
			g.setColor(Color.BLACK);
			g.drawRect(pos.x*20,pos.y*20, 19,19);
			g.drawLine(pos.x*20,pos.y*20, (pos.x+1)*20-1,(pos.y+1)*20-1);
			g.drawLine((pos.x+1)*20-1,pos.y*20, pos.x*20,(pos.y+1)*20-1);
		}
	}
}
