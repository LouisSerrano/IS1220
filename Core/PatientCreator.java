package core;

import java.util.ArrayList;
import java.util.HashMap;

public class PatientCreator {
	public static HashMap<SeverityLevel,Integer> nextPatient;
	
	/**
	 * Instantiates and initialises a new Hashmap for PatientList. Calls 5 patients with a
	 * different severity level. Each time a new patient of certain severity level arrives, it calls a new patient 
	 * with the same severity level. We consider that a new patient of a certain type can arrive only once
	 * that a patient has already arrived
	 * 
	 */
	
	
	public PatientCreator(){
		nextPatient= new HashMap<SeverityLevel,Integer>();
		
		for( SeverityLevel lvl : SeverityLevel.values() ){
			nextPatient.put(lvl,0);
		}
		
	}
	
	public Patient newPatient(SeverityLevel level){
		int lastTime = nextPatient.get(level);
		int time= level.getDistribution().generateSample();
		Patient patient =new Patient(level, lastTime+time);
		nextPatient.put(level,time+lastTime);
		
		return patient;
	}
	
	public String toString() {
		return "The Next Patient of each Severity Level : \n" 
				+"Severity Level 1: "+nextPatient.get(SeverityLevel.L1) + "\n"
				+"Severity Level 2: "+nextPatient.get(SeverityLevel.L2)+ "\n"
				+"Severity Level 3: "+nextPatient.get(SeverityLevel.L3)+ "\n"
				+"Severity Level 4: "+nextPatient.get(SeverityLevel.L4)+ "\n"
				+"Severity Level 5: "+nextPatient.get(SeverityLevel.L5)+ "\n";


	}

	
	
	
	
	
	
	

}
