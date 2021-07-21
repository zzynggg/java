package game.dinosaurs.dinoactors;
import game.Behaviour;
import game.dinosaurs.*;
import game.dinosaurs.dinoenums.Age;
import game.dinosaurs.dinoenums.Status;

import java.util.ArrayList;
import java.util.List;

public class Allosaur extends Dinosaur {

    /**
     * List of actions
     */
    public List<Behaviour> actionFactories = new ArrayList<Behaviour>();

    /**
     * Allosaur constructor
     * @param name Allosaur's name
     * @param gender Gender of Allosaur
     */
    public Allosaur(String name, String gender) {
        super(name, 'A', 50,100, 50,
                90,20,true,"Allosaur", gender,
                60, 100,false);
        addCapability(Age.ADULTDINO);
        addCapability(Status.CARNIVORE);
    }


}
