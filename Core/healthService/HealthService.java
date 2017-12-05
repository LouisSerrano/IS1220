package core.healthService;

import java.util.ArrayList;

import core.*;
import core.distribution.ProbabilityDistribution;

public abstract class HealthService {
	
	private String type;
	private ArrayList<Patient> waitingQueue;
	private ProbabilityDistribution distribution;
	private double cost;
	
	/**
	 * Instantiates a Health service object with given type, cost and probability distribution
	 * @param type
	 * @param cost 
	 * @param distribution
	 */
	public HealthService(String type, double cost, ProbabilityDistribution distribution) {
		this.type = type;
		this.cost = cost;
		this.distribution = distribution;
		this.waitingQueue = new ArrayList<Patient>();
	}
	
	public HealthService() {
		this.waitingQueue = new ArrayList<Patient>();
	}
	
	/**
	 * places the Patient in the waiting queue according to its Severity Level
	 * @param p Patient
	 */
	public void addPatientToQueue(Patient p){
		int severity = Integer.parseInt(p.getSeverityLevel().getLevel());
		if (this.waitingQueue.isEmpty()){
			this.waitingQueue.add(p);
		}
		else {
			browsingQueue:
			for (Patient patient : this.waitingQueue){
				//compares the severity levels of patients
				int index = this.waitingQueue.indexOf(patient);
				if (severity > Integer.parseInt(patient.getSeverityLevel().getLevel())){
						this.waitingQueue.add(index, p);
						break browsingQueue;
				}
			}
		}
	}
	
	public double getCost() {
		return this.cost;
	}

	public ArrayList<Patient> getWaitingQueue() {
		return this.waitingQueue;
	}
	public String toString() {
		return "Health Service : [type : " + type + ", cost : " + cost + ", distribution : " + distribution
				+ ", waiting queue : " + waitingQueue + "]";
	}
}
