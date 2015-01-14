package model.core.water;

import model.core.Agent;

public abstract class Fish extends Agent {
    
    WaterEnvironment water;
    
    /**
     * Turns required before birth.
     */
    public int birthDelay;
    
    /**
     * Current number of turns remaining before making a child.
     */
    public int birthDecount;
    
    int age;

    public Fish(int posX, int posY, int birtheDelay, WaterEnvironment water) {
        super(posX, posY, water);
        this.water = water;
        this.birthDelay = birthDelay;
        this.birthDecount = birthDelay;
        this.age = 0;
    }
    
    public abstract void birth();
    
    public abstract void move();
    
    public int getAge() {
        return age;
    }
    
    @Override
    public WaterEnvironment getEnvironment() {
        return water;
    }

    public void setWaterEnvironment(WaterEnvironment environment) {
        this.water = environment;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
