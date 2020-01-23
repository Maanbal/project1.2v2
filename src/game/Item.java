package src.game;

/**
 * Class Item
 * The Item class holds all properties we need to know about an item.
 *
 * @author Merel Foekens
 * @version final
 */

public class Item {
    private String name;
    private String description;
    private int weight;
    private boolean pickUp;


    /**
     * @param name name of the item
     * @param description description of item
     * @param weight item weight
     * @param pickUp if item can be picked up or niet
     */
    public Item(String name, String description, int weight, boolean pickUp) {
        this.name = name;
        this.description = description;
        this.weight = weight;
        this.pickUp = pickUp;
    }

    /**
     * below simple getters and setters.
     */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public boolean isPickUp() {
        return pickUp;
    }

    public void setPickUp(boolean pickUp) {
        this.pickUp = pickUp;
    }

}
