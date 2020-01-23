package src.game;

import java.util.Random;

/**
 * Class name: ItemDie
 *
 * A class that extends to Item. This class is used to create dice and get different numbers every time it's used.
 *
 * @author Merel Foekens
 * @version final
 */
public class ItemDie extends Item implements ItemUsable {
    private final int sides;
    private final Random random = new Random();

    public ItemDie(String name, String description, int weight, int sides) {
        super(name, description, weight, true);
        this.sides = sides;
    }

    /**
     * Die method. Generate random number.
     * @param player
     * @param room
     * @return return if item needs to be removed from inventory
     */
    @Override
    public boolean onUse(Player player, Room room) {
        int outcome = random.nextInt(sides) + 1;
        System.out.println("You rolled the die... it's " + outcome);
        return false;
    }
}
