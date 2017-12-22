package event;

import Core.EmergencyDepartment;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 
import Core.Patient;

import Core.EmergencyDepartment;
import Core.Patient;

public class Arrival1Event extends PatientArrivalEvent {
	
	public Arrival1Event(Patient patient){
		super(EventType.ARR1,patient.getArrivalTime(),patient);
		}
	
	public void execute(EmergencyDepartment system){
		super.execute(system);
		this.toString();
	}
	
	public String toString() {
		
		return super.toString();
	}
}