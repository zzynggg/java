package game.dinosaurs.dinobehaviours;

import edu.monash.fit2099.engine.*;
import game.Behaviour;
import game.dinosaurs.dinoenums.Status;
import game.items.FoodType;

public class MoveToDrinkBehaviour implements Behaviour {
    @Override
    public Action getAction(Actor actor, GameMap map) {
        if (actor.hasCapability(Status.THIRSTY)) {
            Location here = map.locationOf(actor);
            for (Exit exit : here.getExits()) {
                Location destination = exit.getDestination();
                for (Exit nearByExit : destination.getExits()) {
                    Ground lake = nearByExit.getDestination().getGround();
                    if (lake.hasCapability(FoodType.WATER)
                    && !(nearByExit.getDestination().containsAnActor())){
                        return new MoveActorAction(destination,exit.getName());
                    }
                }
            }
        }
        return null;
    }
}
