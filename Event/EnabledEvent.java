package event;

import java.util.ArrayList;

import core.EmergencyDepartment;

public class EnabledEvent {
	private ArrayList<EventType> abledList;
	private ArrayList<EventType> disabledList;
	
	/***
	 * Initialement tous les types d'événement sont désactivés, il faut faire appel à la fonction update Availability
	 */
	
	public EnabledEvent(){
		this.abledList=new ArrayList<EventType>();
		this.disabledList= new ArrayList<EventType>();
		for (EventType eT : EventType.values()) {
			this.disabledList.add(eT);
		}
	}
	
	
	
	
	public EnabledEvent(EmergencyDepartment system){
		ArrayList<EventType> abledList=new ArrayList<EventType>();
		ArrayList<EventType> disabledList= new ArrayList<EventType>();
		
		for (EventType eT: EventType.values()){
			eT.updateAvailability(system);
			if (eT.isAvailable()==true){
				abledList.add(eT);
			}
			else{
				disabledList.add(eT);
			}
		}

	}
	
	public EnabledEvent(ArrayList<EventType> enabledEvent, ArrayList<EventType> disabledEvent){
		ArrayList<EventType> abledList=new ArrayList<EventType>();
		ArrayList<EventType> disabledList= new ArrayList<EventType>();
		
		for(EventType eT : enabledEvent) {
			abledList.add(eT);
		}
		for (EventType eT : disabledEvent) {
			disabledList.add(eT);
			
		}
		this.abledList=abledList;
		this.disabledList=disabledList;
		

	}

	
	
	public static EnabledEvent update(EmergencyDepartment system){
		EnabledEvent old = system.getEnabledEventList();
		EnabledEvent newly = new EnabledEvent(old.getAbledList(), old.getDisabledList());
		int n = old.getDisabledList().size();
		int i = 0;
		
		while (i<n) {
			EventType eT = old.getDisabledList().get(i);
			eT.updateAvailability(system);
			
			if (eT.isAvailable()){
				newly.abledList.add(eT);	
				newly.disabledList.remove(eT);
				n=n-1;
			}
			i=i+1;
		}
		system.setEnabledEventList(newly);
		return newly;
	}
	
	public static void update2(EmergencyDepartment system) {
		EnabledEvent newly = new EnabledEvent();
		for(EventType eT : system.getEnabledEventList().getAbledList()) {
			eT.updateAvailability(system);
			if(eT.isAvailable()) {
				newly.getAbledList().add(eT);
				newly.getDisabledList().remove(eT);
			}
		}
		system.getEnabledEventList().setAbledList(newly.abledList);
		system.getEnabledEventList().setDisabledList(newly.disabledList);

	}
	
	public void remove(EventType eT){
		this.abledList.remove(eT);
		this.disabledList.add(eT);
	}
		
	
	
	public ArrayList<EventType> getAbledList() {
		return abledList;
	}

	public ArrayList<EventType> getDisabledList() {
		return disabledList;
	}

	public void setAbledList(ArrayList<EventType> abledList) {
		this.abledList = abledList;
	}

	public void setDisabledList(ArrayList<EventType> disabledList) {
		this.disabledList = disabledList;
	}

	

}
