package Event;

import Core.EmergencyDepartment;
import Core.HealthService;
import Core.HumanResourceState;
import Core.Patient;
import Core.PatientState;
import Core.Physician;
import Core.Room;
import Core.Distribution.ConsultationReqProbability;

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
