package game.dinosaurs.dinoactors;

import edu.monash.fit2099.engine.*;
import game.dinosaurs.*;
import game.dinosaurs.dinoactions.GrowingAction;
import game.dinosaurs.dinoenums.Age;
import game.dinosaurs.dinoenums.Status;

public class BabyStego extends Dinosaur {

    private String gender;
    private String dinoSpecies;
    private int age;

    /**
     * Baby Stegosaur Constructor
     * @param name Baby Stegosaur's name
     * @param dinoSpecies baby Stegosaur's species
     * @param gender Baby Stegosaur's gender
     */
    public BabyStego(String name, String dinoSpecies, String gender) {
        super(name, 's', 10,50,-100
                ,25,10,false, dinoSpecies, gender,
                20,50,false);
        this.dinoSpecies = dinoSpecies;
        this.gender = gender;
        addCapability(Age.BABYDINO);
        addCapability(Status.HERBIVORE);
    }

    /**
     * to determine the baby Stegosaur is ready to become adult or not
     * @return Action that the baby Stegosaur can react
     */
    public Action growUp() {
        age++;
        if (age > 30) {
            Actor stego = new Stegosaur("Adult "+ dinoSpecies, gender);
            return new GrowingAction(stego);    // add adult
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
