package model.core.population;

import model.core.Agent;
import model.core.Environment;

public class PeopleBerenguer extends Agent {
    
    public int neighbors;
    
    public int type;

    public PeopleBerenguer(int posX, int posY, Environment environment, int neighbors, int type) {
        super(posX, posY, environment);
        this.neighbors = neighbors;
        this.type = type;
    }

    @Override
    public void action() {
        this.neighbors = this.environment.search(this.posX, this.posY, this.getClass()).size();
    }

}
