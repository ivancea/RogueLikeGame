package es.hol.ivancea;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.imageio.ImageIO;


public class Player {
	
	public int x,y;
	private int spriteState;
	private Image sprite;
	
	public Player(int x, int y){
		this.x = x;
		this.y = y;
		this.spriteState = 0;
		try{
			this.sprite = ImageIO.read(RogueLikeGame.class.getClassLoader().getResourceAsStream("resources/sprites/player_temp_sprite.png"));
		}catch(Exception e){
			System.err.println(e.getMessage());
			sprite = null;
		}
		if(this.sprite==null)
			System.err.println("Error loading sprite");
	}
	
	public void move(MoveDirection mov){
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
			g.drawImage(sprite, x*20,y*20, (x+1)*20,(y+1)*20, initialImageX,0, initialImageX+20, 20, null);
			//g.drawImage(sprite, x*20,y*20, (x+1)*20,(y+1)*20, (spriteState/20%2)*20,0, (spriteState/20%2+1)*20, 20, null);
		}else{
			g.setColor(Color.BLACK);
			g.drawRect(x*20,y*20, 19,19);
			g.drawLine(x*20,y*20, (x+1)*20-1,(y+1)*20-1);
			g.drawLine((x+1)*20-1,y*20, x*20,(y+1)*20-1);
		}
	}
	
	public enum MoveDirection{
		TOP,
		BOTTOM,
		LEFT,
		RIGHT
	}
}
