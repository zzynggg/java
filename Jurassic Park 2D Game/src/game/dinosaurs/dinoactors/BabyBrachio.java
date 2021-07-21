package game.dinosaurs.dinoactors;

import edu.monash.fit2099.engine.*;
import game.dinosaurs.*;
import game.dinosaurs.dinoactions.GrowingAction;
import game.dinosaurs.dinoenums.Age;
import game.dinosaurs.dinoenums.Status;

public class BabyBrachio extends Dinosaur {

    private String gender;
    private String dinoSpecies;
    private int age;

    /**
     * Baby Brachiosaur Constructor
     * @param name Baby Brachiosaur's name
     * @param dinoSpecies baby Brachiosaur's species
     * @param gender Baby Brachiosaur's gender
     */
    public BabyBrachio(String name, String dinoSpecies, String gender) {
        super(name, 'b', 10,60,-100,
                30,15,false, dinoSpecies, gender,
                50,100,false);
        this.dinoSpecies = dinoSpecies;
        this.gender = gender;
        addCapability(Age.BABYDINO);
        addCapability(Status.HERBIVORE);
    }

    /**
     * to determine the baby Brachiosaur is ready to become adult or not
     * @return Action that the baby Brachiosaur can react
     */
    public Action growUp() {
        age++;
        if (age > 50) {
            Actor brachio = new Brachiosaur("Adult "+ dinoSpecies, gender);
            return new GrowingAction(brachio);
        }
        return null;
    }

    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        Action wander = growUp();
        if (wander != null) {
            return wander;
        }
        return super.playTurn(actions, lastAction, map, display);
    }

}
