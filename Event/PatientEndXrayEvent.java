package Event;

import Core.EmergencyDepartment;
import Core.Patient;
import Core.Room;

public class PatientEndXrayEvent extends PatientEndExaminationEvent {
	
	
	public PatientEndXrayEvent(int timeStamp, Patient patient, Room room){
		super(EventType.END_XRAY ,timeStamp, patient, room);
		}
	
	public void execute(EmergencyDepartment system){
		super.execute(system);

	
}
}