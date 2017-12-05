package Core;

import java.util.ArrayList;
import java.util.HashMap;

public class PatientCreator {
	public HashMap<SeverityLevel,Patient> lastPatient;
	
	/**
	 * Instantiates and initialises a new Hashmap for PatientList. Calls 5 patients with a
	 * different severity level. Each time a new patient of certain severity level arrives, it calls a new patient 
	 * with the same severity level. We consider that a new patient of a certain type can arrive only once
	 * that a patient has already arrived
	 * 
	 */
	
	
	public PatientCreator(){
		this.lastPatient= new HashMap<SeverityLevel,Patient>();
		
		for( SeverityLevel lvl : SeverityLevel.values() ){
			int time = lvl.getDistribution().generateSample();
			Patient patient= new Patient (lvl, time);
			lastPatient.put(lvl,patient);
		}
		
	}
	
	public Patient newPatient(SeverityLevel level){
		int lastTime = lastPatient.get(level).getArrivalTime();
		int time= level.getDistribution().generateSample();
		Patient patient =new Patient(level, lastTime+time);
		lastPatient.put(level,patient);
		
		return patient;
	}
	
	
	
	
	
	

}
