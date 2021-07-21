package game.dinosaurs.dinoactions;

import edu.monash.fit2099.engine.*;
import game.dinosaurs.Corpse;
import game.dinosaurs.Dinosaur;
import game.dinosaurs.dinoenums.Status;
import game.items.PortableItem;

public class AlloAttackingAction extends Action{

    private Actor targetOfAttack;

    public AlloAttackingAction(Actor targetOfAttack) {
        this.targetOfAttack = targetOfAttack;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        if (!((Dinosaur)targetOfAttack).isFlying()){
            if (((Dinosaur) actor).isCanAttack() && ((Dinosaur) targetOfAttack).AbilityToBeAttacked()) {
                if(((Dinosaur) targetOfAttack).getDinoSpecies() == "Stegosaur"){
                    System.out.println(((Dinosaur) targetOfAttack).getFoodLevel());
                    ((Dinosaur) actor).incFoodLevel(20);
                    ((Dinosaur) targetOfAttack).decFoodLevel(20);


                    if (targetOfAttack.hasCapability(Status.DYING)) {

                        map.locationOf(targetOfAttack).addItem(new Corpse(((Dinosaur)targetOfAttack).getDinoSpecies()));
                        map.removeActor(targetOfAttack);


                        return (System.lineSeparator() + targetOfAttack + " is killed.");

                    }
                    return( actor + "attacks "+ targetOfAttack) ;


                }else
                if(((Dinosaur) targetOfAttack).getDinoSpecies() == "Pterodactyl"){
                    ((Dinosaur)actor).incFoodLevel(100);

                    Actions dropActions = new Actions();
                    for (Item item : targetOfAttack.getInventory())
                        dropActions.add(item.getDropAction());
                    for (Action drop : dropActions)
                        drop.execute(targetOfAttack, map);
                    map.removeActor(targetOfAttack);
                    return( actor + " attacks "+ targetOfAttack +"\n"+System.lineSeparator() + targetOfAttack + " is killed.") ;

                }
            }
        }

        return "Failed to attack";
    }


    @Override
    public String menuDescription(Actor actor) {
        return actor + " at (";
    }
}
