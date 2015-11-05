package es.hol.ivancea.enemies;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import es.hol.ivancea.Enemy;
import es.hol.ivancea.Player;
import es.hol.ivancea.PlayerActions;
import es.hol.ivancea.RogueLikeGame.MapZone;
import es.hol.ivancea.Utils;
import es.hol.ivancea.Utils.Direction;

public class RandomMoveEnemy extends Enemy {
	public RandomMoveEnemy(int x, int y){
		pos = new Point(x,y);
	}
	
	@Override
	public boolean logic(MapZone[][] map, PlayerActions playerActions, Player player){
		int n = (int)(Math.random()*5);
		if(n<4)
			Utils.tryMove(this.pos, map, Direction.values()[n], MapZone.ENEMY);
		return false;
	}
	
	@Override
	public void draw(Graphics g){
		g.setColor(Color.RED);
		g.drawRect(pos.x*20,pos.y*20, 20,20);
		g.drawLine(pos.x*20,pos.y*20, pos.x*20+20,pos.y*20+20);
		g.drawLine(pos.x*20+20,pos.y*20, pos.x*20,pos.y*20+20);
	}
}
