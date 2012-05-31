import java.util.HashMap;

/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */

public class CommandWords
{
    // a constant array that holds all valid command words
    private HashMap<String, CommandWord> dispo;

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
    	dispo = new HashMap<String, CommandWord>();
	for(CommandWord command : CommandWord.values()) {
	   if(command != CommandWord.UNKNOWN){
	   	dispo.put(command.get_commande(), command);
	   }
	}
    }

    /**
     * Check whether a given String is a valid command word. 
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public CommandWord getCommandWord(String nom){
	CommandWord command = dispo.get(nom);
	
	if(command != null) {
		return command;
	}
	else {
		return CommandWord.UNKNOWN;
	}
}



    public boolean isCommand(String aString)
    {
    	return dispo.containsKey(aString);
    }

    public String getCommandList()
    {
    	String ResCmd = new String();

	for(String command : dispo.keySet()) {
		ResCmd += (command + " ");}
	return ResCmd;
    }
}

