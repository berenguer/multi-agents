package model.core.water;

import java.util.ArrayList;
import java.util.Random;

public class Tuna extends Fish {

    public Tuna(int posX, int posY, int birthDelay, WaterEnvironment water) {
        super(posX, posY, birthDelay, water);
        this.water = water;
    }

    @Override
    public void action() {
        this.age++;
        if ((birthDecount == 0) & (this.water.search(this.posX, this.posY, null).size() > 0)) {
            birth();
            //System.out.println("Fish.action() --> birth");
        } else if (this.water.search(this.posX, this.posY, null).size() > 0) {
            move();
            //System.out.println("Fish.action() --> move");
        }
    }
    
    @Override
    public void birth() {
        System.out.println("TUNA --> BIRTH");
        // selected a random free position around the box
        ArrayList<int[]> freePositions = this.water.search(this.posX, this.posY, null);
        Random random = new Random();
        int[] kidPosition = freePositions.get(random.nextInt(freePositions.size()));
        Tuna kid = new Tuna(kidPosition[0], kidPosition[1], this.birthDelay, this.water);
        // update the grid
        this.water.grid[kid.getPosX()][kid.getPosY()] = kid;
        // reset counter before the next birth
        this.birthDecount = Integer.valueOf(this.birthDelay);
    }
    
    public void move() {
        this.birthDecount--;
        // selected a random free position around the box
        ArrayList<int[]> freePositions = this.water.search(this.posX, this.posY, null);
        Random random = new Random();
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

    public void setBirthClassDelay(int birthClassDelay) {
        this.birthDelay = birthClassDelay;
    }

    public void setBirthDecount(int birthDecount) {
        this.birthDecount = birthDecount;
    }

    @Override
    public String toString() {
        return "F";
    }

}
