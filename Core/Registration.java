package core;

import core.distribution.*;

/**
 * Registration is a Singleton that extends the class HealthService.
 * It has a unique instance, unique cost and unique distribution.
 *
 */
public class Registration extends HealthService {

	/**
	 * Private constructor
	 * @param cost
	 * @param distribution
	 */
	private Registration (double cost, ProbabilityDistribution distribution) {
		super("REGISTRATION", cost, distribution);
	}
	
	private static ProbabilityDistribution distribution;
	
	/**
	 * Pre-initialised unique instance
	 */
	private static Registration INSTANCE = new Registration(0.0, distribution);
	
	/**
	 * To let the user set the ProbabilityDistribution of the unique instance
	 * @param distrib
	 */
	public void setDistribution(ProbabilityDistribution distrib){
		distribution = distrib;
	}
	
	/**
	 * To get the unique instance of Registration
	 * @return
	 */
	public Registration getInstance() {
		return INSTANCE;
	}
	
}
