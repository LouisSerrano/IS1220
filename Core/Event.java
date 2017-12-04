package is1220.projectSimErgy.core;

import java.util.ArrayList;

public abstract class Event {

	private String type;
	private Patient patient;
	private int startingTime;
	private int endingTime;
	private int duration;
	private ArrayList<HumanResource> humanResourceReq;
	private ArrayList<Room> roomReq;
	
	public Event(){
		
	}
	
	public boolean checkResources() {
	}

	public void startEvent() {
		this.patient.setCurrentEvent(this);
	}
	
	public void endEvent() {
		
	}
}
