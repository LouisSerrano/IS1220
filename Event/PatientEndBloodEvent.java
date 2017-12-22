package event;

import Core.EmergencyDepartment;
import Core.Patient;
import Core.PatientState;
import Core.Room;
import Core.RoomState;

public class PatientEndBloodEvent extends PatientEndExaminationEvent {
	
	
	public PatientEndBloodEvent(int timeStamp, Patient patient, Room room){
		super(EventType.END_BLOOD ,timeStamp, patient, room);
		}
	
	public void execute(EmergencyDepartment system){
		super.execute(system);	
		this.toString();
	}
	public String toString() {
	return "End BloodTest of the Patient " + super.getPatient().getName() +  "at the time " + this.getTimeStamp()+"in the Room"+super.getRoom().getName();

	}
}

