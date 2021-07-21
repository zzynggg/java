package game.items;

import edu.monash.fit2099.engine.Location;
import game.mapstuff.TreeInterface;

public class Fruit extends PortableItem implements TreeInterface {
    private boolean rotten;
    private int turns;

    /**
     * Fruit contructor
     * @param rotten fruit condition either will rot or no
     */
    public Fruit(boolean rotten) {
        super("Fruit", '*');
        addCapability(FoodType.HERBIVORE);
        this.rotten = rotten;
    }

    @Override
    public void tick(Location currentLocation) {
        super.tick(currentLocation);

        if (rotten) {
            this.addCapability(FoodType.GROUNDFRUIT);
            turns++;
            if (turns == 15) {
                currentLocation.removeItem(this);
            }
        }else {
            if(currentLocation.getGround().getDisplayChar()=='+'){
                tree.addOnTreeFruit(this);
            }else{
                this.addCapability(FoodType.BUSHFRUIT);
            }
        }
    }

}
