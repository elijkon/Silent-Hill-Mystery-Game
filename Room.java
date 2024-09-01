import java.util.HashMap;
import java.util.Collection;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "Silent Hill" application. 
 * "Silent Hill" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kölling and David J. Barnes and Elijah Konkle
 * @version 2024.04.10
 */
public class Room 
{
    private String description;
    private String imageName;
    private String audioName;
    private HashMap<String, Room> exits;
    private HashMap<String, Item> items;
    private Item lock;
    private boolean looked;
    private boolean started;
    private boolean full;
    private boolean lever;
    private boolean power;
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<>();
        items = new HashMap<>();
        lock = null;
        looked = false;
        started = false;
        full = false;
        lever = false;
        power = false;
    }

    /**
     * Checks to see if power is on.
     * @return true if power is on, false if otherwise.
     */
    public boolean hasPower(){
        return power==true;
    }

    /**
     * Turns power on.
     */
    public void turnOn(){
        power = true;
    }

    /**
     * Checks to see if the lever is in place.
     * @return true if lever is connected, false otherwise.
     */
    public boolean hasLever(){
        return lever==true;
    }

    /**
     * Sets the lever to true when it is there. 
     */
    public void useLever(){
        lever = true;
    }

    /**
     * Checks to see if the fuel tank is full.
     * @return true if the fuel tank is full, false if otherwise. 
     */
    public boolean isFull(){
        return full==true;
    }

    /**
     * Sets the tank to full.
     */
    public void fill(){
        full=true;
    }

    /**
     * Checks to see if the lighthouse has been started.
     * @return true if the lighthouse is started, false if otherwise.
     */
    public boolean hasStarted(){
        return started==true;
    }

    /**
     * Sets the lighthouse to started.
     */
    public void start(){
        started = true;
    }

    /**
     * Checks to see if the room has been looked in.
     * @return true if the door is locked, false if otherwise. 
     */
    public boolean hasLooked(){
        return looked==true;
    }

    /**
     * Sets the room to true when looked.
     */
    public void look(){
        looked = true;
    }

    /**
     * Checks to see if the room is locked.
     * @return true if item is not null, false if otherwise.
     */
    public boolean isLocked(){
        return lock!=null;
    }

    /**
     * Sets the room to locked if the player has the key.
     * @param it The item being passed to it.
     */
    public void lock(Item it)
    {
        lock = it;
    }

    /**
     * If lock is not null than it unlocks door.
     * @return a string if door is locked and returns item if the item does
     * not fit.
     * @param it The item being passed to it.
     */
    public String unlock(Item it){
        if(lock == it){
            lock = null;
            return "Door is unlocked!";
        }
        return it + " doesn't fit this lock.";
    }

    /**
     * Adds every item in your inventory to a string called message.
     * @return a string called message. 
     */
    public String getItemString(){
        String message = "";
        for(Item it : items.values())
        {
            message += "\nYou see " + it +" It weighs " + it.getWeight() +" oz's.";
        }

        return message;
    }
    /**
     * Get all the exits to the room.
     * @return all the exits.
     */
    public Collection<Room> neighbors() {
        return exits.values();
    }

    /**
     * Removes and item from the iventory.
     * @param key Passes the key value to remove it from hashmap.
     */
    public void removeItem(String key){
        items.remove(key);
    }

    /**
     * Adds a item to the room.
     * @param key The key attached to the value.
     * @param item The item attached to the key. 
     */
    public void addItem(String key, Item item){
        items.put(key, item);
    }
    /**
     * Gets the item using its key from the hashmap.
     * @return the item.
     */
    public Item getItem(String key){
        return items.get(key);
    }

    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     */
    public void setExit(String direction, Room room) {
        exits.put(direction, room);
    }
    /**
     * Gives the room given the direction from current room.
     * @return the exit depending on direction.
     */
    public Room getExit(String direction){
        return exits.get(direction);
    }
    /**
     * Gets all the exits that exist in the room
     * @return a string called message.
     */
    public String getExitString(){
        String message = "Exits:";
        for(String d : exits.keySet())
        {
            message += " " + d;
        }

        return message;
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }
    /**
     * @return a string of the room description.
     */
    public String getLongDescription() {
        return "You are " + getDescription() + "\n" 
        + getExitString();

    }
    /**
     * @return a string of the room description and items.
     */
    public String getLonggDescription() {
        return "You are " + getDescription() + "\n" 
        + getExitString()
        + getItemString();

    }

    /*************************************************************
     * added by William H. Hooper, 2006-11-28
     *************************************************************/
    /**
     * @return a String, which hopefully contains the file name
     * of an Image file.
     */
    public String getImage()
    {
        return imageName;
    }

    /**
     * associate an image with this room
     * @param filename a String containing a file.
     * The file <b>must</b> reside in the media directory, 
     * and must be in a format viewable in the Java AWT.
     * Readable formats include: 
     * PNG, JPG (RGB color scheme only), GIF
     */
    public void setImage(String filename)
    {
        imageName = "media/" + filename;
    }

    /**
     * @return a String, which hopefully contains the file name
     * of an audio file.
     */
    public String getAudio()
    {
        return audioName;
    }

    /**
     * associate an audio clip with this room
     * @param filename a String containing a file.
     * The file <b>must</b> reside in the media directory, 
     * and must be in a format palyable in the Java AWT.
     * Readable formats include: 
     * WAV, AU.
     */
    public void setAudio(String filename)
    {
        audioName = "media/" + filename;
    }
}
