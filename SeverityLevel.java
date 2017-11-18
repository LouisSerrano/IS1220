package core;

public enum SeverityLevel {
	L1("RESUSCITATION"),
	L2("EMERGENCY"),
	L3("URGENT"),
	L4("LESS URGENT"),
	L5("NON-URGENT");
	
	public ProbabilityDistribution distribution;
	public String level;
	
	private SeverityLevel(String level){
		this.level=level;
		this.setDistribution(distribution);
		
	}
	
	public String getLevel() {
		return level;
	}
	public ProbabilityDistribution getDistribution() {
		return distribution;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public void setDistribution(ProbabilityDistribution distribution) {
		this.distribution = distribution;
	}
}
