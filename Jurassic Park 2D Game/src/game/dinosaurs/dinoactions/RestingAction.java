package game.dinosaurs.dinoactions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import game.dinosaurs.Dinosaur;
import game.dinosaurs.dinoactors.Pterodactyl;
import game.dinosaurs.dinobehaviours.WanderBehaviour;
import game.dinosaurs.dinoenums.CanFly;

public class RestingAction extends Action {
    private Actor actor;
    private GameMap map;

    public RestingAction(Actor actor, GameMap map) {
        this.actor = actor;
        this.map = map;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        ((Dinosaur)actor).setFlying(true);
        ((Dinosaur)actor).removeCapability(CanFly.CANT_FLY);
        ((Dinosaur)actor).addCapability(CanFly.FlY);
        ((Pterodactyl)actor).setFatigue(0);
        return menuDescription(actor)+ map.locationOf(actor).x() + ", " + map.locationOf(actor).y() + ") Rests on Tree";
    }

    @Override
    public Action getNextAction() {
        if (new WanderBehaviour().getAction(actor, map) != null) {
            return new DoNothingAction();
        }

        return null;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " at (";
    }
}
