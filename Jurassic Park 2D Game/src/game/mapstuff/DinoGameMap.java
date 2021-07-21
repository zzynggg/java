package game.mapstuff;

import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.GroundFactory;
import edu.monash.fit2099.engine.Location;

import java.util.List;

public class DinoGameMap extends GameMap {


    public DinoGameMap(GroundFactory groundFactory, List<String> lines) {
        super(groundFactory, lines);
    }

    @Override
    protected Location makeNewLocation(int x, int y) {
        return new DinoLocation(this, x, y);
    }

}
