package event;

import core.EmergencyDepartment;
import core.HumanResourceState;
import core.Nurse;
import core.Patient;
import core.PatientState;
import core.Physician;
import core.SeverityLevel;
import core.Transporter;
import core.room.Room;
import core.room.RoomState;

public enum EventType {
	ARR1("PatientArrival1"),
	ARR2("PatientArrival2"),
	ARR3("PatientArrival3"),
	ARR4("PatientArrival4"),
	ARR5("PatientArrival5"),
	REGIST("RegistrationEvent"),
	START_INSTAL("PatientStartInstallationEvent"),
	END_INSTAL("PatientEndInstallationEvent"),
	START_VISIT("PatientStartConsultationEvent"),
	END_VISIT("PatientEndConsultationEvent"),
	START_TRANSPORT("PatientStartTransportationEvent"),
	END_TRANSPORT("PatientEndTransportationEvent"),
	START_BLOOD("PatientStartBloodEvent"),
	END_BLOOD("PatientEndBloodEvent"),
	START_XRAY("PatientStartXrayEvent"),
	END_XRAY("PatientEndXrayEvent"),
	START_MRI("PatientStartMriEvent"),
	END_MRI("PatientEndMriEvent"),
	VERDICT("PatientDeparture");
	
	public String name;
	public boolean available;
	
	
	EventType(String name){
		this.name=name;
		this.available= false; 
	}
	

	
	
	@SuppressWarnings("unlikely-arg-type")
	public void updateAvailability (EmergencyDepartment system){
		
		boolean arrivingPatientEventNotInQueue =true;
		boolean waitingPatient=false;
		boolean waitingHighPriorityPatient=false;
		boolean waitingLowPriorityPatient=false;
		boolean availableNurse=false;
		boolean availableBoxRoom=false;
		boolean availableShockRoom=false;
		boolean availableXrayRoom= false;
		boolean availableMriRoom= false;
		boolean availableBloodRoom= false;
		boolean availablePhysician=false;
		boolean availableTransporter=false;
		boolean leavingPatient= false;
		
		if(this.name=="PatientArrival1") {
			
			
			if(system.getEventqueue().getNextEvents().size()>0) {
			for(Event e : system.getEventqueue().getNextEvents()) {
				if(e.getType().equals(EventType.ARR1)) {
					
					arrivingPatientEventNotInQueue=false;
					break;
				}
			}
			}
				this.available=arrivingPatientEventNotInQueue;
		}
		if(this.name=="PatientArrival2") {
			if(system.getEventqueue().getNextEvents().size()>0) {
				for(Event e : system.getEventqueue().getNextEvents()) {
					if(e.getType().equals(EventType.ARR2)) {
						arrivingPatientEventNotInQueue=false;
						break;
					}
				}		
			}
			this.available=arrivingPatientEventNotInQueue;
		}
		if(this.name=="PatientArrival3") {
			if(system.getEventqueue().getNextEvents().size()>0) {
				for(Event e : system.getEventqueue().getNextEvents()) {
					if(e.getType().equals(EventType.ARR3)) {
						arrivingPatientEventNotInQueue=false;
						break;
					}
				}
			}
			this.available=arrivingPatientEventNotInQueue;
		
		}
		if(this.name=="PatientArrival4") {
			if(system.getEventqueue().getNextEvents().size()>0) {
				for(Event e : system.getEventqueue().getNextEvents()) {
					if(e.getType().equals(EventType.ARR4)) {
						arrivingPatientEventNotInQueue=false;
						break;
					}
				}		
			}
			this.available=arrivingPatientEventNotInQueue;
		}
		if(this.name=="PatientArrival5") {
			if(system.getEventqueue().getNextEvents().size()>0) {
				for(Event e : system.getEventqueue().getNextEvents()) {
					if(e.getType().equals(EventType.ARR5)) {
						arrivingPatientEventNotInQueue=false;
						break;
					}
				}
					
			}
			this.available=arrivingPatientEventNotInQueue;
		}
		
		if (this.name.equals("RegistrationEvent")){
			if(!system.getRegistration().getWaitingQueue().isEmpty()){
				waitingPatient=true;
			}
			
			for (Nurse nurse:system.getNurseList()){
				if (nurse.getHumanResourceState().equals(HumanResourceState.IDLE)){
					availableNurse=true;
					break;	
				}
			}
			this.available=waitingPatient && availableNurse;
			}
			
		
		if (this.name.equals("PatientStartInstallationEvent")){
			int i = 0;
			int p = 0;
			int n= system.getPatientList().size();
			int m = system.getRoomList().size();
			
			while (i<n && (waitingHighPriorityPatient==false ||  waitingLowPriorityPatient==false)){
				Patient patient=system.getPatientList().get(i);
				if (patient.getPatientState().equals(PatientState.WAITING_INSTALLATION)){
					if(patient.getSeverityLevel().equals(SeverityLevel.L1) || patient.getSeverityLevel().equals(SeverityLevel.L2 )){
						waitingHighPriorityPatient=true;
					}
					else{
						waitingLowPriorityPatient=true;
					}			
				}
				i+=1;	
		    }
				
			for (Nurse nurse:system.getNurseList()){
				if (nurse.getHumanResourceState().equals(HumanResourceState.IDLE)){
					availableNurse=true;
					break;	
						}
			}
			
			while (p<m && (availableShockRoom==false ||  availableBoxRoom==false)){
				Room room = system.getRoomList().get(p);
				if (room.getType().equals("SHOCK_ROOM")&&room.getRoomState().equals(RoomState.AVAILABLE)){
					availableShockRoom=true;
				}
				if (room.getType().equals("BOX_ROOM")&&room.getRoomState().equals(RoomState.AVAILABLE)){
					availableBoxRoom=true;
					}
				p+=1;
				}
			this.available=(availableNurse && ((availableShockRoom && waitingHighPriorityPatient )||(availableBoxRoom && waitingLowPriorityPatient)) );
			}
		
			
		
		
		if (this.name.equals("PatientStartConsultationEvent")){
			if(!system.getConsultation().getWaitingQueue().isEmpty()){
				waitingPatient=true;
			}
			for (Physician physician : system.getPhysicianList()){
				if (physician.getHumanResourceState().equals(HumanResourceState.IDLE)){
					availablePhysician=true;
					break;
				}
			}
			this.available=(waitingPatient && availablePhysician);
			}
		
		
		
			
		
		if (this.name.equals("PatientStartTransportationEvent")){
			if(!system.getTransportation().getWaitingQueue().isEmpty()){
				waitingPatient=true;
			}
			
			for (Transporter transporter: system.getTranspoterList()){
				if (transporter.getHumanResourceState().equals(HumanResourceState.IDLE)){
					availableTransporter=true;
					break;
				}
			}
			this.available=(waitingPatient && availableTransporter);
			}
		
		
		
		if (this.name.equals("PatientStartXrayEvent")){
			if(!system.getXray().getWaitingQueue().isEmpty()){
				waitingPatient=true;
				}
			
			
			for (Room room : system.getRoomList()){
				if (room.getType().equals("RADIOGRAPHY_ROOM")&& room.getRoomState().equals(RoomState.AVAILABLE)){
					availableXrayRoom=true;
					break;
				}
			}
			this.available= waitingPatient && availableXrayRoom;	
		}
		
		
		
		if (this.name.equals("PatientStartBloodEvent")){
			if(!system.getBlood().getWaitingQueue().isEmpty()){
				waitingPatient = true;
				
			}
			
			for (Room room : system.getRoomList()){
				if (room.getType().equals("LABORATORY_ROOM")&& room.getRoomState().equals(RoomState.AVAILABLE)){
					availableBloodRoom=true;
					break;
				}
			}
			this.available= waitingPatient && availableBloodRoom;	
		}
		
		
		
		if (this.name.equals("PatientStartMriEvent")){
			if(!system.getMri().getWaitingQueue().isEmpty()){
				waitingPatient=true;
				
			}
			
			for (Room room : system.getRoomList()){
				if (room.getType().equals("MRI_ROOM")&& room.getRoomState().equals(RoomState.AVAILABLE)){
					availableMriRoom=true;
					break;
				}
			}
			this.available= waitingPatient && availableMriRoom;	
		}
		
		
		
		
		if (this.name.equals("PatientEndInstallationEvent")){
			for (Patient patient : system.getPatientList()){
				if (patient.getPatientState().equals(PatientState.BEING_INSTALLED)){
					waitingPatient=true;
					break;
				}
			}
			this.available= waitingPatient;	
		}
			
		if (this.name.equals("PatientEndConsultationEvent")){
			for (Patient patient : system.getPatientList()){
				if (patient.getPatientState().equals(PatientState.BEING_CONSULTED)){
					waitingPatient=true;
					break;
					}
			}
			this.available= waitingPatient;	
			}
			
		if (this.name.equals("PatientEndTransportationEvent")){
			for (Patient patient : system.getPatientList()){
				if (patient.getPatientState().equals(PatientState.BEING_TRANSPORTED)){
					waitingPatient=true;
					break;
					}
			}
			this.available= waitingPatient;	
			}
		if (this.name.equals("PatientEndExaminationEvent")){
			for (Patient patient : system.getPatientList()){
				if (patient.getPatientState().equals(PatientState.BEING_EXAMINATED)){
					waitingPatient=true;
					break;
					}
			}
			this.available= waitingPatient;	
			}
		
		if (this.name.equals("PatientDeparture")) {
			for (Patient patient : system.getPatientList()) {
				if(patient.getPatientState().equals(PatientState.WAITING_RELEASE)) {
					leavingPatient =true;
					break;
				}
			}
			this.available=leavingPatient;
		}
		
		
		    }



	public String getName() {
		return name;
	}




	public boolean isAvailable() {
		return available;
	}




	public void setName(String name) {
		this.name = name;
	}




	public void setAvailable(boolean available) {
		this.available = available;
	}
	
	
	}

		
		
		
		
				
	
	
	
	


