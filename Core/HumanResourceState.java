package core;

public enum HumanResourceState {
		
		IDLE("IDLE"),
		VISITING("VISITING"),
		OFFDUTY("OFFDUTY");
		
		private String state; 
		
		HumanResourceState(String state){
			this.state = state;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}
		
		

}
