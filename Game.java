import java.util.Timer;
import java.util.TimerTask;
import javax.sound.sampled.*;
import java.io.*;
/**
 *  This class is the main class of the "Silent Hill" application. 
 *  "Silent Hill" is a very simple, text based adventure game.  Users 
 *  must find the backpack, flashlight, batteries, lever, and fuel. To start
 *  the light house.
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes and Elijah Konkle
 * @version 2024.04.29
 */

public class Game 
{
    private Parser parser;
    private Room otherWorld;
    private Room previousR;
    private Player Cheryl;
    private Timer timer;
    private int tLimit;
    private Room janitorsCloset;
    private Room  fuelingR;
    
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        tLimit = 600000;
        Cheryl = new Player("Cheryl");
        createRooms();
        parser = new Parser();
        timer = new Timer();
        startTimer();
    }
    /**
     * starts a 10 minute timer. 
     */                                                                                                                                                                                                                              
    private void startTimer() {
        timer.schedule(new TimerTask() 
            {
                @Override
                public void run() {
                    println("You have ran out of time.");
                    wantToQuit = true;
                }
            }
        , tLimit); 
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room street, hospital, school, lightHouse, waitingR,
        supplies, basement, r125, lunchR, 
        livingQ, tower, otherWorld, principals, shore, sewer, br;
        // create the rooms
        street = new Room("in the foggy streets of Silent Hill.");
        sewer = new Room("in the dark and gross sewers under Silent Hill.");

        hospital = new Room("in a run down dirty hospital lobby.");
        //rooms inside hospital
        waitingR = new Room("in the waiting room.");
        supplies = new Room("in the supplies room.");
        basement = new Room("in the basement of the hospital.");

        school = new Room("in the towns abandoned elementary school.");
        //rooms inside of school
        r125 = new Room("in room #125.");
        lunchR = new Room("in the lunch room.");
        janitorsCloset = new Room ("in the janitors closet.");
        principals = new Room("in the principals office.");
        lightHouse = new Room("walking up the lighthouse on the hill.");
        br = new Room("in the bathroom in the school.");
        //rooms inside of the lighthouse
        fuelingR = new Room("in the fueling room.");
        livingQ = new Room("in the living quarters.");
        tower = new Room("walking up the lighthouse and arrive to the tower on top.");
        shore = new Room("on a boatdock looking past the shore.");
        
        //audio
        street.setAudio("streets.mp3");
        school.setAudio("schoolAudio.mp3");
        waitingR.setAudio("waitingRAudio.mp3");
        sewer.setAudio("sewers.mp3");
        livingQ.setAudio("livingQAudio.mp3");
        basement.setAudio("basementAudio.mp3");
        principals.setAudio("principalsAudio.mp3");
        hospital.setAudio("hospitalAudio.mp3");
        r125.setAudio("r.mp3");
        lightHouse.setAudio("lighthouseAudio.mp3");
        br.setAudio("brAudio.mp3");
        supplies.setAudio("s.mp3");
        lunchR.setAudio("lR.mp3");
        janitorsCloset.setAudio("j.mp3");
        fuelingR.setAudio("f.mp3");
        tower.setAudio("towerAudio.mp3");
        shore.setAudio("shoreAudio.mp3");
        //Items
        Item lever, key, batteries, syringe, flashlight, fuel, backpack;
        lever = new Item("a rusty lever for a circuit breaker.", 4);
        key = new Item("a dusty key with a emerald on it.", 1);
        batteries = new Item("a set of AAA batteries.", 2);
        syringe = new Item("a syringe that will give you more time.",1); 
        flashlight = new Item("a flashlight that will help you see.",5);
        fuel = new Item("a can of fuel that can be used to power things.", 10);
        backpack = new Item("a backpack that will increase your inventory by 3 oz's.", 3);
        //adding items
        sewer.addItem("lever", lever);
        sewer.addItem("batteries", batteries);
        br.addItem("key", key);
        basement.addItem("syringe", syringe);
        shore.addItem("flashlight", flashlight);
        principals.addItem("fuel", fuel);
        livingQ.addItem("backpack", backpack);
        principals.lock(key);
        //street to hospital
        street.setExit("north", hospital);
        //inside hospital
        hospital.setExit("south", street);
        hospital.setExit("north", waitingR);
        hospital.setExit("east", supplies);
        hospital.setExit("down",basement);
        waitingR.setExit("south", hospital);
        supplies.setExit("west", hospital);
        supplies.setExit("north", waitingR);
        basement.setExit("up", hospital);
        basement.setExit("south", sewer);
        //street to lighthouse
        street.setExit("east", lightHouse);
        //inside lighthouse
        lightHouse.setExit("west", street);
        lightHouse.setExit("up", tower);
        lightHouse.setExit("north", shore);
        shore.setExit("south", lightHouse);
        tower.setExit("down", lightHouse);
        tower.setExit("north", livingQ);
        livingQ.setExit("south", tower);
        tower.setExit("south", fuelingR);
        fuelingR.setExit("north", tower);
        //street to school
        street.setExit("west", school);
        //inside of school
        school.setExit("east", street);
        school.setExit("north", lunchR);
        school.setExit("west", janitorsCloset);
        school.setExit("south", principals);
        principals.setExit("north", school);
        principals.setExit("west", janitorsCloset);
        janitorsCloset.setExit("south", principals);
        janitorsCloset.setExit("east", school);
        lunchR.setExit("south", school);
        lunchR.setExit("west", r125);
        r125.setExit("east", lunchR);
        r125.setExit("south", sewer);
        r125.setExit("north", br);
        br.setExit("south", r125);
        //street to sewer
        street.setExit("down", sewer);
        //inside sewer
        sewer.setExit("west", r125);
        sewer.setExit("north", basement); 
        sewer.setExit("up", street);
        //assign images to hospital locations
        hospital.setImage("hospital.jpg");
        waitingR.setImage("waiting-room.jpg");
        supplies.setImage("supplies.jpg");
        basement.setImage("basement.jpg");
        //street and sewer image
        street.setImage("streets.jpg");
        sewer.setImage("sewers.jpg");
        //school locations image
        r125.setImage("room.jpg");
        school.setImage("school.jpg");
        lunchR.setImage("lunchroom.jpg");
        br.setImage("bathroom.jpg");
        janitorsCloset.setImage("closet.jpg");
        principals.setImage("princ.jpg");
        //light house locations image
        tower.setImage("light.jpg");
        lightHouse.setImage("LH.png");
        shore.setImage("shore.jpg");
        fuelingR.setImage("fuel.jpg");
        livingQ.setImage("living.jpg");
        // currentRoom = street;  // start game outside
        Cheryl.setRoom(street);
        previousR = null;
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        println();
        println("Welcome to Silent Hill.");
        println("Type 'help' if you need help.");
        println();

        Room currentRoom = Cheryl.getRoom();
        println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private void processCommand(Command command) 
    {
        if(command.isUnknown()) {
            println("I don't know what you mean...");
            return;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            quit(command);
        }
        else if(commandWord.equals("take")) {
            take(command);
        }
        else if (commandWord.equals("back")) {
            back();
        }
        else if (commandWord.equals("look")) {
            look();
        }else if (commandWord.equals("drop")){
            drop(command);
        }else if (commandWord.equals("items")) {
            items();
        }else if(commandWord.equals("use")) {
            use(command);
        }else if(commandWord.equals("on")) {
            on();
        }else if(commandWord.equals("start")) {
            congrats();
        }  
    }

    // implementations of user commands:
    /**
     * When the player starts the light house this method determines if he wins.
     * @return true If the command ends the game, false otherwise.
     */
    public void congrats(){
        Room cR = Cheryl.getRoom();
        if(cR.getDescription() != "in the fueling room."){
            println("There is nothing to start.");
            return;
        }
        if(janitorsCloset.hasPower() == true && cR.getDescription() == "in the fueling room."){
            if(fuelingR.isFull() == false){
                println("There is no fuel in the tank.");
                return;
            }
            println("You have won and saved the town of Silent from the mysterious fog.");
            wantToQuit = true;
        }
        if(janitorsCloset.hasPower() == false && cR.getDescription() == "in the fueling room."){
            println("There is no power currently.");
            return;
        }
    }
    /**
     * Shows the player everything in there inventory. 
     */
    private void items(){
        println(Cheryl.getItemString());
    }
    /**
     * Turns the electricity on so the player can start the lighthouse.
     * @return true if power is on, false otherwise.
     */
    public void on(){
        Room cR = Cheryl.getRoom();
        if(cR.getDescription() != "in the janitors closet."){
            println("There is nothing to turn on.");
            return;
        }
        if(cR.hasLever() == false){
            println("The circuit breaker does not have a lever to use to turn it on.");
            return;
        }
        if(cR.hasPower() == true){
            println("Power is already on.");
            return;
        }
        cR.turnOn();
        println("You have started the power!");
    }
    /**
     * Shows the player what is in the current room that they are in.
     * @return true If the player can look, false otherwise.
     */
    private void look() {
        Room currentRoom = Cheryl.getRoom();
        
        //looking in bathroom
        if(currentRoom.getDescription() == "in the bathroom in the school.") {
            String searchKey = "flashlight";
            Item it = Cheryl.getItem(searchKey);
            if(it == null){
                println("You need the flashlight to see in here.");
                return;
            }
            currentRoom.look();
            boolean b = it.haveBatteries();
            if(b == false){
                println("Your flashlight needs batteries.");
                return;
            }
            
        }
        //looking in fuel room
        if(currentRoom.getDescription() == "in the fueling room." 
        && currentRoom.isFull()==false) {
            println("You need to add fuel to power the lighthouse.");
        }else if(currentRoom.getDescription() == "in the fueling room." 
        && currentRoom.isFull()==true){
            println("The fuel tank is full but there is no electricity.");
        }
        //looking in janitors closet
        if(currentRoom.getDescription() == "in the janitors closet.") {
            println("It seems that there is a circuit breaker you can use but is missing a lever.");  
        }
        //looking in school
        if(currentRoom.getDescription() == "in the towns abandoned elementary school.") {
            println("The south door is locked.");  
        }
        currentRoom.look();
        println(currentRoom.getLonggDescription());

    }
    /**
     * Allows the player to pick up a item and store it in their inventory. 
     * @param command The item to be used.
     * @return true If the player uses something, false otherwise.
     */
    private void use(Command command){
        Room currentRoom = Cheryl.getRoom();
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know what to use...
            println("Use what?");
            return;
        }
        //searching for item if null say you dont have it
        String thing = command.getSecondWord();
        if(Cheryl.getItem(thing) == null){
            println("You do not have anything to use in inventory.");
            return;
        }
        //if you have item go through this 
        Item it = Cheryl.getItem(thing);
        int itemW = it.getWeight();
        String d = it.toString();

        //backpack logic 
        if(d == "a backpack that will increase your inventory by 3 oz's.") {
            Cheryl.increaseL(itemW);
            Cheryl.removeItem(thing);
            println("You increased your inventory space by 3 oz's.");
            return;
        }
        if (currentRoom.hasLooked() == false){
            println("You need to look around before you use items.");
            return;
        }
        //if you have item go through this 
        
        
        //key logic
        if(d == "a dusty key with a emerald on it." && currentRoom.getDescription()  != "in the towns abandoned elementary school." ) {
            println("No use here.");
            return;
        }
        if(d == "a dusty key with a emerald on it.") {
            for(Room nextRoom : currentRoom.neighbors()) {
                if(nextRoom.isLocked()) {
                    nextRoom.unlock(it);
                    println("You have unlocked the principals!");
                    return;
                }
            }
        }
        //use batteries
        if(d == "a set of AAA batteries."){
            Item fl = Cheryl.getItem("flashlight");
            if(fl != null){
                fl.insert();
                Cheryl.removeItem(thing);
                println("You have put the batteries into the flashlight!");
                return;
            }
            println("You do not have the flashlight yet.");
            return;
        }
        //use syringe
        if(d == "a syringe that will give you more time."){
            increaseTime();
            Cheryl.removeItem(thing);
            println("You have increased your time limit by 3 minutes.");
            return;
        }
        //use fuel
        if(d == "a can of fuel that can be used to power things." && 
        currentRoom.getDescription() != "in the fueling room.") {
            println("You can not use this here.");
            return;
        }else if(d == "a can of fuel that can be used to power things." && 
        currentRoom.getDescription() == "in the fueling room."){
            currentRoom.fill();
            Cheryl.removeItem(thing);
            println("You have put fuel into the fuel tank.");
        }
        //use lever
        if(d == "a rusty lever for a circuit breaker."&& 
        currentRoom.getDescription() != "in the janitors closet."){
            println("You can not use this here.");
            return;
        }
        if(d == "a rusty lever for a circuit breaker."&& 
        currentRoom.getDescription() == "in the janitors closet."){
            currentRoom.useLever();
            Cheryl.removeItem(thing);
            println("You put the lever on the circuit breaker and can now turn it on.");
        }
        //use flashlight
        if (d == "a flashlight that will help you see."){
            Item fl = Cheryl.getItem("flashlight");
            if(fl.haveBatteries() == true){
                println("The flashlight is already on since you put batteries in.");
                return;
            }
            println("Insert batteries and it will turn on automatically.");
        }

    }
    /**
     * Increases the time a player has by 3 minutes. 
     */
    private void increaseTime(){
        tLimit +=180000;
    }
    /**
     * Takes the player back to the previous room that they were in.
     * @return true If the player goes back, false otherwise.
     */
    private void back(){
        Room currentRoom = Cheryl.getRoom();
        if(previousR == null){
            println("No previous room");
            return;
        }
        else if(previousR.getDescription() == "in the supplies room.") {
            println("You can not go back into the supplies room this way.");
            return;
        }
        if(currentRoom != previousR) {
            // currentRoom = previousR;
            Cheryl.setRoom(previousR);
            println("You are " + previousR.getDescription());
        }else
        {
            println("You can only go back once");
        }
    }
    /**
     * Picks up the item in a room if the player has looked around in it.
     * @param command The item to take.
     * @return true If the player takes something, false otherwise.
     */
    private void take(Command command) {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            println("Take what?");
            return;
        }
        int weight = Cheryl.totalWeight();
        int Limit = Cheryl.getLimit();
        String secondW = command.getSecondWord();
        Room rm = Cheryl.getRoom();
        Item it = rm.getItem(secondW);
        if(it == null){
            println("It seems that is not in this room.");
            return;
        }
        if (rm.hasLooked() == false){
            println("You need to look around.");
            return;
        }
        int itemW = it.getWeight();
        weight += itemW;
        if(weight <= Limit) {
            println(Cheryl.take(command.getSecondWord()));
        }else{
            println("You can not carry anymore. Your current weight is: "
                + Cheryl.totalWeight() + ", " +"the "+ rm.getItem(secondW)+" weighs: " + itemW);
        }
    }
    /**
     * Drops the item if it is in your inventory.
     * @param command The command to be processed.
     * @return true If the player has dropped something, false otherwise.
     */
    private void drop(Command command) {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            println("Drop what?");
            return;
        }
        println(Cheryl.drop(command.getSecondWord()));
    }

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        println("You are lost. You are alone. You wander");
        println("around in the town of Silent Hill.");
        println();
        println("Your command words are:");
        println(parser.userCommands());
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     * @param command The command to be processed.
     * @return true If the player goes to another room, false otherwise.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            println("Go where?");
            return;
        }
        String direction = command.getSecondWord();
        Room currentRoom = Cheryl.getRoom();
        Room nextRoom = currentRoom.getExit(direction);
        if (nextRoom == null) {
            println("There is no door!");
            return;
        }
        if (nextRoom.isLocked()) {
            println("The door is locked!");
            currentRoom.look();
            return;
        }

        previousR = currentRoom;
        currentRoom = nextRoom;
        Cheryl.setRoom(nextRoom);
        println(currentRoom.getLongDescription());

    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     * @param command The command to be processed.
     */
    private void quit(Command command) 
    {
        if(command.hasSecondWord()) {
            println("Type only quit to exit the game");
            return;
        }

        wantToQuit = true;  // signal that we want to quit
    }

    /****************************************************************
     * If you want to launch an Applet
     ****************************************************************/

    /**
     * @return an Image from the current room
     * @see Image
     */
    public String getImage()
    {
        Room currentRoom = Cheryl.getRoom();
        return currentRoom.getImage();
    }

    /**
     * @return an audio clip from the current room
     * @see AudioClip
     */
    public String getAudio()
    {
        Room currentRoom = Cheryl.getRoom();
        return currentRoom.getAudio();
    }

    /****************************************************************
     * Variables & Methods added 2018-04-16 by William H. Hooper
     ****************************************************************/

    private String messages;
    private boolean wantToQuit;

    /**
     * Initialize the new variables and begin the game.
     */
    private void start()
    {
        messages = "";
        wantToQuit = false;
        printWelcome();
    }

    /**
     * process commands or queries to the game
     * @param input user-supplied input
     */
    public void processInput(String input)
    {
        if(finished()) {
            println("This game is over.");
            return;
        }

        Command command = parser.getCommand(input);
        processCommand(command);
    }

    /**
     * clear and return the output messages
     * @return current contents of the messages.
     */
    public String readMessages()
    {
        if(messages == null) {
            start();
        }
        String oldMessages = messages;
        messages = "";
        return oldMessages;
    }

    /**
     * @return true when the game is over.
     */
    public boolean finished()
    {
        return wantToQuit;
    }

    /**
     * add a message to the output list.
     * @param message the string to be displayed
     */
    private void print(String message)
    {
        messages += message;
    }

    /**
     * add a message to the output list, 
     * followed by newline.
     * @param message the string to be displayed
     * @see readMessages
     */
    private void println(String message)
    {
        print(message + "\n");
    }

    /**
     * add a blank line to the output list.
     */
    private void println()
    {
        println("");
    }
}
