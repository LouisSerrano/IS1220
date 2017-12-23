package core.room;

public enum RoomState {

	AVAILABLE("AVAILABLE"),
	USED("USED");
	
	private String state;
	
	RoomState(String state) {
		this.state = state;
	}
	
	public String getState() {
		return state;
	}
	
}
