package game.mapstuff;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import game.ProbabilityInterface;
import game.items.Fruit;

public class Bush extends Ground implements ProbabilityInterface {

    /**
     * Bush constructor
     */
    public Bush() {
        super('u');
        addCapability(GroundType.BUSH);
    }

    @Override
    public void tick(Location location) {
        super.tick(location);
        if (probability.getChances(10)) {    //10% grow fruit
            produceRipeFruit(location);
        }
    }

    /**
     * To add the ripe fruit that produced by the bush
     *
     * @param location location of the bush
     */
    public void produceRipeFruit(Location location) {
        Item fruit = new Fruit(false);
        location.addItem(fruit); // Add an fruit to this location
    }

    @Override
    public boolean canActorEnter(Actor actor) {
        return true;
    }
}
