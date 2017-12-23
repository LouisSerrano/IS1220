package core;
import java.util.ArrayList;

import core.distribution.LaboratoryRoom;
import core.healthservice.Blood;
import core.healthservice.Consultation;
import core.healthservice.Installation;
import core.healthservice.MRI;
import core.healthservice.Registration;
import core.healthservice.Transportation;
import core.healthservice.Xray;
import core.room.BoxRoom;
import core.room.MriRoom;
import core.room.RadiographyRoom;
import core.room.Room;
import core.room.RoomState;
import core.room.ShockRoom;
import event.*;

public class EmergencyDepartment {
	private String name;
	private int simTime;
	private PatientCreator patientCreator;
	private ArrayList<Patient> PatientList;
	private ArrayList<Physician> PhysicianList;
	private ArrayList<Nurse> NurseList;
	private ArrayList<Room> RoomList;
	private ArrayList<Transporter> TransporterList;
	private Xray xray=Xray.getXRayInstance();
	private Blood blood=Blood .getBloodInstance();
	private MRI mri = MRI.getMRIInstance();
	private Registration registration = Registration.getRegistrationInstance();
	private Consultation consultation = Consultation.getConsultationInstance();
	private Transportation transportation = Transportation.getTransportationInstance();
	private Installation installation = Installation.getInstallationInstance();
	private EnabledEvent EnabledEvent;
	private EventQueue eventqueue;
	
	public EmergencyDepartment(){
		this.name="ED";
		this.simTime=0;
		this.patientCreator=new PatientCreator();
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
	
	
	
	EmergencyDepartment(int transporterNb, int nurseNb, int physicianNb, int boxRoomNb, 
								int shockRoomNb, int labRoomNb, int mriRoomNb, int xRayRoomNb){
		this.name="ED";
		this.simTime=0;
		this.patientCreator=new PatientCreator();
		this.PatientList=new ArrayList<Patient>();
		this.TransporterList=new ArrayList<Transporter>();
		this.NurseList= new ArrayList<Nurse>();
		this.PhysicianList= new ArrayList<Physician>();
		this.RoomList=new ArrayList<Room>();
		this.EnabledEvent= new EnabledEvent();
		this.eventqueue=new EventQueue();
		
		this.transporterNb=transporterNb;
		this.nurseNb=nurseNb;
		this.physicianNb=physicianNb;
		this.boxRoomNb=boxRoomNb;
		this.shockRoomNb=shockRoomNb;
		this.labRoomNb=labRoomNb;
		this.mriRoomNb=mriRoomNb;
		this.xRayRoomNb=xRayRoomNb;
		
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
		for (int i = 0; i < nb; i++) {
			Transporter t = new Transporter("Transporter", "Generic");
			TransporterList.add(t);
		}
		transporterNb = nb;

	}
	
	public void setNurseNb(int nb){
		nurseNb = nb;
		for (int i = 0; i < nb; i++) {
			Nurse n = new Nurse("Nurse", "Generic");
			NurseList.add(n);
		}
	}
	
	public void setPhysicianNb(int nb){
		for (int i = 0; i < nb; i++) {
			Physician p = new Physician("Physician", "Generic");
			PhysicianList.add(p);
		}
		physicianNb = nb;

	}
	
	public void setBoxRoomNb(int nb){
		boxRoomNb = nb;
		for (int i = 0; i < nb; i++) {
			BoxRoom r = new BoxRoom("Generic");
			RoomList.add(r);
		}
	}
	
	public void setShockRoomNb(int nb){
		shockRoomNb = nb;
		for (int i = 0; i < nb; i++) {
			ShockRoom r = new ShockRoom("Generic");
			RoomList.add(r);
		}
	}
	
	public void setLabRoomNb(int nb){
		labRoomNb = nb;
		for (int i = 0; i < labRoomNb; i++) {
			LaboratoryRoom r = new LaboratoryRoom("Generic");
			RoomList.add(r);
		}
	}
	
	public void setMriRoomNb(int nb){
		mriRoomNb = nb;
		for (int i = 0; i < mriRoomNb; i++) {
			MriRoom r = new MriRoom("Genreric");
			RoomList.add(r);
		}
		
	}
	
	public void setXrayRoomNb(int nb){
		xRayRoomNb = nb;
		for (int i = 0; i < xRayRoomNb; i++) {
			RadiographyRoom r = new RadiographyRoom("Generic");
			RoomList.add(r);
		}
	}
	
	/**
	 * To get the unique instance of the ED
	 * @return
	 */
	public static EmergencyDepartment getEDInstance() {
		return INSTANCE;
	}
	
	
	public Nurse getFreeNurse(){
		Nurse result = null;
		for(Nurse nurse : this.NurseList){
			if(nurse.getHumanResourceState().equals(HumanResourceState.IDLE)){
				result = nurse;
				break;
			}		
		}
		return result;
		
	}
	
	public Physician getFreePhysician(){
		Physician result = null;
		for(Physician physician : this.PhysicianList){
			if(physician.getHumanResourceState().equals(HumanResourceState.IDLE)){
				result = physician;
				break;
			}	
		}
		return result;
		
	}
	
	public Room getFreeRoom(String type){
		Room result = null;
		
		switch(type){
		
		case "LABORATORY_ROOM":
		for(Room room : this.RoomList){
			if(room.getRoomState().equals(RoomState.AVAILABLE) && room.getType().equals(type)){
				result = room;
				break;
			}
		}
		case "MRI_ROOM":
			for(Room room : this.RoomList){
				if(room.getRoomState().equals(RoomState.AVAILABLE) && room.getType().equals(type)){
					result = room;
					break;
				}
			}
		case "RADIOGRAPHY_ROOM":
			for(Room room : this.RoomList){
				if(room.getRoomState().equals(RoomState.AVAILABLE) && room.getType().equals(type)){
					result = room;
					break;
					}	
				}
		case "BOX_ROOM":
			for(Room room : this.RoomList){
				if(room.getRoomState().equals(RoomState.AVAILABLE) && room.getType().equals(type)){
					result =room;
					break;
					}
			}
		
		case "SHOCK_ROOM":
			for(Room room : this.RoomList){
				if(room.getRoomState().equals(RoomState.AVAILABLE) && room.getType().equals(type)){
					result =room;
					break;
					}
			}
		
		}
		return result;	
	}
	
	
	public Transporter getFreeTransporter(){
		Transporter result = null;
		for(Transporter transporter : this.TransporterList){
			if(transporter.getHumanResourceState().equals(HumanResourceState.IDLE)){
				result = transporter;
				break;
			}		
		}
		return result;
		
	}
	
	public Patient firstPatientToInstall() {
		int n = this.getInstallation().getWaitingQueue().size();
		ArrayList<Patient> patientList = this.getInstallation().getWaitingQueue();
		
		if(this.getFreeRoom("SHOCK_ROOM")==null) {
			int i = 0;
			while (i<n && (patientList.get(i).getSeverityLevel().equals(SeverityLevel.L1)||patientList.get(i).getSeverityLevel().equals(SeverityLevel.L2))) {
				i+=1;
			}
			return patientList.get(i);
		}
		else {
			return patientList.get(0);
		}
		
		
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
	public void setTranspoterList(ArrayList<Transporter> transpoterList) {
		TransporterList = transpoterList;
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
	public int getSimTime() {
		return simTime;
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
	public void setSimTime(int simTime) {
		this.simTime = simTime;
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
	
	
	public String toString() {
		String result= "";
		ArrayList<HumanResourceState> physicianStateList= new ArrayList<HumanResourceState>();
		ArrayList<HumanResourceState> nurseStateList= new ArrayList<HumanResourceState>();
		ArrayList<HumanResourceState> transporterStateList= new ArrayList<HumanResourceState>();
		ArrayList<PatientState> patientStateList= new ArrayList<PatientState>();
		
		int p = PhysicianList.size();
		int n = NurseList.size();
		int t = TransporterList.size();
		int pa = PatientList.size();
		for(int i = 0; i <p ; i ++ ) {
			Physician physician= PhysicianList.get(i);
			physicianStateList.add(physician.getHumanResourceState());
		}
		for(int i = 0; i <n ; i ++ ) {
			Nurse nurse = NurseList.get(i);
			nurseStateList.add(nurse.getHumanResourceState());
		}
		for(int i = 0; i<t ; i ++ ) {
			Transporter transporter = TransporterList.get(i);
			transporterStateList.add(transporter.getHumanResourceState());
		}
		if (pa>0) {
			for(int i = 0; i <pa ; i ++ ) {
				Patient patient = PatientList.get(i);
			patientStateList.add(patient.getPatientState());
		}
		}
		
		result +="\n EMERGENCY DPT : \n"
		+ "Etat des Human Ressources : \n"
		+ "[TRANSPORTERS : Nb= " + transporterNb + ", "+  transporterStateList + "\n ";
		result +="NURSES : Nb = " + nurseNb + ", " +nurseStateList+ "\n ";
		result +="PHYSICIANS : Nb = "+ physicianNb+ ", " +physicianStateList + "\n ";
		result +="BOX ROOMS : Nb = " + boxRoomNb 
		+", SHOCK ROOMS : Nb = " + shockRoomNb
		+", LABORATORY ROOMS : Nb = " + labRoomNb
		+ ", MRI ROOMS : Nb = " + mriRoomNb +  "\n "
		+ ", RADIOGRAPHY ROOMS : Nb = " + xRayRoomNb + "\n "
		+this.RoomList +" ] \n \n";
		
		result+="[Etat des Patients et des Queues : \n"
		+"PATIENTS : Nb = "+this.PatientList.size()+",Liste des Etats de chaque Patient : "+patientStateList+ "\n"
		+"REGISTRATION QUEUE : Nb =  "+this.getRegistration().getWaitingQueue().size()+", "+this.getRegistration().getWaitingQueue()+ "\n"
		+"INSTALLATION QUEUE : Nb = "+this.getInstallation().getWaitingQueue().size()+", "+this.getInstallation().getWaitingQueue()+ "\n"
		+"CONSULTATION QUEUE : Nb = "+this.getConsultation().getWaitingQueue().size()+", "+this.getConsultation().getWaitingQueue()+ "\n"
		+"TRANSPORTATION QUEUE : Nb = "+this.getTransportation().getWaitingQueue().size()+", "+this.getTransportation().getWaitingQueue()+ "\n"
		+"BLOOD-TEST QUEUE : Nb = "+this.getBlood().getWaitingQueue().size()+", "+this.getBlood().getWaitingQueue()+ "\n"
		+"XRAY QUEUE : Nb = "+this.getXray().getWaitingQueue().size()+", "+this.getXray().getWaitingQueue()+ "\n"
		+"MRI QUEUE : Nb = "+this.getMri().getWaitingQueue().size()+", "+this.getMri().getWaitingQueue()+ " ]\n"
		;
		
		result+="[Etat des EnabledEvent au temps : "+this.getSimTime()+ " : "
				+this.getEnabledEventList().getAbledList()+  " ] \n"
				+"[Etat de la EventQueue au temps : " +this.getSimTime()+ " : "
				+this.getEventqueue().getNextEvents()+" ] \n";
		if(this.getEventqueue().getNextEvents().size()>0) {
				result +="[Le premier événement de la Queue est : "+this.getEventqueue().getNextEvents().get(0)+"] \n"
				;
		}
		
		
		return result;
	}

	public Registration getRegistration() {
		return registration;
	}

	public void setRegistration(Registration registration) {
		this.registration = registration;
	}

	public Consultation getConsultation() {
		return consultation;
	}

	public void setConsultation(Consultation consultation) {
		this.consultation = consultation;
	}

	public Transportation getTransportation() {
		return transportation;
	}

	public void setTransportation(Transportation transportation) {
		this.transportation = transportation;
	}

	public MRI getMri() {
		return mri;
	}

	public void setMri(MRI mri) {
		this.mri = mri;
	}

	public Blood getBlood() {
		return blood;
	}

	public void setBlood(Blood blood) {
		this.blood = blood;
	}

	public Xray getXray() {
		return xray;
	}

	public void setXray(Xray xray) {
		this.xray = xray;
	}

	public Installation getInstallation() {
		return installation;
	}

	public void setInstallation(Installation installation) {
		this.installation = installation;
	}

	public PatientCreator getPatientCreator() {
		return patientCreator;
	}

	public void setPatientCreator(PatientCreator patientCreator) {
		this.patientCreator = patientCreator;
	}
}