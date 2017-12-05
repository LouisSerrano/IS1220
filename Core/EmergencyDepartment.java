package Core;
import java.util.ArrayList;

import Event.*;

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
	private Registration registration;
	private Installation installation;
	private Consultation consultation;
	private Transportation transportation;
	private MRI mri ;
	private XRAY xray;
	private Blood blood;
	
	
	public Registration getRegistration() {
		return registration;
	}


	public Installation getInstallation() {
		return installation;
	}


	public Consultation getConsultation() {
		return consultation;
	}


	public Transportation getTransportation() {
		return transportation;
	}


	public MRI getMri() {
		return mri;
	}


	public XRAY getXray() {
		return xray;
	}


	public Blood getBlood() {
		return blood;
	}


	public void setMri(MRI mri) {
		this.mri = mri;
	}


	public void setXray(XRAY xRay) {
		this.xray = xRay;
	}


	public void setBlood(Blood blood) {
		this.blood = blood;
	}


	public void setRegistration(Registration registration) {
		this.registration = registration;
	}


	public void setInstallation(Installation installation) {
		this.installation = installation;
	}


	public void setConsultation(Consultation consultation) {
		this.consultation = consultation;
	}


	public void setTransportation(Transportation transportation) {
		this.transportation = transportation;
	}


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
	
	
	public Nurse getFreeNurse(){
		Nurse result = null;
		for(Nurse nurse: this.NurseList){
			if (nurse.getHumanResourceState().equals("IDLE")){
				result= nurse;
				break;
			}
		}
		return result;
		
	}
	
	public Physician getFreePhysician(){
		Physician result = null;
		for(Physician physician: this.PhysicianList){
			if (physician.getHumanResourceState().equals("IDLE")){
				result = physician;
				break;
				}
		}
		return result;
	}
	
	public Room getFreeRoom(){
		Room result = null;

		for( Room room : this.RoomList){
			if (room.getRoomState().equals("ILDE")){
				result= room;
				break;
			}
		}
		return result;
		
	}
	
	public Transporter getFreeTransporter(){
		Transporter result = null;
		for(Transporter transporter: this.TransporterList){
			if (transporter.getHumanResourceState().equals("IDLE")){
				result = transporter;
				break;
				}
		}
		return result;
		
	}
	
	

	public EventQueue getEventqueue() {
		return eventqueue;
	}

	public void setEventqueue(EventQueue eventqueue) {
		this.eventqueue = eventqueue;
	}


	public ArrayList<Transporter> getTranspoterList() {
		return TransporterList;
	}

	public void setTranspoterList(ArrayList<Transporter> transporterList) {
		TransporterList = transporterList;
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