package game.playerBehaviour;

import edu.monash.fit2099.engine.*;
import game.Behaviour;

public class SearchingFruitBehaviour implements Behaviour {

    @Override
    public Action getAction(Actor actor, GameMap map) {
        if (map.locationOf(actor).getGround().getDisplayChar() == '+'
                || map.locationOf(actor).getGround().getDisplayChar() == 'u') {
            return new SearchingFruitAction(map.locationOf(actor).getGround().getDisplayChar());
        }
        return null;
    }
}
