package core;

import java.util.ArrayList;
import java.util.HashMap;

public class PatientCreator {
	public HashMap<SeverityLevel,ArrayList<Integer>> PatientList;
	
	/**
	 * Instantiates and initialises a new Hashmap for PatientList. Calls 5 patients with a
	 * different severity level. Each time a new patient of certain severity level arrives, it calls a new patient 
	 * with the same severity level. We consider that a new patient of a certain type can arrive only once
	 * that a patient has already arrived
	 * 
	 */
	
	
	public PatientCreator(){
		this.PatientList= new HashMap<SeverityLevel,ArrayList<Integer>>();
		
		for( SeverityLevel lvl : SeverityLevel.values() ){
			ArrayList<Integer> list = new ArrayList<Integer>();
			list.add(lvl.distribution.generateSample());
			PatientList.put(lvl, list);
		}
		
	}
	
	public void addPatient(SeverityLevel level){
		ArrayList<Integer> list = PatientList.get(level);
		int lastTime = list.get(list.size()-1);
		list.add(lastTime+level.distribution.generateSample());
		PatientList.put(level, list);
		
	}
	
	
	
	
	
	

}
