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

public class RandomMoveEnemy extends Enemy {
	public RandomMoveEnemy(int x, int y){
		pos = new Point(x,y);
		life = 4;
	}
	
	@Override
	public List<LogicEvent> logic(MapZone[][] map, PlayerActions playerActions, Player player, List<Enemy> enemies){
		List<LogicEvent> events = new ArrayList<LogicEvent>();
		int n = (int)(Math.random()*5);
		if(n<4)
			Utils.tryMove(this.pos, map, Direction.values()[n], MapZone.ENEMY);
		return events;
	}
	
	@Override
	public void draw(Graphics g){
		g.setColor(Color.RED);
		g.drawRect(pos.x*20,pos.y*20, 20,20);
		g.drawLine(pos.x*20,pos.y*20, pos.x*20+20,pos.y*20+20);
		g.drawLine(pos.x*20+20,pos.y*20, pos.x*20,pos.y*20+20);
	}
}
