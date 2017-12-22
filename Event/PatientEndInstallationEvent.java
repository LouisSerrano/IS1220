package event;

import Core.EmergencyDepartment;
import Core.HumanResourceState;
import Core.Nurse;
import Core.Patient;
import Core.PatientState;
import Core.Room;
import Core.RoomState;

public class PatientEndInstallationEvent extends Event {
	private Patient patient;
	private Nurse nurse;
	private Room room;
	
	public PatientEndInstallationEvent(int timeStamp, Patient patient, Nurse nurse, Room room){
		super("END OF INSTALLATION OF"+patient.getName(),EventType.END_INSTAL,timeStamp);
		this.patient=patient;
		this.nurse=nurse;
		this.room=room;
	}
	
	public void execute(EmergencyDepartment system){
		super.execute(system);

		patient.setPatientState(PatientState.WAITING_CONSULTATION);
		system.getConsultation().addPatientToQueue(this.patient);
		nurse.setHumanResourceState(HumanResourceState.IDLE);
		
		
	}
	
	public String toString() {
		return "Nurse  "+nurse.getName()+" completes Installation for the patient " + patient.getName() + " at the time " + this.getTimeStamp() +" in the room "+room.getName();
	}
	

}
