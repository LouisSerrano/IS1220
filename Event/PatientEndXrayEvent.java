package event;

import core.EmergencyDepartment;
import core.Patient;
import core.room.Room;

public class PatientEndXrayEvent extends PatientEndExaminationEvent {
	
	
	public PatientEndXrayEvent(int timeStamp, Patient patient, Room room){
		super(EventType.END_XRAY ,timeStamp, patient, room);
		}
	
	public void execute(EmergencyDepartment system){
		super.execute(system);
		getPatient().updateHistory(this, getTimeStamp());
		getPatient().updateCharges(system.getXray());
		this.toString();
	}
	
	public String toString() {
		
		return "End Xray of the Patient " + super.getPatient().getName() +  "at the time " + this.getTimeStamp()+"in the Room"+super.getRoom().getName();
		}
}