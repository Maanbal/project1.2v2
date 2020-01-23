package src.game;

// import java.util.ArrayList;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;
// import java.util.Date;
// TODO keep track of time played

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
    private Room currentRoom;       // Knows which room the player is in currently
    private Stack<Room> roomStack;  // Keeps track of the player's movement
    private Set<Room> roomSet;      // Knows which rooms the player has been in
    private Player player;

    /**
     * Create the game and initialise its internal map.
     */
    public Game() {
        createRooms();
        parser = new Parser();
        roomStack = new Stack<>();
        roomSet = new HashSet<>();
        player = new Player();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms() {
        GenerateCode randomcode = new GenerateCode(); // creates the password for the pharmacy door
        // Declare all Items
        Item plant = new Item("plant", "It's withering.", 2, false);
        Item flower = new Item("pretty flower", "You spot that it's a rose!", 1, true);
        Item body = new Item("body bag", "The smell coming from this thing is awful, better not touch it.", 76, true);
        Item brick = new Item("brick", "It's been broken off from somewhere.", 4, true);
        Item painting = new Item("painting", "It's pretty solemn, but you're no art critic.", 7, false);
        Item scalpel = new Item("bloody scalpel", "You rather not touch it.", 1, true);
        Item couch = new Item("leather couch", "Looks comfy enough.", 12, false);
        Item lamp = new Item("lamp", "It's turned off.", 2, true);
        Item fridge = new Item("fridge", "It has a huge lock on it. It probably holds medication of sorts.", 400, false);
        Item toiletPaper = new Item("toiletpaper", "You don't need to go to the bathroom though.", 1, true);
        Item coatRack = new Item("coat rack", "It has a single coat on it, but it looks like moths have been enjoying it.", 20, false);

        //cafetaria
        Item vendingmachine = new Item("vending machine", "Every single item is sold out", 300, false);
        Item sandwich = new ItemText("sandwich", "It doesn't look that fresh anymore", 3, "You take a bite and notice that you now have a cockroach hanging half out of your mouth, extra proteins for you!");
        Item soup = new Item("\"soup\" pan ", "Can you even call this soup anymore?", 20, false);

        //east wing
        Item keypad = new Item("keypad", "It looks like I need some sort of card to unlock this door", 1, false);
        Item wheelchair = new Item("wheelchair", "Sadly there is nobody around to push you around with it", 50, false);
        Item poster = new Item("poster", "A poster with a random saying on it: \"Time waits for no one\"", 1, false);

        //infirmary
        Item digitalclock = new ItemText("digital clock", "It looks like the time isn't displayed correctly", 1, "it shows the time as " + randomcode.getRandomCode() + ", weird");
        Item bed = new Item("bed", "At the foot end of the bed is a plate with a number on it: number 33", 300, false);
        Item stuffed_unicorn = new Item("fluffy unicorn", "It's so fluffy I'm gonna die!", 2, true);

        //pharmacy
        Item sleeping_pill = new ItemText("sleeping-pill bottle", "Sleep is the last thing I am thinking about in my current situation", 2, "You're starting to feel a bit drowsy, \nzzzzzzzzzzzzzzzzzzzzzzzzzz\nYou wake up an unknown amount of time later");
        Item westWingKey = new ItemKey("shiny key", "It has a keychain with a compass rose pointing to the west", 1, 5);

        //nurses station
        Item mirror = new Item("mirror", "Why do I have the feeling that I'm being watched?", 1, false);
        Item keyCard = new ItemKeyCard("key-card", "A key-card with 4 customizable numbers", 1, "which 4 numbers should I enter?");

        // ItemKey items
        // hidden keys
        Item roofKey = new ItemKey("silver key", "Where can you use it?", 1, 2);
        Item operatingKey = new ItemKey("bloody key", "Where can you use it? It's really gross.", 1, 4);

        // non-hidden keys
        Item stairwellKey = new ItemKey("brass key", "Where can you use it?", 1, 1);
        Item mortuaryKey = new ItemKey("red key", "Where can you use it? Who even makes their key red?", 1, 3);
        Item largeKey = new ItemKey("large key", "It looks pretty impressive. Where can you use it?", 1, 5);

        // ItemDie items
        Item d6 = new ItemDie("d6", "A six sided die. It seems kinda useless, but a fun item to play around with.", 1, 6);
        Item d20 = new ItemDie("d20", "A twenty sided die. Someone here must be a D&D fan. " +
                "It seems kinda useless, but a fun item to play around with.", 1, 20);
        Item d8 = new ItemDie("d8", "An eight sided die. It seems kinda useless, but a fun item to play around with.", 1, 8);

        // ItemText items
        Item laptop = new ItemText("laptop", "It looks broken", 4, "You try to boot the computer, " +
                "but you just get a blue screen...");
        Item note = new ItemText("note", "It's scrumpled up.", 1,
                "Log 4. \n Patient 33 keeps forgetting everything we tell them. " +
                        "\nIt's like they have Alzheimer's, except our tests conclude " +
                        "\nthat Alzheimer's isn't the case. This is a tough case for sure.");
        Item chair = new ItemText("chair", "The folding kind.", 3,
                "You put down the chair and sit on it." +
                        "\n..." +
                        "\n..." +
                        "\n..." +
                        "\nYou get bored after a while, so you stand up and pick up the chair again.");
        Item xRay = new ItemText("x-ray", "Whose x-ray is it?", 1,
                "The top of the x-ray it says 'Patient 33'." +
                        "\nThe x-ray itself seems to be of someone's skull." +
                        "\nYou don't possess the medical knowledge to deduce what's wrong with this x-ray.");
        Item clue = new ItemText("torn note", "Despite it being torn, you can still read most of it", 1,
                "... don't know what's wrong with patient 3..." +
                        "\n... it's clear something special happened to..." +
                        "\n... must continue experimenting on..." +
                        "\n... you can't read further.");
        Item sheet = new ItemText("white sheet", "The only sheets around here that don't look dirty.", 2,
                "You pull the sheets over yourself." +
                        "\n'BOOOOOO' you try to say as scary as you can." +
                        "\nyou realise you look and sound ridiculous, and you're embarrassed despite being alone." +
                        "\nyou take off the sheets and put them back in your inventory.");
        Item newspaper = new ItemText("newspaper", "It seems to be pretty old.", 1,
                "The headline reads:" +
                        "\n'STUDENTS MAKE NEW TEXT BASED VIDEO GAME, IT'S AMAZING!'" +
                        "\n... you find that hard to believe.");
        Item hat = new ItemText("hat", "It's a fedora. It's not very fashionable nowadays.", 2,
                "You put it on your head, and immediately feel less attractive." +
                        "\nYou take it off, feeling repulsed.");

        // ItemTransformer items
        Item pie = new ItemTransformer("prisoner's pie", "A cartoony cake. Could something be inside?", 3,
                "You eat the cake. It tastes terrible, but you find a silver key!", roofKey);
        Item bottle = new ItemTransformer("bottle", "It's containing a dark, unknown liquid", 2,
                "You smash open the bottle." +
                        "\n..." +
                        "\n..." +
                        "\new... it got on your pants..." +
                        "\nyou don't feel so bad when you find a bloody key in the mess you made!", operatingKey);


        // Declare rooms
        Room lobby, westWing, emergencyFirstAid, operatingRoom, radiology,
                eastWing, cafeteria, pharmacy, infirmary, nurses_station, stairwell, roof, basement, mortuary, outside;

        // Initialise rooms and add items to room.
        // If room has a lockID, the room is initialised as a locked room.
        // to match keys and rooms, set lockID and keyID to the same number.
        // TODO new Room: outside
        // add exit to lobby when final puzzle is solved
        // Win is achieved when stepping into the room

        lobby = new Room("lobby", "in the hospital lobby");
        lobby.addItem(plant);
        lobby.addItem(couch);

        eastWing = new Room("east_wing", "a big hall with colorful walls");
        eastWing.addItem(keypad);
        eastWing.addItem(wheelchair);
        eastWing.addItem(poster);

        cafeteria = new Room("cafeteria", "a lunchroom with eating tables and a kitchen");
        cafeteria.addItem(vendingmachine);
        cafeteria.addItem(sandwich);
        cafeteria.addItem(soup);

        infirmary = new Room("infirmary", "lots of empty hospital beds");
        infirmary.addItem(digitalclock);
        infirmary.addItem(bed);
        infirmary.addItem(stuffed_unicorn);

        pharmacy = new Room("pharmacy", "a drug store, there seem to be some medicine left");
        pharmacy.addItem(sleeping_pill);
        pharmacy.addItem(westWingKey);

        nurses_station = new Room("nurses_station", "nobody to look after the patients here");
        nurses_station.addItem(mirror);
        nurses_station.addItem(keyCard);

        stairwell = new LockedRoom("stairwell", "a solemn looking stairwell. It seems you can go up as well as down", 1);
        stairwell.addItem(brick);
        stairwell.addItem(toiletPaper);

        roof = new LockedRoom("rooftop", "that it's very misty out here, you can't make out much", 2);
        roof.addItem(note);
        roof.addItem(chair);
        roof.addItem(largeKey);

        basement = new Room("basement", "a weak light flickering in a space full of boxes");
        basement.addItem(mortuaryKey);
        basement.addItem(d6);
        basement.addItem(laptop);

        mortuary = new LockedRoom("mortuary", "that it seems empty except for a single body bag", 3);
        mortuary.addItem(d20);
        mortuary.addItem(pie);
        mortuary.addItem(body);
        mortuary.addItem(newspaper);

        // West Wing, rooms and items
        westWing = new Room("west_wing", "the west wing. There's signs to radiology and emergency first aid");
        westWing.addItem(flower);
        westWing.addItem(painting);
        westWing.addItem(coatRack);

        emergencyFirstAid = new Room("emergency_first_aid", "the emergency first aid room. You have a bad feeling about this place");
        emergencyFirstAid.addItem(sheet);
        emergencyFirstAid.addItem(lamp);
        emergencyFirstAid.addItem(fridge);

        operatingRoom = new LockedRoom("operating_theatre", "the operating theatre. It looks meticulously clean", 4);
        operatingRoom.addItem(scalpel);
        operatingRoom.addItem(clue);
        operatingRoom.addItem(hat);
        operatingRoom.addItem(stairwellKey);

        radiology = new Room("radiology", "the radiology department. There are a bunch of x-rays");
        radiology.addItem(bottle);
        radiology.addItem(xRay);
        radiology.addItem(d8);

        outside = new LockedRoom("outside", "that it's a nice day outside. The fresh air makes you feel amazing," +
                "\nlike you have won or something. \n(YOU WON!)", 5);

        // Initialise exits to all rooms
        // Lobby
        lobby.setExit("east", eastWing);
        lobby.setExit("west", westWing);
        lobby.setExit("south", stairwell);
        lobby.setExit("north", outside);

        // East Wing
        eastWing.setExit("west", lobby);
        eastWing.setExit("north", cafeteria);
        eastWing.setExit("east", pharmacy);
        eastWing.setExit("south", nurses_station);

        cafeteria.setExit("south", eastWing);
        pharmacy.setExit("west", eastWing);

        nurses_station.setExit("east", infirmary);
        nurses_station.setExit("north", eastWing);

        infirmary.setExit("west", nurses_station);

        // West Wing
        westWing.setExit("east", lobby);
        westWing.setExit("north", emergencyFirstAid);
        westWing.setExit("south", radiology);

        emergencyFirstAid.setExit("north", operatingRoom);
        emergencyFirstAid.setExit("south", westWing);

        operatingRoom.setExit("south", emergencyFirstAid);

        radiology.setExit("north", westWing);

        // Stairwell
        stairwell.setExit("north", lobby);
        stairwell.setExit("up", roof);
        stairwell.setExit("down", basement);

        roof.setExit("down", stairwell);

        basement.setExit("up", stairwell);
        basement.setExit("south", mortuary);

        mortuary.setExit("north", basement);

        // Set room to start in
        currentRoom = lobby;
    }

    /**
     * Main play routine. Loops until end of play.
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
        System.out.println("You give up on finding the truth about your past. Goodbye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome() {
        System.out.println();
        System.out.println("Welcome to Subject: 0!");
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
                // command for moving to different Rooms
                String second = command.getSecondWord();
                if (second == null) {
                    System.out.println("Go where?");// if the command only says "go", it does nothing
                    break;
                } else {
                    switch (second) {
                        case "back":                // if command says "go back", it does the same as command "back"
                            doBack();
                            break;
                        case "to":                  // if command says "go to ...", player travels to known room
                            if (!command.hasThirdWord()) { // nothing if command has no third word
                                System.out.println("Go to... where");
                            } else {
                                fastTravel(command);
                                break;
                            }
                        default:                    // default "go" command, move to adjacent room
                            goRoom(command);
                            break;
                    }
                }
                break;

            case "quit":
                // quit command
                wantToQuit = quit(command);
                break;

            case "about":
                // about the creators
                System.out.println("This game was made by Merel Foekens and Marnix van den Berg.");
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
        if (roomStack.size() > 0) {
            currentRoom = roomStack.pop();
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
                System.out.println("You find a " + items.get(i).getName() + ". " + items.get(i).getDescription());
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

                System.out.println("You have a(n) " + itemName + ". " + itemDescription + " Weight: " + itemWeight);
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
                    System.out.println("You dropped the " + itemInInventory.getName() + " in/at the " + currentRoom.getName());
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
     * if item implements ItemUsable, the item will be used accordingly, and possibly removed from inventory
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

                        // check if items is usable
                        if (itemInInventory instanceof ItemUsable) {
                            ItemUsable item = (ItemUsable) itemInInventory;
                            boolean isUsed = item.onUse(player, currentRoom);
                            if (isUsed) {
                                player.removeFromInventory(itemInInventory);
                            }
                        } else {
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
        System.out.println("You are lost. You are alone. You wander. What happened?");
        System.out.println("This hospital seems abandoned.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /**
     * General moving method
     * currentRoom is placed in roomSet to remember which rooms you have visited
     * currentRoom tracked in roomStack to remember in which order you have visited the rooms ("back" command)
     */
    private void move(Room nextRoom) {
        roomSet.add(currentRoom);       // You have entered this room
        roomStack.push(currentRoom);    // You have last entered this room
        currentRoom = nextRoom;         // You have moved into the next room
        System.out.println(currentRoom.getLongDescription()); // You are here
    }

    /**
     * Method for fast travelling to Room you have been before
     * roomSet checks if you've visited the Room by comparing third command word to roomNames
     * Prints error message if you have not been in that Room
     */
    private void fastTravel(Command command) {
        String destination = command.getThirdWord();
        // String destination should be the name of the Room the player wants to enter
        for (Room dest : roomSet) {
            // If one of the room names in roomSet is equal to String destination:
            if (dest.getName().equals(destination)) {
                move(dest);     // You move to this room
                return;
            }
        }
        // If the Room is not in the stack, the player hasn't been there yet and can't fast travel to it
        System.out.println("You haven't been there before");
    }

    /**
     * Moving method that uses the second commandWord to determine in which direction you are moving
     * Checks if there is an exit in the direction of secondWord
     * Gives error if player can't move there
     */
    private void goRoom(Command command) {
        String direction = command.getSecondWord();     // the command's second word decides in which direction you move
        Room nextRoom = currentRoom.getExit(direction); // the next room is on that side of the currentRoom

        if (nextRoom == null) { // do nothing if there is no room on that side of currentRoom
            System.out.println("You can't go there.");
        } else if (nextRoom instanceof LockedRoom && ((LockedRoom) nextRoom).isLocked()) {
            System.out.println("The door is locked!");  // check is room is lockable and locked
        } else {
            move(nextRoom);     // move to nextRoom if everything checks out
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