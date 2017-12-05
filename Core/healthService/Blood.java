package core.healthService;

import core.distribution.*;

/**
 * Blood is a Singleton that extends the class HealthService.
 * It has a unique instance, unique cost and unique distribution.
 *
 */
public class Blood extends HealthService {

	/**
	 * Private constructor
	 * @param cost
	 * @param distribution
	 */
	private Blood (double cost, ProbabilityDistribution distribution) {
		super("BLOOD", cost, distribution);
	}
	
	private static double cost;
	
	private static ProbabilityDistribution distribution;
	
	/**
	 * Pre-initialised unique instance
	 */
	private static Blood INSTANCE = new Blood(cost, distribution);
	
	/**
	 * To let the user set the ProbabilityDistribution of the unique instance.
	 * @param distrib
	 */
	public void setDistribution(ProbabilityDistribution distrib){
		distribution = distrib;
	}
	
	/**
	 * To let the user set the cost of the unique instance.
	 * @param hSCost
	 */
	public void setCost(double hSCost){
		cost = hSCost;
	}
	
	/**
	 * To get the unique instance of Blood
	 * @return
	 */
	public static Blood getBloodInstance() {
		return INSTANCE;
	}
	
}
