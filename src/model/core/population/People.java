package model.core.population;

import java.util.Random;

import model.core.Agent;
import model.core.Environment;

public class People extends Agent {
    
    public int minSatisfaction;
    
    /**
     * How many neighbors share the same type around me.
     */
    public int satisfaction;

    public People(int posX, int posY, Environment environment, int minSatisfaction) {
        super(posX, posY, environment);
        this.minSatisfaction = minSatisfaction;
        this.satisfaction = this.environment.search(this.posX, this.posY, this.getEnvironment().getGrid(),
                this.getClass().getName()).size();
    }

    @Override
    public void action() {
        this.satisfaction = this.environment.search(this.posX, this.posY, this.getEnvironment().getGrid(),
                this.getClass().getName()).size();
        // satisfaction egals size of neighbors with the same class Name around my box
        if (this.satisfaction < this.minSatisfaction) {
            move(); // change position if minimal satisfactio is not reached
        }
    }
    
    public void move() {
        // selected a random free position around the box
        int[] nextPosition = this.environment.findEmptyPosition();
        if (nextPosition != null) {
            // remove this from the grid
            this.environment.grid[this.posX][this.posY] = null;
            this.posX = nextPosition[0];
            this.posY = nextPosition[1];
            // update the grid with the new position
            this.environment.grid[this.posX][this.posY] = this;
        }
    }
    
    public int getSatisfaction() {
        return satisfaction;
    }

    public void setSatisfaction(int satisfaction) {
        this.satisfaction = satisfaction;
    }
    
    public String toString() {
        return "P";
    }

}
