package game.playerBehaviour;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.Behaviour;
import game.vendingmachinestuff.BuyingAction;

import java.util.Scanner;

public class QuitGame implements Behaviour {

    @Override
    public Action getAction(Actor actor, GameMap map) {
        return new QuitGameAction();
    }

}
