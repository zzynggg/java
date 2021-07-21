package game.mapstuff;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

/**
 * A class that represents bare dirt.
 */
public class Dirt extends Ground {

	/**
	 * Dirt Constructor
	 */
	public Dirt() {
		super('.');
		addCapability(GroundType.DIRT);
	}


	@Override
	public boolean canActorEnter(Actor actor) {
		return true;
	}
}
