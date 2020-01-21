package src.game;

public class ItemTransformer extends ItemText implements ItemUsable{

    private final Item item;

    public ItemTransformer(String name, String description, int weight, String useMessage, Item item) {
        super(name, description, weight, useMessage);
        this.item = item;
    }

    @Override
    public boolean onUse(Player player, Room room) {
        super.onUse(player, room);
        player.removeFromInventory(this);
        player.addToInventory(item);
        return false;
    }
}
