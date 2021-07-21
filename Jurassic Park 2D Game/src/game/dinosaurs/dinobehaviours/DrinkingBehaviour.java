package game.dinosaurs.dinobehaviours;

import edu.monash.fit2099.engine.*;
import game.Behaviour;
import game.dinosaurs.dinoactions.DrinkingAction;
import game.dinosaurs.dinoenums.Status;
import game.items.FoodType;

public class DrinkingBehaviour implements Behaviour {

    @Override
    public Action getAction(Actor actor, GameMap map) {
        // only pterodactyls can drink water on lake
        if (actor.hasCapability(Status.THIRSTY)) {
            Ground lake = map.locationOf(actor).getGround();
            if ((lake).getDisplayChar() == '~') {
                if ((lake).hasCapability(FoodType.WATER)) {
                    return new DrinkingAction(actor, map);
                }
            }

            // drink water by the lake
            Location here = map.locationOf(actor);
            for (Exit exit : here.getExits()) {
                Location destination = exit.getDestination();
                Ground lakes = destination.getGround();
                if (lakes.hasCapability(FoodType.WATER)) {
                    return new DrinkingAction(actor, map);
                }
            }
        }
        return null;
    }
}
