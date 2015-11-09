package es.hol.ivancea;


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