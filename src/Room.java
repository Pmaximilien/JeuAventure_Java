/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */

import java.util.*;

public class Room 
{
    private String description;
    private HashMap<String, Room> exits;
    private String imageName;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<String, Room>();
    }

    public Room(String description, String image) 
    {
        this.description = description;
        exits = new HashMap<String,Room>();
		setImageName(image);
    }
    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param north The north exit.
     * @param east The east east.
     * @param south The south exit.
     * @param west The west exit.
     */

    public void setExits(String direction, Room exit){
    	 exits.put(direction, exit);
	}

   public Room getExit(String direction){
   	return exits.get(direction);
	}


    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }

     public String getExitString(){
     	StringBuilder info = new StringBuilder(128);
     	info.append("Exits: ");

     	Set<String> keys = exits.keySet();
     	for(String exit : keys)
     		info.append(" " + exit);
     	return info.toString();
     }

    public String getLongDescription(){
    	return "Your are " + description + ".\n" + getExitString();

     }

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public void setExit(String direction, Room neighbor) {
		 exits.put(direction, neighbor);
		
	}

  }	

