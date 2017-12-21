package Event;

import Core.EmergencyDepartment;
import Core.LaboratoryRoom;
import Core.Patient;
import Core.PatientState;
import Core.Room;
import Core.RoomState;
import Core.Distribution.Uniform;

public class PatientStartBloodEvent extends PatientStartExaminationEvent{
	
	public PatientStartBloodEvent(int timeStamp, Patient patient, Room room){
		super(EventType.START_BLOOD ,timeStamp, patient, room );
		}
	@Override
	public void execute(EmergencyDepartment system){
		Room room = super.getRoom();
		Patient patient = super.getPatient();
		
		room.setRoomState(RoomState.USED);
		patient.setPatientState(PatientState.BEING_EXAMINATED);
		system.getBlood().getWaitingQueue().remove(patient);
		patient.setDirection(null);
		int t = new Uniform(15,90).generateSample();
		system.getEventqueue().getNextEvents().add(new PatientEndBloodEvent(system.getSimTime()+t,patient,room));

	}

}
