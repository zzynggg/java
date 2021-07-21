package game.dinosaurs.dinobehaviours;

import edu.monash.fit2099.engine.*;
import game.Behaviour;
import game.FollowBehaviour;
import game.dinosaurs.Dinosaur;
import game.dinosaurs.dinoactions.MateAction;

public class MateBehaviour implements Behaviour {

    @Override
    public Action getAction(Actor actor, GameMap map) {
        Location here = map.locationOf(actor);
        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();
            if (destination.containsAnActor()) {
                Actor oppGender = destination.getActor();
                // prevent dino mate with player
                if (oppGender.getDisplayChar() != '@'
                        && ((Dinosaur) oppGender).abilityToMate(actor, oppGender)) {
                    return new MateAction(oppGender);
                }
            }
        }

        for (Exit exitA : here.getExits()) {
            for (Exit exitB : exitA.getDestination().getExits()) {
                Location targetDestination = exitB.getDestination();
                if (targetDestination.containsAnActor()) {
                    Actor target = targetDestination.getActor();
                    if (((Dinosaur) actor).abilityToMate(actor, target)) {
                        if (new FollowBehaviour(targetDestination.getActor()).getAction(actor, map) != null) {
                            return new FollowBehaviour(targetDestination.getActor()).getAction(actor, map);
                        }
                    }
                }
            }
        }

        return null;
    }
}
