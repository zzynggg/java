package game.dinosaurs.dinobehaviours;

import edu.monash.fit2099.engine.*;
import game.Behaviour;
import game.FollowBehaviour;
import game.dinosaurs.Dinosaur;
import game.dinosaurs.dinoactions.AlloAttackingAction;
import game.dinosaurs.dinoactions.MateAction;

public class AlloAttackingBehaviour implements Behaviour {
    @Override
    public Action getAction(Actor actor, GameMap map) {
        Location here = map.locationOf(actor);
        if(((Dinosaur)actor).isCanAttack()){
            for (Exit exit : here.getExits()) {
                Location destination = exit.getDestination();
                if (destination.containsAnActor()) {
                    Actor target = destination.getActor();
                    // prevent dino mate with player
                    if (target.getDisplayChar() != '@'
                            && ((Dinosaur) target).AbilityToBeAttacked()) {
                        return new AlloAttackingAction(target);
                    }
                }
            }

            for (Exit exitA : here.getExits()) {
                for (Exit exitB : exitA.getDestination().getExits()) {
                    Location targetDestination = exitB.getDestination();
                    if (targetDestination.containsAnActor()) {
                        Actor target = targetDestination.getActor();
                        if (((Dinosaur) actor).isCanAttack() )
                        { if (new FollowBehaviour(targetDestination.getActor()).getAction(actor, map) != null) {
                                return new FollowBehaviour(targetDestination.getActor()).getAction(actor, map);
                            }
                        }
                    }
                }
            }
        }

        return null;
    }
}
