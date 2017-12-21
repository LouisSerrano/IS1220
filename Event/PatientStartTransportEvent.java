package Event;

import Core.EmergencyDepartment;
import Core.HealthService;
import Core.HumanResourceState;
import Core.Patient;
import Core.PatientState;
import Core.Room;
import Core.Transporter;
import Core.Distribution.ConsultationReqProbability;
import Core.Distribution.Dirac;
import Core.Distribution.Uniform;

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

		transporter.setHumanResourceState(HumanResourceState.VISITING);
		patient.setPatientState(PatientState.BEING_TRANSPORTED);
		int t = new Dirac(5).generateSample();
		system.getEventqueue().getNextEvents().add(new PatientEndTransportEvent(system.getSimTime()+t,patient,transporter));
		
	}
	
	

}
