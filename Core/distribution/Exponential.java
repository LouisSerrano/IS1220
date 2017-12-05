package core.distribution;

public class Exponential implements ProbabilityDistribution {
	public double lambda;
	
	Exponential(double lambda){
		this.lambda=lambda;
		
	}
	
	//Renvoie un temps t tel qu'un nouveau patient arrive avant le temps t. On considère alors que le nouveau Patient arrive au temps exact t même s'il a pu arrivé entre 0 et t.
	public int generateSample(){
		double r=Math.random();
		return (int)(Math.round(-Math.log(1-r)/this.lambda));
	}

	
	
}
