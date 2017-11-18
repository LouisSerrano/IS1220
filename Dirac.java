package core;

public class Dirac implements ProbabilityDistribution {
	public double delta;
	
	public Dirac(double delta){
		this.delta=delta;
	}
	
	
	public int generateSample(){
		return (int)(Math.round(this.delta));
	} 
	
	
}

