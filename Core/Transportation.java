package core;

import core.distribution.*;

public class Transportation extends HealthService {
	
	public Transportation(double cost, ProbabilityDistribution distribution) {
		super("TRANSPORTATION", cost, distribution);
	}

}
