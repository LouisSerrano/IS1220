package event;

import Core.EmergencyDepartment;
import Core.Patient;

public class Arrival5Event extends PatientArrivalEvent {
	
	public Arrival5Event(Patient patient){
		super(EventType.ARR5,patient.getArrivalTime(),patient);
		}
	
	public void execute(EmergencyDepartment system){
		super.execute(system);
		this.toString();
	}
	
	public String toString() {
		
		return super.toString();
	}
}