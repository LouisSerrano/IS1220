package event;

import core.EmergencyDepartment;

public abstract class Event implements Comparable<Event>{
	private EventType name;
	private int timeStamp;
	
public Event(EventType name, int timeStamp){
	this.name=name;
	this.timeStamp=timeStamp;
}


public abstract void execute(EmergencyDepartment system);


public EventType getName() {
	return name;
}


public int getTimeStamp() {
	return timeStamp;
}


public void setName(EventType name) {
	this.name = name;
}


public void setTimeStamp(int timeStamp) {
	this.timeStamp = timeStamp;
}

public int compareTo(Event other){
	return Integer.compare(this.timeStamp,other.timeStamp);
	
}


}
