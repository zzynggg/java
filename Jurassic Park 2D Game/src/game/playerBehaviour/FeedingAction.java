package game.playerBehaviour;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import game.items.FoodType;
import game.vendingmachinestuff.EcoPointInterface;
import game.dinosaurs.dinoactions.EatingAction;

public class FeedingAction extends Action implements EcoPointInterface {
    /**
     * Item to feed the dinosaur
     */
    private Item food;
    /**
     * Target dinosaur to feed
     */
    private Actor targetToFeed;

    /**
     * Feeding Constructor
     * @param food Item to feed the dinosaur
     * @param targetToFeed Target dinosaur to feed
     */
    public FeedingAction(Item food, Actor targetToFeed) {
        this.food = food;
        this.targetToFeed = targetToFeed;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        if(food.toString().equals("Fruit")){
            ecoPoints.incEcoPoint(10);
        }
        food.hasCapability(FoodType.FEEDBYPLAYER);
        actor.removeItemFromInventory(food);
        String eating = new EatingAction(food).execute(targetToFeed,map);
        return menuDescription(actor) +". "+ eating;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " feed dinosaur";
    }
}
