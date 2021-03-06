package src.game;

/**
 * This class extends from ItemText and is called when an item needs to disappear after being used
 *
 * @author Merel Foekens Ramon Oosterloo
 * @version final
 */

public class ItemConsumable extends ItemText implements ItemUsable {
    private final String useMessage;

    public ItemConsumable(String name, String description, int weight, String useMessage) {
        super(name, description, weight, useMessage);
        this.useMessage = useMessage;
    }

    /**
     * Deletes item after using it.
     *
     * @param player
     * @param room
     * @return return if item needs to be removed from inventory
     */
    @Override
    public boolean onUse(Player player, Room room) {
        System.out.println(useMessage);
        return true;
    }
}