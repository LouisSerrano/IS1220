package core;

import core.distribution.*;

public class Consultation extends HealthService {

	public Consultation(double cost, ProbabilityDistribution distribution) {
		super("CONSULTATION", cost, distribution);
	}
	
	public Consultation() {
		super();
	}

}
