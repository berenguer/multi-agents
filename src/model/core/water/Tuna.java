package model.core.water;

import java.util.Random;

public class Tuna extends Fish {  

    public Tuna(int posX, int posY, int birthDelay, WaterEnvironment water) {
        super(posX, posY, birthDelay, water); 
    }

    @Override
    public void action() {
        this.neighborPositions = environment.search(this.posX, this.posY, this.environment.getGrid(),
                null);
        if (this.neighborPositions.size() > 0) {
            if (this.birthDecount <= 0) {
                birth();
                //System.out.println("Fish.action() --> birth");
            } else {
                move();
                //System.out.println("Fish.action() --> move");
            }
        }
        this.age++;
        this.birthDecount--;
    }

    @Override
    public void birth() {
        // selected a random free position around the box
        Random random = new Random();
        int[] kidPosition = this.neighborPositions.get(random.nextInt(this.neighborPositions.size()));
        Tuna kid = new Tuna(kidPosition[0], kidPosition[1], this.birthDelay, this.environment);
        // update the grid
        this.environment.grid[kid.getPosX()][kid.getPosY()] = kid;
        // reset counter before the next birth
        this.birthDecount = Integer.valueOf(this.birthDelay);
    }

    @Override
    public String toString() {
        return "F";
    }

}
