package controller;

import java.awt.EventQueue;

import view.MainFrame;
import model.core.Environment;
import model.core.population.PeopleChaste;
import model.core.population.People;
import model.core.population.PeopleType1;
import model.core.population.PeopleType2;
import model.core.water.WaterEnvironment;

public class Main {

    public static void main(String[] args) {
        
        // EXO POPULATION
        Environment city = new Environment(4);
        
        People people1 = new PeopleType1(0,  0, city, 1);
        People people2 = new PeopleType2(0,  1, city, 1);
        People people3 = new PeopleType1(1,  1, city, 1);
        city.grid[0][0] = people1;
        city.grid[0][1] = people2;
        city.grid[1][1] = people3;
        System.out.println(city.toString());
        people1.action();
        people2.action();
        people3.action();
        System.out.println(city.toString());
        people1.action();
        people2.action();
        people3.action();
        System.out.println(city.toString());
        
        // ==> faire appel a une factory pour les agents
        
        /*
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
        */
        
    }
}
