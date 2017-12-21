package Event;

import Core.EmergencyDepartment;
import Core.Patient;

public class Arrival4Event extends PatientArrivalEvent {
	
	public Arrival4Event(Patient patient){
		super(EventType.ARR4,patient.getArrivalTime(),patient);
		}
	
	public void execute(EmergencyDepartment system){
		super.execute(system);
		system.getEventqueue().addInstanceOfEventType(EventType.ARR4, system);
	}

}
