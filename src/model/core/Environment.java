package model.core;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;

import model.core.population.People;
import view.Observer;

public class Environment implements Observable {

    public Agent[][] grid;

    public ArrayList<Observer> observers;

    public ArrayList<Agent> agents;

    public Type agentSupertype;


    public Environment(int size, Type agentSupertype) {
        super();
        this.grid = new Agent[size][size];
        this.agentSupertype = agentSupertype;
        this.observers = new ArrayList<Observer>();
        this.agents = new ArrayList<Agent>();
    }

    /**
     * Play the party.
     */
    public void run() {
        while (true) {
            doIt();
        }
    }

    /**
     * Play one iteration/turn of the system.
     */
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

        float generalSatisfaction = 0;
        for (Agent a : this.agents) {
            People p = (People) this.grid[a.getPosX()][a.posY];
            generalSatisfaction = generalSatisfaction + p.getSatisfaction();
        }
        generalSatisfaction = generalSatisfaction / this.agents.size();
        System.out.println(generalSatisfaction);

    }

    /**
     * Search position of occurences of the target class, when located around the box at posX, posY.
     * Example, with the following grid
     * ---------------------
     *  Agent   null    Shark  
     *  null    Agent   null
     *  --------------------
     *  For the Agent at (0, 0) if target is set to "null" the result is [(0, 1), (1, 0)].
     *  For the Agent at (0, 0) if target is set to "Agent" the result is [(1, 1)].
     * @param posX x coordinate
     * @param posY y coordinate
     * @param target type of Object
     * @return arraylist with an array of the position (x, y) for each solution
     */
    public ArrayList<int[]> search(int posX, int posY, Agent[][] grid, String target) {
        if (grid.length > 1 | grid[0].length > 1) {
            ArrayList<int[]> positionSets = new ArrayList<int[]>();
            int[] values = new int[3];
            values[0] = -1;
            values[1] = 0;
            values[2] = 1;
            // x and y refer to a relative shift of posX, posY
            for (int x : values) {
                // check if the row at x + posX is inbound
                if ((x + posX >= 0) & (x + posX < grid.length)) {
                    for (int y : values) {
                        // check if the column at y + posY is inbound
                        if ((y + posY >= 0) & (y + posY < this.grid.length)) {
                            // omit the value exactly at posX, posY
                            if (!((x == 0) & (y == 0))) {
                                boolean saveIt = false;
                                if ((target == null)
                                        & (grid[x + posX][y + posY] == null)) {
                                    saveIt = true;
                                } else if ((grid[x + posX][y + posY] != null)) {
                                    if (grid[x + posX][y + posY].getClass().getName().equals(target)) {
                                        saveIt = true;
                                    }
                                }
                                if (saveIt) { // save values
                                    int[] position = new int[2];
                                    position[0] = x + posX;
                                    position[1] = y + posY;
                                    positionSets.add(position);
                                    saveIt = false;
                                }
                            }
                        }
                    } // for y
                } // for x
            }
            return positionSets;
        }
        return null;
    }
    
    /**
     * Search position of occurences of the target class, when located around the box at posX, posY.
     * Example, with the following grid
     * ---------------------
     *  Agent   null    Shark  
     *  null    Agent   null
     *  --------------------
     *  For the Agent at (0, 0) if target is set to "null" the result is [(0, 1), (1, 0)].
     *  For the Agent at (0, 0) if target is set to "Agent" the result is [(1, 1)].
     * @param posX x coordinate
     * @param posY y coordinate
     * @param target type of Object
     * @return arraylist with an array of the position (x, y) for each solution
     */
    public ArrayList<int[]> searchType(int posX, int posY, Agent[][] grid, Type target) {
        if (grid.length > 1 | grid[0].length > 1) {
            ArrayList<int[]> positionSets = new ArrayList<int[]>();
            int[] values = new int[3];
            values[0] = -1;
            values[1] = 0;
            values[2] = 1;
            // x and y refer to a relative shift of posX, posY
            for (int x : values) {
                // check if the row at x + posX is inbound
                if ((x + posX >= 0) & (x + posX < grid.length)) {
                    for (int y : values) {
                        // check if the column at y + posY is inbound
                        if ((y + posY >= 0) & (y + posY < this.grid.length)) {
                            // omit the value exactly at posX, posY
                            if (!((x == 0) & (y == 0))) {
                                boolean saveIt = false;
                                if ((target == null)
                                        & (grid[x + posX][y + posY] == null)) {
                                    saveIt = true;
                                } else if ((grid[x + posX][y + posY] != null)) {
                                    if (grid[x + posX][y + posY].getClass().getSuperclass().equals(target)) {
                                        saveIt = true;
                                    }
                                }
                                if (saveIt) { // save values
                                    int[] position = new int[2];
                                    position[0] = x + posX;
                                    position[1] = y + posY;
                                    positionSets.add(position);
                                    saveIt = false;
                                }
                            }
                        }
                    } // for y
                } // for x
            }
            return positionSets;
        }
        return null;
    }

    /**
     * Find a random box in the grid without any agent.
     * 
     * @return [posX, posY]
     */
    public int[] findEmptyPosition() {
        int[] result = new int[2];
        // find random X and Y positions
        int posX_random = (int) Math.round(Math.random()
                * (this.grid.length - 1));
        int posY_random = (int) Math.round(Math.random()
                * (this.grid.length - 1));

        boolean positionAvailable = false;

        // how many positions are tested
        int countTests = 0;
        // find a random position free if still available
        while ((!positionAvailable)
                & (countTests < (this.grid.length * this.grid[0].length))) {
            countTests++;
            if (this.grid[posX_random][posY_random] == null) {
                result[0] = posX_random;
                result[1] = posY_random;
                return result;

            } else {
                posX_random = (int) Math.round(Math.random()
                        * (this.grid.length - 1));
                posY_random = (int) Math.round(Math.random()
                        * (this.grid.length - 1));
            }
        }
        return null;
    }

    public void addAgent(Agent agent) {
        this.grid[agent.getPosX()][agent.getPosY()] = agent;
        this.agents.add(agent);
    }

    public void removeAgent(int posX, int posY) {
        this.grid[posX][posY] = null;
        for (int a = 0; a < this.agents.size(); a++) {
            if ((this.agents.get(a).getPosX() == posX)
                    & (this.agents.get(a).getPosY() == posY)) {
                this.agents.remove(a);
                this.grid[posX][posY] = null;
            }
        }
    }

    public void updateAgentsList() {
        // update agents collections
        this.agents.clear();
        for (int x = 0; x < this.grid.length; x++) {
            for (int y = 0; y < this.grid[x].length; y++) {
                if (this.grid[x][y] != null) {
                    if (this.grid[x][y].getClass().getSuperclass()
                            .equals(this.agentSupertype)) {
                        this.agents.add(this.grid[x][y]);
                    }
                }
            }
        }
    }

    public Agent[][] getGrid() {
        return this.grid;
    }
    
    public void setGrid(Agent[][] grid) {
        this.grid = grid;
    }
    
    public ArrayList<Agent> getAgents() {
        return agents;
    }

    public void setAgents(ArrayList<Agent> agents) {
        this.agents = agents;
    }

    /**
     * Return a readable form of the grid with agents.
     */
    public String toString() {
        String res = "";
        for (int i = 0; i < this.grid.length; i++) {
            for (int j = 0; j < this.grid[i].length; j++) {
                res += this.grid[i][j];
                res += "\t";
            }
            res += "\n";
        }
        return res;
    }

    @Override
    public void attach(Observer observeur) {
        this.observers.add(observeur);
    }

    @Override
    public void remove(Observer observer) {
        this.observers.remove(observer);
    }

    @Override
    public void notifyObserver() {
        for (Observer observer : this.observers) {
            observer.update();
        }
    }

}
