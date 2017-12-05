package Event;

import Core.EmergencyDepartment;
import Core.HumanResourceState;
import Core.Patient;
import Core.PatientState;
import Core.Physician;
import Core.Room;

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

