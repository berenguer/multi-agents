package model.core.hunt;

import model.core.Agent;
import model.core.Environment;

public class Wall extends Agent {
	/**
	 * Useless Agent who just don't move.
	 * @param posX
	 * @param posY
	 * @param environment
	 */
	public Wall(int posX, int posY, Environment environment) {
		super(posX, posY, environment);
	}

	@Override
	public void action() {
	    // do nothing
	}

}
