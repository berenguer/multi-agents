package model.core.population;

import model.core.Agent;
import model.core.Environment;

public class People extends Agent {
    
    public float minSatisfaction;
    
    /**
     * How many neighbors share the same type around me.
     */
    public float satisfaction;

    public People(int posX, int posY, Environment environment, float minSatisfaction) {
        super(posX, posY, environment);
        this.minSatisfaction = minSatisfaction;
        this.satisfaction = this.environment.search(this.posX, this.posY, this.getEnvironment().getGrid(),
                this.getClass().getName()).size();
    }

    @Override
    public void action() {
        this.satisfaction = this.environment.search(this.posX, this.posY, this.getEnvironment().getGrid(),
                this.getClass().getName()).size();
        int countNeighbors = this.environment.searchType(this.posX, this.posY, this.getEnvironment().getGrid(),
                this.getClass().getSuperclass()).size();
        if (countNeighbors != 0) {
            this.satisfaction = this.satisfaction / countNeighbors;
        } else {
            this.satisfaction = 0;
        }
        
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
    
    public float getSatisfaction() {
        return this.satisfaction;
    }

    public void setSatisfaction(int satisfaction) {
        this.satisfaction = satisfaction;
    }
    
    public String toString() {
        return "P";
    }

}
