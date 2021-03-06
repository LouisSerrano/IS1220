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
		super("END OF TRANSPORT"+patient.getName(),EventType.END_TRANSPORT,timeStamp);
		this.patient=patient;
		this.transporter= transporter;
		}
	@Override
	public void execute(EmergencyDepartment system){
		super.execute(system);
		patient.updateHistory(this, getTimeStamp());
		patient.updateCharges(system.getTransportation());

		this.toString();


		transporter.setHumanResourceState(HumanResourceState.IDLE);
		switch(patient.getDirection()){
		
		case "X_RAY":
			patient.setPatientState(PatientState.WAITING_XRAY);
			system.getXray().addPatientToQueue(patient);
			patient.setDirection(null);
			break;
			
		case "BLOOD_TEST":
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
			patient.setPatientState(PatientState.WAITING_INSTALLATION);
			system.getInstallation().addPatientToQueue(patient);
			patient.setDirection(null);
			break;
		}
	}
	
public String toString() {
	return "Transporter "+transporter.getName()+" completes transport of the Patient " + patient.getName() +  " at the time " + this.getTimeStamp()+" to "+patient.getDirection();
}

}
