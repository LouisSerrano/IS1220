package event;

import core.EmergencyDepartment;
import core.Patient;

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