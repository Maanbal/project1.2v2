package src.game;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private static final int MAX_INVENTORY_WEIGHT = 5;
    private int currentInventoryWeight = 0;
    private List<Item> inventory;

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
     * @param inventory
     */
    public void setInventory(ArrayList<Item> inventory) {
        this.inventory = inventory;
    }

    /**
     * Checks item is not over weight limit and if it can be picked up. If not too heavy and can be picked up,
     * item is added to player's inventory.
     *
     * @param item
     * @return boolean so that if item was searched & found but couldn't be added because of isPickUp/weight flags,
     * it won't be added to inventory.
     */
    public boolean addToInventory(Item item) {
        if (!item.isPickUp()) {
            System.out.println("This " + item.getName() + " seems to be stuck in place...");
            return false;
        } else if ((item.getWeight() + currentInventoryWeight) > MAX_INVENTORY_WEIGHT) {
            System.out.println("Argh! This " + item.getName() + " is too heavy! You can't carry more!");
            return false;
        } else {
            inventory.add(item);
            currentInventoryWeight += item.getWeight();
        }
        return true;
    }

    /**
     * removes item from inventory and updates currentInventoryWeight
     *
     * @param item
     */
    public void removeFromInventory(Item item) {
        inventory.remove(item);
        currentInventoryWeight -= item.getWeight();
    }
}
