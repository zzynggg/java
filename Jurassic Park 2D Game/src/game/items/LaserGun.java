package game.items;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.WeaponItem;
import edu.monash.fit2099.interfaces.ItemInterface;

public class LaserGun extends WeaponItem{
    /**
     * Constructor.
     *
     * @param name        name of the item
     * @param displayChar character to use for display when item is on the ground
     * @param damage      amount of damage this weapon does
     * @param verb        verb to use for this weapon, e.g. "hits", "zaps"
     */
    public LaserGun(String name, char displayChar, int damage, String verb) {
        super(name, displayChar, damage, verb);
    }

    public void addAction(Action action) {
        this.allowableActions.add(action);
    }

}
