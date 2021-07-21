package game.playerBehaviour;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.Application;
import game.vendingmachinestuff.EcoPointInterface;

public class QuitGameAction extends Action {
    @Override
    public String execute(Actor actor, GameMap map) {

        map.removeActor(actor);
        return "Player has quit Game";
    }

    @Override
    public String menuDescription(Actor actor) {
        return ("Quit Game");
    }
}
