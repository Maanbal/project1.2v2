package src.game;

import java.util.Scanner;

/**
 * Class name: ItemKeyCard
 *
 * Class extends ItemText and implements ItemUsable
 */
public class ItemKeyCard extends ItemKey implements ItemUsable {

    private int keyID;

    public ItemKeyCard(String name, String description, int weight, int keyID ) {
        super(name, description, weight, keyID);
    }

    public int getCode() {
        return keyID;
    }

    public void setCode(int keyID) {
        this.keyID = keyID;
    }

    /**
     * key-card method, ifused at the correct location with the correct code entered it will unlock a door
     * @param player
     * @param room
     */
    @Override
    public boolean onUse(Player player, Room room) {
        System.out.println("The current number is "+this.getCode());//Shows the current number saved by the keycard
        // unlocks any locked door, remove key-card from inventory, message player, end method
        for (Room adjacentRoom : room.getExits().values()) {
            if (adjacentRoom instanceof LockedRoom) {
                LockedRoom lockedRoom = (LockedRoom) adjacentRoom;

                if (lockedRoom.tryUnlock(keyID)) {
                    System.out.println("You used the " + getName() + " on the " + adjacentRoom.getName() + " door.");
                    return true;
                }
            }
        }
        //Gets the input from the user, only accepting an int with a length of 4
        Scanner scanner = new Scanner(System.in); //creates a scanner object to read the commandline input
        try {
            int tempcode = scanner.nextInt(); //asks input from user
            if (String.valueOf(tempcode).length() == 4) { //checks if the length of the input is 4 characters
                this.setCode(tempcode); //sets the value of the code to the input of the user
                System.out.print("I changed the numbers on the key-card to " + this.getCode()+"\n");
            }
            else{
                System.out.print("I have to enter 4 numbers\n");}
        }catch(java.util.InputMismatchException e){ //catches and handles the InputMismatchException that happens if a player would enter any other character than a number
            System.out.println("I have to enter 4 numbers");
        }
        return false;
        }
}
