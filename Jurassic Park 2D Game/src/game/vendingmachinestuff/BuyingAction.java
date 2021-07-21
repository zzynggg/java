package game.vendingmachinestuff;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import game.items.*;

public class BuyingAction extends Action implements EcoPointInterface {

    /**
     * Item to purchase from vending machine
     */
    private Item itemToBuy;
    private Integer deductEcoPoint;
    private Integer balanceEcoPoint;
    private final int FRUIT_ECO_POINT = 30;
    private final int VEG_MEAL_KIT_ECO_POINT = 100;
    private final int CARN_MEAL_KIT_ECO_POINT = 500;
    private final int STEGO_EGG_ECO_POINT = 200;
    private final int BRACHIO_EGG_ECO_POINT = 500;
    private final int ALLO_EGG_ECO_POINT = 1000;
    private final int LASER_GUN_ECO_POINT = 500;
    private final int PTERO_EGG_ECO_POINT = 200;

    /**
     * Buying Constructor
     * @param itemToBuy Item to buy
     */
    public BuyingAction(Item itemToBuy) {
        this.itemToBuy = itemToBuy;
    }

    /**
     * Check the ecoPoint
     * @return ecoPoint to deduct
     */
    public Integer checkEcoPoint() {
        if (itemToBuy.toString().equals("Fruit")) {
            balanceEcoPoint = ecoPoints.getEcoPoint() - FRUIT_ECO_POINT;
            deductEcoPoint = FRUIT_ECO_POINT;
        } else if (itemToBuy.toString().equals(new CarnMealKit().toString())) {
            balanceEcoPoint = ecoPoints.getEcoPoint() - CARN_MEAL_KIT_ECO_POINT;
            deductEcoPoint = CARN_MEAL_KIT_ECO_POINT;
        } else if (itemToBuy.toString().equals(new VegMealKit().toString())) {
            balanceEcoPoint = ecoPoints.getEcoPoint() - VEG_MEAL_KIT_ECO_POINT;
            deductEcoPoint = VEG_MEAL_KIT_ECO_POINT;
        } else if (itemToBuy.toString().equals("LASERGUN")) {
            balanceEcoPoint = ecoPoints.getEcoPoint() - LASER_GUN_ECO_POINT;
            deductEcoPoint = LASER_GUN_ECO_POINT;
        } else if (itemToBuy.toString().equals("Dinosaur Egg")) {
            if (((Egg) itemToBuy).getDinoEggSpecies().equals("Allosaur")) {
                balanceEcoPoint = ecoPoints.getEcoPoint() - ALLO_EGG_ECO_POINT;
                deductEcoPoint = ALLO_EGG_ECO_POINT;
            } else if (((Egg) itemToBuy).getDinoEggSpecies().equals("Stegosaur")) {
                balanceEcoPoint = ecoPoints.getEcoPoint() - STEGO_EGG_ECO_POINT;
                deductEcoPoint = STEGO_EGG_ECO_POINT;
            } else if (((Egg) itemToBuy).getDinoEggSpecies().equals("Brachiosaur")) {
                balanceEcoPoint = ecoPoints.getEcoPoint() - BRACHIO_EGG_ECO_POINT;
                deductEcoPoint = BRACHIO_EGG_ECO_POINT;
            }else if (((Egg) itemToBuy).getDinoEggSpecies().equals("Pterodactyl")) {
                balanceEcoPoint = ecoPoints.getEcoPoint() - PTERO_EGG_ECO_POINT;
                deductEcoPoint = PTERO_EGG_ECO_POINT;
            }
        }
        if (balanceEcoPoint >= 0) {
            return deductEcoPoint;
        }
        return -1;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        if (checkEcoPoint() >= 0) {
            System.out.println("eco point before purchase: "+ ecoPoints.getEcoPoint());
            ecoPoints.incEcoPoint(-checkEcoPoint());
            actor.addItemToInventory(itemToBuy);
            System.out.println("eco point after purchase: " + ecoPoints.getEcoPoint());
            return menuDescription(actor) + " successfully";
        }
        return actor + " has insufficient Eco Points to purchase " + itemToBuy.toString();
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " purchase " + itemToBuy.toString();
    }
}
