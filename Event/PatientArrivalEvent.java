package Event;

import Core.EmergencyDepartment;
import Core.Patient;
import Core.PatientState;

public class PatientArrivalEvent extends Event {
	Patient patient;
	
	
	public PatientArrivalEvent(String name, Patient patient){
		super(EventType.ARR1,patient.getArrivalTime());
		this.patient=patient;
		}

	@Override
	public void execute(EmergencyDepartment system){
		patient.setPatientState(PatientState.WAITING_REGISTRATION);
		system.getRegistration().addPatientToQueue(patient);
	}
	
	public String toString() {
		return "Patient " + patient.getName() + " arrived at " + patient.getArrivalTime();
	}

}

