package es.hol.ivancea;

import java.awt.Graphics;

public abstract class Enemy {
	public int x, y;
	
	public boolean move(int[][] map, Player player){ // Return true if enemy "dies"
		return false;
	}
	
	public void draw(Graphics g){
	}
}
