package event;

import Core.EmergencyDepartment;
import Core.Patient;

public class Arrival3Event extends PatientArrivalEvent {
	
	public Arrival3Event(Patient patient){
		super(EventType.ARR3,patient.getArrivalTime(),patient);
		}
	
	public void execute(EmergencyDepartment system){
		super.execute(system);
		this.toString();
	}

	public String toString() {
		
		return super.toString();
	}
}
