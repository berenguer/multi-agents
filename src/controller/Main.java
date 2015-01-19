package controller;

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
        Environment city = EnvironmentFactory.createAndInitializeCity(50, 1000, 1000, 0.7f);
        //Environment city = EnvironmentFactory.createAndInitializeCity(20, 20, 20, 2);

        // initiate view
        MainFrame view = new MainFrame(city);
        // attach view as observer of the model
        city.attach(view);
        view.setVisible(true);

        
        /*
        // ---------------- tp 1 fish ------------------
        WaterEnvironment water = EnvironmentFactory.createAndInitializeWater(10, 8, 8, 1, 3, 3);
        MainFrame view = new MainFrame(water);
        // attach view as observer of the model
        water.attach(view);
        view.setVisible(true);
        System.out.println("Number of agents : " + water.agents.size());
        System.out.println("Size of the grid : " + water.grid.length + " x " + water.grid.length);
        System.out.println(water.toString());
        */
        
    }
}
