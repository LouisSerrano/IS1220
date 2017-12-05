package core.healthService;

import core.distribution.*;

/**
 * XRay is a Singleton that extends the class HealthService.
 * It has a unique instance, unique cost and unique distribution.
 *
 */
public class XRay extends HealthService {

	/**
	 * Private constructor
	 * @param cost
	 * @param distribution
	 */
	private XRay (double cost, ProbabilityDistribution distribution) {
		super("XRAY", cost, distribution);
	}
	
	private static double cost;
	
	private static ProbabilityDistribution distribution;
	
	/**
	 * Pre-initialised unique instance
	 */
	private static XRay INSTANCE = new XRay(cost, distribution);
	
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
	public static XRay getXRayInstance() {
		return INSTANCE;
	}
	
}

