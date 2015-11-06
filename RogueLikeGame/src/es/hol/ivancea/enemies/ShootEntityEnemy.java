package es.hol.ivancea.enemies;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import es.hol.ivancea.Enemy;
import es.hol.ivancea.Player;
import es.hol.ivancea.PlayerActions;
import es.hol.ivancea.RogueLikeGame.MapZone;
import es.hol.ivancea.Utils;
import es.hol.ivancea.Utils.Direction;

public class ShootEntityEnemy extends Enemy { // TODO: Create entity type?
	public Direction direction;

	public ShootEntityEnemy(int x, int y, Direction dir){
		pos = new Point(x,y);
		life = 4;
		this.direction = dir;
	}
	
	@Override
	public List<LogicEvent> logic(MapZone[][] map, PlayerActions playerActions, Player player, List<Enemy> enemies){
		List<LogicEvent> events = new ArrayList<LogicEvent>();
		
		switch(Utils.tryMove(pos, map, direction, MapZone.ENEMY)){
		case WALL:
		case ENEMY:
			events.add(new LogicEvent(LogicEventType.DEAD));
			break;
		case PLAYER:
			player.life -= 1;
			events.add(new LogicEvent(LogicEventType.DAMAGE_PLAYER));
			events.add(new LogicEvent(LogicEventType.DEAD));
			break;
		default:
			break;
		}
		return events;
	}
	
	@Override
	public void draw(Graphics g){
		g.setColor(Color.YELLOW);
		g.fillRect(pos.x*20+5,pos.y*20+5, 10,10);
	}
}
