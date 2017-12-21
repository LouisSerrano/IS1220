package Event;

import Core.EmergencyDepartment;
import Core.Nurse;
import Core.Patient;
import Core.Room;
import Core.Physician;
import Core.SeverityLevel;
import Core.Transporter;

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
		
		if(this.name=="PatientArrival1") {
			this.available=true;
		}
		if(this.name=="PatientArrival2") {
			this.available=true;
		}
		if(this.name=="PatientArrival3") {
			this.available=true;
		}
		if(this.name=="PatientArrival4") {
			this.available=true;
		}
		if(this.name=="PatientArrival5") {
			this.available=true;
		}
		else {
			this.available= false; 
		}
	}
	

	
	
	public void updateAvailability (EmergencyDepartment system){
		
	
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
		
		
		
		if (this.name.equals("RegistrationEvent")){
			if(!system.getRegistration().getWaitingQueue().isEmpty()){
				waitingPatient=true;
			}
			
			for (Nurse nurse:system.getNurseList()){
				if (nurse.getHumanResourceState().equals("IDLE")){
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
			
			while (i<n && (waitingHighPriorityPatient==false ||  waitingLowPriorityPatient==false)){
				Patient patient=system.getPatientList().get(i);
				if (patient.getPatientState().equals("WAITING_INSTALLATION")){
					if(patient.getSeverityLevel()==SeverityLevel.L1 || patient.getSeverityLevel()==SeverityLevel.L2 ){
						waitingHighPriorityPatient=true;
					}
					else{
						waitingLowPriorityPatient=true;
					}
				i+=1;				}
		    }
				
			for (Nurse nurse:system.getNurseList()){
				if (nurse.getHumanResourceState().equals("IDLE")){
					availableNurse=true;
					break;	
						}
			}
			
			while (p<n && (availableShockRoom==false ||  availableBoxRoom==false)){
				Room room = system.getRoomList().get(p);
				if (room.getType().equals("SOCKROOM")&&room.getRoomState().equals("AVAILABLE")){
					availableShockRoom=true;
				}
				if (room.getType().equals("BOXROOM")&&room.getRoomState().equals("AVAILABLE")){
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
				if (physician.getHumanResourceState().equals("IDLE")){
					availablePhysician=true;
					break;
				}
			}
			this.available=(waitingPatient && availablePhysician);
			}
		
		
		
			
		
		if (this.name.equals("PatientStartTransportationEvent")){
			if(!system.getRegistration().getWaitingQueue().isEmpty()){
				waitingPatient=true;
			}
			
			for (Transporter transporter: system.getTranspoterList()){
				if (transporter.getHumanResourceState().equals("IDLE")){
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
				if (room.getType().equals("XRAY")&& room.getRoomState().equals("IDLE")){
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
				if (room.getType().equals("BLOOD")&& room.getRoomState().equals("IDLE")){
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
				if (room.getType().equals("MRI")&& room.getRoomState().equals("IDLE")){
					availableMriRoom=true;
					break;
				}
			}
			this.available= waitingPatient && availableMriRoom;	
		}
		
		
		
		
		if (this.name.equals("PatientEndInstallationEvent")){
			for (Patient patient : system.getPatientList()){
				if (patient.getPatientState().equals("BEING_INSTALLED")){
					waitingPatient=true;
					break;
				}
			}
			this.available= waitingPatient;	
		}
			
		if (this.name.equals("PatientEndConsultationEvent")){
			for (Patient patient : system.getPatientList()){
				if (patient.getPatientState().equals("BEING_CONSULTED")){
					waitingPatient=true;
					break;
					}
			}
			this.available= waitingPatient;	
			}
			
		if (this.name.equals("PatientEndTransportationEvent")){
			for (Patient patient : system.getPatientList()){
				if (patient.getPatientState().equals("BEING_TRANSPORTED")){
					waitingPatient=true;
					break;
					}
			}
			this.available= waitingPatient;	
			}
		if (this.name.equals("PatientEndExaminationEvent")){
			for (Patient patient : system.getPatientList()){
				if (patient.getPatientState().equals("BEING_EXAMINATED")){
					waitingPatient=true;
					break;
					}
			}
			this.available= waitingPatient;	
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

		
		
		
		
				
	
	
	
	


