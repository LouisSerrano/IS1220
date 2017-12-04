package core;

public abstract class HumanResource {

	/**
	 * counts the total number of Human Resources working in the ED
	 */
	private static int counter;
	
	private int id;
	private String surname;
	private String name;
	private String type;
	private String state;
	
	public HumanResource(String surname, String name, String type) {
		HumanResource.counter++;
		this.id = HumanResource.counter;
		this.setSurname(surname);
		this.setName(name);
		this.type = type;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public static int getCounter() {
		return HumanResource.counter;
	}
	
	public String toString() {
		return "Human Resource : [id = " + id + ", type = " + type + ", name = " + name + " " + surname 
				+ ", state = " + state + "]";
	}
}
