package is1220.projectSimErgy.core;

import java.util.Map;

public class Patient {
	
	/**
	 * counts the total number of patients admitted in the ED
	 */
	private static int patientCounter = 0;
	
	private int id;
	private String name;
	private String surname;
	private SeverityLevel severity;
	private HealthInsurance insurance;
	private State state;
	private Room location;
	private Map<Event, Time> history;
	private Time arrivalTime;
	private double totalCharges;
	
	public Patient(String name, String surname, SeverityLevel severity){
		Patient.patientCounter++;
		this.id = Patient.patientCounter;
		this.name = name;
		this.surname = surname;
		this.severity = severity;
		this.totalCharges = 0;
		this.state = State.waiting;
	}
	
	public void changeState() {}
	
	public void updateHistory(Event e, Time t) {
		history.put(e, t);
	}
	
	public void updateCharges(HealthService service) {
		this.totalCharges = totalCharges + service.getCost()*(1 - insurance.getDiscount());
	}

	public Time getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(Time arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public HealthInsurance getInsurance() {
		return insurance;
	}

	public void setInsurance(HealthInsurance insurance) {
		this.insurance = insurance;
	}
	
	public double getTotalCharges() {
		return totalCharges;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

}
