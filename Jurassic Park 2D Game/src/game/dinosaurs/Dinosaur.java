package game.dinosaurs;

import edu.monash.fit2099.engine.*;
import game.Behaviour;
import game.dinosaurs.dinobehaviours.*;
import game.dinosaurs.dinoenums.Age;
import game.dinosaurs.dinoenums.Status;
import game.items.Egg;

public abstract class Dinosaur extends Actor {

    private int currentFoodLevel;
    private int maxFoodLevel;
    private int breedableFoodLevel;
    private int feelHungry;
    private int unconsciousTurn;
    private int turn;
    private boolean canAttack;
    private String dinoSpecies;
    private String dinoGender;
    private boolean hatching;
    private int timeToLayEgg;
    private boolean flying;
    // assignment 3
    private final int FEEL_THIRSTY = 40;
    private int currentWaterLevel;
    private int maxWaterLevel;
    private int dehydratedTurn;
    private final int DEHYDRATED_UNCONSCIOUS_TURN = 15;

    /**
     * attribute (Association)
     * A list of behaviour that the particular actor can behave
     */
    private Behaviour[] dinoBehaviour = {new AlloAttackingBehaviour(),
            new EatingBehaviour(), new MoveToEatBehaviour(),
            new DrinkingBehaviour(), new MoveToDrinkBehaviour(),
            new MateBehaviour()
            ,new MoveToTreeBehaviour(),
    new RestingBehaviour(),
            new WanderBehaviour()};


    /**
     * Dinosaur constructor
     * @param name Dinosaur's name
     * @param displayChar Dinosaur's display character
     * @param currentFoodLevel Dinosaur's starting food level
     * @param maxFoodLevel Dinosaur's maximum food level
     * @param breedableFoodLevel Dinosaur's breedable food level
     * @param feelHungry Dinosaur's hungry food level
     * @param unconsciousTurn Dinosaur's unconscious turn
     * @param canAttack ability for Dinosaur to attack
     * @param dinoSpecies Dinosaur's species
     * @param dinoGender Dinosaur's gender
     * @param currentWaterLevel Dinosaur's stating water level
     * @param maxWaterLevel Dinosaur's maximum water level
     */
    public Dinosaur(String name, char displayChar, int currentFoodLevel, int maxFoodLevel,
                    int breedableFoodLevel, int feelHungry, int unconsciousTurn, boolean canAttack,
                    String dinoSpecies, String dinoGender, int currentWaterLevel, int maxWaterLevel,
                    boolean flying) {
        super(name, displayChar, currentFoodLevel);
        this.currentFoodLevel = currentFoodLevel;
        this.maxFoodLevel = maxFoodLevel;
        this.dinoSpecies = dinoSpecies;
        this.dinoGender = dinoGender;
        this.breedableFoodLevel = breedableFoodLevel;
        this.feelHungry = feelHungry;
        this.unconsciousTurn = unconsciousTurn;
        this.canAttack = canAttack;
        this.currentWaterLevel = currentWaterLevel;
        this.maxWaterLevel = maxWaterLevel;
        this.flying = flying;
    }

    /**
     * to get the species of the actor
     *
     * @return actor species
     */
    public String getDinoSpecies() {
        return dinoSpecies;
    }

    /**
     * to decrement the food level of the actor
     *
     * @param newFoodLevel food level that need to decrement
     */
    public void decFoodLevel(int newFoodLevel) {
        if (this.currentFoodLevel > 0) {
            this.currentFoodLevel -= newFoodLevel;
        }
        if (this.currentFoodLevel < 0){
            addCapability(Status.DYING);
        }
    }

    /**
     * to increment the food level of the actor
     *
     * @param newFoodLevel food level that need to increment
     */
    public void incFoodLevel(int newFoodLevel) {
        if (this.maxFoodLevel >= currentFoodLevel + newFoodLevel) {
            this.currentFoodLevel += newFoodLevel;
        } else {
            this.currentFoodLevel = this.maxFoodLevel;
            this.addCapability(Status.FULL);
        }
    }

    /**
     * Accessor the get the food level for the particular actor
     *
     * @return the current food level
     */
    public int getFoodLevel() {
        return currentFoodLevel;
    }

    /**
     * To check the food level of each actor
     *
     * @param actor   dino that is hungry
     * @param display display appropriate output
     * @param gameMap get the location of the hungry dino from gameMap
     */
    public void hungry(Actor actor, Display display, GameMap gameMap) {
        if (getFoodLevel() < this.feelHungry && getFoodLevel() > 0) {
            display.println(actor + " at (" + gameMap.locationOf(actor).x() + ", "
                    + gameMap.locationOf(actor).y() + ") is getting hungry! Current food level: " + getFoodLevel());
            this.addCapability(Status.HUNGRY);
        } else if (getFoodLevel() >= this.feelHungry) {
            this.removeCapability(Status.HUNGRY);
        } else if (getFoodLevel() > this.breedableFoodLevel) {
            this.addCapability(Status.BREEDABLE);
        } else if (getFoodLevel() <= 0) {      // fdLvl == 0
            turn++;
            display.println(actor + " at (" + gameMap.locationOf(actor).x() + ", "
                    + gameMap.locationOf(actor).y() + ") is unconscious!");
            this.addCapability(Status.DYING);
        }
    }

    /**
     * To decrement the water level of the actor
     *
     * @param newWaterLevel water level that need to decrement
     */
    public void decWaterLevel(int newWaterLevel) {
        if (currentWaterLevel > 0) {
            currentWaterLevel -= newWaterLevel;
        }
    }

    /**
     * To increment the water level of the actor
     *
     * @param newWaterLevel water level that need to increment
     */
    public void incWaterLevel(int newWaterLevel) {
        if (this.maxWaterLevel >= currentWaterLevel) {
            this.currentWaterLevel += newWaterLevel;
        } else {
            this.currentWaterLevel = this.maxWaterLevel;
            this.addCapability(Status.NOTTHIRSTY);
        }
    }

    /**
     * Accessor the get the water level for the particular actor
     *
     * @return the current water level
     */
    public int getWaterLevel() {
        return currentWaterLevel;
    }

    /**
     * To check the water level of each actor
     *
     * @param actor   dino that is thirsty
     * @param display display appropriate output
     * @param gameMap get the location of the thirsty dino from gameMap
     */
    public void thirsty(Actor actor, Display display, GameMap gameMap) {
        if (getWaterLevel() < this.FEEL_THIRSTY && getWaterLevel() > 0) {
            display.println(actor + " at (" + gameMap.locationOf(actor).x() + ", "
                    + gameMap.locationOf(actor).y() + ") is getting thirsty! Current water level: " + getWaterLevel());
            this.addCapability(Status.THIRSTY);
        } else if (getWaterLevel() >= this.FEEL_THIRSTY) {
            this.removeCapability(Status.THIRSTY);
        } else if (getWaterLevel() <= 0) {
            dehydratedTurn++;
            display.println(actor + " at (" + gameMap.locationOf(actor).x() + ", "
                    + gameMap.locationOf(actor).y() + ") is dehydrated!");
            this.addCapability(Status.DEHYDRATED);
        }
    }

    /**
     * To check the number of turn for actor been unconscious on the map when the food level is below 0
     *
     * @param display display appropriate output
     * @param map     get the location of the unconscious dino from map
     * @return action that the actor can do
     */
    public Action dinoUnconscious(Display display, GameMap map) {
        if (this.hasCapability(Status.DYING)) {
            if (getFoodLevel() > 0) {
                // feed by player
                removeCapability(Status.DYING);
                turn = 0;
            } else if (turn == this.unconsciousTurn) {
                display.println(this + " at (" + map.locationOf(this).x() + ", "
                        + map.locationOf(this).y() + ") become corpse after been unconscious for a few turns");
                map.locationOf(this).addItem(new Corpse(this.getDinoSpecies()));
                map.removeActor(this);
            }
            return new DoNothingAction();
        } else if (this.hasCapability(Status.DEHYDRATED)) {
            if (getWaterLevel() > 0) {
                // rain
                removeCapability(Status.DEHYDRATED);
                dehydratedTurn = 0;
            } else if (dehydratedTurn == DEHYDRATED_UNCONSCIOUS_TURN) {
                display.println(this + " at (" + map.locationOf(this).x() + ", "
                        + map.locationOf(this).y() + ") is dead (removed) after been dehydrated for 15 turns");
                map.removeActor(this);
            }
            return new DoNothingAction();
        }
        return null;
    }

    /**
     * To determine the female actor is hatching an egg or not
     *
     * @return true if the actor is hatching an egg else false
     */
    public boolean isHatching() {
        return hatching;
    }

    /**
     * To set the female actor hatching status either is hatching or not hatching an egg
     *
     * @param hatching either is hatching or not hatching an egg
     */
    public void setHatching(boolean hatching) {
        this.hatching = hatching;
    }

    /**
     * Accessor to get the dinosaur species
     *
     * @return dinosaur species
     */
    public String getDinoGender() {
        return dinoGender;
    }

    /**
     * Accessor to get the breedable food level for adult dinosaur
     *
     * @return breedable food level
     */
    public int getBreedableFoodLevel() {
        return breedableFoodLevel;
    }

    /**
     * To check the species, food level, and gender before mating.
     *
     * @param dino1 first actor
     * @param dino2 second actor (partner)
     * @return true if both actor meet all the conditions else false
     */
    public boolean abilityToMate(Actor dino1, Actor dino2) {
        if (dino1.hasCapability(Age.ADULTDINO) && dino2.hasCapability(Age.ADULTDINO)) {     // check is adult or not
            if (!this.isHatching() == !((Dinosaur) dino2).isHatching()) {   // check dino currently is not hatching an egg
                if (this.dinoSpecies.equals(((Dinosaur) dino2).dinoSpecies)) {  // check both species
                    if (((Dinosaur) dino1).getFoodLevel() > this.getBreedableFoodLevel()
                            && ((Dinosaur) dino2).getFoodLevel() > this.getBreedableFoodLevel()) {  // check both food level
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * To check whether the female actor can lay an egg or not
     *
     * @param actor female actor
     * @param map   to get the location of the actor from the map
     */
    public void layEgg(Actor actor, GameMap map) {
        if (isHatching()) {
            timeToLayEgg++;
            // place an egg on map
            if (timeToLayEgg > 10 && ((Dinosaur) actor).dinoSpecies.equals("Stegosaur")) {
                map.locationOf(actor).addItem(new Egg(((Dinosaur) actor).dinoSpecies));
                ((Dinosaur) actor).setHatching(false);
                timeToLayEgg = 0;
            } else if (timeToLayEgg > 30 && ((Dinosaur) actor).dinoSpecies.equals("Brachiosaur")) {
                map.locationOf(actor).addItem(new Egg(((Dinosaur) actor).dinoSpecies));
                ((Dinosaur) actor).setHatching(false);
                timeToLayEgg = 0;
            } else if (timeToLayEgg > 2 && ((Dinosaur) actor).dinoSpecies.equals("Allosaur")) {
                map.locationOf(actor).addItem(new Egg(((Dinosaur) actor).dinoSpecies));
                ((Dinosaur) actor).setHatching(false);
                timeToLayEgg = 0;
            }else if(timeToLayEgg > 10 && ((Dinosaur) actor).dinoSpecies.equals("Pterodactyl")){
                map.locationOf(actor).addItem(new Egg(((Dinosaur) actor).dinoSpecies));
                ((Dinosaur) actor).setHatching(false);
                timeToLayEgg = 0;
            }
        }
    }

    /**
     * To run the game
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return action that the actor can react
     */
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        decFoodLevel(1);    // foodLevel--
        decWaterLevel(1);   //waterLevel--
        hungry(this, display, map); // check for food level
        thirsty(this, display, map);  // check for water level
        layEgg(this, map);  // to inc the timeToLayEgg
        // move around
        Action unconsciousAction = dinoUnconscious(display, map);
        if (unconsciousAction != null) {
            return unconsciousAction;
        }

        if (lastAction != null && lastAction.getNextAction() != null) {
            return lastAction.getNextAction();
        }
        // loop the behaviour that the actor can react
        for (Behaviour behaviour : dinoBehaviour) {
            if (behaviour.getAction(this, map) != null) {
                    return behaviour.getAction(this, map);
            }
        }


        return new DoNothingAction();
    }

    public Boolean AbilityToBeAttacked(){
        if (dinoSpecies.contains("Stegosaur") || dinoSpecies.contains("Pterodactyl")){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean isCanAttack() {
        return canAttack;
    }

    public void setFlying(boolean flying) {
        this.flying = flying;
    }

    public boolean isFlying() {
        return flying;
    }
}
