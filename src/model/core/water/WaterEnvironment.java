package model.core.water;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;

import view.Observer;
import model.core.Environment;

public class WaterEnvironment extends Environment {

    public Fish[][] grid;

    public int nbFish;

    public int nbShark;

    public int tour;

    public ArrayList<Fish> agents;

    public ArrayList<Observer> observers;

    public double averageFishAge;

    public double averageSharkAge;

    public int turn = 0;

    public WaterEnvironment(int size, Type agentSupertype, int nbFish, int nbShark) {
        super(size, agentSupertype);
        this.grid = new Fish[size][size];
        this.nbFish = nbFish;
        this.nbShark = nbShark;
        this.tour = 0;
        this.averageFishAge = 0;
        this.averageSharkAge = 0;
        this.agents = new ArrayList<Fish>(nbFish + nbShark);
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
        // ----------------- stats -----------------
        // outputs are used to build graph 'n statistics
        // 1 a curve of the number of fish over time (a second one respectively
        // for Sharks)
        // System.out.println(this.nbFish + "\t" + this.nbShark);

        // 2 age structure
        // System.out.println(this.averageFishAge + "\t" +
        // this.averageSharkAge);

        // 3 number of fish / number of sharks (goal an ellipse)
        // System.out.println(this.nbFish + "\t" + this.nbShark + "\t" +
        // this.nbFish / this.nbShark);

        // 4 number of shark for each age
        /*
         * HashMap hm = new HashMap(); for (int a = 0; a < this.agents.size();
         * a++) { if (!hm.containsKey(this.agents.get(a).getAge())) {
         * hm.put(this.agents.get(a).getAge(), 1); } else {
         * hm.put(this.agents.get(a).getAge(),
         * Integer.parseInt(hm.get(this.agents.get(a).getAge()).toString()) +
         * 1); } } System.out.println(hm.toString());
         */
    }

    

    @Override
    public void updateAgentsList() {
        // update agents collections (births, dead)
        this.agents.clear();
        this.nbFish = 0;
        this.nbShark = 0;
        for (int x = 0; x < this.grid.length; x++) {
            for (int y = 0; y < this.grid[x].length; y++) {
                if (this.grid[x][y] != null) {
                    if (this.grid[x][y].getClass().getSuperclass()
                            .equals(Fish.class)) {
                        if (this.grid[x][y].getClass() == Tuna.class) {
                            this.nbFish++;
                            this.averageFishAge = this.averageFishAge
                                    + this.grid[x][y].getAge();
                        } else {
                            this.nbShark++;
                            this.averageSharkAge = this.averageSharkAge
                                    + this.grid[x][y].getAge();
                        }
                        this.agents.add(this.grid[x][y]);
                    }
                }
            }
        }
        if (this.nbFish != 0) {
            this.averageFishAge = this.averageFishAge / this.nbFish;
        }
        if (this.nbShark != 0) {
            this.averageSharkAge = this.averageSharkAge / this.nbShark;
        }
    }

    public void initiateGrid() {
        int nbFish_count = nbFish;
        int nbShark_count = nbShark;

        // place agents into the grid
        while ((nbFish_count | nbShark_count) > 0) {
            // random = 0 place a fish or random = 1 place a shark
            int fishOrShark = (int) Math.round(Math.random() * (1));

            Fish agent;
            int[] availablePosition = findEmptyPosition();
            int posX = availablePosition[0];
            int posY = availablePosition[1];

            // create a fish, put it the grid, and referenced it in the list of
            // agents
            if ((fishOrShark == 0) && (nbFish_count > 0)) {
                agent = new Tuna(posX, posY, 1, this);
                this.agents.add(agent);
                this.grid[posX][posY] = agent;
                nbFish_count--;
            }
            // create a shark, put it in the grid, and referenced it in the list
            // of agents
            else if ((fishOrShark == 1) && (nbShark_count > 0)) {
                agent = new Shark(posX, posY, 3, 3, this);
                this.agents.add(agent);
                this.grid[posX][posY] = agent;
                nbShark_count--;
            }
        }
    }

    public Fish[][] getGrid() {
        return grid;
    }

    public void setGrid(Fish[][] grid) {
        this.grid = grid;
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

}