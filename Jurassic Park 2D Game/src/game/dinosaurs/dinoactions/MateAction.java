package game.dinosaurs.dinoactions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.dinosaurs.Dinosaur;
import game.mapstuff.GroundType;

public class MateAction extends Action {
    /**
     * The opposite gender actor to mate
     */
    private Actor targetOppGender;

    /**
     * Mate Action constructor
     * @param targetOppGender The target dino to mate
     */
    public MateAction(Actor targetOppGender) {
        this.targetOppGender = targetOppGender;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        if (((Dinosaur)actor).getDinoSpecies().contains("Pterodactyl")){
            if(map.locationOf(actor).getGround().hasCapability(GroundType.TREE) && map.locationOf(targetOppGender).getGround().hasCapability(GroundType.TREE)){
                if (((Dinosaur) actor).getDinoGender().equals("Male") && ((Dinosaur) targetOppGender).getDinoGender().equals("Female")) {
                    ((Dinosaur) targetOppGender).setHatching(true);
                    return menuDescription(targetOppGender) + map.locationOf(actor).x() + ", " + map.locationOf(actor).y() + ") mate successfully";
                } else if (((Dinosaur) actor).getDinoGender().equals("Female") && ((Dinosaur) targetOppGender).getDinoGender().equals("Male")) {
                    ((Dinosaur) actor).setHatching(true);
                    return menuDescription(actor)+ map.locationOf(actor).x() + ", " + map.locationOf(actor).y() + ") mate successfully";
                }
            }

        }
        else{
            if (((Dinosaur) actor).getDinoGender().equals("Male") && ((Dinosaur) targetOppGender).getDinoGender().equals("Female")) {
                ((Dinosaur) targetOppGender).setHatching(true);
                return menuDescription(targetOppGender) + map.locationOf(actor).x() + ", " + map.locationOf(actor).y() + ") mate successfully";
            } else if (((Dinosaur) actor).getDinoGender().equals("Female") && ((Dinosaur) targetOppGender).getDinoGender().equals("Male")) {
                ((Dinosaur) actor).setHatching(true);
                return menuDescription(actor)+ map.locationOf(actor).x() + ", " + map.locationOf(actor).y() + ") mate successfully";
            }
        }
        return "Dinosaur failed to mate";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " at (";
    }
}
