package game.items;

import edu.monash.fit2099.engine.Location;
import game.ProbabilityInterface;
import game.dinosaurs.dinoactors.*;
import game.mapstuff.Dirt;
import game.vendingmachinestuff.EcoPointInterface;

public class Egg extends PortableItem implements EcoPointInterface, ProbabilityInterface {

    private int timeToHatch;
    private String dinoEggSpecies;

    /**
     * Egg constructor
     * @param dinoEggSpecies Dinosaur's egg species
     */
    public Egg(String dinoEggSpecies) {
        super("Dinosaur Egg", 'E');
        this.dinoEggSpecies = dinoEggSpecies;
        addCapability(FoodType.CARNIVORE);
    }

    /**
     * To get the dinosaur egg species
     * @return egg species
     */
    public String getDinoEggSpecies() {
        return dinoEggSpecies;
    }

    @Override
    public void tick(Location currentLocation) {
        super.tick(currentLocation);
        boolean hatched = false;
        timeToHatch++;
        // gender reveal
        if (timeToHatch == 20 && dinoEggSpecies.equals("Stegosaur")) {
            ecoPoints.incEcoPoint(100);
            hatched = true;
            if (probability.getChances(50)) {
                currentLocation.addActor(new BabyStego("Baby " + dinoEggSpecies, dinoEggSpecies, "Female"));
            } else {
                currentLocation.addActor(new BabyStego("Baby " + dinoEggSpecies, dinoEggSpecies, "Male"));
            }
        } else if (timeToHatch == 15 && dinoEggSpecies.equals("Brachiosaur")) {
            ecoPoints.incEcoPoint(1000);
            hatched = true;
            if (probability.getChances(50)) {
                currentLocation.addActor(new BabyBrachio("Baby " + dinoEggSpecies, dinoEggSpecies, "Female"));
            } else {
                currentLocation.addActor(new BabyBrachio("Baby " + dinoEggSpecies, dinoEggSpecies, "Male"));
            }
        } else if (timeToHatch == 50 && dinoEggSpecies.equals("Allosaur")) {
            ecoPoints.incEcoPoint(1000);
            hatched = true;
            if (probability.getChances(50)) {
                currentLocation.addActor(new BabyAllo("Baby " + dinoEggSpecies, dinoEggSpecies, "Female"));
            } else {
                currentLocation.addActor(new BabyAllo("Baby " + dinoEggSpecies, dinoEggSpecies, "Male"));
            }
        }else if (timeToHatch == 20 && dinoEggSpecies.equals("Pterodactyl")) {
            ecoPoints.incEcoPoint(100);
            hatched = true;
            if (probability.getChances(50)) {
                currentLocation.addActor(new BabyPtero("Baby " + dinoEggSpecies, dinoEggSpecies, "Female"));
            } else {
                currentLocation.addActor(new BabyPtero("Baby " + dinoEggSpecies, dinoEggSpecies, "Male"));
            }
        }

        if (hatched) {
            System.out.println(dinoEggSpecies + " egg at (" + currentLocation.x() + ", " + currentLocation.y() + ") is hatched!");
            currentLocation.removeItem(this);   // remove egg
            currentLocation.setGround(new Dirt());
        }
    }
}
