package Event;

import Core.EmergencyDepartment;
import Core.HealthService;
import Core.HumanResourceState;
import Core.Patient;
import Core.PatientState;
import Core.Room;
import Core.Transporter;
import Core.Distribution.ConsultationReqProbability;

public class PatientStartTransportEvent extends Event {
	
	private Patient patient;
	private Transporter transporter;
	
	public PatientStartTransportEvent(int timeStamp, Patient patient, Transporter transporter){
		super(EventType.START_TRANSPORT,timeStamp);
		this.patient=patient;
		this.transporter= transporter;
		}
	@Override
	public void execute(EmergencyDepartment system){
		transporter.setHumanResourceState(HumanResourceState.VISITING);
		
	}
	
	

}
