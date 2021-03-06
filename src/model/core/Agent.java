package model.core;


public abstract class Agent {

    public int posX;

    public int posY;

    public Environment environment;

    public Agent(int posX, int posY, Environment environment) {
        super();
        this.posX = posX;
        this.posY = posY;
        this.environment = environment;
    }
    
    public abstract void action();

    public Environment getEnvironment() {
        return environment;
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

}
