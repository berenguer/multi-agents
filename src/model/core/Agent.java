package model.core;

import model.core.water.WaterEnvironnement;


public abstract class Agent {
    
    public int posX;
    
    public int posY;

    /**
     * Environnement contains every agents.
     * Usefull for analyze neighbours of this agent in the grid with other agents.
     */
    public WaterEnvironnement env;

    public Agent(int posX, int posY, WaterEnvironnement env) {
        super();
        this.posX = posX;
        this.posY = posY;
        this.env = env;
    }

    public WaterEnvironnement getEnv() {
        return env;
    }

    public void setEnv(WaterEnvironnement env) {
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

    public abstract void action();
    
    @Override
    public abstract String toString();

}
