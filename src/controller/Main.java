package controller;

import java.awt.EventQueue;

import view.MainFrame;
import model.core.Environment;
import model.core.water.WaterEnvironment;

public class Main {

    public static void main(String[] args) {
        
        // EXO POPULATION
        Environment city = new Environment(20);
        // ==> faire appel a une factory pour les agents
        

        //EXO WATER
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    
                    // initiate model
                    //WaterEnvironment water = new WaterEnvironment(60, 450, 250);
                    WaterEnvironment water = new WaterEnvironment(5, 2, 2);
                    water.initiateGrid();

                    // initiate view
                    MainFrame view = new MainFrame(water);

                    // attach view as observer of the model
                    water.attach(view);
                    view.setVisible(true);
                    //Collections.shuffle(env.agents);                    

                    System.out.println("Number of agents : " + water.agents.size());
                    System.out.println("Size of the grid : " + water.grid.length + " x " + water.grid.length);
                    System.out.println(water.toString());

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        
    }
}
