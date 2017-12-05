package event;

import core.EmergencyDepartment;
import core.healthService.HealthService;
import core.Nurse;
import core.Patient;
import core.Room;

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


