package core;

import java.util.ArrayList;

import core.distribution.Dirac;
import core.distribution.Exponential;
import core.distribution.Uniform;
import core.healthservice.Blood;
import core.healthservice.Consultation;
import core.healthservice.Installation;
import core.healthservice.MRI;
import core.healthservice.Transportation;
import core.healthservice.Xray;
import event.Arrival1Event;
import event.Arrival2Event;
import event.Arrival3Event;
import event.Arrival4Event;
import event.EnabledEvent;
import event.Event;
import event.EventQueue;
import event.EventType;
import event.PatientRegistrationEvent;

public class PrgmTest {


	public static void main(String[] args) {
 
		
		/**
		 * Initialisation des différents paramètres du système
		 */
		
		int Tend = 50;
		EmergencyDepartment system = new EmergencyDepartment();
		system.setPhysicianNb(5);
		system.setNurseNb(10);
		system.setTransporterNb(3);
		system.setBoxRoomNb(3);
		system.setShockRoomNb(2);
		system.setLabRoomNb(2);
		system.setXrayRoomNb(2);
		system.setMriRoomNb(2);
				
		Uniform consultationDistribution = new Uniform(5,20);
		Uniform mriDistribution = new Uniform(30,70);
		Uniform bloodTestDistribution = new Uniform(15,90);
		Uniform radiographyDistribution = new Uniform(10,20);
		Dirac installationDistribution = new Dirac(2);
		Dirac transportationDistribution = new Dirac(5);

		Consultation consultation = Consultation.getConsultationInstance();
		consultation.setCost(150);
		consultation.setDistribution(consultationDistribution);
		
		MRI mri = MRI.getMRIInstance();
		mri.setCost(1000);
		mri.setDistribution(mriDistribution);
		
		Blood blood = Blood.getBloodInstance();
		blood.setCost(350);
		blood.setDistribution(bloodTestDistribution);
		
		Xray xray = Xray.getXRayInstance();
		xray.setCost(550);
		xray.setDistribution(radiographyDistribution);
		
		Installation installation = Installation.getInstallationInstance();
		installation.setDistribution(installationDistribution);
		
		Transportation transportation = Transportation.getTransportationInstance();
		transportation.setDistribution(transportationDistribution);
		
		SeverityLevel.L1.setDistribution(new Exponential(0.1));
		SeverityLevel.L2.setDistribution(new Exponential(0.1));
		SeverityLevel.L3.setDistribution(new Exponential(0.1));
		SeverityLevel.L4.setDistribution(new Exponential(0.1));
		SeverityLevel.L5.setDistribution(new Exponential(0.1));
				
		/**
		 * System.out.println(system);
		 */

		
	
		/**
		 * 
		 * EventQueue enabledEventTest = new EventQueue();
		ArrayList<Event> nextEvents = enabledEventTest.getNextEvents();
		System.out.println(nextEvents);
		
		
		  Patient patient = system.getPatientCreator().newPatient(SeverityLevel.L1);
		   Patient patient2 = system.getPatientCreator().newPatient(SeverityLevel.L2);
		  Patient patient3 = system.getPatientCreator().newPatient(SeverityLevel.L3);
		  Patient patient4 = system.getPatientCreator().newPatient(SeverityLevel.L4);
		 
		
		 Arrival1Event arr1 = new Arrival1Event(patient);
		 Arrival2Event arr2 = new Arrival2Event(patient2);
		 Arrival3Event arr3 = new Arrival3Event(patient3);
		 Arrival4Event arr4 = new Arrival4Event(patient4);
		 
		 System.out.println("TEST DES TYPES D'EVENEMENTS");
		 System.out.println(arr1.getType());
		 System.out.println(arr2.getType());
		 System.out.println(arr3.getType());
		 System.out.println(arr4.getType());
		  
		 
		
		
		System.out.println(system.getEventqueue().getNextEvents());
		System.out.println(system.getEventqueue().getNextEvents().get(4).getType());
		System.out.println(EventType.ARR1.equals(EventType.ARR1));
		System.out.println(system.getEventqueue().getNextEvents().size());
		EventType eT = EventType.ARR1;
		 */
		
		
		 

	
		
		/**
		 * System.out.println("Test de la fonction delete instance");
		 * system.getEventqueue().deleteInstancesOfEventType(EventType.ARR1);
		 */
		
		EnabledEvent enabledEvent0= system.getEnabledEventList();
		System.out.println("DEBUT DU TEST");
		System.out.println("Le temps max de la simulation est Tend = "+Tend);
		
		EnabledEvent enabledEvent1=EnabledEvent.update(system);
		System.out.println("L'eventqueue est vide au départ : "+system.getEventqueue().getNextEvents());
		
		
		EventQueue newEventQueue0 = EventQueue.updateEventQueue(enabledEvent1, enabledEvent0, system);
		System.out.println(newEventQueue0);
		
		Patient patient1 = new Patient(SeverityLevel.L1,0);
		Patient patient2 = new Patient(SeverityLevel.L1,1);
		Patient patient3 = new Patient(SeverityLevel.L3,2);
		
		Nurse nurse1= new Nurse("Nurse","Generic");
		PatientRegistrationEvent regist1 =new PatientRegistrationEvent(0, patient1, nurse1);
		
		System.out.println("TEST DE SORT");
		/**
		 * EventQueue eventQueueTest = new EventQueue();
		 * eventQueueTest.getNextEvents().add(new Arrival1Event(patient1));
		eventQueueTest.getNextEvents().add(new Arrival2Event(patient2));
		eventQueueTest.getNextEvents().add(regist1);
		System.out.println(eventQueueTest);
		eventQueueTest.sort();
		System.out.println(eventQueueTest);
		 */
		
		System.out.println(system.getConsultation().getWaitingQueue());
							
		System.out.println("\n TEST DU CORPS DU MAIN \n");
		
		
		while(system.getSimTime()<Tend){
		System.out.println("L'etat du Systeme au temps "+system.getSimTime()+"\n");
		System.out.println(system);
		system.getEventqueue().getNextEvents().get(0).execute(system);
		System.out.println("\n Le temps du systeme après execution est le suivant : "+system.getSimTime());
		
		system.getEnabledEventList().getAbledList().remove(system.getEventqueue().getNextEvents().get(0).getType());
		system.getEnabledEventList().getDisabledList().add(system.getEventqueue().getNextEvents().get(0).getType());
		system.getEventqueue().getNextEvents().remove(0);
		
		EnabledEvent oldEnabledEvent= system.getEnabledEventList();
		System.out.println("Liste des événements anciennement disponibles : "+oldEnabledEvent.getAbledList());

		EnabledEvent.update(system);
		EnabledEvent newEnabledEvent=system.getEnabledEventList();
		System.out.println("Liste des événements nouvellement disponibles : "+newEnabledEvent.getAbledList());
		
		EventQueue newEventQueue = EventQueue.updateEventQueue(newEnabledEvent,oldEnabledEvent,system);
		system.setEventqueue(newEventQueue);
		
		System.out.println("\n");
		}
		 
		 
		
		
		


		
		
		
	/**
	 * while (system.getSimTime()<Tend) {
			Event e1= system.getEventqueue().getNextEvents().get(0);
			e1.execute(system);
			System.out.println(system.getSimTime());
			system.getEnabledEventList().remove(e1.getType());
			EnabledEvent oldEnabledEvent= system.getEnabledEventList();
			EnabledEvent.update(system);
			EnabledEvent newEnabledEvent=system.getEnabledEventList();
			EventQueue newEventQueue = EventQueue.updateEventQueue(newEnabledEvent,oldEnabledEvent,system);
			system.setEventqueue(newEventQueue);
			
		}
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 	
	 */
	
		 
		
		
		
		
		 
	}
}
	