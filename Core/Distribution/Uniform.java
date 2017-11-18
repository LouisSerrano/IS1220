package core;

public class Uniform implements ProbabilityDistribution{
	public double l;
	public double h;
	
	public Uniform(double l, double h){
		this.l=l;
		this.h=h;
	}
	
	//Renvoie un temps t de manière uniforme entre l et h
	public int generateSample(){
		double r= Math.random();
		return (int)(Math.round(r*(this.h-this.l)+this.l)) ;
		
	}

	
	

}
