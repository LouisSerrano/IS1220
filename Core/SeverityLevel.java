package core;

import core.distribution.ProbabilityDistribution;

public enum SeverityLevel {
	
	L1("1"),
	L2("2"),
	L3("3"),
	L4("4"),
	L5("5");
	
	private String level;
	private ProbabilityDistribution distribution;
	
	SeverityLevel(String level){
		this.level= level;
	}
	SeverityLevel(String level, ProbabilityDistribution distribution){
		this.level = level;
		this.distribution=distribution;
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
	public String getLevel(){
		return this.level;
	}

}
