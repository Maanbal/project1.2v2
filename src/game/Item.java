package src.game;

public class Item {
    private String name;
    private String description;
    private int weight;
    private boolean pickUp;

    public Item(String name, String description, int weight, boolean pickUp) {
        this.name = name;
        this.description = description;
        this.weight = weight;
        this.pickUp = pickUp;
    }

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
