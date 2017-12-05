package event;

import core.EmergencyDepartment;
import core.healthService.HealthService;
import core.HumanResourceState;
import core.Patient;
import core.PatientState;
import core.Room;
import core.Transporter;
import core.distribution.ConsultationReqProbability;

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
