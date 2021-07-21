package game.mapstuff;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import game.ProbabilityInterface;
import game.dinosaurs.dinoenums.CanFly;
import game.dinosaurs.dinoenums.Status;
import game.items.Fish;
import game.items.FoodType;

import java.util.ArrayList;

public class Lakes extends Ground implements ProbabilityInterface {
    private int lakeCapacity;
    private int turns;
    private final int MAXFISHINLAKE = 25;
    /**
     * ArrayList is used to solve to index problem instead of Array because
     * add item into Array need to have accurate index
     */
    private ArrayList<Item> fishInLake = new ArrayList<>();

    /**
     * Lakes constructor for dino to drink water
     */
    public Lakes() {
        super('~');
        addCapability(FoodType.WATER);
        addCapability(GroundType.LAKE);     // [Dependency]
        lakeCapacity = 25;  // starts with 25 sips
        for (int i = 0; i < 5; i++) {   // lakes start with 5 fish
            fishInLake.add(new Fish());
        }
    }

    @Override
    public void tick(Location location) {
        turns++;

        // rain in each lake --> add water into each lake
        if (turns % 10 == 0) {
            if (probability.getChances(20)) {
                // is raining
                incLakeCapacity(probability.getRainFallSips());
                // for dino to alive
                new Rain(location.map());
            }
        }

        // new fish born in each lake
        if (probability.getChances(60)) {
            addFishInLake(new Fish());
            location.addItem(new Fish());
        }
    }

    /**
     * For testing purpose
     * Accessor to return the lake capacity
     *
     * @return lake capacity
     */
    public int getLakeCapacity() {
        return lakeCapacity;
    }

    /**
     * To add water to the lake after raining
     *
     * @param waterToIncrease amount of water to add into the lake
     */
    public void incLakeCapacity(int waterToIncrease) {
        this.lakeCapacity += waterToIncrease;
    }

    /**
     * To remove water from the lake after dino drinking
     *
     * @param waterToDecrease amount of water to add into the lake
     */
    public void decLakeCapacity(int waterToDecrease) {
        this.lakeCapacity -= waterToDecrease;
    }

    /**
     * For testing purpose
     * Accessor to return the arrayList of fish
     *
     * @return an arrayList of fish in lake
     */
    public ArrayList<Item> getFishInLake() {
        return fishInLake;
    }

    /**
     * To add a new fish into the array
     * @param fish fish to be added into the lake
     */
    public void addFishInLake(Item fish) {
        // max 25 in each lake!
        if (fishInLake.size() < MAXFISHINLAKE) {
            fishInLake.add(fish);
        }
    }

    /**
     * To remove fish from lake after pterodactyls eat
     *
     * @param indexOfFishInLake index of fish in lake to remove
     */
    public void removeFishFromLake(int indexOfFishInLake) {
        fishInLake.remove(indexOfFishInLake);
    }

    @Override
    public boolean canActorEnter(Actor actor) {
        // only Pterodactyls can wander around on lakes
        return actor.hasCapability(CanFly.FlY);
    }
}
