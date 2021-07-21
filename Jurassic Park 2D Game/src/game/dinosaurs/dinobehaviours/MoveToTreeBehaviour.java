package game.dinosaurs.dinobehaviours;

import edu.monash.fit2099.engine.*;
import game.Behaviour;
import game.dinosaurs.dinoenums.CanFly;
import game.dinosaurs.dinoenums.Status;
import game.items.FoodType;
import game.mapstuff.GroundType;
import game.mapstuff.Tree;

public class MoveToTreeBehaviour implements Behaviour {

    @Override
    public Action getAction(Actor actor, GameMap map) {
        if (actor.hasCapability(CanFly.CANT_FLY)) {
            Location here = map.locationOf(actor);
            for (Exit exit : here.getExits()) {
                Location destination = exit.getDestination();
                if (destination.canActorEnter(actor)
                        && nearByRestArea(actor, destination)) {
                    if(map.locationOf(actor).getGround().hasCapability(GroundType.TREE)){
                        return new RestingBehaviour().getAction(actor,map);
                    }
                    else if (!destination.containsAnActor()) {
                        return new MoveActorAction(destination, exit.getName());
                    }
                }
            }
        }
        return null;
    }

    /**
     * To search for a place to rest
     * @param actor Tired flying dinosaur looks for place to rest
     * @param destinationLocation The target food location
     * @return true if a tree is found
     */
    public boolean nearByRestArea(Actor actor, Location destinationLocation) {
        Ground ground = destinationLocation.getGround();
        if (ground.hasCapability(GroundType.TREE) && actor.hasCapability(CanFly.CANT_FLY)){
                return true;
            }
        return false;
    }
}
