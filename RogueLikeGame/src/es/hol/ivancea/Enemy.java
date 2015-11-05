package es.hol.ivancea;

import java.awt.Graphics;
import java.awt.Point;

import es.hol.ivancea.RogueLikeGame.MapZone;

public abstract class Enemy {
	public Point pos;
	
	public boolean move(MapZone[][] map, PlayerActions playerActions, Player player){ // Return true if enemy "dies"
		return false;
	}
	
	public void draw(Graphics g){
	}
}
