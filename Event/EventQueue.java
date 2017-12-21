package Event;
import java.util.ArrayList;

import Core.EmergencyDepartment;
import Core.Patient;
import Core.PatientCreator;
import Core.SeverityLevel;
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
	
	
	
	public void sort(){
		int n=nextEvents.size();
				
		for(int j=1; j< n-1;j++){
			Event e = nextEvents.get(j);
			int key= nextEvents.get(j).getTimeStamp();
			int i = j-1;
		
			while (i>=0 && nextEvents.get(j).getTimeStamp()>key ){
				nextEvents.set(i+1, nextEvents.get(i));
				i=i-1;	
			}
			nextEvents.set(i+1, e);
			
		}
	}
		
	/**
	 * Cette fonction ne permet pas de rajouter une instance de fin d'événement; ie PatientEndInstallationEvent, PatientEndConsultationEvent... 
	 * parce que c'est trop complqiué à gérer. Les instances sont automatiquement ajoutées lorsque les PatientStartInstallationEvent... sont éxecutées.
	 * @param eT
	 * @param system
	 */
	public void addInstanceOfEventType(EventType eT, EmergencyDepartment system){
		
		if(eT.name.equals("PatientArrival1")){
			Patient newpatient= PatientCreator.newPatient(SeverityLevel.L1);
			this.nextEvents.add(new Arrival1Event(newpatient));
		}
		
		if(eT.name.equals("PatientArrival2")){
			Patient newpatient= PatientCreator.newPatient(SeverityLevel.L2);
			this.nextEvents.add(new Arrival1Event(newpatient));
		}
		if(eT.name.equals("PatientArrival3")){
			Patient newpatient= PatientCreator.newPatient(SeverityLevel.L3);
			this.nextEvents.add(new Arrival1Event(newpatient));
		}
		if(eT.name.equals("PatientArrival4")){
			Patient newpatient= PatientCreator.newPatient(SeverityLevel.L4);
			this.nextEvents.add(new Arrival1Event(newpatient));
		}
		if(eT.name.equals("PatientArrival5")){
			Patient newpatient= PatientCreator.newPatient(SeverityLevel.L5);
			this.nextEvents.add(new Arrival1Event(newpatient));
		}
		
		if (eT.name.equals("RegistrationEvent")){
			this.nextEvents.add(new PatientRegistrationEvent(
					system.getSimTime(), system.getRegistration().getWaitingQueue().get(0), system.getFreeNurse()));	
		}
		
		if (eT.name.equals("PatientStartInstallationEvent")){
			Patient patient = system.getInstallation().getWaitingQueue().get(0);
			SeverityLevel level= patient.getSeverityLevel();
			
			if(level.equals(SeverityLevel.L1) || level.equals(SeverityLevel.L2)){
				
			this.nextEvents.add(new PatientStartInstallationEvent(
					system.getSimTime(), system.getInstallation().getWaitingQueue().get(0), system.getFreeNurse(), system.getFreeRoom("ShockRoom")));
			}
			else {
				this.nextEvents.add(new PatientStartInstallationEvent(
						system.getSimTime(), system.getInstallation().getWaitingQueue().get(0), system.getFreeNurse(), system.getFreeRoom("BoxRoom")));
				
			}
		}
		
		if (eT.name.equals("PatientStartConsultationEvent")){
			
			this.nextEvents.add(new PatientStartConsultationEvent(
					system.getSimTime(), system.getConsultation().getWaitingQueue().get(0), system.getFreePhysician()));
			
		}
		
		if (eT.name.equals("PatientStartTransportationEvent")){
			this.nextEvents.add(new PatientStartTransportEvent(
					system.getSimTime(), system.getTransportation().getWaitingQueue().get(0), system.getFreeTransporter()));
			
		}
		
		if (eT.name.equals("PatientStartXrayEvent")){
			this.nextEvents.add(new PatientStartXrayEvent(
					system.getSimTime(), system.getXray().getWaitingQueue().get(0), system.getFreeRoom("RADIOGRAPHY_ROOM")));
			
		}
		if (eT.name.equals("PatientStartMriEvent")){
			this.nextEvents.add(new PatientStartMriEvent(
					system.getSimTime(), system.getMri().getWaitingQueue().get(0), system.getFreeRoom("MRI_ROOM")));
			
		}
		if (eT.name.equals("PatientStartBloodEvent")){
			this.nextEvents.add(new PatientStartBloodEvent(
					system.getSimTime(), system.getBlood().getWaitingQueue().get(0), system.getFreeRoom("LABORATORY_ROOM")));
			
			}
		}
		
		public void deleteInstancesOfEventType(EventType eT){
			for(Event e: this.nextEvents){
				if(e.getType().equals(eT)){
					this.nextEvents.remove(e);
				}
			}
			
		}
		
		
		
		
	}
	
		
	


