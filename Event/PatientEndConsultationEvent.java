package event;

import core.EmergencyDepartment;
import core.healthService.HealthService;
import core.HumanResourceState;
import core.Patient;
import core.PatientState;
import core.Physician;
import core.Room;
import core.distribution.ConsultationReqProbability;

public class PatientEndConsultationEvent extends Event {
	
	private Patient patient;
	private Physician physician;
	
	public PatientEndConsultationEvent(int timeStamp, Patient patient,Physician physician, Room room){
		super(EventType.END_VISIT,timeStamp);
		this.patient=patient;
		this.physician= physician;
		}

	public void execute(EmergencyDepartment system){
		String decision = ConsultationReqProbability.getRequest();
		
		switch(decision){
		case "NO_TEST_REQUIRED":
			patient.setPatientState(PatientState.WAITING_RELEASE);
			break;
		case "X_RAY":
			patient.setPatientState(PatientState.WAITING_TRANSPORTATION);	
			patient.setDirection(decision);
			break;
		case "BLOOD_TEST":
			patient.setPatientState(PatientState.WAITING_TRANSPORTATION);
			patient.setDirection(decision);

			break;
		case "MRI":
			patient.setPatientState(PatientState.WAITING_TRANSPORTATION);
			patient.setDirection(decision);

			break;
		}
		system.getConsultation().getWaitingQueue().remove(this.patient);
		physician.setHumanResourceState(HumanResourceState.IDLE);
		
	}

}
