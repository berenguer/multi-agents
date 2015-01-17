package model.core.water;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;

import view.Observer;
import model.core.Agent;
import model.core.Environment;
import model.core.NumberOfAgentsExceedSizeException;

public class WaterEnvironment extends Environment {

    public Fish[][] grid;

    public int nbFish;

    public int nbShark;

    public int tour;

    public ArrayList<Fish> agents;

    public ArrayList<Observer> observers;

    public TimerTask runTimerTask;

    public Timer timer;

    public double averageFishAge;

    public double averageSharkAge;

    public int turn = 0;

    public WaterEnvironment(int size, int nbFish, int nbShark) throws NumberOfAgentsExceedSizeException {
        super(size);
        try {
            if ((nbFish + nbShark) > (size * size)) {
                throw new NumberOfAgentsExceedSizeException();
            }
            else {
                this.grid = new Fish[size][size];
                this.nbFish = nbFish;
                this.nbShark = nbShark;
                this.tour = 0;
                this.averageFishAge = 0;
                this.averageSharkAge = 0;
                this.agents = new ArrayList<Fish>(nbFish + nbShark);
                this.runTimerTask = new TimerTask() {
                    @Override public void run() {
                        doIt();
                    }
                };
                this.timer = new Timer();
            }
        }
        catch(NumberOfAgentsExceedSizeException ex) {
             System.err.println("Too much fishs for this grid !");
        }
    }

    /**
     * Play the party.
     */
    public void run() {
        this.timer.scheduleAtFixedRate(this.runTimerTask, 0, 1);
    }
    
    /**
     * Play one iteration/turn of the system.
     */
    @Override
    public void doIt() {
        // order of agents in the turn is random
        Collections.shuffle(this.agents);

        for (int i = 0; i < this.agents.size(); i++) {
            //System.out.println("Agent at "+String.valueOf(this.agents.get(i).getPosX()+1) + " : " + String.valueOf(this.agents.get(i).getPosY()+1));
            this.agents.get(i).action();
            
        }
        notifyObserver();
        updateAgentsList();
        // ----------------- stats -----------------
        // outputs are used to build graph 'n statistics
        // 1 a curve of the number of fish over time (a second one respectively for Sharks)
        //System.out.println(this.nbFish + "\t" + this.nbShark);

        // 2 age structure
        //System.out.println(this.averageFishAge + "\t" + this.averageSharkAge);

        // 3 number of fish / number of sharks (goal an ellipse)
        //System.out.println(this.nbFish + "\t" + this.nbShark + "\t" + this.nbFish / this.nbShark);

        // 4 number of shark for each age
        /*
        HashMap hm = new HashMap();
        for (int a = 0; a < this.agents.size(); a++) {
            if (!hm.containsKey(this.agents.get(a).getAge())) {
                hm.put(this.agents.get(a).getAge(), 1);
            } else {
                hm.put(this.agents.get(a).getAge(), Integer.parseInt(hm.get(this.agents.get(a).getAge()).toString()) + 1);
            }
        }
        System.out.println(hm.toString());
        */
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
    @Override
    public ArrayList<int[]> search(int posX, int posY, String target) {
        
        if (this.grid.length > 1 | this.grid[0].length > 1) {            

            ArrayList<int[]> positionSets = new ArrayList<int[]>();
            int[] values = new int[3];
            values[0] = -1;
            values[1] = 0;
            values[2] = 1;
            // x and y refer to a relative shift of posX, posY
            for (int x : values) {
                // check if the row at x + posX is inbound
                if ((x + posX >= 0) & (x + posX < this.grid.length)) {
                    for (int y : values) {
                        // check if the column at y + posY is inbound
                        if ((y + posY >= 0) & (y + posY < this.grid.length)) {
                            // omit the value exactly at posX, posY
                            if (!((x == 0) & (y == 0))) {
                                boolean saveIt = false;
                                if ((target == null) & (this.grid[x + posX][y + posY] == null)) {
                                    saveIt = true;
                                } else if ((this.grid[x + posX][y + posY] != null)) {
                                    if (this.grid[x + posX][y + posY].getClass().getClass().getName() == target) {
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

    @Override
    public void updateAgentsList() {
        // update agents collections (births, dead)
        this.agents.clear();
        this.nbFish = 0;
        this.nbShark = 0;
        for (int x = 0; x < this.grid.length; x++) {
            for (int y = 0; y < this.grid[x].length; y++) {
                if (this.grid[x][y] != null) {
                    if (this.grid[x][y].getClass().getSuperclass().equals(Fish.class)) {
                        if (this.grid[x][y].getClass() == Tuna.class) {
                            this.nbFish++;
                            this.averageFishAge = this.averageFishAge + this.grid[x][y].getAge();
                        } else {
                            this.nbShark++;
                            this.averageSharkAge = this.averageSharkAge + this.grid[x][y].getAge();
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
        while ( (nbFish_count | nbShark_count) > 0 ) {
            // random = 0 place a fish or random = 1 place a shark
            int fishOrShark = (int)Math.round(Math.random() * ( 1 ));

            Fish agent;
            int[] availablePosition = findEmptyPosition();
            int posX =  availablePosition[0];
            int posY =  availablePosition[1];

            // create a fish, put it the grid, and referenced it in the list of agents
            if ( (fishOrShark == 0) && (nbFish_count > 0) ) {
                agent = new Tuna(posX, posY, 1, this);
                this.agents.add(agent);
                this.grid[posX][posY] = agent;
                nbFish_count--;
            }
            // create a shark, put it in the grid, and referenced it in the list of agents
            else if ( (fishOrShark == 1) && (nbShark_count > 0) ) {
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