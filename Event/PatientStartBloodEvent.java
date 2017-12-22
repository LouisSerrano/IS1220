package event;

import Core.Distribution.Uniform;

import Core.EmergencyDepartment;
import Core.LaboratoryRoom;
import Core.Patient;
import Core.PatientState;
import Core.Room;
import Core.RoomState;

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
		int t = system.getBlood().getDistribution().generateSample();
		system.getEventqueue().getNextEvents().add(new PatientEndBloodEvent(system.getSimTime()+t,patient,room));
		this.toString();
	}
	
	public String toString() {
		
		return "Start BloodTest of the Patient " + super.getPatient().getName() +  "at the time " + this.getTimeStamp()+"in the Room"+super.getRoom().getName();
		}

}
