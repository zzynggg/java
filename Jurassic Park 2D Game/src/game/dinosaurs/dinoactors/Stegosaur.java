package game.dinosaurs.dinoactors;
import game.dinosaurs.*;
import game.dinosaurs.dinoenums.Age;
import game.dinosaurs.dinoenums.Status;

public class Stegosaur extends Dinosaur {

    /**
     * Stegosaur constructor
     * @param name Stegosaur's name
     * @param gender Gender of Stegosaur
     */
    public Stegosaur(String name, String gender) {
        super(name, 'S', 50,100, 50,
                90,40,false,"Stegosaur", gender,
                60,100,false);
        addCapability(Age.ADULTDINO);
        addCapability(Status.HERBIVORE);
    }



}
