package model.core.population;

import java.util.ArrayList;

import model.core.Agent;
import model.core.Environment;

public class PeopleBerenguer extends Agent {
    
    /**
     * How many neighbors share the same type around me.
     */
    public int satisfaction;

    public PeopleBerenguer(int posX, int posY, Environment environment, int satisfaction) {
        super(posX, posY, environment);
        this.satisfaction = satisfaction;
    }

    @Override
    public void action() {
        ArrayList<int[]> neighborsPositions = this.environment.search(this.posX, this.posY, this.getClass());
        // count agents with the same type around my position
        int sameTypeCounter = 0;
        for (int[] n : neighborsPositions) {
            if (this.environment.grid[n[0]][n[1]] != null) {
                if (this.environment.grid[n[0]][n[1]].getClass() == this.getClass()) {
                    sameTypeCounter++;
                }
            }
        }
        // change position if minimal satisfactio is not reached
        if (sameTypeCounter < this.satisfaction) {
            move();
        }
        
    }
    
    public void move() {
        // to do
    }

    public int getSatisfaction() {
        return satisfaction;
    }

    public void setSatisfaction(int neighbors) {
        this.satisfaction = neighbors;
    }

}
