
package core;

/**
 * 
 * 
 *
 */
public abstract class Room {

	private String name;
	private String type;
	private RoomState state;
	
	/**
	 * Instantiates a new Room with given name and type
	 * @param name
	 * @param type
	 */
	public Room(String name, String type) {
		this.setName(name);
		this.setType(type);
		this.setRoomState(RoomState.AVAILABLE);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public RoomState getRoomState() {
		return state;
	}
	
	public void setRoomState(RoomState state) {
		this.state = state;
	}
	
	public String toString() {
		return "ROOM: [NAME: " + name + ", TYPE: " + type + ", STATE: " + state + "]";
	}
	
	
}
