package Event;

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
}
}
