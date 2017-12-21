package Event;

import Core.EmergencyDepartment;
import Core.Patient;

public class Arrival2Event extends PatientArrivalEvent {
	
	public Arrival2Event(Patient patient){
		super(EventType.ARR2,patient.getArrivalTime(),patient);
		}
	
	public void execute(EmergencyDepartment system){
		super.execute(system);
		system.getEventqueue().addInstanceOfEventType(EventType.ARR2, system);
	}

}