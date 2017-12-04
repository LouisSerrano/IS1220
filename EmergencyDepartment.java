package core;

import java.util.ArrayList;

import event.EnabledEvent;
import event.EventQueue;

public class EmergencyDepartment {
	private String name;
	private int simTme;
	private ArrayList<Patient> PatientList;
	private ArrayList<Physician> PhysicianList;
	private ArrayList<Nurse> NurseList;
	private ArrayList<Room> RoomList;
	private ArrayList<Transporter> TranspoterList;
	private EnabledEvent EnabledEvent;
	private EventQueue eventqueue;
	
	public EmergencyDepartment(){
		this.name="ED";
		this.simTme=0;
		this.PatientList=new ArrayList<Patient>();
		this.TranspoterList=new ArrayList<Transporter>();
		this.PhysicianList= new ArrayList<Physician>();
		this.NurseList= new ArrayList<Nurse>();
		this.RoomList=new ArrayList<Room>();
		this.EnabledEvent= new EnabledEvent();
		this.eventqueue=new EventQueue();
	}
	
	
	public Nurse getFreeNurse(){
		
	}
	
	public Physician getFreePhysician(){
		
	}
	
	public Room getFreeRoom(){
		
	}
	
	public Transporter getFreeTransporter(){
		
	}
	
	public Patient getNextPatient(PatientState state){
		ArrayList<Patient> list= new ArrayList<Patient>();
		Patient result = this.PatientList.get(0);
		for(Patient patient : this.PatientList){
			if (result.co)
				
			}
			
		}
		
	}
	
	
	
	
	
	
	
	

	public EventQueue getEventqueue() {
		return eventqueue;
	}

	public void setEventqueue(EventQueue eventqueue) {
		this.eventqueue = eventqueue;
	}


	public ArrayList<Transporter> getTranspoterList() {
		return TranspoterList;
	}

	public void setTranspoterList(ArrayList<Transporter> transpoterList) {
		TranspoterList = transpoterList;
	}

	public ArrayList<Room> getRoomList() {
		return RoomList;
	}

	public void setRoomList(ArrayList<Room> roomList) {
		RoomList = roomList;
	}

	private EventQueue NextEvents;
	public EventQueue getNextEvents() {
		return NextEvents;
	}

	public void setNextEvents(EventQueue nextEvents) {
		NextEvents = nextEvents;
	}


	public String getName() {
		return name;
	}

	public int getSimTme() {
		return simTme;
	}

	public ArrayList<Patient> getPatientList() {
		return PatientList;
	}

	public ArrayList<Physician> getPhysicianList() {
		return PhysicianList;
	}

	public ArrayList<Nurse> getNurseList() {
		return NurseList;
	}


	public EnabledEvent getEnabledEventList() {
		return EnabledEvent;
	}


	public void setEnabledEventList(EnabledEvent enabledEventList) {
		EnabledEvent = enabledEventList;
	}


	public void setName(String name) {
		this.name = name;
	}

	public void setSimTme(int simTme) {
		this.simTme = simTme;
	}

	public void setPatientList(ArrayList<Patient> patientList) {
		PatientList = patientList;
	}

	public void setPhysicianList(ArrayList<Physician> physicianList) {
		PhysicianList = physicianList;
	}

	public void setNurseList(ArrayList<Nurse> nurseList) {
		NurseList = nurseList;
	}

	
	
	
	
	
}
