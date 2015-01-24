package model.core.hunt;

import model.core.Agent;
import model.core.Environment;

public class Wall extends Agent {
	/**
	 * Useless Agent who just not moove
	 * @param posX
	 * @param posY
	 * @param environment
	 */
	public Wall(int posX, int posY, Environment environment) {
		super(posX, posY, environment);

	}

	@Override
	public void action() {


	}

}
