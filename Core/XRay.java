package core;

import core.distribution.*;

public class XRay extends HealthService {
	
	public XRay(double cost, ProbabilityDistribution distribution) {
		super("XRAY", cost, distribution);
	}

}
