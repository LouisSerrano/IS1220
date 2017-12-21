package Event;

import Core.EmergencyDepartment;
import Core.Patient;

public class Arrival5Event extends PatientArrivalEvent {
	
	public Arrival5Event(Patient patient){
		super(EventType.ARR5,patient.getArrivalTime(),patient);
		}
	
	public void execute(EmergencyDepartment system){
		super.execute(system);
		system.getEventqueue().addInstanceOfEventType(EventType.ARR5, system);
	}

}