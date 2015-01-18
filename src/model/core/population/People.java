package model.core.population;

import java.util.ArrayList;
import java.util.Random;

import model.core.Agent;
import model.core.Environment;

public class People extends Agent {
    
    /**
     * How many neighbors share the same type around me.
     */
    public int satisfaction;

    public People(int posX, int posY, Environment environment, int satisfaction) {
        super(posX, posY, environment);
        this.satisfaction = satisfaction;
    }

    @Override
    public void action() {
        ArrayList<int[]> neighborsPositions = this.environment.search(this.posX, this.posY, this.getClass().getName());
        // satisfaction egals size of neighbors with the same class Name around my box
        System.out.println("same type counter : "+neighborsPositions.size());
        if (neighborsPositions.size() < this.satisfaction) {
            move(); // change position if minimal satisfactio is not reached
        } else {
           System.out.println("satisfaction reached =)");
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
