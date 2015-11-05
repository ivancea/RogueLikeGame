package es.hol.ivancea;

import java.util.LinkedList;
import java.util.List;

public class PlayerActions {
	public List<Action> actions;
	
	public PlayerActions(){
		actions = new LinkedList<Action>();
	}
	
	public void add(ActionType type, Object data){
		actions.add(new Action(type,data));
	}
	public void add(ActionType type){
		actions.add(new Action(type,null));
	}
	
	public Action last(){
		if(actions.size()>0)
			return actions.get(actions.size()-1);
		return new Action(ActionType.ERROR, null);
	}
	
	
	public class Action{
		public ActionType type;
		public Object data;
		
		public Action(ActionType type, Object data){
			this.type = type;
			this.data = data;
		}
		public Action(ActionType type){
			this.type = type;
			this.data = null;
		}
	}
	
	public enum ActionType{
		ERROR,
		MOVE
	}
}
