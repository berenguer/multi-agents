package model.core.water;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;

import view.Observer;
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

                for (int i=0;i<size;i++)
                {
                    for (int j=0;j<size;j++)
                    {
                        this.grid[i][j]= null;
                    }
                }
                this.nbFish = nbFish;
                this.nbShark = nbShark;
                this.tour = 0;
                this.averageFishAge = 0;
                this.averageSharkAge = 0;
                this.agents = new ArrayList<Fish>(nbFish + nbShark);
            }

            this.runTimerTask = new TimerTask() {
                
                @Override public void run() {
                    doIt();
                }
            };
            this.timer = new Timer();
            this.observers = new ArrayList<Observer>();
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
    public void doIt() {
        turn++;
        // order of agents in the turn is random
        Collections.shuffle(WaterEnvironment.this.agents);
        
        //Random random = new Random();
        //System.out.println(random.nextInt(this.agents.size()) - 2);

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
    
    public void removeAgent(int posX, int posY) {
        this.grid[posX][posY] = null;
        for (int a = 0; a < this.agents.size(); a++) {
            if ((this.agents.get(a).getPosX() == posX) & (this.agents.get(a).getPosY() == posY)) {
                this.agents.remove(a);
                this.grid[posX][posY] = null;
            }
        }
    }
    
    /**
     * Find a random free position (without any agent) into the grid.
     * @return [posX, posY]
     */
    public int[] findAvailablePosition() {
        int[] result = new int[2];
        // find random X and Y positions
        int posX_random = (int)Math.round(Math.random() * ( this.grid.length-1 ));
        int posY_random = (int)Math.round(Math.random() * ( this.grid.length-1 ));
        
        boolean positionAvailable = false;
        
        // find a random position free if still available
        while (!positionAvailable) {
            if (this.grid[posX_random][posY_random] == null) {
                result[0] = posX_random;
                result[1] = posY_random;
                return result;
                
            } else {
                posX_random = (int)Math.round(Math.random() * ( this.grid.length-1 ));
                posY_random = (int)Math.round(Math.random() * ( this.grid.length-1 ));
            }
        }
        return null;
    }
    
    public void initiateGrid() {
        int nbFish_count = nbFish;
        int nbShark_count = nbShark;

        // place agents into the grid
        while ( (nbFish_count | nbShark_count) > 0 ) {

            // random = 0 place a fish or random = 1 place a shark
            int fishOrShark = (int)Math.round(Math.random() * ( 1 ));
            
            Fish agent;
            int[] availablePosition = findAvailablePosition();
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