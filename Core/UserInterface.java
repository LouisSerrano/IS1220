package clui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.json.JSONException;

import core.EmergencyDepartment;

public class UserInterface implements Runnable {

		EmergencyDepartment system;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		CommandHandler handler = new CommandHandler();

		public UserInterface(EmergencyDepartment system) {
			this.system = system;
			this.handler=new CommandHandler();
		}

		/**
		 * Main function of the command line user interface. 
		 * 
		 * @throws IOException
		 *             because of file reading problems
		 * @throws JSONException 
		 */
		public void buildSimulation(String[] input) throws InvalidCommandException, IOException, JSONException {
			switch (input[0]) {
			case "createED":
				handler.createED(input);
				return;
			case "createEDFile":
				handler.createEDFile(input);
				return;
			}
			throw new InvalidCommandException();
		}

		@Override
		public void run() {
			System.out.println("Welcome to SimErgy!");
			boolean EDcreated = false;
			while (!EDcreated) {
				System.out.println("Please select an option: ");
				System.out.println(
						"Create new ED: createED <EDname> \nCreate new ED from a given file: createEDFile <Filename>"+
						"\n(If no file name is specified, a default file will be loaded)");
				try {
					String[] input = br.readLine().split(" ", 2);
					this.buildSimulation(input);
					System.out.println("ED created");
					EDcreated = true;
				} catch (InvalidCommandException e) {
					System.out.println("Invalid command, please use the specified syntax");
				} catch (IOException e) {
					System.out.println(
							"Seems there's a problem reading the file, please try creating an ED manually or with other file");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			boolean loop = true;
			while (loop) {
				System.out.println("Introduce next command");
				try {
					String[] args0 = br.readLine().split(" ", 4);
					String[] args = null;
					int n = args0.length;
					for(int i=1; i<n ; i++) {
						args[i-1]=args0[i];
					}
					switch (args0[0]) {
					
					
					case "exit":
						loop = false;
						System.out.println("Ending the program");
						break;
					case "addRoom":
						handler.addRoom(args);
						break;
					case "addRadioService":
						handler.setRadioService(args);
						break;
					case "addMRI":
						handler.setMriService( args);
						break;
					case "addBloodTest":
						handler.setBloodTestService(args);
						break;
					case "addNurse":
						handler.addNurse(args);
						break;
					case "addPhysician":
						handler.addPhysician(args);
						break;
					case "addTransporter":
						handler.addTransporter(args);
						break;
					case "setL1arrivalDist":
						handler.setL1arrivalDist(args);
						break;
					case "setL2arrivalDist":
						handler.setL2arrivalDist(args);
						break;
					case "setL3arrivalDist":
						handler.setL3arrivalDist(args);
						break;
					case "setL4arrivalDist":
						handler.setL4arrivalDist(args);
						break;
					case "setL5arrivalDist":
						handler.setL5arrivalDist(args);
						break;
					case "addPatient":
						handler.addPatient(args);
						break;
					case "setPatientInsurance":
						handler.setPatientInsurance(args);
						break;
					case "executeEvent":
						handler.executeEvent(args);
						break;
	
					default:
						throw new InvalidCommandException();
						
					}
					
					System.out.println("Operation executed");
				} catch (InvalidCommandException e) {
					System.out.println("The entered command is invalid, please take a look to the specified syntax");
				} catch (IOException e) {
					System.out.println("Seems like there's some problem with the precedent input");
				}
			}

		}
	}
