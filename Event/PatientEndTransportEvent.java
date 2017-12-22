package event;

import Core.EmergencyDepartment;
import Core.HumanResourceState;
import Core.Patient;
import Core.PatientState;
import Core.Transporter;

public class PatientEndTransportEvent extends Event{
	
	private Patient patient;
	private Transporter transporter;
	
	public PatientEndTransportEvent(int timeStamp, Patient patient, Transporter transporter){
		super("END OF TRANSPORT"+patient.getName(),EventType.END_TRANSPORT,timeStamp);
		this.patient=patient;
		this.transporter= transporter;
		}
	@Override
	public void execute(EmergencyDepartment system){
		super.execute(system);
		this.toString();


		transporter.setHumanResourceState(HumanResourceState.IDLE);
		switch(patient.getDirection()){
		
		case "X_RAY":
			patient.setPatientState(PatientState.WAITING_XRAY);
			system.getXray().addPatientToQueue(patient);
			patient.setDirection(null);
			break;
			
		case "BLOOD":
			patient.setPatientState(PatientState.WAITING_BLOOD);
			system.getBlood().addPatientToQueue(patient);
			patient.setDirection(null);
			break;
			
		case "MRI":
			patient.setPatientState(PatientState.WAITING_MRI);
			system.getMri().addPatientToQueue(patient);
			patient.setDirection(null);
			break;
			
		case"CONSULTATION":
			patient.setPatientState(PatientState.WAITING_CONSULTATION);
			system.getConsultation().addPatientToQueue(patient);
			patient.setDirection(null);
			break;
		}
	}
	
public String toString() {
	return "Transporter "+transporter.getName()+"completes transport of the Patient " + patient.getName() +  "at the time " + this.getTimeStamp()+"to "+patient.getDirection();
}

}
