package src.game;

public class ItemKey extends Item implements ItemUsable {
    private int keyID;

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

    @Override
    public boolean onUse(Player player, Room room) {
        // unlock any locked door, remove key from inventory, message player, end method
        for (Room adjacentRoom : room.getExits().values()) {
            if (adjacentRoom.isLocked() == keyID) {

                adjacentRoom.setLocked(0);
                System.out.println("You used the " + getName() + " on the " + adjacentRoom.getName() + " door.");
                return true;

            }
        }
        System.out.println(getName() + " doesn't fit in any locks!");
        return false;
    }
}


