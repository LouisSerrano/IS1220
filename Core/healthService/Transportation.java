package core.healthService;

import core.distribution.*;

/**
 * Transportation is a Singleton that extends the class HealthService.
 * It has a unique instance, unique cost and unique distribution.
 *
 */
public class Transportation extends HealthService {

	/**
	 * Private constructor
	 * @param cost
	 * @param distribution
	 */
	private Transportation (double cost, ProbabilityDistribution distribution) {
		super("TRANSPORTATION", cost, distribution);
	}
	
	private static ProbabilityDistribution distribution;
	
	/**
	 * Pre-initialised unique instance
	 */
	private static Transportation INSTANCE = new Transportation(0.0, distribution);
	
	/**
	 * To let the user set the ProbabilityDistribution of the unique instance
	 * @param distrib
	 */
	public void setDistribution(ProbabilityDistribution distrib){
		distribution = distrib;
	}
	
	/**
	 * To get the unique instance of Transportation
	 * @return
	 */
	public Transportation getInstance() {
		return INSTANCE;
	}
	
}
