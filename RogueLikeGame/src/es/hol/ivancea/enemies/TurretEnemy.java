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

public class TurretEnemy extends Enemy {
	
	public Direction direction;
	public int delay;
	
	private int count;

	public TurretEnemy(int x, int y, Direction dir, int delay){
		pos = new Point(x,y);
		life = 4;
		this.direction = dir;
		this.delay = delay;
		if(delay<0)
			this.delay = 0;
	}
	
	@Override
	public List<LogicEvent> logic(MapZone[][] map, PlayerActions playerActions, Player player, List<Enemy> enemies){
		List<LogicEvent> events = new ArrayList<LogicEvent>();
		if(count <= 0){
			Point pos = Utils.getNextPos(this.pos, direction);
			if(map[pos.x][pos.y] == MapZone.NONE){
				events.add(new LogicEvent(LogicEventType.ADD_ENEMY, new ShootEntityEnemy(pos.x, pos.y, direction)));
			}else if (map[pos.x][pos.y] == MapZone.PLAYER){
				player.life -= 1;
				events.add(new LogicEvent(LogicEventType.DAMAGE_PLAYER));
			}
			count = delay;
		}else --count;
		return events;
	}
	
	@Override
	public void draw(Graphics g){
		g.setColor(Color.GREEN);
		g.fillRect(pos.x*20,pos.y*20, 20,20);
	}
}
