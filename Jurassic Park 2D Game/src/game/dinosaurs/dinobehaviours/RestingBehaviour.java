package game.dinosaurs.dinobehaviours;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.Behaviour;
import game.dinosaurs.Dinosaur;
import game.dinosaurs.dinoactions.EatingAction;
import game.dinosaurs.dinoactions.RestingAction;
import game.dinosaurs.dinoenums.CanFly;
import game.dinosaurs.dinoenums.Status;
import game.items.FoodType;
import game.mapstuff.GroundType;
import game.mapstuff.Tree;

public class RestingBehaviour implements Behaviour {
    @Override
    public Action getAction(Actor actor, GameMap map) {
        if (actor.hasCapability(CanFly.CANT_FLY)) {
            if (map.locationOf(actor).getGround().hasCapability(GroundType.TREE)) {
                return new RestingAction(actor,map);
            }
        }
        return null;
    }

}
