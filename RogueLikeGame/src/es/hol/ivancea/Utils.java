package es.hol.ivancea;

import java.awt.Point;

import es.hol.ivancea.RogueLikeGame.MapZone;

public abstract class Utils {
	
	public enum Direction{
		UP,
		DOWN,
		LEFT,
		RIGHT
	}
	
	public static boolean canMove(Point pos, MapZone[][] map, Direction dir){
		switch(dir){
		case UP:
			if(map[pos.x][pos.y-1] == MapZone.NONE)
				return true;
			break;
		case DOWN:
			if(map[pos.x][pos.y+1] == MapZone.NONE)
				return true;
			break;
		case LEFT:
			if(map[pos.x-1][pos.y] == MapZone.NONE)
				return true;
			break;
		case RIGHT:
			if(map[pos.x+1][pos.y] == MapZone.NONE)
				return true;
			break;
		}
		return false;
	}
	
	public static void move(Point pos, MapZone[][] map, Direction dir, MapZone type){
		map[pos.x][pos.y] = MapZone.NONE;
		switch(dir){
		case UP:
			pos.y -= 1;
			break;
		case DOWN:
			pos.y += 1;
			break;
		case LEFT:
			pos.x -= 1;
			break;
		case RIGHT:
			pos.x += 1;
			break;
		}
		map[pos.x][pos.y] = type;
	}
	
	public static boolean tryMove(Point pos, MapZone[][] map, Direction dir, MapZone type){
		if(canMove(pos, map, dir)){
			move(pos, map, dir, type);
			return true;
		}
		return false;
	}
	
	
}
