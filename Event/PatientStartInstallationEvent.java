package Event;

import Core.EmergencyDepartment;
import Core.HumanResourceState;
import Core.Nurse;
import Core.Patient;
import Core.PatientState;
import Core.Room;
import Core.RoomState;
import Core.Distribution.Dirac;
import Core.Distribution.Uniform;

public class PatientStartInstallationEvent extends Event {
	private Patient patient;
	private Nurse nurse;
	private Room room;
	
	
	public PatientStartInstallationEvent(int timeStamp, Patient patient, Nurse nurse, Room room){
		super("START OF INSTALLATION"+patient.getName(),EventType.START_INSTAL,timeStamp);
		this.patient=patient;
		this.nurse=nurse;
		this.room=room;
	}
	
	@Override
	public void execute(EmergencyDepartment system){
		super.execute(system);

		patient.setPatientState(PatientState.BEING_INSTALLED);
		patient.setRoom(room);
		room.setRoomState(RoomState.USED);
		system.getInstallation().getWaitingQueue().remove(this.patient);
		nurse.setHumanResourceState(HumanResourceState.VISITING);
		int t = new Dirac(2).generateSample();
		system.getEventqueue().getNextEvents().add(new PatientEndInstallationEvent(system.getSimTime()+t,patient,nurse));
		
		
	}
	
	
	

}
