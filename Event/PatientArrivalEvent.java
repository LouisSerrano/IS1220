package event;

import Core.EmergencyDepartment;
import Core.Patient;
import Core.PatientCreator;
import Core.PatientState;

public class PatientArrivalEvent extends Event {
	Patient patient;
	
	public PatientArrivalEvent(EventType type,int timeStamp,Patient patient){
		super("Arrival of " + patient.getName(),type,patient.getArrivalTime());
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

	
