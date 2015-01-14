package model.agent;

import java.util.ArrayList;
import java.util.Random;

import model.Environnement;

public class Shark extends Agent {

    /**
     * Turns required before birth.
     */
    public int birthClassDelay;

    /**
     * Current number of turns remaining before making a child.
     */
    public int birthDecount;
    
    /**
     * Turns required before death.
     */
    public int deathClassDelay;

    /**
     * Current number of turns remaining before death.
     */
    public int deathDecount;

    public Shark(int posX, int posY, int birthClassDelay, int deathClassDelay, Environnement env) {
        super(posX, posY, env);
        this.birthClassDelay = birthClassDelay;
        this.birthDecount = birthClassDelay;
        this.deathClassDelay = deathClassDelay;
        this.deathDecount = deathClassDelay;
    }

    @Override
    public void action() {
        if (this.deathDecount == 0) {
            this.env.grid[this.posX][this.posY] = null;
            //System.out.println("Shark.action() --> death");
        } else {
            this.age++;
            if ((birthDecount == 0) & (this.env.search(this.posX, this.posY, null).size() > 0)) {
                birth();
                //System.out.println("Shark.action() --> birth");
            } else if (this.env.search(this.posX, this.posY, Fish.class).size() > 0) {
                eat();
                //System.out.println("Shark.action() --> eat");
            } else if (this.env.search(this.posX, this.posY, null).size() > 0) {
                move();
                //System.out.println("Shark.action() --> move");
            }
        }
    }    
    
    @Override
    public void birth() {
        // selected a random free position around the box
        ArrayList<int[]> freePositions = this.env.search(this.posX, this.posY, null);
        Random random = new Random();
        int[] kidPosition = freePositions.get(random.nextInt(freePositions.size()));
        Shark kid = new Shark(kidPosition[0], kidPosition[1], this.birthClassDelay, this.deathClassDelay, this.env);
        // update the grid
        this.env.grid[kid.getPosX()][kid.getPosY()] = kid;
        // reset counter before the next birth
        this.birthDecount = Integer.valueOf(this.birthClassDelay);
    }

    public void eat() {
        // eating restore the life
        this.deathDecount = this.deathClassDelay;
        this.birthDecount--;
        // selected a random fish around the box
        ArrayList<int[]> fishPositions = this.env.search(this.posX, this.posY, Fish.class);
        Random random = new Random();
        int[] eatenFishPosition = fishPositions.get(random.nextInt(fishPositions.size()));
        // delete eaten fish
        this.env.removeAgent(eatenFishPosition[0], eatenFishPosition[1]);
        // remove this from the grid
        this.env.grid[this.posX][this.posY] = null;
        // set positions to the eaten fish
        this.posX = eatenFishPosition[0];
        this.posY = eatenFishPosition[1];
        // update the grid with the new position
        this.env.grid[eatenFishPosition[0]][eatenFishPosition[1]] = this;
    }
    
    public void move() {
        this.deathDecount--;
        this.birthDecount--;
        // selected a random free position around the box
        ArrayList<int[]> freePositions = this.env.search(this.posX, this.posY, null);
        Random random = new Random();
        // use one available position
        int[] nextPosition = freePositions.get(random.nextInt(freePositions.size()));
        // remove this from the grid
        this.env.grid[this.posX][this.posY] = null;
        this.posX = nextPosition[0];
        this.posY = nextPosition[1];
        // update the grid with the new position
        this.env.grid[this.posX][this.posY] = this;
    }

    public int getBirthClassDelay() {
        return birthClassDelay;
    }

    public int getBirthDecount() {
        return birthDecount;
    }

    public int getDeathClassDelay() {
        return deathClassDelay;
    }

    public int getDeathDecount() {
        return deathDecount;
    }

    public void setBirthClassDelay(int birthClassDelay) {
        this.birthClassDelay = birthClassDelay;
    }

    public void setBirthDecount(int birthDecount) {
        this.birthDecount = birthDecount;
    }

    public void setDeathClassDelay(int deathClassDelay) {
        this.deathClassDelay = deathClassDelay;
    }

    public void setDeathDecount(int deathDecount) {
        this.deathDecount = deathDecount;
    }

    @Override
    public String toString() {
        return "S";
    }
    
}
