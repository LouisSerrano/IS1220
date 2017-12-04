package core;

public class Consultation extends HealthService {

	public Consultation(double cost, ProbabilityDistribution distribution) {
		super("Consultation", cost, distribution);
	}
	
	public Consultation() {
		super();
	}

}
