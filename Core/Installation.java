package core;

import core.distribution.*;

public class Installation extends HealthService {
	
	public Installation(double cost, ProbabilityDistribution distribution) {
		super("INSTALLATION", cost, distribution);
	}

}
