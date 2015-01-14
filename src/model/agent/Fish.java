package model.agent;

import java.util.ArrayList;
import java.util.Random;

import model.Environnement;

public class Fish extends Agent {
    
    /**
     * Turns required before birth.
     */
    public int birthClassDelay;
    
    /**
     * Current number of turns remaining before making a child.
     */
    public int birthDecount;

    public Fish(int posX, int posY, int birthClassDelay, Environnement env) {
        super(posX, posY, env);
        this.birthClassDelay = birthClassDelay;
        this.birthDecount = birthClassDelay;
    }

    @Override
    public void action() {
        this.age++;
        if ((birthDecount == 0) & (this.env.search(this.posX, this.posY, null).size() > 0)) {
            birth();
            //System.out.println("Fish.action() --> birth");
        } else if (this.env.search(this.posX, this.posY, null).size() > 0) {
            move();
            //System.out.println("Fish.action() --> move");
        }
    }
    
    @Override
    public void birth() {
        // selected a random free position around the box
        ArrayList<int[]> freePositions = this.env.search(this.posX, this.posY, null);
        Random random = new Random();
        int[] kidPosition = freePositions.get(random.nextInt(freePositions.size()));
        Fish kid = new Fish(kidPosition[0], kidPosition[1], this.birthClassDelay, this.env);
        // update the grid
        this.env.grid[kid.getPosX()][kid.getPosY()] = kid;
        // reset counter before the next birth
        this.birthDecount = Integer.valueOf(this.birthClassDelay);
    }
    
    public void move() {
        this.birthDecount--;
        // selected a random free position around the box
        ArrayList<int[]> freePositions = this.env.search(this.posX, this.posY, null);
        Random random = new Random();
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

    public void setBirthClassDelay(int birthClassDelay) {
        this.birthClassDelay = birthClassDelay;
    }

    public void setBirthDecount(int birthDecount) {
        this.birthDecount = birthDecount;
    }

    @Override
    public String toString() {
        return "F";
    }

}
