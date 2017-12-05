package Core;

import java.util.ArrayList;

public class PrgmTest {

	public static void main(String[] args) {
		Time t = new Time(16, 20);
		System.out.println(t);
		
		Nurse n1 = new Nurse("Mary", "S");
		Physician p1 = new Physician("Philip", "B");
		System.out.println(n1);
		System.out.println(p1);
		System.out.println(HumanResource.getCounter());
		
		Consultation c = new Consultation(450, null);
		System.out.println(c);
		
		Time aT = new Time(12, 20);
		
		Patient patient1 = new Patient("Brown", "James", SeverityLevel.L1, aT);
		//System.out.println(patient);	
		Patient patient2 = new Patient("Brown", "James", SeverityLevel.L1, aT);
		Patient patient3 = new Patient("Brown", "James", SeverityLevel.L2, aT);
		Patient patient4 = new Patient("Brown", "James", SeverityLevel.L3, aT);
		Patient patient5 = new Patient("Brown", "James", SeverityLevel.L4, aT);
		Patient patient6 = new Patient("Brown", "James", SeverityLevel.L4, aT);
		Patient patient7 = new Patient("Brown", "James", SeverityLevel.L5, aT);
		Patient patient8 = new Patient("Brown", "James", SeverityLevel.L5, aT);
		
		Patient patientTest = new Patient("Testing", "Test", SeverityLevel.L4, aT);	
		
		HealthService hS = new Consultation();
		
		hS.addPatientToQueue(patient1);
		hS.addPatientToQueue(patient2);
		hS.addPatientToQueue(patient7);
		hS.addPatientToQueue(patient6);
		hS.addPatientToQueue(patient5);
		hS.addPatientToQueue(patient4);
		hS.addPatientToQueue(patient3);
		hS.addPatientToQueue(patient8);

		
		hS.addPatientToQueue(patientTest);
		System.out.println(hS.getWaitingQueue());
		
		
		
	}
}
