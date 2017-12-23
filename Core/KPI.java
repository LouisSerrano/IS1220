package core;

import event.EventType;

public class KPI {
	
	private Patient patient;
	private Integer lengthOfStay;
	private Integer doorToDoctorTime;
	
	
	public KPI(Patient patient){
		this.patient = patient;
		updateKPI();	
	}
	
	/**
	 * calculates the length-of-stay (LOS) and door-to-doctor-time (DTDT) of a patient
	 */
	public void updateKPI() {
		int patientArrivalTime = this.patient.getArrivalTime();
		int patientDepartureTime = 0;
		int patientFirstConsultationTime = 0;
		try {
			patientDepartureTime = this.patient.getHistory()
				.get(this.patient.searchEvent(EventType.VERDICT));
		} catch (NullPointerException e) {
			System.out.println("Patient has not left the ED yet.");
		}
		try {
			patientFirstConsultationTime = this.patient.getHistory()
				.get(this.patient.searchEvent(EventType.START_VISIT));
		} catch (NullPointerException e) {
			System.out.println("Patient has not been consulted yet.");
		}
		this.lengthOfStay = patientDepartureTime - patientArrivalTime;
		this.doorToDoctorTime = patientFirstConsultationTime - patientArrivalTime;
		/**
		 * if the patient has not left the ED, his L.O.S. is set at zero
		 */
		if (this.lengthOfStay < 0) {
			lengthOfStay = 0;
		}
		/**
		 * if the patient has not been consulted yet, his D.T.D.T. is set at zero
		 */
		if (this.doorToDoctorTime < 0) {
			doorToDoctorTime = 0;
		}
	}
	
	public int getLOS() {
		updateKPI();
		return lengthOfStay;
	 }
	
	public int getDTDT() {
		return doorToDoctorTime;
	}
	
	
	
	
}
