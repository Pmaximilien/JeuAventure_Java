/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */

 import java.util.HashMap;

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private int etat;
    private HashMap<String, Room> salle;
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
	parser = new Parser();
	salle = new HashMap<String, Room>();

	createRooms();
	etat = 20;
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
	Room outside, theatre, pub, lab, office;

	// create the rooms
	outside = new Room("outside the main entrance of the university");
	theatre = new Room("in a lecture theatre");
	pub = new Room("in the campus pub");
	lab = new Room("in a computing lab");
	office = new Room("in the computing admin office");

	// initialise room exits
	outside.setExits("east", theatre);
	outside.setExits("south", lab);
	outside.setExits("west", pub);

	theatre.setExits("west", outside);
	
	pub.setExits("east", outside);

	lab.setExits("north", outside);
	lab.setExits("east", office);

	office.setExits("west", lab);
	
	salle.put("outside", outside);
	salle.put("theatre", theatre);
	salle.put("pub", pub);
	salle.put("lab", lab);
	salle.put("office", office);

	currentRoom = outside;  // start game outside
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
	printWelcome();

	// Enter the main command loop.  Here we repeatedly read commands and
	// execute them until the game is over.

	boolean finished = false;
	while (! finished) {
	    Command command = parser.getCommand();
	    finished = processCommand(command);
	}
	System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
	System.out.println();
	System.out.println("Welcome to the World of Zuul!");
	System.out.println("World of Zuul is a new, incredibly boring adventure game.");
	System.out.println("Type 'help' if you need help.");
	System.out.println();

	printLocationInfo();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
	boolean wantToQuit = false;

	if(command.isUnknown()) {
	    System.out.println("I don't know what you mean...");
	    return false;
	}

	String commandWord = command.getCommandWord();
	if (commandWord.equals("help"))
	    printHelp();
	else if (commandWord.equals("go")){
	    if (etat > 0){
	    	goRoom(command);
		etat -= 5;}
		}
	else if (commandWord.equals("quit"))
	    wantToQuit = quit(command);
	else if (commandWord.equals("look"))
		look(command);
	else if (commandWord.equals("status")){
		if (etat < 0)
			System.out.println("You are hungry");
		else
			System.out.println("You are not hungry ("+etat+")");
		}
	else if (commandWord.equals("eat"))
		etat += 5;

	return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
	System.out.println("You are lost. You are alone. You wander");
	System.out.println("around at the university.");
	System.out.println();
	System.out.println("Your command words are:");
	System.out.println("   go quit help");
    }

    /** 
     * Try to go to one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
    	Room nextRoom;

	if(!command.hasSecondWord()) {
	    // if there is no second word, we don't know where to go...
	    System.out.println("Go where?");
	    return;
	}

	String direction = command.getSecondWord();

	// Try to leave current room.
	    nextRoom = currentRoom.getExit(direction);

	if (nextRoom == null) {
	    System.out.println("There is no door!");
	}
	else {
	    currentRoom = nextRoom;
	    printLocationInfo();
	}
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
	if(command.hasSecondWord()) {
	    System.out.println("Quit what?");
	    return false;
	}
	else {
	    return true;  // signal that we want to quit
	}
    }
    
    private void look(Command command)
    {
    	if (command.hasSecondWord()) {
		System.out.println("Look what?");
		return;
	}
	else {
		printLocationInfo();	
	}
}

private void printLocationInfo(){
	

            System.out.println(currentRoom.getLongDescription());

 }
}
