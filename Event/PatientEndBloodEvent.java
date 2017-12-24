package event;

import core.EmergencyDepartment;
import core.Patient;
import core.PatientState;
import core.Physician;
import core.room.Room;
import core.room.RoomState;

public class PatientEndBloodEvent extends PatientEndExaminationEvent {
	
	
	public PatientEndBloodEvent(int timeStamp, Patient patient, Room room){
		super(EventType.END_BLOOD ,timeStamp, patient, room);
		}
	
	public void execute(EmergencyDepartment system){
		super.execute(system);
		getPatient().updateHistory(this, getTimeStamp());
		getPatient().updateCharges(system.getBlood());

		this.toString();
	}
	public String toString() {
	return "End BloodTest of the Patient " + super.getPatient().getName() +  "at the time " + this.getTimeStamp()+"in the Room"+super.getRoom().getName();

	}
}