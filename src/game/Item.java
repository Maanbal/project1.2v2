package src.game;

public class Item {
    private String name;
    private String description;
    private int weight;
    private boolean pickUp;
    private int keyID;
    private boolean isUsable;

    public Item(String name, String description, int weight, boolean pickUp, int keyID, boolean isUsable) {
        this.name = name;
        this.description = description;
        this.weight = weight;
        this.pickUp = pickUp;
        this.keyID = keyID;
        this.isUsable = isUsable;
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

    public int getKeyID() {
        return keyID;
    }

    public void setKey(int key) {
        keyID = key;
    }

    public boolean isUsable() {
        return isUsable;
    }

    public void setUsable(boolean usable) {
        isUsable = usable;
    }

}
