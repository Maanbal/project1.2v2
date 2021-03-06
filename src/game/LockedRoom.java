package src.game;

/**
 * Class name: LockedRoom
 *
 * Extends to room. If a room is initialised as a LockedRoom, it will be locked. Player will need a key to unlock.
 * a LockedRoom has a lockID. To open this room, keyID must match.
 *
 * @author Merel Foekens
 * @version final
 */
public class LockedRoom extends Room {

    private final int lockID;
    private boolean isLocked = true;

    public LockedRoom(String name, String description, int lockID) {
        super(name, description);

        this.lockID = lockID;
    }

    /**
     * Getter to see if door is locked or not.
     * @return if door is locked
     */
    public boolean isLocked() {
        return isLocked;
    }

    /**
     * Try to unlock a locked door with key.
     *
     * @param keyID
     * @return return if door was unlocked
     */
    public boolean tryUnlock(int keyID) {
        if (keyID == lockID) {
            if (isLocked) {
                isLocked = false;
            } else {
                System.out.println("Door was already unlocked!");
            }
            return true;
        } else {
            return false;
        }
    }
}
