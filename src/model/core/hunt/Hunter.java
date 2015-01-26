package model.core.hunt;

import model.core.Agent;

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

        // assert that shorterPath is free, then move
        if (this.environment.getGrid()[nextMove[0]][nextMove[1]] == null) {
            // remove this from the grid
            this.environment.grid[this.posX][this.posY] = null;
            this.posX = nextMove[0];
            this.posY = nextMove[1];
            // update the grid with the new position
            this.environment.grid[this.posX][this.posY] = this;
        }
    }

}
