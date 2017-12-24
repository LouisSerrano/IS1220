package event;

import core.EmergencyDepartment;
import core.Nurse;
import core.Patient;
import core.PatientState;
import core.healthservice.Blood;
import core.healthservice.HealthService;
import core.healthservice.MRI;
import core.healthservice.Xray;
import core.room.Room;
import core.room.RoomState;

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
		patient.updateHistory(this, getTimeStamp());
		
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


