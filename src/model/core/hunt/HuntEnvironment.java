package model.core.hunt;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;

import model.core.Agent;
import model.core.Environment;

public class HuntEnvironment extends Environment {
    
    public Prey prey;

    public HuntEnvironment(int size, Type agentSupertype) {
        super(size, agentSupertype);
    }
    
    /**
     * Play one iteration/turn of the system.
     */
    @Override
    public void doIt() {
        // order of agents in the turn is random
        Collections.shuffle(this.agents);
        // prey action then hunters actions
        //this.prey.action();
        for (int i = 0; i < this.agents.size(); i++) {
            // System.out.println("Agent at "+String.valueOf(this.agents.get(i).getPosX()+1)
            // + " : " + String.valueOf(this.agents.get(i).getPosY()+1));
            this.agents.get(i).action();
        }
        notifyObserver();
        updateAgentsList();

    }
    
    public Prey getPrey() {
        return prey;
    }

    public void addPrey(Prey prey) {
        this.grid[prey.getPosX()][prey.getPosY()] = prey;
        this.prey = prey;
    }

}
