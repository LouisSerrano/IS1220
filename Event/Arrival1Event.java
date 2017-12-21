package Event;

import Core.EmergencyDepartment;
import Core.Patient;

public class Arrival1Event extends PatientArrivalEvent {
	
	public Arrival1Event(Patient patient){
		super(EventType.ARR1,patient.getArrivalTime(),patient);
		}
	
	public void execute(EmergencyDepartment system){
		super.execute(system);
		system.getEventqueue().addInstanceOfEventType(EventType.ARR1, system);
	}

}
