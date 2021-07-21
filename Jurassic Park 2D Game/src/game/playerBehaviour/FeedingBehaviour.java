package game.playerBehaviour;

import edu.monash.fit2099.engine.*;
import game.Behaviour;
import game.dinosaurs.dinoenums.Status;
import game.items.FoodType;

public class FeedingBehaviour implements Behaviour {
    @Override
    public Action getAction(Actor actor, GameMap map) {
        Location here = map.locationOf(actor);
        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();
            if (destination.containsAnActor()) {
                // actor inventory is not empty
                if (!actor.getInventory().isEmpty()) {
                    for (Item item : actor.getInventory()) {
                        Actor targetActor = destination.getActor();
                        // feed veg dino with veg food
                        if ((targetActor.hasCapability(Status.CARNIVORE) && item.hasCapability(FoodType.CARNIVORE))
                                || (targetActor.hasCapability(Status.HERBIVORE) && item.hasCapability(FoodType.HERBIVORE))) {
                            return new FeedingAction(item, targetActor);
                        }
                    }
                }
            }
        }
        return null;
    }
}
