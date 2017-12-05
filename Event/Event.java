package Event;

import Core.EmergencyDepartment;

public abstract class Event{
	private EventType name;
	private int timeStamp;
	
public Event(EventType name, int timeStamp){
	this.name=name;
	this.timeStamp=timeStamp;
}


public void execute(EmergencyDepartment system){
	system.setSimTme(this.timeStamp);
}


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
