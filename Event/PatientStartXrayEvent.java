package event;

import core.EmergencyDepartment;
import core.Patient;
import core.PatientState;
import core.distribution.Uniform;
import core.room.RadiographyRoom;
import core.room.Room;
import core.room.RoomState;

public class PatientStartXrayEvent extends PatientStartExaminationEvent {
	
	public PatientStartXrayEvent(int timeStamp, Patient patient, Room room){
		super(EventType.START_XRAY ,timeStamp, patient, room );
		}
	@Override
	public void execute(EmergencyDepartment system){
		Room room = super.getRoom();
		Patient patient = super.getPatient();
		
		room.setRoomState(RoomState.USED);
		patient.setPatientState(PatientState.BEING_EXAMINATED);
		system.getXray().getWaitingQueue().remove(patient);
		patient.setDirection(null);
		
		int t = new Uniform(10,20).generateSample();
		system.getEventqueue().getNextEvents().add(new PatientEndXrayEvent(system.getSimTime()+t,patient, room));
		this.toString();
	}
	public String toString() {
		
	return "Start Xray of the Patient " + super.getPatient().getName() +  "at the time " + this.getTimeStamp()+"in the Room"+super.getRoom().getName();
	}
}


