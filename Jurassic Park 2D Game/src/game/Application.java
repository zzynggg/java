package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import edu.monash.fit2099.engine.*;
import game.dinosaurs.Corpse;
import game.mapstuff.*;
import game.playerBehaviour.Player;
import game.vendingmachinestuff.EcoPointInterface;
import game.vendingmachinestuff.VendingMachine;

/**
 * The main class for the Jurassic World game.
 */
public class Application implements EcoPointInterface {
    private Ground ground;
    private List<Exit> exits = new ArrayList<>();

    public static void main(String[] args) {

        World world = new World(new Display());
        FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Tree(), new Bush(), new Lakes());
        List<String> map = Arrays.asList(
                "................................................................................",
                "................................................................................",
                ".....#######........................................................~~~~~.......",
                ".....#_____#.......................................................~~~..........",
                ".....#_____#..........................................................~~........",
                ".....###.###....................................................................",
                "................................................................................",
                "......................................+++.......................................",
                ".......................................++++.....................................",
                "...................................+++++........................................",
                ".............~~~~~...................++++++.....................................",
                "...............~~~~~..................+++.......................................",
                ".....................................+++........................................",
                "................................................................................",
                "............+++.................................................................",
                ".............+++++..............................................................",
                "...............++........................................+++++..................",
                ".............+++....................................++++++++....................",
                "............+++.......................................+++.......................",
                "..............................~~~...............................................",
                ".............................~~~~........................................++.....",
                "...............................~~~......................................++.++...",
                ".........................................................................++++...",
                "..........................................................................++....",
                "................................................................................");

        List<String> secondMap = Arrays.asList(
                "................................................................................",
                "................................................................................",
                "......++++++....................................................................",
                "........++++++..................................................................",
                "..........~~~...................................................................",
                ".........~~~......................................................~~~~~~........",
                "....................................................................~~~~~.......",
                "................................................................................",
                "................................................................................",
                "................................................................................",
                ".................................########.......................................",
                ".................................#______#.......................................",
                "................................._______#.......................................",
                "........~~~~.....................########.......................................",
                "................................................................................",
                ".....~~~~......................................................+++++............",
                "................................................................................",
                ".............................................+~~................................",
                "..............................................+~~...............................",
                "..............................................~++...............................",
                ".............................................+++++..............................",
                "..............++.............................++.................................",
                "............++++..............................~~~~..............................",
                ".........................................................................+++....",
                "................................................................................");

        // Main map

        // Second map




        // *** for brachio to eat as much as it can ***
//        gameMap.at(38, 6).addActor(new Brachiosaur("Adult Brachiosaur", "Male"));


        int choice = 0;
        while (choice != 3) {
            System.out.println("---------------");
            System.out.println("THE MENU");
            System.out.println("---------------");
            System.out.println("BY: Yong Zi Ying ");
            System.out.println("           &        ");
            System.out.println("      Abdulla Nawwaf Ali");
            System.out.println("1) Challenge");
            System.out.println("2) SandBox");
            System.out.println("3) Exit");
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please Select an option:");
            choice = scanner.nextInt();

            if (choice == 1) {
                System.out.println("---------------");
                System.out.println("  -THE MENU-   ");
                System.out.println("---------------");
                System.out.println("How many Turns?");
                int answer = scanner.nextInt();
                System.out.println("THE MENU");
                System.out.println("---------------");
                System.out.println("EcoPoint limit?");
                int answer2 = scanner.nextInt();


                GameMap gameMap = new DinoGameMap(groundFactory, map);
                world.addGameMap(gameMap);

                GameMap secondGameMap = new DinoGameMap(groundFactory, secondMap);
                world.addGameMap(secondGameMap);

                Actor player = new Player("Player", '@', 100,answer);

                Util.addVendingMachine(gameMap,secondGameMap);
                Util.crossingStuff(gameMap,secondGameMap);
                Util.addStego(gameMap,secondGameMap);
                Util.addBush(gameMap, secondGameMap);

                ecoPoints.setEcoPoint(0);
                world.addPlayer(player, gameMap.at(7, 3));
                ecoPoints.setMaxEcoPoints(answer2);


                world.run();
            }
            if (choice == 2) {
                GameMap gameMap = new DinoGameMap(groundFactory, map);
                world.addGameMap(gameMap);

                GameMap secondGameMap = new DinoGameMap(groundFactory, secondMap);
                world.addGameMap(secondGameMap);

                Actor player = new Player("Player", '@', 100);

                Util.addVendingMachine(gameMap,secondGameMap);
                Util.crossingStuff(gameMap,secondGameMap);
                Util.addStego(gameMap,secondGameMap);
                Util.addBush(gameMap, secondGameMap);



                ecoPoints.setMaxEcoPoints(0);
                world.addPlayer(player, gameMap.at(7, 3));
                ecoPoints.setEcoPoint(0);


                world.run();
            }
        }
        if (choice == 3) {
            System.out.println("game over");
        }


        // *** allosaur eating corpse and egg ***

//        gameMap.at(21,5).addItem(new Egg(EggType.STEGOSAUREGG));
//        gameMap.at(30, 21).addActor(new Allosaur("Adult Allosaur","Female"));

//        gameMap.at(12,18).setGround(new Bush());  // player searching a fruit from bush
//        gameMap.at(12, 18).addActor(new BabyStego("Baby Stegosaur", " Stegosaur","Male"));


        // *** for player feeding ***
//        gameMap.at(8, 5).addActor(new Stegosaur("Adult Stegosaur", "Male"));
//        gameMap.at(8, 5).addActor(new Brachiosaur("Adult Brachiosaur", "Male"));
//        gameMap.at(8, 5).addActor(new Allosaur("Adult Allosaur", "Male"));
        // *** for player searching fruit from bush and tree ***
//        gameMap.at(8,6).setGround(new Bush());  // player searching a fruit from bush
//        gameMap.at(8,7).setGround(new Tree());  // player searching a fruit from tree

        // *** for stego eating ***
//        gameMap.at(11,18).addItem(new Fruit(true));
//        gameMap.at(11,18).addItem(new Fruit(false));

        // *** for breeding ***
//        gameMap.at(13, 6).addActor(new Stegosaur("Adult Stegosaur", "Male"));
//        gameMap.at(14, 7).addActor(new Stegosaur("Adult Stegosaur","Female"));
//        gameMap.at(13, 6).addActor(new Brachiosaur("Adult Brachiosaur", "Male"));
//        gameMap.at(12, 7).addActor(new Brachiosaur("Adult Brachiosaur","Female"));
//        gameMap.at(13, 6).addActor(new Allosaur("Adult Allosaur","Male"));
//        gameMap.at(12, 7).addActor(new Allosaur("Adult Allosaur","Female"));
    }
}



