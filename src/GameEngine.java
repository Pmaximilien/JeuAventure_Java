/*
 *  This class is part of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.
 * 
 *  This class creates all rooms, creates the parser and starts
 *  the game.  It also evaluates and executes the commands that 
 *  the parser returns.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 1.0 (Jan 2003)
 */
import java.util.HashMap;
import java.util.Stack;
import java.io.*;
import java.util.*;

public class GameEngine
{
    private Parser parser;
    private Stack<Room> backRoom;
    private UserInterface gui;
    private HashMap <String, Room> salle;
    private Player heros;
    private int timeline;


    /**
     * Constructor for objects of class GameEngine
     */
    public GameEngine()
    {
        parser = new Parser();
        salle = new HashMap<String, Room>();
        backRoom = new Stack<Room>();
	heros = new Player("Bitch");
        createRooms();


	timeline = 20;
    }

    public void setGUI(UserInterface userInterface)
    {
        gui = userInterface;
        printWelcome();
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        gui.print("\n");
        gui.println("Welcome to the World of Zuul!");
        gui.println("World of Zuul is a new, incredibly boring adventure game.");
        gui.println("Type 'help' if you need help.");
        gui.print("\n");
        gui.println((heros.get_position()).getLongDescription());
        gui.showImage((heros.get_position()).getImageName());
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room porteVille, entreeVille, marchand, place2, place3;
        Item bouteille, cadavre_moisie; 
	Nourriture cookie;
	Cookie_magique ass;
	Beamer start;

        // create the rooms
        porteVille  = new Room("Porte", "images/img_840x525/entree.jpg");
        entreeVille = new Room("Entree", "images/img_840x525/place1.jpg");
        marchand    = new Room("Marchand", "images/img_840x525/maison1.jpg");
        place2      = new Room("Place1", "images/img_840x525/place2.jpg");
        place3      = new Room("Place2", "images/img_840x525/place3.jpg");
        
        // create the doors
	
	new Door(porteVille, entreeVille );
	new Door(entreeVille, true, marchand, true);
	new Door(entreeVille, true, place2, true);
	new Door(entreeVille, true, place3, true);
	
      
        salle.put("porte_de_la_ville", porteVille);
        salle.put("entree_de_la_ville", entreeVille);
        salle.put("marchand", marchand);
        salle.put("place2", place2);
        salle.put("place3", place2);

	bouteille = new Item("bouteille", 1, 1);
	cadavre_moisie = new Item("cadavre", 0, 50);
	cookie = new Nourriture(10, "cookie");
	start = new Beamer(porteVille);
	ass = new Cookie_magique(10);

	marchand.setItem("bouteille", bouteille);
	marchand.setItem("cadavre_moisie", cadavre_moisie);
	place2.setItem(start.getNameItem(), start);
	entreeVille.setItem("cookie", cookie);
	porteVille.setItem(ass.getNameItem(), ass);
			

		
	heros.teleporte(porteVille);

    }

    /**
     * Given a command, process (that is: execute) the command.
     * If this command ends the game, true is returned, otherwise false is
     * returned.
     */
    public void interpretCommand(String commandLine) 
    {
    	
	Room current = heros.get_position();

        gui.println(commandLine);
        Command command = parser.getCommand(commandLine);

        if(command.isUnknown()) {
            gui.println("I don't know what you mean...");
            return;
        }

	// Test fin de jeu
	if (timeline < 0){
		gui.println("-------------------------");
		gui.println("|               ");
		gui.println("|    ARrRg !    ");
		gui.println("|   Trop lent ! ");
		gui.println("|   T'es mort ! ");
		gui.println("|               ");
		gui.println("|------------------------");
		gui.println("");
                endGame();
		return; }


        String commandWord = command.getCommandWord();
	String secondWord = command.getSecondWord();

        if (commandWord.equals("help"))
            printHelp();

	else if (commandWord.equals("go")){
	    if (command.hasSecondWord()){
		if(heros.deplace(command.getSecondWord())){ 
		    timeline--;
		    backRoom.push(current);
		}
		else{
		    gui.println("Cannot mouve to "+command.getSecondWord());}
	    }
	    else{
		gui.println("Move who ?");
	    }

	}
		


        else if (commandWord.equals("quit")) {
            if(command.hasSecondWord())
                gui.println("Quit what?");
            else
                endGame();}
	else if (commandWord.equals("look")){
		if (command.hasSecondWord()){
			if ((command.getSecondWord()).equals("sac")){
				gui.println("In the bag : ");
				gui.println(heros.getSacString());
			}
		}else{
        		gui.println((heros.get_position()).getLongDescription());
            		gui.println((heros.get_position()).getItemString());}
			
	}
				
	else if (commandWord.equals("eat")){
		if (command.hasSecondWord()) {
			if (!heros.utilise(command.getSecondWord())){
				gui.println("cannot do that");}
			else{
				timeline--;
			}
		}
		else {
			gui.println("eat what ?");}
	}

	else if (commandWord.equals("use")){
		if (command.hasSecondWord()) {
			if (!heros.utilise(command.getSecondWord())){
				gui.println("cannot do that");}
			else{
				timeline--;
			}
		}
		else {
			gui.println("use what ?");}
	}

	else if (commandWord.equals("status"))
		gui.println("Etat : "+ heros.get_ventre());
	else if (commandWord.equals("back"))
    		goBack();
	else if (commandWord.equals("execute"))
		execute(command);
	else if (commandWord.equals("take"))
		take(command);
	else if (commandWord.equals("drop"))
		drop(command);
		
	if(current != heros.get_position()){
		current = heros.get_position();
		gui.println(current.getLongDescription());
		gui.println(current.getItemString());
		if(current.getImageName() != null)
			gui.showImage(current.getImageName());
	}
	
    
}

    // implementations of user commands:
    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        gui.println("You are lost. You are alone. You wander");
        gui.println("around at Monash Uni, Peninsula Campus." + "\n");
        gui.print("Your command words are: " + parser.showCommands());
    }
    

    private void execute(Command command) 
    {
	File fichier;
	Scanner it;


	if(!command.hasSecondWord()) {
		gui.println("I see what ? What ?");
		return;}
	else{
		try {
			fichier = new File(command.getSecondWord());
			it = new Scanner(fichier);
			while(it.hasNextLine())
    				interpretCommand(it.nextLine());

		} catch (FileNotFoundException e){
				gui.println("Pas de fichier...");
		}
	}
}

    private void drop(Command command) {
    	String item;

    	if(!command.hasSecondWord()) { 
		gui.println("Wath da fuck... ?");
		return;}
	else {
		item = command.getSecondWord();
		if (heros.hasItem(item)){
			timeline--;
			Item truc =  heros.drop_item(item);
			(heros.get_position()).addItem(truc);}
		else{
				gui.println("This item doesn't exist");}
	}
    }

    private void take(Command command) {
    	String item;

    	if(!command.hasSecondWord()) { 
		gui.println("Wath da fuck... ?");
		return;}
	else {
		item = command.getSecondWord();
		if ((heros.get_position()).hasItem(item)){
			heros.add_item((heros.get_position()).removeItem(item));
			timeline--;}
		else{
				gui.println("This item doesn't exist");}
	}
    }

		
			

    private void goBack(){
    	if(backRoom.empty()){
    		gui.println("Pas de back Room");}
	else{
	    if(heros.deplace(backRoom.pop())){	
		    timeline--;
		    Room currentRoom = heros.get_position();
		    gui.println(currentRoom.getLongDescription());
		    gui.println(currentRoom.getItemString());
		    if(currentRoom.getImageName() != null)
		    	gui.showImage(currentRoom.getImageName());
	    else {
	    	gui.println("Cannot back");
	    	gui.println("");
	    	gui.println("");
	    }
	}	
    	
    }
}

    private void endGame()
    {
        gui.println("Thank you for playing.  Good bye.");
        gui.enable(false);
    }

}
