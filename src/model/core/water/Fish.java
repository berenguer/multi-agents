package model.core.water;

import model.core.Agent;

public abstract class Fish extends Agent {
    
    int age;
    
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Fish(int posX, int posY, WaterEnvironnement env) {
        super(posX, posY, env);
        this.age = 0;
    }
    
    public abstract void birth();
    
    public abstract void move();

}
