package core.healthservice;

import core.distribution.ProbabilityDistribution;

public class Registration extends HealthService {

	/**
	 * Private constructor
	 * @param cost
	 * @param distribution
	 */
	private Registration (double cost) {
		super("REGISTRATION", cost, null);
	}
	
	private static ProbabilityDistribution distribution = null;
	
	/**
	 * Pre-initialised unique instance
	 */
	private static Registration INSTANCE = new Registration(0.0);
	
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
	public static Registration getRegistrationInstance() {
		return INSTANCE;
	}
	
}
