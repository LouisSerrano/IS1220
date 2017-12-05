package Event;

import Core.EmergencyDepartment;
import Core.Nurse;
import Core.Patient;
import Core.PatientState;

public class PatientRegistrationEvent extends Event {
	private Patient patient;
	private Nurse nurse;
	
	
	public PatientRegistrationEvent(Nurse nurse, Patient patient, int timeStamp){
		super(EventType.REGIST, timeStamp);
		this.patient= patient;
		this.nurse=nurse;
		
	}
	
	/*
	 * We consider that we need a nurse to register but since it takes no time to register, it is more 
	 * convenient not to affect a nurse to a patient.
	 * @see Event.Event#execute(Core.EmergencyDepartment)
	 */
	@Override
	public void execute(EmergencyDepartment system){
		patient.setPatientState(PatientState.WAITING_INSTALLATION);
		system.getRegistration().getWaitingQueue().remove(this.patient);
		
	}
	
	
	

}
