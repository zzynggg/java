package game.mapstuff;

import edu.monash.fit2099.engine.*;
import game.ProbabilityInterface;
import game.items.FoodType;
import game.items.Fruit;
import game.vendingmachinestuff.EcoPointInterface;

import java.util.ArrayList;

public class Tree extends Ground implements EcoPointInterface, ProbabilityInterface {
    private ArrayList<Item> onTreeFruit;
    private static final int ECO_POINT_TREE = 1;

    /**
     * Tree constructor
     */
    public Tree() {
        super('+');
        addCapability(GroundType.TREE);
        this.onTreeFruit = new ArrayList<>();
    }

    /**
     * To get the array list from the tree
     * @return an array list of fruit
     */
    public ArrayList<Item> getOnTreeFruit() {
        return onTreeFruit;
    }

    @Override
    public void tick(Location location) {
        super.tick(location);
        if (probability.getChances(50)) { // 50% produce 1 ripe fruit
            produceRipeFruit(location);
        }
    }
    /**
     * To add the ripe fruit that produced by the tree
     *
     * @param location location of the tree
     */
    public void produceRipeFruit(Location location) {
        Item fruit = new Fruit(false);
        fruit.addCapability(FoodType.TREEFRUIT);
        location.addItem(fruit);
        ecoPoints.incEcoPoint(ECO_POINT_TREE);
        for (int i = 0; i < onTreeFruit.size(); i++) {
            if (probability.getChances(5)) {    //any ripe fruit in a trees has 5% to fall
                location.removeItem(onTreeFruit.get(i));
                ripeFruitDrop(location, i);
            }
        }
    }

    /**
     * To add fruit at the location and remove from the tree
     *
     * @param location location of the tree
     */
    public void ripeFruitDrop(Location location, int index) {
        Item fruit = new Fruit(true);
        removeOnTreeFruit(index);
        fruit.addCapability(FoodType.GROUNDFRUIT);
        location.addItem(fruit);    //dropped fruit sit on the same squares as the tree
    }

    /**
     * To add the ripe fruit into the array list
     * @param fruitToAddOnTree ripe fruit to be added
     */
    public void addOnTreeFruit(Item fruitToAddOnTree) {
        onTreeFruit.add(fruitToAddOnTree);
    }

    /**
     * after ripe fruit falling, remove the ripe fruit from tree
     * @param indexOfOnTreeFruit index of the fruit to remove from the array list
     */
    public void removeOnTreeFruit(int indexOfOnTreeFruit) {
        onTreeFruit.remove(indexOfOnTreeFruit);
    }

    @Override
    public boolean canActorEnter(Actor actor) {
        return true;
    }

}
