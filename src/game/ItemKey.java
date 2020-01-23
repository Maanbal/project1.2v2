package src.game;

/**
 * Class name: ItemKey
 *
 * Class extends from Item. This class is used to create keys.
 *
 * @author Merel Foekens
 * @version final
 */
public class ItemKey extends Item implements ItemUsable {
    private int keyID;

    /**
     * @param name check super class
     * @param description check super class
     * @param weight check super class
     * @param keyID ID of the key. Must match lockID
     */
    public ItemKey(String name, String description, int weight, int keyID) {
        super(name, description, weight, true);
        this.keyID = keyID;
    }

    public int getKeyID() {
        return keyID;
    }

    public void setKeyID(int keyID) {
        this.keyID = keyID;
    }

    /**
     * key method. Unlocks door if it can.
     * @param player
     * @param room
     * @return return if item needs to be removed from inventory
     */
    @Override
    public boolean onUse(Player player, Room room) {
        // unlock any locked door, remove key from inventory, message player, end method
        for (Room adjacentRoom : room.getExits().values()) {
            if (adjacentRoom instanceof LockedRoom) {
                LockedRoom lockedRoom = (LockedRoom) adjacentRoom;

                if (lockedRoom.tryUnlock(keyID)) {
                    System.out.println("You used the " + getName() + " on the " + adjacentRoom.getName() + " door.");
                    return true;

                }
            }
        }
        System.out.println("The " + getName() + " doesn't fit!");
        return false;
    }
}


