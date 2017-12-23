package event;




import java.util.ArrayList;
import java.util.Collections;

import Core.EmergencyDepartment;
import Core.Patient;
import Core.PatientCreator;
import Core.SeverityLevel;
import Core.Room;
import Core.Nurse;
import Core.Physician;


public class EventQueue {
	private ArrayList<Event> nextEvents;
	
	public ArrayList<Event> getNextEvents() {
		return nextEvents;
	}

	public void setNextEvents(ArrayList<Event> nextEvents) {
		this.nextEvents = nextEvents;
	}

	public EventQueue(){
		this.nextEvents=new ArrayList<Event>();
	}
	
	
	/**
	 * Il faut toujours donner la priorité aux événements qui ont lieu après dans la chaine:
	 * Registration sur Arrival,
	 * Installation sur Registration et EndTransport,
	 * Consultation sur EndInstallation,
	 * Transportation sur EndConsultation,
	 * Examination sur EndTransportation
	 * Start Transportation sur EndExamination
	 */
	public void sort(){
		int n=this.nextEvents.size();
		for(int j=1; j< n;j++){
			Event e = nextEvents.get(j);
			int key= nextEvents.get(j).getTimeStamp();
			int i=j-1;
			while(i>=0 && nextEvents.get(i).getTimeStamp()>key) {
				nextEvents.set(i+1, nextEvents.get(i));
				i=i-1;
			}
			nextEvents.set(i+1, e);
		}
		int i = 0;
		while(i< n && this.nextEvents.get(0).getTimeStamp()==this.nextEvents.get(i).getTimeStamp()){
			i+=1;
		}
		for(int j = 1; j<i; j++) {
			if((this.nextEvents.get(0).getType().equals(EventType.ARR1)
				||this.nextEvents.get(0).getType().equals(EventType.ARR2)
				||this.nextEvents.get(0).getType().equals(EventType.ARR3)
				||this.nextEvents.get(0).getType().equals(EventType.ARR4)
				||this.nextEvents.get(0).getType().equals(EventType.ARR5))
				&&this.nextEvents.get(j).getType().equals(EventType.REGIST)) {
				Collections.swap(this.nextEvents, 0, j);
			}
			if(this.nextEvents.get(0).getType().equals(EventType.REGIST)&&(this.nextEvents.get(j).getType().equals(EventType.START_INSTAL)
					||this.nextEvents.get(j).getType().equals(EventType.END_TRANSPORT))){
				Collections.swap(this.nextEvents, 0, j);
			}
			if(this.nextEvents.get(0).getType().equals(EventType.END_INSTAL)&&(this.nextEvents.get(j).getType().equals(EventType.START_VISIT))){
				Collections.swap(this.nextEvents, 0, j);
			}
			if(this.nextEvents.get(0).getType().equals(EventType.END_VISIT)&&(this.nextEvents.get(j).getType().equals(EventType.START_TRANSPORT))){
				Collections.swap(this.nextEvents, 0, j);
			}
			if((this.nextEvents.get(0).getType().equals(EventType.START_MRI)
					||this.nextEvents.get(0).getType().equals(EventType.START_XRAY)
					||this.nextEvents.get(0).getType().equals(EventType.START_BLOOD)
					&&this.nextEvents.get(j).getType().equals(EventType.END_TRANSPORT))) {
					Collections.swap(this.nextEvents, 0, j);
				}
			if((this.nextEvents.get(0).getType().equals(EventType.END_TRANSPORT)&&(
					this.nextEvents.get(j).getType().equals(EventType.START_MRI)
					||this.nextEvents.get(j).getType().equals(EventType.START_XRAY)
					||this.nextEvents.get(j).getType().equals(EventType.START_BLOOD)))){
					Collections.swap(this.nextEvents, 0, j);
				}
			
			
		}
		
	}
	
	
		
	/**
	 * Cette fonction ne permet pas de rajouter une instance de fin d'événement; ie PatientEndInstallationEvent, PatientEndConsultationEvent... 
	 * parce que c'est trop complqiué à gérer. Les instances sont automatiquement ajoutées lorsque les PatientStartInstallationEvent... sont éxecutées.
	 * @param eT
	 * @param system
	 * @return 
	 */
	public static void addInstanceOfEventType(EventType eT, EmergencyDepartment system){
		
		if(eT.equals(EventType.ARR1)){
			Patient newpatient= system.getPatientCreator().newPatient(SeverityLevel.L1);
			system.getEventqueue().getNextEvents().add(new Arrival1Event(newpatient));
			
			

		}
		
		if(eT.equals(EventType.ARR2)){
			Patient newpatient= system.getPatientCreator().newPatient(SeverityLevel.L2);
			system.getEventqueue().getNextEvents().add(new Arrival2Event(newpatient));
			
			


		}
		if(eT.equals(EventType.ARR3)){
			Patient newpatient= system.getPatientCreator().newPatient(SeverityLevel.L3);
			system.getEventqueue().getNextEvents().add(new Arrival3Event(newpatient));
			
			


		}
		if(eT.equals(EventType.ARR4)){
			Patient newpatient= system.getPatientCreator().newPatient(SeverityLevel.L4);
			system.getEventqueue().getNextEvents().add(new Arrival4Event(newpatient));
			
			


		}
		if(eT.equals(EventType.ARR5)){
			Patient newpatient= system.getPatientCreator().newPatient(SeverityLevel.L5);
			system.getEventqueue().getNextEvents().add(new Arrival5Event(newpatient));
			
	
		}
		
		if (eT.equals(EventType.REGIST)){
			system.getEventqueue().getNextEvents().add(new PatientRegistrationEvent(
					system.getSimTime(), system.getRegistration().getWaitingQueue().get(0), system.getFreeNurse()));	
		}
		
		if (eT.equals(EventType.START_INSTAL)){
			Patient patient = system.getInstallation().getWaitingQueue().get(0);
			SeverityLevel level= patient.getSeverityLevel();
			
			if(level.equals(SeverityLevel.L1) || level.equals(SeverityLevel.L2)){
				
				system.getEventqueue().getNextEvents().add(new PatientStartInstallationEvent(
					system.getSimTime(), system.getInstallation().getWaitingQueue().get(0), system.getFreeNurse(), system.getFreeRoom("SHOCK_ROOM")));
			}
			else {
				system.getEventqueue().getNextEvents().add(new PatientStartInstallationEvent(
						system.getSimTime(), system.getInstallation().getWaitingQueue().get(0), system.getFreeNurse(), system.getFreeRoom("BOX_ROOM")));
				
			}
		}
		
		if (eT.equals(EventType.START_VISIT)){
			
			system.getEventqueue().getNextEvents().add(new PatientStartConsultationEvent(
					system.getSimTime(), system.getConsultation().getWaitingQueue().get(0), system.getFreePhysician()));
			
		}
		if (eT.equals(EventType.START_TRANSPORT)){
			system.getEventqueue().getNextEvents().add(new PatientStartTransportEvent(
					system.getSimTime(), system.getTransportation().getWaitingQueue().get(0), system.getFreeTransporter()));
			
		}
		 
	
		
		
		if (eT.equals(EventType.START_XRAY)){
			system.getEventqueue().getNextEvents().add(new PatientStartXrayEvent(
					system.getSimTime(), system.getXray().getWaitingQueue().get(0), system.getFreeRoom("RADIOGRAPHY_ROOM")));
			
		}
		if (eT.equals(EventType.START_MRI)){
			system.getEventqueue().getNextEvents().add(new PatientStartMriEvent(
					system.getSimTime(), system.getMri().getWaitingQueue().get(0), system.getFreeRoom("MRI_ROOM")));
			
		}
		if (eT.equals(EventType.START_BLOOD)){
			system.getEventqueue().getNextEvents().add(new PatientStartBloodEvent(
					system.getSimTime(), system.getBlood().getWaitingQueue().get(0), system.getFreeRoom("LABORATORY_ROOM")));
			}
	}
		
		
		
		
		public void deleteInstancesOfEventType(EventType eT){
			int n = this.nextEvents.size();
			int i = 0;
			while(i<n) {
				Event e = this.nextEvents.get(i);
				if(e.getType().equals(eT)){
					this.nextEvents.remove(i);
					i=i-1;
					n=n-1;
				}
				i=i+1;
			}
		}
		
		
		
		public static EventQueue updateEventQueue(EnabledEvent newEnabledEvents, EnabledEvent oldEnabledEvents, EmergencyDepartment system) {
			ArrayList<EventType> newlyEnabledEvents = new ArrayList<EventType>();
			ArrayList<EventType> newlyDisabledEvents = new ArrayList<EventType>();
			
			if(oldEnabledEvents.getAbledList().size()==0) {
				newlyEnabledEvents = newEnabledEvents.getAbledList();
				System.out.println("Newly EnabledEvents :"+newlyEnabledEvents);
			}
			
			else {
				
			
			for(EventType eT: newEnabledEvents.getAbledList()) {
				boolean newly = true;
				for(EventType eventType: oldEnabledEvents.getAbledList()) {
					if(eT.equals(eventType)) {
						newly =false;
						break;
					}
				}
			    if(newly==true) {
			    		newlyEnabledEvents.add(eT);
				}
			}
			for(EventType eT: oldEnabledEvents.getAbledList()) {
				boolean oldly = true;
				for(EventType eventType: newEnabledEvents.getAbledList()) {
					if(eT.equals(eventType)) {
						oldly =false;
					}
				}
				
			if(oldly==true) {
				newlyDisabledEvents.add(eT);
				}
			}
			}
			
			if(newEnabledEvents.getDisabledList().size()==0) {
				newlyDisabledEvents=oldEnabledEvents.getDisabledList();

			}
			
			
			
			for (EventType eT: newlyDisabledEvents) {
				system.getEventqueue().deleteInstancesOfEventType(eT);
			}
			for (EventType eT: newlyEnabledEvents) {
				EventQueue.addInstanceOfEventType(eT, system);	
			}
			
			system.getEventqueue().sort();
			EnabledEvent.update2(system);

			return system.getEventqueue();
			
		}
		
		public String toString() {
			String result= "";
			result+="\nL'EventQueue vaut : \n";
			for(Event e : this.nextEvents) {
				result += e.toString() + "\n";
			}
			return result;
		}
		
		
		
		
	}
	
		
	


