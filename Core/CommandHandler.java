package clui;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONException;

import core.EmergencyDepartment;
import core.HealthInsurance;
import core.Nurse;
import core.Patient;
import core.SeverityLevel;
import core.distribution.Dirac;
import core.distribution.Exponential;
import core.distribution.LaboratoryRoom;
import core.distribution.ProbabilityDistribution;
import core.distribution.Uniform;
import core.healthservice.Xray;
import core.room.BoxRoom;
import core.room.MriRoom;
import core.room.RadiographyRoom;
import core.room.Room;
import core.room.ShockRoom;
import event.Arrival1Event;
import event.Arrival2Event;
import event.Arrival3Event;
import event.Arrival4Event;
import event.Arrival5Event;
import event.Event;

public class CommandHandler {
	private ArrayList<EmergencyDepartment> systemList;
	
	public CommandHandler(){
		ArrayList<EmergencyDepartment> systemList= new ArrayList<EmergencyDepartment>();
		this.systemList=systemList;
		
	}
	
	
	
	public EmergencyDepartment getSystem(String name) {
		EmergencyDepartment result = null;
		for(EmergencyDepartment system : systemList) {
			if (system.getName().equals(name)) {
				result= system;
				break;
			}
		}
			return result;	
	}
	
	public void createED(String[] input) {
		if (input.length==1) {
			String name = input[0];
			if(this.getSystem(name).equals(null)){
			EmergencyDepartment newSystem = new EmergencyDepartment();
			newSystem.setName(name);
			systemList.add(newSystem);
			System.out.println(newSystem);
	}
		}
	}
	
	public void createEDFile(String[] input) throws IOException, JSONException {
		EmergencyDepartment newSystem= new EmergencyDepartment();
		if (input.length == 1) {
			newSystem.addFromFile("my_simergy.ini");
		} else {
			newSystem.addFromFile(input[1]);
		}
		System.out.println(newSystem);
	}
	
	public void addRoom(String[] args) {
		EmergencyDepartment system = this.getSystem(args[0]);
		String type = args[1];
		String roomName;
		switch (type) {
		case "SHOCK_ROOM":
			if (args.length == 3) {
				roomName = args[2];
			} else {
				roomName = "GENERIC";
			}
			Room room = new ShockRoom("");
			room.setName(roomName);
			system.getRoomList().add(room);
			System.out.println("Shock room added");
			break;
		case "BOX_ROOM":
			if (args.length == 3) {
				roomName = args[2];
			} else {
				roomName = "GENERIC";
			}
			Room boxRoom = new BoxRoom("");
			boxRoom.setName(roomName);
			system.getRoomList().add(boxRoom);
			System.out.println("Box room added");
			break;
		
		case "RADIOLOGY_ROOM":
			if (args.length == 3) {
			roomName = args[2];
			} else {
			roomName = "GENERIC";
			}
			Room xrayRoom = new RadiographyRoom("");
			xrayRoom.setName(roomName);
			system.getRoomList().add(xrayRoom);
			System.out.println("Radio room added");
			break;
			
		case "MRI_ROOM":
			if (args.length == 3) {
			roomName = args[2];
			} 
			else {
			roomName = "GENERIC";
			}
			Room mriRoom = new MriRoom("");
			mriRoom.setName(roomName);
			system.getRoomList().add(mriRoom);
			System.out.println("Mri room added");
			break;
		case "LABORATORY_ROOM":
			if (args.length == 3) {
			roomName = args[2];
			} 
			else {
			roomName = "GENERIC";
			}
			Room labRoom = new LaboratoryRoom("");
			labRoom.setName(roomName);
			system.getRoomList().add(labRoom);
			System.out.println("Lab room added");
			break;
	}
	}
	
	public void setRadioService(String[] args) {
		EmergencyDepartment system = this.getSystem(args[0]);
		if(args.length>=2) {
			system.getXray().setCost(Double.parseDouble(args[1]));

			if(args.length==5 && (args[2].equals("uniform") || args[2].equals("UNIFORM"))) {
			double borneInf = Double.parseDouble(args[2]);
			double borneSup = Double.parseDouble(args[3]);
			Uniform distribution = new Uniform(borneInf, borneSup);
			system.getXray().setDistribution(distribution);
			}
			
			if(args.length==4 && (args[2].equals("dirac") || args[2].equals("DIRAC"))) {
				double dirac = Double.parseDouble(args[3]);
				Dirac distribution = new Dirac(dirac);
				system.getXray().setDistribution(distribution);
				}
			}
		}
	
		public void setMriService(String[] args) {
			EmergencyDepartment system = this.getSystem(args[0]);
			if(args.length>=2) {
				system.getMri().setCost(Double.parseDouble(args[1]));

				if(args.length==5 && (args[2].equals("uniform") || args[2].equals("UNIFORM"))) {
				double borneInf = Double.parseDouble(args[3]);
				double borneSup = Double.parseDouble(args[4]);
				
				if (borneSup>borneInf) {
				Uniform distribution = new Uniform(borneInf, borneSup);
				system.getMri().setDistribution(distribution);
				}
				}
				
				if(args.length==4 && (args[2].equals("dirac") || args[2].equals("DIRAC"))) {
					double dirac = Double.parseDouble(args[3]);
					Dirac distribution = new Dirac(dirac);
					system.getMri().setDistribution(distribution);
					}
			}
			}
	public void setBloodTestService(String[] args) {
		EmergencyDepartment system = this.getSystem(args[0]);
		if(args.length>=2) {
			system.getBlood().setCost(Double.parseDouble(args[1]));

			if(args.length==5 && (args[2].equals("uniform") || args[2].equals("UNIFORM"))) {
			double borneInf = Double.parseDouble(args[3]);
			double borneSup = Double.parseDouble(args[4]);
			if (borneSup>borneInf) {
			Uniform distribution = new Uniform(borneInf, borneSup);
			system.getBlood().setDistribution(distribution);
			}
			}
			
			if(args.length==4 && (args[2].equals("dirac") || args[2].equals("DIRAC"))) {
				double dirac = Double.parseDouble(args[3]);
				Dirac distribution = new Dirac(dirac);
				system.getBlood().setDistribution(distribution);
				}
		}
		}
		
	
	public void addNurse(String[] args) {
		EmergencyDepartment system = this.getSystem(args[0]);
		String nameNurse = "";
		String surnameNurse = "";
		if (args.length == 2) {
			nameNurse = args[1];
		} else if (args.length == 3) {
			nameNurse = args[1];
			surnameNurse = args[2];
		}
		system.getNurseList().add(new Nurse(surnameNurse, nameNurse));
		System.out.println("Nurse " + nameNurse + " added");
	}
	
	public void addPhysician(String[] args) {
		EmergencyDepartment system = this.getSystem(args[0]);
		String namePhysician = "";
		String surnamePhysician = "";
		if (args.length == 2) {
			namePhysician = args[1];
		} else if (args.length == 3) {
			namePhysician = args[1];
			surnamePhysician = args[2];
		}
		system.getNurseList().add(new Nurse(surnamePhysician, namePhysician));
		System.out.println("Nurse " + namePhysician + " added");
	}
	
	public void addTransporter(String[] args) {
		EmergencyDepartment system = this.getSystem(args[0]);
		String nameTransporter = "";
		String surnameTransporter = "";
		if (args.length == 2) {
			nameTransporter = args[1];
		} else if (args.length == 3) {
			nameTransporter = args[1];
			surnameTransporter = args[2];
		}
		system.getNurseList().add(new Nurse(surnameTransporter, nameTransporter));
		System.out.println("Nurse " + nameTransporter + " added");
	}
	
	/**
	 * Changes the severityLevel for every system unfortunately, it doesn't take a specific ED into account
	 * @param args
	 */
	public void setL1arrivalDist(String[] args) {
		if(args.length==3 && (args[0].equals("uniform") || args[0].equals("UNIFORM"))) {
			double borneInf = Double.parseDouble(args[1]);
			double borneSup = Double.parseDouble(args[2]);
			
			if(borneSup>borneInf) {
			Uniform distribution = new Uniform(borneInf, borneSup);
			SeverityLevel.L1.setDistribution(distribution);
			}
		}
			if(args.length==2 && (args[0].equals("dirac") || args[0].equals("DIRAC"))) {
				double dirac = Double.parseDouble(args[1]);
				Dirac distribution = new Dirac(dirac);
				SeverityLevel.L1.setDistribution(distribution);

				}
			if(args.length==2 && (args[0].equals("exponential") || args[0].equals("EXPONENTIAL"))) {
				double lambda = Double.parseDouble(args[1]);
				Exponential distribution = new Exponential(lambda);
				SeverityLevel.L1.setDistribution(distribution);

				}			
		}
		


public void setL2arrivalDist(String[] args) {
	if(args.length==3 && (args[0].equals("uniform") || args[0].equals("UNIFORM"))) {
		double borneInf = Double.parseDouble(args[1]);
		double borneSup = Double.parseDouble(args[2]);
		
		if(borneSup>borneInf) {
		Uniform distribution = new Uniform(borneInf, borneSup);
		SeverityLevel.L2.setDistribution(distribution);
		}
	}
		if(args.length==2 && (args[0].equals("dirac") || args[0].equals("DIRAC"))) {
			double dirac = Double.parseDouble(args[1]);
			Dirac distribution = new Dirac(dirac);
			SeverityLevel.L2.setDistribution(distribution);

			}
		if(args.length==2 && (args[0].equals("exponential") || args[0].equals("EXPONENTIAL"))) {
			double lambda = Double.parseDouble(args[1]);
			Exponential distribution = new Exponential(lambda);
			SeverityLevel.L2.setDistribution(distribution);

			}			
	}
	


public void setL3arrivalDist(String[] args) {
	if(args.length==3 && (args[0].equals("uniform") || args[0].equals("UNIFORM"))) {
		double borneInf = Double.parseDouble(args[1]);
		double borneSup = Double.parseDouble(args[2]);
		
		if(borneSup>borneInf) {
		Uniform distribution = new Uniform(borneInf, borneSup);
		SeverityLevel.L3.setDistribution(distribution);
		}
	}
		if(args.length==2 && (args[0].equals("dirac") || args[0].equals("DIRAC"))) {
			double dirac = Double.parseDouble(args[1]);
			Dirac distribution = new Dirac(dirac);
			SeverityLevel.L3.setDistribution(distribution);

			}
		if(args.length==2 && (args[0].equals("exponential") || args[0].equals("EXPONENTIAL"))) {
			double lambda = Double.parseDouble(args[1]);
			Exponential distribution = new Exponential(lambda);
			SeverityLevel.L3.setDistribution(distribution);

			}			
	}
	


public void setL4arrivalDist(String[] args) {
	if(args.length==3 && (args[0].equals("uniform") || args[0].equals("UNIFORM"))) {
		double borneInf = Double.parseDouble(args[1]);
		double borneSup = Double.parseDouble(args[2]);
		
		if(borneSup>borneInf) {
		Uniform distribution = new Uniform(borneInf, borneSup);
		SeverityLevel.L4.setDistribution(distribution);
		}
	}
		if(args.length==2 && (args[0].equals("dirac") || args[0].equals("DIRAC"))) {
			double dirac = Double.parseDouble(args[1]);
			Dirac distribution = new Dirac(dirac);
			SeverityLevel.L4.setDistribution(distribution);

			}
		if(args.length==2 && (args[0].equals("exponential") || args[0].equals("EXPONENTIAL"))) {
			double lambda = Double.parseDouble(args[1]);
			Exponential distribution = new Exponential(lambda);
			SeverityLevel.L4.setDistribution(distribution);

			}			
	}
	


public void setL5arrivalDist(String[] args) {
	if(args.length==3 && (args[0].equals("uniform") || args[0].equals("UNIFORM"))) {
		double borneInf = Double.parseDouble(args[1]);
		double borneSup = Double.parseDouble(args[2]);
		
		if(borneSup>borneInf) {
		Uniform distribution = new Uniform(borneInf, borneSup);
		SeverityLevel.L5.setDistribution(distribution);
		}
	}
		if(args.length==2 && (args[0].equals("dirac") || args[0].equals("DIRAC"))) {
			double dirac = Double.parseDouble(args[1]);
			Dirac distribution = new Dirac(dirac);
			SeverityLevel.L5.setDistribution(distribution);

			}
		if(args.length==2 && (args[0].equals("exponential") || args[0].equals("EXPONENTIAL"))) {
			double lambda = Double.parseDouble(args[1]);
			Exponential distribution = new Exponential(lambda);
			SeverityLevel.L5.setDistribution(distribution);

			}			
	}

public void addPatient(String [] args) {
	EmergencyDepartment system=this.getSystem(args[0]);
	String namePatient = args[2];
	String surnamePatient = args[1];
	int intLevel = Integer.parseInt(args[3]);
	HealthInsurance insurance= HealthInsurance.none;
	SeverityLevel level = null;
	
	if(intLevel==1) {
		level = SeverityLevel.L1;
	}
	if(intLevel==2) {
		level = SeverityLevel.L2;
	}
	if(intLevel==3) {
		level = SeverityLevel.L3;
	}
	if(intLevel==4) {
	level = SeverityLevel.L4;
	}
	if(intLevel==5) {
	level = SeverityLevel.L5;
	}
	
	if (args.length==4) {
		String insuranceString = args[5];
	
	
	if (insuranceString.equals("none")) {
		insurance= HealthInsurance.none;
	}
	if (insuranceString.equals("gold")) {
		insurance= HealthInsurance.gold;
	}
	if (insuranceString.equals("silver")) {
		insurance= HealthInsurance.silver;
	}
	}
	
	Patient patient = new Patient(surnamePatient, namePatient,level, system.getSimTime(), insurance);
	Event e = null;
	if(intLevel==1) {
	 e = new Arrival1Event(patient);
	}
	if(intLevel==2) {
		 e = new Arrival2Event(patient);
	}
	if(intLevel==3) {
		 e = new Arrival3Event(patient);
	}
	if(intLevel==4) {
		 e = new Arrival4Event(patient);
	}
	if(intLevel==5) {
		 e = new Arrival5Event(patient);
	}
	
	system.getEventqueue().getNextEvents().add(e);
	System.out.println("Patient "+patient.getName()+"added to the System");
	
	
}
	
public void setPatientInsurance(String[] args) {
	EmergencyDepartment system = this.getSystem(args[0]);
	int patientID = Integer.parseInt(args[1]);
	HealthInsurance insurance = HealthInsurance.none;
	switch (args[2]) {
	case "none":
		insurance = HealthInsurance.none;
		break;
	case "silver":
		insurance = HealthInsurance.silver;
		break;
	case "gold":
		insurance = HealthInsurance.gold;
		break;
	}
	for (Patient p : system.getPatientList()) {
		if (p.getId() == patientID) {
			p.setInsurance(insurance);
		}
	}
	System.out.println("Health Insurance set");
}

public void executeEvent(String[] EDname) {
		EmergencyDepartment system = this.getSystem(EDname[0]);
		system.execute();	
	}

public void display(String[] EDname) {
	String name= EDname[0];
	EmergencyDepartment system = this.getSystem(name);
	system.toString();
}

	
}
	



	
	
	
	
	
	
	
	
	