package game.items;

public class VegMealKit extends PortableItem {

    /**
     * Vegetarian Meal Kit Constructor
     */
    public VegMealKit() {
        super("VEGMEALKIT", 'v');
        addCapability(FoodType.HERBIVORE);
        addCapability(FoodType.FEEDBYPLAYER);
    }

}
