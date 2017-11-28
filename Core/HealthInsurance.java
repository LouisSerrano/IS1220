package is1220.projectSimErgy.core;

public enum HealthInsurance {
	
	none(0.0),
	silver(0.5),
	gold(0.8);
	
	private double discount;

	HealthInsurance(double discount){
		this.discount = discount;
	}

	public double getDiscount() {
		return this.discount;
	}
}
