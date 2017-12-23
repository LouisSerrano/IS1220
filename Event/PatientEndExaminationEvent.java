package event;

import Core.Blood;
import Core.EmergencyDepartment;
import Core.MRI;
import Core.Patient;
import Core.PatientState;
import Core.Room;
import Core.RoomState;
import Core.Xray;

public abstract class PatientEndExaminationEvent extends Event {
	
	private Patient patient;
	private Room room;
	
	public PatientEndExaminationEvent(EventType name, int timeStamp, Patient patient, Room room){
		super("END OF EXAMINATION"+ patient.getName(),name ,timeStamp);
		this.patient=patient;
		this.room= room;
		}
	
	public void execute(EmergencyDepartment system){
		super.execute(system);

	this.room.setRoomState(RoomState.AVAILABLE);
	this.patient.setPatientState(PatientState.WAITING_TRANSPORTATION);
	system.getTransportation().addPatientToQueue(patient);
	this.patient.setDirection("CONSULTATION");

	
}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	
}
	
	
	
	



