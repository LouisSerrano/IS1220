package event;

import Core.EmergencyDepartment;
import Core.Patient;
import Core.PatientState;

public class PatientDepartureEvent extends Event {
	Patient patient;
	
	public PatientDepartureEvent(int timeStamp,Patient patient){
		super("Departure of " + patient.getName(),EventType.VERDICT,timeStamp);
		this.patient=patient;
		}

	@Override
	public void execute(EmergencyDepartment system){
		super.execute(system);
		patient.setPatientState(PatientState.RELEASED);
		system.getPatientList().remove(patient);
		this.toString();
	}
	
	
	public String toString() {
		return "Patient " + patient.getName() + " left at " + patient.getArrivalTime() +" with the Severitylevel "+this.patient.getSeverityLevel().getLevel();
	}
}
