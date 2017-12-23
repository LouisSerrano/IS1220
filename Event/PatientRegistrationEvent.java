package event;

import core.EmergencyDepartment;
import core.Nurse;
import core.Patient;
import core.PatientState;

public class PatientRegistrationEvent extends Event {
	private Patient patient;
	private Nurse nurse;
	
	
	public PatientRegistrationEvent(int timeStamp,Patient patient, Nurse nurse){
		super("REGISTRATION OF"+patient.getName(),EventType.REGIST, timeStamp);
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
		super.execute(system);
		patient.setPatientState(PatientState.WAITING_INSTALLATION);
		system.getRegistration().getWaitingQueue().remove(this.patient);
		system.getInstallation().addPatientToQueue(this.patient);
		this.toString();
	}
	
	public String toString() {
		
		return "Nurse "+nurse.getName()+" registrates the patient "+ patient.getName() +  " at the time " + this.getTimeStamp();
		}
	
	
	

}
