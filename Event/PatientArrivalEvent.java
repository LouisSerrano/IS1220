package event;

import core.EmergencyDepartment;
import core.Patient;
import core.PatientCreator;
import core.PatientState;

public class PatientArrivalEvent extends Event {
	Patient patient;
	
	public PatientArrivalEvent(EventType type,int timeStamp, Patient patient){
		super("Arrival of " + patient.getName(),type,timeStamp);
		this.patient=patient;
		}

	@Override
	public void execute(EmergencyDepartment system){
		super.execute(system);
		patient.setPatientState(PatientState.WAITING_REGISTRATION);
		system.getRegistration().addPatientToQueue(patient);
		system.getPatientList().add(patient);
		this.toString();
	}
	
	
	public String toString() {
		return "Patient " + patient.getName() + " arrived at " + patient.getArrivalTime() +" with the Severitylevel "+this.patient.getSeverityLevel().getLevel();
	}
}

	
