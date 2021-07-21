package game.items;

public class Fish extends PortableItem {

    /**
     * Fish constructor
     */
    public Fish() {
        super("Fish", '~');
        addCapability(FoodType.CARNIVORE);
    }

}

