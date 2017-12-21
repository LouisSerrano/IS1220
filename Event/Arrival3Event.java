package Event;

import Core.EmergencyDepartment;
import Core.Patient;

public class Arrival3Event extends PatientArrivalEvent {
	
	public Arrival3Event(Patient patient){
		super(EventType.ARR3,patient.getArrivalTime(),patient);
		}
	
	public void execute(EmergencyDepartment system){
		super.execute(system);
		system.getEventqueue().addInstanceOfEventType(EventType.ARR3, system);
	}

}
