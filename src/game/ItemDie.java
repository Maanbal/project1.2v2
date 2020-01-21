package src.game;

import java.util.Random;

public class ItemDie extends Item implements ItemUsable {
    private final int sides;
    private final Random random = new Random();

    public ItemDie(String name, String description, int weight, int sides) {
        super(name, description, weight, true);
        this.sides = sides;
    }

    @Override
    public boolean onUse(Player player, Room room) {
        int outcome = random.nextInt(sides) + 1;
        System.out.println("You rolled the die... it's " + outcome);
        return false;
    }
}
