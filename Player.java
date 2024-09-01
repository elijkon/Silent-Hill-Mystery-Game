import java.util.HashMap;

/**
 * Write a description of class Player here.
 *
 * @author Elijah Konkle
 * @version 2024.04.27
 */
public class Player
{
    // instance variables - replace the example below with your own
    private Room currentRoom;
    private String name;
    private HashMap<String, Item> items;
    private int Limit;
    /**
     * Constructor for objects of class Player
     */
    public Player(String name)
    {
        // initialise instance variables
        this.name = name;
        items = new HashMap<>();
        Limit = 8;
    }
    /**
     * Takes a item from a room.
     * @param key A string that is the key to the item being taken
     * @return A string that describes what you have taken
     */
    public String take(String key){
        Item it = currentRoom.getItem(key);
        addItem(key, it);
        currentRoom.removeItem(key);
        return "You have taken the " + key + ".";
    }
    /**
     * Drops a item from inventory.
     * @param key The key of the item you are dropping.
     * @return A string saying what you have dropped.
     */
    public String drop(String key){
        if (getItem(key) == null){
            return "This item is not in your inventory.";
        }
        Item it = getItem(key);
        currentRoom.addItem(key, it);
        removeItem(key);
        return "You have dropped the " + key + ".";
    }
    /**
     * Sets a the room to a the current room.
     */
    public void setRoom(Room room){
        currentRoom = room;
    }
    /**
     * @return The current room.
     */
    public Room getRoom(){
        return currentRoom;
    }
    /**
     * @return The max weight a player can carry.
     */
    public int getLimit() {
        return Limit;
    }
    /**
     * Increase your max weight limit.
     */
    public void increaseL(int weight){
        Limit += weight;
    }
    /**
     * Remove a item from the player.
     */
    public void removeItem(String key){
        items.remove(key);
    }
    /**
     * Add a item to the player.
     */
    public void addItem(String key, Item item){
        items.put(key, item);
    }
    /**
     * @param key The key to get the value and gets the item.
     */
    public Item getItem(String key){
        return items.get(key);
    }
    /**
     * @return Total weight required. 
     */
    public int totalWeight(){
        int sum = 0;
        for(Item it : items.values()){
            sum += it.getWeight();
        }
        return sum;
    }
    /**
     * @return All items in inventory.
     */
    public String getItemString(){
        String message = "Inventory:";
        for(Item it : items.values())
        {
            message += "\n " + it;
        }
        return message += "\n" +"Total weight is: " + totalWeight()
        + "\n" + "Your storage size is: " + getLimit();
    }
}
