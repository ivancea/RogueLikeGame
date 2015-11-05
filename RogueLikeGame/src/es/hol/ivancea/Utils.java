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
	
	public static MapZone canMove(Point pos, MapZone[][] map, Direction dir){
		switch(dir){
		case UP:
			return map[pos.x][pos.y-1];
		case DOWN:
			return map[pos.x][pos.y+1];
		case LEFT:
			return map[pos.x-1][pos.y];
		case RIGHT:
			return map[pos.x+1][pos.y];
		}
		return MapZone.NONE;
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
	
	public static MapZone tryMove(Point pos, MapZone[][] map, Direction dir, MapZone type){
		MapZone t = canMove(pos, map, dir);
		if(t == MapZone.NONE)
			move(pos, map, dir, type);
		return t;
	}
	
	public static Point getNextPos(Point pos, Direction dir){
		Point t = new Point(pos);
		switch(dir){
		case UP:
			t.y -= 1;
			break;
		case DOWN:
			t.y += 1;
			break;
		case LEFT:
			t.x -= 1;
			break;
		case RIGHT:
			t.x += 1;
			break;
		}
		return t;
	}
	
	
}
