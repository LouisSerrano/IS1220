package is1220.projectSimErgy.core;

public class Time {
	
	private int hours;
	private int minutes;
	
	/**
	 * Instantiates a Time object with given hours and minutes
	 * @param hours
	 * @param minutes
	 */
	public Time(int hours, int minutes) {
		this.hours = hours;
		this.minutes = minutes;
	}
	
	public String toString() {
		return hours + ":" + minutes;
	}
	
	/**
	 * @return the hours
	 */
	public int getHours() {
		return hours;
	}
	/**
	 * @param hours the hours to set
	 */
	public void setHours(int hours) {
		this.hours = hours;
	}
	/**
	 * @return the minutes
	 */
	public int getMinutes() {
		return minutes;
	}
	/**
	 * @param minutes the minutes to set
	 */
	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}
	
	
}
