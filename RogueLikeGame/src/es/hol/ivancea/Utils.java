package es.hol.ivancea;

import java.awt.Point;

import es.hol.ivancea.GameData.MapZone;

public abstract class Utils {
	
	public enum Direction{
		UP,
		DOWN,
		LEFT,
		RIGHT
	}
	
	public static boolean canMove(Point pos, GameData game, Direction dir){
		Point nextPos = new Point(pos);
		switch(dir){
		case UP:
			nextPos.y -= 1;
			break;
		case DOWN:
			nextPos.y += 1;
			break;
		case LEFT:
			nextPos.x -= 1;
			break;
		case RIGHT:
			nextPos.x += 1;
			break;
		}
		boolean found = false;
		for(int i=0; i<game.enemies.size(); i++)
			if(nextPos.equals(game.enemies.get(i).pos)){
				found = true;
				break;
			}
		if(!found)
			for(int i=0; i<game.entities.size(); i++)
				if(nextPos.equals(game.entities.get(i).pos)){
					found = true;
					break;
				}
		
		return !(game.map[nextPos.x][nextPos.y]!=MapZone.NONE || game.player.pos.equals(nextPos) || found);
	}
	
	public static void move(Point pos, GameData game, Direction dir){
		game.map[pos.x][pos.y] = MapZone.NONE;
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
	}
	
	public static boolean tryMove(Point pos, GameData game, Direction dir){
		if(canMove(pos, game, dir)){
			move(pos, game, dir);
			return true;
		}
		return false;
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
