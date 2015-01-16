package model.core.water;

import java.util.ArrayList;
import java.util.Random;

import model.core.Agent;

public abstract class Fish extends Agent {
    
    WaterEnvironment environment;
    
    /**
     * Turns required before birth.
     */
    public int birthDelay;
    
    /**
     * Current number of turns remaining before making a child.
     */
    public int birthDecount;
    
    int age;
    
    ArrayList<int[]> neighborPositions;

    public Fish(int posX, int posY, int birthDelay, WaterEnvironment environment) {
        super(posX, posY, environment);
        this.environment = environment;
        this.birthDelay = birthDelay;
        this.birthDecount = birthDelay;
        this.age = 0;
        this.neighborPositions = new ArrayList<int[]>();
    }
    
    public abstract void birth();

    public void move() {
        // selected a random free position around the box
        Random random = new Random();
        int[] nextPosition = this.neighborPositions.get(random.nextInt(this.neighborPositions.size()));
        // remove this from the grid
        this.environment.grid[this.posX][this.posY] = null;
        this.posX = nextPosition[0];
        this.posY = nextPosition[1];
        // update the grid with the new position
        this.environment.grid[this.posX][this.posY] = this;
    }

    public WaterEnvironment getEnvironment() {
        return environment;
    }

    public int getBirthDelay() {
        return birthDelay;
    }

    public int getBirthDecount() {
        return birthDecount;
    }

    public int getAge() {
        return age;
    }

    public ArrayList<int[]> getNeighborPositions() {
        return neighborPositions;
    }

    public void setEnvironment(WaterEnvironment environment) {
        this.environment = environment;
    }

    public void setBirthDelay(int birthDelay) {
        this.birthDelay = birthDelay;
    }

    public void setBirthDecount(int birthDecount) {
        this.birthDecount = birthDecount;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setNeighborPositions(ArrayList<int[]> neighborPositions) {
        this.neighborPositions = neighborPositions;
    }

}
