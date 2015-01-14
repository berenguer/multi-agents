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

    public Shark(int posX, int posY, int birthDelay, int deathDelay, WaterEnvironment water) {
        super(posX, posY, birthDelay, water);
        this.deathDelay = deathDelay;
        this.deathDecount = deathDelay;
    }

    @Override
    public void action() {
        if (this.deathDecount == 0) {
            this.water.grid[this.posX][this.posY] = null;
            //System.out.println("Shark.action() --> death");
        } else {
            this.age++;
            if ((birthDecount == 0) & (this.water.search(this.posX, this.posY, null).size() > 0)) {
                birth();
                //System.out.println("Shark.action() --> birth");
            } else if (this.water.search(this.posX, this.posY, Tuna.class).size() > 0) {
                eat();
                //System.out.println("Shark.action() --> eat");
            } else if (this.water.search(this.posX, this.posY, null).size() > 0) {
                move();
                //System.out.println("Shark.action() --> move");
            }
        }
    }    
    
    @Override
    public void birth() {
        // selected a random free position around the box
        ArrayList<int[]> freePositions = this.water.search(this.posX, this.posY, null);
        Random random = new Random();
        int[] kidPosition = freePositions.get(random.nextInt(freePositions.size()));
        Shark kid = new Shark(kidPosition[0], kidPosition[1], this.birthDelay, this.deathDelay, this.water);
        // update the grid
        this.water.grid[kid.getPosX()][kid.getPosY()] = kid;
        // reset counter before the next birth
        this.birthDecount = Integer.valueOf(this.birthDelay);
    }

    public void eat() {
        // eating restore the life
        this.deathDecount = this.deathDelay;
        this.birthDecount--;
        // selected a random fish around the box
        ArrayList<int[]> fishPositions = this.water.search(this.posX, this.posY, Tuna.class);
        Random random = new Random();
        int[] eatenFishPosition = fishPositions.get(random.nextInt(fishPositions.size()));
        // delete eaten fish
        this.water.removeAgent(eatenFishPosition[0], eatenFishPosition[1]);
        // remove this from the grid
        this.water.grid[this.posX][this.posY] = null;
        // set positions to the eaten fish
        this.posX = eatenFishPosition[0];
        this.posY = eatenFishPosition[1];
        // update the grid with the new position
        this.water.grid[eatenFishPosition[0]][eatenFishPosition[1]] = this;
    }
    
    public void move() {
        this.deathDecount--;
        this.birthDecount--;
        // selected a random free position around the box
        ArrayList<int[]> freePositions = this.water.search(this.posX, this.posY, null);
        Random random = new Random();
        // use one available position
        int[] nextPosition = freePositions.get(random.nextInt(freePositions.size()));
        // remove this from the grid
        this.water.grid[this.posX][this.posY] = null;
        this.posX = nextPosition[0];
        this.posY = nextPosition[1];
        // update the grid with the new position
        this.water.grid[this.posX][this.posY] = this;
    }

    public int getBirthClassDelay() {
        return birthDelay;
    }

    public int getBirthDecount() {
        return birthDecount;
    }

    public int getDeathClassDelay() {
        return deathDelay;
    }

    public int getDeathDecount() {
        return deathDecount;
    }

    public void setBirthClassDelay(int birthClassDelay) {
        this.birthDelay = birthClassDelay;
    }

    public void setBirthDecount(int birthDecount) {
        this.birthDecount = birthDecount;
    }

    public void setDeathClassDelay(int deathClassDelay) {
        this.deathDelay = deathClassDelay;
    }

    public void setDeathDecount(int deathDecount) {
        this.deathDecount = deathDecount;
    }

    @Override
    public String toString() {
        return "S";
    }
    
}
