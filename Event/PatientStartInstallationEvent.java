package event;

import core.EmergencyDepartment;
import core.HumanResourceState;
import core.Nurse;
import core.Patient;
import core.PatientState;
import core.distribution.Dirac;
import core.distribution.Uniform;
import core.room.Room;
import core.room.RoomState;

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
		system.getEventqueue().getNextEvents().add(new PatientEndInstallationEvent(system.getSimTime()+t,patient,nurse, room));
		this.toString();
		
	}
	
	public String toString() {
		return ("Nurse "+this.nurse.getName()+" starts installation of the Patient " + this.patient.getName() +  " at the time " + super.getTimeStamp());
	}
	
	
	

}
