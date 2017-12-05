package event;

import core.EmergencyDepartment;
import core.HumanResourceState;
import core.Nurse;
import core.Patient;
import core.PatientState;
import core.Room;
import core.RoomState;

public class PatientEndInstallationEvent extends Event {
	private Patient patient;
	private Nurse nurse;
	
	public PatientEndInstallationEvent(int timeStamp, Patient patient, Nurse nurse){
		super(EventType.END_INSTAL,timeStamp);
		this.patient=patient;
		this.nurse=nurse;
	}
	
	public void execute(EmergencyDepartment system){
		patient.setPatientState(PatientState.WAITING_CONSULTATION);
		system.getConsultation().addPatientToQueue(this.patient);
		nurse.setHumanResourceState(HumanResourceState.IDLE);
		
		
	}
	
	public String toString() {
		return "Installation finished for patient " + patient.getName() + " at the time " + this.getTimeStamp() + " with the nurse "
				+ nurse.getName();
	}
	

}
