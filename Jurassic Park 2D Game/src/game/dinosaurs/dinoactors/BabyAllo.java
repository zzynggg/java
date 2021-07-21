package game.dinosaurs.dinoactors;

import edu.monash.fit2099.engine.*;
import game.dinosaurs.*;
import game.dinosaurs.dinoactions.GrowingAction;
import game.dinosaurs.dinoenums.Age;
import game.dinosaurs.dinoenums.Status;

public class BabyAllo extends Dinosaur {

    private String gender;
    private String dinoSpecies;
    private int age;

    /**
     * Baby Allosaur Constructor
     * @param name Baby Allosaur's name
     * @param dinoSpecies baby Allosaur's species
     * @param gender Baby Allosaur's gender
     */
    public BabyAllo(String name, String dinoSpecies, String gender) {
        super(name, 'a', 20,50,-100,
                30,10,true,dinoSpecies ,gender,
                30,50,false);
        this.dinoSpecies = dinoSpecies;
        this.gender = gender;
        addCapability(Age.BABYDINO);
        addCapability(Status.CARNIVORE);

    }

    /**
     * to determine the baby Allosaur is ready to become adult or not
     * @return Action that the baby Allosaur can react
     */
    public Action growUp() {
        age++;
        if (age > 50) {
            Actor allo = new Allosaur("Adult "+ dinoSpecies, gender);
            return new GrowingAction(allo);
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
