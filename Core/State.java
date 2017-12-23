package core;

public enum State {
	
	idle("idle"),
	visiting("visiting"),
	offduty("offduty"),
	
	waiting("waiting"),
	beingVisited("being-visited"),
	takingExam("taking-exam"),
	released("released");
	
	private String state; 
	
	State(String state){
		this.state = state;
	}
	
	public String getState() {
		return this.state;
	}
	
}
