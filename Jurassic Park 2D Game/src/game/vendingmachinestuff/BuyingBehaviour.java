package game.vendingmachinestuff;

import edu.monash.fit2099.engine.*;
import game.Behaviour;

import java.util.Scanner;
// can buy as many as u can
public class BuyingBehaviour implements Behaviour {

    @Override
    public Action getAction(Actor actor, GameMap map) {
        int choice;
        VendingMachine vendingMachine = new VendingMachine();
        if (map.locationOf(actor).getGround().getDisplayChar() == 'V') {
            choice = optionMenu();
            if (choice > 0 && choice < 9) {
                return new BuyingAction(vendingMachine.getItems().get(choice-1));
            }
        }
        return null;
    }

    /**
     * Ask for user to input an option to purchase item from vending machine
     * @return option value
     */
    private Integer optionMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n1) Purchase Fruit: 30 eco points");
        System.out.println("2) Purchase Vegetarian meal kit: 100 eco points");
        System.out.println("3) Purchase Carnivore meal kit: 500 eco points");
        System.out.println("4) Purchase Stegosaur egg: 200 eco points");
        System.out.println("5) Purchase Brachiosaur egg: 500 eco points");
        System.out.println("6) Purchase Allosaur egg: 1000 eco points");
        System.out.println("7) Purchase Pterodactyl egg: 200 eco points");
        System.out.println("8) Purchase Laser gun: 500 eco points");
        System.out.println("9) Don't want to buy anything!");
        System.out.println("--------------------");
        System.out.println("Select your option to  purchase an item");
        return scanner.nextInt();
    }

}
