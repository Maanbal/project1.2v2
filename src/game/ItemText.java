package src.game;

public class ItemText extends Item implements ItemUsable {


    private final String useMessage;

    public ItemText(String name, String description, int weight, String useMessage) {
        super(name, description, weight, true);
        this.useMessage = useMessage;
    }

    @Override
    public boolean onUse(Player player, Room room) {
        System.out.println(useMessage);
        return false;
    }
}
