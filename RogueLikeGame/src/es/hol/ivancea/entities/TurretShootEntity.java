package es.hol.ivancea.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import es.hol.ivancea.Entity;
import es.hol.ivancea.GameData;
import es.hol.ivancea.GameData.MapZone;
import es.hol.ivancea.LogicEvent;
import es.hol.ivancea.LogicEventType;
import es.hol.ivancea.Utils;
import es.hol.ivancea.Utils.Direction;

public class TurretShootEntity extends Entity { // TODO: Create entity type?
	public Direction direction;

	public TurretShootEntity(int x, int y, Direction dir){
		pos = new Point(x,y);
		this.direction = dir;
	}
	
	@Override
	public List<LogicEvent> logic(GameData game){
		List<LogicEvent> events = new ArrayList<LogicEvent>();
		
		Utils.move(pos, game, direction);
		if(game.map[pos.x][pos.y] == MapZone.WALL){
			events.add(new LogicEvent(LogicEventType.DESTROY));
		}else if(game.player.pos.equals(pos)){
			game.player.life -= 1;
			events.add(new LogicEvent(LogicEventType.DAMAGE_PLAYER));
			events.add(new LogicEvent(LogicEventType.DESTROY));
		}
		return events;
	}
	
	@Override
	public void draw(Graphics g){
		g.setColor(Color.YELLOW);
		g.fillRect(pos.x*20+5,pos.y*20+5, 10,10);
	}
}
