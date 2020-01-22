package src.game;

public class ItemText extends Item implements ItemUsable {


    private final String useMessage;

    public ItemText(String name, String description, int weight, String useMessage) {
        super(name, description, weight, true);
        this.useMessage = useMessage;
    }

    /**
     * Text method. Will send player a message upon usage.
     * @param player
     * @param room
     * @return return if item needs to be removed from inventory
     */
    @Override
    public boolean onUse(Player player, Room room) {
        System.out.println(useMessage);
        return false;
    }
}
