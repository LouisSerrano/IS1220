package event;

import java.util.ArrayList;

import core.EmergencyDepartment;

public class EnabledEvent {
	private ArrayList<EventType> abledList;
	private ArrayList<EventType> disabledList;
	
	
	
	public EnabledEvent(){
		ArrayList<EventType> abledList=new ArrayList<EventType>();
		ArrayList<EventType> disabledList= new ArrayList<EventType>();
		for (EventType eT: EventType.values()){
			if (eT.available==true){
				abledList.add(eT);
			}
			else{
				disabledList.add(eT);
			}	
		}
	}
	
	
	public EnabledEvent(EmergencyDepartment system){
		ArrayList<EventType> abledList=new ArrayList<EventType>();
		ArrayList<EventType> disabledList= new ArrayList<EventType>();
		
		for (EventType eT: EventType.values()){
			eT.updateAvailability(system);
			if (eT.available==true){
				abledList.add(eT);
			}
			else{
				disabledList.add(eT);
			}
		}

	}
	
	
	public void update(EmergencyDepartment system){
		EnabledEvent old= system.getEnabledEventList();
		for (EventType eT : old.getDisabledList()){
			eT.updateAvailability(system);
			if (eT.available==true){
				old.abledList.add(eT);
				old.disabledList.remove(eT);
			}	
		}	
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
