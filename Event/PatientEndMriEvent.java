package event;

import core.EmergencyDepartment;
import core.Patient;
import core.room.Room;

public class PatientEndMriEvent extends PatientEndExaminationEvent {
	
	
	public PatientEndMriEvent(int timeStamp, Patient patient, Room room){
		super(EventType.END_MRI ,timeStamp, patient, room);
		}
	
	public void execute(EmergencyDepartment system){
		super.execute(system);
		this.toString();
	}
	public String toString() {
	return "End Mri of the Patient " + super.getPatient().getName() +  "at the time " + this.getTimeStamp()+"in the Room"+super.getRoom().getName();
	}
}

