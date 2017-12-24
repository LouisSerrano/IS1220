package event;

import core.EmergencyDepartment;
import core.HumanResourceState;
import core.Patient;
import core.PatientState;
import core.Transporter;
import core.distribution.ConsultationReqProbability;
import core.distribution.Dirac;
import core.distribution.Uniform;
import core.healthservice.HealthService;
import core.room.Room;

public class PatientStartTransportEvent extends Event {
	
	private Patient patient;
	private Transporter transporter;
	
	public PatientStartTransportEvent(int timeStamp, Patient patient, Transporter transporter){
		super("START TRANSPORT OF"+patient.getName(),EventType.START_TRANSPORT,timeStamp);
		this.patient=patient;
		this.transporter= transporter;
		}
	@Override
	public void execute(EmergencyDepartment system){
		super.execute(system);
		system.getTransportation().getWaitingQueue().remove(patient);
		transporter.setHumanResourceState(HumanResourceState.VISITING);
		patient.setPatientState(PatientState.BEING_TRANSPORTED);
		int t = new Dirac(5).generateSample();
		system.getEventqueue().getNextEvents().add(new PatientEndTransportEvent(system.getSimTime()+t,patient,transporter));
		patient.updateHistory(this, getTimeStamp());
		this.toString();

	}
	
	public String toString() {
		return "Transporter "+transporter.getName()+"starts transport of the Patient " + patient.getName() +  "at the time " + this.getTimeStamp()+"to "+patient.getDirection();
		
	}
	
	

}
