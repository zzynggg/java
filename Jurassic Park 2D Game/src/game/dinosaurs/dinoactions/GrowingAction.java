package game.dinosaurs.dinoactions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.dinosaurs.Dinosaur;

public class GrowingAction extends Action {
    /**
     * particular baby dino
     */
    private Actor dino;

    /**
     * Constructor for baby dino to grow
     * @param newDino actor to grow
     */
    public GrowingAction(Actor newDino) {
        dino = newDino;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        Location adultDinoLocation = map.locationOf(actor);
        map.removeActor(actor);
        adultDinoLocation.addActor(dino);
        return menuDescription(actor) + adultDinoLocation.x() +", "+adultDinoLocation.y() + ") is growing up to adult";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Baby "+ ((Dinosaur)dino).getDinoSpecies() + " at (";
    }
}
