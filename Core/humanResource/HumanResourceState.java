package core;

public enum HumanResourceState {
	
	IDLE("idle"),
	VISITING("visiting"),
	OFFDUTY("offduty");
	
	private String state; 
	
	HumanResourceState(String state){
		this.state = state;
	}
	
	public String getHumanResourceState() {
		return this.state;
	}
	
}
