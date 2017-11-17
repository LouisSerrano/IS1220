package is1220.projectSimErgy.core;
import java.util.ArrayList;

public abstract class HealthService {
	
	private String type;
	private ArrayList<Patient> waitingQueue;
	private ProbabilityDistribution distribution;
	private double cost;
	
	public HealthService(String type, double cost, ProbabilityDistribution distribution) {
		this.type = type;
		this.cost = cost;
		this.distribution = distribution;
		this.waitingQueue = new ArrayList<Patient>();
	}
	
	public void addPatientToQueue(Patient p){
		this.waitingQueue.add(p);
	}

	public String toString() {
		return "Health Service : [type : " + type + ", cost : " + cost + ", distribution : " + distribution
				+ ", waiting queue : " + waitingQueue + "]";
	}
}
