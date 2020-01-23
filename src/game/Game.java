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
     * // Initial method for this project. Used as a test method.
     */
//    /**
//     * Create all the rooms and link their exits together.
//     */
//    private void createRoomsTest() {
//
//        // create item lists
//        List<Item> outsideItems = new ArrayList<>();
//        List<Item> theaterItems = new ArrayList<>();
//        List<Item> pubItems = new ArrayList<>();
//        List<Item> labItems = new ArrayList<>();
//        List<Item> officeItems = new ArrayList<>();
//
//        // create items for the entire game
//
//        Item key = new ItemKey("key", "A brass key", 1, 1);
//        Item keyuggo = new ItemKey("key two", "A bronze key", 1, 2);
//        Item book = new Item("book", "The title reads: 'Dreams of Paradise'", 1, true);
//        Item plant = new Item("plant", "A withering plant", 2, false);
//        Item rock = new Item("rock", "A big rock", 10, true);
//        Item chair = new Item("chair", "A black chair", 3, true);
//        Item laptop = new ItemText("laptop", "It looks broken", 4, "It's just a blue screen...");
//        Item pie = new ItemTransformer("prisoner's pie", "Could something be inside it?", 5,
//                "You eat the pie... You find a key!", key);
//        Item d6 = new ItemDie("d6", "It's a six sided die!", 1, 6);
//        Item d20 = new ItemDie("d20", "It's a twenty sided die!", 1, 20);
//
//        // put items in room list
//
//        outsideItems.add(key);
//        outsideItems.add(keyuggo);
//        outsideItems.add(book);
//        outsideItems.add(plant);
//        outsideItems.add(d6);
//        outsideItems.add(d20);
//        theaterItems.add(rock);
//        theaterItems.add(chair);
//        officeItems.add(laptop);
//        labItems.add(pie);
//
//        Room outside, theater, pub, lab, office;
//
//        // create the rooms
//        // lockID == 0 is an open room, lockID > 0 is a locked room
//        // to make keys and rooms corresponding, make lockID and keyID the same
//
//        outside = new Room("outside", "outside the main entrance of the university");
//        outside.setItemsInRoom(outsideItems);
//        theater = new LockedRoom("theater", "in a lecture theater", 1);
//        theater.setItemsInRoom(theaterItems);
//        pub = new Room("pub", "in the campus pub");
//        pub.setItemsInRoom(pubItems);
//        lab = new Room("lab", "in a computing lab");
//        lab.setItemsInRoom(labItems);
//        office = new Room("office", "in the computing admin office");
//        office.setItemsInRoom(officeItems);
//
//        // initialise room exits
//        outside.setExit("east", theater);
//        outside.setExit("south", lab);
//        outside.setExit("west", pub);
//
//        theater.setExit("west", outside);
//
//        pub.setExit("east", outside);
//
//        lab.setExit("north", outside);
//        lab.setExit("east", office);
//
//        office.setExit("west", lab);
//
//        currentRoom = outside;  // start game outside
//    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms() {
        // list for items

        generateCode randomcode = new generateCode();// creates the password for the pharmacy door
        List<Item> westWingItems = new ArrayList<>();
        List<Item> emergencyFirstAidItems = new ArrayList<>();
        List<Item> operatingRoomItems = new ArrayList<>();
        List<Item> radiologyItems = new ArrayList<>();

        List<Item> eastWingItems = new ArrayList<>();
        List<Item> cafeteriaItems = new ArrayList<>();
        List<Item> pharmacyItems = new ArrayList<>();
        List<Item> infirmaryItems = new ArrayList<>();
        List<Item> nursesRoomItems = new ArrayList<>();
        List<Item> lobbyItems = new ArrayList<>();
        List<Item> stairwellItems = new ArrayList<>();
        List<Item> roofItems = new ArrayList<>();
        List<Item> basementItems = new ArrayList<>();
        List<Item> mortuaryItems = new ArrayList<>();

        // All items for game
        // Items
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
        Item vendingmachine = new Item("vending machine","Every single item is sold out",300,false);
        Item sandwich = new ItemConsumable("sandwich", "It doesn't look that fresh anymore",3, "You take a bite and notice that you now have a cockroach hanging half out of your mouth, extra proteins for you!");
        Item soup = new Item( "\"soup\" pan ", "Can you even call this soup anymore?",20,false);

        //east wing
        Item keypad = new Item("keypad", "It looks like I need some sort of card to unlock this door",1,false);
        Item wheelchair = new Item("wheelchair", "Sadly there is nobody around to push you around with it",50,false);
        Item poster = new Item("poster","A poster with a random saying on it: \"Time waits for no one\"",1,false);

        //infirmary
        Item digitalclock = new ItemText("digital clock","It looks like the time isn't displayed correctly", 1, "it shows the time as "+randomcode.getRandomCode()+", weird");
        Item bed = new Item("bed","At the foot end of the bed is a plate with a number on it: number 33",300,false);
        Item stuffed_unicorn = new Item("fluffy unicorn","It's so fluffy I'm gonna die!",2,true);

        //pharmacy
        Item sleeping_pill = new ItemConsumable("sleeping pill", "Sleep is the last thing I am thinking about in my current situation", 2, "You're starting to feel a bit drowsy, \nzzzzzzzzzzzzzzzzzzzzzzzzzz\nYou wake up an unknown amount of time later");
        Item westWingKey = new ItemKey("shiny key","It has a keychain with a compass rose pointing to the west",1,5);

        //nurses station
        Item mirror = new Item("mirror","Why do I have the feeling that I'm being watched?",1,false);

        // ItemKey items
        // hidden keys
        Item roofKey = new ItemKey("silver key", "Where can you use it?", 1, 2);
        Item operatingKey = new ItemKey("bloody key", "Where can you use it? It's really gross.", 1, 4);

        // non-hidden keys
        Item stairwellKey = new ItemKey("brass key", "Where can you use it?", 1, 1);
        Item mortuaryKey = new ItemKey("red key", "Where can you use it? Who even makes their key red?", 1, 3);

        //ItemKeycard Items
        Item keyCard = new ItemKeyCard("key-card","A key-card with 4 customizable numbers", 1,"which 4 numbers should I enter?");

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
                        "\n It's like they have Alzheimer's, except our tests conclude " +
                        "\n that Alzheimer's isn't the case. This is a tough case for sure.");
        Item chair = new ItemText("chair", "The folding kind.", 3,
                "You put down the chair and sit on it." +
                        "\n ..." +
                        "\n ..." +
                        "\n ..." +
                        "\n You get bored after a while, so you stand up and pick up the chair again.");
        Item xRay = new ItemText("x-ray", "Whose x-ray is it?", 1,
                "The top of the x-ray it says 'Patient 33'." +
                        "\n The x-ray itself seems to be of someone's skull." +
                        "\n You don't possess the medical knowledge to deduce what's wrong with this x-ray.");
        Item clue = new ItemText("torn note", "Despite it being torn, you can still read most of it",1,
                "... don't know what's wrong with patient 3..." +
                        "\n ... it's clear something special happened to..." +
                        "\n ... must continue experimenting on..." +
                        "\n ... you can't read further.");
        Item sheet = new ItemText("white sheet", "The only sheets around here that don't look dirty.", 2,
                "You pull the sheets over yourself." +
                        "\n 'BOOOOOO' you try to say as scary as you can." +
                        "\n you realise you look and sound ridiculous, and you're embarrassed despite being alone." +
                        "\n you take off the sheets and put them back in your inventory.");
        Item newspaper = new ItemText("newspaper", "It seems to be pretty old.", 1,
                "The headline reads:" +
                        "\n'STUDENTS MAKE NEW TEXT BASED VIDEO GAME, IT'S AMAZING!'" +
                        "\n you find that hard to believe.");
        Item hat = new ItemText("hat", "It's a fedora. It's not very fashionable nowadays.", 2,
                "You put it on your head, and immediately feel less attractive." +
                        "\n You take it off, feeling repulsed.");

        // ItemTransformer items
        Item pie = new ItemTransformer("prisoner's pie", "A cartoony cake. Could something be inside?", 3,
                "You eat the cake. It tastes terrible, but you find a silver key!", roofKey);
        Item bottle = new ItemTransformer("bottle", "It's containing a dark, unknown liquid", 2,
                "You smash open the bottle." +
                        "\n ..." +
                        "\n ..." +
                        "\n ew... it got on your pants..." +
                        "\n in the mess you find a bloody key!", operatingKey);

        // Lobby items
        lobbyItems.add(stairwellKey);
        lobbyItems.add(plant);
        lobbyItems.add(couch);

        // West wing items
        westWingItems.add(flower);
        westWingItems.add(painting);
        westWingItems.add(coatRack);

        // First aid items
        emergencyFirstAidItems.add(sheet);
        emergencyFirstAidItems.add(lamp);
        emergencyFirstAidItems.add(fridge);

        // Operating room items
        operatingRoomItems.add(scalpel);
        operatingRoomItems.add(clue);
        operatingRoomItems.add(hat);

        // Radiology items
        radiologyItems.add(bottle);
        radiologyItems.add(xRay);
        radiologyItems.add(d8);

        // East wing items
        eastWingItems.add(keypad);
        eastWingItems.add(wheelchair);
        eastWingItems.add(poster);

        // Cafeteria items
        cafeteriaItems.add(vendingmachine);
        cafeteriaItems.add(sandwich);
        cafeteriaItems.add(soup);

        // Pharmacy items
        pharmacyItems.add(sleeping_pill);
        pharmacyItems.add(westWingKey);

        //nursery room items
        nursesRoomItems.add(mirror);
        nursesRoomItems.add(keyCard);

        // Infirmary items
        infirmaryItems.add(digitalclock);
        infirmaryItems.add(bed);
        infirmaryItems.add(stuffed_unicorn);
        // Basement items
        basementItems.add(mortuaryKey);
        basementItems.add(d6);
        basementItems.add(laptop);

        // Mortuary items
        mortuaryItems.add(d20);
        mortuaryItems.add(pie);
        mortuaryItems.add(body);
        mortuaryItems.add(newspaper);

        // Stairwell items
        stairwellItems.add(brick);
        stairwellItems.add(toiletPaper);

        // Roof items
        roofItems.add(note);
        roofItems.add(chair);

        // Declare rooms
        Room lobby, westWing, emergencyFirstAid, operatingRoom, radiology,
                eastWing, cafeteria, pharmacy, infirmary,nurses_station, stairwell, roof, basement, mortuary;

        // Initialise rooms and add items to room.
        // If room has a lockID, the room is initialised as a locked room.
        // to match keys and rooms, set lockID and keyID to the same number.
        lobby = new Room ("lobby", "in the hospital lobby");
        lobby.setItemsInRoom(lobbyItems);

        stairwell = new LockedRoom("stairwell", "in a solemn looking stairwell. It seems you can go up as well as down", 1);
        stairwell.setItemsInRoom(stairwellItems);

        roof = new LockedRoom("rooftop", "at the rooftop. It's very misty out here, you can't make out much", 2);
        roof.setItemsInRoom(roofItems);

        basement = new Room("basement", "in the basement. A weak light flickers in a basement full of boxes");
        basement.setItemsInRoom(basementItems);

        mortuary = new LockedRoom("mortuary", "in the mortuary. The mortuary seems empty except for a single body bag", 3);
        mortuary.setItemsInRoom(mortuaryItems);

        westWing = new LockedRoom("west wing", "in the west wing. There's signs to radiology and emergency first aid",5);
        westWing.setItemsInRoom(westWingItems);

        emergencyFirstAid = new Room("emergency first aid", " in the emergency first aid room. You have a bad feeling about this place");
        emergencyFirstAid.setItemsInRoom(emergencyFirstAidItems);

        operatingRoom = new LockedRoom("operating theatre", " in the operating theatre. It looks meticulously clean", 4);
        operatingRoom.setItemsInRoom(operatingRoomItems);

        radiology = new Room("radiology", " in radiology. There are a bunch of x-rays");
        radiology.setItemsInRoom(radiologyItems);

        eastWing = new Room("east wing", "in the east wing, the east wing leads to the cafeteria, pharmacy, and the nurses station");
        eastWing.setItemsInRoom(eastWingItems);

        cafeteria = new Room("cafeteria", "in the cafeteria, there are only a few leftovers left.");
        cafeteria.setItemsInRoom(cafeteriaItems);

        pharmacy = new LockedRoom("pharmacy", "in the pharmacy, there are medicines everywhere.",randomcode.getRandomCode());
        pharmacy.setItemsInRoom(pharmacyItems);

        nurses_station = new Room("nurses_station","in the nurses_station, there is nobody to look after the patients here.");
        nurses_station.setItemsInRoom(nursesRoomItems);

        infirmary = new Room("infirmary", "in the infirmary, there are a lot of beds here, but nobody lies on them.");
        infirmary.setItemsInRoom(infirmaryItems);


        // initialise exits
        lobby.setExit("south", stairwell);
        lobby.setExit("west", westWing);
        lobby.setExit("east",eastWing);

        eastWing.setExit("west",lobby);
        eastWing.setExit("north", cafeteria);
        eastWing.setExit("east",pharmacy);
        eastWing.setExit("south", nurses_station);

        pharmacy.setExit("west",eastWing);

        cafeteria.setExit("south",eastWing);

        nurses_station.setExit("east",infirmary);
        nurses_station.setExit("north",eastWing);

        infirmary.setExit("west",nurses_station);

        stairwell.setExit("north", lobby);
        stairwell.setExit("up", roof);
        stairwell.setExit("down", basement);

        roof.setExit("down", stairwell);

        basement.setExit("up", stairwell);
        basement.setExit("south", mortuary);

        mortuary.setExit("north", basement);

        westWing.setExit("east", lobby);
        westWing.setExit("north", emergencyFirstAid);
        westWing.setExit("south", radiology);

        emergencyFirstAid.setExit("north", operatingRoom);
        emergencyFirstAid.setExit("south", westWing);

        operatingRoom.setExit("south", emergencyFirstAid);

        radiology.setExit("north", westWing);

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
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around in a forlorn hospital.");
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
        } else if (nextRoom instanceof LockedRoom && ((LockedRoom)nextRoom).isLocked()) {
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