package src.game;

// interface so all usable items have to have an onUse method.
public interface ItemUsable {
    boolean onUse(Player player, Room room);
}
