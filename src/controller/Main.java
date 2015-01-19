package controller;

import sun.org.mozilla.javascript.xml.XMLLib.Factory;
import view.MainFrame;
import model.EnvironmentFactory;
import model.core.Agent;
import model.core.Environment;
import model.core.population.People;
import model.core.population.PeopleType1;
import model.core.population.PeopleType2;
import model.core.water.Fish;
import model.core.water.WaterEnvironment;

public class Main {

    public static void main(String[] args) {

        // -------------- tp 2 population ----------------
        Environment city = EnvironmentFactory.createAndInitializeCity(6, 3, 3, 1);

        // initiate view
        MainFrame view = new MainFrame(city);
        // attach view as observer of the model
        city.attach(view);
        view.setVisible(true);
        
        float generalSatisfaction = 0;
        for (Agent a : city.getAgents()) {
            System.out.println(a.getClass().getName());
            People p = (People) city.grid[a.getPosX()][a.posY];
            generalSatisfaction = generalSatisfaction + p.getSatisfaction();
            System.out.println("People sat' : "+p.getSatisfaction());
        }
        generalSatisfaction = generalSatisfaction / city.agents.size();
        System.out.println("GeneralSatisfaction : "+generalSatisfaction);

        

        /*
        WaterEnvironment water = EnvironmentFactory.createAndInitializeWater(10, 8, 8, 1, 3, 3);
        MainFrame view = new MainFrame(water);
        // attach view as observer of the model
        water.attach(view);
        view.setVisible(true);
        */

        /*
        // ---------------- tp 1 fish ------------------
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    
                    // initiate model
                    WaterEnvironment water = new WaterEnvironment(60, Fish.class, 450, 250);
                    //WaterEnvironment water = new WaterEnvironment(10, Fish.class, 8, 8);
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
        */
        
        
    }
}
