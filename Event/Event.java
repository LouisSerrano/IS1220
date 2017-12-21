package Event;

import Core.EmergencyDepartment;

public abstract class Event{
	private String name;
	private EventType type;
	private int timeStamp;
	
public Event(String name, EventType type, int timeStamp){
	this.name=name;
	this.type=type;
	this.timeStamp=timeStamp;
}


public EventType getType() {
	return type;
}


public void setType(EventType type) {
	this.type = type;
}


public void execute(EmergencyDepartment system){
	system.setSimTime(this.timeStamp);
}


public String getName() {
	return name;
}


public int getTimeStamp() {
	return timeStamp;
}


public void setName(String name) {
	this.name = name;
}


public void setTimeStamp(int timeStamp) {
	this.timeStamp = timeStamp;
}

public int compareTo(Event other){
	return Integer.compare(this.timeStamp,other.timeStamp);
	
}


}
