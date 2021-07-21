package game.items;

public class CarnMealKit extends PortableItem {

    /**
     * Carnivore Meal Kit constructor
     */
    public CarnMealKit() {
        super("CARNMEALKIT", 'C');
        addCapability(FoodType.CARNIVORE);
        addCapability(FoodType.FEEDBYPLAYER);
    }

}
