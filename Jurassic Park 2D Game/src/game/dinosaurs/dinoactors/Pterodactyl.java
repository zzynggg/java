package game.dinosaurs.dinoactors;

import edu.monash.fit2099.engine.*;
import game.dinosaurs.Dinosaur;
import game.dinosaurs.dinoenums.Age;
import game.dinosaurs.dinoenums.CanFly;
import game.dinosaurs.dinoenums.PteroEat;
import game.dinosaurs.dinoenums.Status;

public class Pterodactyl extends Dinosaur {

    private int fatigue;
    private int eating;

    /**
     * Dinosaur constructor
     *
     * @param name               Dinosaur's name
     * @param gender             Dinosaur's gender
     */
    public Pterodactyl(String name , String gender) {
        super(name, 'P', 50,100, 50,
                90,40,false,"Pterodactyl", gender,
                60,100,true);
        fatigue = 0;
        eating = 0;
        super.
        addCapability(Age.ADULTDINO);
        addCapability(Status.CARNIVORE);
        addCapability(CanFly.FlY);
        addCapability(PteroEat.NOT_EATING);
    }

    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        if (isFlying()){
            fatigue++;
        }
        if (fatigue > 30){
            setFlying(false);
            System.out.println("Adult " + getDinoSpecies() + " too tired to fly");
            removeCapability(CanFly.FlY);
            addCapability(CanFly.CANT_FLY);
        }

        return super.playTurn(actions, lastAction, map, display);
    }

    /**
     * returns eating variables
     * @return
     */
    public int getEating() {
        return eating;
    }

    /**
     * sets the eating variable according ot the input parameter
     * @param eating
     */
    public void setEating(int eating) {
        this.eating = eating;
    }

    /**
     * sets the fatigue variable according ot the input parameter
     * @param fatigue
     */
    public void setFatigue(int fatigue) {
        this.fatigue = fatigue;
    }
}
