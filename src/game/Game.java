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
    private List<Item> outsideItems;
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
     * Create all the rooms and link their exits together.
     */
    private void createRooms() {
        Room outside, theater, pub, lab, office;

        // create the rooms
        outside = new Room("outside the main entrance of the university");
        Item key = new Item("key", "A brass key", 1, true, true);
        Item book = new Item("book", "Dreams of Paradise", 6, true, false);

        outsideItems = new ArrayList<>();
        outsideItems.add(key);
        outsideItems.add(book);

        outside.setItemsInRoom(outsideItems);
        theater = new Room("in a lecture theater");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");

        // initialise room exits
        outside.setExit("east", theater);
        outside.setExit("south", lab);
        outside.setExit("west", pub);

        theater.setExit("west", outside);

        pub.setExit("east", outside);

        lab.setExit("north", outside);
        lab.setExit("east", office);

        office.setExit("west", lab);

        currentRoom = outside;  // start game outside
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
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
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
                printHelp();
                break;

            case "go":
                goRoom(command);
                break;

            case "quit":
                wantToQuit = quit(command);
                break;

            case "about":
                System.out.println("This game was made by three students attending Hanzehogeschool Groningen");
                break;

            case "look":
                // see all items in currentRoom that can be picked
                List<Item> items = currentRoom.getItemsInRoom();
                for (int i = 0; i < currentRoom.getItemsInRoom().size(); i++) {
                    System.out.println("You find a " + items.get(i).getName());
                    System.out.println("Description: " + items.get(i).getDescription());
                }
                break;
            case "take":
                // check if item is in room
                // if item is in room, remove item from room and add to inventory

                // TODO code prints item twice if item isnt present, Merel Fix pls!
                String itemToAdd = command.getSecondWord();
                boolean found = false;
                for (int i = 0; i < currentRoom.getItemsInRoom().size(); i++) {
                    Item item = currentRoom.getItemsInRoom().get(i);
                    if (item.getName().equals(itemToAdd)) {
                        if (player.addToInventory(item)) {
                            player.addToInventory(item);
                            currentRoom.getItemsInRoom().remove(item);
                            System.out.println("You now have a " + item.getName());
                        } else found = true; // needs more Merel fix
                    }
                }
                if (!found){
                    System.out.println("You search " + currentRoom.getShortDescription() +
                            " thoroughly, but you can't find a " + itemToAdd);
            }
                break;

            case "toss":
                // check if item is in inventory
                // if in inventory, remove from inventory and add in room
                String itemToRemove = command.getSecondWord();
                for (int i = 0; i < player.getInventory().size(); i++) {
                    Item itemInInventory = player.getInventory().get(i);
                    if (itemInInventory.getName().equals(itemToRemove)) {
                        player.removeFromInventory(itemInInventory);
                        currentRoom.getItemsInRoom().add(itemInInventory);
                        System.out.println("You dropped the " + itemInInventory.getName() + " at " + currentRoom.getShortDescription());
                    } else System.out.println("You don't have " + itemInInventory.getName());
                }
                break;
            case "inventory":
                // look at inventory get name + description + weight
                for (int i = 1; i < player.getInventory().size(); i++) {
                    Item item = player.getInventory().get(i);
                    String itemName = item.getName();
                    String itemDescription = item.getDescription();
                    int itemWeight = item.getWeight();

                    System.out.println("Name: " + itemName + ", Description: " + itemDescription + ", Weight: " + itemWeight);
                }
                break;
            case "back":
                if (room.size() > 0) {
                    currentRoom = room.pop();
                    System.out.println(currentRoom.getLongDescription());
                } else {
                    System.out.println("You find yourself where you started.");
                }
                break;
        }
        return wantToQuit;
    }

    // implementations of user commands:

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