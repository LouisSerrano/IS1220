package core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



import core.healthservice.HealthService;
import core.room.BoxRoom;
import core.room.LaboratoryRoom;
import core.room.MriRoom;
import core.room.RadiographyRoom;
import core.room.Room;
import core.room.ShockRoom;
import core.SeverityLevel;

/**
 * This class is used to take an existing .ini file, that allows us to
 * initialize SimErgy with a set of initial resources. It uses the JSON format
 * and JSON libraries for parsing it.
 * 
 */
public class FileLoader {
	

	ArrayList<Patient> createdPatients;
	ArrayList<Nurse> createdNurses;
	ArrayList<Physician> createdPhysicians;
	ArrayList<Transporter> createdTransporters;
	ArrayList<Room> createdRoom;
	private BufferedReader br;

	/**
	 * Main method of the file loader. It reads and parses a JSON .ini file and add
	 * the corresponding initial resources to the simulation
	 * 
	 * @param simErgy
	 *            System where resources are going to be added
	 * @throws IOException
	 *             Exception due to the file reading part, could happen if the file
	 *             is corrupted or badly formatted.
	 * @throws JSONException 
	 */
	public FileLoader(EmergencyDepartment system, String fileName) throws IOException, JSONException {
		createdPatients = new ArrayList<>();
		createdNurses = new ArrayList<>();
		createdPhysicians = new ArrayList<>();
		createdTransporters = new ArrayList<>();
		createdRoom = new ArrayList<>();
		

		FileReader fr = new FileReader(fileName);
		br = new BufferedReader(fr);
		String line = null;
		String jsonString = "";
		while ((line = br.readLine()) != null) {
			jsonString += line;
		}
		JSONObject jsonObject = new JSONObject(jsonString);
		JSONArray nursesList = (JSONArray) jsonObject.get("Nurses");
		JSONArray physiciansList = (JSONArray) jsonObject.get("Physicians");
		JSONArray roomsList = (JSONArray) jsonObject.get("Rooms");
		JSONArray transportersList = (JSONArray) jsonObject.get("Transporters");
		JSONArray patientsList = (JSONArray) jsonObject.get("Patients");
		JSONArray healthServicesList = (JSONArray) jsonObject.get("HealthServices");

		for (int i = 0; i < nursesList.length(); ++i) {

			JSONObject nurseObject = nursesList.getJSONObject(i);
			Nurse nurse = new Nurse(nurseObject.getString("name"), nurseObject.getString("name"));
			createdNurses.add(nurse);
		}

		for (int i = 0; i < physiciansList.length(); ++i) {
			JSONObject physicianObject = physiciansList.getJSONObject(i);
			Physician physician = new Physician(physicianObject.getString("surname"), physicianObject.getString("name"));
			createdPhysicians.add(physician);
		}

		for (int i = 0; i < roomsList.length(); ++i) {
			JSONObject roomObject = roomsList.getJSONObject(i);
			String type = roomObject.getString("type");
			
			if(type.equals("SHOCK_ROOM")) {
				ShockRoom shockRoom = new ShockRoom(roomObject.getString("name"));
				createdRoom.add(shockRoom);
			}
			if(type.equals("BOX_ROOM")) {
				BoxRoom boxRoom = new BoxRoom(roomObject.getString("name"));
				createdRoom.add(boxRoom);

			}
			if(type.equals("RADIOGRAPHY_ROOM")) {
				RadiographyRoom xrayRoom = new RadiographyRoom(roomObject.getString("name"));
				createdRoom.add(xrayRoom);

			}
			if(type.equals("MRI_ROOM")) {
				MriRoom mriRoom = new MriRoom(roomObject.getString("name"));
				createdRoom.add(mriRoom);

			}
			if(type.equals("LABORATORY_ROOM")) {
				LaboratoryRoom labRoom = new LaboratoryRoom(roomObject.getString("name"));
				createdRoom.add(labRoom);

			}
			
		}

		

		for (int i = 0; i < transportersList.length(); ++i) {
			JSONObject transporterObject = transportersList.getJSONObject(i);
			Transporter transporter = new Transporter(transporterObject.getString("surname"), transporterObject.getString("name"));
			createdTransporters.add(transporter);
		}

		for (int i = 0; i < patientsList.length(); ++i) {
			JSONObject patientObject = patientsList.getJSONObject(i);
			int intLevel =Integer.parseInt((patientObject.getString("severity_level")));
			SeverityLevel level = null;
			HealthInsurance insurance = null;
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
			
			String insuranceString = patientObject.getString("healthInsurance");
			
			if (insuranceString.equals("none")) {
				insurance= HealthInsurance.none;
			}
			if (insuranceString.equals("gold")) {
				insurance= HealthInsurance.gold;
			}
			if (insuranceString.equals("silver")) {
				insurance= HealthInsurance.silver;
			}
			
			
			Patient patient = new Patient(patientObject.getString("surname"),patientObject.getString("name"), level , Integer.parseInt(patientObject.getString("arrival_time")),insurance);;
			patient.getName();
			createdPatients.add(patient);
		}

	}
	public ArrayList<Patient> getCreatedPatients(){
		return createdPatients;
	}
	public ArrayList<Nurse> getCreatedNurses() {
		return createdNurses;
	}

	public ArrayList<Physician> getCreatedPhysicians() {
		return createdPhysicians;
	}

	public ArrayList<Transporter> getCreatedTransporters() {
		return createdTransporters;
	}

	public ArrayList<Room> getCreatedRoom() {
		return createdRoom;
	}
	public void setCreatedRoom(ArrayList<Room> createdRoom) {
		this.createdRoom = createdRoom;
	}
}
