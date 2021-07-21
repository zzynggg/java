package game.dinosaurs.dinoactors;

import edu.monash.fit2099.engine.*;
import game.dinosaurs.Dinosaur;
import game.dinosaurs.dinoactions.GrowingAction;
import game.dinosaurs.dinoenums.Age;
import game.dinosaurs.dinoenums.CanFly;
import game.dinosaurs.dinoenums.Status;

public class BabyPtero extends Dinosaur {
    private String gender;
    private String dinoSpecies;
    private int age;
    private int fatigue;

    /**
     * Baby Pterodactyl Constructor
     * @param name Baby Pterodactyl's name
     * @param dinoSpecies baby Pterodactyl's species
     * @param gender Baby Pterodactyl's gender
     */
    public BabyPtero(String name, String dinoSpecies, String gender) {
        super(name, 'p', 10,50,-100
                ,25,10,false, dinoSpecies, gender,
                20,50,true);
        this.dinoSpecies = dinoSpecies;
        this.gender = gender;
        this.fatigue = 0;
        addCapability(Age.BABYDINO);
        addCapability(Status.CARNIVORE);
        addCapability(CanFly.FlY);
    }

    /**
     * to determine the baby Pterodactyl is ready to become adult or not
     * @return Action that the baby Pterodactyl can react
     */
    public Action growUp() {
        age++;
        if (age > 30) {
            Actor ptero = new Pterodactyl("Adult "+ dinoSpecies, gender);
            return new GrowingAction(ptero);    // add adult
        }
        return null;
    }



    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        if (isFlying()){
            fatigue++;
        }
        if (fatigue > 30){
            setFlying(false);
            System.out.println("Baby " + dinoSpecies + "too tired to fly");
            removeCapability(CanFly.FlY);
            addCapability(CanFly.CANT_FLY);

        }

        Action wander = growUp();
        if (wander != null) {
            return wander;
        }
        return super.playTurn(actions, lastAction, map, display);
    }

}
