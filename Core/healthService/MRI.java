package core.healthservice;

import core.distribution.ProbabilityDistribution;

public class MRI extends HealthService {

	/**
	 * Private constructor
	 * @param cost
	 * @param distribution
	 */
	private MRI (double cost, ProbabilityDistribution distribution) {
		super("MRI", cost, distribution);
	}
	
	private static double cost;
	
	private static ProbabilityDistribution distribution;
	
	/**
	 * Pre-initialised unique instance
	 */
	private static MRI INSTANCE = new MRI(cost, distribution);
	
	/**
	 * To let the user set the ProbabilityDistribution of the unique instance.
	 * @param distrib
	 */
	public void setDistribution(ProbabilityDistribution distrib){
		distribution = distrib;
	}
	
	public ProbabilityDistribution getDistribution() {
		return this.distribution;
	}
	
	/**
	 * To let the user set the cost of the unique instance.
	 * @param hSCost
	 */
	public void setCost(double hSCost){
		cost = hSCost;
	}
	
	/**
	 * To get the unique instance of MRI
	 * @return
	 */
	public static MRI getMRIInstance() {
		return INSTANCE;
	}
	
}
