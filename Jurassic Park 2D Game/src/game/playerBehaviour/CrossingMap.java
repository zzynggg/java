package game.playerBehaviour;

import edu.monash.fit2099.engine.*;

public class CrossingMap extends Ground {

    private String secondMapDirectionHotKey;
    /**
     * To get the location of the ground
     */
    private Location secondMapLocation;

    /**
     * CrossingMap Constructor
     * @param secondMapDirectionHotKey The display hot key for user to move to another map
     * @param secondMapLocation The Location to move
     */
    public CrossingMap(String secondMapDirectionHotKey, Location secondMapLocation){
        super('=');
        this.secondMapDirectionHotKey = secondMapDirectionHotKey;
        this.secondMapLocation = secondMapLocation;
    }

    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        Actions newActions = super.allowableActions(actor, location, direction);
        newActions.add(new MoveActorAction(secondMapLocation, direction + secondMapDirectionHotKey));
        return newActions;
    }

    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }
}
