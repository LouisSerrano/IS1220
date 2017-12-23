package core.healthservice;

import core.distribution.ProbabilityDistribution;

public class Xray extends HealthService {

	/**
	 * Private constructor
	 * @param cost
	 * @param distribution
	 */
	private Xray (double cost, ProbabilityDistribution distribution) {
		super("XRAY", cost, distribution);
	}
	
	private static double cost;
	
	private static ProbabilityDistribution distribution;
	
	/**
	 * Pre-initialised unique instance
	 */
	private static Xray INSTANCE = new Xray(cost, distribution);
	
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
	 * To get the unique instance of XRay
	 * @return
	 */
	public static Xray getXRayInstance() {
		return INSTANCE;
	}
	
}