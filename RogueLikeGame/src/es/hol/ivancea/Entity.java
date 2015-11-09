package es.hol.ivancea;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public abstract class Entity {
	public Point pos;
	
	public List<LogicEvent> logic(GameData game){ // Return true if enemy "dies"
		return new ArrayList<LogicEvent>();
	}
	
	public void draw(Graphics g){
	}
}
