package model.core.hunt;

import java.lang.reflect.Type;
import java.util.Collections;

import model.core.Environment;

public class HuntEnvironment extends Environment {
    
    public Prey prey;
    
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
        System.out.println(getDistancesToString());
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
