package Event;

import Core.EmergencyDepartment;
import Core.HealthService;
import Core.Nurse;
import Core.Patient;
import Core.Room;

public class PatientStartExaminationEvent extends Event {
	
	private Patient patient;
	private Room room;
	
	public PatientStartExaminationEvent(EventType name, int timeStamp, Patient patient, Room room){
		super(name ,timeStamp);
		this.patient=patient;
		this.room= room;
		}
	
	public void execute(EmergencyDepartment system){
		
		
	}

}


