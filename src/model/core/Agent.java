package model.core;


public abstract class Agent {
    
    public int posX;
    
    public int posY;

    public Environment water;

    public Agent(int posX, int posY, Environment environment) {
        super();
        this.posX = posX;
        this.posY = posY;
        this.water = environment;
    }

    public Environment getEnvironment() {
        return water;
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

}
