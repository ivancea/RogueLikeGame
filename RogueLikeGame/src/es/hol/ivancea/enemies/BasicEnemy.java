package es.hol.ivancea.enemies;

import java.awt.Color;
import java.awt.Graphics;

import es.hol.ivancea.Enemy;
import es.hol.ivancea.Player;

public class BasicEnemy extends Enemy {
	public BasicEnemy(int x, int y){
		this.x=x;
		this.y=y;
	}
	
	@Override
	public boolean move(int[][] map, Player player){
		int n = (int)(Math.random()*5);
		switch(n){
		case 0:
			if(map[x][y-1] == 0 && x!=player.x && y-1!=player.y){
				map[x][y] = 0;
				y -= 1;
				map[x][y] = 2;
			}
			break;
		case 1:
			if(map[x][y+1] == 0 && x!=player.x && y+1!=player.y){
				map[x][y] = 0;
				y += 1;
				map[x][y] = 2;
			}
			break;
		case 2:
			if(map[x-1][y] == 0 && x-1!=player.x && y!=player.y){
				map[x][y] = 0;
				x -= 1;
				map[x][y] = 2;
			}
			break;
		case 3:
			if(map[x+1][y] == 0 && x+1!=player.x && y!=player.y){
				map[x][y] = 0;
				x += 1;
				map[x][y] = 2;
			}
			break;
		default:
			break;
		}
		return false;
	}
	
	@Override
	public void draw(Graphics g){
		g.setColor(Color.RED);
		g.fillRect(x*20,y*20, 20,20);
	}
}
