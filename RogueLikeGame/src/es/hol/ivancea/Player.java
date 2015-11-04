package es.hol.ivancea;

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
	
	public void drawSprite(Graphics g){
		if(sprite != null){
			g.drawImage(sprite, x*20,y*20, (x+1)*20,(y+1)*20, (spriteState/20%2)*20,0, (spriteState/20%2+1)*20, 20, null);
			spriteState += 1;
		}
	}
}
