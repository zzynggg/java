package game.dinosaurs.dinobehaviours;

import edu.monash.fit2099.engine.*;
import game.Behaviour;
import game.dinosaurs.Dinosaur;
import game.dinosaurs.dinoactions.EatingAction;
import game.items.FoodType;
import game.dinosaurs.dinoenums.Status;
import game.mapstuff.Tree;
import game.mapstuff.TreeInterface;

public class EatingBehaviour implements Behaviour, TreeInterface {
    @Override
    public Action getAction(Actor actor, GameMap map) {
        if (actor.hasCapability(Status.HERBIVORE)) {
            for (int i = 0; i < map.locationOf(actor).getItems().size(); i++) {
                if (((Dinosaur) actor).getDinoSpecies().equals("Brachiosaur")) {
                    if (map.locationOf(actor).getItems().get(i).hasCapability(FoodType.TREEFRUIT)
                    && actor.hasCapability(Status.HUNGRY)) {
                        return new EatingAction(((Tree)tree).getOnTreeFruit());
                    }
                } else if (((Dinosaur) actor).getDinoSpecies().equals("Stegosaur")) {
                    if (map.locationOf(actor).getItems().get(i).toString().equals("Fruit")
                            && actor.hasCapability(Status.HUNGRY)){
                        return new EatingAction(map.locationOf(actor).getItems().get(i));
                    }
                }
            }
        } else if (actor.hasCapability(Status.CARNIVORE)) {
            for (int i = 0; i < map.locationOf(actor).getItems().size(); i++) {
                // for allosaur
                if (map.locationOf(actor).getItems().get(i).hasCapability(FoodType.CARNIVORE)
                        && actor.hasCapability(Status.HUNGRY)) {
                    return new EatingAction(map.locationOf(actor).getItems().get(i));
                }
            }
        }
        return null;
    }
}
