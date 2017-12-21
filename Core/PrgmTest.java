package Core;

import Core.Distribution.Dirac;
import Core.Distribution.Uniform;

public class PrgmTest {

	public static void main(String[] args) {
		
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
		
		
		
		
		
		
		
		


		
		Time t = new Time(16, 20);
		System.out.println(t);
		
		Nurse n1 = new Nurse("Mary", "S");
		Physician p1 = new Physician("Philip", "B");
		System.out.println(n1);
		System.out.println(p1);
		System.out.println(HumanResource.getCounter());
				
		
		Patient patient1 = new Patient("Brown", "James", SeverityLevel.L1, 10);
		//System.out.println(patient);	
		Patient patient2 = new Patient("Brown", "James", SeverityLevel.L1, 12);
		Patient patient3 = new Patient("Brown", "James", SeverityLevel.L2, 13);
		Patient patient4 = new Patient("Brown", "James", SeverityLevel.L3, 15);
		Patient patient5 = new Patient("Brown", "James", SeverityLevel.L4, 16);
		Patient patient6 = new Patient("Brown", "James", SeverityLevel.L4, 17);
		Patient patient7 = new Patient("Brown", "James", SeverityLevel.L5, 17);
		Patient patient8 = new Patient("Brown", "James", SeverityLevel.L5, 19);
		
		Patient patientTest = new Patient("Testing", "Test", SeverityLevel.L4, 20);	
		
		
		consultation.addPatientToQueue(patient1);
		consultation.addPatientToQueue(patient2);
		consultation.addPatientToQueue(patient7);
		consultation.addPatientToQueue(patient6);
		consultation.addPatientToQueue(patient5);
		consultation.addPatientToQueue(patient4);
		consultation.addPatientToQueue(patient3);
		consultation.addPatientToQueue(patient8);

		
		consultation.addPatientToQueue(patientTest);
		System.out.println(consultation.getWaitingQueue());
		
		
		
	}
}
