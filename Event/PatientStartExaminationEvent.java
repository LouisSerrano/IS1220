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

public class PatientStartExaminationEvent extends Event {
	
	private Patient patient;
	private Room room;
	
	public PatientStartExaminationEvent(EventType name, int timeStamp, Patient patient, Room room){
		super(name ,timeStamp);
		this.patient=patient;
		this.room= room;
		}
	
	public void execute(EmergencyDepartment system){
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

}


