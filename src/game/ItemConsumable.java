package src.game;

public class ItemConsumable extends ItemText implements ItemUsable {
    private final String useMessage;

    public ItemConsumable(String name, String description, int weight, String useMessage) {
        super(name, description, weight, useMessage);
        this.useMessage = useMessage;
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
        System.out.println(useMessage);
        return true;
    }
}