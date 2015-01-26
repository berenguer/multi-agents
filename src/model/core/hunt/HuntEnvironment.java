package model.core.hunt;

import java.lang.reflect.Type;
import java.util.Collections;

import model.core.Agent;
import model.core.Environment;
import model.core.population.People;

public class HuntEnvironment extends Environment {

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

        for (int i = 0; i < this.agents.size(); i++) {
            // System.out.println("Agent at "+String.valueOf(this.agents.get(i).getPosX()+1)
            // + " : " + String.valueOf(this.agents.get(i).getPosY()+1));
            this.agents.get(i).action();
        }
        notifyObserver();
        updateAgentsList();

    }

}
