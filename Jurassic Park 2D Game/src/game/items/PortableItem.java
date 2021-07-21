package game.items;

import edu.monash.fit2099.engine.Item;

/**
 * Base class for any item that can be picked up and dropped.
 */
public class PortableItem extends Item{

	/**
	 * Portable item constructor
	 * @param name Name of the item
	 * @param displayChar Display character of the item
	 */
	public PortableItem(String name, char displayChar) {
		super(name, displayChar, true);
	}

}
