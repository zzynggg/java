package game.mapstuff;

import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import game.ProbabilityInterface;
import game.items.Fruit;

import java.util.ArrayList;

public class DinoLocation extends Location implements ProbabilityInterface {
    private boolean read = false;
    private DinoLocation.CanGrow action = DinoLocation.CanGrow.SAME;

    public DinoLocation(GameMap map, int x, int y) {
        super(map, x, y);
    }

    public void tick() {
        read = !read;

        if (containsAnActor()) {
            boolean groundType2 = getGround().hasCapability(GroundType.BUSH);
            char stuff = getDisplayChar();
            if (stuff == 'B' && groundType2) {
                action = CanGrow.SAME;
                DestroyBush();
            }

        }
        if (read) {
            boolean groundType = getGround().hasCapability(GroundType.DIRT);

            int treeNeighbours = NeighbourTreeCount();
            if (groundType && treeNeighbours <= 0) {
                action = DinoLocation.CanGrow.GROW;
            }



        } else {
            boolean groundType = getGround().hasCapability(GroundType.DIRT);
            int bushNeighbours = NeighbourBushCount();

            if (probability.getChances(1)) {
                if (action == DinoLocation.CanGrow.GROW) {
                    CreateNewBush();
                }
            }
            if (groundType && bushNeighbours >= 2) {
                if (probability.getChances(10)) {
                    if (action == DinoLocation.CanGrow.GROW) {
                        CreateNewBush();
                    }
                }
            }

        }
        super.tick();
    }

    private void DestroyBush() {

        Dirt dirt = new Dirt();
        setGround(dirt);
        ArrayList<Item> someList = new ArrayList<>();
        for(int i = 0; i < getItems().size();i++){
            someList.add(getItems().get(i));
        }
        for(int i = 0; i < getItems().size();i++){
            removeItem(someList.get(i));
        }

        System.out.println("Bronchiosaur steps on Bush");
    }

    private void CreateNewBush() {
        Bush bush = new Bush();
        setGround(bush);
    }

    public int NeighbourTreeCount() {
        return (int) getExits().stream().map(exit -> exit.getDestination().getGround())
                .filter(ground -> ground.hasCapability(GroundType.TREE)).count();
    }

    private int NeighbourBushCount() {
        return (int) getExits().stream().map(exit -> exit.getDestination().getGround())
                .filter(ground -> ground.hasCapability(GroundType.BUSH)).count();

    }


    public enum CanGrow {
        GROW, SAME, DESTROY
    }
}
