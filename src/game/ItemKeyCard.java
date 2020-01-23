package src.game;

import java.util.Scanner;

public class ItemKeyCard extends ItemText implements ItemUsable {

    private int keyID;

    public ItemKeyCard(String name, String description, int weight, String useMessage) {
        super(name, description, weight, useMessage);
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
        super.onUse(player, room);
        System.out.println("The current number is "+this.getCode());
        for (Room adjacentRoom : room.getExits().values()) {
            if (adjacentRoom instanceof LockedRoom) {
                LockedRoom lockedRoom = (LockedRoom) adjacentRoom;

                if (lockedRoom.tryUnlock(keyID)) {
                    System.out.println("You used the " + getName() + " on the " + adjacentRoom.getName() + " door.");
                    return true;
                }
            }
        }

        Scanner scanner = new Scanner(System.in);
        try {
            int tempcode = scanner.nextInt();//vraagt input van gebruiker
            if (String.valueOf(tempcode).length() == 4) {
                this.setCode(tempcode);
                System.out.print("I changed the numbers on the key-card to " + this.getCode()+"\n");
            }
            else{
                System.out.print("I have to enter 4 numbers\n");}
        }catch(java.util.InputMismatchException e){
            System.out.println("I have to enter 4 numbers");
        }
        return false;
        }
}
