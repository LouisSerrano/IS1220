package Core;

public enum SeverityLevel {
	
	L1("1"),
	L2("2"),
	L3("3"),
	L4("4"),
	L5("5");
	
	private String level;
	
	SeverityLevel(String level){
		this.level = level;
	}
	
	public String getLevel(){
		return this.level;
	}

}
