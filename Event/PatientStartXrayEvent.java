package Event;

import Core.EmergencyDepartment;
import Core.Patient;
import Core.PatientState;
import Core.RadiographyRoom;
import Core.Room;
import Core.RoomState;
import Core.Distribution.Uniform;

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
				
	}

}


