package game.mapstuff;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import game.dinosaurs.Dinosaur;
import game.dinosaurs.dinoenums.Status;

import java.util.ArrayList;

public class Rain extends Ground{
    /**
     * To get the gameMap and search for lake [Association]
     */
    private GameMap gameMap;

    /**
     * Rain constructor
     *
     * @param gameMap The map to search for lake
     */
    public Rain(GameMap gameMap) {
        super('~');
        this.gameMap = gameMap;
        probabilityToRain();
    }

    /**
     * To check is there any dehydrated dino on the map
     */
    public void probabilityToRain() {
        ArrayList<GameMap> maps = new ArrayList<>();
        maps.add(gameMap);
        for (GameMap dinoMap : maps) {
            for (int y : dinoMap.getYRange()) {
                for (int x : dinoMap.getXRange()) {
                    if (dinoMap.at(x, y).containsAnActor()) {
                        Actor dehydratedDino = dinoMap.at(x, y).getActor();
                        if (dehydratedDino.hasCapability(Status.DEHYDRATED)) {
                            dehydratedDino.removeCapability(Status.DEHYDRATED);
                            dehydratedDino.addCapability(Status.THIRSTY);
                            ((Dinosaur) dehydratedDino).incWaterLevel(10);
                        }
                    }
                }
            }
        }

    }

}
