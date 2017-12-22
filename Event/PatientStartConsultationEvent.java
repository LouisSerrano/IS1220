package event;

import Core.Distribution.Uniform;

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
		super("START OF CONSULTATION"+patient.getName(),EventType.START_VISIT,timeStamp);
		this.patient=patient;
		this.room=patient.getRoom();
		this.physician=physician;
		}

	public void execute(EmergencyDepartment system){
		super.execute(system);

		patient.setPatientState(PatientState.BEING_CONSULTED);
		system.getConsultation().getWaitingQueue().remove(this.patient);
		physician.setHumanResourceState(HumanResourceState.VISITING);
		int t = system.getConsultation().getDistribution().generateSample();
		system.getEventqueue().getNextEvents().add(new PatientEndConsultationEvent(system.getSimTime()+t,patient,physician, room));
		this.toString();
	}
	
	public String toString() {
		return "Physician "+physician.getName()+" starts Consultation of the Patient " + patient.getName() + " at the time " + super.getTimeStamp() + 
				" in the room " + room.getName();
	}
	
	

}

