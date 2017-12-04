
package core;

/**
 * 
 * 
 *
 */
public abstract class Room {

	private String name;
	private String type;
	
	/**
	 * Instantiates a new Room with given name and type
	 * @param name
	 * @param type
	 */
	public Room(String name, String type) {
		this.name = name;
		this.type = type;
	}
}
