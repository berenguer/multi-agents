package model.core.hunt;

import java.lang.reflect.Type;
import java.util.Collections;

import model.core.Environment;

public class HuntEnvironment extends Environment {
    
    public Prey prey;
    
    /**
     * Grid of distances from a box to the prey position.
     * The distances are calculated with dijkstra algo' in calculateDistanceFromPrey().
     */
    public int[][] distances;

    public HuntEnvironment(int size, Type agentSupertype) {
        super(size, agentSupertype);
        this.distances = new int[this.grid.length][this.grid.length];
    }
    
    /**
     * Play one iteration/turn of the system.
     */
    @Override
    public void doIt() {
        // order of agents in the turn is random
        Collections.shuffle(this.agents);
        // prey action
        this.prey.action();
        calculateDistanceFromPrey();
        // hunters actions
        for (int i = 0; i < this.agents.size(); i++) {
            this.agents.get(i).action();
        }
        notifyObserver();
        updateAgentsList();
    }
    
    public void calculateDistanceFromPrey() {
        for (int i = 0; i < this.distances.length; i++) {
            for (int j = 0; j < this.distances.length; j++) {
                int distance = Math.abs(i - this.prey.getPosX())
                        + Math.abs(j - this.prey.getPosY());
                this.distances[i][j] = distance;
            }
        }
    }
    
    public int[] shorterPathAt(int posX, int posY) {
        int [] path = new int[2];
        path[0] = -666;
        path[1] = -666;
        int[] values = new int[3];
        values[0] = -1;
        values[1] = 0;
        values[2] = 1;
        // x and y refer to a relative shift of posX, posY
        for (int x : values) {
            // check if the row at x + posX is inbound
            if ((x + posX >= 0) & (x + posX < this.distances.length)) {
                for (int y : values) {
                    // check if the column at y + posY is inbound
                    if ((y + posY >= 0) & (y + posY < this.distances.length)) {
                        // omit the value exactly at posX, posY
                        if (!((x == 0) & (y == 0))) {
                            if (grid[x + posX][y + posY] == null) {
                                if (path[0] == (-666)) {
                                    path[0] = x + posX;
                                    path[1] = y + posY;
                                } else if (this.distances[x + posX][y + posY] < this.distances[path[0]][path[1]]) {
                                    path[0] = x + posX;
                                    path[1] = y + posY;
                                }
                            }
                        }
                    }
                } // for y
            } // for x
        }
        if (path[0] == (-666)) {
            path[0] = posX;
            path[1] = posY;
        }
        return path;
    }
    
    public Prey getPrey() {
        return prey;
    }

    public void addPrey(Prey prey) {
        this.grid[prey.getPosX()][prey.getPosY()] = prey;
        this.prey = prey;
    }
    
    public int[][] getDistances() {
        return distances;
    }
    
    public String getDistancesToString() {
        String res = "";
        for (int i = 0; i < this.distances.length; i++) {
            for (int j = 0; j < this.distances.length; j++) {
                res += this.distances[i][j];
                res += "\t";
            }
            res += "\n";
        }
        return res;
    }

}
