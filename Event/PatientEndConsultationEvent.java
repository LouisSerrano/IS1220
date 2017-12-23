package event;

import core.EmergencyDepartment;
import core.HumanResourceState;
import core.Patient;
import core.PatientState;
import core.Physician;
import core.distribution.ConsultationReqProbability;
import core.healthservice.HealthService;
import core.room.Room;
import core.room.RoomState;

public class PatientEndConsultationEvent extends Event {
	
	private Patient patient;
	private Physician physician;
	private String decision;
	private Room room;
	
	public PatientEndConsultationEvent(int timeStamp, Patient patient,Physician physician, Room room){
		super("END OF CONSULTATION"+patient.getName(),EventType.END_VISIT,timeStamp);
		this.patient=patient;
		this.physician= physician;
		this.room= room;
		this.decision = patient.getDecisionFunction().getRequest();
		}

	public void execute(EmergencyDepartment system){
		super.execute(system);
		this.room.setRoomState(RoomState.AVAILABLE);
		
		patient.setRoom(null);
		String decision = this.decision;
		
		switch(decision){
		case "NO_TEST_REQUIRED":
			patient.setPatientState(PatientState.WAITING_RELEASE);
			system.getEventqueue().getNextEvents().add(new PatientDepartureEvent(system.getSimTime(),this.patient));
			break;
			
		case "X_RAY":
			patient.setPatientState(PatientState.WAITING_TRANSPORTATION);	
			patient.setDirection(decision);
			this.patient.getDecisionFunction().setXrayTaken(true);
			system.getTransportation().addPatientToQueue(patient);
			break;
			
		case "BLOOD_TEST":
			patient.setPatientState(PatientState.WAITING_TRANSPORTATION);
			patient.setDirection(decision);
			this.patient.getDecisionFunction().setBloodTestTaken(true);
			system.getTransportation().addPatientToQueue(patient);
			break;
			
		case "MRI":
			patient.setPatientState(PatientState.WAITING_TRANSPORTATION);
			patient.setDirection(decision);
			this.patient.getDecisionFunction().setMriTaken(true);
			system.getTransportation().addPatientToQueue(patient);
			break;
		}
		physician.setHumanResourceState(HumanResourceState.IDLE);
		this.toString();
		
	}
	public String toString() {
		return "Physician " + physician.getName()+" completes Consultation for the patient "+patient.getName() +  " at the time " + this.getTimeStamp()+" in the Room "+room.getName()+" and the result of the consultation is "+this.decision;

	}

}
