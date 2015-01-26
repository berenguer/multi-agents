package model.core.hunt;

import model.core.Agent;
import model.core.Environment;

public class Prey extends Agent {

    public Prey(int posX, int posY, Environment environment) {
        super(posX, posY, environment);
    }

    @Override
    public void action() {
        escapeBro();
    }

    /**
     * Escape the nearest Hunter.
     */
    public void escapeBro() {
        Hunter hunter = nearestHunter();
        int xMove = 0;
        int yMove = 0;
        if (hunter.getPosX() > this.posX) {
            xMove = -1;
        }
        if (hunter.getPosX() < this.posX) {
            xMove = 1;
        }
        if (hunter.getPosY() > this.posY) {
            yMove = -1;
        }
        if (hunter.getPosY() < this.posY) {
            yMove = 1;
        }
        // check if new coords are inbound
        int newPosX = this.getPosX();
        int newPosY = this.getPosY();
        if (xMove != 0) {
            if (((this.posX + xMove) < this.environment.grid.length - 1) &
                    ((this.posX + xMove) > 0)) {
                newPosX = this.posX + xMove; // x axis ok
            }
        }
        if (yMove != 0) {
            if (((this.posY + yMove) < this.environment.grid.length - 1) &
                    ((this.posY + yMove) > 0)) {
                newPosY = this.posY + yMove; // y axis ok
            }
        }
        // check if the new x, y are free
        if (this.environment.getGrid()[newPosX][newPosY] == null) {
            // remove this from the grid
            this.environment.grid[this.posX][this.posY] = null;
            this.posX = newPosX;
            this.posY = newPosY;
            // update the grid with the new position
            this.environment.grid[this.posX][this.posY] = this;
        }
    }

    public Hunter nearestHunter() {
        int maximumDistance = this.environment.getGrid().length;
        int distance;
        Hunter nearestHunter = null;
        for (int i = 0; i < this.environment.getAgents().size(); i++) {
            distance = Math.abs(this.posX - this.environment.getAgents().get(i).getPosX())
                            + Math.abs(this.posY - this.environment.getAgents().get(i).getPosY());
            if ((distance < maximumDistance)) {
                if (this.environment.getAgents().get(i).getClass().equals(Hunter.class)) {
                    nearestHunter = (Hunter) this.environment.getAgents().get(i);
                    maximumDistance = distance;
                }
            }
        }
        return nearestHunter;
    }
    
}
