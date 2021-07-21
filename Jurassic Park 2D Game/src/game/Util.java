package game;

import edu.monash.fit2099.engine.GameMap;
import game.dinosaurs.Corpse;
import game.dinosaurs.dinoactors.Allosaur;
import game.dinosaurs.dinoactors.Brachiosaur;
import game.dinosaurs.dinoactors.Pterodactyl;
import game.dinosaurs.dinoactors.Stegosaur;
import game.mapstuff.Bush;
import game.mapstuff.DinoLocation;
import game.playerBehaviour.CrossingMap;
import game.vendingmachinestuff.VendingMachine;

import java.util.ArrayList;

public class Util {

    public static void crossingStuff(GameMap gameMap,GameMap secondGameMap){
        // for player to move between both map
        int mainMapMostNorth = gameMap.getYRange().min();   // top of the main map
        int secondMapMostSouth = gameMap.getYRange().max(); // bottom of the second map
        int maxRangeForX = gameMap.getXRange().max() + 1;     // inclusive
        // create border for player to cross the map
        for (int i = 0; i < maxRangeForX; i++) {
            // Main map: at most north
            CrossingMap crossingMapMain = new CrossingMap(" to second map", secondGameMap.at(i, secondMapMostSouth - 1));
            gameMap.at(i, mainMapMostNorth).setGround(crossingMapMain);

            // Second map: at most south
            CrossingMap crossingMapSecond = new CrossingMap(" to main map", gameMap.at(i, mainMapMostNorth + 1));
            secondGameMap.at(i, secondMapMostSouth).setGround(crossingMapSecond);
        }
    }

    public static void addVendingMachine(GameMap gameMap,GameMap secondGameMap){
        // Main map
        gameMap.at(6, 3).setGround(new VendingMachine());
        // Second map
        secondGameMap.at(34, 11).setGround(new VendingMachine());
    }

    public static void addStego(GameMap gameMap,GameMap secondGameMap){
        // first map
        gameMap.at(11, 18).addActor(new Stegosaur("Adult Stegosaur", "Male"));
        gameMap.at(28, 20).addActor(new Stegosaur("Adult Stegosaur", "Female"));
        //gameMap.at(11, 19).addActor(new Allosaur("Adult Allosaur", "Male"));
        //gameMap.at(11,17).addActor(new Pterodactyl("Adult Pterodactyl","Male"));


        // Second map
        secondGameMap.at(13, 16).addActor(new Stegosaur("Adult Stegosaur", "Male"));
        secondGameMap.at(20, 18).addActor(new Stegosaur("Adult Stegosaur", "Female"));
        //gameMap.at(11,17).addActor(new Pterodactyl("Adult Pterodactyl","Male"));
        //gameMap.at(17,17).addActor(new Pterodactyl("Adult Pterodactyl","Female"));
        //gameMap.at(12,17).addItem(new Corpse("Stegosaur"));
        //gameMap.at(16,17).addItem(new Corpse("Stegosaur"));
        //gameMap.at(12, 18).addActor(new Allosaur("Adult Allosaur", "Female"));
    }

    public static void addBrachio(GameMap gameMap,GameMap secondGameMap){
        // create 4 brachiosaurs in the main map
        gameMap.at(38, 7).addActor(new Brachiosaur("Adult Brachiosaur", "Male"));
        gameMap.at(29, 19).addActor(new Brachiosaur("Adult Brachiosaur", "Female"));
        gameMap.at(20, 2).addActor(new Brachiosaur("Adult Brachiosaur", "Male"));
        gameMap.at(21, 3).addActor(new Brachiosaur("Adult Brachiosaur", "Female"));
        // Second map
        secondGameMap.at(15, 8).addActor(new Brachiosaur("Adult Brachiosaur", "Male"));
        secondGameMap.at(10, 16).addActor(new Brachiosaur("Adult Brachiosaur", "Female"));
        secondGameMap.at(23, 20).addActor(new Brachiosaur("Adult Brachiosaur", "Male"));
        secondGameMap.at(12, 15).addActor(new Brachiosaur("Adult Brachiosaur", "Female"));
    }

    public static void addBush(GameMap gameMap, GameMap secondGameMap){
        // place some bushes on map initially
        Probability probability = new Probability();
        ArrayList<GameMap> maps = new ArrayList<>();
        maps.add(gameMap);  // Main map
        maps.add(secondGameMap);    // Second map
        for (GameMap dinoMap : maps) {
            for (int y : dinoMap.getYRange()) {
                for (int x : dinoMap.getXRange()) {
                    if (probability.getChances(1)) {
                        DinoLocation dinoLocation = new DinoLocation(gameMap, x, y);
                        if (dinoMap.at(x, y).getDisplayChar() == '.' && dinoLocation.NeighbourTreeCount() <= 0) {
                            Bush bush = new Bush();
                            dinoMap.at(x, y).setGround(bush);
                        }
                    }
                }
            }
        }
    }

}
