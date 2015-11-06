package es.hol.ivancea.enemies;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import es.hol.ivancea.Enemy;
import es.hol.ivancea.Player;
import es.hol.ivancea.PlayerActions;
import es.hol.ivancea.PlayerActions.ActionType;
import es.hol.ivancea.RogueLikeGame.MapZone;
import es.hol.ivancea.Utils;
import es.hol.ivancea.Utils.Direction;

public class BasicEnemy extends Enemy {
	public BasicEnemy(int x, int y){
		pos = new Point(x,y);
		life = 4;
	}
	
	@Override
	public List<LogicEvent> logic(MapZone[][] map, PlayerActions playerActions, Player player, List<Enemy> enemies){
		List<LogicEvent> events = new ArrayList<LogicEvent>();
		if(playerActions.last().type == ActionType.MOVE)
			Utils.tryMove(this.pos, map, (Direction)playerActions.last().data, MapZone.ENEMY);
		return events;
	}
	
	@Override
	public void draw(Graphics g){
		g.setColor(Color.RED);
		g.fillRect(pos.x*20,pos.y*20, 20,20);
	}
}
