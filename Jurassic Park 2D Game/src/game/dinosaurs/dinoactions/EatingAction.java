package game.dinosaurs.dinoactions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import game.dinosaurs.Corpse;
import game.dinosaurs.Dinosaur;
import game.dinosaurs.dinoactors.Pterodactyl;
import game.dinosaurs.dinoenums.PteroEat;
import game.dinosaurs.dinoenums.Status;
import game.items.CarnMealKit;
import game.items.Fish;
import game.items.FoodType;
import game.items.VegMealKit;

import java.util.ArrayList;

public class EatingAction extends Action {

    /**
     * Item that the actor eat
     */
    private Item food;
    private final int ALLO_STEGO_CORPSE_FOOD_POINT = 50;
    private final int BRACHIO_CORPSE_FOOD_POINT = 100;
    private final int BRACHIO_FRUIT_POINT = 5;
    private final int STEGO_FRUIT_POINT = 10;
    private final int EGG_FOOD_POINT = 10;
    private final int PLAYER_FEED_FRUIT_POINT = 20;
    private final int VEG_FOOD_POINT = 160;
    private final int CAR_FOOD_POINT = 100;
    private final int FISH_FOOD_POINT = 5;  // assignment 3
    private final int PTERO_EAT_POINT = 10;
    /**
     * To get the entire fruit array list from the tree
     */
    private ArrayList<Item> treeFruit;

    /**
     * For stegosaur/allosaur
     * @param food Item that the actor eat
     */
    public EatingAction(Item food) {
        this.food = food;
    }

    /**
     * For brachiosaur because it can eat as much fruit as it can from a tree
     * @param treeFruit an array list that represent the tree that is located at the same square with brachiosaur
     */
    public EatingAction(ArrayList<Item> treeFruit) {
        this.treeFruit = treeFruit;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        boolean eaten = false;
        String output = "";
        if (treeFruit != null && actor.hasCapability(Status.HUNGRY)) {
            for (int i = 0; i < treeFruit.size(); i++) {
                eaten = true;
                if (actor.hasCapability(Status.FULL)) {
                    break;
                }
                map.locationOf(actor).removeItem(treeFruit.get(i));
                treeFruit.remove(i);
                ((Dinosaur) actor).incFoodLevel(BRACHIO_FRUIT_POINT);
            }
            if (eaten) {
                output = "fruits from a same tree";
                return menuDescription(actor) + output;
            }
        } else if (food != null) {
            if (food.hasCapability(FoodType.FEEDBYPLAYER)) {
                if (food.toString().equals("Fruit")) {
                    eaten = true;
                    ((Dinosaur) actor).incFoodLevel(PLAYER_FEED_FRUIT_POINT);
                    output = "a fruit.";
                } else if (food.toString().equals(new CarnMealKit().toString())) {
                    eaten = true;
                    ((Dinosaur) actor).incFoodLevel(CAR_FOOD_POINT);
                    output = "a carnivore meal kit.";
                } else if (food.toString().equals(new VegMealKit().toString())) {
                    // veg meal kit (bra max 160, ste max 100) modulo 160
                    eaten = true;
                    ((Dinosaur) actor).incFoodLevel(VEG_FOOD_POINT);
                    output = "a vegetarian meal kit.";
                }
            }
            else if (food.toString().equals("Fruit")) {
                eaten = true;
                ((Dinosaur) actor).incFoodLevel(STEGO_FRUIT_POINT);
                output = "a fruit";
            } else if (food.toString().equals("Dinosaur Corpse")) {     // dino corpse
                if(((Dinosaur)actor).getDinoSpecies() == "Pterodactyl"){
                    if(((Dinosaur)actor).hasCapability(PteroEat.NOT_EATING)){
                        if (((Corpse) food).getCorpseSpecies().equals("Allosaur")) {
                            ((Dinosaur) actor).incFoodLevel(PTERO_EAT_POINT);
                            output = "a Allosaur corpse";
                        } else if (((Corpse) food).getCorpseSpecies().equals("Stegosaur")) {
                            ((Dinosaur) actor).incFoodLevel(PTERO_EAT_POINT);
                            output = "a Stegosaur corpse";
                        } else if (((Corpse) food).getCorpseSpecies().equals("Brachiosaur")) {
                            ((Dinosaur) actor).incFoodLevel(PTERO_EAT_POINT);
                            output = "a Brachiosaur corpse";
                        }
                        ((Dinosaur)actor).addCapability(PteroEat.EATING);
                        ((Dinosaur)actor).removeCapability(PteroEat.NOT_EATING);
                        ((Pterodactyl)actor).setEating(((Pterodactyl)actor).getEating()+1);
                        return actor.toString() + " is eating " + output + " at (" + map.locationOf(actor).x() + ", "
                                + map.locationOf(actor).y() + ")";
                    }
                    else if(((Dinosaur)actor).hasCapability(PteroEat.EATING)){
                        ((Pterodactyl)actor).setEating(((Pterodactyl)actor).getEating()+1);
                        if (((Corpse) food).getCorpseSpecies().equals("Allosaur")) {
                            ((Dinosaur) actor).incFoodLevel(PTERO_EAT_POINT);
                            if (((Pterodactyl)actor).getEating() >=3){
                                eaten = true;
                                ((Dinosaur)actor).addCapability(PteroEat.EATING);
                                ((Dinosaur)actor).removeCapability(PteroEat.NOT_EATING);
                            }
                            output = "a Allosaur corpse";
                        } else if (((Corpse) food).getCorpseSpecies().equals("Stegosaur")) {
                            ((Dinosaur) actor).incFoodLevel(PTERO_EAT_POINT);
                            if (((Pterodactyl)actor).getEating() >=3){
                                eaten = true;
                                ((Dinosaur)actor).addCapability(PteroEat.NOT_EATING);
                                ((Dinosaur)actor).removeCapability(PteroEat.EATING);
                            }
                            output = "a Stegosaur corpse";
                        } else if (((Corpse) food).getCorpseSpecies().equals("Brachiosaur")) {
                            ((Dinosaur) actor).incFoodLevel(PTERO_EAT_POINT);
                            if (((Pterodactyl)actor).getEating() >=3){
                                eaten = true;
                                ((Dinosaur)actor).addCapability(PteroEat.NOT_EATING);
                                ((Dinosaur)actor).removeCapability(PteroEat.EATING);
                            }
                            return actor.toString() + " is eating " + output + " at (" + map.locationOf(actor).x() + ", "
                                        + map.locationOf(actor).y() + ")";

                        }

                    }
                }else{
                    if (((Corpse) food).getCorpseSpecies().equals("Allosaur")) {
                        eaten = true;
                        ((Dinosaur) actor).incFoodLevel(ALLO_STEGO_CORPSE_FOOD_POINT);
                        output = "a Allosaur corpse";
                    } else if (((Corpse) food).getCorpseSpecies().equals("Stegosaur")) {
                        eaten = true;
                        ((Dinosaur) actor).incFoodLevel(ALLO_STEGO_CORPSE_FOOD_POINT);
                        output = "a Stegosaur corpse";
                    } else if (((Corpse) food).getCorpseSpecies().equals("Brachiosaur")) {
                        eaten = true;
                        ((Dinosaur) actor).incFoodLevel(BRACHIO_CORPSE_FOOD_POINT);
                        output = "a Brachiosaur corpse";
                    }
                }
            } else if (food.toString().equals("Dinosaur Egg")) {
                eaten = true;
                ((Dinosaur) actor).incFoodLevel(EGG_FOOD_POINT);
                output = "a dino egg";
            }
            else if(food.toString().equals("Fish")){
                eaten = true;
                ((Dinosaur) actor).incFoodLevel(FISH_FOOD_POINT);
                output = "a fish";
            }
        }


        if (eaten) {
            map.locationOf(actor).removeItem(food);
            return menuDescription(actor) + output;
        }

        return actor.toString() + " at (" + map.locationOf(actor).x() + ", "
                + map.locationOf(actor).y() + ") fail to eat" ;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " eats ";
    }
}
