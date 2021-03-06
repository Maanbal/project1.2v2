package src.game;

import java.util.ArrayList;
import java.util.List;

/**
 * Class name: Player
 *
 * Class holds information about the player, or in this case, player inventory.
 *
 * @author Merel Foekens
 * @version final
 */
public class Player {
    private static int MAX_INVENTORY_WEIGHT = 5;
    private int currentInventoryWeight = 0;
    private List<Item> inventory;

    /**
     * Constructor method. Only initialises ArrayList in which we can put an Item Object.
     */
    public Player() {
        this.inventory = new ArrayList<>();
    }

    /**
     * Getter
     *
     * @return ArrayList inventory
     */
    public List<Item> getInventory() {
        return inventory;
    }

    /**
     * Setter
     *
     * @param inventory ArrayList inventory
     */
    public void setInventory(ArrayList<Item> inventory) {
        this.inventory = inventory;
    }

    /**
     * Checks item is not over weight limit and if it can be picked up. If not too heavy and can be picked up,
     * item is added to player's inventory.
     *
     * @param item item that should be added
     * @return boolean so that if item was searched & found but couldn't be added because of isPickUp/weight flags,
     * it won't be added to inventory.
     */
    public boolean addToInventory(Item item) {
        // check bool pickUp
        if (!item.isPickUp()) {
            System.out.println("This " + item.getName() + " seems to be stuck in place...");
            return false;
            // check weight limits
        } else if ((item.getWeight() + currentInventoryWeight) > MAX_INVENTORY_WEIGHT) {
            System.out.println("Argh! This " + item.getName() + " is too heavy! You can't carry this!");
            return false;
            // add item if pickUp == true and weight total weight is not >5
        } else {
            inventory.add(item);
            currentInventoryWeight += item.getWeight();
        }
        return true;
    }

    /**
     * removes item from inventory and updates currentInventoryWeight
     *
     * @param item item to be removed
     */
    public void removeFromInventory(Item item) {
        inventory.remove(item);
        currentInventoryWeight -= item.getWeight();
    }
    
    public void carryAll(){
        MAX_INVENTORY_WEIGHT = 1000;
    }
}
