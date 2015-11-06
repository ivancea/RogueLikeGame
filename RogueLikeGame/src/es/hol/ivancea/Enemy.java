package es.hol.ivancea;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import es.hol.ivancea.RogueLikeGame.MapZone;

public abstract class Enemy {
	public Point pos;
	public int life;
	
	public List<LogicEvent> logic(MapZone[][] map, PlayerActions playerActions, Player player, List<Enemy> enemies){ // Return true if enemy "dies"
		return new ArrayList<LogicEvent>();
	}
	
	public void draw(Graphics g){
	}
	
	public class LogicEvent{
		public LogicEventType type;
		public Object data;
		
		public LogicEvent(LogicEventType type, Object data){
			this.type = type;
			this.data = data;
		}
		
		public LogicEvent(LogicEventType type){
			this.type = type;
			this.data = null;
		}
	}
	
	public enum LogicEventType{
		NONE,
		DEAD,
		DAMAGE_PLAYER,
		DAMAGE_ENEMY,
		ADD_ENEMY
	}
}
