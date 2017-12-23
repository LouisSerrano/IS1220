package core.healthservice;

import core.distribution.ProbabilityDistribution;

public class Installation extends HealthService {

	/**
	 * Private constructor
	 * @param cost
	 * @param distribution
	 */
	private Installation (double cost, ProbabilityDistribution distribution) {
		super("INSTALLATION", cost, distribution);
	}
	
	private static ProbabilityDistribution distribution;
	
	/**
	 * Pre-initialised unique instance
	 */
	private static Installation INSTANCE = new Installation(0.0, distribution);
	
	/**
	 * To let the user set the ProbabilityDistribution of the unique instance
	 * @param distrib
	 */
	public void setDistribution(ProbabilityDistribution distrib){
		distribution = distrib;
	}
	
	
	/**
	 * To get the unique instance of Installation
	 * @return
	 */
	public static Installation getInstallationInstance() {
		return INSTANCE;
	}
	
}