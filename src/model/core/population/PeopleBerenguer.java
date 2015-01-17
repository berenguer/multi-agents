package model.core.population;

import java.util.ArrayList;
import java.util.Random;

import model.core.Agent;
import model.core.Environment;

public class PeopleBerenguer extends Agent {
    
    /**
     * How many neighbors share the same type around me.
     */
    public int satisfaction;
    
    public int type;

    public PeopleBerenguer(int posX, int posY, Environment environment, int satisfaction, int type) {
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
        // selected a random free position around the box
        Random random = new Random();
        int[] nextPosition = this.environment.findEmptyPosition();
        // remove this from the grid
        this.environment.grid[this.posX][this.posY] = null;
        this.posX = nextPosition[0];
        this.posY = nextPosition[1];
        // update the grid with the new position
        this.environment.grid[this.posX][this.posY] = this;
    }

    public int getSatisfaction() {
        return satisfaction;
    }

    public void setSatisfaction(int neighbors) {
        this.satisfaction = neighbors;
    }
    
    public String toString() {
        return "P";
    }

}
