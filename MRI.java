package core;

import core.distribution.*;

public class MRI extends HealthService {
	
	public MRI(double cost, ProbabilityDistribution distribution) {
		super("MRI", cost, distribution);
	}

}
