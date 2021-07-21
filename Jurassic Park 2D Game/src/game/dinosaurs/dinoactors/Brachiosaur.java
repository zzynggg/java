package game.dinosaurs.dinoactors;
import game.dinosaurs.*;
import game.dinosaurs.dinoenums.Age;
import game.dinosaurs.dinoenums.Status;

public class Brachiosaur extends Dinosaur {

    /**
     * Brachiosaur constructor
     * @param name Brachiosaur's name
     * @param gender Gender of Brachiosaur
     */
    public Brachiosaur(String name, String gender) {
        super(name, 'B', 100,160, 70,
                140,40,false,"Brachiosaur", gender,
                60,200,false);
        addCapability(Age.ADULTDINO);
        addCapability(Status.HERBIVORE);
    }




}
