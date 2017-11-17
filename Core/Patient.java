package is1220.projectSimErgy.core;

import java.util.Map;

public class Patient {
	
	private int id;
	private String name;
	private String surname;
	private SeverityLevel severity;
	private HealthInsurance insurance;
	private State state;
	private Room location;
	private Map<Event, Time> history;
	private Time arrivalTime;
	private int totalCharges;
	
	public void changeState() {}
	
	public void updateHistory(Event e, Time t) {
		history.put(e, t);
	}
	
	public void setCharges() {}

	public Time getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(Time arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
}
