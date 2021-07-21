package game.playerBehaviour;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Menu;
import game.Behaviour;
import game.vendingmachinestuff.EcoPointInterface;
import game.vendingmachinestuff.BuyingBehaviour;

/**
 * Class representing the Player.
 */
public class Player extends Actor implements EcoPointInterface {
    /**
     * To list out the possible step for actor to move in the map
     */
    private Menu menu = new Menu();
    /**
     * A list of behaviour that the particular actor can behave
     */
    private Behaviour[] behaviour = {new SearchingFruitBehaviour(), new FeedingBehaviour(), new BuyingBehaviour(),new QuitGame()};

    private int turns = 0;

    private int maxTurns;
    /**
     * Constructor.
     *
     * @param name        Name to call the player in the UI
     * @param displayChar Character to represent the player in the UI
     * @param hitPoints   Player's starting number of hitpoints
     */
    public Player(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        maxTurns = 0;
    }

    public Player(String name, char displayChar, int hitPoints,int maxTurns) {
        super(name, displayChar, hitPoints);
        this.maxTurns = maxTurns;
    }

    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        // Handle multi-turn Actions
        for (Behaviour playerBehaviour : behaviour) {
            Action playerAction = playerBehaviour.getAction(this, map);
            if (playerAction != null) {
                actions.add(playerAction);
            }if (lastAction.getNextAction() != null) {
                return lastAction.getNextAction();
            }
        }
        turns++;

        if (ecoPoints.getMaxEcoPoints() > 0){
            display.println("EcoPoints: " + ecoPoints.getEcoPoint() + " || Eco points to win: " + ecoPoints.getMaxEcoPoints());
        }else{
            display.println("EcoPoints: " + ecoPoints.getEcoPoint());
        }
        if (maxTurns != 0){
            display.println("Turns left: " + (maxTurns - turns));
            if (turns > maxTurns){
                if(ecoPoints.getEcoPoint() > ecoPoints.getMaxEcoPoints()){
                    display.println("You WON, YOU MEAN SOMETHING");
                    return new QuitGameAction();
                }
                else{
                    display.println("AWW you lost :(");
                    display.println("don't Try again you probably wont win anyway");
                    return new QuitGameAction();
                }
            }
            else{
                if(ecoPoints.getEcoPoint() > ecoPoints.getMaxEcoPoints()){
                    display.println("You WON, YOU MEAN SOMETHING");
                    return new QuitGameAction();
                }
            }
        }





        return menu.showMenu(this, actions, display);
    }
}
