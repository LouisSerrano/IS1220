package is1220.projectSimErgy.core;
import java.util.ArrayList;

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
	
	public void addPatientToQueue(Patient p){
		this.waitingQueue.add(p);
	}
	
	public double getCost() {
		return this.cost;
	}

	public String toString() {
		return "Health Service : [type : " + type + ", cost : " + cost + ", distribution : " + distribution
				+ ", waiting queue : " + waitingQueue + "]";
	}
}
