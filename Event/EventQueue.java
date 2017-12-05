package event;

import java.util.ArrayList;

import core.EmergencyDepartment;
import core.PatientState;
import core.distribution.Uniform;

public class EventQueue {
	private ArrayList<Event> nextEvents;
	
	public EventQueue(){
		this.nextEvents=new ArrayList<Event>();
	}
	
	public EventQueue(EnabledEvent events, Core.EmergencyDepartment system){
		nextEvents= new ArrayList<Event>();
		for (EventType eT : events.getAbledList()){
			if (eT.name.equals("RegistrationEvent")){
				this.nextEvents.add(new PatientRegistrationEvent(
						system.getRegistration().getWaitingQueue().get(0), system.getSimTme()));
				
			}
			if (eT.name.equals("PatientStartInstallationEvent")){
				this.nextEvents.add(new PatientStartInstallationEvent(
						Installation.waitingQueue.pop(), system.getSimTme()));
				
			}
			if (eT.name.equals("PatientStartConsultationEvent")){
				this.nextEvents.add(new PatientStartConsultationEvent(
						Consultation.waitingQueue.pop(), system.getSimTme()));
				
				
			}
			if (eT.name.equals("PatientStartTransportationEvent")){
				this.nextEvents.add(new PatientStartTransportationEvent(
						Transportation.waitingQueue.pop(), system.getSimTme()));
				
			}
			if (eT.name.equals("PatientStartXrayEvent")){
				this.nextEvents.add(new PatientStartExaminationEvent(
						Xray.waitingQueue.pop(), system.getSimTme()));
				
				
			}
			if (eT.name.equals("PatientStartBloodEvent")){
				this.nextEvents.add(new PatientStartExaminationEvent(
						Blood.waitingQueue.pop(), system.getSimTme()));
				
			}
			if (eT.name.equals("PatientStartMriEvent")){
				this.nextEvents.add(new PatientStartExaminationEvent(
						Mri.waitingQueue.pop(), system.getSimTme()));
				
			}
			if (eT.name.equals("PatientEndInstallationEvent")){
				
				this.nextEvents.add(new PatientEndInstallationEvent(
						system.getNextPatient(PatientState.BI), system.getSimTme()+2));
				
			}
			if (eT.name.equals("PatientEndConsultationEvent")){
				this.nextEvents.add(new PatientEndConsultationEvent(
						system.getNextPatient(PatientState.BC), system.getSimTme()+Uniform.generateSample()));
				
			}
			if (eT.name.equals("PatientEndTransportationEvent")){
				
			}
			if (eT.name.equals("PatientEndXrayEvent")){
				
			}
			if (eT.name.equals("PatientEndBloodEvent")){
				
			}
			if (eT.name.equals("PatientEndMriEvent")){
				
			}
			
			
			
		}
	}
	
	public void addEvent(Event e){
		this.nextEvents.add(e);
	}
	
	public void deleteEvent(Event e){
		this.nextEvents.remove(e);
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

}
