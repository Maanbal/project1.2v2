package src.game;

// Using an interface so all classes that implement from ItemUsable must have an onUse method.
// Having an interface saves a huge chunk of code, because from implementing ItemUsable, the compiler knows that all methods
// from this interface are present in classes that implement this interface.

// What this means is that at places where we want to use this method(Game, when they player tries to use an item),
// we don't need to have code for each concrete item class.
// We need to check whether the item implements ItemUsable so we can call the onUse method.
// This also  makes it the responsibility of the item to figure out what needs to happen when it is used,
// instead of in the Game class, which already has a lot of responsibilities.

// check out link below for more information about interfaces.
// https://www.w3schools.com/java/java_interface.asp

public interface ItemUsable {
    // public modifier unnecessary because methods from an interface are always public.
    boolean onUse(Player player, Room room);
}
