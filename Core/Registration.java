package core;

import core.distribution.*;

public class Registration extends HealthService {

	public Registration (double cost, ProbabilityDistribution distribution) {
		super("REGISTRATION", cost, distribution);
	}
}
