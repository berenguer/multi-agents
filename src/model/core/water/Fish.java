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

    public Fish(int posX, int posY, int birthDelay, WaterEnvironment environment) {
        super(posX, posY, environment);
        this.birthDelay = birthDelay;
        this.birthDecount = birthDelay;
        this.age = 0;
    }
    
    public abstract void birth();
    
    public abstract void move();
    
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
