package game.dinosaurs;

import edu.monash.fit2099.engine.Location;
import game.items.FoodType;
import game.items.PortableItem;
import game.mapstuff.Dirt;

public class Corpse extends PortableItem {

    private int turn;
    private String corpseSpecies;

    /**
     * Dinosaur Corpse Constructor
     * @param corpseSpecies Species of the corpse
     */
    public Corpse(String corpseSpecies) {
        super("Dinosaur Corpse", 'x');
        this.corpseSpecies = corpseSpecies;
        addCapability(FoodType.CARNIVORE);
    }

    /**
     * to get the corpse type
     *
     * @return corpse type
     */
    public String getCorpseSpecies() {
        return corpseSpecies;
    }

    @Override
    public void tick(Location currentLocation) {
        super.tick(currentLocation);
        boolean removed = false;
        turn++;
        if (turn >= 20 && (corpseSpecies.equals("Stegosaur")
                || corpseSpecies.equals("Allosaur"))) {
            currentLocation.removeItem(this);
            currentLocation.setGround(new Dirt());
            removed = true;
        } else if (turn >= 40 && corpseSpecies.equals("Brachiosaur")) {
            currentLocation.removeItem(this);
            currentLocation.setGround(new Dirt());
            removed = true;
        }
        if(removed){
            System.out.println(getCorpseSpecies() + " corpse is removed from the map");
        }
    }
}
