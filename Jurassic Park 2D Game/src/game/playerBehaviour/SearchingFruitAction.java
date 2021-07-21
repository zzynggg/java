package game.playerBehaviour;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import game.ProbabilityInterface;
import game.items.Fruit;
import game.mapstuff.TreeInterface;
import game.vendingmachinestuff.EcoPointInterface;

public class SearchingFruitAction extends Action implements TreeInterface, EcoPointInterface, ProbabilityInterface {
    private char plantType;

    /**
     * Searching fruit constructor
     * @param plantType display character of the plant
     */
    public SearchingFruitAction(char plantType) {
        this.plantType = plantType;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        String output = "";
        Item fruit = new Fruit(false);
        if (probability.getChances(60)) {
            return "You search the tree or bush for fruit, but you can't find any ripe ones.";
        } else {
            if (plantType == '+'&& !tree.getOnTreeFruit().isEmpty()) {
                // can only search one ripe fruit per turn from tree
                tree.removeOnTreeFruit(1);
                output = "tree";
            } else {  // can only search one ripe fruit per turn from bush
                output = "bush";
            }
            map.locationOf(actor).removeItem(fruit);
            actor.addItemToInventory(fruit);
            ecoPoints.incEcoPoint(10);
        }
        return menuDescription(actor) + " from " + output + " successfully";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " searching a ripe fruit";
    }
}
