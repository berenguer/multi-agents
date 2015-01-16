package controller;

import java.awt.EventQueue;

import view.MainFrame;
import model.core.water.WaterEnvironment;

public class Main {

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    // initiate model
                    WaterEnvironment water = new WaterEnvironment(20, 20, 20);
                    System.out.println("water2"+water.grid);
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
