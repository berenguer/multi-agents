package model.agent;

import model.Environnement;

public abstract class Agent {
    
    public int posX;
    
    public int posY;
    
    public int age;

    /**
     * Environnement contains every agents.
     * Usefull for analyze neighbours of this agent in the grid with other agents.
     */
    public Environnement env;

    public Agent(int posX, int posY, Environnement env) {
        super();
        this.posX = posX;
        this.posY = posY;
        this.age = 0;
        this.env = env;
    }

    public Environnement getEnv() {
        return env;
    }

    public void setEnv(Environnement env) {
        this.env = env;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
    
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    
    public abstract void action();
    
    public abstract void birth();
    
    public abstract void move();
    
    @Override
    public abstract String toString();

}
