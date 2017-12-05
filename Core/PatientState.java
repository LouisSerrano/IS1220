package Core;

public enum PatientState {
	
	WAITING_REGISTRATION("WAITING_REGISTRATION"),
	WAITING_INSTALLATION("WAITING_INSTALLATION"),
	WAITING_CONSULTATION("WAITING_CONSULTATION"),
	WAITING_TRANSPORTATION("WAITING_TRANSPORTATION"),
	WAITING_XRAY("WAITING_XRAY"),
	WAITING_BLOOD("WAITING_BLOOD"),
	WAITING_MRI("WAITING_MRI"),
	BEING_INSTALLED("BEING_INSTALLED"),
	BEING_CONSULTED("BEING_CONSULTED"),
	BEING_TRANSPORTED("BEING_TRANSPORTED"),
	BEING_EXAMINATED("BEING_EXAMINATED"),
	RELEASED("RELEASED");
	
	private String state;
	
	PatientState(String state){
		this.state = state;
	}
	
	public String getPatientState() {
		return this.state;
	}
}
