package Event;

import Core.Blood;
import Core.EmergencyDepartment;
import Core.HealthService;
import Core.MRI;
import Core.Nurse;
import Core.Patient;
import Core.PatientState;
import Core.Room;
import Core.RoomState;
import Core.Xray;

public abstract class PatientStartExaminationEvent extends Event {
	
	private Patient patient;
	private Room room;
	
	public PatientStartExaminationEvent(EventType type, int timeStamp, Patient patient, Room room){
		super("START EXAMINATION OF" +patient.getName(),type ,timeStamp);
		this.patient=patient;
		this.room= room;
		}
	
	public void execute(EmergencyDepartment system){
		super.execute(system);
		this.room.setRoomState(RoomState.USED);
		this.patient.setPatientState(PatientState.BEING_EXAMINATED);
		
switch(patient.getDirection()){
		
		case "X_RAY":
			system.getXray().getWaitingQueue().remove(patient);
			patient.setDirection(null);
			break;
			
		case "BLOOD":
			system.getBlood().getWaitingQueue().remove(patient);
			patient.setDirection(null);
			break;
			
		case "MRI":
			system.getMri().getWaitingQueue().remove(patient);
			patient.setDirection(null);
			break;
		
	}
		
		
	}

	public Patient getPatient() {
		return patient;
	}

	public Room getRoom() {
		return room;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

}


