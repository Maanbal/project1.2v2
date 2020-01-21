package src.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * This class is the main class of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game.  Users
 * can walk around some scenery. That's all. It should really be extended
 * to make it more interesting!
 * <p>
 * To play this game, create an instance of this class and call the "play"
 * method.
 * <p>
 * This main class creates and initialises all the others: it creates all
 * rooms, creates the parser and starts the game.  It also evaluates and
 * executes the commands that the parser returns.
 *
 * @author Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class Game {
    private Parser parser;
    private Room currentRoom;
    private Stack<Room> room;
    private Player player;

    /**
     * Create the game and initialise its internal map.
     */
    public Game() {
        createRooms();
        parser = new Parser();
        room = new Stack<>();
        player = new Player();
    }
    
    /**
     * Create all rooms, add descriptions, set isLocked
     */
    private void createRooms() {
        Room lobby, eastWing, cafeteria, infirmary, pharmacy, westWing, radiology, surgicalWard, emergencyRoom,
                stairwell, roof, basement, mortuary;
        // CREATE ROOMS
        lobby = new Room("lobby", "the heart of the hospital", 0);
        // East Wing
        eastWing = new Room("east wing", "a big hall with colorful walls", 0);
        cafeteria = new Room("cafeteria", "a lunchroom with eating tables and a kitchen", 0);
        infirmary = new Room("infirmary", "lots of empty hospital beds", 0);
        pharmacy = new Room("pharmacy", "a drug store, there seem to be some medicine left", 0);
        // West Wing
        westWing = new Room("west wing", "a gloomy, poorly lit hall", 1);
        radiology = new Room("radiology","machines that are used to make x-rays", 0);
        surgicalWard = new Room("surgical ward","hospital beds and heart monitors. you see some dried blood stains", 0);
        emergencyRoom = new Room("emergency room","a single operation table with surgical devices around it", 2);
        // Roof and Basement
        stairwell = new Room("stairwell", "stairs leading up and down", 3);
        roof = new Room("roof", "there seems to be nothing here", 0);
        basement = new Room("basement", "shelves, cabinets and cardboard boxes", 0);
        mortuary = new Room("mortuary", "large, metal drawers. There's an unpleasant smell in this place", 4);
        
        // CREATE EXITS
        lobby.setExit("east", eastWing);
        lobby.setExit("west", westWing);
        lobby.setExit("north", stairwell);
        // East Wing
        eastWing.setExit("west", lobby);
        eastWing.setExit("east", infirmary);
        eastWing.setExit("north", cafeteria);
        eastWing.setExit("south", pharmacy);
        cafeteria.setExit("south", eastWing);
        infirmary.setExit("west", eastWing);
        pharmacy.setExit("north", eastWing);
        // West Wing
        westWing.setExit("east", lobby);
        westWing.setExit("north", surgicalWard);
        westWing.setExit("south", radiology);
        radiology.setExit("north", westWing);
        surgicalWard.setExit("south", westWing);
        surgicalWard.setExit("west", emergencyRoom);
        emergencyRoom.setExit("east", surgicalWard);
        // Roof and Basement
        stairwell.setExit("south", lobby);
        stairwell.setExit("up", roof);
        stairwell.setExit("down", basement);
        roof.setExit("down", stairwell);
        basement.setExit("up", stairwell);
        basement.setExit("east", mortuary);
        mortuary.setExit("west", basement);
        
        // CREATE ITEMS
        Item key = new ItemKey("key", "A brass key", 1, 1);
        Item book = new Item("book", "The title reads: 'Dreams of Paradise'", 1, true);
        Item plant = new Item("plant", "A withering plant", 2, false);
        Item rock = new Item("rock", "A big rock", 10, true);
        Item chair = new Item("chair", "A black chair", 3, true);
        Item laptop = new ItemText("laptop", "It looks broken", 4, "It's just a blue screen...");
        Item pie = new ItemTransformer("prisoner's pie", "Could something be inside it?", 5,
                "You eat the pie... You find a key!", key);
        Item d6 = new ItemDie("d6", "It's a six sided die!", 1, 6);
        Item d20 = new ItemDie("d20", "It's a twenty sided die!", 1, 20);
        
        // ADD ITEMS TO ROOMS
        lobby.addItem(key);
        infirmary.addItem(book);
        pharmacy.addItem(plant);
        westWing.addItem(rock);
        radiology.addItem(chair);
        surgicalWard.addItem(laptop);
        emergencyRoom.addItem(pie);
        stairwell.addItem(d6);
        basement.addItem(d20);
        
        currentRoom = infirmary;
    }

    /**
     * Main play routine.  Loops until end of play.
     */
    public void play() {
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome() {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("Type 'help' if you need help.");
        System.out.println("Type 'about' to find out about the creators of this game.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     *
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) {
        boolean wantToQuit = false;

        if (command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        switch (commandWord) {
            case "help":
                // help command
                printHelp();
                break;

            case "go":

                // go command
                goRoom(command);
                break;

            case "quit":
                // quit command
                wantToQuit = quit(command);
                break;

            case "about":
                // about the creators
                System.out.println("This game was made by three CS students attending Hanzehogeschool Groningen");
                break;

            case "look":
                // look around room, return items
                doLook();
                break;

            case "take":
                // check if there's a third word, concat with second word if third word is present,
                // try to put player's input in inventory
                if (command.getThirdWord() != null) {
                    String conCommand = command.getSecondWord().concat(" " + command.getThirdWord());
                    doTake(conCommand);
                } else {
                    doTake(command.getSecondWord());
                }
                break;

            case "toss":
                // check if there's a third word first, concat with second word if third word is present,
                // toss player's input from inventory
                if (command.getThirdWord() != null) {
                    String conCommand = command.getSecondWord().concat(" " + command.getThirdWord());
                    doToss(conCommand);
                } else {
                    doToss(command.getSecondWord());
                }
                break;

            case "inventory":
                // check player inventory
                doInventory();
                break;

            case "back":
                // go back room from where player came
                doBack();
                break;

            case "use":
                // use item in inventory
                if (command.getThirdWord() != null) {
                    String conCommand = command.getSecondWord().concat(" " + command.getThirdWord());
                    doUse(conCommand);
                } else {
                    doUse(command.getSecondWord());
                }
                break;

        }
        return wantToQuit;
    }

// implementations of user commands:

    /**
     * go back one room
     */
    private void doBack() {
        if (room.size() > 0) {
            currentRoom = room.pop();
            System.out.println(currentRoom.getLongDescription());
        } else {
            System.out.println("You find yourself where you started.");
        }
    }

    /**
     * see all items in currentRoom that can be picked
     */
    private void doLook() {
        // get list of room items
        List<Item> items = currentRoom.getItemsInRoom();

        // message if no items are found
        if (items.size() == 0) {
            System.out.println("You look around. You don't see anything of use.");
        } else {
            // if items are found, return name and description of items
            System.out.println("You look around.");
            for (int i = 0; i < currentRoom.getItemsInRoom().size(); i++) {
                System.out.println("You find a " + items.get(i).getName() + ". Description: " + items.get(i).getDescription());
            }
        }
    }

    /**
     * look at inventory get name + description + weight
     */
    private void doInventory() {
        // check if inventory size bigger than 0
        if (player.getInventory().size() > 0) {
            // for every item in inventory, get & return item name, description and weight
            for (int i = 0; i < player.getInventory().size(); i++) {
                Item item = player.getInventory().get(i);

                String itemName = item.getName();
                String itemDescription = item.getDescription();
                int itemWeight = item.getWeight();

                System.out.println("Name: " + itemName + ", Description: " + itemDescription + ", Weight: " + itemWeight);
            }
            // print this line if inventory size == 0
        } else {
            System.out.println("You have nothing in your inventory.");
        }
    }

    /**
     * check if item is in room
     * if item is in room, remove item from room and add to inventory
     */
    private void doTake(String itemNameToAdd) {
        // itemNameToAdd is null, prompt player for second word
        if (itemNameToAdd == null) {
            System.out.println("Take what?");
        } else {

            // go through item list in room, check if item is there
            boolean isAdded = false;
            for (int i = 0; i < currentRoom.getItemsInRoom().size(); i++) {
                Item item = currentRoom.getItemsInRoom().get(i);

                // compare player input with item names in list
                if (item.getName().equals(itemNameToAdd)) {
                    isAdded = player.addToInventory(item);

                    // let player know item is added, remove item from room list
                    if (isAdded) {
                        currentRoom.getItemsInRoom().remove(item);
                        System.out.println("You now have a " + item.getName());
                    }
                    // if item is found, so no need for the rest of this method
                    isAdded = true;
                    break;
                }
            }
            // item isn't found
            if (!isAdded) {
                System.out.println("You search " + currentRoom.getShortDescription() +
                        " thoroughly, but you can't find a " + itemNameToAdd);
            }
        }
    }

    /**
     * check if item is in inventory
     * if in inventory, remove from inventory and add in room
     */
    private void doToss(String itemNameToRemove) {
        // itemNameToRemove is null, prompt player for second word
        if (itemNameToRemove == null) {
            System.out.println("Toss what?");
        } else {

            // loop to look search player inventory
            boolean isRemoved = false;
            for (int i = 0; i < player.getInventory().size(); i++) {
                Item itemInInventory = player.getInventory().get(i);

                // if item is found, remove from player inventory, add to room item list
                if (itemInInventory.getName().equals(itemNameToRemove)) {
                    isRemoved = true;
                    player.removeFromInventory(itemInInventory);
                    currentRoom.getItemsInRoom().add(itemInInventory);

                    // inform player of dropped item
                    System.out.println("You dropped the " + itemInInventory.getName() + " at " + currentRoom.getShortDescription());
                }
            }
            // item not present in player inventory
            if (!isRemoved) {
                System.out.println("You don't have " + itemNameToRemove);
            }
        }
    }

    /**
     * first checks if player has anything in inventory
     * if inventory size > 0, checks if item input == item in inventory
     * then checks if item is in inventory, if not, message to player
     * then checks Item bool isKey
     * if bool isKey == true then look around to see if any rooms are locked
     * if found a locked door, unlock the door, and remove key from inventory, message to player
     * if no locked door is found, message to player
     * then check Item book isUsable
     * if bool isUsable == true, use item and remove from inventory, message to player
     *
     * @param itemNameToUse item input
     */
    private void doUse(String itemNameToUse) {
        if (itemNameToUse == null) {
            System.out.println("Use what?");
        } else {
            boolean isFound = false;    // bool to avoid a line print later in this method if item is found in inventory

            // if no items in inventory, no use for this method.
            if (player.getInventory().size() == 0) {
                System.out.println("You have nothing to use!");

            } else {
                // loop to find item in player inventory
                for (int i = 0; i < player.getInventory().size(); i++) {
                    Item itemInInventory = player.getInventory().get(i);

                    // check if name of item == item names in inventory
                    if (itemInInventory.getName().equals(itemNameToUse)) {
                        isFound = true;

                        // if item has isKey true, it's a key and can therefore unlock. let's not make it more complicated
                        // than necessary pl0x
                        if (itemInInventory.isKey()) {
                            // boolean to prevent multiple messages
                            boolean keyUnused = false;
                            // unlock any locked door, remove key from inventory, message player, end method
                            for (Room n : currentRoom.getExits().values()) {
                                if (n.isLocked()) {

                                    n.setLocked(false);
                                    player.removeFromInventory(itemInInventory);
                                    System.out.println("You used the " + itemNameToUse + " on the " + n.getName() + " door.");
                                    break;

                                } else {
                                    // no key usage was found, set bool to true
                                    keyUnused = true;
                                }
                            }
                            if (keyUnused){
                                // no locked doors found, message player
                                System.out.println("No need to use the " + itemNameToUse + "!");
                            }
                            // check for isUsable bool, if true, remove item, message player
                        } else if (itemInInventory.isUsable()) {

                            itemInInventory.turnIntoKey();
                            System.out.println("You used " + itemNameToUse);
                            System.out.println("It's a key!");
                        } else {
                            // isUsable bool != true, message player
                            System.out.println("You can't use this item.");
                        }
                    }
                }
                // if item wasn't found in inventory, message player.
                if (!isFound) {
                    System.out.println("You don't have a " + itemNameToUse + ".");
                }
            }
        }
    }

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the
     * command words.
     */
    private void printHelp() {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /**
     * Try to in to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) {
        if (!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }
        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        } else if (nextRoom.isLocked()) {
            System.out.println("The door is locked!");
        } else {
            room.push(currentRoom);
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }

    /**
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     *
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        } else {
            return true;  // signal that we want to quit
        }
    }
}