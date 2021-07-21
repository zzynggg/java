package game.vendingmachinestuff;

import edu.monash.fit2099.engine.*;
import game.items.*;

import java.util.ArrayList;

public class VendingMachine extends Ground  {
    private ArrayList<Item> items = new ArrayList<>();

    /**
     * Vending Machine constructor
     */
    public VendingMachine() {
        super('V');
        items.add(new Fruit(false));
        items.add(new VegMealKit());
        items.add(new CarnMealKit());
        items.add(new Egg("Stegosaur"));
        items.add(new Egg("Brachiosaur"));
        items.add(new Egg("Allosaur"));
        items.add(new Egg("Pterodactyl"));
        items.add(new LaserGun("LASERGUN",'L',50,"hits"));

    }

    /**
     * To get the list of item
     * @return list of item available in vanding machine
     */
    public ArrayList<Item> getItems() {
        return items;
    }

    /**
     * To set the item
     * @param items list of item
     */
    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }
}
