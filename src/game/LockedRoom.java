package src.game;

public class LockedRoom extends Room {

    private final int lockID;
    private boolean isLocked = true;

    public LockedRoom(String name, String description, int lockID) {
        super(name, description);

        this.lockID = lockID;
    }

    public boolean isLocked() {
        return isLocked;
    }

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
