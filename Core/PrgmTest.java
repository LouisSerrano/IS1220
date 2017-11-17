package is1220.projectSimErgy.core;

public class PrgmTest {

	public static void main(String[] args) {
		Time t = new Time(16, 20);
		System.out.println(t);
		
		Nurse n1 = new Nurse("Mary", "S");
		Physician p1 = new Physician("Philip", "B");
		System.out.println(n1);
		System.out.println(p1);
		System.out.println(HumanResource.getCounter());
		
	}
}
