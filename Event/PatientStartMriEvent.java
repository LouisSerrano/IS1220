package event;

import Core.Distribution.Uniform;

import Core.EmergencyDepartment;
import Core.LaboratoryRoom;
import Core.Patient;
import Core.PatientState;
import Core.Room;
import Core.RoomState;

public class PatientStartMriEvent extends PatientStartExaminationEvent {
	

	public PatientStartMriEvent (int timeStamp, Patient patient, Room room){
		super(EventType.START_MRI ,timeStamp, patient, room );
		}
	@Override
	public void execute(EmergencyDepartment system){
		
		Room room = super.getRoom();
		Patient patient = super.getPatient();
		
		room.setRoomState(RoomState.USED);
		patient.setPatientState(PatientState.BEING_EXAMINATED);
		system.getMri().getWaitingQueue().remove(patient);
		patient.setDirection(null);
		int t = new Uniform(30,70).generateSample();
		system.getEventqueue().getNextEvents().add(new PatientEndMriEvent(system.getSimTime()+t,patient, room));
		this.toString();	
	}
	
	public String toString() {
		
		return "Start Mri of the Patient " + super.getPatient().getName() +  "at the time " + this.getTimeStamp()+"in the Room"+super.getRoom().getName();
		}


}
