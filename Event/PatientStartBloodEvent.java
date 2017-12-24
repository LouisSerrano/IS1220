package event;

import core.EmergencyDepartment;
import core.Patient;
import core.PatientState;
import core.distribution.LaboratoryRoom;
import core.distribution.Uniform;
import core.room.Room;
import core.room.RoomState;

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
		patient.updateHistory(this, getTimeStamp());
		this.toString();
	}
	
	public String toString() {
		
		return "Start BloodTest of the Patient " + super.getPatient().getName() +  "at the time " + this.getTimeStamp()+"in the Room"+super.getRoom().getName();
		}

}
