package core;

import core.distribution.*;

public class Blood extends HealthService {
	
	public Blood(double cost, ProbabilityDistribution distribution) {
		super("BLOOD", cost, distribution);
	}

}
