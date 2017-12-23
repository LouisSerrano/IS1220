package event;

import core.EmergencyDepartment;
import core.Patient;

public class Arrival2Event extends PatientArrivalEvent {
	
	public Arrival2Event(Patient patient){
		super(EventType.ARR2,patient.getArrivalTime(),patient);
		}
	
	public void execute(EmergencyDepartment system){
		super.execute(system);
		this.toString();
	}
	public String toString() {
		
		return super.toString();
	}
	

}