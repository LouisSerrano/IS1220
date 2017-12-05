package event;

import core.EmergencyDepartment;
import core.HumanResourceState;
import core.Patient;
import core.PatientState;
import core.Transporter;

public class PatientEndTransportEvent extends Event{
	
	private Patient patient;
	private Transporter transporter;
	
	public PatientEndTransportEvent(int timeStamp, Patient patient, Transporter transporter){
		super(EventType.START_TRANSPORT,timeStamp);
		this.patient=patient;
		this.transporter= transporter;
		}
	@Override
	public void execute(EmergencyDepartment system){
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
		
	}
	
	

}

}
