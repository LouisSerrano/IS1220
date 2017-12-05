package core;
import java.util.ArrayList;

import event.*;
import core.room.*;

public class EmergencyDepartment {
	private String name;
	private int simTme;
	private ArrayList<Patient> PatientList;
	private ArrayList<Physician> PhysicianList;
	private ArrayList<Nurse> NurseList;
	private ArrayList<Room> RoomList;
	private ArrayList<Transporter> TransporterList;
	private EnabledEvent EnabledEvent;
	private EventQueue eventqueue;
	
	public EmergencyDepartment(){
		this.name="ED";
		this.simTme=0;
		this.PatientList=new ArrayList<Patient>();
		this.TransporterList=new ArrayList<Transporter>();
		this.PhysicianList= new ArrayList<Physician>();
		this.NurseList= new ArrayList<Nurse>();
		this.RoomList=new ArrayList<Room>();
		this.EnabledEvent= new EnabledEvent();
		this.eventqueue=new EventQueue();
	}
	
	/**
	 * Private constructor that creates a ED Singleton with given characteristic numbers :
	 * @param transporterNb
	 * @param nurseNb
	 * @param physicianNb
	 * @param boxRoomNb
	 * @param shockRoomNb
	 * @param labRoomNb
	 * @param mriRoomNb
	 * @param xRayRoomNb
	 */
	private EmergencyDepartment(int transporterNb, int nurseNb, int physicianNb, int boxRoomNb, 
								int shockRoomNb, int labRoomNb, int mriRoomNb, int xRayRoomNb){
		this.name="ED";
		this.simTme=0;
		this.PatientList=new ArrayList<Patient>();
		this.TransporterList=new ArrayList<Transporter>();
		this.NurseList= new ArrayList<Nurse>();
		this.PhysicianList= new ArrayList<Physician>();
		this.RoomList=new ArrayList<Room>();
		this.EnabledEvent= new EnabledEvent();
		this.eventqueue=new EventQueue();
		
		for (int i = 1; i < transporterNb; i++) {
			Transporter t = new Transporter("", "");
			TransporterList.add(t);
		}
		for (int i = 1; i < nurseNb; i++) {
			Nurse n = new Nurse("", "");
			NurseList.add(n);
		}
		for (int i = 1; i < physicianNb; i++) {
			Physician p = new Physician("", "");
			PhysicianList.add(p);
		}
		for (int i = 1; i < boxRoomNb; i++) {
			BoxRoom r = new BoxRoom("");
			RoomList.add(r);
		}
		for (int i = 1; i < shockRoomNb; i++) {
			ShockRoom r = new ShockRoom("");
			RoomList.add(r);
		}
		for (int i = 1; i < labRoomNb; i++) {
			LaboratoryRoom r = new LaboratoryRoom("");
			RoomList.add(r);
		}
		for (int i = 1; i < mriRoomNb; i++) {
			MriRoom r = new MriRoom("");
			RoomList.add(r);
		}
		for (int i = 1; i < xRayRoomNb; i++) {
			RadiographyRoom r = new RadiographyRoom("");
			RoomList.add(r);
		}
	}
	
	
	//#### ED CHARACTERISTIC NUMBERS ####
	
	private static int transporterNb;
	private static int nurseNb;
	private static int physicianNb; 
	private static int boxRoomNb; 
	private static int shockRoomNb; 
	private static int labRoomNb; 
	private static int mriRoomNb; 
	private static int xRayRoomNb;
	
	/**
	 * Pre-initialised ED unique instance
	 */
	private static EmergencyDepartment INSTANCE = new EmergencyDepartment(transporterNb, 
			nurseNb, physicianNb, boxRoomNb, shockRoomNb, labRoomNb, mriRoomNb, xRayRoomNb);

	//#### CHARACTERITIC NUMBERS SETTERS ####
	
	public void setTransporterNb(int nb){
		transporterNb = nb;
	}
	
	public void setNurseNb(int nb){
		nurseNb = nb;
	}
	
	public void setPhysicianNb(int nb){
		physicianNb = nb;
	}
	
	public void setBoxRoomNb(int nb){
		boxRoomNb = nb;
	}
	
	public void setShockRoomNb(int nb){
		shockRoomNb = nb;
	}
	
	public void setLabRoomNb(int nb){
		labRoomNb = nb;
	}
	
	public void setMriRoomNb(int nb){
		mriRoomNb = nb;
	}
	
	public void setXrayRoomNb(int nb){
		xRayRoomNb = nb;
	}
	
	/**
	 * To get the unique instance of the ED
	 * @return
	 */
	public static EmergencyDepartment getEDInstance() {
		return INSTANCE;
	}
	
	/*
	public Nurse getFreeNurse(){
		
	}
	
	public Physician getFreePhysician(){
		
	}
	
	public Room getFreeRoom(){
		
	}
	
	public Transporter getFreeTransporter(){
		
	}
	
	public Patient getNextPatient(State state){
		ArrayList<Patient> list= new ArrayList<Patient>();
		Patient result = this.PatientList.get(0);
		for(Patient patient : this.PatientList){
			if (result.co)
				
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
	*/
	
	public String toString() {
		return "EMERGENCY DPT : [TRANSPORTERS :" + transporterNb + ", NURSES :" + nurseNb + ", PHYSICIANS :"
				+ physicianNb + ", BOX ROOMS :" + boxRoomNb + ", SHOCK ROOMS :" + shockRoomNb + 
				", LABORATORY ROOMS :" + labRoomNb + ", MRI ROOMS :" + mriRoomNb + ", RADIOGRAPHY ROOMS :"
				+ xRayRoomNb + "]";
	}
}