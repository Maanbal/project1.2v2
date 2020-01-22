package src.game;

public class ItemTransformer extends ItemText implements ItemUsable {

    private final Item item;

    public ItemTransformer(String name, String description, int weight, String useMessage, Item item) {
        super(name, description, weight, useMessage);
        this.item = item;
    }

    /**
     * Transformer method. Transforms item on usage.
     *
     * @param player
     * @param room
     * @return return if item needs to be removed from inventory
     */
    @Override
    public boolean onUse(Player player, Room room) {
        super.onUse(player, room);
        player.removeFromInventory(this);
        player.addToInventory(item);
        return false;
    }
}
