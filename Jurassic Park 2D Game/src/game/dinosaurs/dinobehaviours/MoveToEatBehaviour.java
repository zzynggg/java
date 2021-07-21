package game.dinosaurs.dinobehaviours;

import edu.monash.fit2099.engine.*;
import game.Behaviour;
import game.dinosaurs.dinoenums.Status;
import game.items.FoodType;

public class MoveToEatBehaviour implements Behaviour {
    @Override
    public Action getAction(Actor actor, GameMap map) {
        if (actor.hasCapability(Status.HUNGRY)) {
            Location here = map.locationOf(actor);
            for (Exit exit : here.getExits()) {
                Location destination = exit.getDestination();
                if (destination.canActorEnter(actor)
                        && nearByFoodSource(actor, destination)) {
                    if (!destination.containsAnActor()) {
                        return new MoveActorAction(destination, exit.getName());
                    }
                }
            }
        }
        return null;
    }

    /**
     * To search for nearby food source to eat
     * @param actor Hungry Dinosaur that is searching for food
     * @param destinationLocation The target food location
     * @return true if food is found
     */
    public boolean nearByFoodSource(Actor actor, Location destinationLocation) {
        for (Item foodSource : destinationLocation.getItems()) {
            if ((foodSource.hasCapability(FoodType.CARNIVORE) && actor.hasCapability(Status.CARNIVORE))
                    || (foodSource.hasCapability(FoodType.HERBIVORE) && actor.hasCapability(Status.HERBIVORE)) ||
                    (foodSource.hasCapability(FoodType.WATER) && actor.hasCapability(Status.CARNIVORE))) {
                return true;
            }
        }
        return false;
    }
}
