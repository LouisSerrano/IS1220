package core;

import java.util.HashMap;
import java.util.Map;

import core.distribution.ConsultationReqProbability;
import event.*;
import core.room.*;
import core.healthService.*;

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
	private Room room;
	private ConsultationReqProbability decisionFunction;
	private String direction;
	private Map<Event, Integer> history;
	private Event currentEvent;
	private int arrivalTime;
	private double totalCharges;
	private KPI kpi;
	
	public Patient(String name, String surname, SeverityLevel severity, int arrivalTime){
		Patient.patientCounter++;
		this.id = Patient.patientCounter;
		this.name = name;
		this.surname = surname;
		this.severity = severity;
		this.arrivalTime = arrivalTime;
		this.totalCharges = 0;
		this.state = PatientState.WAITING_REGISTRATION;
		this.setDecisionFunction(new ConsultationReqProbability());
		this.history = new HashMap<Event, Integer>();
		this.kpi = new KPI(this);
	}
	
	public Patient(SeverityLevel severity, int arrivalTime){
		Patient.patientCounter++;
		this.id = Patient.patientCounter;
		this.severity = severity;
		this.arrivalTime = arrivalTime;
		this.totalCharges = 0;
		this.state = PatientState.WAITING_REGISTRATION;
		this.setDecisionFunction(new ConsultationReqProbability());
	}	
	
	
	public void updateHistory(Event e, Integer t) {
		history.put(e, t);
	}
	
	public void updateCharges(HealthService service) {
		this.totalCharges = totalCharges + service.getCost()*(1 - insurance.getDiscount());
	}

	public int getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(int arrivalTime) {
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
				room + ", TOTAL CHARGES : " + totalCharges + "]";
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
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
	
	public void setPatientState(PatientState state) {
		this.state = state;
	}
	
	public PatientState getPatientState() {
		return this.state;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}
	
	public Map<Event, Integer> getHistory() {
		return history;
	}
	
	public void setHistory(Map<Event, Integer> history) {
		this.history = history;
	}
	
	public ConsultationReqProbability getDecisionFunction() {
		return decisionFunction;
	}

	public void setDecisionFunction(ConsultationReqProbability decisionFunction) {
		this.decisionFunction = decisionFunction;
	}
	
	public Event searchEvent(EventType eT) {
		for (Event event : this.history.keySet()){
			if (event.getName() == eT.getName()) {
				return event;
			}
		}
		return null;
	}
	
	public KPI getKPI() {
		return this.kpi;
	}
}
