package model.core.hunt;

import java.util.ArrayList;
import java.util.Collections;

import model.core.Agent;
import model.core.Environment;

public class Hunter extends Agent {
    
    HuntEnvironment environment;

    public Hunter(int posX, int posY, HuntEnvironment environment) {
        super(posX, posY, environment);
        this.environment = environment;
    }

    @Override
    public void action() {
        move(this.posX, this.posY);
    }
    
    public void move(int x, int y) {
        int[] nextMove = this.environment.shorterPathAt(x, y);
        // if no shorter path and no prey available move around
        if ((nextMove[0] == this.posX) & (nextMove[1] == this.posY)) {
            ArrayList<int[]> preyNeighborhood = this.environment.search(this.posX, this.posY, this.environment.getGrid(),
                    Prey.class.getName());
            
            if (preyNeighborhood.size() == 0) {
                ArrayList<int[]> availablePositions = this.environment.search(this.posX, this.posY, this.environment.getGrid(),
                        null);
                Collections.shuffle(availablePositions);
            }
        }
        // assert that shorterPath is free, then move
        else if (this.environment.getGrid()[nextMove[0]][nextMove[1]] == null) {
            // remove this from the grid
            this.environment.grid[this.posX][this.posY] = null;
            this.posX = nextMove[0];
            this.posY = nextMove[1];
            // update the grid with the new position
            this.environment.grid[this.posX][this.posY] = this;
        }
    }

}
