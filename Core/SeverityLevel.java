package is1220.projectSimErgy.core;

public enum SeverityLevel {
	
	L1("L1"),
	L2("L2"),
	L3("L3"),
	L4("L4"),
	L5("L5");
	
	private String level;
	
	SeverityLevel(String level){
		this.level = level;
	}
	
	public String getLevel(){
		return this.level;
	}

}
