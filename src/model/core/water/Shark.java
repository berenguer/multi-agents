package model.core.water;

import java.util.ArrayList;
import java.util.Random;

public class Shark extends Fish {

    /**
     * Turns required before death.
     */
    public int deathDelay;

    /**
     * Current number of turns remaining before death.
     */
    public int deathDecount;
    
    ArrayList<int[]> neighborTunas;

    public Shark(int posX, int posY, int birthDelay, int deathDelay, WaterEnvironment environment) {
        super(posX, posY, birthDelay, environment);
        this.deathDelay = deathDelay;
        this.deathDecount = deathDelay;
        this.neighborTunas = new ArrayList<int[]>();
    }

    @Override
    public void action() {
        this.neighborPositions = this.environment.search(this.posX, this.posY, this.environment.getGrid(),
                null);
        this.neighborTunas = this.environment.search(this.posX, this.posY, this.getEnvironment().getGrid(),
                Tuna.class.getName());
        if (this.deathDecount <= 0) {
            this.environment.grid[this.posX][this.posY] = null;
            //System.out.println("Shark.action() --> death");
        } else if ((birthDecount <= 0) & (this.neighborPositions.size() > 0)) {
            birth();
            //System.out.println("Shark.action() --> birth");
        } else if (this.neighborTunas.size() > 0) {
            eat();  
            // eating restore the life
            this.deathDecount = this.deathDelay;
            //System.out.println("Shark.action() --> eat");
        } else if (this.neighborPositions.size() > 0) {
            move();
            //System.out.println("Shark.action() --> move");
        }
        this.age++;
        this.birthDecount--;
        this.deathDecount--;
    }

    @Override
    public void birth() {
        // selected a random free position around the box
        Random random = new Random();
        int[] kidPosition = this.neighborPositions.get(random.nextInt(this.neighborPositions.size()));
        Shark kid = new Shark(kidPosition[0], kidPosition[1], this.birthDelay, this.deathDelay, this.environment);
        // update the grid
        this.environment.grid[kid.getPosX()][kid.getPosY()] = kid;
        // reset counter before the next birth
        this.birthDecount = Integer.valueOf(this.birthDelay);
    }

    public void eat() {
        // selected a random fish around the box
        Random random = new Random();
        int[] eatenFishPosition = this.neighborTunas.get(random.nextInt(this.neighborTunas.size()));
        // delete eaten fish
        this.environment.removeAgent(eatenFishPosition[0], eatenFishPosition[1]);
        // remove this from the grid
        this.environment.grid[this.posX][this.posY] = null;
        // set positions to the eaten fish
        this.posX = eatenFishPosition[0];
        this.posY = eatenFishPosition[1];
        // update the grid with the new position
        this.environment.grid[eatenFishPosition[0]][eatenFishPosition[1]] = this;
    }

    @Override
    public String toString() {
        return "S";
    }

}
