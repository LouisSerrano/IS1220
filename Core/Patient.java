package core;

import java.util.HashMap;
import java.util.Map;

import event.*;

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
	private PatientState state;
	private Room location;
	private Map<Event, Time> history;
	private Event currentEvent;
	private Time arrivalTime;
	private double totalCharges;
	
	public Patient(String name, String surname, SeverityLevel severity, Time arrivalTime){
		Patient.patientCounter++;
		this.id = Patient.patientCounter;
		this.name = name;
		this.surname = surname;
		this.severity = severity;
		this.arrivalTime = arrivalTime;
		this.totalCharges = 0;
		this.state = PatientState.WAITING_REGISTRATION;
		this.history = new HashMap<Event, Time>();
	}
	
	public Patient(SeverityLevel severity, Time arrivalTime){
		Patient.patientCounter++;
		this.id = Patient.patientCounter;
		this.severity = severity;
		this.arrivalTime = arrivalTime;
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

	public String toString() {
		return "Patient : [id : " + id + ", name : " + name + ", surname : " + surname + ", Severity Level : "
				 + severity + ", Insurance : " + insurance + ", STATE : " + state + ", LOCATION : " + 
				location + ", TOTAL CHARGES : " + totalCharges + "]";
	}

	public Event getCurrentEvent() {
		return currentEvent;
	}

	public void setCurrentEvent(Event currentEvent) {
		this.currentEvent = currentEvent;
	}
	
	public SeverityLevel getSeverityLevel() {
		return this.severity;
	}
	
	public PatientState getPatientState() {
		return this.state;
	}
}

