package game.dinosaurs.dinoactions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.dinosaurs.Dinosaur;
import game.dinosaurs.dinobehaviours.WanderBehaviour;

public class DrinkingAction extends Action {

    /**
     * The actor that is thirsty
     */
    private Actor thirstyDino;
    /**
     * The gameMap that the thirsty actor currently at
     */
    private GameMap gameMap;
    private final int BRACHIO_WATER_POINT = 80;
    private final int DINO_WATER_POINT = 30;

    /**
     * Drinking constructor for thirsty dino
     * @param thirstyDino Actor that is thirsty
     * @param gameMap gameMap that Actor currently at
     */
    public DrinkingAction(Actor thirstyDino, GameMap gameMap) {
        this.thirstyDino = thirstyDino;
        this.gameMap = gameMap;
    }


    @Override
    public String execute(Actor actor, GameMap map) {
        if(((Dinosaur)actor).getDinoSpecies().equals("Brachiosaur")){
            ((Dinosaur)actor).incWaterLevel(BRACHIO_WATER_POINT);
        }else{
            ((Dinosaur)actor).incWaterLevel(DINO_WATER_POINT);
        }
        return menuDescription(actor);
    }

    @Override
    public Action getNextAction() {
        if (new WanderBehaviour().getAction(thirstyDino, gameMap) != null) {
            return new WanderBehaviour().getAction(thirstyDino, gameMap);
        }
        return null;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " drinks water successfully";
    }
}
