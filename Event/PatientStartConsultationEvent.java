package event;

import core.EmergencyDepartment;
import core.HumanResourceState;
import core.Patient;
import core.PatientState;
import core.Physician;
import core.Room;

public class PatientStartConsultationEvent extends Event{
	private Patient patient;
	private Physician physician;
	private Room room;
	
	public PatientStartConsultationEvent(int timeStamp, Patient patient,Physician physician){
		super(EventType.START_VISIT,timeStamp);
		this.patient=patient;
		this.physician=physician;
		}

	public void execute(EmergencyDepartment system){
		patient.setPatientState(PatientState.BEING_CONSULTED);
		system.getConsultation().getWaitingQueue().remove(this.patient);
		physician.setHumanResourceState(HumanResourceState.VISITING);
		
	}
	
	public String toString() {
		return "Starting consultation of patient " + patient.getName() + " at the time " + this.getTimeStamp() + " with the physician "
				+ physician.getName() + " in the room " + room.getName();
	}
	
	

}

