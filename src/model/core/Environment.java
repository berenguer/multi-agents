package model.core;

import java.lang.reflect.Type;
import java.util.ArrayList;

import view.Observer;

public class Environment implements Observable {

    public Agent[][] grid;
    
    public ArrayList<Observer> observers;

    public Environment(int size) {
        super();
        this.grid = new Agent[size][size];
        this.observers = new ArrayList<Observer>();
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
    public ArrayList<int[]> search(int posX, int posY, Type target) {
        
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
                                    if (this.grid[x + posX][y + posY].getClass().equals(target)) {
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
