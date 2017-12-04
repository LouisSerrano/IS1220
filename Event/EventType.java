package event;

import core.EmergencyDepartment;
import core.Nurse;
import core.Patient;
import core.Room;
import core.Physician;
import core.SeverityLevel;
import core.Transporter;

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
		
			for (Patient patient : system.getPatientList()){
				if (patient.getState().equals("WAITING_REGISTRATION")){
					waitingPatient=true;
					break;
				}
			}
			for (Nurse nurse:system.getNurseList()){
				if (nurse.getState().equals("IDLE")){
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
				if (patient.getState().equals("WAITING_INSTALLATION")){
					if(patient.getLevel()==SeverityLevel.L1 || patient.getLevel()==SeverityLevel.L2 ){
						waitingHighPriorityPatient=true;
					}
					else{
						waitingLowPriorityPatient=true;
					}
				i+=1;				}
		    }
				
			for (Nurse nurse:system.getNurseList()){
				if (nurse.getState().equals("IDLE")){
					availableNurse=true;
					break;	
						}
			}
			while (p<n && (availableShockRoom==false ||  availableBoxRoom==false)){
				Room room = system.getRoomList().get(p);
				if (room.getType().equals("SOCKROOM")&&room.getState().equals("AVAILABLE")){
					availableShockRoom=true;
				}
				if (room.getType().equals("BOXROOM")&&room.getState().equals("AVAILABLE")){
					availableBoxRoom=true;
					}
				p+=1;
				}
			this.available=(availableNurse && ((availableShockRoom && waitingHighPriorityPatient )||(availableBoxRoom && waitingLowPriorityPatient)) );
			}
		
			
		if (this.name.equals("PatientStartConsultationEvent")){
			for (Patient patient : system.getPatientList()){
				if (patient.getState().equals("WAITING_CONSULTATION")){
					waitingPatient=true;
					break;
				}
			}
			for (Physician physician : system.getPhysicianList()){
				if (physician.getState().equals("IDLE")){
					availablePhysician=true;
					break;
				}
			}
			this.available=(waitingPatient && availablePhysician);
			}
		
			
		if (this.name.equals("PatientStartTransportationEvent")){
			
			for (Patient patient : system.getPatientList()){
				if (patient.getState().equals("WAITING_TRANSPORTATION")){
					waitingPatient=true;
					break;
				}
			}
			for (Transporter transporter: system.getTranspoterList()){
				if (transporter.getState().equals("IDLE")){
					availableTransporter=true;
					break;
				}
			}
			this.available=(waitingPatient && availableTransporter);
			}
		if (this.name.equals("PatientStartXrayEvent")){
			for (Patient patient : system.getPatientList()){
				if (patient.getState().equals("WAITING_XRAY")){
					waitingPatient=true;
					break;
				}
			}
			for (Room room : system.getRoomList()){
				if (room.getType().equals("XRAY")&& room.getState().equals("IDLE")){
					availableXrayRoom=true;
					break;
				}
			}
			this.available= waitingPatient && availableXrayRoom;	
		}
		
		if (this.name.equals("PatientStartBloodEvent")){
			for (Patient patient : system.getPatientList()){
				if (patient.getState().equals("WAITING_BLOOD")){
					waitingPatient=true;
					break;
				}
			}
			for (Room room : system.getRoomList()){
				if (room.getType().equals("BLOOD")&& room.getState().equals("IDLE")){
					availableBloodRoom=true;
					break;
				}
			}
			this.available= waitingPatient && availableBloodRoom;	
		}
		
		if (this.name.equals("PatientStartMriEvent")){
			for (Patient patient : system.getPatientList()){
				if (patient.getState().equals("WAITING_MRI")){
					waitingPatient=true;
					break;
				}
			}
			for (Room room : system.getRoomList()){
				if (room.getType().equals("MRI")&& room.getState().equals("IDLE")){
					availableMriRoom=true;
					break;
				}
			}
			this.available= waitingPatient && availableMriRoom;	
		}
		
		if (this.name.equals("PatientEndInstallationEvent")){
			for (Patient patient : system.getPatientList()){
				if (patient.getState().equals("BEING_INSTALLED")){
					waitingPatient=true;
					break;
				}
			}
			this.available= waitingPatient;	
		}
			
		if (this.name.equals("PatientEndConsultationEvent")){
			for (Patient patient : system.getPatientList()){
				if (patient.getState().equals("BEING_CONSULTED")){
					waitingPatient=true;
					break;
					}
			}
			this.available= waitingPatient;	
			}
			
		if (this.name.equals("PatientEndTransportationEvent")){
			for (Patient patient : system.getPatientList()){
				if (patient.getState().equals("BEING_TRANSPORTED")){
					waitingPatient=true;
					break;
					}
			}
			this.available= waitingPatient;	
			}
		if (this.name.equals("PatientEndExaminationEvent")){
			for (Patient patient : system.getPatientList()){
				if (patient.getState().equals("BEING_EXAMINATED")){
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

		
		
		
		
				
	
	
	
	


