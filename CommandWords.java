/**
 * This class is part of the "World of Zuul" application. 
 * "Elijah Konkle" is a very simple, text based adventure game.  
 * 
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Michael Kölling and David J. Barnes and Elijah Konkle
 * @version 2024.04.27
 */

public class CommandWords
{
    // a constant array that holds all valid command words
    private static final String[] validCommands = {
        "go", "quit", "help", "take", "back", "look", "drop","items", 
        "use","on","start"
    };

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
        // nothing to do at the moment...
    }

    /**
     * Check whether a given String is a valid command word. 
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public boolean isCommand(String aString)
    {
        for(int i = 0; i < validCommands.length; i++) {
            if(validCommands[i].equals(aString))
                return true;
        }
        // if we get here, the string was not found in the commands
        return false;
    }
    /**
     * @return A string of all the valid commands.
     */
    public String userCommands() {
        String message = "   ";
        for(String vc : validCommands) {
            message += vc + " ";
        }
        return message;
    }
}
