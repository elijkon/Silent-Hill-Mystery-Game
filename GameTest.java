import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

/**
 * GameTest - a class to test the zuul game engine.
 *
 * @author  William H. Hooper
 * @version 2018-11-19
 */
public class GameTest
{
    private Game game1;
    private Console console1; 

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
    {
        System.out.println("\f");
        game1 = new Game();
        console1 = new Console(game1);
        String message = game1.readMessages();
        System.out.print(message + "> ");
    }

    @Test public void noBackpack(){
        assertEquals(true, console1.testCommand("use backpack", "You do not have anything to use in inventory."));
    }

    @Test public void useBackpack(){
        assertEquals(true, console1.testCommand("go east", "lighthouse"));
        assertEquals(true, console1.testCommand("go up", "tower"));
        assertEquals(true, console1.testCommand("go north", "living quarters"));
        assertEquals(true, console1.testCommand("look", "backpack"));
        assertEquals(true, console1.testCommand("take backpack", "You have taken the backpack."));
        assertEquals(true, console1.testCommand("use backpack", "You increased your inventory space by 3 oz's."));
    }

    @Test public void toHeavy(){
        assertEquals(true, console1.testCommand("go down", "sewers"));
        assertEquals(true, console1.testCommand("look", "batteries"));
        assertEquals(true, console1.testCommand("take batteries", "You have taken the batteries."));
        assertEquals(true, console1.testCommand("take lever", "You have taken the lever."));
        assertEquals(true, console1.testCommand("go north", "basement"));
        assertEquals(true, console1.testCommand("look", "syringe"));
        assertEquals(true, console1.testCommand("take syringe", "You have taken the syringe."));
        assertEquals(true, console1.testCommand("go up", "hospital"));
        assertEquals(true, console1.testCommand("go south", "streets"));
        assertEquals(true, console1.testCommand("go east", "lighthouse"));
        assertEquals(true, console1.testCommand("go north", "shore"));
        assertEquals(true, console1.testCommand("look", "flashlight"));
        assertEquals(true, console1.testCommand("take flashlight", "You can not carry anymore."));
    }

    @Test
    public void Items() {
        assertEquals(true, console1.testCommand("go west", "school"));
        assertEquals(false, console1.testCommand("go south", "principals"));
        assertEquals(false, console1.testCommand("look", "flashlight"));
        assertEquals(false, console1.testCommand("take flashlight", "You have taken the flashlight."));
        assertEquals(true, console1.testCommand("go west", "janitors closet"));
        assertEquals(true, console1.testCommand("go east", "school"));
        assertEquals(true, console1.testCommand("go north", "lunch room"));
        assertEquals(true, console1.testCommand("go west", "room #125"));
        assertEquals(true, console1.testCommand("go north", "bathroom"));
        assertEquals(true, console1.testCommand("look", "You need the flashlight"));
        assertEquals(false, console1.testCommand("take key", "You have taken the key."));
        assertEquals(true, console1.testCommand("go south", "room #125"));
        assertEquals(true, console1.testCommand("go south", "sewers"));
        assertEquals(true, console1.testCommand("look", "batteries"));
        assertEquals(true, console1.testCommand("take batteries", "You have taken the batteries."));
        assertEquals(true, console1.testCommand("look", "lever"));
        assertEquals(true, console1.testCommand("take lever", "You have taken the lever."));
        assertEquals(true, console1.testCommand("go up", "streets"));
        assertEquals(true, console1.testCommand("go down", "sewers"));
        assertEquals(true, console1.testCommand("go north", "basement"));
        assertEquals(true, console1.testCommand("look", "syringe"));
        assertEquals(true, console1.testCommand("take syringe", "You have taken the syringe."));
        assertEquals(true, console1.testCommand("go up", "hospital"));
        assertEquals(true, console1.testCommand("go east", "supplies room"));
        assertEquals(true, console1.testCommand("go north", "waiting room"));
        assertEquals(true, console1.testCommand("go south", "hospital"));
        assertEquals(true, console1.testCommand("go north", "waiting room"));
        assertEquals(true, console1.testCommand("go south", "hospital"));
        assertEquals(true, console1.testCommand("go east", "supplies room"));
        assertEquals(true, console1.testCommand("go west", "hospital"));
        assertEquals(true, console1.testCommand("go south", "streets"));
        assertEquals(true, console1.testCommand("go east", "lighthouse"));
        assertEquals(true, console1.testCommand("go north", "shore"));
        assertEquals(true, console1.testCommand("look", "flashlight"));
        assertEquals(true, console1.testCommand("take flashlight", "You can not carry anymore."));
        assertEquals(true, console1.testCommand("go south", "lighthouse"));
        assertEquals(true, console1.testCommand("go up", "tower"));
        assertEquals(true, console1.testCommand("go north", "living quarters"));
        assertEquals(true, console1.testCommand("go south", "tower"));
        assertEquals(true, console1.testCommand("go south", "fueling room"));
        assertEquals(true, console1.testCommand("go north", "tower"));
        assertEquals(true, console1.testCommand("go down", "lighthouse"));
        assertEquals(true, console1.testCommand("go west", "streets"));
        assertEquals(true, console1.testCommand("go north", "hospital"));
        assertEquals(true, console1.testCommand("go down", "basement"));
        assertEquals(true, console1.testCommand("go south", "sewers"));
        assertEquals(true, console1.testCommand("go west", "room #125"));
        assertEquals(true, console1.testCommand("go east", "lunch room"));
        assertEquals(true, console1.testCommand("go south", "school"));
        assertEquals(true, console1.testCommand("go west", "janitors closet"));
        assertEquals(false, console1.testCommand("go south", "principals"));
        assertEquals(true, console1.testCommand("go east", "school"));
        assertEquals(true, console1.testCommand("go east", "streets"));
        console1.testCommand("items");
    }

    @Test
    public void drop() {
        assertEquals(true, console1.testCommand("go down", "sewer"));
        assertEquals(true, console1.testCommand("look", "batteries"));
        assertEquals(true, console1.testCommand("look", "lever"));
        assertEquals(true, console1.testCommand("take batteries", "You have taken the batteries."));
        console1.testCommand("back");
        assertEquals(true, console1.testCommand("drop batteries", "You have dropped the batteries."));
        assertEquals(true, console1.testCommand("look", "batteries"));
        assertEquals(false, console1.testCommand("items", "batteries"));
    }

    @Test
    public void start()
    {
        assertEquals(false, game1.finished());
    }

    @Test
    public void noback(){
        assertEquals(true, console1.testCommand("back", "No previous room"));
    }

    @Test
    public void back() {
        assertEquals(true, console1.testCommand("go west", "school"));
        assertEquals(true, console1.testCommand("back", "streets"));
    }

    @Test
    public void backOnlyOnce() {
        assertEquals(true, console1.testCommand("go west", "school"));
        assertEquals(true, console1.testCommand("back", "streets"));
        assertEquals(true, console1.testCommand("back", "You can only go back once"));
    }

    @Test
    public void map()
    {
        assertEquals(true, console1.testCommand("go west", "school"));
        assertEquals(false, console1.testCommand("go south", "principals"));
        assertEquals(true, console1.testCommand("go west", "janitors closet"));
        assertEquals(true, console1.testCommand("go east", "school"));
        assertEquals(true, console1.testCommand("go north", "lunch room"));
        assertEquals(true, console1.testCommand("go west", "room #125"));
        assertEquals(true, console1.testCommand("go north", "bathroom"));
        assertEquals(true, console1.testCommand("go south", "room #125"));
        assertEquals(true, console1.testCommand("go south", "sewers"));
        assertEquals(true, console1.testCommand("go up", "streets"));
        assertEquals(true, console1.testCommand("go down", "sewers"));
        assertEquals(true, console1.testCommand("go north", "basement"));
        assertEquals(true, console1.testCommand("go up", "hospital"));
        assertEquals(true, console1.testCommand("go east", "supplies room"));
        assertEquals(true, console1.testCommand("go north", "waiting room"));
        assertEquals(true, console1.testCommand("go south", "hospital"));
        assertEquals(true, console1.testCommand("go north", "waiting room"));
        assertEquals(false, console1.testCommand("go south", "supplies room"));
        assertEquals(true, console1.testCommand("go east", "supplies room"));
        assertEquals(true, console1.testCommand("go west", "hospital"));
        assertEquals(true, console1.testCommand("go south", "streets"));
        assertEquals(true, console1.testCommand("go east", "lighthouse"));
        assertEquals(true, console1.testCommand("go north", "shore"));
        assertEquals(true, console1.testCommand("go south", "lighthouse"));
        assertEquals(true, console1.testCommand("go up", "tower"));
        assertEquals(true, console1.testCommand("go north", "living quarters"));
        assertEquals(true, console1.testCommand("go south", "tower"));
        assertEquals(true, console1.testCommand("go south", "fueling room"));
        assertEquals(true, console1.testCommand("go north", "tower"));
        assertEquals(true, console1.testCommand("go down", "lighthouse"));
        assertEquals(true, console1.testCommand("go west", "streets"));
        assertEquals(true, console1.testCommand("go north", "hospital"));
        assertEquals(true, console1.testCommand("go down", "basement"));
        assertEquals(true, console1.testCommand("go south", "sewers"));
        assertEquals(true, console1.testCommand("go west", "room #125"));
        assertEquals(true, console1.testCommand("go east", "lunch room"));
        assertEquals(true, console1.testCommand("go south", "school"));
        assertEquals(true, console1.testCommand("go west", "janitors closet"));
        assertEquals(false, console1.testCommand("go south", "principals"));
        assertEquals(true, console1.testCommand("go east", "school"));
        assertEquals(true, console1.testCommand("go east", "streets"));
    }

    @Test
    public void noDoor()
    {
        assertEquals(true, console1.testCommand("go north", "hospital"));
        assertEquals(true, console1.testCommand("go west", "no door"));
    }

    @Test
    public void useBatteriesAndLook(){
        assertEquals(true, console1.testCommand("go down", "sewer"));
        assertEquals(true, console1.testCommand("look", "batteries"));
        assertEquals(true, console1.testCommand("look", "lever"));
        assertEquals(true, console1.testCommand("take batteries", "You have taken the batteries."));
        console1.testCommand("back");
        assertEquals(true, console1.testCommand("go east", "lighthouse"));
        assertEquals(true, console1.testCommand("go north", "shore"));
        assertEquals(true, console1.testCommand("look", "flashlight"));
        assertEquals(true, console1.testCommand("take flashlight", "You have taken the flashlight."));
        assertEquals(true, console1.testCommand("use batteries", "You have put the batteries into the flashlight!"));
        assertEquals(true, console1.testCommand("go south", "lighthouse"));
        assertEquals(true, console1.testCommand("go west", "streets"));
        assertEquals(true, console1.testCommand("go west", "school"));
        assertEquals(true, console1.testCommand("go north", "lunch room"));
        assertEquals(true, console1.testCommand("go west", "room #125"));
        assertEquals(true, console1.testCommand("go north", "bathroom"));
        assertEquals(true, console1.testCommand("look", "key"));
        assertEquals(true, console1.testCommand("take key", "You have taken the key."));
        assertEquals(true, console1.testCommand("go south", "room #125"));
        assertEquals(true, console1.testCommand("go east", "lunch room"));
        assertEquals(true, console1.testCommand("go south", "school"));
        assertEquals(true, console1.testCommand("go south", "locked"));
        assertEquals(true, console1.testCommand("use key", "unlocked"));
        assertEquals(true, console1.testCommand("go south", "principals"));
    }
    //taking the gas and using it in the fuel house to fill up tank
    @Test
    public void useGas(){
        //get backpack
        assertEquals(true, console1.testCommand("go east", "lighthouse"));
        assertEquals(true, console1.testCommand("go up", "tower"));
        assertEquals(true, console1.testCommand("go north", "living quarters"));
        assertEquals(true, console1.testCommand("look", "backpack"));
        assertEquals(true, console1.testCommand("take backpack", "You have taken the backpack."));
        assertEquals(true, console1.testCommand("use backpack", "You increased your inventory space by 3 oz's."));
        console1.testCommand("back");
        assertEquals(true, console1.testCommand("go down", "lighthouse"));
        assertEquals(true, console1.testCommand("go west", "streets"));

        assertEquals(true, console1.testCommand("go down", "sewer"));
        assertEquals(true, console1.testCommand("look", "batteries"));
        assertEquals(true, console1.testCommand("look", "lever"));
        assertEquals(true, console1.testCommand("take batteries", "You have taken the batteries."));
        console1.testCommand("back");
        assertEquals(true, console1.testCommand("go east", "lighthouse"));
        assertEquals(true, console1.testCommand("go north", "shore"));
        assertEquals(true, console1.testCommand("look", "flashlight"));
        assertEquals(true, console1.testCommand("take flashlight", "You have taken the flashlight."));
        assertEquals(true, console1.testCommand("use batteries", "You have put the batteries into the flashlight!"));
        assertEquals(true, console1.testCommand("go south", "lighthouse"));
        assertEquals(true, console1.testCommand("go west", "streets"));
        assertEquals(true, console1.testCommand("go west", "school"));
        assertEquals(true, console1.testCommand("go north", "lunch room"));
        assertEquals(true, console1.testCommand("go west", "room #125"));
        assertEquals(true, console1.testCommand("go north", "bathroom"));
        assertEquals(true, console1.testCommand("look", "key"));
        assertEquals(true, console1.testCommand("take key", "You have taken the key."));
        assertEquals(true, console1.testCommand("go south", "room #125"));
        assertEquals(true, console1.testCommand("go east", "lunch room"));
        assertEquals(true, console1.testCommand("go south", "school"));
        assertEquals(true, console1.testCommand("go south", "locked"));
        assertEquals(true, console1.testCommand("use key", "unlocked"));
        assertEquals(true, console1.testCommand("go south", "principals"));
        assertEquals(true, console1.testCommand("look", "fuel"));
        console1.testCommand("items");
        assertEquals(true, console1.testCommand("drop key", "You have dropped the key."));
        assertEquals(true, console1.testCommand("drop flashlight", "You have dropped the flashlight."));
        assertEquals(true, console1.testCommand("take fuel", "You have taken the fuel."));
        assertEquals(true, console1.testCommand("use fuel", "You can not use this here."));
        assertEquals(true, console1.testCommand("go north", "school"));
        assertEquals(true, console1.testCommand("go east", "streets"));
        assertEquals(true, console1.testCommand("go east", "lighthouse"));
        assertEquals(true, console1.testCommand("go up", "tower"));
        assertEquals(true, console1.testCommand("go south", "fueling room"));
        assertEquals(true, console1.testCommand("look", "You need to add fuel to power the lighthouse."));
        assertEquals(true, console1.testCommand("use fuel", "You have put fuel into the fuel tank."));
        assertEquals(false, console1.testCommand("look", "You need to add fuel to power the lighthouse."));
        assertEquals(true, console1.testCommand("look", "The fuel tank is full but there is no electricity."));
    }

    @Test
    public void canUWin(){
        //get backpack
        assertEquals(true, console1.testCommand("go east", "lighthouse"));
        assertEquals(true, console1.testCommand("go up", "tower"));
        assertEquals(true, console1.testCommand("go north", "living quarters"));
        assertEquals(true, console1.testCommand("look", "backpack"));
        assertEquals(true, console1.testCommand("take backpack", "You have taken the backpack."));
        assertEquals(true, console1.testCommand("use backpack", "You increased your inventory space by 3 oz's."));
        console1.testCommand("back");
        assertEquals(true, console1.testCommand("go down", "lighthouse"));
        assertEquals(true, console1.testCommand("go west", "streets"));

        assertEquals(true, console1.testCommand("go down", "sewer"));
        assertEquals(true, console1.testCommand("look", "batteries"));
        assertEquals(true, console1.testCommand("look", "lever"));
        assertEquals(true, console1.testCommand("take batteries", "You have taken the batteries."));
        console1.testCommand("back");
        assertEquals(true, console1.testCommand("go east", "lighthouse"));
        assertEquals(true, console1.testCommand("go north", "shore"));
        assertEquals(true, console1.testCommand("look", "flashlight"));
        assertEquals(true, console1.testCommand("take flashlight", "You have taken the flashlight."));
        assertEquals(true, console1.testCommand("use batteries", "You have put the batteries into the flashlight!"));
        assertEquals(true, console1.testCommand("go south", "lighthouse"));
        assertEquals(true, console1.testCommand("go west", "streets"));
        assertEquals(true, console1.testCommand("go west", "school"));
        assertEquals(true, console1.testCommand("go north", "lunch room"));
        assertEquals(true, console1.testCommand("go west", "room #125"));
        assertEquals(true, console1.testCommand("go north", "bathroom"));
        assertEquals(true, console1.testCommand("look", "key"));
        assertEquals(true, console1.testCommand("take key", "You have taken the key."));
        assertEquals(true, console1.testCommand("go south", "room #125"));
        assertEquals(true, console1.testCommand("go east", "lunch room"));
        assertEquals(true, console1.testCommand("go south", "school"));
        assertEquals(true, console1.testCommand("go south", "locked"));
        assertEquals(true, console1.testCommand("use key", "unlocked"));
        assertEquals(true, console1.testCommand("go south", "principals"));
        assertEquals(true, console1.testCommand("look", "fuel"));
        console1.testCommand("items");
        assertEquals(true, console1.testCommand("drop key", "You have dropped the key."));
        assertEquals(true, console1.testCommand("drop flashlight", "You have dropped the flashlight."));
        assertEquals(true, console1.testCommand("take fuel", "You have taken the fuel."));
        assertEquals(true, console1.testCommand("use fuel", "You can not use this here."));
        assertEquals(true, console1.testCommand("go north", "school"));
        assertEquals(true, console1.testCommand("go east", "streets"));
        assertEquals(true, console1.testCommand("go east", "lighthouse"));
        assertEquals(true, console1.testCommand("go up", "tower"));
        assertEquals(true, console1.testCommand("go south", "fueling room"));
        assertEquals(true, console1.testCommand("look", "You need to add fuel to power the lighthouse."));
        assertEquals(true, console1.testCommand("use fuel", "You have put fuel into the fuel tank."));
        assertEquals(false, console1.testCommand("look", "You need to add fuel to power the lighthouse."));
        assertEquals(true, console1.testCommand("look", "The fuel tank is full but there is no electricity."));
        assertEquals(true, console1.testCommand("go north ", "tower"));
        assertEquals(true, console1.testCommand("go down", "lighthouse"));
        assertEquals(true, console1.testCommand("go west", "streets"));
        assertEquals(true, console1.testCommand("go down", "sewers"));
        assertEquals(true, console1.testCommand("look", "lever"));
        assertEquals(true, console1.testCommand("take lever", "You have taken the lever."));
        console1.testCommand("items");
        assertEquals(true, console1.testCommand("go up", "streets"));
        assertEquals(true, console1.testCommand("go west", "school"));
        assertEquals(true, console1.testCommand("go west", "janitors closet"));
        assertEquals(true, console1.testCommand("look", "It seems that there is a circuit breaker you can use but is missing a lever."));
        assertEquals(true, console1.testCommand("use lever", "You put the lever on the circuit breaker and can now turn it on."));
        assertEquals(true, console1.testCommand("on", "You have started the power!"));
        assertEquals(true, console1.testCommand("go east", "school"));
        assertEquals(true, console1.testCommand("go east", "streets"));
        assertEquals(true, console1.testCommand("go east", "lighthouse"));
        assertEquals(true, console1.testCommand("go up", "tower"));
        assertEquals(true, console1.testCommand("go south", "fueling room"));
        assertEquals(true, console1.testCommand("start", "You have won and saved the town of Silent from the mysterious fog."));
    }
    @Test 
    public void on(){
        assertEquals(true, console1.testCommand("on", "There is nothing to turn on."));
        assertEquals(true, console1.testCommand("go west", "school"));
        assertEquals(true, console1.testCommand("go west", "janitors closet"));
        assertEquals(true, console1.testCommand("on", "The circuit breaker does not have a lever to use to turn it on."));
        console1.testCommand("start");
    }
    @Test
    public void noFuel(){
        assertEquals(true, console1.testCommand("go east", "lighthouse"));
        assertEquals(true, console1.testCommand("go up", "tower"));
        assertEquals(true, console1.testCommand("go south", "fueling room"));
        assertEquals(true, console1.testCommand("start", "There is no power currently."));
    }
    @Test
    public void quit()
        {
        console1.testCommand("quit");
        assertEquals(true, game1.finished());
        assertEquals(false, console1.testCommand("go North", "doorway"));
        assertEquals(true, console1.testCommand("anything", "game is over"));
    }

    
    @Test
    public void help()
    {
        String string1 = console1.getResponse("help");
        assertNotNull(string1);
        assertEquals(true, string1.contains("go"));
        assertEquals(true, string1.contains("quit"));
        assertEquals(true, string1.contains("help"));
        assertEquals(true, string1.contains("take"));
        assertEquals(true, string1.contains("back"));
        assertEquals(true, string1.contains("look"));
    }

    @Test
    public void getItem()
    {
        Room room1 = new Room("room");
        Item item1 = new Item("light", 5);
        room1.addItem("light", item1);
        assertNotNull(room1.getItem("light"));
    }

    @Test
    public void removeItem()
    {
        Room room1 = new Room("room");
        Item item1 = new Item("flash", 5);
        room1.addItem("flash", item1);
        room1.removeItem("flash");
        assertNull(room1.getItem("flash"));
    }
}

